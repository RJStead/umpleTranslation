/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.io.Serializable;

// line 676 "../../TestHarnessAssociations.ump"
public class MultipleSortedRegistration implements java.io.Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MultipleSortedRegistration Associations
  private MultipleSortedCourse multipleSortedCourse;
  private MultipleSortedStudent multipleSortedStudent;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MultipleSortedRegistration(MultipleSortedCourse aMultipleSortedCourse, MultipleSortedStudent aMultipleSortedStudent)
  {
    // Direct set of variable needed to ensure consistency
    // for multiple sorted associations
    multipleSortedCourse=aMultipleSortedCourse;
    multipleSortedStudent=aMultipleSortedStudent;
    boolean didAddMultipleSortedCourse = setMultipleSortedCourse(aMultipleSortedCourse);
    if (!didAddMultipleSortedCourse)
    {
      throw new RuntimeException("Unable to create multipleSortedRegistration due to multipleSortedCourse. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddMultipleSortedStudent = setMultipleSortedStudent(aMultipleSortedStudent);
    if (!didAddMultipleSortedStudent)
    {
      throw new RuntimeException("Unable to create multipleSortedRegistration due to multipleSortedStudent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getName()
  {
    return getMultipleSortedStudent().getName();
  }

  public String getCode()
  {
    return getMultipleSortedCourse().getCode();
  }
  /* Code from template association_GetOne */
  public MultipleSortedCourse getMultipleSortedCourse()
  {
    return multipleSortedCourse;
  }
  /* Code from template association_GetOne */
  public MultipleSortedStudent getMultipleSortedStudent()
  {
    return multipleSortedStudent;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMultipleSortedCourse(MultipleSortedCourse aMultipleSortedCourse)
  {
    boolean wasSet = false;
    if (aMultipleSortedCourse == null)
    {
      return wasSet;
    }

    MultipleSortedCourse existingMultipleSortedCourse = multipleSortedCourse;
    multipleSortedCourse = aMultipleSortedCourse;
    if (existingMultipleSortedCourse != null && !existingMultipleSortedCourse.equals(aMultipleSortedCourse))
    {
      existingMultipleSortedCourse.removeMultipleSortedRegistration(this);
    }
    multipleSortedCourse.addMultipleSortedRegistration(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMultipleSortedStudent(MultipleSortedStudent aMultipleSortedStudent)
  {
    boolean wasSet = false;
    if (aMultipleSortedStudent == null)
    {
      return wasSet;
    }

    MultipleSortedStudent existingMultipleSortedStudent = multipleSortedStudent;
    multipleSortedStudent = aMultipleSortedStudent;
    if (existingMultipleSortedStudent != null && !existingMultipleSortedStudent.equals(aMultipleSortedStudent))
    {
      existingMultipleSortedStudent.removeMultipleSortedRegistration(this);
    }
    multipleSortedStudent.addMultipleSortedRegistration(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    MultipleSortedCourse placeholderMultipleSortedCourse = multipleSortedCourse;
    this.multipleSortedCourse = null;
    if(placeholderMultipleSortedCourse != null)
    {
      placeholderMultipleSortedCourse.removeMultipleSortedRegistration(this);
    }
    MultipleSortedStudent placeholderMultipleSortedStudent = multipleSortedStudent;
    this.multipleSortedStudent = null;
    if(placeholderMultipleSortedStudent != null)
    {
      placeholderMultipleSortedStudent.removeMultipleSortedRegistration(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "code" + ":" + getCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "multipleSortedCourse = "+(getMultipleSortedCourse()!=null?Integer.toHexString(System.identityHashCode(getMultipleSortedCourse())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "multipleSortedStudent = "+(getMultipleSortedStudent()!=null?Integer.toHexString(System.identityHashCode(getMultipleSortedStudent())):"null");
  }
}