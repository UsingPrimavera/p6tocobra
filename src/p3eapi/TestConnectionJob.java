package p3eapi;

public class TestConnectionJob extends Job {

	public TestConnectionJob(Parameters params, P6Connection p6) {
		super(params,p6);
	}

	public String name() { return "TestConnection"; }

	public Boolean run() {
		return true;
	}

}
