package p3eapi;

public abstract class Job {

	private Parameters params = null;
	private P6Connection p6 = null;
	private Boolean isError = false;
	private String message = "";

	public Job(Parameters args, P6Connection p6con) {

		this.params = args;
		this.p6 = p6con;
	}

	public String name() {
		return "method <name> needs to be overridden in " + this.getClass();
	}

	abstract Boolean run();

	public Boolean isError() { return isError; }
	public String message() { return message; }

}
