package p3eapi;

/**
 * Converts the arguments supplied by cobra into sensible names.
 *
 * There are two sets of arguments issued by Deltek Cobra depending on
 * whether a Test Connection is required or a spread is required.
 *
 * */
public class Parameters {

	private String sUsername = null;
	private String sPassword = null;
	private String sPathname = null;
	private String sProjectId = null;
	private Boolean bTestConnection = false;
	private Boolean bRun = false;
	private String sDateSource = null;
	private Boolean bBoolean = false;

	public Parameters(String[] args) {
		if (args != null && (args.length == 6)
				&& args[3].equals("TestConnection")
				&& args[5].equals("true") ) {
			sUsername = args[0];
			sPassword = args[1];
			sPathname = args[2];
			requesting(args[3]);
			sDateSource = args[4];
			bBoolean = stringToBoolean(args[5]);
			}
		else {
			sUsername = args[0];
			sPassword = args[1];
			sPathname = args[2];

			sProjectId = args[3];
			for(int i=4; i<(args.length - 1); i++){
				sProjectId = sProjectId + " " + args[i];
				}

			sDateSource = args[args.length - 1];
			}
		}

	public String Username(){ return sUsername;}
	public String Password(){ return sPassword;}
	public String Pathname(){ return sPathname;}
	public String ProjectId(){ return sProjectId;}
	public Boolean TestConnection(){ return bTestConnection;}
	public Boolean Run() { return bRun;}
	public String DateSource(){ return sDateSource;}
	public Boolean Boolean(){ return bBoolean;}

	private Boolean stringToBoolean(String str){
		if (str.equals("true")) {
			return true;
		}
		else { 
			return false;
		}
	}

	private void requesting(String str) {
		switch (str) {
			case "TestConnection": 
				bTestConnection = true;
				bRun = false;
				break;
			case "Run":
				bTestConnection = false;
				bRun = true;
				break;
			default:
				bTestConnection = false;
				bRun = false;
		}
	}
}
