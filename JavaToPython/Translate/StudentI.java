/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 130 "../../TestHarnessAssociations.ump"
public class StudentI
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StudentI Attributes
  private int number;

  //StudentI Associations
  private ProgramI program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StudentI(int aNumber)
  {
    number = aNumber;
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
  public ProgramI getProgram()
  {
    return program;
  }

  public boolean hasProgram()
  {
    boolean has = program != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setProgram(ProgramI aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramI existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getStudentI() != null)
      {
        existingProgram.setStudentI(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramI currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setStudentI(null);
    }

    program = aNewProgram;
    StudentI existingStudentI = aNewProgram.getStudentI();

    if (!equals(existingStudentI))
    {
      aNewProgram.setStudentI(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (program != null)
    {
      program.setStudentI(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "program = "+(getProgram()!=null?Integer.toHexString(System.identityHashCode(getProgram())):"null");
  }
}