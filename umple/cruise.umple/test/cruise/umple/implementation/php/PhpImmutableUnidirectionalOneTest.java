/*

 Copyright: All contributers to the Umple Project
 
 This file is made available subject to the open source license found at:
 http://umple.org/license

*/

package cruise.umple.implementation.php;

import org.junit.*;

import cruise.umple.implementation.ImmutableUnidirectionalOneTest;

public class PhpImmutableUnidirectionalOneTest extends ImmutableUnidirectionalOneTest
{
	  @Before
	  public void setUp()
	  {
	    super.setUp();
	    language = "Php";
	    languagePath = "php";
	  }
}