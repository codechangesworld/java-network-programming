/**
 * Copyright (C) zuoguoqing All Rights Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.urlconnection
 * @file FormPoster.java
 * @author zuoguoqing
 * @date 2017年4月27日
 * @version 
 */
package name.zuoguoqing.np.urlconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zuoguoqing
 *
 */
public class FormPoster {
    private URL url;
    private QueryString query = new QueryString();

    public FormPoster(URL url) {
        if (!url.getProtocol().toLowerCase().startsWith("http")) {
            throw new IllegalArgumentException("Post only works for http URLs");
        }
        this.url = url;
    }

    public void add(String key, String value) {
        query.add(key, value);
    }

    public InputStream post() throws IOException {
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        try (OutputStreamWriter writer = new OutputStreamWriter(
                connection.getOutputStream())) {
            writer.write(query.getQuery());
            writer.write("\r\n\r\n");
            writer.flush();
        }
        
        return connection.getInputStream();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String urlString = "http://www.cafeaulait.org/books/jnp4/postquery.phtml";
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }
        
        FormPoster poster = new FormPoster(url);
        poster.add("name", "Jim");
        poster.add("email", "jim@bibi.com");
        
        try (Reader reader = new InputStreamReader(poster.post())) {
            int v;
            while ((v = reader.read()) != -1) {
                System.out.print((char)v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
