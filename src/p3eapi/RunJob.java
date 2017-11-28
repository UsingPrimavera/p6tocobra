package p3eapi;

import java.util.logging.Logger;

import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.object.ResourceAssignment;
import com.primavera.ServerException;
import com.primavera.integration.network.NetworkException;
import com.primavera.integration.client.bo.BusinessObjectException;

public class RunJob extends Job {

  private static Logger logger = Logger.getLogger(RunJob.class.getName());

  private Parameters params = null;
  private P6Connection p6Conn = null;
  private String[] resourceAssignmentFields = null;

	public RunJob(Parameters params, P6Connection p6Conn) {
		super(params, p6Conn);
        this.params = params;
        this.p6Conn = p6Conn;
        if (this.params.dateSource()=="B") {
          this.resourceAssignmentFields = new String[] {"ObjectId","PlannedStartDate","PlannedFinishDate"};
        }
        else {
          this.resourceAssignmentFields = new String[] {"ObjectId","StartDate","FinishDate"};

        }
	}

	public String name() { return "Run"; }

	public Boolean run() {

    Boolean retval = false;
    ResourceAssignment resAss = null;
    ResourceAssignment loadedResAss = null;

    this.p6Conn.login(this.params.username(), this.params.password());
    if ( this.p6Conn.isLoggedIn()) {
      IProject project = this.p6Conn.getProjectOrBaseline(params.projectId());
      BOIterator<ResourceAssignment> boiResAss = project.getResourceAssignments(this.resourceAssignmentFields);
      while (boiResAss.hasNext()) {
        resAss = boiResAss.next();
        if (this.params.dateSource()=="B") {
          loadedResAss = ResourceAssignment.loadWithLiveSpread(
              session,
              this.resourceAssignmentFields,
              resAss.getObjectId(),
              new String[] {"RemainingUnits","RemainingCost","PlannedUnits","PlannedCost"},
              SpreadPeriodType.DAY,
              resAss.getPlannedStartDate(),
              resAss.getPlannedFinishDate(),
              false);
        }
        else {
          loadedResAss = ResourceAssignment.loadWithLiveSpread(
              session,
              this.resourceAssignmentFields,
              resAss.getObjectId(),
              new String[] {"RemainingUnits","RemainingCost","AtCompletionUnits","AtCompletionCosts"},
              SpreadPeriodType.DAY,
              resAss.getStartDate(),
              resAss.getFinishDate(),
              false);
        }
          //    For Each Resource Assignment Spread

          //      Extract data and ssave to csv
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
