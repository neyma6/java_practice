package com.nng.homework;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.nng.homework.domain.IStreetPlate;
import com.nng.homework.io.StreetPlateParser;
import com.nng.homework.io.TextFileReader;

public class App {
	
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	
	private static String RESOURCE_NAME = "network.mid";
	private static String ENCODING = "Cp1252";
	
    public static void main( String[] args ) {
        
    	InputStream fileStream = Thread.currentThread().getContextClassLoader()
			    .getResourceAsStream(RESOURCE_NAME);
    	
    	try {
    		TextFileReader reader = new TextFileReader(ENCODING);
    		List<IStreetPlate> streetPlates = StreetPlateParser.parse(reader.readFile(fileStream));
    		
    		for (IStreetPlate sp : streetPlates) {
    			System.out.println(sp);
    		}
    		
    		System.out.println(streetPlates.size());
    	} catch (Exception ex) {
    		LOGGER.log(Level.SEVERE, "There was an exception during running!", ex);
    	}
    }
}
