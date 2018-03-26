package com.kata.duplicates.impl;


import com.kata.duplicates.api.Duplicate;

public interface FileCompareStrategy {

	boolean isDuplicate(Duplicate file);
}
