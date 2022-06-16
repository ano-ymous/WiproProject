package com.news.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.news.errors.FetchingError;
import com.news.models.FetchModel;
import com.news.models.NewsModel;
import com.news.services.FetchControllerHelperService;

@RestController
@RequestMapping("/news/api/fetch")
public class FetchController {
//	private String apiKey = "837f153b83484a01b09283c18ac839ab";
//	private String apiKey = "de6c0cfd55c34584b74a3cd51c42283a";
//	private String apiKey = "4d9c69eab8fb49dbb7613457e3e7a719";
	private String apiKey = "bc8fa300484f48c38d71501f52b9e877";
//	private String apiKey = "d9ad0be141bd4621a463035de8436d9d";
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private FetchControllerHelperService fetchHelperService;
	
	@GetMapping(value = {
			"/top-headlines"
	})
	public ResponseEntity<List<NewsModel>> fetchByCategory(
			@RequestParam(value = "category",required=false) Optional<String> category,
			@RequestParam(value = "nation",required=false) Optional<String> nation,
			@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize) throws FetchingError {
		String url = "https://newsapi.org/v2/top-headlines?";
		
		if(category.isPresent())
			url += String.format("category=%s&",category.get()); 
		if(nation.isPresent())
			url += String.format("country=%s&",nation.get());
		
		url += String.format("page=%d&pagesize=%d&apiKey=%s",page,pageSize,apiKey);
		
		RequestEntity<?> request = RequestEntity.get(url).build();
		HttpEntity entity = fetchHelperService.generateHttpEntity();
		
		FetchModel fetchedData = null;
		System.out.println(url);
		try {
			fetchedData = restTemplate.exchange(url,HttpMethod.GET, entity,FetchModel.class).getBody();
		}
		catch(Exception e) {
			throw new FetchingError(e.getMessage());
		}
		
		return ResponseEntity.ok(fetchedData.getArticles());
	}
	@GetMapping(value = {
		"/everything"
	})
	public ResponseEntity<List<NewsModel>> fetchKeyword(
			@RequestParam("keyword") String keyword,
			@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize) throws FetchingError {
		String url = String.format("https://newsapi.org/v2/everything?q=%s&page=%d&pagesize=%d&apiKey=%s",
				keyword,page,pageSize,apiKey);
		
		RequestEntity<?> request = RequestEntity.get(url).build();
		
		HttpEntity entity = this.fetchHelperService.generateHttpEntity();
		
		FetchModel fetchedData = null;
		
		try {
			fetchedData = restTemplate.exchange(url,HttpMethod.GET, entity,FetchModel.class).getBody();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			throw new FetchingError("Erro while fetching");
		}
		
		return ResponseEntity.ok(fetchedData.getArticles());
	}
}
