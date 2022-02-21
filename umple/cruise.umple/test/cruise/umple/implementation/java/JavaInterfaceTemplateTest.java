package cruise.umple.implementation.java;

import org.junit.*;

import cruise.umple.implementation.InterfaceTemplateTest;


public class JavaInterfaceTemplateTest extends InterfaceTemplateTest
{

	@Before
	public void setUp()
	{
		super.setUp();
		language = "Java";
		languagePath = "java";
	}
	
	@Test
	public void MethodsInterface()
	{
		assertUmpleTemplateFor("InterfaceTemplateTest.ump",languagePath + "/InterfaceTemplateTest_Methods."+ languagePath +".txt","ICodeTranslator");
	}
	
	@Test
	public void isASingleInterface()
	{
		assertUmpleTemplateFor("InterfaceTemplateTest.ump",languagePath + "/InterfaceTemplateTest_singleImplements."+ languagePath +".txt","IFirstChild");
	}
	
	@Test
	public void isAMultipleInterface()
	{
		assertUmpleTemplateFor("InterfaceTemplateTest.ump",languagePath + "/InterfaceTemplateTest_multipleImplements."+ languagePath +".txt","ISecondChild");
	}
	
	@Test
	public void implementsMultipleInterface()
	{
		assertUmpleTemplateFor("InterfaceTemplateTest.ump",languagePath + "/InterfaceTemplateTest_implementsMultipleInterface."+ languagePath +".txt","A");
	}
	
	@Test
	public void extendsImplementsMultipleInterface()
	{
		assertUmpleTemplateFor("InterfaceTemplateTest2.ump",languagePath + "/InterfaceTemplateTest_extendsImplementsMultipleInterface."+ languagePath +".txt","B");
		assertUmpleTemplateFor("InterfaceTemplateTest3.ump",languagePath + "/InterfaceTemplateTest_extendsImplementsMultipleInterface2."+ languagePath +".txt","A");
	}
}
