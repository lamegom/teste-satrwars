package br.com.spring.boot.teste.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Result implements Serializable {

	private String diameter;

	private String climate;

	private String surface_water;

	private String name;

	private String created;

	private String url;

	private String rotation_period;

	private String edited;

	private String terrain;

	private String gravity;

	private String orbital_period;
	@JsonProperty("films")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<String> films;
	@JsonProperty("residents")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<String> residents;
	@JsonProperty("population")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<String> population;

}
