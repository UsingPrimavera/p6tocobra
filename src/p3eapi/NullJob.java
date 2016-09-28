package p3eapi;

public class NullJob extends Job {

    private String logFilename = null;

	public NullJob(Parameters params, P6Connection p6) {
		super(params, p6);
        this.logFilename = "..\\WrongParameters.log";
	}

	public String name() { return "Null"; }

	public Boolean run() {
		return false;
	}

    public Boolean isError() { return true;}

    public String message() { return "Wrong set of parameters provided";}

    public String getLogfilename() {
        return this.logFilename;
    }
}
