package p3eapi;

import java.util.logging.Logger;

import com.primavera.integration.client.Session;
import com.primavera.ServerException;
import com.primavera.integration.client.ClientException;
import com.primavera.integration.network.NetworkException;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.GlobalObjectManager;
import com.primavera.integration.client.EnterpriseLoadManager;
import com.primavera.integration.client.bo.enm.SpreadPeriodType;
import com.primavera.integration.client.bo.object.ResourceAssignment;
import com.primavera.common.value.BeginDate;
import com.primavera.common.value.EndDate;

class P6Session implements ISession {
	private static Logger logger = Logger.getLogger(P6Session.class.getName());

	private Session p6session = null;

	private Exception caughtException = null;

	public P6Session() {
	}

	public boolean login(String url, String dbase, String username, String password) {

		boolean result = false;
		try {
			Session p6session = Session.login(url,dbase,username,password);
			this.p6session = p6session;
			result = true;
		}
		catch (ServerException ex)
		{
			this.caughtException = ex;
		}
		catch (ClientException ex)
		{
			this.caughtException = ex;
		}
		catch (NetworkException ex)
		{
			this.caughtException = ex;
		}
		finally
		{
			return result;
		}

	}

	public GlobalObjectManager getGlobalObjectManager() {
		return p6session.getGlobalObjectManager();
	}

	public EnterpriseLoadManager getEnterpriseLoadManager() {
		return p6session.getEnterpriseLoadManager();
	}

	public ResourceAssignment loadSpread(ResourceAssignment resAss, String[] staticFields, String[] spreadFields, BeginDate startDate, EndDate finishDate) {

		ResourceAssignment retval = null;

		try {
			retval = ResourceAssignment.loadWithLiveSpread(
        p6session,
        staticFields,
        resAss.getObjectId(),
        spreadFields,
        SpreadPeriodType.DAY,
        startDate,
        finishDate,
        false);
		}
		catch (ServerException ex) {
			logger.info("Primavera ServerException error loading live spread");
	    logger.info(ex.toString());
		}
		catch (BusinessObjectException ex) {
			logger.info("Primavera BusinessObjectException error loading live spread");
	    logger.info(ex.toString());
		}
		catch (NetworkException ex) {
			logger.info("Primavera NetworkException error loading live spread");
	    logger.info(ex.toString());
		}

		return retval;
	}


}
