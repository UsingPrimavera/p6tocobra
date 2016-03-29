package p3eapi;

import com.primavera.integration.client.RMIURL;
import java.util.Hashtable;

import java.util.logging.Logger;

/**
 * Holds all the information about how to connect to the Primavera API.
 *
 * @author Barrie Callender
 */
class P6Connection
{
  private static Logger logger = Logger.getLogger(P6Connection.class.getName());
  public static final String LOCAL_MODE = "local";
  public static final String STANDARD_MODE = "standard";
  public static final String SSL_MODE = "ssl";
  public static final String COMPRESSION_MODE = "compression";

  private int iCallingMode;
  private String sHostname;
  private int iPort;
  private Hashtable<String,Integer> modeMap = new Hashtable<String,Integer>();

  /**
   * Constructor establishes connection information based on the
   * P3eAPIExportApp.properties file and defaults
   */
  P6Connection()
  {
    super();
    // set the map up.
    modeMap.put(LOCAL_MODE,RMIURL.LOCAL_SERVICE);
    modeMap.put(STANDARD_MODE,RMIURL.STANDARD_RMI_SERVICE);
    modeMap.put(SSL_MODE,RMIURL.SSL_RMI_SERVICE);
    modeMap.put(COMPRESSION_MODE,RMIURL.COMPRESSION_RMI_SERVICE);

    // open the properties file and read in the variables

    Configuration props = new Configuration();
    
    Integer j = null;
    logger.info("property mode=" + props.getProperty("mode",LOCAL_MODE));
    setCallingMode(props.getProperty("mode",LOCAL_MODE));
    setHostname(props.getProperty("hostname","http://localhost"));
    setPort(new Integer(props.getProperty("port","9099")) );

  }

  /**
   * Returns calling mode as either Local or Remote
   *
   * @return
   */
  public int getCallingMode() {
      return iCallingMode;
  }

  /**
   * Sets the calling mode.
   *
   * @param mode
   * @return
   */
  public int setCallingMode(String mode) {

    if (modeMap.containsKey(mode.toLowerCase())) {
      iCallingMode = (Integer)modeMap.get(mode.toLowerCase());
    }
    else {
      iCallingMode = (Integer)modeMap.get(STANDARD_MODE);
    }
    
    return getCallingMode();
  }

  /**
   * Returns the name of the host for non=local modes
   *
   * @return
   */
  public String getHostname() {
      return sHostname;
  }

  /**
   * Sets the name of the host for use in non-local modes
   *
   * @param Hostname
   * @return
   */
  public String setHostname(String Hostname) {
      sHostname = Hostname;
      return getHostname();
  }

  /**
   * Returns the port number for non-local modes
   *
   * @return
   */
  public int getPort() {
      return iPort;
  }

  /**
   * Returns the port number for non-local modes.
   *
   * @param Port
   * @return
   */
  public int setPort(int Port) {
      iPort = Port;
      return getPort();
  }

  /**
   * Uses the information in this object to return a URL that primavera can use
   * to connect to the Primavera API
   *
   * @return
   */
  public String getRmiUrl() {

      return RMIURL.getRmiUrl(getCallingMode(),getHostname(),getPort());
  }

}
