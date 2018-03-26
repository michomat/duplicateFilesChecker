package com.kata.duplicates.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.kata.duplicates.api.Duplicate;
import com.kata.duplicates.impl.CompareByNameAndSizeStrategyImpl;
import com.kata.duplicates.impl.DuplicateImpl;

public class CompareByNameAndSizeStrategyImplTest {

	@Rule
	public TemporaryFolder templateFolder = new TemporaryFolder();

	private CompareByNameAndSizeStrategyImpl objUnderTest;

	private Duplicate duplicateA;
	private Duplicate duplicateB;
	private Duplicate duplicateC;

	private DuplicateImpl duplicateD;


	@Before
	public void setup() throws IOException {

		templateFolder.newFolder("root");
		
		File fileA = templateFolder.newFile("root/FileA");
		duplicateA = new DuplicateImpl(fileA,1024);

		File fileB = templateFolder.newFile("root/FileB");
		duplicateB = new DuplicateImpl(fileB,256);

		templateFolder.newFolder("root","sub");

		File duplicateFile = templateFolder.newFile("root/sub/FileA");
		duplicateC = new DuplicateImpl(duplicateFile, 1024);
		
		templateFolder.newFolder("root" ,"sub","subsub");
		
		File fileC = templateFolder.newFile("root/sub/subsub/FileA");
		duplicateD = new DuplicateImpl(fileC, 2048);

		objUnderTest = new CompareByNameAndSizeStrategyImpl(Arrays.asList(duplicateA, duplicateB, duplicateC, duplicateD));

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
