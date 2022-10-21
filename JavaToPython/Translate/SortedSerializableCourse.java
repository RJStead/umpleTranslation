/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;
import java.io.Serializable;

// line 725 "../../TestHarnessAssociations.ump"
public class SortedSerializableCourse implements java.io.Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SortedSerializableCourse Attributes
  private String code;
  private transient Comparator<SortedSerializableRegistration> sortedSerializableRegistrationsPriority;

  //SortedSerializableCourse Associations
  private SortedSerializableAcademy sortedSerializableAcademy;
  private List<SortedSerializableRegistration> sortedSerializableRegistrations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SortedSerializableCourse(String aCode, Comparator<SortedSerializableRegistration> aSortedSerializableRegistrationsPriority, SortedSerializableAcademy aSortedSerializableAcademy)
  {
    code = aCode;
    sortedSerializableRegistrationsPriority = aSortedSerializableRegistrationsPriority;
    boolean didAddSortedSerializableAcademy = setSortedSerializableAcademy(aSortedSerializableAcademy);
    if (!didAddSortedSerializableAcademy)
    {
      throw new RuntimeException("Unable to create sortedSerializableCourse due to sortedSerializableAcademy. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    sortedSerializableRegistrations = new ArrayList<SortedSerializableRegistration>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCode(String aCode)
  {
    boolean wasSet = false;
    code = aCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setSortedSerializableRegistrationsPriority(Comparator<SortedSerializableRegistration> aSortedSerializableRegistrationsPriority)
  {
    boolean wasSet = false;
    sortedSerializableRegistrationsPriority = aSortedSerializableRegistrationsPriority;
    wasSet = true;
    return wasSet;
  }

  public String getCode()
  {
    return code;
  }

  public Comparator<SortedSerializableRegistration> getSortedSerializableRegistrationsPriority()
  {
    return sortedSerializableRegistrationsPriority;
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
      existingSortedSerializableAcademy.removeSortedSerializableCourse(this);
    }
    sortedSerializableAcademy.addSortedSerializableCourse(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSortedSerializableRegistrations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SortedSerializableRegistration addSortedSerializableRegistration(SortedSerializableStudent aSortedSerializableStudent)
  {
    return new SortedSerializableRegistration(aSortedSerializableStudent, this);
  }

  public boolean addSortedSerializableRegistration(SortedSerializableRegistration aSortedSerializableRegistration)
  {
    boolean wasAdded = false;
    if (sortedSerializableRegistrations.contains(aSortedSerializableRegistration)) { return false; }
    SortedSerializableCourse existingSortedSerializableCourse = aSortedSerializableRegistration.getSortedSerializableCourse();
    boolean isNewSortedSerializableCourse = existingSortedSerializableCourse != null && !this.equals(existingSortedSerializableCourse);
    if (isNewSortedSerializableCourse)
    {
      aSortedSerializableRegistration.setSortedSerializableCourse(this);
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
    //Unable to remove aSortedSerializableRegistration, as it must always have a sortedSerializableCourse
    if (!this.equals(aSortedSerializableRegistration.getSortedSerializableCourse()))
    {
      sortedSerializableRegistrations.remove(aSortedSerializableRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }


  /* Code from template association_sorted_serializable_readObject */ 
  private void readObject(java.io.ObjectInputStream in)
  throws Exception
  {
    in.defaultReadObject();

    sortedSerializableRegistrationsPriority = null;
  }
  
  public void delete()
  {
    SortedSerializableAcademy placeholderSortedSerializableAcademy = sortedSerializableAcademy;
    this.sortedSerializableAcademy = null;
    if(placeholderSortedSerializableAcademy != null)
    {
      placeholderSortedSerializableAcademy.removeSortedSerializableCourse(this);
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
            "code" + ":" + getCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sortedSerializableRegistrationsPriority" + "=" + (getSortedSerializableRegistrationsPriority() != null ? !getSortedSerializableRegistrationsPriority().equals(this)  ? getSortedSerializableRegistrationsPriority().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "sortedSerializableAcademy = "+(getSortedSerializableAcademy()!=null?Integer.toHexString(System.identityHashCode(getSortedSerializableAcademy())):"null");
  }
}