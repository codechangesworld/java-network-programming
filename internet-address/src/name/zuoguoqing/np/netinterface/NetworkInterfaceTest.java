/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.netinterface
 * @file NetworkInterfaceTest.java
 * @author zuoguoqing
 * @date 2017年4月26日
 * @version 
 */
package name.zuoguoqing.np.netinterface;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author zuoguoqing
 *
 */
public class NetworkInterfaceTest {
    
    public void listInterfaces() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            System.out.println("List Interfaces: ");
            while (interfaces.hasMoreElements()) {
                System.out.println(interfaces.nextElement());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    
    public void getIPAddress() {
        NetworkInterface ni;
        try {
            ni = NetworkInterface.getByName("wlan0");
            Enumeration<InetAddress> enumeration = ni.getInetAddresses();
            System.out.println("Get IP Address:");
            System.out.println("Display Name: " + ni.getDisplayName());
            System.out.println("Name: " + ni.getName());
            while (enumeration.hasMoreElements()) {
                System.out.println(enumeration.nextElement());
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        NetworkInterfaceTest test = new NetworkInterfaceTest();
        test.listInterfaces();
        
        test.getIPAddress();
    }

}
