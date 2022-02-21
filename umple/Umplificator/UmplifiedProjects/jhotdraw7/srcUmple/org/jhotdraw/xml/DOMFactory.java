/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package org.jhotdraw.xml;
import java.io.IOException;

public class DOMFactory
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DOMFactory()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  public String getName(Object o);
/**
     * Creates an object from the specified element name.
     */
    public Object create(String name);
/** Writes the specified object to DOMOutput.
     * <p>
     * This method is only ever called from DOMOutput. You should never call
     * this method directly.
     *
     * @param object The object to be written.
     */
    public void write(DOMOutput out, Object object) throws IOException;
/** Reads the specified object from {@code DOMInput}.
     * <p>
     * This method is only ever called from {@code DOMInput}. You should never call
     * this method directly.
     * <p>
     * If the object returned by this method is an instanceof {@link DOMStorable}
     * then {@code DOMInput} invokes its read method.
     *
     * @param in The {@code DOMInput} object which creates the object from an element
     * in a DOM.
     */
    public Object read(DOMInput in) throws IOException;
}