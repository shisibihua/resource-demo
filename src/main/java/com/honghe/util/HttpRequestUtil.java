package com.honghe.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;

/**
 * @author caoqian
 */
public class HttpRequestUtil
{
    private static Logger logger = Logger.getLogger(HttpRequestUtil.class);

    public static String sendPost(String url, String query)
            throws IOException
    {
        String resultStr = "";
        PrintStream ps = null;
        BufferedReader bReader = null;
        try {
            URL restURL = new URL(url);

            HttpURLConnection conn = (HttpURLConnection)restURL.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("Accept-Charset", "utf-8");

            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            conn.setRequestMethod("POST");

            conn.setDoOutput(true);

            conn.setAllowUserInteraction(false);

            ps = new PrintStream(conn.getOutputStream());
            ps.write(query.getBytes());

            bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";

            while (null != (line = bReader.readLine())) {
                resultStr = resultStr + line;
            }
        }
        catch (Exception e) {
            resultStr = "";
            logger.error("调用接口异常，url=" + url + ",params=" + query);
        } finally {
            ps.close();
            bReader.close();
        }
        return resultStr;
    }

    public static String sendGet(String url, String query) throws IOException {
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        String mPath = url +"?"+ query;
        try
        {
            logger.debug("get请求地址url=" + mPath);
            System.out.println("get请求地址url=" + mPath);

            URL restURL = new URL(mPath);
            HttpURLConnection conn = (HttpURLConnection)restURL.openConnection();
            conn.setRequestMethod("GET");

            conn.setDoOutput(false);
            conn.setDoInput(true);

            conn.setConnectTimeout(120000);
            conn.setReadTimeout(120000);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.connect();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line="";
            while (null != (line = in.readLine()))
            {
                result.append(line);
            }
        } catch (Exception e) {
            logger.error("get请求url异常，url=" +mPath);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                in.close();
                logger.error("流关闭异常!", ex);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        String url = ConfigUtil.getInstance().getConfig("url").toString();
        String param = ConfigUtil.getInstance().getConfig("param").toString();
        System.out.print(sendGet(url, param));
    }
}