package cruise.umple.implementation.ruby;

import org.junit.*;
import cruise.umple.implementation.*;

public class RubyUnidirectionalOneTest extends UnidirectionalOneTest
{

  @Before
  public void setUp()
  {
    super.setUp();
    language = "Ruby";
    languagePath = "ruby";
  }
}