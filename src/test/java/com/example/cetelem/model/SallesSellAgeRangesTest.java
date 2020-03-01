package com.example.cetelem.model;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;


class SallesSellAgeRangesTest {

	@Test
	public void testComercialSellAgeRangestEquality() {
		SalesSellAgeRanges comercialSellAgeRanges = SalesSellAgeRanges.builder()
				.lowRange((short) 18)
				.lowRange((short) 19)
				.build();

		SalesSellAgeRanges sameComercialSellAgeRanges = SalesSellAgeRanges.builder()
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
		SalesSellAgeRanges.builder().build();
	}
	
	@Test(expected = RuntimeException.class)
	public void testComercialSellAgeHighRangesNullCheck() {
		SalesSellAgeRanges.builder().lowRange((short)18).build();
	}
}
