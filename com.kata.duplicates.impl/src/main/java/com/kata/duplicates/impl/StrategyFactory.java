package com.kata.duplicates.impl;

import java.util.Collection;

import com.kata.duplicates.api.Duplicate;
import com.kata.duplicates.api.EModi;

public final class StrategyFactory {

	public static FileCompareStrategy create(EModi modi, Collection<Duplicate> files){
			
		switch (modi) {
		case SICE_AND_NAME:
			return new CompareByNameAndSizeStrategyImpl(files);
		case SIZE:
			return new CompareBySizeStrategyImpl(files);
		default:
			throw new IllegalArgumentException("Unknown compare modi " + modi.getClass().getName());
		}
	}
	
	private StrategyFactory(){
	}
}
