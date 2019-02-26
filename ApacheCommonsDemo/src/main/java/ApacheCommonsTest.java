import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.map.LinkedMap;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.core.Ordered;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * author: dulei
 * date: 18-7-11
 * desc:
 */
public class ApacheCommonsTest {
    /**
     * 从一个entity中把属性复制进另外一个entity中
     */
    @Test
    public void testCopyNewBean() throws Exception {
        StuForm form = new StuForm("dulei", 23, 1, new Date(), true);
        Stu stu = new Stu();
        BeanUtils.copyProperties(form, stu);
        System.out.printf(stu.toString());
    }

    /**
     * base64加密
     */
    @Test
    public void testBase64Code() throws Exception {
        String name_1 = "hello,my name is lei";
        System.out.println("Before:" + name_1);

        String name_2 = Base64.encodeBase64String(name_1.getBytes());
        System.out.println("After encode:" + name_2 );

        String name_3 = new String(Base64.decodeBase64(name_2));
        System.out.println("After decode:" +  name_3);

        String url_1 = "www.baidu.com";
        System.out.println("URL Before:" + url_1);

        String url_2 = Base64.encodeBase64URLSafeString(url_1.getBytes());
        System.out.println("URL After decode:" + url_2);

        String url_3 = new String(Base64.decodeBase64(url_2));
        System.out.println("URL After decode:"+ url_3);
    }

    /**
     * commons 下 collection 工具包
     */
    @Test
    public void testCollection() throws Exception {
        OrderedMap<String, Object> om  = new LinkedMap<String, Object>();
        om.put("one", 1);
        om.put("two", 2);
        om.put("three", "three");
        om.put("four", 4);
        om.put("five", "5");
        System.out.println(om.firstKey());
        System.out.println(om.nextKey("four"));
        System.out.println(om.previousKey("five"));
        System.out.println("====================");

        BidiMap bm = new TreeBidiMap();
        bm.put("three", "3");
        bm.put("five", "isfive");
        System.out.println(bm.getKey("isfive").toString());
        System.out.println(bm.getKey("three"));

        //交换key和value
        BidiMap newMap = bm.inverseBidiMap();
        System.out.println(newMap.size());

        System.out.println("======================");

        Bag<Object> bag = new HashBag<Object>();
        bag.add("abc");
        bag.add("def", 3);
        bag.add("ghi", 5);
        System.out.println(bag.size());

        //过滤重复元素
        Set<Object> onlyU = bag.uniqueSet();
        Iterator<Object> i = onlyU.iterator();
        while (i.hasNext()){
            Object o = i.next();
            System.out.println(o.toString());
        }
    }
}

