/**
 * Copyright (C) zuoguoqing All Rights Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.socket
 * @file DaytimeUDPClient.java
 * @author zuoguoqing
 * @date 2017年12月25日
 * @version 
 */
package name.zuoguoqing.np.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author zuoguoqing
 *
 */
public class DaytimeUDPClient {
    private static final int PORT = 12345;
    private static final String HOSTNAME = "localhost";

    private void getDaytime() {
        try (DatagramSocket socket = new DatagramSocket(0)) {
            System.out.println("open socket...");
            socket.setSoTimeout(10000);
            InetAddress address = InetAddress.getByName(HOSTNAME);
            DatagramPacket request = new DatagramPacket(new byte[1], 1, address,
                    PORT);
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);

            socket.send(request);
            System.out.println("connecting to server...");
            
            socket.receive(response);

            String result = new String(response.getData(), 0,
                    response.getLength(), "US-ASCII");
            System.out.println(result);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        getDaytime();
    }

    public static void main(String[] args) {
        new DaytimeUDPClient().start();
    }
}
