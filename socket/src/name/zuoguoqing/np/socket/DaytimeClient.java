/**
 * Copyright (C) zuoguoqing All Rights Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.socket
 * @file DaytimeClient.java
 * @author zuoguoqing
 * @date 2017年4月28日
 * @version 
 */
package name.zuoguoqing.np.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author zuoguoqing
 *
 */
public class DaytimeClient {
    private final String HOST = "localhost";
    private final int PORT = 10000;

    private void getDaytime() {
        try (Socket socket = new Socket(HOST, PORT);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
            System.out.println("connected with daytime server...");
            System.out.println(in.readLine());
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        getDaytime();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new DaytimeClient().start();
    }

}
