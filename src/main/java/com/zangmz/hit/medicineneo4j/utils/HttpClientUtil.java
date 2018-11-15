package com.zangmz.hit.medicineneo4j.utils;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class HttpClientUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public static String httpPost(String url,List<NameValuePair> list){
		
		CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        //构建url加密实体，并以utf-8方式进行加密；
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, Consts.UTF_8);
        httppost.setEntity(entity);
        try {
            CloseableHttpResponse response = client.execute(httppost);
            if(response.getStatusLine().getStatusCode()==200){
                //org.apache.http.util.EntityUtils类可以快速处理服务器返回实体对象
                return EntityUtils.toString(response.getEntity());
            }else{
            	logger.error("http return code",response.getStatusLine().getStatusCode());
            	return "";
            }
        } catch (ClientProtocolException e) {
        	logger.error("ClientProtocolException",e);
        	return "";
        } catch (IOException e) {
        	logger.error("IOException",e);
        	return "";
        }
		
	}
	
}
