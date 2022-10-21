/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 640 "../../TestHarnessAssociations.ump"
public class Otherclass
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Otherclass Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Otherclass(String aName)
  {
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

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