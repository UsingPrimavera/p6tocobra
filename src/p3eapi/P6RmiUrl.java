package p3eapi;

import com.primavera.integration.client.RMIURL;

class P6RmiUrl implements IRmiUrl {


	public P6RmiUrl() {}

	public int getCompressionRmiService() {
		return RMIURL.COMPRESSION_RMI_SERVICE;
	}

	public int getLocalService() {
		return RMIURL.LOCAL_SERVICE;
	}

	public int getSSLRmiService() {
		return RMIURL.SSL_RMI_SERVICE;
	}

	public int getStandardRmiService() {
		return RMIURL.STANDARD_RMI_SERVICE;
	}

	public String getRmiUrl(int mode, String hostname, int port) {
		return RMIURL.getRmiUrl(mode, hostname, port);
	}


}
