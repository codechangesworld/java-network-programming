/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.url
 * @file SourceViewer.java
 * @author zuoguoqing
 * @date 2017年4月27日
 * @version 
 */
package name.zuoguoqing.np.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author zuoguoqing
 *
 */
public class SourceViewer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // if (args == null || args.length != 1) {
        // System.out.println("illeague arguement!");
        // return ;
        // }
        System.out.print("please input url: ");
        Scanner scanner = null;

        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        BufferedReader reader = null;
        try {
            URL url = new URL(input);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String text = null;
            while ((text = reader.readLine()) != null) {
                System.out.println(text);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
