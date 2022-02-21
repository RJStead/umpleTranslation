package cruise.ui.jsf.templates.impl.fragment.declaration;

import cruise.model.abstractObjects.IGenerator;

import java.util.*;
import cruise.umple.compiler.Attribute;;

public class SetteableDateDeclaration implements IGenerator {

  protected static String nl;
  public static synchronized SetteableDateDeclaration create(String lineSeparator)
  {
    nl = lineSeparator;
    SetteableDateDeclaration result = new SetteableDateDeclaration();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "private  ";
  protected final String TEXT_2 = " ";
  protected final String TEXT_3 = "=getInitial";
  protected final String TEXT_4 = "();";

	public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     Attribute attVar = (Attribute) argument; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append( attVar.getType());
    stringBuffer.append(TEXT_2);
    stringBuffer.append( "a"+attVar.getUpperCaseName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append( attVar.getUpperCaseName());
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}