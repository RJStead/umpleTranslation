package cruise.umple.implementation;

import org.junit.*;

public class AssociationSortedTest extends TemplateTest {
	
	@Test
	public void AssociationShouldNotHaveSortMethod()
	{
		assertUmpleTemplateFor("AssociationSorting.ump", languagePath + "/AssociationTestSorted_Student."+ languagePath +".txt", "Student");
	}
	
	@Test
	public void AssociationShouldHaveSortMethod()
	{
		assertUmpleTemplateFor("AssociationSorting.ump", languagePath + "/AssociationTestSorted_Mentor."+ languagePath +".txt", "Mentor");
	}

}
