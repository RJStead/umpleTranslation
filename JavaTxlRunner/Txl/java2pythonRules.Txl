% yava2python project
% Conversions and transformations 
% Mykhaylo Sorochan, January 2009
% msorc@bigmir.net, macroexpand.org

% Copyright 2009 Mykhaylo Sorochan
%
% Redistribution and use in source and binary forms, with or without
% modification, are permitted provided that the following conditions are met:
%
%    Redistributions of source code must retain the above copyright notice,
%    this list of conditions and the following disclaimer.
%    Redistributions in binary form must reproduce the above copyright notice,
%    this list of conditions and the following disclaimer in the documentation
%    and/or other materials provided with the distribution.
%
%    THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
%    INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
%    AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
%    AUTHORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
%    OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
%    SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
%    INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
%    CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
%    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
%    POSSIBILITY OF SUCH DAMAGE.

%%import
function transformImpEx
    replace [program]
        P [program]
    by
        P [removeImports]
end function

%remove package header and import declarations
function removeImports
    replace * [package_declaration]
        PkgHead [opt package_header]
        ImpDecl [repeat import_declaration]
        TypeDecl [repeat type_declaration]
    by
        TypeDecl
end function


%%class
function classTransforms
    replace [program]
	P [program]
    by
	P [transformClassHeaderNoExtend]
	  [transformClassHeaderExtend]

	  [removeEmptyAttributes]
	  [transformAttrDeclToAss]

	  [constructorTransforms]

	  [transformMethodDecl]
	  [transformConstructorHeader]
	
	  [addInitMethod]

	  [transformNonParameterVarRefs]

	  [tranformThisVarRefs]
	  [tranformThisClassVarRefs]
end function

% transform references to parameter variables
rule transformNonParameterVarRefs
    replace $ [repeat class_body_declaration]
 	Method [method_declaration]
 	Methods [repeat class_body_declaration]
    deconstruct Method
	_ [repeat decorator] 'def _ [method_name] '( VarList [list variable_name] ') ': 
	_ [repeat declaration_or_statement]
    construct VarIds [repeat id]
	_ [^ VarList]
    by
	Method [javaRef2Self VarIds] [javaStaticVar2Self VarIds]
	Methods 
end rule

rule javaRef2Self VarIds [repeat id]
    replace $ [reference]
	Id [id] Comp [repeat component]
    where not
	Id [= each VarIds]
    where 
	Id [is_class_var]
    by
	'self. Id Comp
end rule

rule javaStaticVar2Self VarIds [repeat id]
    replace $ [reference]
	Id [id] Comp [repeat component]
    where not
	Id [= each VarIds]
    where 
	Id [is_staticclass_var]
    by
	'self.__class__. Id Comp
end rule


% calls to this() super() 
function constructorTransforms
    replace [program]
	P [program]
    by
	P [transformThisInConstructor] [transformSuperInConstructors]
end function	

rule transformSuperInConstructors
    replace $ [constructor_declaration]
	Constructor [constructor_declaration]
    by
	Constructor [addSuperInConstructorIfAbsent Constructor]
		    [transformSuperInConstructor Constructor]
		    [removeSuperInConstructor]
end rule

rule addSuperInConstructorIfAbsent Constructor [constructor_declaration] 
    replace $ [constructor_body]
	'{ Decls [repeat declaration_or_statement] '}
    where not
	Decls [?transformSuperInConstructor Constructor]
    by
	'{
	'super '( ') ';
	Decls '}
end rule


rule transformSuperInConstructor Constructor [constructor_declaration] 
    replace $ [declaration_or_statement]
	'super MethArg [method_argument] ';
    import JClassOrigin [class_declaration]
    deconstruct JClassOrigin
	_ [repeat modifier] 'class _ [class_name] 
	Extends [opt extends_clause] 
	_ [opt implements_clause] _ [class_body]
    construct ExtendClasses [repeat reference]
	_ [^ Extends]
    construct Parents [repeat reference]
	ExtendClasses [removeNonIdRef]
    deconstruct * ExtendClasses 
	ParentName [id]
    construct Args [repeat expression]
	_ [^ MethArg]
    construct VarIds [repeat id]
	_ [^ Args]
    construct TypeString [id]
	_ [buildVarsTypeList4Constructor Constructor each VarIds]
    construct XNewName [id]
	_ [+ ParentName] [+ "_body"] [+ TypeString]
    by
	ParentName '. XNewName MethArg ';
end rule

rule removeSuperInConstructor 
    replace [repeat declaration_or_statement]
	'super MethArg [method_argument] ';
	Decls [repeat declaration_or_statement]
    by
	Decls
end rule

rule transformThisInConstructor 
    replace $ [constructor_declaration]
	Constructor [constructor_declaration]
    by
	Constructor [transformThisStatement Constructor]
end rule


rule transformThisStatement Constructor [constructor_declaration] 
    replace $ [declaration_or_statement]
	'this MethArg [method_argument] ';
    deconstruct Constructor
	_ [repeat modifier] ClassName [id] _ [opt generic_parameter]
	'( _ [list formal_parameter] ')  _ [opt throws] _ [constructor_body]
    construct Args [repeat expression]
	_ [^ MethArg]
    construct VarIds [repeat id]
	_ [^ Args]
    construct TypeString [id]
	_ [buildVarsTypeList4Constructor Constructor each VarIds]
    construct XNewName [id]
	_ [+ ClassName] [+ "_body"] [+ TypeString]
    by
	XNewName MethArg ';
