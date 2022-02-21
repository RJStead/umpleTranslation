/*

 Copyright: All contributers to the Umple Project
 
 This file is made available subject to the open source license found at:
 http://umple.org/license

*/

package cruise.umple.compiler;

import org.junit.*;

import cruise.umple.util.SampleFileWriter;

public class AssociationVariableTest
{

  @After
  public void tearDown()
  {
    SampleFileWriter.destroy("umpleAssociationVariableTest.ump");
  }

  @Test
  public void isSymmetricReflexive()
  {
    AssociationVariable var = new AssociationVariable("aName","MyType","symmetricreflexive","",createMultiplicity(0,1),true);
    AssociationVariable var2 = new AssociationVariable("anotherName","MyType","not","",createMultiplicity(0,1),true);
    AssociationVariable var3 = new AssociationVariable("thirdName","NotMyType","symmetricreflexive","",createMultiplicity(0,1),true);

    Assert.assertEquals(false, var.isSymmetricReflexive());
    
    var.setRelatedAssociation(var2);
    Assert.assertEquals(false, var2.isSymmetricReflexive());
    
    var.setRelatedAssociation(var3);
    Assert.assertEquals(true, var.isSymmetricReflexive());
  }
    
  @Test
  public void MinAndMaxBasedOnMinusOneAsMany()
  {
    Assert.assertEquals(3,AssociationVariable.min(3,4));
    Assert.assertEquals(4,AssociationVariable.min(-1,4));
    Assert.assertEquals(-1,AssociationVariable.min(-1,-1));

    Assert.assertEquals(4,AssociationVariable.max(3,4));
    Assert.assertEquals(-1,AssociationVariable.max(-1,4));
    Assert.assertEquals(-1,AssociationVariable.max(-1,-1));
  
  }
  
