/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 536 "../../TestHarnessAssociations.ump"
public class MentorAJ
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorAJ Attributes
  private String name;

  //MentorAJ Associations
  private List<StudentAJ> students;
  private ProgramAJ program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorAJ(String aName, StudentAJ... allStudents)
  {
    name = aName;
    students = new ArrayList<StudentAJ>();
    boolean didAddStudents = setStudents(allStudents);
    if (!didAddStudents)
    {
      throw new RuntimeException("Unable to create MentorAJ, must have 3 students. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public StudentAJ getStudent(int index)
  {
    StudentAJ aStudent = students.get(index);
    return aStudent;
  }

  public List<StudentAJ> getStudents()
  {
    List<StudentAJ> newStudents = Collections.unmodifiableList(students);
    return newStudents;
  }

  public int numberOfStudents()
  {
    int number = students.size();
    return number;
  }

  public boolean hasStudents()
  {
    boolean has = students.size() > 0;
    return has;
  }

  public int indexOfStudent(StudentAJ aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
  }
  /* Code from template association_GetOne */
  public ProgramAJ getProgram()
  {
    return program;
  }

  public boolean hasProgram()
  {
    boolean has = program != null;
    return has;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfStudents()
  {
    return 3;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStudents()
  {
    return 3;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfStudents()
  {
    return 3;
  }
  /* Code from template association_SetUnidirectionalN */
  public boolean setStudents(StudentAJ... newStudents)
  {
    boolean wasSet = false;
    ArrayList<StudentAJ> verifiedStudents = new ArrayList<StudentAJ>();
    for (StudentAJ aStudent : newStudents)
    {
      if (verifiedStudents.contains(aStudent))
      {
        continue;
      }
      verifiedStudents.add(aStudent);
    }

    if (verifiedStudents.size() != newStudents.length || verifiedStudents.size() != requiredNumberOfStudents())
    {
      return wasSet;
    }

    students.clear();
    students.addAll(verifiedStudents);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setProgram(ProgramAJ aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramAJ existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getMentor() != null)
      {
        existingProgram.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramAJ currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setMentor(null);
    }

    program = aNewProgram;
    MentorAJ existingMentor = aNewProgram.getMentor();

    if (!equals(existingMentor))
    {
      aNewProgram.setMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    students.clear();
    if (program != null)
    {
      program.setMentor(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "program = "+(getProgram()!=null?Integer.toHexString(System.identityHashCode(getProgram())):"null");
  }
}