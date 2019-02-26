package com.landmine.code.consistent_hash;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

interface HashFunction {
    Integer hash(String keyStr);
}

class MD5Hash implements HashFunction {

    public Integer hash(String keyStr) {
        byte[] bKey=mac("MD5", keyStr);
        return ((int) (bKey[3] & 0xFF) << 24)
                | ((int) (bKey[2] & 0xFF) << 16)
                | ((int) (bKey[1] & 0xFF) << 8)
                | (bKey[0] & 0xFF);
    }


    public static byte[] mac(String alga, String str) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(alga);
            return md.digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }

    }

}

public class ConsistentHash<T> {
    private static Log logger = LogFactory.getLog(ConsistentHash.class);
    private final HashFunction hashFunction;
    private final int numberOfReplicas;
    private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas,
                          Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;

        for (T node : nodes) {
            add(node);
        }
    }
    //为集群添加节点,每个节点都有指定数量的虚拟节点位于hash环中
    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashFunction.hash(node.toString() + i), node);
        }
    }
    //移除节点包括该节点所有的虚拟节点
    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunction.hash(node.toString() + i));
        }
    }

    public T get(String keyStr) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = hashFunction.hash(keyStr);

        if (!circle.containsKey(hash)) {//获取大于key的第一个节点，若没有，则使用第一个
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap
                    .firstKey();
        }
        return circle.get(hash);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for(Map.Entry<Integer, T> entry : circle.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        java.util.List<String> nodes = new java.util.ArrayList<String>(3);
        //初始化两个节点的集群
        nodes.add("node1");
        nodes.add("node2");

        ConsistentHash<String> cluster = new ConsistentHash<String>(new MD5Hash(), 32, nodes);

        String strID = "16580";
        //在集群中查询key为16580的数据，返回数据所在的节点
        String node = cluster.get(strID);
        System.out.println("select nodes: " + strID + " in " + node);

        java.util.Map<String, Integer> statMap = testDistribution(cluster);
        System.out.println(statMap);

        cluster.add("node3");
        node = cluster.get(strID);
        System.out.println("add node 3");
        System.out.println("select nodes: " + strID + " in " + node);

        java.util.Map<String, Integer> statMap1 = testDistribution(cluster);
        System.out.println(statMap1);

        cluster.remove("node3");
        node = cluster.get(strID);
        System.out.println("remove node 3");
        System.out.println("select nodes: " + strID + " in " + node);
        java.util.Map<String, Integer> statMap2 = testDistribution(cluster);
        System.out.println(statMap2);
    }

    /**
     * 测试数据在集群节点中分布的数量
     * @param continuum
     * @return
     */
    private static java.util.Map<String, Integer> testDistribution(
            ConsistentHash<String> continuum) {
        System.out.println("每个节点的数据量：");
        java.util.Map<String, Integer> statMap = new java.util.HashMap<String, Integer>();

        for(int i = 10000; i < 20000; i++) {
            String svr = continuum.get("data" + i);//模拟分布式数据查询
//            System.out.println("data"+ i + " in the node: " + svr);
            Integer cnt = statMap.get(svr);
            if(null == cnt) {
                statMap.put(svr, 1);
            } else {
                statMap.put(svr, cnt + 1);
            }
        }
        return statMap;
    }

}
