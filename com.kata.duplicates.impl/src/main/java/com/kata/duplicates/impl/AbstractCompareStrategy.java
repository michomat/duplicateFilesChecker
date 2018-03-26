package com.kata.duplicates.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kata.duplicates.api.Duplicate;

public abstract class AbstractCompareStrategy implements FileCompareStrategy {

	private Map<String, List<Duplicate>> duplicatesByName = new HashMap<>();

	protected abstract String toCompareable(Duplicate duplicate);
	
	AbstractCompareStrategy(Collection<Duplicate> allFiles){
		initStrategy(allFiles);
	}

	protected void initStrategy(Collection<Duplicate> allFiles) {
		duplicatesByName = allFiles.parallelStream()
		.collect(Collectors.groupingBy(this::toCompareable));
	}

	public boolean isDuplicate(Duplicate file) {
		Collection<Duplicate> files = duplicatesByName.get(toCompareable(file));
		
		return files.size() >1;
	}

}