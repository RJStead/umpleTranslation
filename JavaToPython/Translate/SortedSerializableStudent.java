/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;
import java.io.Serializable;

// line 720 "../../TestHarnessAssociations.ump"
public class SortedSerializableStudent implements java.io.Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SortedSerializableStudent Attributes
  private int id;
  private String name;

  //SortedSerializableStudent Associations
  private SortedSerializableAcademy sortedSerializableAcademy;
  private List<SortedSerializableRegistration> sortedSerializableRegistrations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SortedSerializableStudent(int aId, String aName, SortedSerializableAcademy aSortedSerializableAcademy)
  {
    id = aId;
    name = aName;
    boolean didAddSortedSerializableAcademy = setSortedSerializableAcademy(aSortedSerializableAcademy);
    if (!didAddSortedSerializableAcademy)
    {
      throw new RuntimeException("Unable to create registrant due to sortedSerializableAcademy. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    sortedSerializableRegistrations = new ArrayList<SortedSerializableRegistration>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne */
  public SortedSerializableAcademy getSortedSerializableAcademy()
  {
    return sortedSerializableAcademy;
  }
  /* Code from template association_GetMany */
  public SortedSerializableRegistration getSortedSerializableRegistration(int index)
  {
    SortedSerializableRegistration aSortedSerializableRegistration = sortedSerializableRegistrations.get(index);
    return aSortedSerializableRegistration;
  }

  public List<SortedSerializableRegistration> getSortedSerializableRegistrations()
  {
    List<SortedSerializableRegistration> newSortedSerializableRegistrations = Collections.unmodifiableList(sortedSerializableRegistrations);
    return newSortedSerializableRegistrations;
  }

  public int numberOfSortedSerializableRegistrations()
  {
    int number = sortedSerializableRegistrations.size();
    return number;
  }

  public boolean hasSortedSerializableRegistrations()
  {
    boolean has = sortedSerializableRegistrations.size() > 0;
    return has;
  }

  public int indexOfSortedSerializableRegistration(SortedSerializableRegistration aSortedSerializableRegistration)
  {
    int index = sortedSerializableRegistrations.indexOf(aSortedSerializableRegistration);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSortedSerializableAcademy(SortedSerializableAcademy aSortedSerializableAcademy)
  {
    boolean wasSet = false;
    if (aSortedSerializableAcademy == null)
    {
      return wasSet;
    }

    SortedSerializableAcademy existingSortedSerializableAcademy = sortedSerializableAcademy;
    sortedSerializableAcademy = aSortedSerializableAcademy;
    if (existingSortedSerializableAcademy != null && !existingSortedSerializableAcademy.equals(aSortedSerializableAcademy))
    {
      existingSortedSerializableAcademy.removeRegistrant(this);
    }
    sortedSerializableAcademy.addRegistrant(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSortedSerializableRegistrations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SortedSerializableRegistration addSortedSerializableRegistration(SortedSerializableCourse aSortedSerializableCourse)
  {
    return new SortedSerializableRegistration(this, aSortedSerializableCourse);
  }

  public boolean addSortedSerializableRegistration(SortedSerializableRegistration aSortedSerializableRegistration)
  {
    boolean wasAdded = false;
    if (sortedSerializableRegistrations.contains(aSortedSerializableRegistration)) { return false; }
    SortedSerializableStudent existingSortedSerializableStudent = aSortedSerializableRegistration.getSortedSerializableStudent();
    boolean isNewSortedSerializableStudent = existingSortedSerializableStudent != null && !this.equals(existingSortedSerializableStudent);
    if (isNewSortedSerializableStudent)
    {
      aSortedSerializableRegistration.setSortedSerializableStudent(this);
    }
    else
    {
      sortedSerializableRegistrations.add(aSortedSerializableRegistration);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSortedSerializableRegistration(SortedSerializableRegistration aSortedSerializableRegistration)
  {
    boolean wasRemoved = false;
    //Unable to remove aSortedSerializableRegistration, as it must always have a sortedSerializableStudent
    if (!this.equals(aSortedSerializableRegistration.getSortedSerializableStudent()))
    {
      sortedSerializableRegistrations.remove(aSortedSerializableRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSortedSerializableRegistrationAt(SortedSerializableRegistration aSortedSerializableRegistration, int index)
  {  
    boolean wasAdded = false;
    if(addSortedSerializableRegistration(aSortedSerializableRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSortedSerializableRegistrations()) { index = numberOfSortedSerializableRegistrations() - 1; }
      sortedSerializableRegistrations.remove(aSortedSerializableRegistration);
      sortedSerializableRegistrations.add(index, aSortedSerializableRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSortedSerializableRegistrationAt(SortedSerializableRegistration aSortedSerializableRegistration, int index)
  {
    boolean wasAdded = false;
    if(sortedSerializableRegistrations.contains(aSortedSerializableRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSortedSerializableRegistrations()) { index = numberOfSortedSerializableRegistrations() - 1; }
      sortedSerializableRegistrations.remove(aSortedSerializableRegistration);
      sortedSerializableRegistrations.add(index, aSortedSerializableRegistration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSortedSerializableRegistrationAt(aSortedSerializableRegistration, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    SortedSerializableAcademy placeholderSortedSerializableAcademy = sortedSerializableAcademy;
    this.sortedSerializableAcademy = null;
    if(placeholderSortedSerializableAcademy != null)
    {
      placeholderSortedSerializableAcademy.removeRegistrant(this);
    }
    for(int i=sortedSerializableRegistrations.size(); i > 0; i--)
    {
      SortedSerializableRegistration aSortedSerializableRegistration = sortedSerializableRegistrations.get(i - 1);
      aSortedSerializableRegistration.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sortedSerializableAcademy = "+(getSortedSerializableAcademy()!=null?Integer.toHexString(System.identityHashCode(getSortedSerializableAcademy())):"null");
  }
}