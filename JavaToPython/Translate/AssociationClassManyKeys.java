/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 710 "../../TestHarnessAssociations.ump"
public class AssociationClassManyKeys
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AssociationClassManyKeys Associations
  private List<AssociatedClassWithKey> associatedClasses;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetAssociatedClasses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AssociationClassManyKeys()
  {
    cachedHashCode = -1;
    canSetAssociatedClasses = true;
    associatedClasses = new ArrayList<AssociatedClassWithKey>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public AssociatedClassWithKey getAssociatedClass(int index)
  {
    AssociatedClassWithKey aAssociatedClass = associatedClasses.get(index);
    return aAssociatedClass;
  }

  public List<AssociatedClassWithKey> getAssociatedClasses()
  {
    List<AssociatedClassWithKey> newAssociatedClasses = Collections.unmodifiableList(associatedClasses);
    return newAssociatedClasses;
  }

  public int numberOfAssociatedClasses()
  {
    int number = associatedClasses.size();
    return number;
  }

  public boolean hasAssociatedClasses()
  {
    boolean has = associatedClasses.size() > 0;
    return has;
  }

  public int indexOfAssociatedClass(AssociatedClassWithKey aAssociatedClass)
  {
    int index = associatedClasses.indexOf(aAssociatedClass);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssociatedClasses()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAssociatedClass(AssociatedClassWithKey aAssociatedClass)
  {
    boolean wasAdded = false;
    if (!canSetAssociatedClasses) { return false; }
    if (associatedClasses.contains(aAssociatedClass)) { return false; }
    associatedClasses.add(aAssociatedClass);
    if (aAssociatedClass.indexOfAssociationClassManyKey(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAssociatedClass.addAssociationClassManyKey(this);
      if (!wasAdded)
      {
        associatedClasses.remove(aAssociatedClass);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAssociatedClass(AssociatedClassWithKey aAssociatedClass)
  {
    boolean wasRemoved = false;
    if (!canSetAssociatedClasses) { return false; }
    if (!associatedClasses.contains(aAssociatedClass))
    {
      return wasRemoved;
    }

    int oldIndex = associatedClasses.indexOf(aAssociatedClass);
    associatedClasses.remove(oldIndex);
    if (aAssociatedClass.indexOfAssociationClassManyKey(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAssociatedClass.removeAssociationClassManyKey(this);
      if (!wasRemoved)
      {
        associatedClasses.add(oldIndex,aAssociatedClass);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssociatedClassAt(AssociatedClassWithKey aAssociatedClass, int index)
  {  
    boolean wasAdded = false;
    if(addAssociatedClass(aAssociatedClass))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssociatedClasses()) { index = numberOfAssociatedClasses() - 1; }
      associatedClasses.remove(aAssociatedClass);
      associatedClasses.add(index, aAssociatedClass);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssociatedClassAt(AssociatedClassWithKey aAssociatedClass, int index)
  {
    boolean wasAdded = false;
    if(associatedClasses.contains(aAssociatedClass))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssociatedClasses()) { index = numberOfAssociatedClasses() - 1; }
      associatedClasses.remove(aAssociatedClass);
      associatedClasses.add(index, aAssociatedClass);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssociatedClassAt(aAssociatedClass, index);
    }
    return wasAdded;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    AssociationClassManyKeys compareTo = (AssociationClassManyKeys)obj;
  
    if (getAssociatedClasses().size() != compareTo.getAssociatedClasses().size())
    {
      return false;
    }

    for (int i=0; i<getAssociatedClasses().size(); i++)
    {
      AssociatedClassWithKey me = getAssociatedClasses().get(i);
      AssociatedClassWithKey them = compareTo.getAssociatedClasses().get(i);
      if (me == null && them != null)
      {
       return false;
      }
      else if (me != null && !me.equals(them))
      {
        return false;
      }
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
    if (getAssociatedClasses() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getAssociatedClasses().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetAssociatedClasses = false;
    return cachedHashCode;
  }

  public void delete()
  {
    ArrayList<AssociatedClassWithKey> copyOfAssociatedClasses = new ArrayList<AssociatedClassWithKey>(associatedClasses);
    associatedClasses.clear();
    for(AssociatedClassWithKey aAssociatedClass : copyOfAssociatedClasses)
    {
      aAssociatedClass.removeAssociationClassManyKey(this);
    }
  }

}