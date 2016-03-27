package p3eapi;

/**
 * Converts the arguments supplied by cobra into sensible names.
 *
 * There are two sets of arguments issued by Deltek Cobra depending on
 * whether a Test Connection is required or a spread is required.
 *
 * */
public class Parameters {

	private String username = null;
	private String password = null;
	private String pathName = null;
	private String projectId = null;
	private Boolean isTestConnection = false;
	private Boolean isRun = false;
	private String dateSource = null;
	private Boolean flag = false;

	public Parameters(String[] args) {

		if (args != null && (args.length == 6)
				&& args[3].equals("TestConnection")
				&& args[5].equals("true") ) {
			username = args[0];
			password = args[1];
			pathName = args[2];
			evaluateRequestType(args);
			dateSource = args[4];
			flag = stringToBoolean(args[5]);
			}
		else {
			username = args[0];
			password = args[1];
			pathName = args[2];
			evaluateRequestType(args);
			projectId = extractProjectId(args);
			dateSource = args[args.length - 1];
			isRun = true;
			}
		}

	public String username(){ return username;}
	public String password(){ return password;}
	public String pathName(){ return pathName;}
	public String projectId(){ return projectId;}
	public Boolean testConnection(){ return isTestConnection;}
	public Boolean Run() { return isRun;}
	public String dateSource(){ return dateSource;}
	public Boolean flag(){ return flag;}

	private Boolean stringToBoolean(String str){
		if (str.equals("true")) {
			return true;
		}
		else {
			return false;
		}
	}

	private void evaluateRequestType(String[] args) {

		isTestConnection = false;
		isRun = false;

		if ((args.length == 6)
		  && args[3].equals("TestConnection")
		  && args[5].equals("true") ) {
			isTestConnection = true;
		  } 
		else {
			isRun = true;
		}
	}

	private String extractProjectId(String[] args) {
		if (args.length == 4) {
			return args[3];
		}
		else {
			String projectId = args[3];
			for(int i=4; i<(args.length - 1); i++){
				projectId = projectId + " " + args[i];
				}
			return projectId;

		}
	}
}
