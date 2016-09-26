# Developers Notes

These notes should explain a little about this application that the Developer needs to know.  That Developerjust so happens to be me in a couple of days, weeks, months or whatever.

## Application Entry Point

Deltek Cobra starts the app by calling P3eAPIExportApp with a set of command line parameters it pulled together depending on what the user has chosen to do.  

This means the entry point for the app is the *main* method which is in the *P3eAPIExportApp* class. 

There are just two kinds of jobs that can be ran from Deltek Cobra.

* The Run job (*RunJob* class) which connects to P6 and extracts the data as requested in the parameters provided.
* The Test Connection Job (*TestConnectionJob* class) which tries to connect to P6 and reports whether or not it worked.

The *main* method controls everything and it really does just two things.  First it instantiates the *Parameters* class which holds all the Parameters supplied to it.  Then it passes this into the *JobFactory* class which looks at the parameters and returns the correct job as an interface.  It then just runs the Job returned and then exits.

## Decoupling From P6

The application should run with diferent versions of P6 so as to not tie it down to each version, it makes use of interfaces.  By doing this I can mock the interfaces when testing and do not have the time consuming startup times of the full P6 application.

However, this increases the complexity of the application and can make it difficult to work out what is going on.  This is the main reason for these notes.

## The Application Detail

### Parameters Class

The Parameters class is instantiated by P3eAPIExportApp.main by passing the command line arguments into it.  It stores all the arguments so they can be retrieved and also evaluates whether it is a Test Connection Job, a Run Job or an error.

### P6RmiUrl Class

This is a wrapper arounf the P6 RMIURL class, taking the class methods of that class and providing them as instance methods.

This class implements the *IRmiUrl* interface to decouple it from P6 when testing.

### IRmiUrl Interface

Implemented by the P6RmiUrl class, this interface can be mocked during testing to act as different scenarios for P6.

### P6Session Class

This class implements the *ISession* interface which is used for mocking and decoupling from P6 api.  

This makes P6Session a wrapper around the P6 Session class with the capability to login to a p6 database and will gain the capability to logout.  It will have other capabilities, but that depends on how the application gets implemented.

### ISession Interface

Implemented by the P6Session class, this interface can be mocked during testing to act out different scenarios for P6.

### P6Connection Class

The P6Connection Class is instantiated with an IRmiUrl and ISession interface so it has everything it needs to connect to P6.  This is the one the jobs will use to login to P6.  I could have just used the ISession and IRmiUrl objects but placing the code in this means I don't have to repeat the same logic when writing code for the two jobs.  It also means the app can expand if Deltek add another type of job or we do something that Deltek doesn't need to know about.

### JobFactory Class

This is a singleton which returns the class representing the correct Job.  Currently it returns one of 3 jobs which are all based on the abstract Job class.  The 3 classes are:

* RunJob which extracts the data from P6 and puts it in a csv file for Deltek to pick it up.
* TestConnectionJob which justs tests if the connection to P6 can be made or not.
* NullJob which is returned when there is a problem and it does nothing but report the error.

