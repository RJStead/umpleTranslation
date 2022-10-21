/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 635 "../../TestHarnessAssociations.ump"
public class ClassOptionalOneAssociation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClassOptionalOneAssociation Associations
  private Otherclass otherclass;

  //Helper Variables
  private boolean canSetOtherclass;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClassOptionalOneAssociation(Otherclass aOtherclass)
  {
    canSetOtherclass = true;
    setOtherclass(aOtherclass);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Otherclass getOtherclass()
  {
    return otherclass;
  }

  public boolean hasOtherclass()
  {
    boolean has = otherclass != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  private boolean setOtherclass(Otherclass aNewOtherclass)
  {
    boolean wasSet = false;
    if (!canSetOtherclass) { return false; }
    canSetOtherclass = false;
    otherclass = aNewOtherclass;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {}

}