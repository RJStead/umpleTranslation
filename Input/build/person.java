/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 1 "Attributes1.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;
  private int age;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName, int aAge)
  {
    name = aName;
    age = aAge;
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

  public boolean setAge(int aAge)
  {
    boolean wasSet = false;
    age = aAge;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getAge()
  {
    return age;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "age" + ":" + getAge()+ "]";
  }
}