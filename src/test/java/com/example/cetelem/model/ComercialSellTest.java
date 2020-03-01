package com.example.cetelem.model;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;


class ComercialSellTest {

	@Test
	public void testClientHashEquality() {
		
		Integer[] range = new Integer[] {18, 19};

		ComercialSell comercialSell = ComercialSell.builder()
				.name("Kids under 20").comercialSellAgeRanges("18,19")
				.geographicalArea(GeographicalArea.NORTH).risKProfile(ComercialSellRiskProfile.LOW).build();

		ComercialSell sameComercialSell = ComercialSell.builder()
				.name("Kids under 20").comercialSellAgeRanges("18,19")
				.geographicalArea(GeographicalArea.NORTH).risKProfile(ComercialSellRiskProfile.LOW).build();

		
		then(comercialSell)
			.as("Is the same object")
			.isEqualTo(sameComercialSell);
		then(comercialSell.hashCode())
			.as("Has the same hashcode")
			.isEqualTo(sameComercialSell.hashCode());
	}

}
