package p3eapi;

public class RunJob extends Job {

	public RunJob(Parameters params) {
		super(params);
	}

	public String name() { return "Run"; }

	public Boolean run() {
		return true;
	}

}
