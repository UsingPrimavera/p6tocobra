package p3eapi;

import java.util.Properties;



import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Handles the reading and supplying of properties from the properties file
 *
 * @author Barrie Callender
 */
public class Configuration {

    private static String propertyFileName = "p6tocobra.properties";
    private Properties props = null;

    public Configuration() {

      Properties defaultProps = new Properties();
      defaultProps.setProperty("mode", "local");
      defaultProps.setProperty("hostname", "localhost");
      defaultProps.setProperty("port", "9099");
      defaultProps.setProperty("RMIServerMode", "Standard");
      defaultProps.setProperty("keep", "y");
      props = new Properties(defaultProps);
      try {

        File file = createFile(whereAmI() + "/" + propertyFileName);
	      FileInputStream in = new FileInputStream(file);
	      props.load(in);
	      in.close();
      }
      catch (java.io.IOException ex) {
        Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    public String getProperty(String key, String def) {

        String ret = props.getProperty(key);

        if (ret == null) {ret = def;}

        return ret;
    }

    public String getProperty(String key) {

        String ret = this.getProperty(key, null);
        return ret;
    }


    public String whereAmI() {
      //ClassLoader loader = P6Connection.class.getClassLoader();
      //return loader.getResource("p3eapi").toString();
      return System.getProperty("user.dir");
  }

  private File createFile(String filename) {

    File file = null;
    boolean isCreated = false;

    try {
      file = new File(filename);
      if (file.exists()) return file;
      isCreated = file.createNewFile();
    }
    catch (IOException ex) {
      System.out.println("createFile() Error while creating a new empty file :" + ex);
    }

    return file;
  }
}
