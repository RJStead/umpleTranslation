package cruise.ui.jsf.templates.impl.fragment.copy;

import cruise.model.abstractObjects.IGenerator;

public class InternalCopy implements IGenerator {

  protected static String nl;
  public static synchronized InternalCopy create(String lineSeparator)
  {
    nl = lineSeparator;
    InternalCopy result = new InternalCopy();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";

	public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}