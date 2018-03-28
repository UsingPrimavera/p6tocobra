package p3eapi;

import java.util.Properties;
import java.util.HashMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ConfigFileTest {

  private String _propertiesFile = System.getProperty("user.dir") + "/" + "p6tocobra.properties";


	public ConfigFileTest() {
	}

	@Test
	public void testConfigurationClosesFile() {

		Properties testProps = new Properties();
    testProps.setProperty("mode","standard");

		File file = createConfigFile(testProps);

		Configuration config = new Configuration();

    assertTrue(file.exists());
		assertEquals(true,file.delete());
	}

	@Test
	public void testMissingFileReturnsDefaults() {

		deleteFile(_propertiesFile);
		Configuration config = new Configuration();

		assertEquals("local", config.getProperty("mode"));
		assertEquals("localhost", config.getProperty("hostname"));
		assertEquals("9099", config.getProperty("port"));
		assertEquals("Standard", config.getProperty("RMIServerMode"));
		assertEquals("y", config.getProperty("keep"));

	}

	@Test
	public void testValuesFromFile() {

    Properties testProps = new Properties();
    testProps.setProperty("mode","standard");
    testProps.setProperty("hostname","example.com");
		testProps.setProperty("port","1234");
		testProps.setProperty("RMIServerMode","Custom");
		testProps.setProperty("keep","n");

		File file =createConfigFile(testProps);

		Configuration config = new Configuration();

		assertEquals(testProps.getProperty("mode"), config.getProperty("mode"));
		assertEquals(testProps.getProperty("hostname"), config.getProperty("hostname"));
		assertEquals(testProps.getProperty("port"), config.getProperty("port"));
		assertEquals(testProps.getProperty("RMIServerMode"), config.getProperty("RMIServerMode"));
		assertEquals(testProps.getProperty("keep"), config.getProperty("keep"));
    deleteFile(_propertiesFile);
	}

  @Test
  public void testMixedDefaultCustomValues() {

    Properties testProps = new Properties();
    testProps.setProperty("mode","standard");
		testProps.setProperty("port","1234");
		testProps.setProperty("keep","n");

		File file =createConfigFile(testProps);

		Configuration config = new Configuration();

		assertEquals(testProps.getProperty("mode"), config.getProperty("mode"));
		assertEquals("localhost", config.getProperty("hostname"));
		assertEquals(testProps.getProperty("port"), config.getProperty("port"));
		assertEquals("Standard", config.getProperty("RMIServerMode"));
		assertEquals(testProps.getProperty("keep"), config.getProperty("keep"));
    deleteFile(_propertiesFile);

  }
	private void deleteFile(String filename) {

  	File file = new File(filename);
	 	file.delete();

	}

	private File createConfigFile(Properties configProps) {

    File file = new File(_propertiesFile);

    try {
      if (file.exists()) {
        file.delete();
        file.createNewFile();
      }

      FileOutputStream out = new FileOutputStream(file);
      configProps.store(out, "---Testing---");
      out.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("FileNotFoundException writing appProperties");
    }
    catch (IOException ex) {
      System.out.println("IOException writing defaultProperties");
    }

		return file;

	}

}
