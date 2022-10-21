/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 6 "../../TestHarnessAssociations.ump"
public class MentorA
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorA Associations
  private StudentA student;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorA()
  {}

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public StudentA getStudent()
  {
    return student;
  }

  public boolean hasStudent()
  {
    boolean has = student != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setStudent(StudentA aNewStudent)
  {
    boolean wasSet = false;
    if (aNewStudent == null)
    {
      StudentA existingStudent = student;
      student = null;
      
      if (existingStudent != null && existingStudent.getMentor() != null)
      {
        existingStudent.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    StudentA currentStudent = getStudent();
    if (currentStudent != null && !currentStudent.equals(aNewStudent))
    {
      currentStudent.setMentor(null);
    }

    student = aNewStudent;
    MentorA existingMentor = aNewStudent.getMentor();

    if (!equals(existingMentor))
    {
      aNewStudent.setMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (student != null)
    {
      student.setMentor(null);
    }
  }

}