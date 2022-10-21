/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 520 "../../TestHarnessAssociations.ump"
public class MentorAI
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorAI Attributes
  private String name;

  //MentorAI Associations
  private StudentAI student;
  private ProgramAI program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorAI(String aName)
  {
    name = aName;
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
  public StudentAI getStudent()
  {
    return student;
  }

  public boolean hasStudent()
  {
    boolean has = student != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ProgramAI getProgram()
  {
    return program;
  }

  public boolean hasProgram()
  {
    boolean has = program != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setStudent(StudentAI aNewStudent)
  {
    boolean wasSet = false;
    student = aNewStudent;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setProgram(ProgramAI aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramAI existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getMentor() != null)
      {
        existingProgram.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramAI currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setMentor(null);
    }

    program = aNewProgram;
    MentorAI existingMentor = aNewProgram.getMentor();

    if (!equals(existingMentor))
    {
      aNewProgram.setMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    student = null;
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