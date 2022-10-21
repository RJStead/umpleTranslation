/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 51 "../../TestHarnessAssociations.ump"
public class MentorD
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextY = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes
  private int y;

  //MentorD Associations
  private List<StudentD> students;
  private ProgramD program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorD(StudentD... allStudents)
  {
    y = nextY++;
    students = new ArrayList<StudentD>();
    boolean didAddStudents = setStudents(allStudents);
    if (!didAddStudents)
    {
      throw new RuntimeException("Unable to create MentorD, must have 3 students. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getY()
  {
    return y;
  }
  /* Code from template association_GetMany */
  public StudentD getStudent(int index)
  {
    StudentD aStudent = students.get(index);
    return aStudent;
  }

  public List<StudentD> getStudents()
  {
    List<StudentD> newStudents = Collections.unmodifiableList(students);
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

  public int indexOfStudent(StudentD aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
  }
  /* Code from template association_GetOne */
  public ProgramD getProgram()
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
  /* Code from template association_SetNToOptionalOne */
  public boolean setStudents(StudentD... newStudents)
  {
    boolean wasSet = false;
    ArrayList<StudentD> checkNewStudents = new ArrayList<StudentD>();
    for (StudentD aStudent : newStudents)
    {
      if (checkNewStudents.contains(aStudent))
      {
        return wasSet;
      }
      else if (aStudent.getMentor() != null && !this.equals(aStudent.getMentor()))
      {
        return wasSet;
      }
      checkNewStudents.add(aStudent);
    }

    if (checkNewStudents.size() != minimumNumberOfStudents())
    {
      return wasSet;
    }

    students.removeAll(checkNewStudents);
    
    for (StudentD orphan : students)
    {
      setMentor(orphan, null);
    }
    students.clear();
    for (StudentD aStudent : newStudents)
    {
      setMentor(aStudent, this);
      students.add(aStudent);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setMentor(StudentD aStudent, MentorD aMentor)
  {
    try
    {
      java.lang.reflect.Field mentorField = aStudent.getClass().getDeclaredField("mentor");
      mentorField.setAccessible(true);
      mentorField.set(aStudent, aMentor);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aMentor to aStudent", e);
    }
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setProgram(ProgramD aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramD existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getMentor() != null)
      {
        existingProgram.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramD currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setMentor(null);
    }

    program = aNewProgram;
    MentorD existingMentor = aNewProgram.getMentor();

    if (!equals(existingMentor))
    {
      aNewProgram.setMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(StudentD aStudent : students)
    {
      setMentor(aStudent,null);
    }
    students.clear();
    if (program != null)
    {
      program.setMentor(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "y" + ":" + getY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "program = "+(getProgram()!=null?Integer.toHexString(System.identityHashCode(getProgram())):"null");
  }
}