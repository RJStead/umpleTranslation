/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.io.Serializable;

// line 653 "../../TestHarnessAssociations.ump"
public class ClassThatWillHaveSortedAssociationOne implements java.io.Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClassThatWillHaveSortedAssociationOne Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClassThatWillHaveSortedAssociationOne(String aName)
  {
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}