package com.example.cetelem.model;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;


class SallesSellTest {

	@Test
	public void testClientHashEquality() {
		

		SallesSell comercialSell = SallesSell.builder()
				.name("Kids under 20").comercialSellAgeRanges("18,19")
				.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();

		SallesSell sameComercialSell = SallesSell.builder()
				.name("Kids under 20").comercialSellAgeRanges("18,19")
				.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();

		
		then(comercialSell)
			.as("Is the same object")
			.isEqualTo(sameComercialSell);
		then(comercialSell.hashCode())
			.as("Has the same hashcode")
			.isEqualTo(sameComercialSell.hashCode());
	}

}
