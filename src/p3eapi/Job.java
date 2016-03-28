package p3eapi;

public abstract class Job {

	private Parameters params = null;
	private Boolean isError = false;
	private String message = "";

	public Job(Parameters args) {

		this.params = args;
	}

	public String name() {
		return "method <name> needs to be overridden in " + this.getClass();
	}

	abstract Boolean run();

	public Boolean isError() { return isError; }
	public String message() { return message; }

}
