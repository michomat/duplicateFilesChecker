package com.kata.duplicates.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.kata.duplicates.api.Duplicate;
import com.kata.duplicates.impl.CompareByHashCodeStrategyImpl;
import com.kata.duplicates.impl.DuplicateImpl;

public class CompareByHashCodeStrategyImplTest {
	@Rule
	public TemporaryFolder templateFolder = new TemporaryFolder();
	
	private Duplicate duplicateA;
	private Duplicate duplicateB;
	private Duplicate duplicateC;
	private DuplicateImpl duplicateD;

	private CompareByHashCodeStrategyImpl objUnderTest;


	@Before
	public void setup() throws IOException {

		templateFolder.newFolder("root");
		
		File fileA = templateFolder.newFile("root/FileA");
		FileUtils.writeStringToFile(fileA, "This file A", Charset.defaultCharset());
		duplicateA = new DuplicateImpl(fileA,FileUtils.sizeOf(fileA));

		File fileB = templateFolder.newFile("root/FileB");
		FileUtils.writeStringToFile(fileB, "This file B", Charset.defaultCharset());
		duplicateB = new DuplicateImpl(fileB,FileUtils.sizeOf(fileB));

		templateFolder.newFolder("root","sub");

		File duplicateFile = templateFolder.newFile("root/sub/FileA");
		FileUtils.writeStringToFile(duplicateFile, "This file A", Charset.defaultCharset());
		duplicateC = new DuplicateImpl(duplicateFile, FileUtils.sizeOf(duplicateFile));
		
		templateFolder.newFolder("root" ,"sub","subsub");
		
		File fileC = templateFolder.newFile("root/sub/subsub/FileA");
		FileUtils.writeStringToFile(fileC, "This other file", Charset.defaultCharset());
		duplicateD = new DuplicateImpl(fileC, FileUtils.sizeOf(fileC));

		objUnderTest = new CompareByHashCodeStrategyImpl(Arrays.asList(duplicateA, duplicateB, duplicateC, duplicateD));

	}
	
	
	@Test
	public void testIsDuplicate_Duplicate() throws Exception {
		assertTrue(objUnderTest.isDuplicate(duplicateA));
		assertTrue(objUnderTest.isDuplicate(duplicateC));
	}

	@Test
	public void testIsDuplicate_noDuplicate() throws Exception {
		assertFalse(objUnderTest.isDuplicate(duplicateB));
	}
	
	@Test
	public void testIsDuplicate_noDuplicate_sameNameDifferentSize() throws Exception {
		assertFalse(objUnderTest.isDuplicate(duplicateD));
	}
}
