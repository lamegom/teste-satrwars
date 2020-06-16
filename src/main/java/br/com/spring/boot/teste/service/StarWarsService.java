package br.com.spring.boot.teste.service;

import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.spring.boot.teste.domain.Result;
import br.com.spring.boot.teste.domain.StarWars;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StarWarsService {

	@Value("${starwars.api.url}")
	private String starWarsUrl;

	@Autowired
	private RestTemplate restTemplate;

	public List<Result> getStarWars() {

		ResponseEntity<StarWars> resp = restTemplate.getForEntity(starWarsUrl , StarWars.class);

		return resp.getStatusCode() == HttpStatus.OK ? resp.getBody().getResults() : null;
	}

	public StarWars getStarWarsByName(String name) {

		ResponseEntity<StarWars> resp = restTemplate.getForEntity(starWarsUrl + "?search=" + name, StarWars.class);
		return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;
	}

	@Bean
	public RestTemplate restTemplate() {
		CloseableHttpClient httpClient = HttpClientBuilder
		        .create()
		        .setRedirectStrategy( new DefaultRedirectStrategy() {

		            @Override
		            public boolean isRedirected(HttpRequest request, HttpResponse response,
		                    HttpContext context) throws ProtocolException {

		                System.out.println(response);

		                // If redirect intercept intermediate response.
		                if (super.isRedirected(request, response, context)){
		                    int statusCode  = response.getStatusLine().getStatusCode();
		                    String redirectURL = response.getFirstHeader("Location").getValue();
		                    System.out.println("redirectURL: " + redirectURL);
		                    return true;
		                }
		                return false;
		            }
		        })
		        .build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		RestTemplate restTemplate = new RestTemplate(factory);
		
		return restTemplate;
	}
}


