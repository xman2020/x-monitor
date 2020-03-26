package x.framework.http;

/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import x.framework.lang.AppException;
import x.framework.lang.StringTools;

/**
 * HTTP工具
 * 
 * @author xman 2016年4月14日
 */
public class HttpTools {

    private static final Logger logger             = LoggerFactory.getLogger(HttpTools.class);

    private static final String HTTP_NOT_200_ERROR = "HTTP_NOT_200_ERROR";

    // private static final String HTTP_FINALLY_ERROR = "HTTP_FINALLY_ERROR";

    /**
     * Post提交
     * @param httpContext 上下文
     * @return 结果（未知：发送了但未拿到结果，失败：没有发送，成功：发送了拿到结果）
     */
    public static HttpResult post(HttpContext httpContext) {
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        String postData = null;
        int phase = 0;
        BufferedReader reader = null;
        StringBuffer result = null;
        HttpResult httpResult = new HttpResult();

        try {
            connection = getPostConnection(httpContext);

            if (httpContext.getParams() != null && httpContext.getParams().size() > 0) {
                out = new DataOutputStream(connection.getOutputStream());
                postData = StringTools.genQueryString(httpContext.getParams());
                out.write(postData.getBytes("UTF-8"));
                out.flush();
            }

            phase = 1;

            if (connection.getResponseCode() == 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                result = new StringBuffer();
                String enter = "";
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(enter).append(line);
                    enter = "\n";
                }

                httpResult.setSuccess(true);
                httpResult.setResult(result.toString());
            } else {
                throw new AppException(HTTP_NOT_200_ERROR, "HTTP Post调用异常|code:" + connection.getResponseCode());
            }
        } catch (Throwable t) {
            if (phase == 0) {
                httpResult.setSuccess(false);
            }
            httpResult.setError(t);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (reader != null) {
                    reader.close();
                }

                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Throwable t) {
                logger.error("http post请求finally处理异常！", t);
            }
        }

        return httpResult;
    }

    /**
     * 通知
     * @param httpContext 上下文
     * @return 结果（未知：发送有异常，失败：没有发送，成功：发送了）
     */
    public static HttpResult notify(HttpContext httpContext) {
        // httpContext.setReadTimeout(100);
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        String postData = null;
        int phase = 0;
        HttpResult httpResult = new HttpResult();

        try {
            connection = getPostConnection(httpContext);

            if (httpContext.getParams() != null && httpContext.getParams().size() > 0) {
                out = new DataOutputStream(connection.getOutputStream());
                postData = StringTools.genQueryString(httpContext.getParams());
                out.write(postData.getBytes("UTF-8"));
                out.flush();
            }

            phase = 1;

            if (connection.getResponseCode() > 0) {
                httpResult.setSuccess(true);
            }
        } catch (Throwable t) {
            if (phase == 0) {
                httpResult.setSuccess(false);
            }
            httpResult.setError(t);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Throwable t) {
                logger.error("http通知finally处理异常！", t);
            }
        }

        return httpResult;
    }

    /**
     * 获取Post连接
     * @param httpContext 上下文
     * @return 连接
     * @throws MalformedURLException
     * @throws IOException
     * @throws ProtocolException
     */
    private static HttpURLConnection getPostConnection(HttpContext httpContext) throws MalformedURLException,
            IOException, ProtocolException {
        URL url = new URL(httpContext.getAddress());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(httpContext.getConnectTimeout());
        connection.setReadTimeout(httpContext.getReadTimeout());
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("User-Agent", "HttpTools");
        connection.connect();

        return connection;
    }

}
