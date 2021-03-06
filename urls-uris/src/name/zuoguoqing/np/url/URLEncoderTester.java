/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.url
 * @file URLEncoderTester.java
 * @author zuoguoqing
 * @date 2017年4月27日
 * @version 
 */
package name.zuoguoqing.np.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author zuoguoqing
 *
 */
public class URLEncoderTester {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.out.println(
                    URLEncoder.encode("this string has spaces", "UTF-8"));
            System.out.println(
                    URLEncoder.encode("this*string*has*asterisks", "UTF-8"));
            System.out.println(URLEncoder
                    .encode("This%string%has%percent%signs", "UTF-8"));
            System.out.println(
                    URLEncoder.encode("This+string+has+pluses", "UTF-8"));
            System.out.println(
                    URLEncoder.encode("This/string/has/slashes", "UTF-8"));
            System.out.println(URLEncoder
                    .encode("This\"string\"has\"quote\"marks", "UTF-8"));
            System.out.println(
                    URLEncoder.encode("This:string:has:colons", "UTF-8"));
            System.out.println(
                    URLEncoder.encode("This~string~has~tildes", "UTF-8"));
            System.out.println(
                    URLEncoder.encode("This(string)has(parentheses)", "UTF-8"));
            System.out.println(
                    URLEncoder.encode("This.string.has.periods", "UTF-8"));
            System.out.println(
                    URLEncoder.encode("This=string=has=equals=signs", "UTF-8"));
            System.out.println(
                    URLEncoder.encode("This&string&has&ampersands", "UTF-8"));
            System.out.println(URLEncoder
                    .encode("Thiséstringéhasénon-ASCII characters", "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
