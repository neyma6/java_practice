package nng.homework.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextFileReader {

	private static final Logger LOGGER = Logger.getLogger(TextFileReader.class.getName());
	
	private String fileEncoding;

	public TextFileReader(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}
	
	public List<String> readFile(String filePath) throws IOException, FileNotFoundException {
		List<String> lines = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(filePath), fileEncoding))) {
			
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "Exception was thrown during file reading!");
			throw ex;
		}
		
		return lines;
	} 
}
