/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 492 "../../TestHarnessAssociations.ump"
public class MentorAF
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorAF Attributes
  private String name;

  //MentorAF Associations
  private MentorAF friend;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorAF(String aName, MentorAF aFriend)
  {
    name = aName;
    if (aFriend == null || aFriend.getFriend() != null)
    {
      throw new RuntimeException("Unable to create MentorAF due to aFriend. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    friend = aFriend;
  }

  public MentorAF(String aName, String aNameForFriend)
  {
    name = aName;
    friend = new MentorAF(aNameForFriend, this);
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
  public MentorAF getFriend()
  {
    return friend;
  }

  public void delete()
  {
    MentorAF existingFriend = friend;
    friend = null;
    if (existingFriend != null)
    {
      existingFriend.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "friend = "+(getFriend()!=null?Integer.toHexString(System.identityHashCode(getFriend())):"null");
  }
}