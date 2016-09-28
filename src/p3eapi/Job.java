package p3eapi;

public abstract class Job {

    public Job(Parameters params, P6Connection p6Conn) {
    }

	abstract String name();

	abstract Boolean run();

	abstract Boolean isError();
	abstract String message();
    abstract String getLogfilename();

}
