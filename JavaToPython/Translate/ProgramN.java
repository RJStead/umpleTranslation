/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 216 "../../TestHarnessAssociations.ump"
public class ProgramN
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ProgramN Associations
  private MentorN mentor;
  private StudentN student;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ProgramN()
  {}

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public MentorN getMentor()
  {
    return mentor;
  }

  public boolean hasMentor()
  {
    boolean has = mentor != null;
    return has;
  }
  /* Code from template association_GetOne */
  public StudentN getStudent()
  {
    return student;
  }

  public boolean hasStudent()
  {
    boolean has = student != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setMentor(MentorN aNewMentor)
  {
    boolean wasSet = false;
    if (aNewMentor == null)
    {
      MentorN existingMentor = mentor;
      mentor = null;
      
      if (existingMentor != null && existingMentor.getProgram() != null)
      {
        existingMentor.setProgram(null);
      }
      wasSet = true;
      return wasSet;
    }

    MentorN currentMentor = getMentor();
    if (currentMentor != null && !currentMentor.equals(aNewMentor))
    {
      currentMentor.setProgram(null);
    }

    mentor = aNewMentor;
    ProgramN existingProgram = aNewMentor.getProgram();

    if (!equals(existingProgram))
    {
      aNewMentor.setProgram(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setStudent(StudentN aNewStudent)
  {
    boolean wasSet = false;
    if (aNewStudent == null)
    {
      StudentN existingStudent = student;
      student = null;
      
      if (existingStudent != null && existingStudent.getProgram() != null)
      {
        existingStudent.setProgram(null);
      }
      wasSet = true;
      return wasSet;
    }

    StudentN currentStudent = getStudent();
    if (currentStudent != null && !currentStudent.equals(aNewStudent))
    {
      currentStudent.setProgram(null);
    }

    student = aNewStudent;
    ProgramN existingProgram = aNewStudent.getProgram();

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