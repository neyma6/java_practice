package com.nng.homework.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nng.homework.io.TextFileReader;

public class TextFileReaderTest {

	private static final String ENCODING = "UTF8";
	private static final int NUMBER_OF_LINES = 3;
	
	private InputStream fileInputStream;
	private TextFileReader reader;
	
	@Before
	public void setup() {
		fileInputStream = Thread.currentThread().getContextClassLoader()
			    .getResourceAsStream("testinput.mid");
		
		reader = new TextFileReader(ENCODING);
	}
	
	@Test
	public void whenTheFileIsAvailableThenTheFileContentReturns() throws FileNotFoundException, IOException {
		List<String> line = reader.readFile(fileInputStream);
		
		assertFalse(line.isEmpty());
	}
	
	@Test
	public void whenTheFileContainsXLinesThenTheOutputShouldReturnXEntries() throws FileNotFoundException, IOException {
		List<String> line = reader.readFile(fileInputStream);
		
		assertFalse(line.isEmpty());
		assertTrue(line.size() == NUMBER_OF_LINES);
	}
	
	@Test (expected = Exception.class)
	public void whenTheFileStreamIsNullThenAnExceptionShouldBeThrown() throws FileNotFoundException, IOException {
		reader.readFile(null);
	}
}
