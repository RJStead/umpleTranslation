/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 155 "../../TestHarnessAssociations.ump"
public class MentorK
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorK Attributes
  private String name;

  //MentorK Associations
  private List<StudentK> students;
  private ProgramK program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorK(String aName)
  {
    name = aName;
    students = new ArrayList<StudentK>();
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
  public StudentK getStudent(int index)
  {
    StudentK aStudent = students.get(index);
    return aStudent;
  }

  public List<StudentK> getStudents()
  {
    List<StudentK> newStudents = Collections.unmodifiableList(students);
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

  public int indexOfStudent(StudentK aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
  }
  /* Code from template association_GetOne */
  public ProgramK getProgram()
  {
    return program;
  }

  public boolean hasProgram()
  {
    boolean has = program != null;
    return has;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfStudentsValid()
  {
    boolean isValid = numberOfStudents() >= minimumNumberOfStudents() && numberOfStudents() <= maximumNumberOfStudents();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStudents()
  {
    return 2;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfStudents()
  {
    return 3;
  }
  /* Code from template association_AddMNToOnlyOne */
  public StudentK addStudent(int aNumber)
  {
    if (numberOfStudents() >= maximumNumberOfStudents())
    {
      return null;
    }
    else
    {
      return new StudentK(aNumber, this);
    }
  }

  public boolean addStudent(StudentK aStudent)
  {
    boolean wasAdded = false;
    if (students.contains(aStudent)) { return false; }
    if (numberOfStudents() >= maximumNumberOfStudents())
    {
      return wasAdded;
    }

    MentorK existingMentor = aStudent.getMentor();
    boolean isNewMentor = existingMentor != null && !this.equals(existingMentor);

    if (isNewMentor && existingMentor.numberOfStudents() <= minimumNumberOfStudents())
    {
      return wasAdded;
    }

    if (isNewMentor)
    {
      aStudent.setMentor(this);
    }
    else
    {
      students.add(aStudent);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStudent(StudentK aStudent)
  {
    boolean wasRemoved = false;
    //Unable to remove aStudent, as it must always have a mentor
    if (this.equals(aStudent.getMentor()))
    {
      return wasRemoved;
    }

    //mentor already at minimum (2)
    if (numberOfStudents() <= minimumNumberOfStudents())
    {
      return wasRemoved;
    }
    students.remove(aStudent);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addStudentAt(StudentK aStudent, int index)
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

  public boolean addOrMoveStudentAt(StudentK aStudent, int index)
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
  public boolean setProgram(ProgramK aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramK existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getMentor() != null)
      {
        existingProgram.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramK currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setMentor(null);
    }

    program = aNewProgram;
    MentorK existingMentor = aNewProgram.getMentor();

    if (!equals(existingMentor))
    {
      aNewProgram.setMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=students.size(); i > 0; i--)
    {
      StudentK aStudent = students.get(i - 1);
      aStudent.delete();
    }
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