package p3eapi;

public class RunJob extends Job {

	public RunJob(Parameters params, P6Connection p6) {
		super(params, p6);
	}

	public String name() { return "Run"; }

	public Boolean run() {
		return true;
	}

}
