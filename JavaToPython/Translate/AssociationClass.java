/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 705 "../../TestHarnessAssociations.ump"
public class AssociationClass
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AssociationClass Associations
  private AssociatedClassWithKey associatedClassWithKey;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetAssociatedClassWithKey;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AssociationClass(AssociatedClassWithKey aAssociatedClassWithKey)
  {
    cachedHashCode = -1;
    canSetAssociatedClassWithKey = true;
    if (aAssociatedClassWithKey == null || aAssociatedClassWithKey.getAssociationClass() != null)
    {
      throw new RuntimeException("Unable to create AssociationClass due to aAssociatedClassWithKey. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    associatedClassWithKey = aAssociatedClassWithKey;
  }

  public AssociationClass(int aIdForAssociatedClassWithKey)
  {
    associatedClassWithKey = new AssociatedClassWithKey(aIdForAssociatedClassWithKey, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public AssociatedClassWithKey getAssociatedClassWithKey()
  {
    return associatedClassWithKey;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    AssociationClass compareTo = (AssociationClass)obj;
  
    if (getAssociatedClassWithKey() == null && compareTo.getAssociatedClassWithKey() != null)
    {
      return false;
    }
    else if (getAssociatedClassWithKey() != null && !getAssociatedClassWithKey().equals(compareTo.getAssociatedClassWithKey()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getAssociatedClassWithKey() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getAssociatedClassWithKey().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetAssociatedClassWithKey = false;
    return cachedHashCode;
  }

  public void delete()
  {
    AssociatedClassWithKey existingAssociatedClassWithKey = associatedClassWithKey;
    associatedClassWithKey = null;
    if (existingAssociatedClassWithKey != null)
    {
      existingAssociatedClassWithKey.delete();
    }
  }

}