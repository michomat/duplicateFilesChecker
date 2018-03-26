package com.kata.duplicates.impl;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;


public final class FileCollector {

	public static Collection<File> collect(String path) {
		File file = new File(path);
		return FileUtils.listFiles(file, null, true);
				
	}

	private FileCollector() {
	}

}
