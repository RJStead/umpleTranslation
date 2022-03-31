include "Python.Grm"


%--------------------%
%     Top level      %
%--------------------%
function main
    replace [program] 
	P [program]
    by
	P [replaceAllClasses]
end function

define program
    [repeat class_declaration]
end define

%--------------------%
%     General        %
%--------------------%
function translateParams PreviousParam [method_parameter]
    replace [list id]
        SequenceSoFar [list id]
    deconstruct PreviousParam
        _ [id] paramName [id]
    by
        SequenceSoFar [, paramName]
end function


function addMemberVariable MemberVariable [member_variable_declaration]
    replace [repeat id]
        SequenceSoFar [repeat id]
    deconstruct MemberVariable
        _[opt acess_modifier] decl [variable_declaration]
    deconstruct decl
        _ [id] memberName [id]';
    by
        SequenceSoFar [. memberName]
end function

rule replaceMemberVariableNames memberVariables [repeat id]
    replace [variable_name]
        name [variable_name]
    deconstruct name 
        id [id]
    where 
        memberVariables [contains id]
    by
        'self._ id
end rule

rule contains Object [id]
    match [id]
        Object
end rule

function replaceAllClasses
    replace [program]
        class [repeat class_declaration]
    by
        class
            [replaceConcreteClasses]
            [replaceInterfaces]
end function

function replaceConcreteClasses
    replace [repeat class_declaration]
        class [concrete_class_declaration]
    by
        class
            [replaceConcreteClassesNoInheritance]
            [replaceConcreteClassesSingleInheritance]
            [replaceConcreteClassesDoubleInheritance]
end function

function replaceInterfaces
    replace [repeat class_declaration]
        interface [interface_declaration]
    by
        interface
            [replaceInterfacesNoInheritance]
            [replaceInterfacesInheritance]
end function

function addImportStatement a [id]
    replace [repeat import_statement]
        _ [repeat import_statement]
    by
        'from a 'import a
end function

