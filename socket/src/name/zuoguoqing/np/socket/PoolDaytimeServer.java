/**
 * Copyright (C) zuoguoqing All Rights Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.socket
 * @file PoolDaytimeServer.java
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zuoguoqing
 *
 */
public class PoolDaytimeServer {
    private final int PORT = 10000;
    private final int nThreads = 10;
    private final ExecutorService pool = Executors.newFixedThreadPool(nThreads);

    private void serve() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("server started!");
            while (true) {
                System.out.println("waiting for connected...");
                try {
                    Socket connection = serverSocket.accept();
                    System.out.println("connected client: "
                            + connection.getLocalAddress().getHostAddress()
                            + ":" + connection.getPort());
                    pool.submit(new DaytimeTask(connection));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        serve();
    }

    private static class DaytimeTask implements Runnable {
        private Socket connection;

        DaytimeTask(Socket connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try (OutputStreamWriter writer = new OutputStreamWriter(
                    connection.getOutputStream())) {
                writer.write(new Date().toString() + "\r\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new PoolDaytimeServer().start();
    }

}
