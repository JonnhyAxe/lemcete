package com.example.cetelem.model;

import java.util.Objects;

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
public class ComercialSellAgeRanges {
	
	@EqualsAndHashCode.Include
	@NotNull
	@ApiModelProperty(notes = "Low range of client's age to apply to the comecial sell")
	private short lowRange;
	
	@EqualsAndHashCode.Include
	@NotNull
	@ApiModelProperty(notes = "High range of client's age to apply to the comecial sell")
	private short highRange;
	
	static class Builder {
		ComercialSellAgeRanges build() {
 
            if (Objects.isNull(lowRange)) {
                throw new RuntimeException("Low age range is null");
            }
            if (Objects.isNull(highRange)) {
                throw new RuntimeException("High age range is null");
            }
            return new ComercialSellAgeRanges(lowRange, highRange);
        }
    }
	
}
