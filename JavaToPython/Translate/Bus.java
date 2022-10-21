/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;
import java.util.*;

// line 687 "../../TestHarnessAssociations.ump"
public class Bus
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bus Attributes
  private int number;

  //Bus Associations
  private List<Seating> seatings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bus(int aNumber)
  {
    number = aNumber;
    seatings = new ArrayList<Seating>();
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
  /* Code from template association_GetMany */
  public Seating getSeating(int index)
  {
    Seating aSeating = seatings.get(index);
    return aSeating;
  }

  public List<Seating> getSeatings()
  {
    List<Seating> newSeatings = Collections.unmodifiableList(seatings);
    return newSeatings;
  }

  public int numberOfSeatings()
  {
    int number = seatings.size();
    return number;
  }

  public boolean hasSeatings()
  {
    boolean has = seatings.size() > 0;
    return has;
  }

  public int indexOfSeating(Seating aSeating)
  {
    int index = seatings.indexOf(aSeating);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSeatings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Seating addSeating(Commuter aCommuter)
  {
    return new Seating(this, aCommuter);
  }

  public boolean addSeating(Seating aSeating)
  {
    boolean wasAdded = false;
    if (seatings.contains(aSeating)) { return false; }
    Bus existingBus = aSeating.getBus();
    boolean isNewBus = existingBus != null && !this.equals(existingBus);
    if (isNewBus)
    {
      aSeating.setBus(this);
    }
    else
    {
      seatings.add(aSeating);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSeating(Seating aSeating)
  {
    boolean wasRemoved = false;
    //Unable to remove aSeating, as it must always have a bus
    if (!this.equals(aSeating.getBus()))
    {
      seatings.remove(aSeating);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSeatingAt(Seating aSeating, int index)
  {  
    boolean wasAdded = false;
    if(addSeating(aSeating))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeatings()) { index = numberOfSeatings() - 1; }
      seatings.remove(aSeating);
      seatings.add(index, aSeating);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSeatingAt(Seating aSeating, int index)
  {
    boolean wasAdded = false;
    if(seatings.contains(aSeating))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeatings()) { index = numberOfSeatings() - 1; }
      seatings.remove(aSeating);
      seatings.add(index, aSeating);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSeatingAt(aSeating, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=seatings.size(); i > 0; i--)
    {
      Seating aSeating = seatings.get(i - 1);
      aSeating.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]";
  }
}