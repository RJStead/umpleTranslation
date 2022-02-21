/*

 Copyright: All contributers to the Umple Project
 
 This file is made available subject to the open source license found at:
 http://umple.org/license     

*/

package cruise.umple.tracer.implementation.java.log4j;


import org.junit.*;

import cruise.umple.implementation.TemplateTest;
import cruise.umple.util.SampleFileWriter;


public class JavaLog4jTracerTest extends TemplateTest
{
  @Before
  public void setUp()
  {
    super.setUp();
    pathToInput = SampleFileWriter.rationalize("test/cruise/umple/tracer/implementation/java/log4j");
  }
  
  @After
  public void tearDown()
  {
    super.tearDown();
    SampleFileWriter.destroy(pathToInput + "tracer/java/example");
    SampleFileWriter.destroy(pathToInput + "/cruise");
    SampleFileWriter.destroy(pathToInput + "/Tracer.java");
    SampleFileWriter.destroy(pathToInput + "/log4j2.xml");
  }
  
  @Test
  public void Test1()
  {
    assertLog("Log4jTest1.ump","Log4jTest1.java.txt");
  }
  
  @Test
  public void Test2()
  {
    assertLog("Log4jTest2.ump","Log4jTest2.java.txt");
  }
  
  @Test
  public void Test3()
  {
    assertLog("Log4jTest3.ump","Log4jTest3.java.txt");
  }
  
  @Test
  public void Test4()
  {
    assertLog("Log4jTest4.ump","Log4jTest4.java.txt");
  }
  
  @Test
  public void Test5()
  {
    assertLog("Log4jTest5.ump","Log4jTest5.java.txt");
  }
  
  @Test
  public void TestModel()
  {
    assertUmpleTemplateFor("TestModel.ump","TestModelA.java.txt","A",false);
    assertUmpleTemplateFor("TestModel.ump","TestModelB.java.txt","B",false);
  }
  
  public void assertLog(String input, String expected)
  {
    assertUmpleTemplateFor(input,expected,"Tracer",false);
  }
  
}
