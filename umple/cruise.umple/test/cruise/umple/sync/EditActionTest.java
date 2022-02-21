/*
 
 Copyright: All contributers to the Umple Project
 
 This file is made available subject to the open source license found at:
 http://umple.org/license

*/

package cruise.umple.sync;

import java.io.File;

import org.junit.*;

import cruise.umple.util.SampleFileWriter;

public class EditActionTest extends ActionTest
{
  String pathToInput;
  
  @Before
  public void setUp()
  {
    pathToInput = SampleFileWriter.rationalize("test/cruise/umple/sync");
  }
  
  @After
  public void tearDown()
  {
    SampleFileWriter.destroy(pathToInput + "/myfile.ump");
  }
  
  @Test
  public void RenameClassHasSameId()
  {
    String umple = "class One { position 1 2 3 4; } class Two { position 5 6 7 8; }";
    String json = "{\"position\":{\"x\":10,\"y\":20,\"width\":30,\"height\":40},\"id\":\"One\",\"name\":\"Uno\",\"oldname\":\"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class Uno { position 10 20 30 40; } class Two { position 5 6 7 8; }";
    Assert.assertEquals(expected, action.getUmpleCode());
  } 
  
  @Test
  public void NothingToEdit()
  {
    String umple = "class One { position 1 2 3 4; } class Two { position 5 6 7 8; }";
    String json = "{\"position\":{\"x\":10,\"y\":20,\"width\":30,\"height\":40},\"id\":\"umpleClass_3\",\"name\":\"Student\",\"oldname\":\"NewClass\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    Assert.assertEquals(umple, action.getUmpleCode());
  }
  
  @Test
  public void FirstClass()
  {
    String umple = "class One { position 1 2 3 4; } class Two { position 5 6 7 8; }";
    String json = "{\"position\":{\"x\":10,\"y\":20,\"width\":30,\"height\":40},\"id\":\"One\",\"name\":\"Uno\",\"oldname\":\"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class Uno { position 10 20 30 40; } class Two { position 5 6 7 8; }";
    Assert.assertEquals(expected, action.getUmpleCode());
  } 

  @Test
  public void NoSpaces()
  {
    String umple = "class One{position 1 2 3 4;}";
    String json = "{\"position\":{\"x\":10,\"y\":20,\"width\":30,\"height\":40},\"id\":\"umpleClass_1\",\"name\":\"Uno\",\"oldname\":\"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class Uno{position 10 20 30 40;}";
    Assert.assertEquals(expected, action.getUmpleCode());
  }  
  
  @Test
  public void LastClass()
  {
    String umple = "class One { position 1 2 3 4; } class Two { position 5 6 7 8; }";
    String json = "{\"position\":{\"x\":50,\"y\":60,\"width\":70,\"height\":80},\"id\":\"Duo\",\"name\":\"Duo\",\"oldname\":\"Two\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class One { position 1 2 3 4; } class Duo { position 50 60 70 80; }";
    Assert.assertEquals(expected, action.getUmpleCode());
  } 
  
  @Test
  public void MiddleClass()
  {
    String umple = "class One { position 1 2 3 4; } class Two { position 5 6 7 8; } class Three { position umpleClass_3 3 4 5 6; }";
    String json = "{\"position\":{\"x\":50,\"y\":60,\"width\":70,\"height\":80},\"id\":\"umpleClass_2\",\"name\":\"Duo\",\"oldname\":\"Two\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class One { position 1 2 3 4; } class Duo { position 50 60 70 80; } class Three { position umpleClass_3 3 4 5 6; }";
    Assert.assertEquals(expected, action.getUmpleCode());
  }  
  
  @Test
  public void RenameNoPosition()
  {
    String umple = "class One {  } class One { position 1 2 3 4; } class Three { position umpleClass_3 3 4 5 6; }";
    String json = "{\"position\":{\"x\":50,\"y\":60,\"width\":70,\"height\":80},\"id\":\"umpleClass_1\",\"name\":\"Uno\",\"oldname\":\"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class Uno {  } class Uno { position 50 60 70 80; } class Three { position umpleClass_3 3 4 5 6; }";
    Assert.assertEquals(expected, action.getUmpleCode());
  }
  
