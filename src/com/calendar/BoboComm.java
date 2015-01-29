package com.calendar;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class BoboComm {
	
	private HttpClient httpClient = new DefaultHttpClient();
	
	private String getResponse(HttpEntity entity) {
		String url = "http://cold-leaf-59.heroku.com/myevents?format=xml";

	

		String response = "";

		try {
			int length = (int) entity.getContentLength();
			StringBuffer sb = new StringBuffer(length);
			InputStreamReader isr = new InputStreamReader(entity.getContent(),
					"UTF-8");
			char buff[] = new char[length];
			int cnt;
			while ((cnt = isr.read(buff, 0, length - 1)) > 0) {
				sb.append(buff, 0, cnt);
			}

			response = sb.toString();
			isr.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return response;
	}

}
