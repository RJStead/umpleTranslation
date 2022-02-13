/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 6 "Attributes1.ump"
public class House
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //House Attributes
  private String address;
  private float price;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public House(String aAddress, float aPrice)
  {
    address = aAddress;
    price = aPrice;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(float aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public String getAddress()
  {
    return address;
  }

  public float getPrice()
  {
    return price;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "address" + ":" + getAddress()+ "," +
            "price" + ":" + getPrice()+ "]";
  }
}