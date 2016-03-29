package p3eapi;

class JobFactory {

	public static Job getJob(Parameters params) {

		if (params.Run())
			return new RunJob(params);
		else if (params.testConnection())
			return new TestConnectionJob(params);
		else return new NullJob(params);

	}

}
