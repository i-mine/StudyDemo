package InetAddressDemo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * author: dulei
 * date: 18-6-27
 * desc:
 */
public class Main {
    public static void main(String[] args) {
        InetAddress inetAddress;//声明InetAddress对象
        try {
            inetAddress = InetAddress.getLocalHost();
            String address = inetAddress.getHostAddress();
            String host = inetAddress.getHostName();
            String canonicalHostName = inetAddress.getCanonicalHostName();
            byte [] byteAddress = inetAddress.getAddress();
            boolean reachable = inetAddress.isReachable(2000);
            System.out.println("本机IP:" + address);
            System.out.println("主机名:" + host);
            System.out.println("完全限定域名:" + canonicalHostName );
            System.out.println("原始IP地址为:"+byteAddress[0] + '.' + byteAddress[1]+'.' + + byteAddress[2]+'.' + byteAddress[3]);
            System.out.println("是否能到达此IP地址:" + reachable);
            System.out.println("/etc/hosts中指定host的IP:" + InetAddress.getByName("account.jetbrains.com").getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
