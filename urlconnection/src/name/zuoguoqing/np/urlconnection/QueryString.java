/**
 * Copyright (C) zuoguoqing All Rights Reserved
 *
 * @description 
 * @package name.zuoguoqing.np.urlconnection
 * @file QueryString.java
 * @author zuoguoqing
 * @date 2017年4月27日
 * @version 
 */
package name.zuoguoqing.np.urlconnection;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author zuoguoqing
 *
 */
public class QueryString {
    private StringBuilder query = new StringBuilder();
    
    public synchronized void add(String key, String value) {
        query.append('&');
        encode(key, value);
    }

    private synchronized void encode(String key, String value) {
        try {
            query.append(URLEncoder.encode(key, "UTF-8"));
            query.append('=');
            query.append(URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized String getQuery() {
        return query.toString();
    }

    @Override
    public String toString() {
        return getQuery();
    }
    
    
}
