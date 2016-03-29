package p3eapi;

public class NullJob extends Job {

	public NullJob(Parameters params) {
		super(params);
	}

	public String name() { return "Null"; }

	public Boolean run() {
		return true;
	}

}
