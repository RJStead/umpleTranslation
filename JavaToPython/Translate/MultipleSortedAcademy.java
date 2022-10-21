/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;
import java.io.Serializable;

// line 662 "../../TestHarnessAssociations.ump"
public class MultipleSortedAcademy implements java.io.Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MultipleSortedAcademy Attributes
  private transient Comparator<MultipleSortedStudent> registrantsPriority;

  //MultipleSortedAcademy Associations
  private List<MultipleSortedCourse> multipleSortedCourses;
  private List<MultipleSortedStudent> registrants;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MultipleSortedAcademy(Comparator<MultipleSortedStudent> aRegistrantsPriority)
  {
    registrantsPriority = aRegistrantsPriority;
    multipleSortedCourses = new ArrayList<MultipleSortedCourse>();
    registrants = new ArrayList<MultipleSortedStudent>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRegistrantsPriority(Comparator<MultipleSortedStudent> aRegistrantsPriority)
  {
    boolean wasSet = false;
    registrantsPriority = aRegistrantsPriority;
    wasSet = true;
    return wasSet;
  }

  public Comparator<MultipleSortedStudent> getRegistrantsPriority()
  {
    return registrantsPriority;
  }
  /* Code from template association_GetMany */
  public MultipleSortedCourse getMultipleSortedCourse(int index)
  {
    MultipleSortedCourse aMultipleSortedCourse = multipleSortedCourses.get(index);
    return aMultipleSortedCourse;
  }

  public List<MultipleSortedCourse> getMultipleSortedCourses()
  {
    List<MultipleSortedCourse> newMultipleSortedCourses = Collections.unmodifiableList(multipleSortedCourses);
    return newMultipleSortedCourses;
  }

  public int numberOfMultipleSortedCourses()
  {
    int number = multipleSortedCourses.size();
    return number;
  }

  public boolean hasMultipleSortedCourses()
  {
    boolean has = multipleSortedCourses.size() > 0;
    return has;
  }

  public int indexOfMultipleSortedCourse(MultipleSortedCourse aMultipleSortedCourse)
  {
    int index = multipleSortedCourses.indexOf(aMultipleSortedCourse);
    return index;
  }
  /* Code from template association_GetMany */
  public MultipleSortedStudent getRegistrant(int index)
  {
    MultipleSortedStudent aRegistrant = registrants.get(index);
    return aRegistrant;
  }

  public List<MultipleSortedStudent> getRegistrants()
  {
    List<MultipleSortedStudent> newRegistrants = Collections.unmodifiableList(registrants);
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

  public int indexOfRegistrant(MultipleSortedStudent aRegistrant)
  {
    int index = registrants.indexOf(aRegistrant);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMultipleSortedCourses()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MultipleSortedCourse addMultipleSortedCourse(String aCode, Comparator<MultipleSortedRegistration> aMultipleSortedRegistrationsPriority)
  {
    return new MultipleSortedCourse(aCode, aMultipleSortedRegistrationsPriority, this);
  }

  public boolean addMultipleSortedCourse(MultipleSortedCourse aMultipleSortedCourse)
  {
    boolean wasAdded = false;
    if (multipleSortedCourses.contains(aMultipleSortedCourse)) { return false; }
    MultipleSortedAcademy existingMultipleSortedAcademy = aMultipleSortedCourse.getMultipleSortedAcademy();
    boolean isNewMultipleSortedAcademy = existingMultipleSortedAcademy != null && !this.equals(existingMultipleSortedAcademy);
    if (isNewMultipleSortedAcademy)
    {
      aMultipleSortedCourse.setMultipleSortedAcademy(this);
    }
    else
    {
      multipleSortedCourses.add(aMultipleSortedCourse);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMultipleSortedCourse(MultipleSortedCourse aMultipleSortedCourse)
  {
    boolean wasRemoved = false;
    //Unable to remove aMultipleSortedCourse, as it must always have a multipleSortedAcademy
    if (!this.equals(aMultipleSortedCourse.getMultipleSortedAcademy()))
    {
      multipleSortedCourses.remove(aMultipleSortedCourse);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMultipleSortedCourseAt(MultipleSortedCourse aMultipleSortedCourse, int index)
  {  
    boolean wasAdded = false;
    if(addMultipleSortedCourse(aMultipleSortedCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMultipleSortedCourses()) { index = numberOfMultipleSortedCourses() - 1; }
      multipleSortedCourses.remove(aMultipleSortedCourse);
      multipleSortedCourses.add(index, aMultipleSortedCourse);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMultipleSortedCourseAt(MultipleSortedCourse aMultipleSortedCourse, int index)
  {
    boolean wasAdded = false;
    if(multipleSortedCourses.contains(aMultipleSortedCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMultipleSortedCourses()) { index = numberOfMultipleSortedCourses() - 1; }
      multipleSortedCourses.remove(aMultipleSortedCourse);
      multipleSortedCourses.add(index, aMultipleSortedCourse);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMultipleSortedCourseAt(aMultipleSortedCourse, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRegistrants()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MultipleSortedStudent addRegistrant(int aId, String aName, Comparator<MultipleSortedRegistration> aMultipleSortedRegistrationsPriority)
  {
    return new MultipleSortedStudent(aId, aName, aMultipleSortedRegistrationsPriority, this);
  }

  public boolean addRegistrant(MultipleSortedStudent aRegistrant)
  {
    boolean wasAdded = false;
    if (registrants.contains(aRegistrant)) { return false; }
    MultipleSortedAcademy existingMultipleSortedAcademy = aRegistrant.getMultipleSortedAcademy();
    boolean isNewMultipleSortedAcademy = existingMultipleSortedAcademy != null && !this.equals(existingMultipleSortedAcademy);
    if (isNewMultipleSortedAcademy)
    {
      aRegistrant.setMultipleSortedAcademy(this);
    }
    else
    {
      registrants.add(aRegistrant);
    }
    wasAdded = true;
    
    return wasAdded;
  }

  public boolean removeRegistrant(MultipleSortedStudent aRegistrant)
  {
    boolean wasRemoved = false;
    //Unable to remove aRegistrant, as it must always have a multipleSortedAcademy
    if (!this.equals(aRegistrant.getMultipleSortedAcademy()))
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

    registrantsPriority = null;
  }
  
  public void delete()
  {
    for(int i=multipleSortedCourses.size(); i > 0; i--)
    {
      MultipleSortedCourse aMultipleSortedCourse = multipleSortedCourses.get(i - 1);
      aMultipleSortedCourse.delete();
    }
    for(int i=registrants.size(); i > 0; i--)
    {
      MultipleSortedStudent aRegistrant = registrants.get(i - 1);
      aRegistrant.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "registrantsPriority" + "=" + (getRegistrantsPriority() != null ? !getRegistrantsPriority().equals(this)  ? getRegistrantsPriority().toString().replaceAll("  ","    ") : "this" : "null");
  }
}