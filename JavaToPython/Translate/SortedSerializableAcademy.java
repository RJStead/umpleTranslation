/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;
import java.io.Serializable;

// line 715 "../../TestHarnessAssociations.ump"
public class SortedSerializableAcademy implements java.io.Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SortedSerializableAcademy Attributes
  private transient Comparator<SortedSerializableCourse> sortedSerializableCoursesPriority;
  private transient Comparator<SortedSerializableStudent> registrantsPriority;

  //SortedSerializableAcademy Associations
  private List<SortedSerializableCourse> sortedSerializableCourses;
  private List<SortedSerializableStudent> registrants;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SortedSerializableAcademy(Comparator<SortedSerializableCourse> aSortedSerializableCoursesPriority, Comparator<SortedSerializableStudent> aRegistrantsPriority)
  {
    sortedSerializableCoursesPriority = aSortedSerializableCoursesPriority;
    registrantsPriority = aRegistrantsPriority;
    sortedSerializableCourses = new ArrayList<SortedSerializableCourse>();
    registrants = new ArrayList<SortedSerializableStudent>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSortedSerializableCoursesPriority(Comparator<SortedSerializableCourse> aSortedSerializableCoursesPriority)
  {
    boolean wasSet = false;
    sortedSerializableCoursesPriority = aSortedSerializableCoursesPriority;
    wasSet = true;
    return wasSet;
  }

  public boolean setRegistrantsPriority(Comparator<SortedSerializableStudent> aRegistrantsPriority)
  {
    boolean wasSet = false;
    registrantsPriority = aRegistrantsPriority;
    wasSet = true;
    return wasSet;
  }

  public Comparator<SortedSerializableCourse> getSortedSerializableCoursesPriority()
  {
    return sortedSerializableCoursesPriority;
  }

  public Comparator<SortedSerializableStudent> getRegistrantsPriority()
  {
    return registrantsPriority;
  }
  /* Code from template association_GetMany */
  public SortedSerializableCourse getSortedSerializableCourse(int index)
  {
    SortedSerializableCourse aSortedSerializableCourse = sortedSerializableCourses.get(index);
    return aSortedSerializableCourse;
  }

  public List<SortedSerializableCourse> getSortedSerializableCourses()
  {
    List<SortedSerializableCourse> newSortedSerializableCourses = Collections.unmodifiableList(sortedSerializableCourses);
    return newSortedSerializableCourses;
  }

  public int numberOfSortedSerializableCourses()
  {
    int number = sortedSerializableCourses.size();
    return number;
  }

  public boolean hasSortedSerializableCourses()
  {
    boolean has = sortedSerializableCourses.size() > 0;
    return has;
  }

  public int indexOfSortedSerializableCourse(SortedSerializableCourse aSortedSerializableCourse)
  {
    int index = sortedSerializableCourses.indexOf(aSortedSerializableCourse);
    return index;
  }
  /* Code from template association_GetMany */
  public SortedSerializableStudent getRegistrant(int index)
  {
    SortedSerializableStudent aRegistrant = registrants.get(index);
    return aRegistrant;
  }

  public List<SortedSerializableStudent> getRegistrants()
  {
    List<SortedSerializableStudent> newRegistrants = Collections.unmodifiableList(registrants);
    return newRegistrants;
  }

  public int numberOfRegistrants()
  {
    int number = registrants.size();
    return number;
  }

  public boolean hasRegistrants()
  {
    boolean has = registrants.size() > 0;
    return has;
  }

  public int indexOfRegistrant(SortedSerializableStudent aRegistrant)
  {
    int index = registrants.indexOf(aRegistrant);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSortedSerializableCourses()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SortedSerializableCourse addSortedSerializableCourse(String aCode, Comparator<SortedSerializableRegistration> aSortedSerializableRegistrationsPriority)
  {
    return new SortedSerializableCourse(aCode, aSortedSerializableRegistrationsPriority, this);
  }

  public boolean addSortedSerializableCourse(SortedSerializableCourse aSortedSerializableCourse)
  {
    boolean wasAdded = false;
    if (sortedSerializableCourses.contains(aSortedSerializableCourse)) { return false; }
    SortedSerializableAcademy existingSortedSerializableAcademy = aSortedSerializableCourse.getSortedSerializableAcademy();
    boolean isNewSortedSerializableAcademy = existingSortedSerializableAcademy != null && !this.equals(existingSortedSerializableAcademy);
    if (isNewSortedSerializableAcademy)
    {
      aSortedSerializableCourse.setSortedSerializableAcademy(this);
    }
    else
    {
      sortedSerializableCourses.add(aSortedSerializableCourse);
    }
    wasAdded = true;
    
    return wasAdded;
  }

  public boolean removeSortedSerializableCourse(SortedSerializableCourse aSortedSerializableCourse)
  {
    boolean wasRemoved = false;
    //Unable to remove aSortedSerializableCourse, as it must always have a sortedSerializableAcademy
    if (!this.equals(aSortedSerializableCourse.getSortedSerializableAcademy()))
    {
      sortedSerializableCourses.remove(aSortedSerializableCourse);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRegistrants()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SortedSerializableStudent addRegistrant(int aId, String aName)
  {
    return new SortedSerializableStudent(aId, aName, this);
  }

  public boolean addRegistrant(SortedSerializableStudent aRegistrant)
  {
    boolean wasAdded = false;
    if (registrants.contains(aRegistrant)) { return false; }
    SortedSerializableAcademy existingSortedSerializableAcademy = aRegistrant.getSortedSerializableAcademy();
    boolean isNewSortedSerializableAcademy = existingSortedSerializableAcademy != null && !this.equals(existingSortedSerializableAcademy);
    if (isNewSortedSerializableAcademy)
    {
      aRegistrant.setSortedSerializableAcademy(this);
    }
    else
    {
      registrants.add(aRegistrant);
    }
    wasAdded = true;
    
    return wasAdded;
  }

  public boolean removeRegistrant(SortedSerializableStudent aRegistrant)
  {
    boolean wasRemoved = false;
    //Unable to remove aRegistrant, as it must always have a sortedSerializableAcademy
    if (!this.equals(aRegistrant.getSortedSerializableAcademy()))
    {
      registrants.remove(aRegistrant);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  /* Code from template association_sorted_serializable_readObject */ 
  private void readObject(java.io.ObjectInputStream in)
  throws Exception
  {
    in.defaultReadObject();

    sortedSerializableCoursesPriority = null;
    registrantsPriority = null;
  }
  
  public void delete()
  {
    for(int i=sortedSerializableCourses.size(); i > 0; i--)
    {
      SortedSerializableCourse aSortedSerializableCourse = sortedSerializableCourses.get(i - 1);
      aSortedSerializableCourse.delete();
    }
    for(int i=registrants.size(); i > 0; i--)
    {
      SortedSerializableStudent aRegistrant = registrants.get(i - 1);
      aRegistrant.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sortedSerializableCoursesPriority" + "=" + (getSortedSerializableCoursesPriority() != null ? !getSortedSerializableCoursesPriority().equals(this)  ? getSortedSerializableCoursesPriority().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "registrantsPriority" + "=" + (getRegistrantsPriority() != null ? !getRegistrantsPriority().equals(this)  ? getRegistrantsPriority().toString().replaceAll("  ","    ") : "this" : "null");
  }
}