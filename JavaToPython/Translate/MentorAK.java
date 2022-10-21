/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 552 "../../TestHarnessAssociations.ump"
public class MentorAK
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorAK Attributes
  private String name;

  //MentorAK Associations
  private List<StudentAK> students;
  private ProgramAK program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorAK(String aName)
  {
    name = aName;
    students = new ArrayList<StudentAK>();
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
  public StudentAK getStudent(int index)
  {
    StudentAK aStudent = students.get(index);
    return aStudent;
  }

  public List<StudentAK> getStudents()
  {
    List<StudentAK> newStudents = Collections.unmodifiableList(students);
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

  public int indexOfStudent(StudentAK aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
  }
  /* Code from template association_GetOne */
  public ProgramAK getProgram()
  {
    return program;
  }

  public boolean hasProgram()
  {
    boolean has = program != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStudents()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfStudents()
  {
    return 2;
  }
  /* Code from template association_AddUnidirectionalOptionalN */
  public boolean addStudent(StudentAK aStudent)
  {
    boolean wasAdded = false;
    if (students.contains(aStudent)) { return false; }
    if (numberOfStudents() < maximumNumberOfStudents())
    {
      students.add(aStudent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removeStudent(StudentAK aStudent)
  {
    boolean wasRemoved = false;
    if (students.contains(aStudent))
    {
      students.remove(aStudent);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalOptionalN */
  public boolean setStudents(StudentAK... newStudents)
  {
    boolean wasSet = false;
    ArrayList<StudentAK> verifiedStudents = new ArrayList<StudentAK>();
    for (StudentAK aStudent : newStudents)
    {
      if (verifiedStudents.contains(aStudent))
      {
        continue;
      }
      verifiedStudents.add(aStudent);
    }

    if (verifiedStudents.size() != newStudents.length || verifiedStudents.size() > maximumNumberOfStudents())
    {
      return wasSet;
    }

    students.clear();
    students.addAll(verifiedStudents);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addStudentAt(StudentAK aStudent, int index)
  {  
    boolean wasAdded = false;
    if(addStudent(aStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudents()) { index = numberOfStudents() - 1; }
      students.remove(aStudent);
      students.add(index, aStudent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStudentAt(StudentAK aStudent, int index)
  {
    boolean wasAdded = false;
    if(students.contains(aStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudents()) { index = numberOfStudents() - 1; }
      students.remove(aStudent);
      students.add(index, aStudent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStudentAt(aStudent, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setProgram(ProgramAK aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramAK existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getMentor() != null)
      {
        existingProgram.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramAK currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setMentor(null);
    }

    program = aNewProgram;
    MentorAK existingMentor = aNewProgram.getMentor();

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