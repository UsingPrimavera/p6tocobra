package p3eapi;

public class TestConnectionJob extends Job {

    private Parameters params = null;
    private P6Connection p6Conn = null;
    private Boolean isError = null;
    private String message = null;

	public TestConnectionJob(Parameters params, P6Connection p6Conn) {
		super(params,p6Conn);
        this.params = params;
        this.p6Conn = p6Conn;
        this.isError = false;
        this.message = "Not Connected";
	}

	public String name() { return "TestConnection"; }

	public Boolean run() {

        p6Conn.login(params.username(), params.password());
        Boolean retval = p6Conn.isLoggedIn();
		return retval;
	}

    public Boolean isError() { return this.isError;}

    public String message() { return this.message;}

}
