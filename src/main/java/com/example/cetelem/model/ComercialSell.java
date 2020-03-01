package com.example.cetelem.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ApiModel(description = "Client's comercial Sell")
@Data(staticConstructor = "of")
@ToString(includeFieldNames = true)
@Builder(builderClassName = "Builder",buildMethodName = "build")

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ComercialSell {

	@EqualsAndHashCode.Include
	@NotNull
	@ApiModelProperty(notes = "Name of the client's comecial sell - without format contrains")
	private String name;

	@EqualsAndHashCode.Exclude
	@NotNull
	@ApiModelProperty(notes = "Risk Profile")
	private ComercialSellRiskProfile risKProfile; // [Baixo, Médio, Elevado]
	
	@EqualsAndHashCode.Exclude
	@NotNull
	@ApiModelProperty(notes = "Age Ranges to apply to the Commercial Sells")
	private ComercialSellAgeRanges comercialSellAgeRanges;
	
	@EqualsAndHashCode.Exclude
	@NotNull
	@ApiModelProperty(notes = "Geographical Area")
	private GeographicalArea geographicalArea; // [Norte, Centro, Sul]

	
	
}
