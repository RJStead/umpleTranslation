/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 700 "../../TestHarnessAssociations.ump"
public class AssociatedClassWithKey
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AssociatedClassWithKey Attributes
  private int id;

  //AssociatedClassWithKey Associations
  private AssociationClass associationClass;
  private List<AssociationClassManyKeys> associationClassManyKeys;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AssociatedClassWithKey(int aId, AssociationClass aAssociationClass)
  {
    cachedHashCode = -1;
    canSetId = true;
    id = aId;
    if (aAssociationClass == null || aAssociationClass.getAssociatedClassWithKey() != null)
    {
      throw new RuntimeException("Unable to create AssociatedClassWithKey due to aAssociationClass. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    associationClass = aAssociationClass;
    associationClassManyKeys = new ArrayList<AssociationClassManyKeys>();
  }

  public AssociatedClassWithKey(int aId)
  {
    id = aId;
    associationClass = new AssociationClass(this);
    associationClassManyKeys = new ArrayList<AssociationClassManyKeys>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    if (!canSetId) { return false; }
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public AssociationClass getAssociationClass()
  {
    return associationClass;
  }
  /* Code from template association_GetMany */
  public AssociationClassManyKeys getAssociationClassManyKey(int index)
  {
    AssociationClassManyKeys aAssociationClassManyKey = associationClassManyKeys.get(index);
    return aAssociationClassManyKey;
  }

  public List<AssociationClassManyKeys> getAssociationClassManyKeys()
  {
    List<AssociationClassManyKeys> newAssociationClassManyKeys = Collections.unmodifiableList(associationClassManyKeys);
    return newAssociationClassManyKeys;
  }

  public int numberOfAssociationClassManyKeys()
  {
    int number = associationClassManyKeys.size();
    return number;
  }

  public boolean hasAssociationClassManyKeys()
  {
    boolean has = associationClassManyKeys.size() > 0;
    return has;
  }

  public int indexOfAssociationClassManyKey(AssociationClassManyKeys aAssociationClassManyKey)
  {
    int index = associationClassManyKeys.indexOf(aAssociationClassManyKey);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssociationClassManyKeys()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAssociationClassManyKey(AssociationClassManyKeys aAssociationClassManyKey)
  {
    boolean wasAdded = false;
    if (associationClassManyKeys.contains(aAssociationClassManyKey)) { return false; }
    associationClassManyKeys.add(aAssociationClassManyKey);
    if (aAssociationClassManyKey.indexOfAssociatedClass(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAssociationClassManyKey.addAssociatedClass(this);
      if (!wasAdded)
      {
        associationClassManyKeys.remove(aAssociationClassManyKey);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAssociationClassManyKey(AssociationClassManyKeys aAssociationClassManyKey)
  {
    boolean wasRemoved = false;
    if (!associationClassManyKeys.contains(aAssociationClassManyKey))
    {
      return wasRemoved;
    }

    int oldIndex = associationClassManyKeys.indexOf(aAssociationClassManyKey);
    associationClassManyKeys.remove(oldIndex);
    if (aAssociationClassManyKey.indexOfAssociatedClass(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAssociationClassManyKey.removeAssociatedClass(this);
      if (!wasRemoved)
      {
        associationClassManyKeys.add(oldIndex,aAssociationClassManyKey);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssociationClassManyKeyAt(AssociationClassManyKeys aAssociationClassManyKey, int index)
  {  
    boolean wasAdded = false;
    if(addAssociationClassManyKey(aAssociationClassManyKey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssociationClassManyKeys()) { index = numberOfAssociationClassManyKeys() - 1; }
      associationClassManyKeys.remove(aAssociationClassManyKey);
      associationClassManyKeys.add(index, aAssociationClassManyKey);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssociationClassManyKeyAt(AssociationClassManyKeys aAssociationClassManyKey, int index)
  {
    boolean wasAdded = false;
    if(associationClassManyKeys.contains(aAssociationClassManyKey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssociationClassManyKeys()) { index = numberOfAssociationClassManyKeys() - 1; }
      associationClassManyKeys.remove(aAssociationClassManyKey);
      associationClassManyKeys.add(index, aAssociationClassManyKey);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssociationClassManyKeyAt(aAssociationClassManyKey, index);
    }
    return wasAdded;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    AssociatedClassWithKey compareTo = (AssociatedClassWithKey)obj;
  
    if (getId() != compareTo.getId())
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
    cachedHashCode = cachedHashCode * 23 + getId();

    canSetId = false;
    return cachedHashCode;
  }

  public void delete()
  {
    AssociationClass existingAssociationClass = associationClass;
    associationClass = null;
    if (existingAssociationClass != null)
    {
      existingAssociationClass.delete();
    }
    ArrayList<AssociationClassManyKeys> copyOfAssociationClassManyKeys = new ArrayList<AssociationClassManyKeys>(associationClassManyKeys);
    associationClassManyKeys.clear();
    for(AssociationClassManyKeys aAssociationClassManyKey : copyOfAssociationClassManyKeys)
    {
      aAssociationClassManyKey.removeAssociatedClass(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "associationClass = "+(getAssociationClass()!=null?Integer.toHexString(System.identityHashCode(getAssociationClass())):"null");
  }
}