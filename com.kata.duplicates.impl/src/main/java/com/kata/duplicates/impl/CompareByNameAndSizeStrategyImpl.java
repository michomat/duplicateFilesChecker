package com.kata.duplicates.impl;

import java.util.Collection;

import com.kata.duplicates.api.Duplicate;

public class CompareByNameAndSizeStrategyImpl extends  AbstractCompareStrategy {
	
	public CompareByNameAndSizeStrategyImpl(Collection<Duplicate> allFiles){
		super(allFiles);
	}

	protected String toCompareable(Duplicate duplicate){
		return duplicate.getFile().getName() + "#"+ duplicate.getSize();
	}

}
