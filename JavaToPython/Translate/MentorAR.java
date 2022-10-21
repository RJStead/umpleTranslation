/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 596 "../../TestHarnessAssociations.ump"
public class MentorAR
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorAR Associations
  private List<StudentAR> studentARs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorAR(StudentAR... allStudentARs)
  {
    studentARs = new ArrayList<StudentAR>();
    boolean didAddStudentARs = setStudentARs(allStudentARs);
    if (!didAddStudentARs)
    {
      throw new RuntimeException("Unable to create MentorAR, must have at least 1 studentARs. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public StudentAR getStudentAR(int index)
  {
    StudentAR aStudentAR = studentARs.get(index);
    return aStudentAR;
  }

  public List<StudentAR> getStudentARs()
  {
    List<StudentAR> newStudentARs = Collections.unmodifiableList(studentARs);
    return newStudentARs;
  }

  public int numberOfStudentARs()
  {
    int number = studentARs.size();
    return number;
  }

  public boolean hasStudentARs()
  {
    boolean has = studentARs.size() > 0;
    return has;
  }

  public int indexOfStudentAR(StudentAR aStudentAR)
  {
    int index = studentARs.indexOf(aStudentAR);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStudentARs()
  {
    return 1;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addStudentAR(StudentAR aStudentAR)
  {
    boolean wasAdded = false;
    if (studentARs.contains(aStudentAR)) { return false; }
    MentorAR existingMentorAR = aStudentAR.getMentorAR();
    if (existingMentorAR != null && existingMentorAR.numberOfStudentARs() <= minimumNumberOfStudentARs())
    {
      return wasAdded;
    }
    else if (existingMentorAR != null)
    {
      existingMentorAR.studentARs.remove(aStudentAR);
    }
    studentARs.add(aStudentAR);
    setMentorAR(aStudentAR,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStudentAR(StudentAR aStudentAR)
  {
    boolean wasRemoved = false;
    if (studentARs.contains(aStudentAR) && numberOfStudentARs() > minimumNumberOfStudentARs())
    {
      studentARs.remove(aStudentAR);
      setMentorAR(aStudentAR,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToOptionalOne */
  public boolean setStudentARs(StudentAR... newStudentARs)
  {
    boolean wasSet = false;
    if (newStudentARs.length < minimumNumberOfStudentARs())
    {
      return wasSet;
    }

    ArrayList<StudentAR> checkNewStudentARs = new ArrayList<StudentAR>();
    HashMap<MentorAR,Integer> mentorARToNewStudentARs = new HashMap<MentorAR,Integer>();
    for (StudentAR aStudentAR : newStudentARs)
    {
      if (checkNewStudentARs.contains(aStudentAR))
      {
        return wasSet;
      }
      else if (aStudentAR.getMentorAR() != null && !this.equals(aStudentAR.getMentorAR()))
      {
        MentorAR existingMentorAR = aStudentAR.getMentorAR();
        if (!mentorARToNewStudentARs.containsKey(existingMentorAR))
        {
          mentorARToNewStudentARs.put(existingMentorAR, Integer.valueOf(existingMentorAR.numberOfStudentARs()));
        }
        Integer currentCount = mentorARToNewStudentARs.get(existingMentorAR);
        int nextCount = currentCount - 1;
        if (nextCount < 1)
        {
          return wasSet;
        }
        mentorARToNewStudentARs.put(existingMentorAR, Integer.valueOf(nextCount));
      }
      checkNewStudentARs.add(aStudentAR);
    }

    studentARs.removeAll(checkNewStudentARs);

    for (StudentAR orphan : studentARs)
    {
      setMentorAR(orphan, null);
    }
    studentARs.clear();
    for (StudentAR aStudentAR : newStudentARs)
    {
      if (aStudentAR.getMentorAR() != null)
      {
        aStudentAR.getMentorAR().studentARs.remove(aStudentAR);
      }
      setMentorAR(aStudentAR, this);
      studentARs.add(aStudentAR);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setMentorAR(StudentAR aStudentAR, MentorAR aMentorAR)
  {
    try
    {
      java.lang.reflect.Field mentorField = aStudentAR.getClass().getDeclaredField("mentorAR");
      mentorField.setAccessible(true);
      mentorField.set(aStudentAR, aMentorAR);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aMentorAR to aStudentAR", e);
    }
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addStudentARAt(StudentAR aStudentAR, int index)
  {  
    boolean wasAdded = false;
    if(addStudentAR(aStudentAR))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudentARs()) { index = numberOfStudentARs() - 1; }
      studentARs.remove(aStudentAR);
      studentARs.add(index, aStudentAR);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStudentARAt(StudentAR aStudentAR, int index)
  {
    boolean wasAdded = false;
    if(studentARs.contains(aStudentAR))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudentARs()) { index = numberOfStudentARs() - 1; }
      studentARs.remove(aStudentAR);
      studentARs.add(index, aStudentAR);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStudentARAt(aStudentAR, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(StudentAR aStudentAR : studentARs)
    {
      setMentorAR(aStudentAR,null);
    }
    studentARs.clear();
  }

}