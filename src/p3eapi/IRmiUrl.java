package p3eapi;

interface IRmiUrl {

	 int getCompressionRmiService();
	 int getLocalService();
	 int getSSLRmiService();
	 int getStandardRmiService();

	 String getRmiUrl(int mode, String hostname, int port);

}
