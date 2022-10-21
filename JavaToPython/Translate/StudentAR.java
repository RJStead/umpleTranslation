/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 601 "../../TestHarnessAssociations.ump"
public class StudentAR
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StudentAR Associations
  private MentorAR mentorAR;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StudentAR()
  {}

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public MentorAR getMentorAR()
  {
    return mentorAR;
  }

  public boolean hasMentorAR()
  {
    boolean has = mentorAR != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToMandatoryMany */
  public boolean setMentorAR(MentorAR aMentorAR)
  {
    //
    // This source of this source generation is association_SetOptionalOneToMandatoryMany.jet
    // This set file assumes the generation of a maximumNumberOfXXX method does not exist because 
    // it's not required (No upper bound)
    //   
    boolean wasSet = false;
    MentorAR existingMentorAR = mentorAR;

    if (existingMentorAR == null)
    {
      if (aMentorAR != null)
      {
        if (aMentorAR.addStudentAR(this))
        {
          existingMentorAR = aMentorAR;
          wasSet = true;
        }
      }
    } 
    else if (existingMentorAR != null)
    {
      if (aMentorAR == null)
      {
        if (existingMentorAR.minimumNumberOfStudentARs() < existingMentorAR.numberOfStudentARs())
        {
          existingMentorAR.removeStudentAR(this);
          existingMentorAR = aMentorAR;  // aMentorAR == null
          wasSet = true;
        }
      } 
      else
      {
        if (existingMentorAR.minimumNumberOfStudentARs() < existingMentorAR.numberOfStudentARs())
        {
          existingMentorAR.removeStudentAR(this);
          aMentorAR.addStudentAR(this);
          existingMentorAR = aMentorAR;
          wasSet = true;
        }
      }
    }
    if (wasSet)
    {
      mentorAR = existingMentorAR;
    }
    return wasSet;
  }
  
  public void delete()
  {
    if (mentorAR != null)
    {
      if (mentorAR.numberOfStudentARs() <= 1)
      {
        mentorAR.delete();
      }
      else
      {
        MentorAR placeholderMentorAR = mentorAR;
        this.mentorAR = null;
        placeholderMentorAR.removeStudentAR(this);
      }
    }
  }

}