/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 100 "../../TestHarnessAssociations.ump"
public class MentorG
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorG Attributes
  private String name;

  //MentorG Associations
  private StudentG student;
  private ProgramG program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorG(String aName, StudentG aStudent)
  {
    name = aName;
    if (aStudent == null || aStudent.getMentor() != null)
    {
      throw new RuntimeException("Unable to create MentorG due to aStudent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    student = aStudent;
  }

  public MentorG(String aName, int aNumberForStudent)
  {
    name = aName;
    student = new StudentG(aNumberForStudent, this);
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
  /* Code from template association_GetOne */
  public StudentG getStudent()
  {
    return student;
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
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setProgram(ProgramG aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramG existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getMentor() != null)
      {
        existingProgram.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramG currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setMentor(null);
    }

    program = aNewProgram;
    MentorG existingMentor = aNewProgram.getMentor();

    if (!equals(existingMentor))
    {
      aNewProgram.setMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    StudentG existingStudent = student;
    student = null;
    if (existingStudent != null)
    {
      existingStudent.delete();
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
            "  " + "student = "+(getStudent()!=null?Integer.toHexString(System.identityHashCode(getStudent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "program = "+(getProgram()!=null?Integer.toHexString(System.identityHashCode(getProgram())):"null");
  }
}