end rule

function buildVarsTypeList4Constructor Constructor [constructor_declaration] VarId [id]
    replace [id]
	Ids [id]
    construct Tmp1 [constructor_declaration]
	Constructor [findVarTypeInConstructorParams VarId]
    import TypeName [id]
    by
	Ids [+ TypeName]
end function

rule findVarTypeInConstructorParams VarId [id]
    replace $ [formal_parameter]
	FP [formal_parameter]
    deconstruct FP
	_ [opt 'final] _ [type_specifier] _ [opt var_arg_specifier] 
	Id  [id] _ [opt generic_parameter] _ [repeat dimension]
    where 
	Id [= VarId]
    construct TypeName [id]
	_ [buildParameterTypeList FP]
    export TypeName
    by
	FP
end rule



rule transformConstructorHeader
    replace [repeat class_body_declaration]
 	Constructor [constructor_declaration]
 	Methods [repeat class_body_declaration]
    deconstruct Constructor
	JModifiers [repeat modifier] 
	JClassName [id] _ [opt generic_parameter] '( Parameters [list formal_parameter] ') 
	_ [opt throws] Body [block]

    construct Params [repeat variable_name]
	_ [^ Parameters]
    construct FirstVar [list variable_name]
	'self
    construct VarList [list variable_name] 
	FirstVar [, each Params]
    construct VarListForCall [list variable_name] 
	_ [, each Params]


    construct ParamsString [id]
	_ [buildParameterTypeList each Parameters]
    construct XNew [id]
	_ [+ JClassName] 
    construct XNewBody [id]
	_ [+ JClassName] [+ "_body"]
    construct XNewName [id]
	XNew [+ ParamsString]
    construct XNewBodyName [id]
	XNewBody [+ ParamsString]

    construct ConstrBody [repeat declaration_or_statement]
	tmp = JClassName '() ';
	tmp.XNewBodyName '( VarListForCall ') ';
	return tmp ';
    deconstruct Body
	'{ RealBody [repeat declaration_or_statement] '}
    by
	'@classmethod
	'def XNewName '( VarList ') ': ConstrBody
	'def XNewBodyName '( VarList ') ': RealBody
	Methods
end rule

rule transformClassHeaderNoExtend
    replace [class_declaration]
	_ [repeat modifier] 'class Name [id] _ [opt generic_parameter] 
	_ [opt implements_clause]
	'{ BodyDecls [repeat class_body_declaration] '} _ [opt ';]
    by
	'class Name ': BodyDecls
end rule

rule transformClassHeaderExtend
    replace [class_declaration]
	_ [repeat modifier] 'class Name [id] _ [opt generic_parameter] 
	Extend [opt extends_clause] 
	_ [opt implements_clause]
	'{ BodyDecls [repeat class_body_declaration] '} _ [opt ';]
    deconstruct Extend 
	'extends Types [list type_name+]
    deconstruct Types
	Type [type_name] 
    by
	'class Name '( Type ') ': BodyDecls
end rule

rule tranformThisVarRefs
    replace [reference]
	'this '. Id [id] Comps [repeat component]	
    where not
	Id [is_staticclass_var]
    by
	'self. Id Comps
end rule

rule tranformThisClassVarRefs
    replace [reference]
	'this '. Id [id] Comps [repeat component]	
    where 
	Id [is_staticclass_var]
    by
	'self.__class__. Id Comps
end rule

function removeEmptyAttributes
    replace * [repeat class_body_declaration]
	Decls [repeat class_body_declaration]
    construct NewDecls [repeat class_body_declaration]
	_ [addNonEmptyAttribute each Decls]
    by
	NewDecls
end function

function addNonEmptyAttribute Decl [class_body_declaration]
    deconstruct not Decl
	_ [repeat modifier] _ [type_specifier] Name [id] _ [opt generic_parameter] '; 
    where not 
	Decl [is_nonstatic_decl]
    replace [repeat class_body_declaration]
	Decls [repeat class_body_declaration]
    by
	Decls [. Decl]
end function

rule transformMethodDecl
    replace [method_declaration]
	Modi [repeat modifier] _ [type_specifier]
	Id [id] '( Parameters [list formal_parameter] ') _ [repeat dimension] _ [opt throws] 
	'{ Decls [repeat declaration_or_statement] '}
    construct Params [repeat variable_name]
	_ [^ Parameters]
    construct FirstVar [list variable_name]
	'self
    construct VarList [list variable_name] 
	FirstVar [, each Params]
    construct Decors [repeat decorator]
	_ [makeStaticDecorator Modi]
	  [makeSynchDecorator Modi]
    by
	Decors 'def Id '( VarList ') ': Decls
end rule 

function makeStaticDecorator Modi [repeat modifier]
    replace [repeat decorator]
	Decors [repeat decorator]
    where 
	Modi [is_static]
    construct Decor [decorator]
	'@ 'classmethod
    by
	Decors [. Decor]
end function

function makeSynchDecorator Modi [repeat modifier]
    replace [repeat decorator]
	Decors [repeat decorator]
    where 
	Modi [is_synchronized]
    construct Decor [decorator]
	'@ 'synchronize
    by
	Decors [. Decor]
end function

function exportClass
    replace * [class_declaration]
	CD [class_declaration]
    export JClassOrigin [class_declaration]
	CD
    by
	CD
end function

function buildClassVarInits Field [field_declaration]
    deconstruct Field
	_ [repeat modifier] _ [type_specifier] Id [id] _ [opt generic_parameter] _ [repeat dimension]
	'= Exp [conditional_expression] ';
    where 
	Id [is_class_var]
    construct Ass [declaration_or_statement]
	'self '.                 Id '= Exp 
    replace [repeat declaration_or_statement]
	Decls [repeat declaration_or_statement]
    by
	Decls [. Ass]
end function

function addInitMethod
    import JClassOrigin [class_declaration]
    construct Fields [repeat field_declaration]
	_ [^ JClassOrigin]
    construct Asses [repeat declaration_or_statement]
	_ [buildClassVarInits each Fields]
    construct AssesLen [number]
	_ [length Asses]
    where 
	AssesLen [> 0]
    construct InitMethod [class_body_declaration]
	'def __init__ '( self ') ': 
	Asses
    replace * [repeat class_body_declaration]
	Decls [repeat class_body_declaration]
    by
	Decls [. InitMethod]
end function


function is_nonstatic_decl
    match * [field_declaration]
	    _ [repeat modifier] _ [type_specifier] Name [id] '= _ [variable_initializer]';
    where 
	Name [is_class_var]
end function

function is_class_var
    import JClassOrigin [class_declaration]
    construct Fields [repeat field_declaration]
	_ [^ JClassOrigin]
    match * [id]
	Id [id]
    where 
	Fields [is_nonstatic_var Id]
end function

function is_staticclass_var
    import JClassOrigin [class_declaration]
    construct Fields [repeat field_declaration]
	_ [^ JClassOrigin]
    match * [id]
	Id [id]
    where 
	Fields [is_static_var Id]
end function


function has_var Id [id]
    match * [variable_name]
	VarId [id] _ [opt generic_parameter] _ [repeat dimension]
    where 
	VarId [= Id]
end function

function is_static_var Id [id]
    match * [field_declaration]
	Field [field_declaration]
    where all
	Field [has_var Id] [is_static]
end function

function is_nonstatic_var Id [id]
    match * [field_declaration]
	Field [field_declaration]
    where
	Field [has_var Id] 
    where not
	Field [is_static]
end function


%%variable
function transformVariables
    replace [program]
	P [program]
    by
	P [splitVarDeclarations] [sortAllVarsUp]
	  [splitLocalVarDeclarations]
	  [removeEmptyLocalVarDeclarations]
	  [localVarDecl2Assignment]
end function

%split field declaration into one var per line
rule splitVarDeclarations
    replace [repeat class_body_declaration]
	Modr [repeat modifier] TypSpec [type_specifier] VarDecl [variable_declarator] ', VarDeclRest [list variable_declarator+] '; 
	Rest [repeat class_body_declaration]
    by
	Modr TypSpec VarDecl ';
	Modr TypSpec VarDeclRest ';
	Rest 
end rule

rule splitLocalVarDeclarations
    replace [repeat declaration_or_statement]
	Modr [repeat modifier] TypSpec [type_specifier] VarDecl [variable_declarator] ', VarDeclRest [list variable_declarator+] '; 
	Rest [repeat declaration_or_statement]
    by
	Modr TypSpec VarDecl ';
	Modr TypSpec VarDeclRest ';
	Rest
end rule

%remove empty local vars declarations
rule removeEmptyLocalVarDeclarations
    replace [repeat declaration_or_statement]
	_ [repeat modifier] _ [type_specifier] _ [id] _ [opt generic_parameter] '; 
	Rest [repeat declaration_or_statement]
    by
	Rest
end rule



%move all fields to the top of class declaration
rule sortAllVarsUp
    replace [repeat class_body_declaration]
	NonVar [class_body_declaration_wo_field]
	Var [field_declaration]
	Rest [repeat class_body_declaration]
    by
	Var NonVar Rest
end rule

% local variable declaration to simple assignment
rule localVarDecl2Assignment
    replace $ [declaration_or_statement]
	Decl [declaration_or_statement] 
    deconstruct Decl
	_ [repeat modifier] _ [type_specifier] _ [variable_declarators] ';
    by
	Decl [localVarDecl2AssignmentExp] %[localVarDecl2AssignmentArray] 
end rule


function localVarDecl2AssignmentExp
    replace [declaration_or_statement]
	_ [repeat modifier] _ [type_specifier] Id [id] _ [opt generic_parameter] _ [repeat dimension]
	'= Exp [conditional_expression] ';
    construct Ass [assignment_expression]
	Id '= Exp 
    by
	Ass ';
end function


%%preparation
%remove cast
rule removeCastExpressions
    replace [unary_expression]
        '( _ [type_specifier] ') Unary [unary_expression]
    by
        Unary
end rule

%--i to i--
rule javaPreUnary2PostUnary
    replace [unary_expression]
	Op [pre_inc_dec] Una [primary]
    by
	Una Op
end rule


%expand java switch for each label having its own block
rule expandJavaSwitch
    replace $ [switch_block]
	Block [switch_block]
    by
	Block [removeSwitchBreak]
	      [expandJavaSwitchAlts]
end rule

rule removeSwitchBreak
    replace [repeat declaration_or_statement]
	'break '; Decls [repeat declaration_or_statement]
    by
	Decls
end rule

function expandJavaSwitchAlts
    replace * [repeat switch_alternative]
	Alts [repeat switch_alternative]
    deconstruct * Alts
	LastAlt [switch_alternative]
    construct AltsLen [number]
	_ [length Alts] [- 1]
    construct FirstAlts [repeat switch_alternative]
	Alts [head AltsLen]
    construct LastAltConverted [switch_alternative]
	LastAlt [processJavaSwitchAltEmptyEntry] [processJavaSwitchAltEntry] 
    by
	FirstAlts [expandJavaSwitchAlts] [. LastAltConverted]
end function

function processJavaSwitchAltEntry
    replace [switch_alternative]
	Label [switch_label] Decls [repeat declaration_or_statement+] 
    export SwitchAltDecls [repeat declaration_or_statement+]
	Decls 
    by
	Label Decls 
end function

function processJavaSwitchAltEmptyEntry
    replace [switch_alternative]
	Label [switch_label] 
    import SwitchAltDecls [repeat declaration_or_statement+]
    by
	Label SwitchAltDecls 
end function

%boolean and null
rule transformLiterals
    replace $ [literal]
	L [literal]
    by
	L [transformBooleanTrue] [transformBooleanFalse] [transformLiteralNull]
end rule

rule transformBooleanTrue
    replace [literal]
	'true
    by
	'True
end rule

rule transformBooleanFalse
    replace [literal]
	'false
    by
	'False 
end rule

rule transformLiteralNull
    replace [literal]
	'null
    by
	'None
end rule

function transformBooleanOps
    replace [program]
	P [program]
    by
	P [transformBooleanOr] [transformBooleanAnd] [transformBooleanNot]
end function

rule transformBooleanOr
    replace [or_conditional_and_expression]
	'|| CE [conditional_and_expression]
    by
	'or CE
end rule

rule transformBooleanAnd
    replace [and_inclusive_or_expression]
	'&& CE [inclusive_or_expression]
    by
	'and CE
end rule

rule transformBooleanNot
    replace [unary_op]
	'!
    by
	'not
end rule

rule transformConditionalChoice
    replace [declaration_or_statement]
	Ref [reference] '= Condor [conditional_or_expression] 
	'? Expr [conditional_expression] ': Cond [conditional_expression] ';
    by
	'if '( Condor ') Ref '= Expr '; 'else Ref '= Cond
end rule


%%expression
function expressionTransforms
    replace [program]
	P [program]
    by
	P [arrayDeclReplace]
	  [arrayDeclAssReplace]
	  [arrayInitReplace]

	  [newClassExprNoParam]
	  [newClassExprParams]

	  [transformWhile]
	  [transformDoWhile]

	  [transformIf]
	  [transformElseIfToElif]

	  [transformSwitchWith1DefaultToIfs]
	  [transformEmptySwitch]
	  [transformSwitchToIfs]

	  [transformFinally]
	  [transformCatchTyped]
	  [transformTry]

	  [transformThrow]

	  [transformForeachToForin]
end function

function is_static 
    match * [modifier]
	'static
end function

function is_synchronized
    match * [modifier]
	'synchronized
end function

rule transformAttrDeclToAss
    replace [class_body_declaration]
	_ [repeat modifier] _ [type_specifier] Id [id] _ [opt generic_parameter] _ [repeat dimension]
	'= Exp [conditional_expression] ';
    construct Ass [assignment_expression]
	Id '= Exp 
    by
	Ass ';
end rule

function addMainIf
    replace * [package_declaration]
	      PH [opt package_header]
	      ID [repeat import_declaration] 
	      TD [repeat type_declaration]
    deconstruct TD
	_ [repeat modifier] 'class Name [id]
	_ [opt extends_clause] _ [opt implements_clause]
	Body [class_body]
    where 
	Body [?hasMainMethod]
    construct If [if_stmt]
	if __name__ == ''__main__' : 
	'import sys ';
	Name . main ( sys . argv ) ';
    by
	PH ID TD If
end function 

rule hasMainMethod
    replace $ [method_declaration]
	Decl [method_declaration]
    deconstruct Decl
	_ [repeat modifier] _ [type_specifier]  
	'main '( _ [list formal_parameter] ') _ [repeat dimension]    
	_ [opt throws] _ [method_body]
    by
	Decl
end rule

function buildParameterTypeList Parameter [formal_parameter]
    replace [id]
	Params [id] 
    construct TypeName [id]
	_ [extractPrimitiveTypeName Parameter]
	  [extractReferenceTypeName Parameter]
    by
	Params [_ TypeName]
end function

function extractPrimitiveTypeName Parameter [formal_parameter]
    replace [id]
	_ [id]
    construct Primitive [repeat primitive_type]
	_ [^ Parameter]
    deconstruct Primitive
	First [primitive_type] _ [repeat primitive_type]
    construct TypeName [id]
	_ [quote First]
    by
	_ [+ TypeName]
end function

function extractReferenceTypeName Parameter [formal_parameter]
    replace [id]
	_ [id]
    construct References [repeat reference]
	_ [^ Parameter]
    deconstruct References
	_ [id]  _ [repeat component]
    construct Ids [repeat id]
	_ [^ References]
    deconstruct * Ids 
	TypeName [id]
    by
	_ [+ TypeName]
end function

rule removeNonIdRef
    replace [repeat reference]
	First [reference] Rest [repeat reference]
    deconstruct First
	Prima [primitive_type] _ [repeat component]
    by
	Rest
end rule

rule systemOutPrintln2Print
    replace $ [declaration_or_statement]
	'System.out.println '( Args [list argument] ') ';
    by
	'print Args [expandPrintlnParams] 
end rule

rule systemOutPrint2Print
    replace $ [declaration_or_statement]
	'System.out '. Id [id] '( Args [list argument] ') ';
    where Id
	[= 'print]
    by
	'print Args [expandPrintlnParams] ', 
end rule

function expandPrintlnParams
    replace [list argument]
	Mores [list argument]
    export PutsUnas [repeat unary_expression]
	_
    construct Dumb [list argument]
	Mores [collectUnaries]
    import PutsUnas

    construct Len [number]
	_ [length PutsUnas]
    where 
	Len [> 1]

    construct Args [list argument]
	_ [addUnaryToArgsList each PutsUnas]
    by
	Args
end function

rule collectUnaries
    skipping [unary_expression]
    replace $ [unary_expression]
	Una [unary_expression]
    import PutsUnas [repeat unary_expression]
    construct NewPutsUnas [repeat unary_expression]
	PutsUnas [. Una]
    export PutsUnas
	NewPutsUnas
    by
	Una
end rule

function addUnaryToArgsList Una [unary_expression]
    replace [list argument]
	Args [list argument]
    construct Arg [argument]
	Una
    by
	Args [, Arg]
end function


rule arrayDeclReplace
    replace [assignment_expression]
	Ref [reference] '= 'new _ [type_name] '[ Size [number] '] 
    by
	Ref '= 'range '( Size ') 
end rule

rule arrayDeclAssReplace
    replace [assignment_expression]
	Ref [reference] '= 'new _ [array_type_specifier]
	'{ Vars [list variable_initializer] _ [opt ',] '}
    construct ArrInit [array_initializer]
	'[ Vars ']
    by
	Ref '=  ArrInit 
end rule

rule arrayInitReplace
    replace [array_initializer]
	'{ Vars [list variable_initializer] _ [opt ',] '}
    by
	'[ Vars ']
