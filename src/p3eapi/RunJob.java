package p3eapi;

import com.primavera.common.value.BeginDate;
import com.primavera.common.value.EndDate;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.object.ResourceAssignment;
import com.primavera.ServerException;
import com.primavera.integration.network.NetworkException;
import com.primavera.integration.client.bo.BusinessObjectException;
import java.util.logging.Logger;
import java.util.Iterator;
import com.primavera.common.value.spread.ResourceAssignmentSpread;
import com.primavera.common.value.spread.ResourceAssignmentSpreadPeriod;
import com.primavera.common.value.spread.SpreadFieldNotFoundException;
import com.primavera.integration.client.bo.enm.SpreadPeriodType;
import com.primavera.common.value.spread.SpreadPeriod;
import com.primavera.common.value.Unit;
import com.primavera.common.value.Cost;


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
          this.resourceAssignmentFields = new String[]{"ObjectId","PlannedStartDate","PlannedFinishDate"};
          this.spreadFields = new String[]{"RemainingUnits", "RemainingCost", "PlannedUnits", "PlannedCost"};
        }
        else {
          this.resourceAssignmentFields = new String[]{"ObjectId","StartDate","FinishDate"};
          this.spreadFields = new String[]{"RemainingUnits", "RemainingCost", "AtCompletionUnits", "AtCompletionCost"};
        }
	}

	public String name() { return "Run"; }

	public Boolean run() {
    Boolean retval = false;

    // create the log file
    PrinterFacade logFile = new PrinterFacade(params.pathName() + "\\" + "p3eapiSpread.log");
    printMessage(logFile,"P6ToCobra - Copyright 2018 Workpattern Limited");
    printMessage(logFile,"https://usingprimavera.com - info@usingprimavera.com");

    // create the csv file
    PrinterFacade csvFile = new PrinterFacade(params.pathName() + "\\" + params.projectId() + ".csv");


    // connect to P6 database
    this.p6Conn.login(this.params.username(), this.params.password());
    if ( this.p6Conn.isLoggedIn()) {
    //   Determine if Project or Baseline
      IProject project = this.p6Conn.getProjectOrBaseline(params.projectId());
      printMessage(logFile,"Exporting project: " + params.projectId() + " - " + project.getName());
      try {

        ResourceAssignment resAss = null;
        ResourceAssignment loadedResAss = null;
        ResourceAssignmentSpread resAssSpread = null;
        ResourceAssignmentSpreadPeriod resAssSpreadPeriod = null;
        BeginDate startDate = null;
        EndDate finishDate = null;
        Iterator<SpreadPeriod> boiResAssSpread = null;
        Unit spreadUnitsField = null;
        Cost spreadCostField = null;

        BOIterator<ResourceAssignment> boiResAss = project.getResourceAssignments(this.resourceAssignmentFields);

        while (boiResAss.hasNext()) {

          resAss = boiResAss.next();
          //    load the resource assignment spread
          if (this.params.dateSource()=="B") {
            startDate = resAss.getPlannedStartDate();
            finishDate = resAss.getPlannedFinishDate();
          }
          else {
            startDate = resAss.getStartDate();
            finishDate = resAss.getFinishDate();
          }

          loadedResAss = ResourceAssignment.loadWithLiveSpread(
            this.p6Conn.getSession(),
            this.resourceAssignmentFields,
            resAss.getObjectId(),
            this.spreadFields,
            SpreadPeriodType.DAY,
            startDate,
            finishDate,
            false);

          resAssSpread = loadedResAss.getResourceAssignmentSpread();
          boiResAssSpread = resAssSpread.getSpreadIterator(false);

          //    For Each Resource Assignment Spread
          while (boiResAssSpread.hasNext()) {
            //      Extract data and ssave to csv
            resAssSpreadPeriod =   (ResourceAssignmentSpreadPeriod)boiResAssSpread.next();
            if (this.params.dateSource()=="B") {
              spreadUnitsField = resAssSpreadPeriod.getPlannedUnits();
              spreadCostField = resAssSpreadPeriod.getPlannedCost();
            }
            else {
              spreadUnitsField = resAssSpreadPeriod.getAtCompletionUnits();
              spreadCostField = resAssSpreadPeriod.getAtCompletionCost();
            }
            csvFile.println(resAss.getObjectId() + ","
              + resAssSpreadPeriod.getSpreadPeriodStart() + ","
              + resAssSpreadPeriod.getCost("RemainingUnits") + ","
              + resAssSpreadPeriod.getCost("RemainingCost") + ","
              + spreadUnitsField + ","
              + spreadCostField
            );
          }
        }
      }
      catch (ServerException ex) {
          logger.info("Primavera ServerException error retrieving iterating over Resource Assignments");
          logger.info(ex.toString());
      }
      catch (BusinessObjectException ex) {
          logger.info("Primavera BusinessObjectException error retrieving iterating over Resource Assignments");
          logger.info(ex.toString());
      }
      catch (NetworkException ex) {
          logger.info("Primavera NetworkException error retrieving iterating over Resource Assignments");
          logger.info(ex.toString());
      }
      catch (SpreadFieldNotFoundException ex) {
        logger.info("Primavera SpreadFieldNotFoundException error retrieving spread fields");
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

  private void printMessage(PrinterFacade printer, String msg) {
    printer.println(msg);
    System.out.println(msg);
  }


}
