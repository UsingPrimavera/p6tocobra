package p3eapi;

import java.util.logging.Logger;

import com.primavera.integration.client.bo.object.BaselineProject;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.object.ResourceAssignment;
import com.primavera.ServerException;
import com.primavera.integration.network.NetworkException;
import com.primavera.integration.client.bo.BusinessObjectException;

public class P6Baseline implements IProject {

  private static Logger logger = Logger.getLogger(P6Baseline.class.getName());
  private BaselineProject p6baseline = null;

  public P6Baseline(BaselineProject baseline) {
    this.p6baseline = baseline;
  }

  public BOIterator<ResourceAssignment> getResourceAssignments(String[] fields) {

    BOIterator<ResourceAssignment> retval = null;
    try {
      retval = this.p6baseline.loadAllResourceAssignments(fields, null, null);
    }
    catch (ServerException ex) {
      logger.info("Primavera ServerException error retrieving Resource Assignments");
      logger.info(ex.toString());
    }
    catch (NetworkException ex) {
      logger.info("Primavera NetworkException error retrieving Resource Assignments");
      logger.info(ex.toString());
    }
    catch (BusinessObjectException ex) {
      logger.info("Primavera BusinessObjectException error retrieving Resource Assignments");
      logger.info(ex.toString());
    }
    return retval;
  }

  public String getName() {
    try {
      return this.p6baseline.getName();
    }
    catch (BusinessObjectException e) {
      return "NAME NOT RETRIEVED";
    }
  }

}
