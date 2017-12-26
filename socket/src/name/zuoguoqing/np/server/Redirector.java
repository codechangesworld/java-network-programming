/**
 * Copyright (C) zuoguoqing All Rights Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.server
 * @file Redirector.java
 * @author zuoguoqing
 * @date 2017年4月29日
 * @version 
 */
package name.zuoguoqing.np.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zuoguoqing
 *
 */
public class Redirector {
    public static final Logger LOGGER = Logger.getAnonymousLogger();

    private final int PORT;
    private final String newSite;

    private final int N_THREADS = 10;

    public Redirector(String site, int port) {
        this.PORT = port;
        this.newSite = site;
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(N_THREADS);
        try (ServerSocket server = new ServerSocket(PORT)) {
            LOGGER.log(Level.INFO, "Redirecting connection on port: " + PORT
                    + " to " + newSite);
            while (true) {
                try {
                    Socket client = server.accept();
                    LOGGER.log(Level.INFO,
                            "client " + client.getInetAddress().getHostAddress()
                                    + " connected on port: "
                                    + client.getPort());
                    pool.submit(new RedirectHandler(client));
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING,
                            "exception hanppens to connecting client");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "could not start server", e);
            e.printStackTrace();
        }
    }

    private class RedirectHandler implements Runnable {
        Socket client;

        public RedirectHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                Writer writer = new BufferedWriter(
                        new OutputStreamWriter(client.getOutputStream()));
                Reader reader = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));
                StringBuffer request = new StringBuffer(80);

                while (true) {
                    int c = reader.read();
                    if (c == '\r' || c == '\n' || c == -1) {
                        break;
                    }
                    request.append((char) c);
                }

                String get = request.toString();
                String[] lines = get.split(" ");
                String theFile = lines[1];
                if (get.indexOf("HTTP") != -1) {
                    writer.write("HTTP/1.0 302 DOUND\r\n");
                    writer.write("Date: " + new Date() + "\r\n");
                    writer.write("Server Redirect 1.1\r\n");
                    writer.write("Location: " + newSite + theFile + "\r\n");
                    writer.write("Content-type: text/html\r\n\r\n");
                    writer.flush();
                }

                writer.write(
                        "<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
                writer.write("<BODY><H1>Document moved</H1>\r\n");
                writer.write("The Document " + theFile
                        + " has moved to \r\n<A href=\"" + newSite + theFile
                        + "\">" + newSite + theFile
                        + "</A>.\r\n Please update your bookmarks<p>");
                writer.write("</BODY></HTML>\r\n");
                writer.flush();

                LOGGER.log(Level.INFO,
                        "Redirent to " + client.getRemoteSocketAddress());
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "exception writing to client");
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String newSite = "https://www.baidu.com";
        int port = 10000;
        
        Redirector redirector = new Redirector(newSite, port);
        redirector.start();
    }

}
