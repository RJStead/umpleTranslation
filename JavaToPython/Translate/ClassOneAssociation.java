/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 625 "../../TestHarnessAssociations.ump"
public class ClassOneAssociation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClassOneAssociation Associations
  private Otherclass otherclass;

  //Helper Variables
  private boolean canSetOtherclass;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClassOneAssociation(Otherclass aOtherclass)
  {
    canSetOtherclass = true;
    if (!setOtherclass(aOtherclass))
    {
      throw new RuntimeException("Unable to create ClassOneAssociation due to aOtherclass. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Otherclass getOtherclass()
  {
    return otherclass;
  }
  /* Code from template association_SetUnidirectionalOne */
  private boolean setOtherclass(Otherclass aNewOtherclass)
  {
    boolean wasSet = false;
    if (!canSetOtherclass) { return false; }
    canSetOtherclass = false;
    if (aNewOtherclass != null)
    {
      otherclass = aNewOtherclass;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {}

}