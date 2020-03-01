package com.example.cetelem.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class SalesSellAgeRanges {
	
	@EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	
	@EqualsAndHashCode.Include
    @Column(unique = true, name = "lowRange")
	@NotNull
	@ApiModelProperty(notes = "Low range of client's age to apply to the comecial sell")
	private Short lowRange;
	
	@EqualsAndHashCode.Include
    @Column(unique = true, name = "highRange")
	@NotNull
	@ApiModelProperty(notes = "High range of client's age to apply to the comecial sell")
	private Short highRange;
    
    
	static class Builder {
		SalesSellAgeRanges build() {
 
            if (Objects.isNull(lowRange)) {
                throw new RuntimeException("Low age range is null");
            }
            if (Objects.isNull(highRange)) {
                throw new RuntimeException("High age range is null");
            }
            return new SalesSellAgeRanges(id, lowRange, highRange);
        }
    }
	
}
