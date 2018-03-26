package com.kata.duplicates.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.codec.digest.DigestUtils;

import com.kata.duplicates.api.Duplicate;

public class CompareByHashCodeStrategyImpl extends AbstractCompareStrategy {
	
	public CompareByHashCodeStrategyImpl(Collection<Duplicate> allFiles){
		super(allFiles);
	}

	protected String toCompareable(Duplicate duplicate){
		try(FileInputStream fis = new FileInputStream(duplicate.getFile())) {
			return DigestUtils.md5Hex(fis);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot calculate md5 for " + duplicate.getFile().getAbsolutePath());
		}
	}
}
