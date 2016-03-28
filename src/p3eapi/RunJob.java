package p3eapi;

public class RunJob extends AbstractJob {

	public RunJob(Parameters params) {
		super(params);
	}

	public String name() { return "Run"; }

	public Boolean run() {
		return true;
	}

}
