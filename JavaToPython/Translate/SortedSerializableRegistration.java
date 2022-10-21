/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.io.Serializable;

// line 729 "../../TestHarnessAssociations.ump"
public class SortedSerializableRegistration implements java.io.Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SortedSerializableRegistration Associations
  private SortedSerializableStudent sortedSerializableStudent;
  private SortedSerializableCourse sortedSerializableCourse;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SortedSerializableRegistration(SortedSerializableStudent aSortedSerializableStudent, SortedSerializableCourse aSortedSerializableCourse)
  {
    boolean didAddSortedSerializableStudent = setSortedSerializableStudent(aSortedSerializableStudent);
    if (!didAddSortedSerializableStudent)
    {
      throw new RuntimeException("Unable to create sortedSerializableRegistration due to sortedSerializableStudent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSortedSerializableCourse = setSortedSerializableCourse(aSortedSerializableCourse);
    if (!didAddSortedSerializableCourse)
    {
      throw new RuntimeException("Unable to create sortedSerializableRegistration due to sortedSerializableCourse. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getName()
  {
    return getSortedSerializableStudent().getName();
  }

  public String getCode()
  {
    return getSortedSerializableCourse().getCode();
  }
  /* Code from template association_GetOne */
  public SortedSerializableStudent getSortedSerializableStudent()
  {
    return sortedSerializableStudent;
  }
  /* Code from template association_GetOne */
  public SortedSerializableCourse getSortedSerializableCourse()
  {
    return sortedSerializableCourse;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSortedSerializableStudent(SortedSerializableStudent aSortedSerializableStudent)
  {
    boolean wasSet = false;
    if (aSortedSerializableStudent == null)
    {
      return wasSet;
    }

    SortedSerializableStudent existingSortedSerializableStudent = sortedSerializableStudent;
    sortedSerializableStudent = aSortedSerializableStudent;
    if (existingSortedSerializableStudent != null && !existingSortedSerializableStudent.equals(aSortedSerializableStudent))
    {
      existingSortedSerializableStudent.removeSortedSerializableRegistration(this);
    }
    sortedSerializableStudent.addSortedSerializableRegistration(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSortedSerializableCourse(SortedSerializableCourse aSortedSerializableCourse)
  {
    boolean wasSet = false;
    if (aSortedSerializableCourse == null)
    {
      return wasSet;
    }

    SortedSerializableCourse existingSortedSerializableCourse = sortedSerializableCourse;
    sortedSerializableCourse = aSortedSerializableCourse;
    if (existingSortedSerializableCourse != null && !existingSortedSerializableCourse.equals(aSortedSerializableCourse))
    {
      existingSortedSerializableCourse.removeSortedSerializableRegistration(this);
    }
    sortedSerializableCourse.addSortedSerializableRegistration(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    SortedSerializableStudent placeholderSortedSerializableStudent = sortedSerializableStudent;
    this.sortedSerializableStudent = null;
    if(placeholderSortedSerializableStudent != null)
    {
      placeholderSortedSerializableStudent.removeSortedSerializableRegistration(this);
    }
    SortedSerializableCourse placeholderSortedSerializableCourse = sortedSerializableCourse;
    this.sortedSerializableCourse = null;
    if(placeholderSortedSerializableCourse != null)
    {
      placeholderSortedSerializableCourse.removeSortedSerializableRegistration(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "code" + ":" + getCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sortedSerializableStudent = "+(getSortedSerializableStudent()!=null?Integer.toHexString(System.identityHashCode(getSortedSerializableStudent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "sortedSerializableCourse = "+(getSortedSerializableCourse()!=null?Integer.toHexString(System.identityHashCode(getSortedSerializableCourse())):"null");
  }
}