package cruise.ui.jsf.templates.impl.fragment.asignation;

import cruise.model.abstractObjects.IGenerator;

import java.util.*;
import cruise.umple.compiler.Attribute;;

public class InternalTimeAsignation implements IGenerator {

  protected static String nl;
  public static synchronized InternalTimeAsignation create(String lineSeparator)
  {
    nl = lineSeparator;
    InternalTimeAsignation result = new InternalTimeAsignation();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t";
  protected final String TEXT_2 = " =getInitial";
  protected final String TEXT_3 = "Hour();" + NL + "\t";
  protected final String TEXT_4 = "=getInitial";
  protected final String TEXT_5 = "Minute();";

	public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     Attribute attVar = (Attribute) argument; 
     if (attVar.getValue()==null) { 
    stringBuffer.append(TEXT_1);
    stringBuffer.append("a"+ attVar.getUpperCaseName()+"Hour");
    stringBuffer.append(TEXT_2);
    stringBuffer.append( attVar.getUpperCaseName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append("a"+ attVar.getUpperCaseName()+"Minute");
    stringBuffer.append(TEXT_4);
    stringBuffer.append( attVar.getUpperCaseName());
    stringBuffer.append(TEXT_5);
    } 
    return stringBuffer.toString();
  }
}