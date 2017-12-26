/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.url
 * @file ContentGetter.java
 * @author zuoguoqing
 * @date 2017年4月27日
 * @version 
 */
package name.zuoguoqing.np.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zuoguoqing
 *
 */
public class ContentGetter {

    

    /**
     * @param args
     */
    public static void main(String[] args) {
        URL url = null;
        String urlString = "https://www.baidu.com";
        
        try {
            url = new URL(urlString);
            Object object = url.getContent();
            System.out.println(object.getClass().getName());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
