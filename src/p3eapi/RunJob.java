package p3eapi;

import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.object.ResourceAssignment;

public class RunJob extends Job {

  private Parameters params = null;
  private P6Connection p6Conn = null;
  private String[] resourceAssignmentFields = null;

	public RunJob(Parameters params, P6Connection p6Conn) {
		super(params, p6Conn);
        this.params = params;
        this.p6Conn = p6Conn;
        if (this.params.datesource()=="B") {
          this.resourceAssignmentFields = ["ObjectId","PlannedStartDate","PlannedFinishDate"];
        }
        else {
          this.resourceAssignmentFields = ["ObjectId","StartDate","FinishDate"];

        }
	}

	public String name() { return "Run"; }

	public Boolean run() {
    Boolean retval = false;

    // connect to P6 database
    this.p6Conn.login(this.params.username(), this.params.password());
    if ( this.p6Conn.isLoggedIn()) {
    //   Determine if Project or Baseline
      IProject project = this.p6Conn.getProjectOrBaseline(params.projectId());
      try {
        BOIterator<ResourceAssignment> boiResAss = project.getResourceAssignments(this.resourceAssignmentFields);
        while (boiResAss.hasNext()) {

          //    load the resource assignment spread

          //    For Each Resource Assignment Spread

          //      Extract data and ssave to csv
        }

      }
      catch (ServerException ex) {
          logger.info("Primavera ServerException error retrieving iterating over Resource Assignments");
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
