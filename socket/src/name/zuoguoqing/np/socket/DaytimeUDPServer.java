/**
 * Copyright (C) zuoguoqing All Rights Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.socket
 * @file DaytimeUDPServer.java
 * @author zuoguoqing
 * @date 2017年12月25日
 * @version 
 */
package name.zuoguoqing.np.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDateTime;

/**
 * @author zuoguoqing
 *
 */
public class DaytimeUDPServer {
    private static final int PORT = 12345;

    private void serve() {
        try (DatagramSocket server = new DatagramSocket(PORT)) {
            System.out.println("server started...");
            while (true) {
                DatagramPacket request = new DatagramPacket(new byte[10], 10);
                System.out.println("waiting client...");

                server.receive(request);
                System.out.println("recieve data packet from "
                        + request.getAddress().getHostAddress() + ":" + request.getPort());
                
                String daytime = LocalDateTime.now().toString();
                byte[] data = daytime.getBytes("US-ASCII");
                DatagramPacket response = new DatagramPacket(data, data.length,
                        request.getAddress(), request.getPort());
                server.send(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        serve();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new DaytimeUDPServer().start();
    }

}
