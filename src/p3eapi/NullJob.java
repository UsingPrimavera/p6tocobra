package p3eapi;

public class NullJob extends Job {

	public NullJob(Parameters params, P6Connection p6) {
		super(params, p6);
	}

	public String name() { return "Null"; }

	public Boolean run() {
		return false;
	}

    public Boolean isError() { return true;}

    public String message() { return "Wrong set of parameters provided";}

}