  @Test
  public void AddWhenNoPosition()
  {
    String umple = "class One {  } class Two { }";
    String json = "{\"position\":{\"x\":50,\"y\":60,\"width\":70,\"height\":80},\"id\":\"umpleClass_0\",\"name\":\"Uno\",\"oldname\":\"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class Uno {  } class Two { }\nclass Uno\n{\n  position 50 60 70 80;\n}\n";
    Assert.assertEquals(expected, action.getUmpleCode());
  }  
  
  @Test
  public void DuplicateAcrossNames()
  {
    String umple = "class One { position 1 2 3 4;  } class One { position 1 2 3 4; }";
    String json = "{\"position\":{\"x\":10,\"y\":20,\"width\":30,\"height\":40},\"id\":\"One\",\"name\":\"Uno\",\"oldname\":\"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class Uno { position 10 20 30 40;  } class Uno { position 10 20 30 40; }";
    Assert.assertEquals(expected, action.getUmpleCode());
  }   
  
  @Test
  public void EditClass()
  {
    String umple = "class Blah { position umpleClass_0 1 2 3 4; } class NewClass { position 1 2 3 4; } class MoreBlah { position 1 2 3 4; }";
    String json = "{\"position\":{\"x\":10,\"y\":20,\"width\":30,\"height\":40},\"id\":\"umpleClass_1\",\"name\":\"Student\",\"oldname\":\"NewClass\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    Assert.assertEquals("class Blah { position umpleClass_0 1 2 3 4; } class Student { position 10 20 30 40; } class MoreBlah { position 1 2 3 4; }",action.getUmpleCode());

  }
  
  @Test
  public void EditExample2()
  {
    String umple = "class Person {  }";
    String json = "{\"position\":{\"x\":83,\"y\":198,\"width\":0,\"height\":0},\"attributes\":[\"String : name\", \"Integer : idNumber\"],\"id\":\"Person\",\"name\":\"Person\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    System.out.println(action.getUmpleCode());
    
  }

  @Test
  public void EditClassPositionsAndNoPosition()
  {
    String umple = "class Blah { position umpleClass_0 1 2 3 4; } class NewClass {  } class MoreBlah { position 1 2 3 4; }";
    String json = "{\"position\":{\"x\":10,\"y\":20,\"width\":30,\"height\":40},\"id\":\"umpleClass_2\",\"name\":\"Garble\",\"oldname\":\"MoreBlah\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();

    Assert.assertEquals("class Blah { position umpleClass_0 1 2 3 4; } class NewClass {  } class Garble { position 10 20 30 40; }",action.getUmpleCode());
  }
  
  @Test
  public void EditWithErrors()
  {
    String umple = "class Person { }";
    String json = "garble";  
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();

    if (!action.getUmpleCode().startsWith("//Failed at: "))
    {
      Assert.assertEquals("Should have failed",action.getUmpleCode());
    }
  }
  
  @Test
  public void EditAirlineExample()
  {
    String umple = SampleFileWriter.readContent(new File(pathToInput,"Example1.ump"));  
    String json = "{\"position\":{\"x\":10,\"y\":20,\"width\":30,\"height\":40},\"id\":\"Airline\",\"name\":\"Airline\",\"oldname\":\"Airline\"}";
    String filename = pathToInput + "/Example1.ump";
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();

    SampleFileWriter.assertFileContent(new File(pathToInput,"Example1_Edit.ump"), action.getUmpleCode());
  }

