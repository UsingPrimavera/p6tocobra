package p3eapi;

public class TestConnectionJob extends AbstractJob {

	public TestConnectionJob(Parameters params) {
		super(params);
	}

	public String name() { return "TestConnection"; }

	public Boolean run() {
		return true;
	}

}
