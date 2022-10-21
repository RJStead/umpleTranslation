/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;
import java.io.Serializable;

// line 656 "../../TestHarnessAssociations.ump"
public class ClassThatWillHaveSortedAssociationTwo implements java.io.Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClassThatWillHaveSortedAssociationTwo Attributes
  private transient Comparator<ClassThatWillHaveSortedAssociationOne> massPriority;

  //ClassThatWillHaveSortedAssociationTwo Associations
  private List<ClassThatWillHaveSortedAssociationOne> mass;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClassThatWillHaveSortedAssociationTwo(Comparator<ClassThatWillHaveSortedAssociationOne> aMassPriority)
  {
    massPriority = aMassPriority;
    mass = new ArrayList<ClassThatWillHaveSortedAssociationOne>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMassPriority(Comparator<ClassThatWillHaveSortedAssociationOne> aMassPriority)
  {
    boolean wasSet = false;
    massPriority = aMassPriority;
    wasSet = true;
    return wasSet;
  }

  public Comparator<ClassThatWillHaveSortedAssociationOne> getMassPriority()
  {
    return massPriority;
  }
  /* Code from template association_GetMany */
  public ClassThatWillHaveSortedAssociationOne getMass(int index)
  {
    ClassThatWillHaveSortedAssociationOne aMass = mass.get(index);
    return aMass;
  }

  public List<ClassThatWillHaveSortedAssociationOne> getMass()
  {
    List<ClassThatWillHaveSortedAssociationOne> newMass = Collections.unmodifiableList(mass);
    return newMass;
  }

  public int numberOfMass()
  {
    int number = mass.size();
    return number;
  }

  public boolean hasMass()
  {
    boolean has = mass.size() > 0;
    return has;
  }

  public int indexOfMass(ClassThatWillHaveSortedAssociationOne aMass)
  {
    int index = mass.indexOf(aMass);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMass()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addMass(ClassThatWillHaveSortedAssociationOne aMass)
  {
    boolean wasAdded = false;
    if (mass.contains(aMass)) { return false; }
    mass.add(aMass);
    wasAdded = true;
    
    return wasAdded;
  }

  public boolean removeMass(ClassThatWillHaveSortedAssociationOne aMass)
  {
    boolean wasRemoved = false;
    if (mass.contains(aMass))
    {
      mass.remove(aMass);
      wasRemoved = true;
    }
    return wasRemoved;
  }


  /* Code from template association_sorted_serializable_readObject */ 
  private void readObject(java.io.ObjectInputStream in)
  throws Exception
  {
    in.defaultReadObject();

    massPriority = null;
  }
  
  public void delete()
  {
    mass.clear();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "massPriority" + "=" + (getMassPriority() != null ? !getMassPriority().equals(this)  ? getMassPriority().toString().replaceAll("  ","    ") : "this" : "null");
  }
}