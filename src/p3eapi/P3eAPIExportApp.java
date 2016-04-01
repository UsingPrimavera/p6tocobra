package p3eapi;

import java.util.logging.Logger;

class P3eAPIExportApp {

	private static Logger logger = Logger.getLogger(P3eAPIExportApp.class.getName());

	public static void main(String[] args) {
		logger.info("P6ToCobra is released under MIT License");
		logger.info("P6ToCobra copyright (c) 2016 by Barrie Callender <barrie@callenb.org>");
		logger.info("P6ToCobra source available at http://github.com/usingp6/p6tocobra");
		logger.info("Extracting Parameters supplied by Deltek Cobra");
		Parameters params = new Parameters(args);
		if (params.isError()) {
			logger.info(params.message());
		}
		else {
			logger.info("retrieving P6 connection parameters");
			IRmiUrl p6RmiUrl = (IRmiUrl) new P6RmiUrl();
			ISession p6Session = (ISession) new P6Session();
			P6Connection p6 = new P6Connection(p6RmiUrl, p6Session);
			logger.info("Retrieving requested job");
			Job job = JobFactory.getJob(params,p6);
			logger.info("TODO: Execute the job");

		}
		System.exit(0);
	}
}
