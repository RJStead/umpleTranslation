package cruise.ui.jsf.templates.impl;

import cruise.model.abstractObjects.IGenerator;

import java.util.*;
import cruise.model.abstractObjects.BackingObject;
import cruise.umple.compiler.*;
import cruise.ui.jsf.*;

public class BckBean implements IGenerator {

	protected static String nl;
	public static synchronized BckBean create(String lineSeparator)
	{
		nl = lineSeparator;
		BckBean result = new BckBean();
		nl = null;
		return result;
	}

	public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
	protected final String TEXT_1 = "/**" + NL + " * " + NL + " */" + NL + "package ";
	protected final String TEXT_2 = ";" + NL + "" + NL + "import javax.faces.application.FacesMessage;" + NL + "import javax.faces.context.FacesContext;" + NL + "import java.util.List;" + NL + "import java.util.Set;" + NL + "import java.util.ResourceBundle;" + NL + "import java.util.ArrayList;" + NL + "import java.util.Calendar;" + NL + "import ";
	protected final String TEXT_3 = ".control.BeanLinker;" + NL + "import ";
	protected final String TEXT_4 = ".utils.PageFlowUtils;" + NL + "import dao.keys.";
	protected final String TEXT_5 = ".*;" + NL + "import ";
	protected final String TEXT_6 = ".";
	protected final String TEXT_7 = ";";
	protected final String TEXT_8 = NL + "import ";
	protected final String TEXT_9 = ";";
	protected final String TEXT_10 = NL + NL + "public class  ";
	protected final String TEXT_11 = " implements java.io.Serializable {" + NL + "" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL + "    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(\"yyyy-MM-dd\");" + NL + "\tprivate ResourceBundle resourceBundle =  ResourceBundle.getBundle(\"bundles.";
	protected final String TEXT_12 = "\" );" + NL + "" + NL + "\tprivate ";
	protected final String TEXT_13 = " a";
	protected final String TEXT_14 = ";" + NL;
	protected final String TEXT_15 = NL + "\t";
	protected final String TEXT_16 = NL + "\t";
	protected final String TEXT_17 = NL + NL;
	protected final String TEXT_18 = NL + "\tprivate ";
	protected final String TEXT_19 = " a";
	protected final String TEXT_20 = ";" + NL + "" + NL + "\tpublic ";
	protected final String TEXT_21 = " get";
	protected final String TEXT_22 = "() {" + NL + "\t\tif (isFrom";
	protected final String TEXT_23 = "()) {" + NL + "\t\t\treturn linked";
	protected final String TEXT_24 = "();" + NL + "\t\t}" + NL + "" + NL + "\t\treturn getSelected";
	protected final String TEXT_25 = "();" + NL + "" + NL + "\t}" + NL + "" + NL + "\tpublic void set";
	protected final String TEXT_26 = "(";
	protected final String TEXT_27 = " arg) {" + NL + "\t\tthis.a";
	protected final String TEXT_28 = " = arg;" + NL + "\t}" + NL + "\t" + NL + "\tpublic String to";
	protected final String TEXT_29 = "() {" + NL + "\t\tBeanLinker bLinker = new BeanLinker();" + NL + "\t\tbLinker.setRoleName(\"";
	protected final String TEXT_30 = "\");" + NL + "\t\tbLinker.setLinker(getSelected";
	protected final String TEXT_31 = "());" + NL + "\t\tPageFlowUtils.pushBeanLinker(bLinker);" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "\t" + NL + "\tpublic String to";
	protected final String TEXT_32 = "Done() {" + NL + "\t\t";
	protected final String TEXT_33 = " newValue =(";
	protected final String TEXT_34 = ")PageFlowUtils.peekBeanLinker().getLinker();" + NL + "\t\tPageFlowUtils.popBeanLinker();" + NL + "\t\tsetSelected";
	protected final String TEXT_35 = "(newValue);" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "\t" + NL + "\tpublic String to";
	protected final String TEXT_36 = "Cancel() {" + NL + "\t\tPageFlowUtils.popBeanLinker();" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "" + NL + "\tpublic boolean isFrom";
	protected final String TEXT_37 = "() {" + NL + "" + NL + "\t\treturn linked";
	protected final String TEXT_38 = "() != null;" + NL + "\t}" + NL + "" + NL + "\tpublic ";
	protected final String TEXT_39 = " linked";
	protected final String TEXT_40 = "() {" + NL + "\t\tBeanLinker er = PageFlowUtils.peekBeanLinker(); //.getInitBean();" + NL + "\t\tif  (\"";
	protected final String TEXT_41 = "\".equals(er.getRoleName())){" + NL + "\t\t\tif(er.getInitBean()!=null && er.getInitBean()  instanceof  ";
	protected final String TEXT_42 = ")" + NL + "\t\t\t\treturn ((";
	protected final String TEXT_43 = ") er.getInitBean()).a";
	protected final String TEXT_44 = ";" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\treturn null;" + NL + "" + NL + "\t}" + NL + "" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic void setSelected";
	protected final String TEXT_45 = "(";
	protected final String TEXT_46 = " selected";
	protected final String TEXT_47 = ") {" + NL + "\t\tPageFlowUtils.getPageFlow().put(\"selected";
	protected final String TEXT_48 = "\", selected";
	protected final String TEXT_49 = ");" + NL + "\t}" + NL + "" + NL + "\tpublic ";
	protected final String TEXT_50 = " getSelected";
	protected final String TEXT_51 = "() {" + NL + "" + NL + "\t\treturn (";
	protected final String TEXT_52 = ") PageFlowUtils.getPageFlow().get(\"selected";
	protected final String TEXT_53 = "\");" + NL + "\t}" + NL + "" + NL + "\tpublic void removeSelected";
	protected final String TEXT_54 = "() {" + NL + "" + NL + "\t\tPageFlowUtils.getPageFlow().remove(\"selected";
	protected final String TEXT_55 = "\");" + NL + "" + NL + "\t}" + NL;
	protected final String TEXT_56 = NL + "\tprivate List<";
	protected final String TEXT_57 = "> a";
	protected final String TEXT_58 = "List;" + NL + "" + NL + "\tpublic List<";
	protected final String TEXT_59 = "> get";
	protected final String TEXT_60 = "List() {" + NL + "\t\treturn getSelected";
	protected final String TEXT_61 = "List();" + NL + "" + NL + "\t}" + NL + "" + NL + "\tpublic void set";
	protected final String TEXT_62 = "List(List<";
	protected final String TEXT_63 = "> arg) {" + NL + "\t\tthis.a";
	protected final String TEXT_64 = "List = arg;" + NL + "\t}" + NL + "\t" + NL + "\tpublic String to";
	protected final String TEXT_65 = "() {" + NL + "\t\tBeanLinker bLinker = new BeanLinker();" + NL + "\t\tbLinker.setRoleName(\"";
	protected final String TEXT_66 = "\");" + NL + "\t\tbLinker.setLinkerCollection(getSelected";
	protected final String TEXT_67 = "List());" + NL + "\t\tPageFlowUtils.pushBeanLinker(bLinker);" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "\t" + NL + "\tpublic String to";
	protected final String TEXT_68 = "Done() {" + NL + "\t\tList<";
	protected final String TEXT_69 = "> newList=(List<";
	protected final String TEXT_70 = ">)PageFlowUtils.peekBeanLinker().getLinkerCollection();" + NL + "\t\tPageFlowUtils.popBeanLinker();" + NL + "\t\tsetSelected";
	protected final String TEXT_71 = "List(newList);" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "\t" + NL + "\tpublic String to";
	protected final String TEXT_72 = "Cancel() {" + NL + "\t\tPageFlowUtils.popBeanLinker();" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "" + NL + "" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic void setSelected";
	protected final String TEXT_73 = "List(List<";
	protected final String TEXT_74 = "> selected";
	protected final String TEXT_75 = "List) {" + NL + "\t\tPageFlowUtils.getPageFlow().put(\"selected";
	protected final String TEXT_76 = "List\", selected";
	protected final String TEXT_77 = "List);" + NL + "\t}" + NL + "" + NL + "\tpublic List<";
	protected final String TEXT_78 = "> getSelected";
	protected final String TEXT_79 = "List() {" + NL + "" + NL + "\t\treturn (List<";
	protected final String TEXT_80 = ">) PageFlowUtils.getPageFlow().get(\"selected";
	protected final String TEXT_81 = "List\");" + NL + "\t}" + NL + "" + NL + "\tpublic void removeSelected";
	protected final String TEXT_82 = "List() {" + NL + "" + NL + "\t\tPageFlowUtils.getPageFlow().remove(\"selected";
	protected final String TEXT_83 = "List\");" + NL + "" + NL + "\t}" + NL;
	protected final String TEXT_84 = NL + "\tprivate List<";
	protected final String TEXT_85 = "> a";
	protected final String TEXT_86 = "List;" + NL + "" + NL + "\tpublic List<";
	protected final String TEXT_87 = "> get";
	protected final String TEXT_88 = "List() {" + NL + "\t\treturn getSelected";
	protected final String TEXT_89 = "List();" + NL + "" + NL + "\t}" + NL + "" + NL + "\tpublic void set";
	protected final String TEXT_90 = "List(List<";
	protected final String TEXT_91 = "> arg) {" + NL + "\t\tthis.a";
	protected final String TEXT_92 = "List = arg;" + NL + "\t}" + NL + "\t" + NL + "\tpublic String to";
	protected final String TEXT_93 = "() {" + NL + "\t\tBeanLinker bLinker = new BeanLinker();" + NL + "\t\tbLinker.setRoleName(\"";
	protected final String TEXT_94 = "\");" + NL + "\t\tbLinker.setLinkerCollection(getSelected";
	protected final String TEXT_95 = "List());" + NL + "\t\tPageFlowUtils.pushBeanLinker(bLinker);" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "\t" + NL + "\tpublic String to";
	protected final String TEXT_96 = "Done() {" + NL + "\t\tList<";
	protected final String TEXT_97 = "> newList =(List<";
	protected final String TEXT_98 = ">)PageFlowUtils.peekBeanLinker().getLinkerCollection();" + NL + "\t\tPageFlowUtils.popBeanLinker();" + NL + "\t\tsetSelected";
	protected final String TEXT_99 = "List(newList);" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "\t" + NL + "\tpublic String to";
	protected final String TEXT_100 = "Cancel() {" + NL + "\t\tPageFlowUtils.popBeanLinker();" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic void setSelected";
	protected final String TEXT_101 = "List(List<";
	protected final String TEXT_102 = "> selected";
	protected final String TEXT_103 = "List) {" + NL + "\t\tPageFlowUtils.getPageFlow().put(\"selected";
	protected final String TEXT_104 = "List\", selected";
	protected final String TEXT_105 = "List);" + NL + "\t}" + NL + "" + NL + "\tpublic List<";
	protected final String TEXT_106 = "> getSelected";
	protected final String TEXT_107 = "List() {" + NL + "" + NL + "\t\treturn (List<";
	protected final String TEXT_108 = ">) PageFlowUtils.getPageFlow().get(\"selected";
	protected final String TEXT_109 = "List\");" + NL + "\t}" + NL + "" + NL + "\tpublic void removeSelected";
	protected final String TEXT_110 = "List() {" + NL + "" + NL + "\t\tPageFlowUtils.getPageFlow().remove(\"selected";
	protected final String TEXT_111 = "List\");" + NL + "" + NL + "\t}" + NL;
	protected final String TEXT_112 = NL + "private List<";
	protected final String TEXT_113 = "> a";
	protected final String TEXT_114 = "List;" + NL + "" + NL + "\tpublic List<";
	protected final String TEXT_115 = "> get";
	protected final String TEXT_116 = "List() {" + NL + "\t\treturn getSelected";
	protected final String TEXT_117 = "List();" + NL + "" + NL + "\t}" + NL + "" + NL + "\tpublic void set";
	protected final String TEXT_118 = "List(List<";
	protected final String TEXT_119 = "> arg) {" + NL + "\t\tthis.a";
	protected final String TEXT_120 = "List = arg;" + NL + "\t}" + NL + "\t" + NL + "\t";
	protected final String TEXT_121 = NL + "\tpublic String to";
	protected final String TEXT_122 = "() {" + NL + "\t\tBeanLinker bLinker = new BeanLinker();" + NL + "\t\tbLinker.setRoleName(\"";
	protected final String TEXT_123 = "\");" + NL + "\t\t";
	protected final String TEXT_124 = ".";
	protected final String TEXT_125 = ".";
	protected final String TEXT_126 = " bean=new web.";
	protected final String TEXT_127 = ".";
	protected final String TEXT_128 = "();" + NL + "\t\tbean.set";
	protected final String TEXT_129 = "(this.getEdited";
	protected final String TEXT_130 = "());" + NL + "\t\tbLinker.setInitBean(bean);" + NL + "\t\tPageFlowUtils.pushBeanLinker(bLinker);" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "\t" + NL + "\tpublic String to";
	protected final String TEXT_131 = "Done() {" + NL + "\t\tPageFlowUtils.popBeanLinker();" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "\t" + NL + "\tpublic String to";
	protected final String TEXT_132 = "Cancel() {" + NL + "\t\tPageFlowUtils.popBeanLinker();" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "" + NL + "" + NL + "\tpublic List<";
	protected final String TEXT_133 = "> getSelected";
	protected final String TEXT_134 = "List() {" + NL + "\t\tif(this.getEdited";
	protected final String TEXT_135 = "()!=null)" + NL + "\t\t\treturn  this.getEdited";
	protected final String TEXT_136 = "().get";
	protected final String TEXT_137 = "();" + NL + "\t\treturn null;" + NL + "\t}" + NL + NL;
	protected final String TEXT_138 = NL + "\tpublic void getInstance";
	protected final String TEXT_139 = "() {" + NL + "\t\tsetSelected";
	protected final String TEXT_140 = "(";
	protected final String TEXT_141 = ".getInstance());" + NL + "" + NL + "\t}" + NL + "" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic void setSelected";
	protected final String TEXT_142 = "(";
	protected final String TEXT_143 = " selected";
	protected final String TEXT_144 = ") {" + NL + "\t\tPageFlowUtils.getPageFlow().put(\"selected";
	protected final String TEXT_145 = "\", selected";
	protected final String TEXT_146 = ");" + NL + "\t}" + NL + "" + NL + "\tpublic ";
	protected final String TEXT_147 = " getSelected";
	protected final String TEXT_148 = "() {" + NL + "" + NL + "\t\treturn (";
	protected final String TEXT_149 = ") PageFlowUtils.getPageFlow().get(\"selected";
	protected final String TEXT_150 = "\");" + NL + "\t}" + NL + "" + NL + "\tpublic void removeSelected";
	protected final String TEXT_151 = "() {" + NL + "" + NL + "\t\tPageFlowUtils.getPageFlow().remove(\"selected";
	protected final String TEXT_152 = "\");" + NL + "" + NL + "\t}" + NL;
	protected final String TEXT_153 = NL + "\tpublic ";
	protected final String TEXT_154 = " getSelected";
	protected final String TEXT_155 = "() {" + NL + "" + NL + "\t\treturn ";
	protected final String TEXT_156 = ".getInstance();" + NL + "\t}" + NL;
	protected final String TEXT_157 = NL + NL + " \tpublic ";
	protected final String TEXT_158 = " get";
	protected final String TEXT_159 = "(){" + NL + "\treturn a";
	protected final String TEXT_160 = ";" + NL + "\t}" + NL + "\tpublic void set";
	protected final String TEXT_161 = "(";
	protected final String TEXT_162 = " ){" + NL + "\tthis.a";
	protected final String TEXT_163 = "=a";
	protected final String TEXT_164 = ";" + NL + "\t}";
	protected final String TEXT_165 = " ";
	protected final String TEXT_166 = NL;
	protected final String TEXT_167 = NL;
	protected final String TEXT_168 = " ";
	protected final String TEXT_169 = NL;
	protected final String TEXT_170 = NL;
	protected final String TEXT_171 = NL + "\tpublic String cancel() {" + NL + "\t  ";
	protected final String TEXT_172 = NL + "\t  ";
	protected final String TEXT_173 = "  " + NL + "\t\t";
	protected final String TEXT_174 = NL + "\t\t";
	protected final String TEXT_175 = NL + "\t\t";
	protected final String TEXT_176 = NL + "\t\t";
	protected final String TEXT_177 = " " + NL + "\t\t";
	protected final String TEXT_178 = NL + "\t  ";
	protected final String TEXT_179 = NL + "\t  ";
	protected final String TEXT_180 = "  " + NL + "\t\t";
	protected final String TEXT_181 = NL + "\t\t";
	protected final String TEXT_182 = NL + "\t\t";
	protected final String TEXT_183 = NL + "\t\t";
	protected final String TEXT_184 = " " + NL + "\t\t";
	protected final String TEXT_185 = NL + "\t   ";
	protected final String TEXT_186 = NL + "\t   removeSelected";
	protected final String TEXT_187 = "();" + NL + "\t   ";
	protected final String TEXT_188 = NL + "\t    ";
	protected final String TEXT_189 = NL + "\t\tremoveSelected";
	protected final String TEXT_190 = "List();" + NL + "\t\t";
	protected final String TEXT_191 = NL + "\t\t";
	protected final String TEXT_192 = NL + "\t\tremoveSelected";
	protected final String TEXT_193 = "List();" + NL + "\t\t";
	protected final String TEXT_194 = NL + "\t\t";
	protected final String TEXT_195 = NL + "\t\tremoveSelected";
	protected final String TEXT_196 = "();" + NL + "\t\t";
	protected final String TEXT_197 = "\t\t" + NL + "\t\tremoveEdited";
	protected final String TEXT_198 = "();" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "" + NL + "\tpublic String createOrEdit() {" + NL + "\t\t try{" + NL + "\t\t\tif (!isEditing()) {" + NL + "\t\t\t";
	protected final String TEXT_199 = NL + "\t\t\t ";
	protected final String TEXT_200 = " a";
	protected final String TEXT_201 = "=get";
	protected final String TEXT_202 = "();" + NL + "\t\t\t ";
	protected final String TEXT_203 = NL + "\t\t\t";
	protected final String TEXT_204 = NL + "\t\t\t ";
	protected final String TEXT_205 = " a";
	protected final String TEXT_206 = "=";
	protected final String TEXT_207 = ".getInstance();" + NL + "\t\t\t ";
	protected final String TEXT_208 = "\t\t\t " + NL + "\t\t";
	protected final String TEXT_209 = NL + "\t\t\t \t";
	protected final String TEXT_210 = "[] all";
	protected final String TEXT_211 = "=null;" + NL + "\t\t\t \tif (getSelected";
	protected final String TEXT_212 = "List() !=null){" + NL + "\t\t\t \t\tall";
	protected final String TEXT_213 = " =getSelected";
	protected final String TEXT_214 = "List().toArray(new ";
	protected final String TEXT_215 = "[getSelected";
	protected final String TEXT_216 = "List().size()]);" + NL + "\t\t\t \t}else{" + NL + "\t\t\t \t\tall";
	protected final String TEXT_217 = " = new ";
	protected final String TEXT_218 = "[0];" + NL + "\t\t\t \t}" + NL + "\t \t";
	protected final String TEXT_219 = "\t\t " + NL + "\t\t\t ";
	protected final String TEXT_220 = " ";
	protected final String TEXT_221 = "=null; " + NL + "\t\t\t" + NL + "\t\t\t  ";
	protected final String TEXT_222 = NL + "\t\t\t\t";
	protected final String TEXT_223 = "  ";
	protected final String TEXT_224 = NL + "\t\t\t  ";
	protected final String TEXT_225 = NL + "\t\t\t\t";
	protected final String TEXT_226 = "  ";
	protected final String TEXT_227 = NL + "\t\t\t \t";
	protected final String TEXT_228 = NL + "\t\t\t\t";
	protected final String TEXT_229 = "Key ";
	protected final String TEXT_230 = "Key= new ";
	protected final String TEXT_231 = "Key(";
	protected final String TEXT_232 = "); " + NL + "\t\t\t\t\tif(PageFlowUtils.getDAOFactory().get";
	protected final String TEXT_233 = "DAO().findById(";
	protected final String TEXT_234 = "Key)!=null)" + NL + "\t\t\t\t\t\tthrow new RuntimeException(\"An entity with the same key field(s) exists!\");" + NL + "\t\t\t\t\t";
	protected final String TEXT_235 = " = new ";
	protected final String TEXT_236 = "(";
	protected final String TEXT_237 = ");" + NL + "\t\t\t\t";
	protected final String TEXT_238 = NL + "\t\t\t \t";
	protected final String TEXT_239 = ".set";
	protected final String TEXT_240 = "(this.getSelected";
	protected final String TEXT_241 = "());" + NL + "\t\t\t \t";
	protected final String TEXT_242 = NL + "\t\t\t\t";
	protected final String TEXT_243 = NL + "\t\t\t \t";
	protected final String TEXT_244 = ".set";
	protected final String TEXT_245 = "(this.getSelected";
	protected final String TEXT_246 = "());" + NL + "\t\t\t \t";
	protected final String TEXT_247 = "\t\t\t \t" + NL + "\t\t\t \t\t\t";
	protected final String TEXT_248 = NL + "\t\t\t \tif (getSelected";
	protected final String TEXT_249 = "List() !=null)" + NL + "\t\t\t\tfor ( ";
	protected final String TEXT_250 = " obj : getSelected";
	protected final String TEXT_251 = "List()){" + NL + "\t\t\t \t\t";
	protected final String TEXT_252 = ".add";
	protected final String TEXT_253 = "(obj);" + NL + "\t\t\t \t}" + NL + "\t\t\t \t";
	protected final String TEXT_254 = "\t" + NL + "\t\t\t\t\tPageFlowUtils.getDAOFactory().get";
	protected final String TEXT_255 = "DAO().save(";
	protected final String TEXT_256 = ", ";
	protected final String TEXT_257 = "Key);" + NL + "\t\t\t\t" + NL + "\t\t\t} else {" + NL + "\t\t\t\tif(!validate())" + NL + "\t\t\t\t\treturn \"\";" + NL + "\t\t\t\t";
	protected final String TEXT_258 = NL + "\t\t\t\t";
	protected final String TEXT_259 = NL + "\t\t\t\t";
	protected final String TEXT_260 = NL + "\t\t\t\t";
	protected final String TEXT_261 = NL + "\t" + NL + "\t\t\t\t";
	protected final String TEXT_262 = NL + "\t\t\t \tthis.getEdited";
	protected final String TEXT_263 = "().set";
	protected final String TEXT_264 = "(this.getSelected";
	protected final String TEXT_265 = "());" + NL + "\t\t\t \t";
	protected final String TEXT_266 = NL + "\t\t\t\t";
	protected final String TEXT_267 = NL + "\t\t\t \tthis.getEdited";
	protected final String TEXT_268 = "().set";
	protected final String TEXT_269 = "(this.getSelected";
	protected final String TEXT_270 = "());" + NL + "\t\t\t \t";
	protected final String TEXT_271 = "\t" + NL + "\t\t\t\t";
	protected final String TEXT_272 = NL + "\t\t\t\tif (getSelected";
	protected final String TEXT_273 = "List() !=null)" + NL + "\t\t\t\tfor ( ";
	protected final String TEXT_274 = " obj : getSelected";
	protected final String TEXT_275 = "List()){" + NL + "\t\t\t \t\tif(this.getEdited";
	protected final String TEXT_276 = "().indexOf";
	protected final String TEXT_277 = "(obj)<0)" + NL + "\t\t\t \t\t\tthis.getEdited";
	protected final String TEXT_278 = "().add";
	protected final String TEXT_279 = "(obj);" + NL + "\t\t\t \t}" + NL + "\t\t\t \tif (this.getEdited";
	protected final String TEXT_280 = "().get";
	protected final String TEXT_281 = "()!=null){" + NL + "\t\t\t\t \tArrayList<";
	protected final String TEXT_282 = "> toRemove =new ArrayList<";
	protected final String TEXT_283 = ">();" + NL + "\t\t\t\t \tfor ( ";
	protected final String TEXT_284 = " obj : this.getEdited";
	protected final String TEXT_285 = "().get";
	protected final String TEXT_286 = "()){" + NL + "\t\t\t\t \t\tif(getSelected";
	protected final String TEXT_287 = "List()!=null && !getSelected";
	protected final String TEXT_288 = "List().contains(obj))" + NL + "\t\t\t\t \t\t\ttoRemove.add(obj);" + NL + "\t\t\t\t \t\t\t" + NL + "\t\t\t\t \t\t\t" + NL + "\t\t\t\t \t}" + NL + "\t\t\t\t \tfor ( ";
	protected final String TEXT_289 = " obj : toRemove){" + NL + "\t\t\t\t \t\tthis.getEdited";
	protected final String TEXT_290 = "().remove";
	protected final String TEXT_291 = "(obj);" + NL + "\t\t\t\t \t}" + NL + "\t\t\t \t}" + NL + "\t\t\t \t";
	protected final String TEXT_292 = "\t" + NL + "\t\t\t \t";
	protected final String TEXT_293 = NL + "\t\t\t\tif (getSelected";
	protected final String TEXT_294 = "List() !=null)" + NL + "\t\t\t\tfor ( ";
	protected final String TEXT_295 = " obj : getSelected";
	protected final String TEXT_296 = "List()){" + NL + "\t\t\t \t\tif(this.getEdited";
	protected final String TEXT_297 = "().indexOf";
	protected final String TEXT_298 = "(obj)<0)" + NL + "\t\t\t \t\t\tthis.getEdited";
	protected final String TEXT_299 = "().add";
	protected final String TEXT_300 = "(obj);" + NL + "\t\t\t \t}" + NL + "\t\t\t \tif (this.getEdited";
	protected final String TEXT_301 = "().get";
	protected final String TEXT_302 = "()!=null){" + NL + "\t\t\t\t \tArrayList<";
	protected final String TEXT_303 = "> toRemove =new ArrayList<";
	protected final String TEXT_304 = ">();" + NL + "\t\t\t\t \tfor ( ";
	protected final String TEXT_305 = " obj : this.getEdited";
	protected final String TEXT_306 = "().get";
	protected final String TEXT_307 = "()){" + NL + "\t\t\t\t \t\tif(getSelected";
	protected final String TEXT_308 = "List()!=null && !getSelected";
	protected final String TEXT_309 = "List().contains(obj))" + NL + "\t\t\t\t \t\t\ttoRemove.add(obj);" + NL + "\t\t\t\t \t\t\t" + NL + "\t\t\t\t \t\t\t" + NL + "\t\t\t\t \t}" + NL + "\t\t\t\t \tfor ( ";
	protected final String TEXT_310 = " obj : toRemove){" + NL + "\t\t\t\t \t\tthis.getEdited";
	protected final String TEXT_311 = "().remove";
	protected final String TEXT_312 = "(obj);" + NL + "\t\t\t\t \t}" + NL + "\t\t\t \t}" + NL + "\t\t\t \t";
	protected final String TEXT_313 = "\t" + NL + "\t\t\t\t" + NL + "\t\t\t}" + NL + "\t\t}catch(RuntimeException e){" + NL + "\t\t\t   \tFacesMessage fm = new FacesMessage(e.getMessage());" + NL + "            \tfm.setSeverity(FacesMessage.SEVERITY_ERROR);" + NL + "              \tFacesContext.getCurrentInstance().addMessage(e.getMessage(), fm);" + NL + "              \treturn \"\";" + NL + "\t\t\t" + NL + "\t\t\t}" + NL + "\t\tcancel();" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "" + NL + "\tpublic String delete() {" + NL + "\t\tPageFlowUtils.getDAOFactory().get";
	protected final String TEXT_314 = "DAO().deleteTree(getEdited";
	protected final String TEXT_315 = "());" + NL + "\t\tthis.removeEdited";
	protected final String TEXT_316 = "();" + NL + "\t\tcancel();" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "" + NL + "\tpublic String prepareForEdit() {" + NL + "\t";
	protected final String TEXT_317 = NL + "\t";
	protected final String TEXT_318 = NL + "\t";
	protected final String TEXT_319 = NL + "\t";
	protected final String TEXT_320 = NL + "\t   ";
	protected final String TEXT_321 = NL + "\t  this.setSelected";
	protected final String TEXT_322 = "(this.getEdited";
	protected final String TEXT_323 = "().get";
	protected final String TEXT_324 = "());" + NL + "\t   ";
	protected final String TEXT_325 = NL + "\t  ";
	protected final String TEXT_326 = NL + "\t  this.setSelected";
	protected final String TEXT_327 = "List(new ArrayList(this.getEdited";
	protected final String TEXT_328 = "().get";
	protected final String TEXT_329 = "()));" + NL + "\t   ";
	protected final String TEXT_330 = NL + "\t   ";
	protected final String TEXT_331 = NL + "\t  this.setSelected";
	protected final String TEXT_332 = "List(new ArrayList(this.getEdited";
	protected final String TEXT_333 = "().get";
	protected final String TEXT_334 = "()));" + NL + "\t   ";
	protected final String TEXT_335 = NL + "\t";
	protected final String TEXT_336 = NL + "\t  this.setSelected";
	protected final String TEXT_337 = "(this.getEdited";
	protected final String TEXT_338 = "().get";
	protected final String TEXT_339 = "());" + NL + "\t   ";
	protected final String TEXT_340 = NL + "\treturn \"\";" + NL + "\t}" + NL + "" + NL + "\tpublic boolean isEditing(){" + NL + "\t\treturn this.getEdited";
	protected final String TEXT_341 = "() != null;" + NL + "\t}" + NL + "\t" + NL + "\tpublic void removeEdited";
	protected final String TEXT_342 = "() {" + NL + "\t\tPageFlowUtils.getPageFlow().remove(\"edited";
	protected final String TEXT_343 = "\");" + NL + "" + NL + "\t}" + NL + "" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic void setEdited";
	protected final String TEXT_344 = "(";
	protected final String TEXT_345 = " edited";
	protected final String TEXT_346 = ") {" + NL + "\t\tPageFlowUtils.getPageFlow().put(\"edited";
	protected final String TEXT_347 = "\", edited";
	protected final String TEXT_348 = ");" + NL + "\t}" + NL + "" + NL + "\tpublic ";
	protected final String TEXT_349 = " getEdited";
	protected final String TEXT_350 = "() {" + NL + "\t\treturn (";
	protected final String TEXT_351 = ") PageFlowUtils.getPageFlow().get(\"edited";
	protected final String TEXT_352 = "\");" + NL + "" + NL + "\t}" + NL + "" + NL + "\tpublic List<";
	protected final String TEXT_353 = "> getEdited";
	protected final String TEXT_354 = "List() {" + NL + "\t\tif ( PageFlowUtils.peekBeanLinker().getLinkerCollection() instanceof List<?> )" + NL + "\t\t\treturn (List<";
	protected final String TEXT_355 = ">) PageFlowUtils.peekBeanLinker()" + NL + "\t\t\t\t.getLinkerCollection();" + NL + "\t\treturn null;" + NL + "\t}" + NL + "" + NL + "" + NL + "\tpublic List<? extends ";
	protected final String TEXT_356 = "> get";
	protected final String TEXT_357 = "s() {";
	protected final String TEXT_358 = NL + "\t\tif (isFrom";
	protected final String TEXT_359 = "()) {" + NL + "\t\t\treturn linked";
	protected final String TEXT_360 = "().get";
	protected final String TEXT_361 = "();" + NL + "\t\t}" + NL + "\t\t";
	protected final String TEXT_362 = NL + "\t";
	protected final String TEXT_363 = NL + "\t\treturn PageFlowUtils.getDAOFactory().get";
	protected final String TEXT_364 = "DAO().findAll(";
	protected final String TEXT_365 = ");" + NL + "\t}" + NL + "" + NL + "\tpublic String bind() {" + NL + "\t\tPageFlowUtils.peekBeanLinker().setLinker(getSelected";
	protected final String TEXT_366 = "());" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "" + NL + "\tpublic String bindCollection() {" + NL + "\t\tif(PageFlowUtils.peekBeanLinker().getLinkerCollection()==null)" + NL + "\t\t\tPageFlowUtils.peekBeanLinker().setLinkerCollection(new ArrayList<";
	protected final String TEXT_367 = ">());" + NL + "\t\tif(!PageFlowUtils.peekBeanLinker().getLinkerCollection().contains(getSelected";
	protected final String TEXT_368 = "()))" + NL + "\t\t\tPageFlowUtils.peekBeanLinker().getLinkerCollection().add(getSelected";
	protected final String TEXT_369 = "());" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "\t\t" + NL + "\tpublic String removeFromLinker() {" + NL + "\t\tgetEdited";
	protected final String TEXT_370 = "List().remove(getEdited";
	protected final String TEXT_371 = "List().indexOf(getSelected";
	protected final String TEXT_372 = "()));" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "\t" + NL + "\tpublic String removeLinker() {" + NL + "\t\tPageFlowUtils.peekBeanLinker().setLinker(null);" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "\t" + NL + "\t" + NL + "\tpublic boolean isSingleSelection(){" + NL + "\t\treturn PageFlowUtils.peekBeanLinker().isSingleSelection();" + NL + "\t}" + NL + "\t" + NL + "\tpublic ";
	protected final String TEXT_373 = " getSelected";
	protected final String TEXT_374 = "() {" + NL + "\t\tif(PageFlowUtils.peekBeanLinker().getLinker() instanceof ";
	protected final String TEXT_375 = ")" + NL + "\t\t\treturn (";
	protected final String TEXT_376 = ") PageFlowUtils.peekBeanLinker().getLinker();" + NL + "\t\treturn null;" + NL + "\t}" + NL + "\t" + NL + "\tpublic void  setSelected";
	protected final String TEXT_377 = "(";
	protected final String TEXT_378 = " selected";
	protected final String TEXT_379 = ") {" + NL + "\t\tPageFlowUtils.peekBeanLinker().setLinker(selected";
	protected final String TEXT_380 = ");" + NL + "\t}";
	protected final String TEXT_381 = NL;
	protected final String TEXT_382 = "\t" + NL + "\t\t" + NL + "}";
	protected final String TEXT_383 = NL;

	public String generate(Object argument)
	{
		final StringBuffer stringBuffer = new StringBuffer();
		BackingObject bckObject = (BackingObject) argument; 
		String voName= bckObject.getClassName()+"VO";
		String preffix=JSFProvider.properties.getProperty(cruise.model.Constants.PACKAGE_PREFIX);
		String suffix=JSFProvider.properties.getProperty(cruise.model.Constants.BCK_OBJECT_SUFFIX);
		String className=bckObject.getClassName()+suffix;

		List<AssociationVariable> mergeList= new ArrayList<AssociationVariable>();
		mergeList.addAll( bckObject.getConstructorOneAsociations());
		mergeList.addAll(bckObject.getZeroOrOneAsociations());
		
		List<AssociationVariable> multipleZeroManyToOptionalAssociations=bckObject.getZeroManyToOptionalAssociations();
		List<AssociationVariable> multipleNManyToOptionalAssociations=bckObject.getNManyToOptionalAssociations();
		List<AssociationVariable> multipleManyToOneAsociations =bckObject.getManyToOneAssociations();
		List<AssociationVariable> mandatoryToSingletonAssociations=bckObject.getMandatoryToSingletonAssociations();
		List<AssociationVariable> optionalToSingletonAssociations=bckObject.getOptionalToSingletonAssociations();


		stringBuffer.append(TEXT_1);
		stringBuffer.append(preffix + "." + bckObject.getPackageName());
		stringBuffer.append(TEXT_2);
		stringBuffer.append(preffix);
		stringBuffer.append(TEXT_3);
		stringBuffer.append(preffix);
		stringBuffer.append(TEXT_4);
		stringBuffer.append(bckObject.getPackageName());
		stringBuffer.append(TEXT_5);
		stringBuffer.append(bckObject.getPackageName());
		stringBuffer.append(TEXT_6);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_7);
		for (Iterator<String> i = bckObject.getImports().iterator(); i.hasNext(); ) { 
			stringBuffer.append(TEXT_8);
			stringBuffer.append(i.next().toString());
			stringBuffer.append(TEXT_9);
		} 



		stringBuffer.append(TEXT_10);
		stringBuffer.append(className);
		stringBuffer.append(TEXT_11);
		stringBuffer.append((bckObject.getPackageName()!=null?(bckObject.getPackageName()+"."):"" )+bckObject.getClassName());
		stringBuffer.append(TEXT_12);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_13);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_14);
		for (Attribute attVar: bckObject.getAttVariables()) { 
			stringBuffer.append(TEXT_15);
			stringBuffer.append(bckObject.getFragmentProvider().getDeclarationFragment(attVar));
		} 
		for (Attribute attVar: bckObject.getKeysAttributes()) { 
			stringBuffer.append(TEXT_16);
			stringBuffer.append(bckObject.getFragmentProvider().getDeclarationFragment(attVar));
		} 
		stringBuffer.append(TEXT_17);
		for (AssociationVariable assocVar: mergeList) {
			//if (assocVar.getRelatedAssociation().isIsNavigable()) {
				stringBuffer.append(TEXT_18);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_19);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_20);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_21);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_22);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_23);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_24);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_25);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_26);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_27);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_28);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_29);
				stringBuffer.append(assocVar.getRelatedAssociation().getUpperCaseName());
				stringBuffer.append(TEXT_30);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_31);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_32);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_33);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_34);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_35);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_36);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_37);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_38);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_39);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_40);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_41);
				stringBuffer.append(className);
				stringBuffer.append(TEXT_42);
				stringBuffer.append(className);
				stringBuffer.append(TEXT_43);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_44);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_45);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_46);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_47);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_48);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_49);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_50);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_51);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_52);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_53);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_54);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_55);
			//}
		} 
		for (AssociationVariable assocVar: multipleZeroManyToOptionalAssociations) { 
			stringBuffer.append(TEXT_56);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_57);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_58);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_59);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_60);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_61);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_62);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_63);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_64);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_65);
			stringBuffer.append(assocVar.getRelatedAssociation().getUpperCaseName());
			stringBuffer.append(TEXT_66);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_67);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_68);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_69);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_70);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_71);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_72);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_73);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_74);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_75);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_76);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_77);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_78);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_79);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_80);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_81);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_82);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_83);
		} 
		for (AssociationVariable assocVar: multipleNManyToOptionalAssociations) { 
			stringBuffer.append(TEXT_84);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_85);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_86);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_87);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_88);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_89);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_90);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_91);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_92);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_93);
			stringBuffer.append(assocVar.getRelatedAssociation().getUpperCaseName());
			stringBuffer.append(TEXT_94);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_95);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_96);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_97);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_98);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_99);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_100);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_101);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_102);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_103);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_104);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_105);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_106);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_107);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_108);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_109);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_110);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_111);
		} 
		for (AssociationVariable assocVar: multipleManyToOneAsociations) { 
			if (assocVar.isIsNavigable()) {

				stringBuffer.append(TEXT_112);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_113);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_114);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_115);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_116);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_117);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_118);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_119);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_120);

				String url=bckObject.getFragmentProvider().getClassMap().get(assocVar.getType()).getPackageName();
				if (url==null)
					url="";
				//	url=url.replace('.', '/');	

				stringBuffer.append(TEXT_121);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_122);
				stringBuffer.append(assocVar.getRelatedAssociation().getUpperCaseName());
				stringBuffer.append(TEXT_123);
				stringBuffer.append(preffix);
				stringBuffer.append(TEXT_124);
				stringBuffer.append(url);
				stringBuffer.append(TEXT_125);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(suffix);
				stringBuffer.append(TEXT_126);
				stringBuffer.append(url);
				stringBuffer.append(TEXT_127);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(suffix);
				stringBuffer.append(TEXT_128);
				stringBuffer.append(assocVar.getRelatedAssociation().getUpperCaseName());
				stringBuffer.append(TEXT_129);
				stringBuffer.append(voName);
				stringBuffer.append(TEXT_130);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_131);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_132);
				stringBuffer.append(assocVar.getType());
				stringBuffer.append(TEXT_133);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_134);
				stringBuffer.append(voName);
				stringBuffer.append(TEXT_135);
				stringBuffer.append(voName);

				stringBuffer.append(TEXT_136);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_137);
			}
		} 
		for (AssociationVariable assocVar: optionalToSingletonAssociations) { 
			stringBuffer.append(TEXT_138);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_139);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_140);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_141);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_142);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_143);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_144);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_145);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_146);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_147);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_148);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_149);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_150);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_151);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_152);
		} 
		for (AssociationVariable assocVar: mandatoryToSingletonAssociations) { 
			stringBuffer.append(TEXT_153);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_154);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_155);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_156);
		} 
		stringBuffer.append(TEXT_157);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_158);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_159);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_160);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_161);
		stringBuffer.append(bckObject.getClassName() + " a"+voName);
		stringBuffer.append(TEXT_162);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_163);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_164);
		for (Attribute attVar: bckObject.getAttVariables()) { 
			stringBuffer.append(TEXT_165);
			stringBuffer.append(TEXT_166);
			stringBuffer.append(bckObject.getFragmentProvider().getGetFragment(attVar));
			stringBuffer.append(TEXT_167);
			stringBuffer.append(bckObject.getFragmentProvider().getSetFragment(attVar));
		} 
		for (Attribute attVar: bckObject.getKeysAttributes()) { 
			stringBuffer.append(TEXT_168);
			stringBuffer.append(TEXT_169);
			stringBuffer.append(bckObject.getFragmentProvider().getGetFragment(attVar));
			stringBuffer.append(TEXT_170);
			stringBuffer.append(bckObject.getFragmentProvider().getSetFragment(attVar));
		} 
		stringBuffer.append(TEXT_171);
		for (Attribute attVar: bckObject.getAttVariables()) { 
			Object obj= attVar.getValue();
			if (obj!=null &&  !"Date".equals(attVar.getType()) ){

				stringBuffer.append(TEXT_172);
				stringBuffer.append(bckObject.getFragmentProvider().getAsignationFragment(attVar, obj.toString()) );
				stringBuffer.append(TEXT_173);
			}else if ("Date".equals(attVar.getType()) ){ 
				stringBuffer.append(TEXT_174);
				stringBuffer.append(bckObject.getFragmentProvider().getAsignationFragment(attVar, "getInitial"+attVar.getUpperCaseName()+"()") );
				stringBuffer.append(TEXT_175);
			}else{ 
				stringBuffer.append(TEXT_176);
				stringBuffer.append(bckObject.getFragmentProvider().getAsignationFragment(attVar, "null") );
				stringBuffer.append(TEXT_177);
			}
		} 
		stringBuffer.append(TEXT_178);
		for (Attribute attVar: bckObject.getKeysAttributes()) { 
			Object obj= attVar.getValue();
			if (obj!=null &&  !"Date".equals(attVar.getType()) ){

				stringBuffer.append(TEXT_179);
				stringBuffer.append(bckObject.getFragmentProvider().getAsignationFragment(attVar, obj.toString()) );
				stringBuffer.append(TEXT_180);
			}else if ("Date".equals(attVar.getType()) ){ 
				stringBuffer.append(TEXT_181);
				stringBuffer.append(bckObject.getFragmentProvider().getAsignationFragment(attVar, "getInitial"+attVar.getUpperCaseName()+"()") );
				stringBuffer.append(TEXT_182);
			}else{ 
				stringBuffer.append(TEXT_183);
				stringBuffer.append(bckObject.getFragmentProvider().getAsignationFragment(attVar, "null") );
				stringBuffer.append(TEXT_184);
			}
		} 
		stringBuffer.append(TEXT_185);
		for (AssociationVariable assocVar: mergeList) { 
			stringBuffer.append(TEXT_186);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_187);
		} 
		stringBuffer.append(TEXT_188);
		for (AssociationVariable assocVar: multipleZeroManyToOptionalAssociations) { 
			stringBuffer.append(TEXT_189);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_190);
		} 
		stringBuffer.append(TEXT_191);
		for (AssociationVariable assocVar: multipleNManyToOptionalAssociations) { 
			stringBuffer.append(TEXT_192);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_193);
		} 
		stringBuffer.append(TEXT_194);
		for (AssociationVariable assocVar: optionalToSingletonAssociations) { 
			stringBuffer.append(TEXT_195);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_196);
		} 
		stringBuffer.append(TEXT_197);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_198);
		for (AssociationVariable assocVar: bckObject.getConstructorOneAsociations()) { 
			stringBuffer.append(TEXT_199);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_200);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_201);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_202);
		} 
		stringBuffer.append(TEXT_203);
		for (AssociationVariable assocVar: mandatoryToSingletonAssociations) { 
			stringBuffer.append(TEXT_204);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_205);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_206);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_207);
		} 
		stringBuffer.append(TEXT_208);
		for (AssociationVariable assocVar: multipleNManyToOptionalAssociations) { 
			stringBuffer.append(TEXT_209);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_210);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_211);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_212);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_213);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_214);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_215);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_216);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_217);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_218);
		} 
		stringBuffer.append(TEXT_219);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_220);
		stringBuffer.append(bckObject.getClassName().toLowerCase());
		stringBuffer.append(TEXT_221);
		for (Attribute attVar: bckObject.getAttVariables()) { 
			stringBuffer.append(TEXT_222);
			stringBuffer.append(bckObject.getFragmentProvider().getPreConstructorFragment(attVar) );
			stringBuffer.append(TEXT_223);
		} 
		stringBuffer.append(TEXT_224);
		for (Attribute attVar: bckObject.getKeysAttributes()) { 
			stringBuffer.append(TEXT_225);
			stringBuffer.append(bckObject.getFragmentProvider().getPreConstructorFragment(attVar) );
			stringBuffer.append(TEXT_226);
		} 
		stringBuffer.append(TEXT_227);

		Iterator<Attribute> keyIter=bckObject.getKeysAttributes().iterator();
		String signature="";
		while (keyIter.hasNext()){
			Attribute att=keyIter.next();
			signature=signature+" a"+ att.getUpperCaseName();
			if(keyIter.hasNext())
				signature=signature+", ";
		}

		stringBuffer.append(TEXT_228);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_229);
		stringBuffer.append(bckObject.getClassName().toLowerCase());
		stringBuffer.append(TEXT_230);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_231);
		stringBuffer.append(signature);
		stringBuffer.append(TEXT_232);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_233);
		stringBuffer.append(bckObject.getClassName().toLowerCase());
		stringBuffer.append(TEXT_234);
		stringBuffer.append(bckObject.getClassName().toLowerCase());
		stringBuffer.append(TEXT_235);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_236);
		stringBuffer.append(bckObject.getConstructorSignature());
		stringBuffer.append(TEXT_237);
		for (AssociationVariable assocVar: bckObject.getZeroOrOneAsociations()) { 
			stringBuffer.append(TEXT_238);
			stringBuffer.append(bckObject.getClassName().toLowerCase());
			stringBuffer.append(TEXT_239);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_240);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_241);
		} 
		stringBuffer.append(TEXT_242);
		for (AssociationVariable assocVar: optionalToSingletonAssociations) { 
			stringBuffer.append(TEXT_243);
			stringBuffer.append(bckObject.getClassName().toLowerCase());
			stringBuffer.append(TEXT_244);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_245);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_246);
		} 
		stringBuffer.append(TEXT_247);
		for (AssociationVariable assocVar: multipleZeroManyToOptionalAssociations) { 
			stringBuffer.append(TEXT_248);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_249);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_250);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_251);
			stringBuffer.append(bckObject.getClassName().toLowerCase());
			stringBuffer.append(TEXT_252);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_253);
		} 
		stringBuffer.append(TEXT_254);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_255);
		stringBuffer.append(bckObject.getClassName().toLowerCase());
		stringBuffer.append(TEXT_256);
		stringBuffer.append(bckObject.getClassName().toLowerCase());
		stringBuffer.append(TEXT_257);
		for (Attribute attVar: bckObject.getAttVariables()) { 
			stringBuffer.append(TEXT_258);
			stringBuffer.append(bckObject.getFragmentProvider().getCopyFragment(attVar,"this.getEdited"+voName+"()") );
		} 
		stringBuffer.append(TEXT_259);
		for (Attribute attVar: bckObject.getKeysAttributes()) { 
			stringBuffer.append(TEXT_260);
			stringBuffer.append(bckObject.getFragmentProvider().getCopyFragment(attVar,"this.getEdited"+voName+"()") );
		} 
		stringBuffer.append(TEXT_261);
		for (AssociationVariable assocVar: mergeList) { 
				stringBuffer.append(TEXT_262);
				stringBuffer.append(voName);
				stringBuffer.append(TEXT_263);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_264);
				stringBuffer.append(assocVar.getUpperCaseName());
				stringBuffer.append(TEXT_265);
		} 
		stringBuffer.append(TEXT_266);
		for (AssociationVariable assocVar: optionalToSingletonAssociations) { 
			stringBuffer.append(TEXT_267);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_268);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_269);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_270);
		} 
		stringBuffer.append(TEXT_271);
		for (AssociationVariable assocVar: multipleZeroManyToOptionalAssociations) { 
			stringBuffer.append(TEXT_272);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_273);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_274);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_275);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_276);
			int l=assocVar.getUpperCaseName().length();
			stringBuffer.append(assocVar.getUpperCaseName().substring(0, l-1));
			stringBuffer.append(TEXT_277);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_278);
			l=assocVar.getUpperCaseName().length();
			stringBuffer.append(assocVar.getUpperCaseName().substring(0, l-1));
			stringBuffer.append(TEXT_279);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_280);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_281);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_282);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_283);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_284);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_285);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_286);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_287);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_288);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_289);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_290);
			l=assocVar.getUpperCaseName().length();
			stringBuffer.append(assocVar.getUpperCaseName().substring(0, l-1));
			stringBuffer.append(TEXT_291);
		} 
		stringBuffer.append(TEXT_292);
		for (AssociationVariable assocVar: multipleNManyToOptionalAssociations) { 
			stringBuffer.append(TEXT_293);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_294);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_295);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_296);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_297);
			int l=assocVar.getUpperCaseName().length();
			stringBuffer.append(assocVar.getUpperCaseName().substring(0, l-1));
			stringBuffer.append(TEXT_298);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_299);
			l=assocVar.getUpperCaseName().length();
			stringBuffer.append(assocVar.getUpperCaseName().substring(0, l-1));
			stringBuffer.append(TEXT_300);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_301);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_302);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_303);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_304);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_305);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_306);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_307);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_308);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_309);
			stringBuffer.append(assocVar.getType());
			stringBuffer.append(TEXT_310);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_311);
			l=assocVar.getUpperCaseName().length();
			stringBuffer.append(assocVar.getUpperCaseName().substring(0, l-1));
			stringBuffer.append(TEXT_312);
		} 
		stringBuffer.append(TEXT_313);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_314);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_315);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_316);
		for (Attribute attVar: bckObject.getAttVariables()) { 
			stringBuffer.append(TEXT_317);
			stringBuffer.append(bckObject.getFragmentProvider().getReverseCopyFragment(attVar,"this.getEdited"+voName+"()") );
		} 
		stringBuffer.append(TEXT_318);
		for (Attribute attVar: bckObject.getKeysAttributes()) { 
			stringBuffer.append(TEXT_319);
			stringBuffer.append(bckObject.getFragmentProvider().getReverseCopyFragment(attVar,"this.getEdited"+voName+"()") );
		} 
		stringBuffer.append(TEXT_320);
		for (AssociationVariable assocVar: mergeList) { 
			stringBuffer.append(TEXT_321);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_322);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_323);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_324);
		} 
		stringBuffer.append(TEXT_325);
		for (AssociationVariable assocVar: multipleZeroManyToOptionalAssociations) { 
			stringBuffer.append(TEXT_326);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_327);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_328);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_329);
		} 
		stringBuffer.append(TEXT_330);
		for (AssociationVariable assocVar: multipleNManyToOptionalAssociations) { 
			stringBuffer.append(TEXT_331);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_332);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_333);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_334);
		} 
		stringBuffer.append(TEXT_335);
		for (AssociationVariable assocVar: optionalToSingletonAssociations) { 
			stringBuffer.append(TEXT_336);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_337);
			stringBuffer.append(voName);
			stringBuffer.append(TEXT_338);
			stringBuffer.append(assocVar.getUpperCaseName());
			stringBuffer.append(TEXT_339);
		} 
		stringBuffer.append(TEXT_340);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_341);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_342);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_343);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_344);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_345);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_346);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_347);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_348);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_349);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_350);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_351);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_352);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_353);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_354);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_355);
		stringBuffer.append(bckObject.getDAOTable());
		stringBuffer.append(TEXT_356);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_357);
		for (AssociationVariable assocVar: mergeList) { 
			if (assocVar.getRelatedAssociation().isIsNavigable()) {
				if(assocVar.getRelatedAssociation().getMultiplicity().getUpperBound() > 1 || assocVar.getRelatedAssociation().getMultiplicity().getUpperBound() == -1){	
					stringBuffer.append(TEXT_358);
					stringBuffer.append(assocVar.getUpperCaseName());
					stringBuffer.append(TEXT_359);
					stringBuffer.append(assocVar.getUpperCaseName());
					stringBuffer.append(TEXT_360);
					stringBuffer.append(assocVar.getRelatedAssociation().getUpperCaseName());
					stringBuffer.append(TEXT_361);
				} 
				stringBuffer.append(TEXT_362);
			}
		} 
		stringBuffer.append(TEXT_363);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_364);
		stringBuffer.append(bckObject.hasParent()?(bckObject.getClassName()+".class"):"");
		stringBuffer.append(TEXT_365);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_366);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_367);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_368);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_369);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_370);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_371);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_372);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_373);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_374);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_375);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_376);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_377);
		stringBuffer.append(bckObject.getClassName());
		stringBuffer.append(TEXT_378);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_379);
		stringBuffer.append(voName);
		stringBuffer.append(TEXT_380);
		stringBuffer.append(TEXT_381);
		stringBuffer.append((new Validation()).generate(bckObject));
		stringBuffer.append(TEXT_382);
		stringBuffer.append(TEXT_383);
		return stringBuffer.toString();
	}
}