/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 300 "../../TestHarnessAssociations.ump"
public class MentorT
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorT Attributes
  private String name;

  //MentorT Associations
  private List<StudentT> students;
  private ProgramT program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorT(String aName)
  {
    name = aName;
    students = new ArrayList<StudentT>();
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
  public StudentT getStudent(int index)
  {
    StudentT aStudent = students.get(index);
    return aStudent;
  }

  public List<StudentT> getStudents()
  {
    List<StudentT> newStudents = Collections.unmodifiableList(students);
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

  public int indexOfStudent(StudentT aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
  }
  /* Code from template association_GetOne */
  public ProgramT getProgram()
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
  /* Code from template association_AddManyToManyMethod */
  public boolean addStudent(StudentT aStudent)
  {
    boolean wasAdded = false;
    if (students.contains(aStudent)) { return false; }
    if (numberOfStudents() >= maximumNumberOfStudents())
    {
      return wasAdded;
    }

    students.add(aStudent);
    if (aStudent.indexOfMentor(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aStudent.addMentor(this);
      if (!wasAdded)
      {
        students.remove(aStudent);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMNToMany */
  public boolean removeStudent(StudentT aStudent)
  {
    boolean wasRemoved = false;
    if (!students.contains(aStudent))
    {
      return wasRemoved;
    }

    if (numberOfStudents() <= minimumNumberOfStudents())
    {
      return wasRemoved;
    }

    int oldIndex = students.indexOf(aStudent);
    students.remove(oldIndex);
    if (aStudent.indexOfMentor(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aStudent.removeMentor(this);
      if (!wasRemoved)
      {
        students.add(oldIndex,aStudent);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToMany */
  public boolean setStudents(StudentT... newStudents)
  {
    boolean wasSet = false;
    ArrayList<StudentT> verifiedStudents = new ArrayList<StudentT>();
    for (StudentT aStudent : newStudents)
    {
      if (verifiedStudents.contains(aStudent))
      {
        continue;
      }
      verifiedStudents.add(aStudent);
    }

    if (verifiedStudents.size() != newStudents.length || verifiedStudents.size() < minimumNumberOfStudents() || verifiedStudents.size() > maximumNumberOfStudents())
    {
      return wasSet;
    }

    ArrayList<StudentT> oldStudents = new ArrayList<StudentT>(students);
    students.clear();
    for (StudentT aNewStudent : verifiedStudents)
    {
      students.add(aNewStudent);
      if (oldStudents.contains(aNewStudent))
      {
        oldStudents.remove(aNewStudent);
      }
      else
      {
        aNewStudent.addMentor(this);
      }
    }

    for (StudentT anOldStudent : oldStudents)
    {
      anOldStudent.removeMentor(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setProgram(ProgramT aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramT existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getMentor() != null)
      {
        existingProgram.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramT currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setMentor(null);
    }

    program = aNewProgram;
    MentorT existingMentor = aNewProgram.getMentor();

    if (!equals(existingMentor))
    {
      aNewProgram.setMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<StudentT> copyOfStudents = new ArrayList<StudentT>(students);
    students.clear();
    for(StudentT aStudent : copyOfStudents)
    {
      if (aStudent.numberOfMentors() <= StudentT.minimumNumberOfMentors())
      {
        aStudent.delete();
      }
      else
      {
        aStudent.removeMentor(this);
      }
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