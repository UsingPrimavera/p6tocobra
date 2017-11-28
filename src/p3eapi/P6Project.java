package p3eapi;

import java.util.logging.Logger;

import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.object.ResourceAssignment;
import com.primavera.ServerException;
import com.primavera.integration.network.NetworkException;
import com.primavera.integration.client.bo.BusinessObjectException;

public class P6Project implements IProject {

  private static Logger logger = Logger.getLogger(P6Project.class.getName());

  private Project p6project = null;

  public P6Project(Project project) {
    this.p6project = project;
  }

  public BOIterator<ResourceAssignment> getResourceAssignments(String[] fields) {

    BOIterator<ResourceAssignment> retval = null;

    try {
      retval = this.p6project.loadAllResourceAssignments(fields, null, null);
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

}
