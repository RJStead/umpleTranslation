/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 11 "../../TestHarnessAssociations.ump"
public class StudentA
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StudentA Associations
  private MentorA mentor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StudentA()
  {}

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public MentorA getMentor()
  {
    return mentor;
  }

  public boolean hasMentor()
  {
    boolean has = mentor != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setMentor(MentorA aNewMentor)
  {
    boolean wasSet = false;
    if (aNewMentor == null)
    {
      MentorA existingMentor = mentor;
      mentor = null;
      
      if (existingMentor != null && existingMentor.getStudent() != null)
      {
        existingMentor.setStudent(null);
      }
      wasSet = true;
      return wasSet;
    }

    MentorA currentMentor = getMentor();
    if (currentMentor != null && !currentMentor.equals(aNewMentor))
    {
      currentMentor.setStudent(null);
    }

    mentor = aNewMentor;
    StudentA existingStudent = aNewMentor.getStudent();

    if (!equals(existingStudent))
    {
      aNewMentor.setStudent(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (mentor != null)
    {
      mentor.setStudent(null);
    }
  }

}