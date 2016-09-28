package p3eapi;

public class RunJob extends Job {

    private Parameters params = null;
    private P6Connection p6Conn = null;

	public RunJob(Parameters params, P6Connection p6Conn) {
		super(params, p6Conn);
        this.params = params;
        this.p6Conn = p6Conn;
	}

	public String name() { return "Run"; }

	public Boolean run() {
		return false;
	}

    public Boolean isError() { return true;}

    public String message() {return "Nothing";}

    public String getLogfilename() {
        return params.pathName() + "\\p3eapiSpread.log";
    }

}
