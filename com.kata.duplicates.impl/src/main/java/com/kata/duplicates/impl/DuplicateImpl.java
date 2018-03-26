package com.kata.duplicates.impl;

import java.io.File;

import com.kata.duplicates.api.Duplicate;

public class DuplicateImpl implements Duplicate {

	private final long size;
	private final File file;
	
	public DuplicateImpl(File file, long size) {
		this.file = file;
		this.size = size;
	}
	
	public long getSize(){
		return size;
	}
	
	public File getFile() {
		return file;
	}

	@Override
	public String toString() {
		return  file.getAbsolutePath() + "#" + getSize();
	}
}
