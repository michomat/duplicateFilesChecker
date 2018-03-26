package com.kata.duplicates.impl;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.kata.duplicates.impl.FileCollector;


public class FileCollectorTest {

	@Rule
	public TemporaryFolder templateFolder = new TemporaryFolder();
	private File fileA;
	private File fileB;
	private File duplicate;



	@Before
	public void setup() throws IOException {
		File root = templateFolder.newFolder("root");
		
		fileA = File.createTempFile("FileA", ".txt", root);

		fileB = File.createTempFile("FileB", ".txt", root);

		File subFolder = templateFolder.newFolder("root", "sub");

		duplicate = File.createTempFile("FileA", ".txt", subFolder);

	}
	
	@Test
	public void testCollect() throws Exception {
		Collection<File> files = FileCollector.collect(templateFolder.getRoot().getAbsolutePath());
		
		assertThat(files, containsInAnyOrder(fileA, fileB, duplicate));
	}

}
