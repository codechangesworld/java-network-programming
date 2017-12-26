/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.url
 * @file HeadView.java
 * @author zuoguoqing
 * @date 2017年4月27日
 * @version 
 */
package name.zuoguoqing.np.urlconnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * @author zuoguoqing
 *
 */
public class HeaderView {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String urlString = "https://www.baidu.com";

        viewHeader(urlString);
    }

    public static void viewHeader(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            System.out.println("Content-Type: " + connection.getContentType());
            System.out.println(
                    "Content-Length: " + connection.getContentLength());
            System.out.println(
                    "Content-Encoding: " + connection.getContentEncoding());
            System.out.println("Date: " + new Date(connection.getDate()));
            System.out.println(
                    "Last Modified: " + new Date(connection.getLastModified()));
            System.out.println("Experiation Time: "
                    + new Date(connection.getExpiration()));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
