package com.news.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class FetchControllerHelperService {
	public HttpEntity generateHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "http://localhost:4200");
		headers.add("Access-Control-Allow-Header", "*");
		HttpEntity entity = new HttpEntity(headers);
		return entity;
	}
}
