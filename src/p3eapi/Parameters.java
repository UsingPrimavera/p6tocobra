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
	private String sPathName = null;
	private String sProjectId = null;
	private Boolean bTestConnection = false;
	private Boolean bRun = false;
	private String sDateSource = null;
	private Boolean bFlag = false;

	public Parameters(String[] args) {

		if (args != null && (args.length == 6)
				&& args[3].equals("TestConnection")
				&& args[5].equals("true") ) {
			sUsername = args[0];
			sPassword = args[1];
			sPathName = args[2];
			requesting(args[3]);
			sDateSource = args[4];
			bFlag = stringToBoolean(args[5]);
			}
		else {
			sUsername = args[0];
			sPassword = args[1];
			sPathName = args[2];
			requesting(args[3]);
			sProjectId = extractProjectId(args);
			sDateSource = args[args.length - 1];
			bRun = true;
			}
		}

	public String username(){ return sUsername;}
	public String password(){ return sPassword;}
	public String pathName(){ return sPathName;}
	public String projectId(){ return sProjectId;}
	public Boolean testConnection(){ return bTestConnection;}
	public Boolean Run() { return bRun;}
	public String dateSource(){ return sDateSource;}
	public Boolean flag(){ return bFlag;}

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
			default:
				bTestConnection = false;
				bRun = true;
		}
	}

	private String extractProjectId(String[] args) {
		if (args.length == 4) {
			return args[3];
		}
		else {
			String sProjectId = args[3];
			for(int i=4; i<(args.length - 1); i++){
				sProjectId = sProjectId + " " + args[i];
				}
			return sProjectId;

		}
	}
}
