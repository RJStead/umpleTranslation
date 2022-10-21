/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 194 "../../TestHarnessAssociations.ump"
public class StudentM
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StudentM Attributes
  private int number;

  //StudentM Associations
  private ProgramM program;
  private MentorM mentor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StudentM(int aNumber, MentorM aMentor)
  {
    number = aNumber;
    boolean didAddMentor = setMentor(aMentor);
    if (!didAddMentor)
    {
      throw new RuntimeException("Unable to create student due to mentor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }
  /* Code from template association_GetOne */
  public ProgramM getProgram()
  {
    return program;
  }

  public boolean hasProgram()
  {
    boolean has = program != null;
    return has;
  }
  /* Code from template association_GetOne */
  public MentorM getMentor()
  {
    return mentor;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setProgram(ProgramM aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramM existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getStudent() != null)
      {
        existingProgram.setStudent(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramM currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setStudent(null);
    }

    program = aNewProgram;
    StudentM existingStudent = aNewProgram.getStudent();

    if (!equals(existingStudent))
    {
      aNewProgram.setStudent(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setMentor(MentorM aMentor)
  {
    boolean wasSet = false;
    //Must provide mentor to student
    if (aMentor == null)
    {
      return wasSet;
    }

    if (mentor != null && mentor.numberOfStudents() <= MentorM.minimumNumberOfStudents())
    {
      return wasSet;
    }

    MentorM existingMentor = mentor;
    mentor = aMentor;
    if (existingMentor != null && !existingMentor.equals(aMentor))
    {
      boolean didRemove = existingMentor.removeStudent(this);
      if (!didRemove)
      {
        mentor = existingMentor;
        return wasSet;
      }
    }
    mentor.addStudent(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (program != null)
    {
      program.setStudent(null);
    }
    MentorM placeholderMentor = mentor;
    this.mentor = null;
    if(placeholderMentor != null)
    {
      placeholderMentor.removeStudent(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "program = "+(getProgram()!=null?Integer.toHexString(System.identityHashCode(getProgram())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "mentor = "+(getMentor()!=null?Integer.toHexString(System.identityHashCode(getMentor())):"null");
  }
}