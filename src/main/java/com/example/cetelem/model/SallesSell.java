package com.example.cetelem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@ApiModel(description = "Client's comercial Sell")
@Data(staticConstructor = "of")
@ToString(includeFieldNames = true)
@Builder

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SallesSell {

	@EqualsAndHashCode.Exclude
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@EqualsAndHashCode.Include
	@NotNull
	@ApiModelProperty(notes = "Name of the client's comecial sell - without format contrains")
	private String name;

	@EqualsAndHashCode.Exclude
	@NotNull
	@ApiModelProperty(notes = "Risk Profile")
	private SallesSellRiskProfile risKProfile; // [Baixo, Médio, Elevado]

	@EqualsAndHashCode.Exclude
	@NotNull
	@ApiModelProperty(notes = "Age Ranges to apply to the Commercial Sells")
	private String comercialSellAgeRanges;

	@EqualsAndHashCode.Exclude
	@NotNull
	@ApiModelProperty(notes = "Geographical Area")
	private GeographicalArea geographicalArea; // [Norte, Centro, Sul]

	@JsonIgnore
	@ToString.Exclude
	@Builder.Default
	@ManyToMany(mappedBy = "sallesSell", fetch = FetchType.EAGER)
//	@ManyToMany(mappedBy = "sallesSell", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@EqualsAndHashCode.Exclude
	private Set<Client> clients = new HashSet<Client>();

//	public void setComercialSellAgeRanges(Integer[] vals) {
//		comercialSellAgeRanges = Arrays.stream(vals).map((val) -> Integer.toString(val)).collect(Collectors.joining(","));
//	}
//
//	public Integer[] getComercialSellAgeRanges() {
//		return Arrays.stream(comercialSellAgeRanges.split(",")).map((val) -> Integer.parseInt(val)).toArray(Integer[]::new);
//	}

}
