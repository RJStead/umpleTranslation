/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;
import java.io.Serializable;

// line 645 "../../TestHarnessAssociations.ump"
public class ClassWithOneSortedAssociations implements java.io.Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClassWithOneSortedAssociations Attributes
  private transient Comparator<StudentC> studentCsPriority;

  //ClassWithOneSortedAssociations Associations
  private List<StudentC> studentCs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClassWithOneSortedAssociations(Comparator<StudentC> aStudentCsPriority)
  {
    studentCsPriority = aStudentCsPriority;
    studentCs = new ArrayList<StudentC>();
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

  public Comparator<StudentC> getStudentCsPriority()
  {
    return studentCsPriority;
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


  /* Code from template association_sorted_serializable_readObject */ 
  private void readObject(java.io.ObjectInputStream in)
  throws Exception
  {
    in.defaultReadObject();

    studentCsPriority = null;
  }
  
  public void delete()
  {
    studentCs.clear();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "studentCsPriority" + "=" + (getStudentCsPriority() != null ? !getStudentCsPriority().equals(this)  ? getStudentCsPriority().toString().replaceAll("  ","    ") : "this" : "null");
  }
}