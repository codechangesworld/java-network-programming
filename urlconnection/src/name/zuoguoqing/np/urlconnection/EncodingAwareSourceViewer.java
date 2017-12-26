/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.url
 * @file EncodingAwareSourceViewer.java
 * @author zuoguoqing
 * @date 2017年4月27日
 * @version 
 */
package name.zuoguoqing.np.urlconnection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zuoguoqing
 *
 */
public class EncodingAwareSourceViewer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String url = "https://www.baidu.com";
        
        sourceView(url);
    }

    public static void sourceView(String urlString) {

        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            String encoding = "UTF-8";
            String contentType = connection.getContentType();
            int encodingBegin = contentType.indexOf("charset=");
            if (encodingBegin != -1) {
                encoding = contentType.substring(encodingBegin + 8);
            }
            
            //java 7  try-with-resource
            try (Reader reader = new InputStreamReader(
                    connection.getInputStream(), encoding)) {
                int v = 0;
                while ((v = reader.read()) != -1) {
                    System.out.print((char)v);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
