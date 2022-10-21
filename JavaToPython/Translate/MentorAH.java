/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 504 "../../TestHarnessAssociations.ump"
public class MentorAH
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorAH Attributes
  private String name;

  //MentorAH Associations
  private StudentAH student;
  private ProgramAH program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorAH(String aName, StudentAH aStudent)
  {
    name = aName;
    if (!setStudent(aStudent))
    {
      throw new RuntimeException("Unable to create MentorAH due to aStudent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  public StudentAH getStudent()
  {
    return student;
  }
  /* Code from template association_GetOne */
  public ProgramAH getProgram()
  {
    return program;
  }

  public boolean hasProgram()
  {
    boolean has = program != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setStudent(StudentAH aNewStudent)
  {
    boolean wasSet = false;
    if (aNewStudent != null)
    {
      student = aNewStudent;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setProgram(ProgramAH aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramAH existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getMentor() != null)
      {
        existingProgram.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramAH currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setMentor(null);
    }

    program = aNewProgram;
    MentorAH existingMentor = aNewProgram.getMentor();

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