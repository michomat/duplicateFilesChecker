package com.kata.duplicates.api;

import java.io.File;

public interface Duplicate {
	
	File getFile();
	
	long getSize();
	
	default String getClassifier(){
		return getFile() + "#" + getSize();
	}
}
