package p3eapi;

import java.util.logging.Logger;

import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.object.ResourceAssignment;
import com.primavera.common.value.BeginDate;
import com.primavera.common.value.EndDate;
import com.primavera.ServerException;
import com.primavera.integration.network.NetworkException;
import com.primavera.integration.client.bo.BusinessObjectException;


public class RunJob extends Job {

  private static Logger logger = Logger.getLogger(RunJob.class.getName());

  private Parameters params = null;
  private P6Connection p6Conn = null;
  private String[] resourceAssignmentFields = null;
  private String[] spreadFields = null;

	public RunJob(Parameters params, P6Connection p6Conn) {
		super(params, p6Conn);
    this.params = params;
    this.p6Conn = p6Conn;
    if (this.params.dateSource()=="B") {
      String[] staticFields = {"ObjectId","PlannedStartDate","PlannedFinishDate"};
      this.resourceAssignmentFields = staticFields;
      String[] spreadFields = {"RemainingUnits","RemainingCost","PlannedUnits","PlannedCost"};
      this.spreadFields = spreadFields;
    }
    else {
      String[] staticFields = {"ObjectId","StartDate","FinishDate"};
      this.resourceAssignmentFields = staticFields;
      String[] spreadFields = {"RemainingUnits","RemainingCost","AtCompletionUnits","AtCompletionCosts"};
      this.spreadFields = spreadFields;
    }
	}

	public String name() { return "Run"; }

	public Boolean run() {

    Boolean retval = false;
    ResourceAssignment resAss = null;

    // connect to P6 database
    this.p6Conn.login(this.params.username(), this.params.password());
    if ( this.p6Conn.isLoggedIn()) {
      try {
        IProject project = this.p6Conn.getProjectOrBaseline(params.projectId());
        BOIterator<ResourceAssignment> boiResAss = project.getResourceAssignments(this.resourceAssignmentFields);
        while (boiResAss.hasNext()) {
          resAss = boiResAss.next();
          if (this.params.dateSource()=="B") {
            resAss = this.p6Conn.loadSpread(resAss, this.resourceAssignmentFields, this.spreadFields, resAss.getPlannedStartDate(), resAss.getPlannedFinishDate());
          }
          else {
            resAss = this.p6Conn.loadSpread(resAss, this.resourceAssignmentFields, this.spreadFields, resAss.getStartDate(), resAss.getFinishDate());
          }

          //    load the resource assignment spread

          //    For Each Resource Assignment Spread

          //      Extract data and ssave to csv
        }
      }
  		catch (BusinessObjectException ex) {
  			logger.info("Primavera BusinessObjectException error processing data extract");
  	    logger.info(ex.toString());
  		}
    }
		return retval;
	}

  public Boolean isError() { return true;}

  public String message() {return "Nothing";}

  public String getLogfilename() {
    return params.pathName() + "\\p3eapiSpread.log";
  }

  public String getCSVfilename() {
    return params.pathName() + "\\" + params.projectId() + ".csv";
  }


}
