package com.unistrong.geotsd.datasource.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequest {


    /**
     *@Author:
     *@Description 向指定URL发送GET方法请求
     * @url 发送请求的URL；param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return URL代表远程资源的响应结果
     *@Data:14:14  2018/7/2
     */
    public static String httpGet(String url,String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + new String(param.getBytes("iso-8859-1"),"utf-8");
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
              //  System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader( connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println("请求结果为:  " + result);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
       /* finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }*/
        return result;
    }
    /**
     *@Author:
     *@Description 向指定URL发送POST方法的请求
     * @url 发送请求的URL，param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return result代表远程资源的响应结果
     *@Data:14:19  2018/7/2
     */
    public static String httpPost(String url, String param) {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
/*            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }*/
            OutputStream os = conn.getOutputStream();
            //参数是键值队  , 不以"?"开始
            os.write(param.getBytes());
            //os.write("googleTokenKey=&username=admin&password=5df5c29ae86331e1b5b526ad90d767e4".getBytes());
            os.flush();
            //6, 获取响应的数据
            //得到服务器写回的响应数据
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
             result = br.readLine();
            System.out.println("响应内容为:  " + result);


        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
       /* finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }*/
        return result;
    }
    /**
     *@Author:
     *@Description 向指定URL发送POST方法的请求 传递参数形式时json形式
     *@Data:16:16  2018/7/3
     */
    public static String httpJson(String postData, String postUrl) {
        String result = "";
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();
            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return result;
    }

}
