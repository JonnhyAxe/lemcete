package com.example.cetelem.model;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@ApiModel(description = "Client's comercial Sell")
@Data(staticConstructor = "of")
@ToString(includeFieldNames = true)
@Builder(builderClassName = "Builder", buildMethodName = "build")

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ComercialSell {

	@EqualsAndHashCode.Exclude
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@EqualsAndHashCode.Include
	@NotNull
//	@ApiModelProperty(notes = "Name of the client's comecial sell - without format contrains")
	private String name;

//	@OneToOne(mappedBy = "risKProfile")
	@EqualsAndHashCode.Exclude
	@NotNull
//	@ApiModelProperty(notes = "Risk Profile")
	private ComercialSellRiskProfile risKProfile; // [Baixo, Médio, Elevado]

//	@OneToOne(mappedBy = "comercialSellAgeRanges")
	@EqualsAndHashCode.Exclude
	@NotNull
//	@ApiModelProperty(notes = "Age Ranges to apply to the Commercial Sells")
//	private ComercialSellAgeRanges comercialSellAgeRanges;
	private String comercialSellAgeRanges;

//	@OneToOne(mappedBy = "geographicalArea")
	@EqualsAndHashCode.Exclude
	@NotNull
//	@ApiModelProperty(notes = "Geographical Area")
	private GeographicalArea geographicalArea; // [Norte, Centro, Sul]

//	public void setComercialSellAgeRanges(Integer[] vals) {
//		comercialSellAgeRanges = Arrays.stream(vals).map((val) -> Integer.toString(val)).collect(Collectors.joining(","));
//	}
//
//	public Integer[] getComercialSellAgeRanges() {
//		return Arrays.stream(comercialSellAgeRanges.split(",")).map((val) -> Integer.parseInt(val)).toArray(Integer[]::new);
//	}

}
