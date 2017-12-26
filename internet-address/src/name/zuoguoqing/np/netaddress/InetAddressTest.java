/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.netaddress
 * @file InetAddressTest.java
 * @author zuoguoqing
 * @date 2017年4月26日
 * @version 
 */
package name.zuoguoqing.np.netaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zuoguoqing
 *
 */
public class InetAddressTest {
    
    public void getByName() {
        try {
            InetAddress address = InetAddress.getByName("www.baidu.com");
            System.out.println("get by name:");
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    
    public void getAllByName() {
        try {
            InetAddress[] address = InetAddress.getAllByName("www.taobao.com");
            System.out.println("get all by name:");
            for (InetAddress add : address) {
                System.out.println(add);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    
    public void getByAddress() {
        byte[] add = {(byte)180, 97, 33, 107};
        try {
            InetAddress address = InetAddress.getByAddress("baidu.com", add);
            System.out.println("get by address:");
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        
        
    }
    
    public void getLocalHost() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("get local host:");
            System.out.println(address);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public void getLoopAddress() {
        InetAddress address = InetAddress.getLoopbackAddress();
        System.out.println("get loop back address:");
        System.out.println(address);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        InetAddressTest test = new InetAddressTest();
        test.getByName();
        
        test.getAllByName();
        
        test.getByAddress();
        
        test.getLocalHost();
        
        test.getLoopAddress();
    }
    
    

}
