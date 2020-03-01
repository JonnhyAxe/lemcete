package com.example.cetelem.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@ApiModel(description = "Client model")
@Data(staticConstructor = "of")
@ToString(includeFieldNames = true)
@Builder(builderClassName = "Builder",buildMethodName = "build")

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {
	
	@EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@EqualsAndHashCode.Include
	@NotNull
//	@ApiModelProperty(notes = "First Name of the client - without format contrains")
	private String firstName;

	@EqualsAndHashCode.Include
	@NotNull
//	@ApiModelProperty(notes = "Last Name of the client - without format contrains")
	private String lastName;
	
	@EqualsAndHashCode.Exclude
//	@ApiModelProperty(notes = "Email of the client - with format")
	private String email;
	
	@EqualsAndHashCode.Include
	@NotNull
//	@ApiModelProperty(notes = "First Name of the client - without format contrains")
	private short age; // [18 - 70]
	
	@EqualsAndHashCode.Exclude
	@NotNull
//	@ApiModelProperty(notes = "Risk Profile")
	private ClientRiskProfile risKProfile; // [Baixo, Médio, Elevado]
	
	@EqualsAndHashCode.Exclude
	@NotNull
//	@ApiModelProperty(notes = "Geographical Area")
	private GeographicalArea geographicalArea; // [Norte, Centro, Sul]

	static class Builder {
		Client build() {
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
            return new Client(id, firstName, lastName, email, age, risKProfile, geographicalArea);
        }
    }
	
}
