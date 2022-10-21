/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 67 "../../TestHarnessAssociations.ump"
public class MentorE
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

  //MentorE Associations
  private List<StudentE> students;
  private ProgramE program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorE(StudentE... allStudents)
  {
    y = nextY++;
    students = new ArrayList<StudentE>();
    boolean didAddStudents = setStudents(allStudents);
    if (!didAddStudents)
    {
      throw new RuntimeException("Unable to create MentorE, must have 2 to 5 students. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  public StudentE getStudent(int index)
  {
    StudentE aStudent = students.get(index);
    return aStudent;
  }

  public List<StudentE> getStudents()
  {
    List<StudentE> newStudents = Collections.unmodifiableList(students);
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

  public int indexOfStudent(StudentE aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
  }
  /* Code from template association_GetOne */
  public ProgramE getProgram()
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
    return 2;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfStudents()
  {
    return 5;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addStudent(StudentE aStudent)
  {
    boolean wasAdded = false;
    if (students.contains(aStudent)) { return false; }
    if (numberOfStudents() >= maximumNumberOfStudents())
    {
      return wasAdded;
    }
    MentorE existingMentor = aStudent.getMentor();
    if (existingMentor != null && existingMentor.numberOfStudents() <= minimumNumberOfStudents())
    {
      return wasAdded;
    }
    else if (existingMentor != null)
    {
      existingMentor.students.remove(aStudent);
    }
    students.add(aStudent);
    setMentor(aStudent,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStudent(StudentE aStudent)
  {
    boolean wasRemoved = false;
    if (students.contains(aStudent) && numberOfStudents() > minimumNumberOfStudents())
    {
      students.remove(aStudent);
      setMentor(aStudent,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToOptionalOne */
  public boolean setStudents(StudentE... newStudents)
  {
    boolean wasSet = false;
    if (newStudents.length < minimumNumberOfStudents() || newStudents.length > maximumNumberOfStudents())
    {
      return wasSet;
    }

    ArrayList<StudentE> checkNewStudents = new ArrayList<StudentE>();
    HashMap<MentorE,Integer> mentorToNewStudents = new HashMap<MentorE,Integer>();
    for (StudentE aStudent : newStudents)
    {
      if (checkNewStudents.contains(aStudent))
      {
        return wasSet;
      }
      else if (aStudent.getMentor() != null && !this.equals(aStudent.getMentor()))
      {
        MentorE existingMentor = aStudent.getMentor();
        if (!mentorToNewStudents.containsKey(existingMentor))
        {
          mentorToNewStudents.put(existingMentor, Integer.valueOf(existingMentor.numberOfStudents()));
        }
        Integer currentCount = mentorToNewStudents.get(existingMentor);
        int nextCount = currentCount - 1;
        if (nextCount < 2)
        {
          return wasSet;
        }
        mentorToNewStudents.put(existingMentor, Integer.valueOf(nextCount));
      }
      checkNewStudents.add(aStudent);
    }

    students.removeAll(checkNewStudents);

    for (StudentE orphan : students)
    {
      setMentor(orphan, null);
    }
    students.clear();
    for (StudentE aStudent : newStudents)
    {
      if (aStudent.getMentor() != null)
      {
        aStudent.getMentor().students.remove(aStudent);
      }
      setMentor(aStudent, this);
      students.add(aStudent);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setMentor(StudentE aStudent, MentorE aMentor)
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
  /* Code from template association_AddIndexControlFunctions */
  public boolean addStudentAt(StudentE aStudent, int index)
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

  public boolean addOrMoveStudentAt(StudentE aStudent, int index)
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
  public boolean setProgram(ProgramE aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramE existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getMentor() != null)
      {
        existingProgram.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramE currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setMentor(null);
    }

    program = aNewProgram;
    MentorE existingMentor = aNewProgram.getMentor();

    if (!equals(existingMentor))
    {
      aNewProgram.setMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(StudentE aStudent : students)
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