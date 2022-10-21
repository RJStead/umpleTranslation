/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package cruise.associations;

// line 694 "../../TestHarnessAssociations.ump"
public class Seating
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Seating Attributes
  private String seat;

  //Seating Associations
  private Bus bus;
  private Commuter commuter;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Seating(Bus aBus, Commuter aCommuter)
  {
    seat = null;
    boolean didAddBus = setBus(aBus);
    if (!didAddBus)
    {
      throw new RuntimeException("Unable to create seating due to bus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCommuter = setCommuter(aCommuter);
    if (!didAddCommuter)
    {
      throw new RuntimeException("Unable to create seating due to commuter. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSeat(String aSeat)
  {
    boolean wasSet = false;
    seat = aSeat;
    wasSet = true;
    return wasSet;
  }

  public String getSeat()
  {
    return seat;
  }
  /* Code from template association_GetOne */
  public Bus getBus()
  {
    return bus;
  }
  /* Code from template association_GetOne */
  public Commuter getCommuter()
  {
    return commuter;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBus(Bus aBus)
  {
    boolean wasSet = false;
    if (aBus == null)
    {
      return wasSet;
    }

    Bus existingBus = bus;
    bus = aBus;
    if (existingBus != null && !existingBus.equals(aBus))
    {
      existingBus.removeSeating(this);
    }
    bus.addSeating(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCommuter(Commuter aCommuter)
  {
    boolean wasSet = false;
    if (aCommuter == null)
    {
      return wasSet;
    }

    Commuter existingCommuter = commuter;
    commuter = aCommuter;
    if (existingCommuter != null && !existingCommuter.equals(aCommuter))
    {
      existingCommuter.removeSeating(this);
    }
    commuter.addSeating(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Bus placeholderBus = bus;
    this.bus = null;
    if(placeholderBus != null)
    {
      placeholderBus.removeSeating(this);
    }
    Commuter placeholderCommuter = commuter;
    this.commuter = null;
    if(placeholderCommuter != null)
    {
      placeholderCommuter.removeSeating(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "seat" + ":" + getSeat()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bus = "+(getBus()!=null?Integer.toHexString(System.identityHashCode(getBus())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "commuter = "+(getCommuter()!=null?Integer.toHexString(System.identityHashCode(getCommuter())):"null");
  }
}