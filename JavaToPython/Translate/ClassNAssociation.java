/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 620 "../../TestHarnessAssociations.ump"
public class ClassNAssociation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClassNAssociation Associations
  private List<Otherclass> otherclasses;

  //Helper Variables
  private boolean canSetOtherclasses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClassNAssociation(Otherclass... allOtherclasses)
  {
    canSetOtherclasses = true;
    otherclasses = new ArrayList<Otherclass>();
    boolean didAddOtherclasses = setOtherclasses(allOtherclasses);
    if (!didAddOtherclasses)
    {
      throw new RuntimeException("Unable to create ClassNAssociation, must have 2 otherclasses. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Otherclass getOtherclass(int index)
  {
    Otherclass aOtherclass = otherclasses.get(index);
    return aOtherclass;
  }

  public List<Otherclass> getOtherclasses()
  {
    List<Otherclass> newOtherclasses = Collections.unmodifiableList(otherclasses);
    return newOtherclasses;
  }

  public int numberOfOtherclasses()
  {
    int number = otherclasses.size();
    return number;
  }

  public boolean hasOtherclasses()
  {
    boolean has = otherclasses.size() > 0;
    return has;
  }

  public int indexOfOtherclass(Otherclass aOtherclass)
  {
    int index = otherclasses.indexOf(aOtherclass);
    return index;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfOtherclasses()
  {
    return 2;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOtherclasses()
  {
    return 2;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfOtherclasses()
  {
    return 2;
  }
  /* Code from template association_SetUnidirectionalN */
  private boolean setOtherclasses(Otherclass... newOtherclasses)
  {
    boolean wasSet = false;
    if (!canSetOtherclasses) { return false; }
    canSetOtherclasses = false;
    ArrayList<Otherclass> verifiedOtherclasses = new ArrayList<Otherclass>();
    for (Otherclass aOtherclass : newOtherclasses)
    {
      if (verifiedOtherclasses.contains(aOtherclass))
      {
        continue;
      }
      verifiedOtherclasses.add(aOtherclass);
    }

    if (verifiedOtherclasses.size() != newOtherclasses.length || verifiedOtherclasses.size() != requiredNumberOfOtherclasses())
    {
      return wasSet;
    }

    otherclasses.clear();
    otherclasses.addAll(verifiedOtherclasses);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {}

}