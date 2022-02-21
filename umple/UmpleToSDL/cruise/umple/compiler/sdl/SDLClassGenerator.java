package cruise.umple.compiler.sdl;

import cruise.umple.compiler.*;
import cruise.umple.util.*;
import java.util.*;

@SuppressWarnings("unused")
public class SDLClassGenerator implements ILang 
{

  protected static String nl;
  public static synchronized SDLClassGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    SDLClassGenerator result = new SDLClassGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "-- PLEASE DO NOT EDIT THIS CODE" + NL + "-- This code was generated using the UMPLE @UMPLE_VERSION@ modeling language!";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL;

  // Add a newline to the end of the input
  private void appendln(StringBuffer buffer, String input, Object... variables)
  {
    append(buffer, input + "\n", variables);
  }  

  // This method will be used to append formatted strings
  // i.e. format("Hello {0} {1}", "andrew","forward");
  private void append(StringBuffer buffer, String input, Object... variables)
  {
    buffer.append(StringFormatter.format(input,variables));
  }

  /* 
  * This method will return the generated code.
  */
  public String getCode(UmpleModel model, UmpleElement uElement)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
  UmpleClass uClass = (UmpleClass) uElement;
  GeneratedClass gClass = uClass.getGeneratedClass();
  SDLGenerator gen = new SDLGenerator();
  gen.setModel(model);
  

    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}