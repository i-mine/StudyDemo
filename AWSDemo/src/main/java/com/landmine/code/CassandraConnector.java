package com.landmine.code;

import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;

/**
 * author: dulei
 * date: 18-9-1
 * desc:
 */
public class CassandraConnector {

    private static Cluster cluster;

    private  static Session session;

    public void connect(String node, Integer port) {
        Cluster.Builder b = Cluster.builder().addContactPoint(node);
        PoolingOptions poolingOptions = new PoolingOptions();
        poolingOptions.setConnectionsPerHost(HostDistance.LOCAL, 2, 100);
        poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL,10);
        poolingOptions.setIdleTimeoutSeconds(10);
        poolingOptions.setHeartbeatIntervalSeconds(60);
        poolingOptions.setPoolTimeoutMillis(1000 * 60);
        if (port != null) {
            b.withPort(port).withPoolingOptions(poolingOptions).withRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE)
                    .withReconnectionPolicy(new ConstantReconnectionPolicy(100L))
                    .withSocketOptions(new SocketOptions()
                            .setTcpNoDelay(true)
                            .setConnectTimeoutMillis(6000)
                            .setReadTimeoutMillis(180000))
//                    .withCredentials("cassandra", "cassandra")
                    .withCompression(ProtocolOptions.Compression.LZ4)
                    .withQueryOptions(new QueryOptions().setConsistencyLevel(ConsistencyLevel.ONE))
                    .withLoadBalancingPolicy(new TokenAwarePolicy(DCAwareRoundRobinPolicy.builder().build()));
        }
        cluster = b.build();

        session = cluster.connect();
    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        session.close();
        cluster.close();
    }
}