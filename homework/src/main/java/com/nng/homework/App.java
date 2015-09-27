package com.nng.homework;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.nng.homework.domain.IStreetPlate;
import com.nng.homework.io.StreetPlateParser;
import com.nng.homework.io.StreetPlateValidator;
import com.nng.homework.io.TextFileReader;
import com.nng.homework.process.DuplicationProcessor;

public class App {
	
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	
	private static String RESOURCE_NAME = "network.mid";
	private static String ENCODING = "Cp1252";
	
    public static void main( String[] args ) {
        
    	InputStream fileStream = Thread.currentThread().getContextClassLoader()
			    .getResourceAsStream(RESOURCE_NAME);
    	
    	try {
    		TextFileReader reader = new TextFileReader(ENCODING);
    		List<IStreetPlate> streetPlates = new StreetPlateParser(new StreetPlateValidator())
    				.parse(reader.readFile(fileStream));
    		

    		List<IStreetPlate> duplicatedStreetPlates = new DuplicationProcessor().process(streetPlates);
    	} catch (Exception ex) {
    		LOGGER.log(Level.SEVERE, "There was an exception during running!", ex);
    	}
    	
    	System.out.println("Success");
    }
}
