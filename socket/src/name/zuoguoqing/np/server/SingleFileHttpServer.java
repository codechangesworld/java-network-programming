/**
 * Copyright (C) zuoguoqing All Rights Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.server
 * @file SingleFileHttpServer.java
 * @author zuoguoqing
 * @date 2017年4月29日
 * @version 
 */
package name.zuoguoqing.np.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zuoguoqing
 *
 */
public class SingleFileHttpServer {
    public final static Logger LOGGER = Logger.getLogger("name.zuoguoqing.np");

    private final byte[] data;
    private final byte[] header;
    private final String encoding;
    private final int PORT;
    private final int NTHREADS = 10;

    public SingleFileHttpServer(String data, String encoding, String mimeType,
            int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, mimeType, port);
    }

    public SingleFileHttpServer(byte[] data, String encoding, String mimeType,
            int port) throws UnsupportedEncodingException {
        this.data = data;
        this.encoding = encoding;
        this.PORT = port;
        this.header = new String("HTTP/1.0 200 OK\r\n"
                + "Server: OneFile 2.0\r\n" + "Content-length: "
                + this.data.length + "\r\n" + "Content-type: " + mimeType
                + ";charset=" + encoding + "\r\n\r\n")
                        .getBytes(Charset.forName("US-ASCII"));
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(NTHREADS);
        try (ServerSocket server = new ServerSocket(PORT)) {
            LOGGER.log(Level.INFO, "Accepting connection on port: " + PORT);
            LOGGER.log(Level.INFO, "Data to be send:");
            LOGGER.log(Level.INFO, new String(data, encoding));
            while (true) {
                try {
                    Socket connection = server.accept();
                    LOGGER.log(Level.INFO, "client "
                            + connection.getInetAddress().getHostAddress()
                            + " connected on port: " + connection.getPort());
                    pool.submit(new ConnectionHander(connection));
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING,
                            "Exception acceptiong connection ", e);
                } catch (RuntimeException e) {
                    LOGGER.log(Level.SEVERE, "Unexpected error", e);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Cound not start server", e);
        }
    }

    private class ConnectionHander implements Callable<Void> {
        Socket connection;

        public ConnectionHander(Socket connection) {
            this.connection = connection;
        }

        @Override
        public Void call() throws Exception {
            try {
                OutputStream os = new BufferedOutputStream(
                        connection.getOutputStream());
                InputStream is = new BufferedInputStream(
                        connection.getInputStream());
                StringBuffer request = new StringBuffer(80);

                while (true) {
                    int c = is.read();
                    if (c == '\r' || c == '\n' || c == -1) {
                        break;
                    }
                    request.append((char) c);
                }

                if (request.indexOf("HTTP/") != -1) {
                    os.write(header);
                    os.flush();
                }

                os.write(data);
                os.flush();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Error writing to client", e);
            } finally {
                connection.close();
            }

            return null;
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String file = "resource/data.html";
        int port = 10000;
        String encoding = "UTF-8";

        Path path = Paths.get(file);
        try {
            byte[] data = Files.readAllBytes(path);
            String contentType = URLConnection.getFileNameMap()
                    .getContentTypeFor(file);
            SingleFileHttpServer server = new SingleFileHttpServer(data,
                    encoding, contentType, port);
            server.start();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

    }

}