  @Test
  public void isMany()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(true,var.isMany());
    
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(true,var.isMany());

    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(false,var.isMany());
  }

  @Test
  public void isOptionalN()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isOptionalN());

    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(true,var.isOptionalN());

    var.getMultiplicity().setMaximum("3");
    Assert.assertEquals(true,var.isOptionalN());

    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(false,var.isOptionalN());
  }

  @Test
  public void isOne()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isOne());
    
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isOne());

    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(true,var.isOne());
  }

  @Test
  public void isOnlyOne()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isOnlyOne());
    
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isOnlyOne());

    var.getMultiplicity().setMinimum("1");
    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(true,var.isOnlyOne());

    var.getMultiplicity().setMinimum("0");
    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(false,var.isOnlyOne());
  }

  @Test
  public void isOptionalOne()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isOptionalOne());
    
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isOptionalOne());

    var.getMultiplicity().setMinimum("1");
    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(false,var.isOptionalOne());

    var.getMultiplicity().setMinimum("0");
    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(true,var.isOptionalOne());
  }
  
  @Test
  public void isN()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isN());
    
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isN());

    var.getMultiplicity().setMinimum("1");
    Assert.assertEquals(false,var.isN());

    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(false,var.isN());

    var.getMultiplicity().setMinimum("2");
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(true,var.isN());
    
    var.getMultiplicity().setMinimum("*");
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isN());    
  }  
  
  @Test
  public void isMN()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isMN());
    
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isMN());

    var.getMultiplicity().setMinimum("1");
    Assert.assertEquals(true,var.isMN());

    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(false,var.isMN());

    var.getMultiplicity().setMinimum("2");
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isMN());

    var.getMultiplicity().setMinimum("2");
    var.getMultiplicity().setMaximum("5");
    Assert.assertEquals(true,var.isMN());

    var.getMultiplicity().setMinimum("2");
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(true,var.isMN());
  }   

  @Test
  public void isMStar()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isMStar());
    
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isMStar());

    var.getMultiplicity().setMinimum("1");
    Assert.assertEquals(false,var.isMStar());

    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(false,var.isMStar());

    var.getMultiplicity().setMinimum("2");
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isMStar());

    var.getMultiplicity().setMinimum("2");
    var.getMultiplicity().setMaximum("5");
    Assert.assertEquals(false,var.isMStar());

    var.getMultiplicity().setMinimum("2");
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(true,var.isMStar());
    
  }   
  
  
  @Test
  public void isStar()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(true,var.isStar());
    
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isStar());

  }   

  
  @Test
  public void isMandatory()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isMandatory());
    
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isMandatory());

    var.getMultiplicity().setMinimum("1");
    Assert.assertEquals(true,var.isMandatory());

    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(true,var.isMandatory());
  }
  
  @Test
  public void isMandatoryMany()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isMandatoryMany());
    
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isMandatoryMany());

    var.getMultiplicity().setMinimum("1");
    Assert.assertEquals(true,var.isMandatoryMany());

    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(false,var.isMandatoryMany());
  }
  
  @Test
  public void isMandatoryOne()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    var.getMultiplicity().setMinimum("1");
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isMandatoryOne());

    var.getMultiplicity().setMinimum("0");
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(false,var.isMandatoryOne());

    var.getMultiplicity().setMinimum("1");
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isMandatoryOne());

    var.getMultiplicity().setMinimum("0");
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(false,var.isMandatoryOne());

    var.getMultiplicity().setMinimum("1");
    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(true,var.isMandatoryOne());

    var.getMultiplicity().setMinimum("3");
    var.getMultiplicity().setMaximum("6");
    Assert.assertEquals(false,var.isMandatoryOne());

  }

  @Test
  public void isOptionalMany()
  {
    AssociationVariable var = new AssociationVariable("x","y","","",createMultiplicity(0,1),true);
    
    var.getMultiplicity().setMinimum("1");
    Assert.assertEquals(false, var.isOptionalMany());

    var.getMultiplicity().setMinimum("0");
    var.getMultiplicity().setMaximum("1");
    Assert.assertEquals(false, var.isOptionalMany());
    
    var.getMultiplicity().setMaximum("2");
    Assert.assertEquals(true, var.isOptionalMany());
    
    var.getMultiplicity().setMaximum("*");
    Assert.assertEquals(true, var.isOptionalMany());
    
  }
  
  @Test
  public void getAssociatedName()
  {
    AssociationVariable var1 = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    AssociationVariable var2 = new AssociationVariable("x","y","","",createMultiplicity(0,0),true);
    
    Assert.assertEquals(null, var1.getRelatedAssociation());
    
    var2.setRelatedAssociation(var1);
    Assert.assertEquals(var2, var1.getRelatedAssociation());
    Assert.assertEquals(var1, var2.getRelatedAssociation());
  }
  
  
  @Test
  public void isImmutable()
  {
    AssociationVariable assoc1 = new AssociationVariable("x","y","","",createMultiplicity(1,1),true);
    Assert.assertEquals(false, assoc1.isImmutable());
    
    AssociationVariable assoc2 = new AssociationVariable("x","y", "","",createMultiplicity(1,1),true);
    assoc1.setRelatedAssociation(assoc2);
    Assert.assertEquals(false, assoc1.isImmutable());
    Assert.assertEquals(false, assoc2.isImmutable());
  
    assoc1.setModifier("immutable");
    Assert.assertEquals(true, assoc1.isImmutable());
    Assert.assertEquals(true, assoc2.isImmutable());
    
    assoc2.setModifier("immutable");
    Assert.assertEquals(true, assoc1.isImmutable());
    Assert.assertEquals(true, assoc2.isImmutable());
  }
  
  @Test
  public void isImmutableBasedOnClass()
  {
    UmpleClass c = new UmpleClass("Student");
    UmpleClass c2 = new UmpleClass("Mentor");
    
    AssociationVariable assoc1 = new AssociationVariable("x","Mentor","","",createMultiplicity(1,1),true);
    AssociationVariable assoc2 = new AssociationVariable("y","Student", "","",createMultiplicity(1,1),false);
    assoc1.setRelatedAssociation(assoc2);
    c.addAssociationVariable(assoc1);
    Assert.assertEquals(false, assoc1.isImmutable());
    Assert.assertEquals(false, assoc2.isImmutable());
    
    Assert.assertTrue(c.setImmutable());
    Assert.assertEquals(true, assoc1.isImmutable());
    Assert.assertEquals(true, assoc2.isImmutable());
    
    
    c = new UmpleClass("Student");
    assoc1 = new AssociationVariable("x","Mentor","","",createMultiplicity(0,1),true);
    assoc2 = new AssociationVariable("y","Student", "","",createMultiplicity(0,2),false);
    assoc1.setRelatedAssociation(assoc2);
    Assert.assertTrue(c.addAssociationVariable(assoc1));
    Assert.assertTrue(c2.addAssociationVariable(assoc2));
    
    Assert.assertEquals(false, assoc1.isImmutable());
    Assert.assertEquals(false, assoc2.isImmutable());
    
    Assert.assertTrue(c2.setImmutable());
    Assert.assertEquals(false, assoc1.isImmutable());
    Assert.assertEquals(false, assoc2.isImmutable());
    
    Assert.assertTrue(c.setImmutable());
    Assert.assertEquals(true, assoc1.isImmutable());
    Assert.assertEquals(true, assoc2.isImmutable());
  }
  
  @Test
  public void canOnlyBeMadeImmutableWhenIsNotBidirectionalAndWhenClassAtDirectedEndIsImmutable()
  {
    UmpleClass c = new UmpleClass("Student");
    UmpleClass c2 = new UmpleClass("Mentor");
        
    AssociationVariable assoc1 = new AssociationVariable("x","Mentor","","",createMultiplicity(1,1),true);
    AssociationVariable assoc2 = new AssociationVariable("y","Student", "","",createMultiplicity(1,1),true);
    assoc1.setRelatedAssociation(assoc2);
    Assert.assertFalse(assoc1.setImmutable());
    
    assoc1 = new AssociationVariable("x","Mentor","","",createMultiplicity(1,1),false);
    assoc1.setRelatedAssociation(assoc2);
    Assert.assertTrue(assoc1.setImmutable());
    
    assoc1 = new AssociationVariable("x","Mentor","","",createMultiplicity(1,1),true);
    assoc1.setRelatedAssociation(assoc2);
    c.addAssociationVariable(assoc1);
    c2.addAssociationVariable(assoc2);  
    Assert.assertFalse(assoc1.setImmutable());
    c.removeAssociationVariable(assoc1);
    
    assoc1 = new AssociationVariable("x","Mentor","","",createMultiplicity(1,1),false);
    assoc1.setRelatedAssociation(assoc2);
    c.addAssociationVariable(assoc1);
    Assert.assertFalse(assoc1.setImmutable());
    Assert.assertTrue(c.setImmutable());
    Assert.assertTrue(assoc1.setImmutable());
  }

  @Test
  public void testAssociationVariable()
  {
    AssociationVariable av1 = new AssociationVariable("x","Mentor","","",createMultiplicity(1,1),true);
    AssociationVariable av2 = new AssociationVariable("y","Student","","",createMultiplicity(1,1),true);
    Assert.assertNull(av1.getSpecializedFromVariable());
    Assert.assertNull(av2.getSpecializedFromVariable());

    av1.setSpecializedFromVariable(av2);
    Assert.assertEquals(av1.getSpecializedFromVariable(), av2);
  }
  
  @Test
  public void canOnlySetRelatedAssociationToCreateValidAssociationsWithImmutableClass()
  {
    // cannot create bidirectional association
    UmpleClass aClass = new UmpleClass("aClass");
    UmpleClass bClass = new UmpleClass("bClass");
    
    Multiplicity mult = new Multiplicity();
    mult.setRange("0", "10");
    AssociationVariable a = new AssociationVariable("a", "a", "", "", mult, true);
    mult = new Multiplicity();
    mult.setRange("0", "1");
    AssociationVariable b = new AssociationVariable("b", "b", "", "", mult, true);
    
    aClass.addAssociationVariable(a);
    bClass.addAssociationVariable(b);
    Assert.assertTrue(aClass.setImmutable()); 
    Assert.assertFalse(a.setRelatedAssociation(b));
    
    // cannot create immutable -> ?mutable? (i.e. class has no explicit immutability violations but is not immutable)
    b = new AssociationVariable("b", "b", "", "", mult, false); 
    bClass.addAssociationVariable(b);
    Assert.assertFalse(a.setRelatedAssociation(b));
    
    // cannot create immutable -> mutable
    Assert.assertTrue(aClass.setImmutable()); 
    Assert.assertFalse(a.setRelatedAssociation(b));
    
    // can create immutable -> immutable
    bClass.setImmutable();
    Assert.assertTrue(a.setRelatedAssociation(b));
    
    // immutable asymmetric reflexive association succeeds
    a = new AssociationVariable("a", "a", "", "", mult, true);
    b = new AssociationVariable("b", "b", "", "", mult, false);
    aClass.addAssociationVariable(a);
    aClass.addAssociationVariable(b);
    Assert.assertTrue(a.setRelatedAssociation(b));
  }

  @Test
  public void testAssociationVariableEnds()
  {
    String code = "class A{1 -- * C;} class B{isA A; 1 -- 4 C;} class C{}";

    UmpleModel model = getModel(code);
    model.run();
    for (UmpleClass uClass : model.getUmpleClasses()) {
      int numAv = uClass.numberOfAssociationVariables();
      for (int i = 0; i < numAv; i ++) {
        AssociationVariable av = uClass.getAssociationVariable(i);
        Association assoc = uClass.getAssociation(i);
        AssociationEnd aEnd = assoc.getEnd(av.getRelevantEnd());

        Assert.assertEquals(av.getType(), aEnd.getClassName());
        Assert.assertEquals(av.getName(), aEnd.getRoleName());
      }
    }
  }

  private Multiplicity createMultiplicity(int lower, int upper)
  {
    Multiplicity m = new Multiplicity();
    m.setRange(lower + "", upper + "");
    return m;
  }

  private UmpleModel getModel(String inCode) {
    SampleFileWriter.createFile("umpleAssociationVariableTest.ump",inCode);  
    UmpleFile uFile = new UmpleFile("umpleAssociationVariableTest.ump"); 
    return new UmpleModel(uFile);
  }
}
