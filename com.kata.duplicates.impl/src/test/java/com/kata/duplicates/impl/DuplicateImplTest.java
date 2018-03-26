package com.kata.duplicates.impl;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.kata.duplicates.impl.DuplicateImpl;

public class DuplicateImplTest {
	
	@Test
	public void testGetFile(){
		File file = new File("test.txt");
		
		DuplicateImpl duplicate = new DuplicateImpl(file, 200);
		
		assertEquals(file, duplicate.getFile());
		assertEquals(200, duplicate.getSize());
	}
}