%--------------------%
%Classes & Interfaces%
%--------------------%
rule replaceConcreteClassesNoInheritance
    replace [concrete_class_declaration]
        _ [acess_modifier] 'class className [id] '{ classBody [class_body_decl] '} 
    by
    'class className ':  classBody [replaceClassBody] [replaceClassBodyNoConstructor]
end rule

rule replaceConcreteClassesSingleInheritance
    replace [concrete_class_declaration]
        _ [acess_modifier] 'class className [id] _ [inheritanceStatement] inList [list id] '{ classBody [class_body_decl] '}
    construct importBlock [repeat import_statement]
        _ [addImportStatement each inList]
    by
        importBlock 'class className '( inList ') ':  classBody [replaceClassBody] [replaceClassBodyNoConstructor]
end rule

rule replaceConcreteClassesDoubleInheritance
    replace [concrete_class_declaration]
        _ [acess_modifier] 'class className [id] _ [inheritanceStatement] inListI [list id] _ [inheritanceStatement] inListII [list id] '{ classBody [class_body_decl] '}
    construct firstImportBlock [repeat import_statement]
        _ [addImportStatement each inListI]
    construct secondImportBlock [repeat import_statement]
        _ [addImportStatement each inListII]
    by
        firstImportBlock secondImportBlock 'class className '( inListI ', inListII ') ':  classBody [replaceClassBody] [replaceClassBodyNoConstructor]
end rule

rule replaceInterfacesNoInheritance
    replace [interface_declaration]
        _ [acess_modifier] 'interface className [id] '{ classBody [class_body_decl] '} 
    by
        'from 'abc 'import 'ABC, 'abstractmethod 'class className '(ABC):  classBody [replaceClassBody] [replaceClassBodyNoConstructor]
end rule

rule replaceInterfacesInheritance
    replace [interface_declaration]
        _ [acess_modifier] 'interface className [id] _ [inheritanceStatement] inList [list id] '{ classBody [class_body_decl] '} 
    construct importBlock [repeat import_statement]
        _ [addImportStatement each inList]
    by
        'from 'abc 'import 'ABC, 'abstractmethod importBlock 'class className '(ABC, inList '):  classBody [replaceClassBody] [replaceClassBodyNoConstructor]
end rule

function replaceClassBody
    replace [class_body_decl]
        declarations [repeat member_variable_declaration] oldConstructor [constructor] methods [repeat method_declaration]
    construct memberVariables [repeat id]
        _ [addMemberVariable each declarations]
    construct newContructor [constructor]
        oldConstructor [replaceMemberVariableNames memberVariables] [replaceContructor] [replaceContructorNoArgs]
    by
        newContructor methods  [replaceAllMethods memberVariables]
end function

function replaceClassBodyNoConstructor
    replace [class_body_decl]
        declarations [repeat member_variable_declaration] methods [repeat method_declaration]
    construct memberVariables [repeat id]
        _ [addMemberVariable each declarations]
    by
        '@abstractmethod 'def __init__(self): 'pass methods [replaceAllMethods memberVariables]
end function

function replaceContructor
    replace [constructor]
         mod [acess_modifier] className [id]'( params [list method_parameter +] ') '{ statements [repeat statement]  '}
    construct newParams [list id]
    by
        'def '__init__(self, newParams [translateParams each params]'):  statements [replaceStatements]
end function

function replaceContructorNoArgs
    replace [constructor]
         mod [acess_modifier] className [id]'() '{ statements [repeat statement]  '}
    by
        'def '__init__(self):  statements [replaceStatements]
end function



%--------------------%
%     Methods        %
%--------------------%

function replaceAllMethods memberVariables [repeat id]
    replace [repeat method_declaration]
        methods [repeat method_declaration]
    by
        methods 
            [replaceMemberVariableNames memberVariables] 
            [replaceToString]
            [replaceAbstractMethod]
            [replaceAbstractMethodNoArgs]
            [replaceConcreteMethod] 
            [replaceConcreteMethodNoArgs]
            
end function

rule replaceConcreteMethod
    replace [concrete_method_declaration]
        _[acess_modifier] _[id] methodName [id]'( params [list method_parameter +] ') '{ statements [repeat statement] '}
    construct newParams [list id]
    by
        'def methodName '(self, newParams [translateParams each params] '):  statements [replaceStatements]
end rule

rule replaceConcreteMethodNoArgs
    replace [concrete_method_declaration]
        _[acess_modifier] _[id] methodName [id]'() '{ statements [repeat statement] '}
    by
        'def methodName '(self):  statements [replaceStatements]
end rule

rule replaceAbstractMethod
    replace [abstract_method_declaration]
        _[acess_modifier] _[id] methodName [id] '( params [list method_parameter +] ');
    construct newParams [list id]
    by
        '@abstractmethod 'def methodName '(self, newParams [translateParams each params] '): 'pass
end rule

rule replaceAbstractMethodNoArgs
    replace [abstract_method_declaration]
        _[acess_modifier] _[id] methodName [id]'();
    by
        '@abstractmethod 'def methodName '(self): 'pass
end rule

rule replaceToString
    replace [method_declaration]
        _[acess_modifier] _[id]  'toString '() '{ statements [repeat statement] '}
    by
        'def '__str__ '(self):  statements [replaceStatements]
end rule

%--------------------%
%     Statements     %
%--------------------%
function replaceStatements
    replace [repeat statement]
        statements [repeat statement]
    by 
        statements 
            [replaceAssignment] 
            [replaceReturn] 
            [replaceNoStateMents] 
            [correctSuperInit]
            [correctSuperFunctions]
            [addSelfToFunctionCalls]
            [replaceDecleration]
            [replaceDeclerationWithAssignment]
            [replaceTrue]
            [replaceFalse]
end function

function replaceNoStateMents
    replace [repeat statement]
        _ [empty]
    by 
        'pass
end function

rule replaceAssignment
    replace [assignment]
        name [variable_name] '= val [value] '; 
    by 
        name '= val
end rule

rule replaceReturn
    replace [stmt_return]
        'return val [value] ';
    by 
        'return val
end rule

rule addSelfToFunctionCalls
    replace [function_call]
        funcName [id]'( values [list value]')
    by
        'self. funcName '( values')
end rule

rule correctSuperInit
    replace [function_call]
        'super( params [list value] ')
    by
        'super().__init__( params ')
end rule

rule correctSuperFunctions
    replace [function_call]
        'super. func [subfunction_call]
    by
        'super(). func
end rule

rule replaceDeclerationWithAssignment
    replace [variable_declaration]
        _ [id] assignment [assignment]
    by 
        assignment
end rule

rule replaceDecleration
    replace [variable_declaration]
        _[id] varName [variable_name]';
    by 
        varName
end rule

rule replaceTrue
    replace [value]
        'true
    by 
        'True
end rule

rule replaceFalse
    replace [value]
        'false
    by 
        'False
end rule