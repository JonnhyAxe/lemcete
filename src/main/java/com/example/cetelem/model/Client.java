package com.example.cetelem.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ApiModel(description = "Client model")
@Data(staticConstructor = "of")
@ToString(includeFieldNames = true)
@Builder(builderClassName = "Builder", buildMethodName = "build")

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

	@EqualsAndHashCode.Exclude
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@EqualsAndHashCode.Include
	@NotNull
	@ApiModelProperty(notes = "First Name of the client - without format contrains")
	private String firstName;

	@EqualsAndHashCode.Include
	@NotNull
	@ApiModelProperty(notes = "Last Name of the client - without format contrains")
	private String lastName;

	@EqualsAndHashCode.Exclude
	@ApiModelProperty(notes = "Email of the client - with format")
	@Size(min = 1, max = 100)
	@Pattern(regexp="^([a-zA-Z0-9\\-\\.\\_]+)'+'(\\@)([a-zA-Z0-9\\-\\.]+)'+'(\\.)([a-zA-Z]{2,4})$")
	private String email;

	@EqualsAndHashCode.Include
	@NotNull
	@ApiModelProperty(notes = "First Name of the client - without format contrains")
	@Min(18)
	@Max(70)
	private short age; 

	@EqualsAndHashCode.Exclude
	@NotNull
	@ApiModelProperty(notes = "Risk Profile")
	private ClientRiskProfile risKProfile;  

	@EqualsAndHashCode.Exclude
	@NotNull
	@ApiModelProperty(notes = "Geographical Area")
	private GeographicalArea geographicalArea;  
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@EqualsAndHashCode.Exclude
	@JoinTable(name = "client_salles_map", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = {
			@JoinColumn(name = "salles_sell_id") })
	private Set<SallesSell> sallesSell;

	public static class Builder {
		public Client build() {
			if (age < 18 || age > 70) {
				throw new RuntimeException("Invaid age range [18 - 70]");
			}
			if (Objects.isNull(firstName)) {
				throw new RuntimeException("First Name is null");
			}
			if (Objects.isNull(lastName)) {
				throw new RuntimeException("Last Name is null");
			}
			if (Objects.isNull(risKProfile)) {
				throw new RuntimeException("Risk Profile is null");
			}
			if (Objects.isNull(geographicalArea)) {
				throw new RuntimeException("Geographical Area is null");
			}

			Client client = new Client(id, firstName, lastName, email, age, risKProfile, geographicalArea, sallesSell);
			client.setSallesSell(new HashSet<SallesSell>());
			return client;
		}

	}

}
