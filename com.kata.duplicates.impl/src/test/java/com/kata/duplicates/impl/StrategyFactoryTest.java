package com.kata.duplicates.impl;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

import com.kata.duplicates.api.EModi;
import com.kata.duplicates.impl.CompareByNameAndSizeStrategyImpl;
import com.kata.duplicates.impl.CompareBySizeStrategyImpl;
import com.kata.duplicates.impl.StrategyFactory;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class StrategyFactoryTest {

	@Test
	public void testCreate_compareOnlySize() throws Exception {
		assertThat(StrategyFactory.create(EModi.SIZE, Collections.emptyList()), instanceOf(CompareBySizeStrategyImpl.class));
	}
	
	@Test
	public void testCreate_compareNameAndSize() throws Exception {
		assertThat(StrategyFactory.create(EModi.SICE_AND_NAME, Collections.emptyList()), instanceOf(CompareByNameAndSizeStrategyImpl.class));
	}
	
	
}