  @Test
  public void AttributesNewDefaultType()
  {
    String umple = "class One { position 105 81 81 41;  }";
    String json = "{\"position\" : {\"x\" : \"105\",\"y\" : \"81\",\"width\" : \"81\",\"height\" : \"41\"},\"attributes\" : [{\"type\" : \"String\",\"name\" : \"username\",\"newType\" : \"String\",\"newName\" : \"username\"}],\"id\" : \"One\",\"name\" : \"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();

    String expected = "class One { username;\nposition 105 81 81 41;  }";
    Assert.assertEquals(action.getUmpleCode(),expected);
  }  

  @Test
  public void AttributesNew_CheckSpacingFirstAttribute()
  {
    String umple = "class One\n{\n  position 105 81 81 41;\n}";
    String json = "{\"position\" : {\"x\" : \"105\",\"y\" : \"81\",\"width\" : \"81\",\"height\" : \"41\"},\"attributes\" : [{\"type\" : \"Integer\",\"name\" : \"username\",\"newType\" : \"Integer\",\"newName\" : \"username\"}],\"id\" : \"One\",\"name\" : \"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class One\n{\n  Integer username;\nposition 105 81 81 41;\n}";
    Assert.assertEquals(expected, action.getUmpleCode());
  } 
  
  @Test
  public void AttributesNew_CheckSpacingSecondAttribute()
  {
    String umple = "class One\n{\n  name;\n  position 105 81 81 41;\n}";
    String json = "{\"position\" : {\"x\" : \"105\",\"y\" : \"81\",\"width\" : \"81\",\"height\" : \"41\"},\"attributes\" : [{\"type\" : \"Integer\",\"name\" : \"username\",\"newType\" : \"Integer\",\"newName\" : \"username\"}],\"id\" : \"One\",\"name\" : \"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class One\n{\n  name;\n  Integer username;\nposition 105 81 81 41;\n}";
    Assert.assertEquals(expected, action.getUmpleCode());
  }   
  
  @Test
  public void AttributesNew()
  {
    String umple = "class One { position 105 81 81 41;  }";
    String json = "{\"position\" : {\"x\" : \"105\",\"y\" : \"81\",\"width\" : \"81\",\"height\" : \"41\"},\"attributes\" : [{\"type\" : \"Integer\",\"name\" : \"username\",\"newType\" : \"Integer\",\"newName\" : \"username\"}],\"id\" : \"One\",\"name\" : \"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class One { Integer username;\nposition 105 81 81 41;  }";
    Assert.assertEquals(expected, action.getUmpleCode());
  }  

  @Test
  public void AttributesReplaceDefaultToNonDefault()
  {
    String umple = "class One { username; position 105 81 81 41;  }";
    String json = "{\"position\" : {\"x\" : \"105\",\"y\" : \"81\",\"width\" : \"81\",\"height\" : \"41\"},\"attributes\" : [{\"type\" : \"Integer\",\"name\" : \"id\",\"oldType\" : \"String\",\"oldName\" : \"username\"}],\"id\" : \"One\",\"name\" : \"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class One { Integer id; position 105 81 81 41;  }";
    Assert.assertEquals(expected, action.getUmpleCode());
  }
  
  @Test
  public void AttributesUnchanged()
  {
    String umple = "class One { username; position 105 81 81 41;  }";
    String json = "{\"position\" : {\"x\" : \"105\",\"y\" : \"81\",\"width\" : \"81\",\"height\" : \"41\"},\"attributes\" : [{\"type\" : \"Integer\",\"name\" : \"id\"}],\"id\" : \"One\",\"name\" : \"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class One { username; position 105 81 81 41;  }";
    Assert.assertEquals(expected, action.getUmpleCode());
  }  

