package p3eapi;

import java.util.Properties;
import java.util.HashMap;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ConfigFileTest {

	public ConfigFileTest() {
	}

	@Test
	public void testConfigurationClosesFile() {

		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mode","standard");

		File file =createConfigFile(map);

		Configuration config = new Configuration();

		assertTrue(deleteFile(whereAmI() + "/" + "p6tocobra.class"));
	}

	@Test
	public void testMissingFileReturnsDefaults() {

		deleteFile(whereAmI() + "/" + "p6tocobra.class");
		Configuration config = new Configuration();

		assertEquals("local", config.getProperty("mode"));
		assertEquals("http://localhost", config.getProperty("hostname"));
		assertEquals("9099", config.getProperty("port"));
		assertEquals("Standard", config.getProperty("RMIServerMode"));
		assertEquals("y", config.getProperty("keep"));

	}

	@Test
	public void testValuesFromFile() {

		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mode","standard");
		map.put("hostname","http://example.com");
		map.put("port","1234");
		map.put("RMIServerMode","Custom");
		map.put("keep","n");

		File file =createConfigFile(map);

		Configuration config = new Configuration();

		assertEquals(map.get("mode"), config.getProperty("mode"));
		assertEquals(map.get("hostname"), config.getProperty("hostname"));
		assertEquals(map.get("port"), config.getProperty("port"));
		assertEquals(map.get("RMIServerMode"), config.getProperty("RMIServerMode"));
		assertEquals(map.get("keep"), config.getProperty("keep"));
	}

	private String whereAmI() {
		ClassLoader loader = P6Connection.class.getClassLoader();
		return loader.getResource("p3eapi").toString();
	}

	private File createFile(String uri) {

		File file = null;
		boolean isCreated = false;

		try {
			file = new File(new URI(uri));
			if (file.exists()) return file;
			isCreated = file.createNewFile();
		}
		catch (URISyntaxException ex) {
			System.out.println("createFile() URI Syntax Exception :" + ex);
		}
		catch (IOException ex) {
			System.out.println("Error while creating a new empty file :" + ex);
		}

		return file;
	}

	private boolean deleteFile(String uri) {
		

		File file = null;
		boolean isDeleted = false;

		try {
			file = new File(new URI(uri));
			isDeleted = file.delete();
		}
		catch (URISyntaxException ex) {
			System.out.println("deleteFile() URI Syntax Exception :" + ex);
		}

		return isDeleted;

	}

	private File createConfigFile(HashMap<String, String> map) {

		Properties props = new Properties();
		props.setProperty("mode",map.getOrDefault("mode",""));
		props.setProperty("hostname",map.getOrDefault("hostname",""));
		props.setProperty("port",map.getOrDefault("port",""));
		props.setProperty("RMIServerMode",map.getOrDefault("RMIServerMode",""));
		props.setProperty("keep",map.getOrDefault("keep",""));

		String filename = whereAmI() + "/" + "p6tocobra.class";
		File file =createFile(filename);

		try {
			FileOutputStream out = new FileOutputStream(file);
			props.store(out,"-- Created by ConfigFileTest.testValuesFromFile ---");
			out.close();
		} 
		catch (FileNotFoundException ex) {
			assertTrue("FileNotFoundException has been caught unexpectedly for" + filename,false);
		}
		catch (IOException ex) {
			assertTrue("IOException has been caught unexpectedly", false);
		}

		return file;

	}



}
