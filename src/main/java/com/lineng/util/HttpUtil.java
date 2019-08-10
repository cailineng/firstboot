package com.lineng.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
	
	private static String initEncode = "GBK";
	
	/**
	 * http协议 post请求（默认GBK编码）
	 * @param postURL：路径（http://XXXXXX)
	 * @param params：参数（paramA=xx&paramB=xxx&....）对于中文的，需要先对参数进行转码
	 * @return
	 * @throws Exception 
	 */
	public static String doPost(String postURL, String params) throws Exception {
		return doPost(postURL, params, initEncode);
	}
	/**
	 * 
	 * http协议 post请求
	 * @param postURL：路径（http://XXXXXX)
	 * @param params：参数（paramA=xx&paramB=xxx&....）对于中文的，需要先对参数进行转码
	 * @param encode：返回数据编码格式
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String postURL, String params, String encode) throws Exception{
		StringBuffer res = null;
		BufferedReader reader = null;
		HttpURLConnection connection = null;
		try {
			// Post请求的url，与get不同的是不需要带参数
			URL postUrl = new URL(postURL);
			connection = (HttpURLConnection) postUrl.openConnection();
			// http正文内，因此需要设为true
			connection.setDoOutput(true);
			// Read from the connection. Default is true.
			connection.setDoInput(true);
			// Set the post method. Default is GET
			connection.setRequestMethod("POST");
			// Post cannot use caches
			// Post 请求不能使用缓存
			connection.setUseCaches(false);
			// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
			connection.setInstanceFollowRedirects(true);
			// 进行编码
			connection.setRequestProperty("Content-Type",
			"application/x-www-form-urlencoded");
			// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
			// 要注意的是connection.getOutputStream会隐含的进行connect。
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
//		System.out.println(params);
			out.writeBytes(params);
			out.flush();
			out.close(); // flush and close
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encode));// 设置编码,否则中文乱码
			res = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				res.append(line);
//				System.out.println("line:"+line);
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			throw new Exception("无效的POST地址....");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception....");
		} finally{
			try {
				if(reader!=null) reader.close();
				if(connection!=null) connection.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception("Exception close...");
			}
		}
//		System.out.println("http,Post:" + res==null?null:res.toString().trim());
		return res==null?null:res.toString();
	}
	/**
	 * http协议 get请求（默认GBK）
	 * @param getURL：请求路径以及参数（http:/www.xxx.xx?paramA=xx&paramB=xxx&....）对于中文的，需要先对参数进行转码)
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String getURL) throws Exception {
		return doPost(getURL, initEncode);
	}
	/**
	 * http协议 get请求
	 * @param getURL：请求路径以及参数（http:/www.xxx.xx?paramA=xx&paramB=xxx&....）对于中文的，需要先对参数进行转码)
	 * @param encode：返回数据编码格式
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String getURL, String encode) throws Exception {
		StringBuffer res = null;
		BufferedReader reader = null;
		HttpURLConnection connection = null;
		try {
			URL getUrl = new URL(getURL);
			// 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
			// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
			connection = (HttpURLConnection) getUrl.openConnection();
			// 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
			// 服务器
			connection.connect();
			// 取得输入流，并使用Reader读取
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),encode));//设置编码,否则中文乱码
			res = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				res.append(line);
//				System.out.println("line:"+line);
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			throw new Exception("无效的GET地址....");
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(reader!=null) reader.close();
				if(connection!=null) connection.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res==null?null:res.toString();
	}
	

	public static String getRequestHttpResultStr(String httpUrlStr,Map<String,String> params) throws Exception {
        System.out.println(httpUrlStr);
		String resultStr ="";
		HttpPost httpPost2 = new HttpPost(httpUrlStr);
		CloseableHttpClient httpClient2 = HttpClients.createDefault();
		List<NameValuePair> formparams2 = new ArrayList<NameValuePair>();
		
		for (Map.Entry<String, String> entry : params.entrySet()) {
			formparams2.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}

		try {
			HttpEntity reqEntity2 = new UrlEncodedFormEntity(formparams2,
					 "UTF-8");
			httpPost2.addHeader("Content-Type", "application/json");
			httpPost2.setEntity(new StringEntity(JsonUtil.mapToJson(params),"UTF-8"));

			HttpResponse response2 = httpClient2.execute(httpPost2);
			int statusCodeStr=response2.getStatusLine().getStatusCode();
			HttpEntity resEntity = response2.getEntity();
						resultStr = EntityUtils.toString(resEntity, "UTF-8");
			System.out.println("返回值"+resultStr);
			if (statusCodeStr != 200) {
				System.out.println("调用接口:"+httpUrlStr+"错误！");
			}
		} catch (Exception e) {
			System.out.println("调用接口httpUrlStr："+httpUrlStr+" 返回失败!");

			throw e;
		}
		return resultStr;
	}
	
	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
     * @throws URISyntaxException 
	 */
	public static String doGetStr(String url,int count) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		ConnectionKeepAliveStrategy connectionKeepAliveStrategy = new ConnectionKeepAliveStrategy() {
		      @Override
		      public long getKeepAliveDuration(org.apache.http.HttpResponse httpResponse,
		          HttpContext httpContext) {
		        return 20 * 1000; // 20 seconds,because tomcat default keep-alive timeout is 20s
		      }
		    };
		client.setKeepAliveStrategy(connectionKeepAliveStrategy);
		httpGet.addHeader( "User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");   
		String result ="";
		try {
			httpGet.setURI( new java.net.URI(url));
			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			result = "";
			if(entity != null){
			     // 使用EntityUtils的toString方法，传递编码，默认编码是ISO-8859-1   
			    result = EntityUtils.toString(entity, "UTF-8");   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("异常！！！！"+count);
			if(count<5){
				count ++;
				result = doGetStr(url,count);
			}
		}
		return result;
	}
}
