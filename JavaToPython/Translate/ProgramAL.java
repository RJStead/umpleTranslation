/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 581 "../../TestHarnessAssociations.ump"
public class ProgramAL
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ProgramAL Associations
  private MentorAL mentor;
  private StudentAL student;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ProgramAL()
  {}

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public MentorAL getMentor()
  {
    return mentor;
  }

  public boolean hasMentor()
  {
    boolean has = mentor != null;
    return has;
  }
  /* Code from template association_GetOne */
  public StudentAL getStudent()
  {
    return student;
  }

  public boolean hasStudent()
  {
    boolean has = student != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setMentor(MentorAL aNewMentor)
  {
    boolean wasSet = false;
    if (aNewMentor == null)
    {
      MentorAL existingMentor = mentor;
      mentor = null;
      
      if (existingMentor != null && existingMentor.getProgram() != null)
      {
        existingMentor.setProgram(null);
      }
      wasSet = true;
      return wasSet;
    }

    MentorAL currentMentor = getMentor();
    if (currentMentor != null && !currentMentor.equals(aNewMentor))
    {
      currentMentor.setProgram(null);
    }

    mentor = aNewMentor;
    ProgramAL existingProgram = aNewMentor.getProgram();

    if (!equals(existingProgram))
    {
      aNewMentor.setProgram(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setStudent(StudentAL aNewStudent)
  {
    boolean wasSet = false;
    if (aNewStudent == null)
    {
      StudentAL existingStudent = student;
      student = null;
      
      if (existingStudent != null && existingStudent.getProgram() != null)
      {
        existingStudent.setProgram(null);
      }
      wasSet = true;
      return wasSet;
    }

    StudentAL currentStudent = getStudent();
    if (currentStudent != null && !currentStudent.equals(aNewStudent))
    {
      currentStudent.setProgram(null);
    }

    student = aNewStudent;
    ProgramAL existingProgram = aNewStudent.getProgram();

    if (!equals(existingProgram))
    {
      aNewStudent.setProgram(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (mentor != null)
    {
      mentor.setProgram(null);
    }
    if (student != null)
    {
      student.setProgram(null);
    }
  }

}