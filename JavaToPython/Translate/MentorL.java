/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 171 "../../TestHarnessAssociations.ump"
public class MentorL
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorL Attributes
  private String name;

  //MentorL Associations
  private List<StudentL> students;
  private ProgramL program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorL(String aName)
  {
    name = aName;
    students = new ArrayList<StudentL>();
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
  public StudentL getStudent(int index)
  {
    StudentL aStudent = students.get(index);
    return aStudent;
  }

  public List<StudentL> getStudents()
  {
    List<StudentL> newStudents = Collections.unmodifiableList(students);
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

  public int indexOfStudent(StudentL aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
  }
  /* Code from template association_GetOne */
  public ProgramL getProgram()
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
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfStudents()
  {
    return 2;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStudents()
  {
    return 2;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfStudents()
  {
    return 2;
  }
  /* Code from template association_AddMNToOnlyOne */
  public StudentL addStudent(int aNumber)
  {
    if (numberOfStudents() >= maximumNumberOfStudents())
    {
      return null;
    }
    else
    {
      return new StudentL(aNumber, this);
    }
  }

  public boolean addStudent(StudentL aStudent)
  {
    boolean wasAdded = false;
    if (students.contains(aStudent)) { return false; }
    if (numberOfStudents() >= maximumNumberOfStudents())
    {
      return wasAdded;
    }

    MentorL existingMentor = aStudent.getMentor();
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

  public boolean removeStudent(StudentL aStudent)
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
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setProgram(ProgramL aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramL existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getMentor() != null)
      {
        existingProgram.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramL currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setMentor(null);
    }

    program = aNewProgram;
    MentorL existingMentor = aNewProgram.getMentor();

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
      StudentL aStudent = students.get(i - 1);
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