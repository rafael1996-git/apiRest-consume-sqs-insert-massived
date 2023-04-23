package com.huellitas.serverless.models.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet implements Serializable{

	
	@JsonProperty("idUser")
	@SerializedName("idUser")
	private int idUser;
	@JsonProperty("name")
	@SerializedName("name")
	private String name;
	@JsonProperty("rescueDate")
	@SerializedName("rescueDate")
	private String rescueDate;
	@JsonProperty("idPetType")
	@SerializedName("idPetType")
	private int idPetType;
	@JsonProperty("age")
	@SerializedName("age")
	private String age;
	@JsonProperty("idSize")
	@SerializedName("idSize")
	private int idSize;
	@JsonProperty("idGender")
	@SerializedName("idGender")
	private int idGender;
	@JsonProperty("sterilized")
	@SerializedName("sterilized")
	private int sterilized;
	@JsonProperty("wormed")
	@SerializedName("wormed")
	private int wormed;
	@JsonProperty("vaccinated")
	@SerializedName("vaccinated")
	private int vaccinated;
	@JsonProperty("idDisability")
	@SerializedName("idDisability")
	private int idDisability;
	@JsonProperty("vaccines")
	@SerializedName("vaccines")
	private List<Integer> vaccines;
	@JsonProperty("observations")
	@SerializedName("observations")
	private String observations;
	@JsonProperty("features")
	@SerializedName("features")
	private List<Integer> features;
	@JsonProperty("price")
	@SerializedName("price")
	private int price;


	    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
