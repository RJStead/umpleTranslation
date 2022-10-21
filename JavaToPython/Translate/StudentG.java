/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 107 "../../TestHarnessAssociations.ump"
public class StudentG
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StudentG Attributes
  private int number;

  //StudentG Associations
  private ProgramG program;
  private MentorG mentor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StudentG(int aNumber, MentorG aMentor)
  {
    number = aNumber;
    if (aMentor == null || aMentor.getStudent() != null)
    {
      throw new RuntimeException("Unable to create StudentG due to aMentor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    mentor = aMentor;
  }

  public StudentG(int aNumber, String aNameForMentor)
  {
    number = aNumber;
    mentor = new MentorG(aNameForMentor, this);
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
  public ProgramG getProgram()
  {
    return program;
  }

  public boolean hasProgram()
  {
    boolean has = program != null;
    return has;
  }
  /* Code from template association_GetOne */
  public MentorG getMentor()
  {
    return mentor;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setProgram(ProgramG aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramG existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getStudent() != null)
      {
        existingProgram.setStudent(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramG currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setStudent(null);
    }

    program = aNewProgram;
    StudentG existingStudent = aNewProgram.getStudent();

    if (!equals(existingStudent))
    {
      aNewProgram.setStudent(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (program != null)
    {
      program.setStudent(null);
    }
    MentorG existingMentor = mentor;
    mentor = null;
    if (existingMentor != null)
    {
      existingMentor.delete();
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