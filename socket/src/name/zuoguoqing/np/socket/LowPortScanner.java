/**
 * Copyright (C) zuoguoqing All Rights Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.socket
 * @file LowPortScanner.java
 * @author zuoguoqing
 * @date 2017年4月28日
 * @version 
 */
package name.zuoguoqing.np.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * @author zuoguoqing
 *
 */
public class LowPortScanner {

    /**
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 1; i < 1024; i++) {
            Socket socket = null;
            try {
                socket = new Socket("localhost", i);
                System.out.println(
                        "There is a server on port: [" + i + "] of localhost.");
            } catch (Exception e) {
                // System.out.println("some exception on port: " + i);
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
