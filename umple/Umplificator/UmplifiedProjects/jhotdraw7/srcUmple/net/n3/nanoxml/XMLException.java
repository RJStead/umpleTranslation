/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package net.n3.nanoxml;
import java.io.PrintStream;
import java.io.PrintWriter;

public class XMLException
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public XMLException()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  private String msg;
/**
    * The system ID of the XML data where the exception occurred.
    */
   private String systemID;
/**
    * The line number in the XML data where the exception occurred.
    */
   private int lineNr;
/**
    * Encapsulated exception.
    */
   private Exception encapsulatedException;
/**
    * Builds the exception message
    *
    * @param systemID     the system ID from where the data came
    * @param lineNr       the line number in the XML data where the exception
    *                     occurred.
    * @param e            the encapsulated exception.
    * @param msg          the message of the exception.
    * @param reportParams true if the systemID, lineNr and e params need to be
    *                     appended to the message
    */
   private static String buildMessage(String    systemID,
                                      int       lineNr,
                                      Exception e,
                                      String    msg,
                                      boolean   reportParams)
   {
      String str = msg;

      if (reportParams) {
         if (systemID != null) {
            str += ", SystemID='" + systemID + "'";
         }

         if (lineNr >= 0) {
            str += ", Line=" + lineNr;
         }

         if (e != null) {
            str += ", Exception: " + e;
         }
      }

      return str;
   }
/**
    * Cleans up the object when it's destroyed.
    */
   protected void finalize()
      throws Throwable
   {
      this.systemID = null;
      this.encapsulatedException = null;
      super.finalize();
   }
/**
    * Returns the system ID of the XML data where the exception occurred.
    * If there is no system ID known, null is returned.
    */
   public String getSystemID()
   {
      return this.systemID;
   }
/**
    * Returns the line number in the XML data where the exception occurred.
    * If there is no line number known, -1 is returned.
    */
   public int getLineNr()
   {
      return this.lineNr;
   }
/**
    * Returns the encapsulated exception, or null if no exception is
    * encapsulated.
    */
   public Exception getException()
   {
      return this.encapsulatedException;
   }
/**
    * Dumps the exception stack to a print writer.
    *
    * @param writer the print writer
    */
   public void printStackTrace(PrintWriter writer)
   {
      super.printStackTrace(writer);

      if (this.encapsulatedException != null) {
         writer.println("*** Nested Exception:");
         this.encapsulatedException.printStackTrace(writer);
      }
   }
/**
    * Dumps the exception stack to an output stream.
    *
    * @param stream the output stream
    */
   public void printStackTrace(PrintStream stream)
   {
      super.printStackTrace(stream);

      if (this.encapsulatedException != null) {
         stream.println("*** Nested Exception:");
         this.encapsulatedException.printStackTrace(stream);
      }
   }
/**
    * Dumps the exception stack to System.err.
    */
   public void printStackTrace()
   {
      super.printStackTrace();

      if (this.encapsulatedException != null) {
         System.err.println("*** Nested Exception:");
         this.encapsulatedException.printStackTrace();
      }
   }
/**
    * Returns a string representation of the exception.
    */
   public String toString()
   {
      return this.msg;
   }
}