package com.kata.duplicates.impl;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.kata.duplicates.api.Duplicate;
import com.kata.duplicates.api.EModi;
import com.kata.duplicates.impl.DuplicateCheckImpl;

public class DuplicateCheckImplTest {

	@Rule
	public TemporaryFolder templateFolder = new TemporaryFolder();

	private File root;

	private File fileA;
	private File fileB;
	private File fileC;
	private File duplicate;

	private final DuplicateCheckImpl objUnderTest = new DuplicateCheckImpl();


	@Before
	public void setup() throws IOException {
		this.root = this.templateFolder.newFolder("root");

		this.fileA = this.templateFolder.newFile("root/FileA");
		FileUtils.writeStringToFile(this.fileA, "This file A", Charset.defaultCharset());

		this.fileB = this.templateFolder.newFile("root/FileB");
		FileUtils.writeStringToFile(this.fileB, "This file B", Charset.defaultCharset());

		this.templateFolder.newFolder("root", "sub");

		this.duplicate = this.templateFolder.newFile("root/sub/FileA");
		FileUtils.writeStringToFile(this.duplicate, "This file A", Charset.defaultCharset());

		this.templateFolder.newFolder("root", "sub" , "subsub");

		this.fileC = this.templateFolder.newFile("root/sub/subsub/FileA");
		FileUtils.writeStringToFile(this.fileC, "This file X", Charset.defaultCharset());
	}

	@Test
	public void testCollectCandidates__bySize() throws Exception {
		final Collection<Duplicate> dublicates = this.objUnderTest.collectCandidates(this.root.getAbsolutePath(), EModi.SIZE);
		assertThat(toFiles(dublicates), containsInAnyOrder(this.fileA, this.fileB, this.fileC, this.duplicate));
	}

	@Test
	public void testCollectCandidates__bySizeAndName() throws Exception {
		final Collection<Duplicate> dublicates = this.objUnderTest.collectCandidates(this.root.getAbsolutePath(), EModi.SICE_AND_NAME);
		assertThat(toFiles(dublicates), containsInAnyOrder(this.fileA, this.fileC, this.duplicate));
	}

	@Test
	public void tetsTheWholeWorld() throws Exception {
		final Collection<Duplicate> possible = this.objUnderTest.collectCandidates(this.root.getAbsolutePath(), EModi.SIZE);

		final Collection<Duplicate> dublicates = this.objUnderTest.checkCandidates(possible);

		assertThat(toFiles(dublicates), containsInAnyOrder(this.fileA, this.duplicate));
	}


	private static List<File> toFiles(final Collection<Duplicate> dublicates) {
		return dublicates.stream().map(Duplicate::getFile).collect(Collectors.toList());
	}
}
