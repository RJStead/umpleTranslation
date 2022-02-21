/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package org.jhotdraw.xml;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.io.IOException;

public class DOMInput
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DOMInput()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  public String getTagName();
/**
     * Gets an attribute of the current element of the DOM Document.
     */
    @Nullable public String getAttribute(String name, @Nullable  String defaultValue);
/**
     * Gets the text of the current element of the DOM Document.
     */
    @Nullable public String getText();
/**
     * Gets the text of the current element of the DOM Document.
     */
    @Nullable public String getText(@Nullable String defaultValue);
/**
     * Gets an attribute of the current element of the DOM Document.
     */
    public int getAttribute(String name, int defaultValue);
/**
     * Gets an attribute of the current element of the DOM Document.
     */
    public double getAttribute(String name, double defaultValue);
/**
     * Gets an attribute of the current element of the DOM Document.
     */
    public boolean getAttribute(String name, boolean defaultValue);
/**
     * Gets an attribute of the current element of the DOM Document and of
     * all parent DOM elements.
     */
    public java.util.List<String> getInheritedAttribute(String name);
/**
     * Returns the number of child elements of the current element.
     */
    public int getElementCount();
/**
     * Returns the number of child elements with the specified tag name
     * of the current element.
     */
    public int getElementCount(String tagName);
/**
     * Opens the element with the specified index and makes it the current node.
     */
    public void openElement(int index) throws IOException;
/**
     * Opens the last element with the specified name and makes it the current node.
     */
    public void openElement(String tagName) throws IOException;
/**
     * Opens the element with the specified name and index and makes it the
     * current node.
     */
    public void openElement(String tagName, int index) throws IOException;
/**
     * Closes the current element of the DOM Document.
     * The parent of the current element becomes the current element.

     * @exception IllegalArgumentException if the provided tagName does
     * not match the tag name of the element.
     */
    public void closeElement();
/**
     * Reads an object from the current element.
     */
    public Object readObject() throws IOException;
/**
     * Reads an object from the current element.
     */
    public Object readObject(int index) throws IOException ;
}