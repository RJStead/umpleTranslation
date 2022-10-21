/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;
import java.io.Serializable;

// line 648 "../../TestHarnessAssociations.ump"
public class ClassWithManySortedAssociations implements java.io.Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClassWithManySortedAssociations Attributes
  private transient Comparator<StudentC> studentCsPriority;
  private transient Comparator<StudentAL> studentALsPriority;

  //ClassWithManySortedAssociations Associations
  private List<StudentC> studentCs;
  private List<StudentAL> studentALs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClassWithManySortedAssociations(Comparator<StudentC> aStudentCsPriority, Comparator<StudentAL> aStudentALsPriority)
  {
    studentCsPriority = aStudentCsPriority;
    studentALsPriority = aStudentALsPriority;
    studentCs = new ArrayList<StudentC>();
    studentALs = new ArrayList<StudentAL>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStudentCsPriority(Comparator<StudentC> aStudentCsPriority)
  {
    boolean wasSet = false;
    studentCsPriority = aStudentCsPriority;
    wasSet = true;
    return wasSet;
  }

  public boolean setStudentALsPriority(Comparator<StudentAL> aStudentALsPriority)
  {
    boolean wasSet = false;
    studentALsPriority = aStudentALsPriority;
    wasSet = true;
    return wasSet;
  }

  public Comparator<StudentC> getStudentCsPriority()
  {
    return studentCsPriority;
  }

  public Comparator<StudentAL> getStudentALsPriority()
  {
    return studentALsPriority;
  }
  /* Code from template association_GetMany */
  public StudentC getStudentC(int index)
  {
    StudentC aStudentC = studentCs.get(index);
    return aStudentC;
  }

  public List<StudentC> getStudentCs()
  {
    List<StudentC> newStudentCs = Collections.unmodifiableList(studentCs);
    return newStudentCs;
  }

  public int numberOfStudentCs()
  {
    int number = studentCs.size();
    return number;
  }

  public boolean hasStudentCs()
  {
    boolean has = studentCs.size() > 0;
    return has;
  }

  public int indexOfStudentC(StudentC aStudentC)
  {
    int index = studentCs.indexOf(aStudentC);
    return index;
  }
  /* Code from template association_GetMany */
  public StudentAL getStudentAL(int index)
  {
    StudentAL aStudentAL = studentALs.get(index);
    return aStudentAL;
  }

  public List<StudentAL> getStudentALs()
  {
    List<StudentAL> newStudentALs = Collections.unmodifiableList(studentALs);
    return newStudentALs;
  }

  public int numberOfStudentALs()
  {
    int number = studentALs.size();
    return number;
  }

  public boolean hasStudentALs()
  {
    boolean has = studentALs.size() > 0;
    return has;
  }

  public int indexOfStudentAL(StudentAL aStudentAL)
  {
    int index = studentALs.indexOf(aStudentAL);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStudentCs()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addStudentC(StudentC aStudentC)
  {
    boolean wasAdded = false;
    if (studentCs.contains(aStudentC)) { return false; }
    studentCs.add(aStudentC);
    wasAdded = true;
    
    return wasAdded;
  }

  public boolean removeStudentC(StudentC aStudentC)
  {
    boolean wasRemoved = false;
    if (studentCs.contains(aStudentC))
    {
      studentCs.remove(aStudentC);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStudentALs()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addStudentAL(StudentAL aStudentAL)
  {
    boolean wasAdded = false;
    if (studentALs.contains(aStudentAL)) { return false; }
    studentALs.add(aStudentAL);
    wasAdded = true;
    
    return wasAdded;
  }

  public boolean removeStudentAL(StudentAL aStudentAL)
  {
    boolean wasRemoved = false;
    if (studentALs.contains(aStudentAL))
    {
      studentALs.remove(aStudentAL);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  /* Code from template association_sorted_serializable_readObject */ 
  private void readObject(java.io.ObjectInputStream in)
  throws Exception
  {
    in.defaultReadObject();

    studentCsPriority = null;
    studentALsPriority = null;
  }
  
  public void delete()
  {
    studentCs.clear();
    studentALs.clear();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "studentCsPriority" + "=" + (getStudentCsPriority() != null ? !getStudentCsPriority().equals(this)  ? getStudentCsPriority().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "studentALsPriority" + "=" + (getStudentALsPriority() != null ? !getStudentALsPriority().equals(this)  ? getStudentALsPriority().toString().replaceAll("  ","    ") : "this" : "null");
  }
}