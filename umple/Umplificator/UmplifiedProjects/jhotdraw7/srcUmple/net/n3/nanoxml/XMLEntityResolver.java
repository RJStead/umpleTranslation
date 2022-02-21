/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package net.n3.nanoxml;
import java.util.Hashtable;
import java.io.Reader;
import java.io.StringReader;

public class XMLEntityResolver
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public XMLEntityResolver()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  private Hashtable entities;
/**
    * Cleans up the object when it's destroyed.
    */
   protected void finalize()
      throws Throwable
   {
      this.entities.clear();
      this.entities = null;
      super.finalize();
   }
/**
    * Adds an internal entity.
    *
    * @param name the name of the entity.
    * @param value the value of the entity.
    */
   public void addInternalEntity(String name,
                                 String value)
   {
      if (! this.entities.containsKey(name)) {
         this.entities.put(name, value);
      }
   }
/**
    * Adds an external entity.
    *
    * @param name the name of the entity.
    * @param publicID the public ID of the entity, which may be null.
    * @param systemID the system ID of the entity.
    */
   public void addExternalEntity(String name,
                                 String publicID,
                                 String systemID)
   {
      if (! this.entities.containsKey(name)) {
         this.entities.put(name, new String[] { publicID, systemID } );
      }
   }
/**
    * Returns a Java reader containing the value of an entity.
    *
    * @param xmlReader the current XML reader
    * @param name the name of the entity.
    *
    * @return the reader, or null if the entity could not be resolved.
    */
   public Reader getEntity(IXMLReader xmlReader,
                           String     name)
      throws XMLParseException
   {
      Object obj = this.entities.get(name);

      if (obj == null) {
         return null;
      } else if (obj instanceof java.lang.String) {
         return new StringReader((String)obj);
      } else {
         String[] id = (String[]) obj;
         return this.openExternalEntity(xmlReader, id[0], id[1]);
      }
   }
/**
    * Returns true if an entity is external.
    *
    * @param name the name of the entity.
    */
   public boolean isExternalEntity(String name)
   {
      Object obj = this.entities.get(name);
      return ! (obj instanceof java.lang.String);
   }
/**
    * Opens an external entity.
    *
    * @param xmlReader the current XML reader
    * @param publicID the public ID, which may be null
    * @param systemID the system ID
    *
    * @return the reader, or null if the reader could not be created/opened
    */
   protected Reader openExternalEntity(IXMLReader xmlReader,
                                       String     publicID,
                                       String     systemID)
      throws XMLParseException
   {
      String parentSystemID = xmlReader.getSystemID();

      try {
         return xmlReader.openStream(publicID, systemID);
      } catch (Exception e) {
         throw new XMLParseException(parentSystemID,
                                     xmlReader.getLineNr(),
                                     "Could not open external entity "
                                     + "at system ID: " + systemID);
      }
   }
}