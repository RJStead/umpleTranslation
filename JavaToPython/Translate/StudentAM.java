/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 591 "../../TestHarnessAssociations.ump"
public class StudentAM
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StudentAM Attributes
  private int id;

  //StudentAM Associations
  private MentorAM mentor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StudentAM(int aId)
  {
    id = aId;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public MentorAM getMentor()
  {
    return mentor;
  }

  public boolean hasMentor()
  {
    boolean has = mentor != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setMentor(MentorAM aMentor)
  {
    boolean wasSet = false;
    MentorAM existingMentor = mentor;
    mentor = aMentor;
    if (existingMentor != null && !existingMentor.equals(aMentor))
    {
      existingMentor.removeStudent(this);
    }
    if (aMentor != null)
    {
      aMentor.addStudent(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (mentor != null)
    {
      MentorAM placeholderMentor = mentor;
      this.mentor = null;
      placeholderMentor.removeStudent(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "mentor = "+(getMentor()!=null?Integer.toHexString(System.identityHashCode(getMentor())):"null");
  }
}