end rule


rule newClassExprNoParam
    replace [assignment_expression]
	Ref [reference] '= 'new JClassName [reference] '( ') _ [opt class_body]
    construct TmpIds [repeat id]
	_ [^ JClassName]
    deconstruct * TmpIds
	ClassName [id]
    construct Comp [component]
	'. ClassName
    construct Comp2 [component]
	'( ')
    deconstruct JClassName
	Id [id] Comps [repeat component]
    by
	Ref '= Id Comps [. Comp] [. Comp2]
end rule

rule newClassExprParams
    replace [assignment_expression]
	Ref [reference] '= 'new JClassName [reference] '( Args [list argument] ') _ [opt class_body]
    construct TmpIds [repeat id]
	_ [^ JClassName]
    deconstruct * TmpIds
	ClassNameTmp [id]
    construct ClassName [id]
	ClassNameTmp [_ 'unknown]
    construct Comp [component]
	'. ClassName 
    construct Comp2 [component]
	'( Args ')
    deconstruct JClassName
	Id [id] Comps [repeat component]
    by
	Ref '= Id Comps [. Comp] [. Comp2]
end rule

rule transformWhile
    replace [statement]
	'while '( Expr [expression] ') Stmt [statement]
    construct NewDecls [repeat declaration_or_statement]
	_ [statementBlockToDecls Stmt]
	  [statementOneDeclToDecls Stmt] 
    by
	'while Expr ': NewDecls
end rule

rule transformIf
    replace [statement]
	'if '( Expr [expression] ') Stmt [statement]
	Else [opt else_clause]
    construct PyElse [repeat py_else_clause]
	_ [addElseWithBlock Else] [addElseSingle Else]
    construct NewDecls [repeat declaration_or_statement]
	_ [statementBlockToDecls Stmt]
	  [statementOneDeclToDecls Stmt] 
    by
	'if Expr ': NewDecls PyElse
end rule

function addElseWithBlock Else [opt else_clause]
    replace [repeat py_else_clause]
	Elses [repeat py_else_clause]
    deconstruct Else
	'else '{ Decls [repeat declaration_or_statement] '}
    construct PyElse [py_else_clause]
	'else ': Decls
    by
	Elses [. PyElse]
end function

function addElseSingle Else [opt else_clause]
    replace [repeat py_else_clause]
	Elses [repeat py_else_clause]
    deconstruct not Elses
	_ [py_else_clause] _ [repeat py_else_clause]
    deconstruct Else
	'else Stmt [statement]
    construct PyElse [py_else_clause]
	'else ': Stmt
    by
	Elses [. PyElse]
end function

rule transformElseIfToElif
    replace [if_stmt]
	'if Expr [expression] ': Decls [repeat declaration_or_statement]
	Elif [repeat elif_clause]
	'else ': %another if
	'if Expr2 [expression] ': Decls2 [repeat declaration_or_statement]
	Else2 [repeat py_else_clause]
    construct ElifNew [elif_clause]
	'elif Expr2 ': Decls2
    by
	'if Expr ': Decls
	Elif [. ElifNew]
	Else2
end rule

rule transformDoWhile
    replace [statement]
	'do Stmt [statement] 
	'while '( Expr [assignment_expression] ') ';
    construct TmpVar_ [id]	
	'tmp_do_var
    construct TmpVar [id]
	TmpVar_ [!]
    construct Ass1 [declaration_or_statement]
	TmpVar = Expr ';
    construct NewDecls [repeat declaration_or_statement]
	_ [statementBlockToDecls Stmt]
	  [statementOneDeclToDecls Stmt] 
    by
	TmpVar = True '; 
	'while TmpVar ': NewDecls [. Ass1]
end rule

rule transformSwitchToIfs
    replace [statement]
	'switch '( Expr [assignment_expression] ') '{ Alts [repeat switch_alternative] '}

    construct TmpVar_ [id]	
	'tmp_switch_var
    construct TmpVar [id]
	TmpVar_ [!]

    construct DumbIf [if_stmt]
	'if 1 ': ';
    construct IfDecl [declaration_or_statement]
	DumbIf [addElifsForSwitch TmpVar each Alts] 
	       [addElseForSwitch TmpVar each Alts]
	       [transformFirstElifToIf]

    construct Decls [repeat declaration_or_statement]
	TmpVar = Expr '; 

    by
	Decls [. IfDecl]
end rule

function addElifsForSwitch VarId [id] Alt [switch_alternative]
    deconstruct not Alt
	'default ': _ [repeat declaration_or_statement]
    deconstruct Alt 
	'case Literal [literal] ': AltDecls [repeat declaration_or_statement]
    construct TmpExpr [expression]
	VarId '== Literal
    construct Elif [elif_clause]
	'elif TmpExpr ': AltDecls
    replace [if_stmt]
	'if Expr [expression] ': Decls [repeat declaration_or_statement]
	Elifs [repeat elif_clause]
	Elses [repeat py_else_clause]
    by
	'if Expr ': Decls 
	Elifs [. Elif]
	Elses 
end function

function addElseForSwitch VarId [id] Alt [switch_alternative]
    deconstruct Alt
	'default ': AltDecls [repeat declaration_or_statement]
    construct Else [repeat py_else_clause]
	'else ': AltDecls
    replace [if_stmt]
	'if Expr [expression] ': Decls [repeat declaration_or_statement]
	Elifs [repeat elif_clause]
	_ [repeat py_else_clause]
    by
	'if Expr ': Decls 
	Elifs 
	Else
end function


function transformFirstElifToIf
    replace [if_stmt]
	'if _ [expression] ': _ [repeat declaration_or_statement]
	ElifsFull [repeat elif_clause]
	Else [repeat py_else_clause]
    deconstruct ElifsFull
	'elif Expr [expression] ': Decls [repeat declaration_or_statement]
	RestElifs [repeat elif_clause]
    by
	'if Expr ': Decls 
	RestElifs
	Else
end function


rule transformSwitchWith1DefaultToIfs
    replace [statement]
	'switch '( Expr [assignment_expression] ') 
	'{ 
	'default ':
	Decls [repeat declaration_or_statement] 
	'}
    by
	Decls
end rule

rule transformEmptySwitch
    replace [statement]
	'switch '( Expr [assignment_expression] ') 
	'{ 
	'}
    by
	';
end rule

rule transformFinally
    replace [finally_clause]
	'finally '{ Decls [repeat declaration_or_statement] '}
    by
	'finally ': Decls
end rule

rule transformCatchTyped
    replace [catch_clause]
	'catch '( Type [type_specifier] VarName [variable_name] ') 
	'{ Decls [repeat declaration_or_statement] '}
    by
	'except Type ', VarName ': 
	Decls
end rule

rule transformCatchEmpty
    replace [catch_clause]
	'catch '( 'Exception VarName [variable_name] ') 
	'{ Decls [repeat declaration_or_statement] '}
    by
	'except ': 
	Decls
end rule

rule transformTry
    replace [try_statement]
	'try '{ Decls [repeat declaration_or_statement] '}
	Catches [repeat catch_clause]
	Finally [opt finally_clause]
    by
	'try ': Decls Catches Finally
end rule

rule transformThrow
    replace [throw_statement]
	'throw Expr [expression] ';
    by
	'raise Expr ';           
end rule

rule transformForeachToForin
    replace [declaration_or_statement]
	'for '( 
	%for_in_init
	_ [repeat modifier] _ [type_specifier] Id [id] _ [opt generic_parameter] _ [repeat dimension]
	': Expr [expression] ')
		Stmt [statement]        
    construct NewDecls [repeat declaration_or_statement]
	_ [statementBlockToDecls Stmt]
	  [statementOneDeclToDecls Stmt] 
    by
	'for Id 'in Expr ':
	NewDecls
end rule

rule transformForToWhile
    replace [statement]
	'for '( ForInit [for_init] 
	ForExpr [opt expression] '; 
	ForUpd [list expression] ') 
	Stmt [statement] 

    construct NewInits [repeat declaration_or_statement]
	_ [transformForInitStmt1 ForInit] 
	  [transformForInitStmt2 ForInit]

    construct NewExpr [expression]
	'True

    construct NewDecls [repeat declaration_or_statement]
	_ [statementBlockToDecls Stmt]
	  [statementOneDeclToDecls Stmt] 

    construct ForUpdTmp [repeat expression]
	_ [. each ForUpd]
    construct NewUpdates [repeat declaration_or_statement]
	_ [expressionsToDecls ForUpdTmp] 
	  
    by
	NewInits
	'while '( NewExpr [addForExpr ForExpr] ') '{ NewDecls [. NewUpdates] '}
end rule 

function addForExpr ForExpr [opt expression]
    deconstruct ForExpr
	Expr [expression]
    replace [expression]
	_ [expression]
    by
	Expr
end function


%list expression
function transformForInitStmt1 ForInit [for_init]
    deconstruct ForInit
	Exprs [list expression] ';
    replace [repeat declaration_or_statement]
	_ [repeat declaration_or_statement]
    construct ExprSeq [repeat expression]
	_ [. each Exprs]
    by
	_ [expressionsToDecls ExprSeq] 
end function

%local_variable_declaration
function transformForInitStmt2 ForInit [for_init]
    deconstruct ForInit
	LocalDecl [local_variable_declaration] 
    construct TmpDecls [repeat declaration_or_statement]
	LocalDecl
    construct TmpDecls2 [repeat declaration_or_statement]
	TmpDecls [splitLocalVarDeclarations] [localVarDecl2Assignment]
    deconstruct TmpDecls2
	Decl1 [declaration_or_statement] _ [repeat declaration_or_statement]
    replace [repeat declaration_or_statement]
	_ [repeat declaration_or_statement]
    by
	TmpDecls2
end function

function expressionsToDecls Expressions [repeat expression]
    replace [repeat declaration_or_statement]
	_  [repeat declaration_or_statement]
    deconstruct Expressions
	Expr [expression] Exprs [repeat expression]
    construct Decls [repeat declaration_or_statement]
	Expr
    by
	Decls  [addExprToDecls each Exprs]
end function

function addExprToDecls Expr [expression]
    replace [repeat declaration_or_statement]
	Decls [repeat declaration_or_statement]
    construct Decl [declaration_or_statement]
	Expr
    by
	Decls [. Decl]
end function

%one decl statetement to decls
function statementOneDeclToDecls Stmt [statement]
    replace [repeat declaration_or_statement]
	_ [repeat declaration_or_statement]
    deconstruct not Stmt
	_ [block]
    construct Decl [declaration_or_statement]
	Stmt
    by
	Decl
end function
%one decl statetement to decls
function statementBlockToDecls Stmt [statement]
    replace [repeat declaration_or_statement]
	_ [repeat declaration_or_statement]
    deconstruct Stmt
	'{ Decls [repeat declaration_or_statement] '}
    by
	Decls
end function


%%finals
rule transformEmptyArray
    replace [variable_name]
	Id [id] '[ ']
    by
	Id
end rule

function transformSinglePostfixes
    replace [program]
	P [program]
    by
	P [transformSingleIncr]
	  [transformSingleDecr]
	  [transformSingleIncr0]
	  [transformSingleDecr0]
	  [transformIncr]
	  [transformDecr]
end function

rule transformSingleIncr0
    replace [declaration_or_statement]
	Prima [reference] '++ 
    by
	Prima '+= '1
end rule

rule transformSingleDecr0
    replace [declaration_or_statement]
	Prima [reference] '-- 
    by
	Prima '-= '1
end rule

rule transformSingleIncr
    replace [declaration_or_statement]
	Prima [reference] '++ ';
    by
	Prima '+= '1
end rule

rule transformSingleDecr
    replace [declaration_or_statement]
	Prima [reference] '-- ';
    by
	Prima '-= '1
end rule

rule transformIncr
    replace [unary_expression]
	Prima [reference] '++ 
    by
	'( Prima '+ '1 ')
end rule

rule transformDecr
    replace [declaration_or_statement]
	Prima [reference] '-- 
    by
	'( Prima '- '1 ')
end rule

