/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.url
 * @file AllHeaders.java
 * @author zuoguoqing
 * @date 2017年4月27日
 * @version 
 */
package name.zuoguoqing.np.urlconnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zuoguoqing
 *
 */
public class AllHeaders {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String urlString = "https://www.taobao.com";
        
        getAllHeaders(urlString);
    }
    
    public static void getAllHeaders(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection con = url.openConnection();
            String headerKey;
            String headerValue;
            for (int i = 1; ; i++) {
                headerKey = con.getHeaderFieldKey(i);
                if (headerKey == null) {
                    break;
                }
                headerValue = con.getHeaderField(i);
                System.out.println(headerKey + " : " + headerValue);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
