/*

 Copyright: All contributers to the Umple Project
 
 This file is made available subject to the open source license found at:
 http://umple.org/license

*/

package cruise.umple.compiler;

import org.junit.*;
@Ignore
public class RubyGeneratorTraceTest
{

  UmpleModel model;
  RubyGenerator generator;

  @Before
  public void setUp()
  {
    model = new UmpleModel(null);
    generator = new RubyGenerator();
    generator.setModel(model);
  }
  
  @Test
  public void prepare_postpare_traceItem_attribute()
  {
    UmpleClass c = model.addUmpleClass("LightFixture");
    Attribute attr = new Attribute("name","String",null,null,false,c);
    TraceDirective traceDirective = new TraceDirective(model.getTracer());
    
    traceDirective.setUmpleClass(c);
    AttributeTraceItem traceAttr = new AttributeTraceItem(traceDirective);
    traceAttr.addAttribute(attr);
    traceDirective.addAttributeTraceItem(traceAttr);
    generator.prepare();

    Assert.assertEquals(1,c.numberOfCodeInjections());
    CodeInjection inject = c.getCodeInjection(0);
    Assert.assertEquals("after",inject.getType());
    Assert.assertEquals("set_name", inject.getOperation());
    Assert.assertEquals("puts \"name=#{a_name}\"",inject.getCode());
    
    GeneratorHelper.postpare(model);
    Assert.assertEquals(0,c.numberOfCodeInjections());
  }    
  
  @Test 
  public void prepare_traceItem_attribute_string_default_package()
  {
    model.setTracer(new TracerDirective("String"));
    
    UmpleClass c = model.addUmpleClass("LightFixture");
    c.setPackageName("notblah");
    Attribute attr = new Attribute("name","String",null,null,false,c);
    TraceDirective traceDirective = new TraceDirective(model.getTracer());
    
    traceDirective.setUmpleClass(c);
    AttributeTraceItem traceAttr = new AttributeTraceItem(traceDirective);
    traceAttr.addAttribute(attr);
    traceDirective.addAttributeTraceItem(traceAttr);
    generator.prepare();

    Assert.assertEquals(2,model.numberOfUmpleClasses());
    UmpleClass c2 = model.getUmpleClass("StringTracer");
    Assert.assertEquals("cruise.util",c2.getPackageName());
    
    Assert.assertEquals(1,c.numberOfCodeInjections());
  }    
  
  @Test
  public void prepare_postpare_traceItem_attribute_string()
  {
    model.setTracer(new TracerDirective("String"));
    model.setDefaultPackage("blah");
    
    UmpleClass c = model.addUmpleClass("LightFixture");
    c.setPackageName("notblah");
    Attribute attr = new Attribute("name","String",null,null,false,c);
    TraceDirective traceDirective = new TraceDirective(model.getTracer());
    
    traceDirective.setUmpleClass(c);
    AttributeTraceItem traceAttr = new AttributeTraceItem(traceDirective);
    traceAttr.addAttribute(attr);
    traceDirective.addAttributeTraceItem(traceAttr);
    generator.prepare();

    Assert.assertEquals(2,model.numberOfUmpleClasses());
    UmpleClass c2 = model.getUmpleClass("StringTracer");
    Assert.assertEquals(true,c2.getIsInternal());
    Assert.assertEquals("traces",c2.getAttribute(0).getName());
    
    Assert.assertEquals(1,c.numberOfCodeInjections());
    CodeInjection inject = c.getCodeInjection(0);
    Assert.assertEquals("after",inject.getType());
    Assert.assertEquals("set_name", inject.getOperation());
    Assert.assertEquals("StringTracer::execute(\"name=#{a_name}\")",inject.getCode());
    
    GeneratorHelper.postpare(model);
    Assert.assertEquals(0,c.numberOfCodeInjections());
    Assert.assertEquals(1,model.numberOfUmpleClasses());
    Assert.assertEquals(0, c.numberOfDepends());
  }   
  
  @Test
  public void prepare_postpare_traceItem_attribute_string_samePackage()
  {
    model.setTracer(new TracerDirective("String"));
    model.setDefaultPackage("blah");
    
    UmpleClass c = model.addUmpleClass("LightFixture");
    c.setPackageName("blah");
    Attribute attr = new Attribute("name","String",null,null,false,c);
    TraceDirective traceDirective = new TraceDirective(model.getTracer());
    
    traceDirective.setUmpleClass(c);
    AttributeTraceItem traceAttr = new AttributeTraceItem(traceDirective);
    traceAttr.addAttribute(attr);
    traceDirective.addAttributeTraceItem(traceAttr);
    generator.prepare();

    Assert.assertEquals(0,c.numberOfDepends());
  } 
  
  
}
