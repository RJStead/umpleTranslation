package cruise.umple.implementation.php;

import org.junit.*;
import cruise.umple.implementation.*;

public class PhpNToOptionalNTest extends NToOptionalNTest
{

  @Before
  public void setUp()
  {
    super.setUp();
    language = "Php";
    languagePath = "php";
  }
}