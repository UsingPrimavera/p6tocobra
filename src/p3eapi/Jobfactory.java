package p3eapi;

class JobFactory {

	public static Job getJob(Parameters params, P6Connection p6) {

		if (params.Run())
			return new RunJob(params,p6);
		else if (params.testConnection())
			return new TestConnectionJob(params, p6);
		else return new NullJob(params, p6);

	}

}
