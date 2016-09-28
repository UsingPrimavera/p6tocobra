package p3eapi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.IOException;

/**
 * Looks after the creation, writing and deletion of data to a file
 *
 * @author Barrie Callender
 */
public class PrinterFacade {

    String _filename = null;
    FileOutputStream _file = null;
    PrintStream _printer = null;

    public PrinterFacade(String filename) {
      try {
       _filename = filename;
       _file = new FileOutputStream(filename);
       _printer = new PrintStream(new BufferedOutputStream(_file), false);

      }
      catch (FileNotFoundException e) {
          e.printStackTrace();
      }

    }

    public void close() {
        boolean retval = false;
        try {
          if (_printer != null) {
            _printer.flush();
            _printer.close();
          }

          if (_file != null) {
            _file.flush();
            _file.close();
          }

        }
        catch (IOException ex) {
          ex.printStackTrace();
        }
    }

    /**
     * Deletes the file from the file system
     */
    public void delete() {
        close();

        File deleteFile = new File(_filename);

        if (!deleteFile.delete()) {
          System.out.println("Couldn't delete the file '" + _filename + "'");
        }

    }

    public void println(String msg) {
        _printer.println(msg);
    }

    public void println() {
        _printer.println();
    }

    public void flush() {
        _printer.flush();
    }

}
