/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;
import java.io.Serializable;

// line 667 "../../TestHarnessAssociations.ump"
public class MultipleSortedStudent implements java.io.Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MultipleSortedStudent Attributes
  private int id;
  private String name;
  private transient Comparator<MultipleSortedRegistration> multipleSortedRegistrationsPriority;

  //MultipleSortedStudent Associations
  private MultipleSortedAcademy multipleSortedAcademy;
  private List<MultipleSortedRegistration> multipleSortedRegistrations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MultipleSortedStudent(int aId, String aName, Comparator<MultipleSortedRegistration> aMultipleSortedRegistrationsPriority, MultipleSortedAcademy aMultipleSortedAcademy)
  {
    id = aId;
    name = aName;
    multipleSortedRegistrationsPriority = aMultipleSortedRegistrationsPriority;
    boolean didAddMultipleSortedAcademy = setMultipleSortedAcademy(aMultipleSortedAcademy);
    if (!didAddMultipleSortedAcademy)
    {
      throw new RuntimeException("Unable to create registrant due to multipleSortedAcademy. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    multipleSortedRegistrations = new ArrayList<MultipleSortedRegistration>();
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

  public boolean setMultipleSortedRegistrationsPriority(Comparator<MultipleSortedRegistration> aMultipleSortedRegistrationsPriority)
  {
    boolean wasSet = false;
    multipleSortedRegistrationsPriority = aMultipleSortedRegistrationsPriority;
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

  public Comparator<MultipleSortedRegistration> getMultipleSortedRegistrationsPriority()
  {
    return multipleSortedRegistrationsPriority;
  }
  /* Code from template association_GetOne */
  public MultipleSortedAcademy getMultipleSortedAcademy()
  {
    return multipleSortedAcademy;
  }
  /* Code from template association_GetMany */
  public MultipleSortedRegistration getMultipleSortedRegistration(int index)
  {
    MultipleSortedRegistration aMultipleSortedRegistration = multipleSortedRegistrations.get(index);
    return aMultipleSortedRegistration;
  }

  public List<MultipleSortedRegistration> getMultipleSortedRegistrations()
  {
    List<MultipleSortedRegistration> newMultipleSortedRegistrations = Collections.unmodifiableList(multipleSortedRegistrations);
    return newMultipleSortedRegistrations;
  }

  public int numberOfMultipleSortedRegistrations()
  {
    int number = multipleSortedRegistrations.size();
    return number;
  }

  public boolean hasMultipleSortedRegistrations()
  {
    boolean has = multipleSortedRegistrations.size() > 0;
    return has;
  }

  public int indexOfMultipleSortedRegistration(MultipleSortedRegistration aMultipleSortedRegistration)
  {
    int index = multipleSortedRegistrations.indexOf(aMultipleSortedRegistration);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMultipleSortedAcademy(MultipleSortedAcademy aMultipleSortedAcademy)
  {
    boolean wasSet = false;
    if (aMultipleSortedAcademy == null)
    {
      return wasSet;
    }

    MultipleSortedAcademy existingMultipleSortedAcademy = multipleSortedAcademy;
    multipleSortedAcademy = aMultipleSortedAcademy;
    if (existingMultipleSortedAcademy != null && !existingMultipleSortedAcademy.equals(aMultipleSortedAcademy))
    {
      existingMultipleSortedAcademy.removeRegistrant(this);
    }
    multipleSortedAcademy.addRegistrant(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMultipleSortedRegistrations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MultipleSortedRegistration addMultipleSortedRegistration(MultipleSortedCourse aMultipleSortedCourse)
  {
    return new MultipleSortedRegistration(aMultipleSortedCourse, this);
  }

  public boolean addMultipleSortedRegistration(MultipleSortedRegistration aMultipleSortedRegistration)
  {
    boolean wasAdded = false;
    if (multipleSortedRegistrations.contains(aMultipleSortedRegistration)) { return false; }
    MultipleSortedStudent existingMultipleSortedStudent = aMultipleSortedRegistration.getMultipleSortedStudent();
    boolean isNewMultipleSortedStudent = existingMultipleSortedStudent != null && !this.equals(existingMultipleSortedStudent);
    if (isNewMultipleSortedStudent)
    {
      aMultipleSortedRegistration.setMultipleSortedStudent(this);
    }
    else
    {
      multipleSortedRegistrations.add(aMultipleSortedRegistration);
    }
    wasAdded = true;
    
    return wasAdded;
  }

  public boolean removeMultipleSortedRegistration(MultipleSortedRegistration aMultipleSortedRegistration)
  {
    boolean wasRemoved = false;
    //Unable to remove aMultipleSortedRegistration, as it must always have a multipleSortedStudent
    if (!this.equals(aMultipleSortedRegistration.getMultipleSortedStudent()))
    {
      multipleSortedRegistrations.remove(aMultipleSortedRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }


  /* Code from template association_sorted_serializable_readObject */ 
  private void readObject(java.io.ObjectInputStream in)
  throws Exception
  {
    in.defaultReadObject();

    multipleSortedRegistrationsPriority = null;
  }
  
  public void delete()
  {
    MultipleSortedAcademy placeholderMultipleSortedAcademy = multipleSortedAcademy;
    this.multipleSortedAcademy = null;
    if(placeholderMultipleSortedAcademy != null)
    {
      placeholderMultipleSortedAcademy.removeRegistrant(this);
    }
    for(int i=multipleSortedRegistrations.size(); i > 0; i--)
    {
      MultipleSortedRegistration aMultipleSortedRegistration = multipleSortedRegistrations.get(i - 1);
      aMultipleSortedRegistration.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "multipleSortedRegistrationsPriority" + "=" + (getMultipleSortedRegistrationsPriority() != null ? !getMultipleSortedRegistrationsPriority().equals(this)  ? getMultipleSortedRegistrationsPriority().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "multipleSortedAcademy = "+(getMultipleSortedAcademy()!=null?Integer.toHexString(System.identityHashCode(getMultipleSortedAcademy())):"null");
  }
}