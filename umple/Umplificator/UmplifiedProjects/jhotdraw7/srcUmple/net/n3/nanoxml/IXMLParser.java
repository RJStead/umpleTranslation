/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package net.n3.nanoxml;
import java.io.IOException;

public class IXMLParser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public IXMLParser()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  public void setReader(IXMLReader reader);
/**
    * Returns the reader from which the parser retrieves its data.
    *
    * @return the reader.
    */
   public IXMLReader getReader();
/**
    * Sets the builder which creates the logical structure of the XML data.
    *
    * @param builder the builder.
    */
   public void setBuilder(IXMLBuilder builder);
/**
    * Returns the builder which creates the logical structure of the XML data.
    *
    * @return the builder.
    */
   public IXMLBuilder getBuilder();
/**
    * Sets the validator that validates the XML data.
    *
    * @param validator the validator.
    */
   public void setValidator(IXMLValidator validator);
/**
    * Returns the validator that validates the XML data.
    *
    * @return the validator.
    */
   public IXMLValidator getValidator();
/**
    * Sets the entity resolver.
    *
    * @param resolver the non-null resolver.
    */
   public void setResolver(IXMLEntityResolver resolver);
/**
    * Returns the entity resolver.
    *
    * @return the non-null resolver.
    */
   public IXMLEntityResolver getResolver();
/**
    * Parses the data and lets the builder create the logical data structure.
    * The method returns the result of <I>getResult</I> of the builder. if an
    * error occurred while reading or parsing the data, the method may throw
    * an XMLException.
    *
    * @see net.n3.nanoxml.IXMLBuilder#getResult
    *
    * @return the logical structure built by the builder.
    *
    * @throws net.n3.nanoxml.XMLException
    *		if an error occurred reading or parsing the data
    */
   public Object parse()
      throws XMLException;
}