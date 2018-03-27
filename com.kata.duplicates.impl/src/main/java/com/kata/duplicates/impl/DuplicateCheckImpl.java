package com.kata.duplicates.impl;

import java.io.File;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import com.kata.duplicates.api.Duplicate;
import com.kata.duplicates.api.DuplicateCheck;
import com.kata.duplicates.api.EModi;

public class DuplicateCheckImpl implements DuplicateCheck{

	@Override
	public Collection<Duplicate> collectCandidates(String path) {
		return collectCandidates(path, EModi.SICE_AND_NAME);
	}	

	@Override
	public Collection<Duplicate> collectCandidates(String path, EModi modi) {
		Collection<File> files = FileCollector.collect(path);
		
		Collection<Duplicate> possibleDuplicates =  files.parallelStream()
				.map(file -> new DuplicateImpl(file, FileUtils.sizeOf(file)))
				.collect(Collectors.toList());
		
		FileCompareStrategy strategy = StrategyFactory.create(modi, possibleDuplicates);
		
		return possibleDuplicates.parallelStream()
				.filter(strategy::isDuplicate)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<Duplicate> checkCandidates(Collection<Duplicate> kandidaten) {
		FileCompareStrategy strategy =	new CompareByHashCodeStrategyImpl(kandidaten);
		
		return kandidaten.parallelStream()
				.filter(strategy::isDuplicate)
				.collect(Collectors.toList());
	}

}
