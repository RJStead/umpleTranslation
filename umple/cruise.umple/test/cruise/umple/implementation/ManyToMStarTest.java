/*

 Copyright: All contributers to the Umple Project
 
 This file is made available subject to the open source license found at:
 http://umple.org/license

*/

package cruise.umple.implementation;

import org.junit.*;

public class ManyToMStarTest extends TemplateTest
{

  @Test
  public void Many()
  {
    assertUmpleTemplateFor("ManyToMStarTest.ump",languagePath + "/ManyToMStarTest_Many."+ languagePath +".txt","Mentor");
  }
  
  @Test
  public void MStar()
  {
    assertUmpleTemplateFor("ManyToMStarTest.ump",languagePath + "/ManyToMStarTest_MStar."+ languagePath +".txt","Student");
  }    
  
}
