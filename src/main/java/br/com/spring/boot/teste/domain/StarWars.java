package br.com.spring.boot.teste.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StarWars  implements Serializable{
	
	  private float count;
	  private String next = null;
	  private String previous = null;
	  @JsonProperty("results")
	  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	  List<Result> results;

}
