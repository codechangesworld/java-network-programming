/**
 * Copyright (C) zuoguoqing All Rights Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.socket
 * @file DaytimeServer.java
 * @author zuoguoqing
 * @date 2017年4月28日
 * @version 
 */
package name.zuoguoqing.np.socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author zuoguoqing
 *
 */
public class DaytimeServer {

    private final int PORT = 10000;

    private void serve() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("server started!\nwaiting for connected...");
            while (true) {
                try (Socket connection = server.accept();
                        OutputStreamWriter writer = new OutputStreamWriter(
                                connection.getOutputStream());) {
                    System.out.println("connected client: "
                            + connection.getLocalAddress().getHostAddress()
                            + ":" + connection.getPort());
                    writer.write(new Date().toString() + "\r\n");
                    writer.flush();
                    connection.close();
                } catch (IOException e) {
                    System.out.println("connection io exception happens.");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("server io exception happens.");
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
        new DaytimeServer().start();
    }

}
