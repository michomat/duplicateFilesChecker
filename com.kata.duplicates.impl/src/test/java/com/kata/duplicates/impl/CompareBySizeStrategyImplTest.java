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
import com.kata.duplicates.impl.AbstractCompareStrategy;
import com.kata.duplicates.impl.CompareBySizeStrategyImpl;
import com.kata.duplicates.impl.DuplicateImpl;

public class CompareBySizeStrategyImplTest {

	@Rule
	public TemporaryFolder root = new TemporaryFolder();

	private AbstractCompareStrategy objUnderTest;

	private Duplicate duplicateA;
	private Duplicate duplicateB;
	private Duplicate duplicateC;

	@Before
	public void setup() throws IOException {

		File fileA =File.createTempFile("FileA", ".txt");
		FileUtils.writeStringToFile(fileA, "Test Data A", Charset.defaultCharset());
		duplicateA = new DuplicateImpl(fileA, FileUtils.sizeOf(fileA));

		File fileB = File.createTempFile("FileB", ".txt");
		FileUtils.writeStringToFile(fileB, "A lot of test date in here", Charset.defaultCharset());
		duplicateB = new DuplicateImpl(fileB, FileUtils.sizeOf(fileB));

		File folder = root.newFolder("Folder");

		File fileC = File.createTempFile("FileC", ".txt", folder);
		FileUtils.writeStringToFile(fileC, "Test Data A", Charset.defaultCharset());
		duplicateC = new DuplicateImpl(fileC, FileUtils.sizeOf(fileC));

		objUnderTest = new CompareBySizeStrategyImpl(Arrays.asList(duplicateA, duplicateB, duplicateC));
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
}
