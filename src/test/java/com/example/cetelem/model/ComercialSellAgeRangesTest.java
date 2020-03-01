package com.example.cetelem.model;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;


class ComercialSellAgeRangesTest {

	@Test
	public void testComercialSellAgeRangestEquality() {
		ComercialSellAgeRanges comercialSellAgeRanges = ComercialSellAgeRanges.builder()
				.lowRange((short) 18)
				.lowRange((short) 19)
				.build();

		ComercialSellAgeRanges sameComercialSellAgeRanges = ComercialSellAgeRanges.builder()
				.lowRange((short) 18)
				.lowRange((short) 19)
				.build();

		then(comercialSellAgeRanges)
			.as("Is the same object")
			.isEqualTo(sameComercialSellAgeRanges);
		then(comercialSellAgeRanges.hashCode())
			.as("Has the same hashcode")
			.isEqualTo(sameComercialSellAgeRanges.hashCode());
	}
	
	@Test(expected = RuntimeException.class)
	public void testComercialSellAgeLowRangesNullCheck() {
		ComercialSellAgeRanges.builder().build();
	}
	
	@Test(expected = RuntimeException.class)
	public void testComercialSellAgeHighRangesNullCheck() {
		ComercialSellAgeRanges.builder().lowRange((short)18).build();
	}
}
