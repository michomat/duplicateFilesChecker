package com.kata.duplicates.impl;

import java.util.Collection;

import com.kata.duplicates.api.Duplicate;

public class CompareBySizeStrategyImpl extends AbstractCompareStrategy  {

	public CompareBySizeStrategyImpl(Collection<Duplicate> allFiles) {
		super(allFiles);
	}
	
	protected String toCompareable(Duplicate duplicate){
		return String.valueOf(duplicate.getSize());
	}

}