  @Test
  public void AttributesReplaceDefaultToDefault()
  {
    String umple = "class One { username; position 105 81 81 41;  }";
    String json = "{\"position\" : {\"x\" : \"105\",\"y\" : \"81\",\"width\" : \"81\",\"height\" : \"41\"},\"attributes\" : [{\"type\" : \"String\",\"name\" : \"id\",\"oldType\" : \"String\",\"oldName\" : \"username\"}],\"id\" : \"One\",\"name\" : \"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class One { id; position 105 81 81 41;  }";
    Assert.assertEquals(expected, action.getUmpleCode());
  }
  
  
  @Test
  public void AttributesRemove()
  {
    String umple = "class One { username; position 105 81 81 41;  }";
    String json = "{\"position\" : {\"x\" : \"105\",\"y\" : \"81\",\"width\" : \"81\",\"height\" : \"41\"},\"attributes\" : [{\"type\" : \"String\",\"name\" : \"username\",\"deleteType\" : \"String\",\"deleteName\" : \"username\"}],\"id\" : \"One\",\"name\" : \"One\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class One { position 105 81 81 41;  }";
    Assert.assertEquals(expected, action.getUmpleCode());
  }   
  
  @Test
  public void AttributeRemoveMiddle()
  {
    assertEditAction("EditTest_DeleteAttribute");    
  }

  @Test
  public void AttributeRemoveLast()
  {
    assertEditAction("EditTest_DeleteAttribute2");    
  }

  @Test
  public void UpdateAssociations()
  {
    String umple = "class One { * -- * Two; 0..1 -- 0..3 One; position umpleClass_0 0 0 0 0; } class Two { position 0 0 0 0; }";
    String json = "{\"position\" : {\"x\" : \"0\",\"y\" : \"0\",\"width\" : \"0\",\"height\" : \"0\"},\"attributes\" : [],\"id\" : \"umpleClass_1\",\"name\" : \"Three\", \"oldname\" : \"Two\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class One { * -- * Three; 0..1 -- 0..3 One; position umpleClass_0 0 0 0 0; } class Three { position 0 0 0 0; }";
    Assert.assertEquals(expected, action.getUmpleCode());
  }
  
  @Test
  public void AddAttributeWhenModelPositionSeparate_empty()
  {
    assertEditAction("EditTest_Go");
  }
  
  @Test
  public void AddAttributeWhenModelPositionSeparate_nonEmpty()
  {
    assertEditAction("EditTest2_Go");
  }
  
  @Test
  public void RenameClassThatIsParentInGeneralization()
  {
    assertEditAction("EditTest3_Go");
  }
  
  @Test
  public void EditClassWithoutPositionAssociation()
  {
    String umple = "class NewClass { * -- * NewClass1; position 68 52 109 42; position.association NewClass_NewClass1 1,2 3,4; } class NewClass1 { position 176 199 109 42; }";
    String json = "{\"position\" : {\"x\" : \"176\",\"y\" : \"199\",\"width\" : \"109\",\"height\" : \"42\"},\"attributes\" : [],\"id\" : \"umpleClass_1\",\"name\" : \"Mentor\", \"oldname\" : \"NewClass1\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class NewClass { * -- * Mentor; position 68 52 109 42; position.association NewClass_Mentor 1,2 3,4; } class Mentor { position 176 199 109 42; }";
    Assert.assertEquals(expected, action.getUmpleCode());
  }
  
  @Test
  public void EditClassWithPositionAssociation()
  {
    String umple = "class NewClass { * -- * NewClass1; position 68 52 109 42; position.association NewClass_NewClass1 1,2 3,4; } class NewClass1 { position 176 199 109 42; }";
    String json = "{\"position\" : {\"x\" : \"68\",\"y\" : \"52\",\"width\" : \"109\",\"height\" : \"42\"},\"attributes\" : [],\"id\" : \"umpleClass_0\",\"name\" : \"Student\", \"oldname\" : \"NewClass\"}";
    String filename = pathToInput + "/myfile.ump";
    SampleFileWriter.createFile(filename, umple);
    
    EditAction action = new EditAction(json,umple,filename);
    action.go();
    
    String expected = "class Student { * -- * NewClass1; position 68 52 109 42; position.association Student_NewClass1 1,2 3,4; } class NewClass1 { position 176 199 109 42; }";
    Assert.assertEquals(expected, action.getUmpleCode());
  }
  
}
