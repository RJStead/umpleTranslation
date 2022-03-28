include "Python.Grm"


%--------------------%
%     Top level      %
%--------------------%
function main
    replace [program] 
	_[repeat import_statement] Classes [repeat class_declaration]
    by
	Classes [replaceClasses]
end function

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
        _ [class_name] memberName [id]';
    by
        SequenceSoFar [. memberName]
end function

function replaceAllMemberVariableNames memberVariables [repeat id]
    replace [any]
        any [any]
    by 
        any [replaceMemberVariableNames memberVariables] [replaceMemberVariableNamesWithThis memberVariables]
end function

rule replaceMemberVariableNames memberVariables [repeat id]
    replace [nested_identifier]
         name [id] attributes [repeat attribute]
    where 
        memberVariables [contains name]
    construct underscore [id]
        '_
    by
        'self '. underscore [+ name] attributes
end rule

rule replaceMemberVariableNamesWithThis memberVariables [repeat id]
    replace [nested_identifier]
        'this '. name [id] attributes [repeat attribute]
    where 
        memberVariables [contains name]
    construct underscore [id]
        '_
    by
        'self '. underscore [+ name] attributes
end rule

rule contains Object [id]
    match [id]
        Object
end rule



%--------------------%
%     Classes        %
%--------------------%
rule replaceClasses
    replace $ [class_declaration]
        _ [acess_modifier] _ [class_type] className [id] '{ classBody [class_body_decl] '} 
    by
    'class className ':  classBody [replaceClassBody]
end rule


function replaceClassBody
    replace [class_body_decl]
        declarations [repeat member_variable_declaration] oldConstructor [constructor] methods [repeat method_declaration]
    construct memberVariables [repeat id]
        _ [addMemberVariable each declarations]
    construct newContructor [constructor]
        oldConstructor [replaceAllMemberVariableNames memberVariables] [replaceContructor] [replaceContructorNoArgs]
    by
        newContructor methods  [replaceAllMethods memberVariables]
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
            [replaceAllMemberVariableNames memberVariables] 
            [replaceToString]
            [replaceMethod] 
            [replaceMethodNoArgs]
            [replaceStaticMethod]
end function

rule replaceMethod
    replace [method_declaration]
        _[acess_modifier] _[class_name] methodName [id]'( params [list method_parameter +] ') '{ statements [repeat statement] '}
    construct newParams [list id]
    by
        'def methodName '(self, newParams [translateParams each params] '):  statements [replaceStatements]
end rule

rule replaceMethodNoArgs
    replace [method_declaration]
        _[acess_modifier] _[class_name] methodName [id]'() '{ statements [repeat statement] '}
    by
        'def methodName '(self):  statements [replaceStatements]
end rule


rule replaceToString
    replace [method_declaration]
        _[acess_modifier] _[class_name]  'toString '() '{ statements [repeat statement] '}
    by
        'def '__str__ '(self):  statements [replaceStatements]
end rule

rule replaceStaticMethod
    replace [method_declaration]
        _[acess_modifier] _[static] _[class_name] methodName [id]'() '{ statements [repeat statement] '}
    by
        '@staticmethod 'def methodName'():  statements [replaceStatements]
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
            [addSelfToOwnMethodCalls]
            [replaceThisFunctionCall]
            [replaceFunctionCall]
            [replaceDecleration]
            [replaceAllBoolean]
            [replaceDeclerationWithAssignment]
            [replaceIf]
            [replaceElseIf]
            [replaceElse]
            [replaceWhile]
            [replaceNull]
            [replaceThis]
end function


function replaceNoStateMents
    replace [repeat statement]
        _ [empty]
    by 
        'pass
end function

rule replaceAssignment
    replace [assignment]
        identifier [nested_identifier] '= val [value] '; 
    by 
        identifier '= val
end rule

rule replaceReturn
    replace [stmt_return]
        'return val [value] ';
    by 
        'return val
end rule

rule addSelfToOwnMethodCalls
    replace [function_call]
        nested [nested_identifier] '( values [list value]')
    deconstruct nested  
        id [id]
    by
        'self '. id '( values')
end rule

rule replaceThisFunctionCall
    replace [function_call]
        'this funcName [repeat attribute]'( values [list value]')
    by
        'self funcName '( values')
end rule


rule replaceFunctionCall
    replace [statement]
        stmt [statement]
    deconstruct stmt
        call [function_call] ';
    by
        call
end rule


rule replaceThis
    replace [value]
        'this
    by 
        'self
end rule

rule replaceDeclerationWithAssignment
    replace [variable_declaration]
        _ [id] assignment [assignment]
    by 
        assignment
end rule

rule replaceDecleration
    replace [variable_declaration]
        _[class_name] varName [id]';
    by 
        varName
end rule

rule replaceIf
    replace [if]
        'if '( bool [boolean_expression] ') '{ statements [repeat statement]  '}
    by 
        'if bool ': statements
end rule

rule replaceElseIf
    replace [else_if]
        'else 'if '( bool [boolean_expression] ') '{  statements [repeat statement]  '} 
    by 
        'elif bool ': statements
end rule


rule replaceElse
    replace [else]
        'else '{  statements [repeat statement]  '} 
    by 
        'else ': statements
end rule

rule replaceWhile
    replace [while_loop]
        'while( bool [boolean_expression] ')  '{ statements [repeat statement] '} 
    by
        'while bool ':  statements 
end rule

rule replaceNull
    replace [value]
        'null
    by
        'None
end rule


%---------------------%
% Boolean expressions %
%---------------------%

function replaceAllBoolean
    replace [repeat statement]
        statements [repeat statement]

    by
        statements
            [replaceNullCheck]
            [replaceNotNullCheck]
            [replaceBoolNegation]
            [replaceBoolAnd]
            [replaceBoolOr]
            [replaceTrue]
            [replaceFalse]
end function

rule replaceNullCheck
    replace [condition]
        elem [condition_element] '== 'null
    by 
        elem 'is 'None
end rule

rule replaceNotNullCheck
    replace [boolean_expression]
        cond [condition]
    deconstruct cond
        elem [condition_element] '!= 'null
    by 
        '( 'not elem 'is 'None ')
end rule

rule replaceBoolNegation
    replace [boolean_expression]
        '! expr [boolean_expression]
    by 
        'not expr
end rule

rule replaceBoolAnd
    replace [boolean_operator]
        '&&
    by 
        'and
end rule

rule replaceBoolOr
    replace [boolean_operator]
        '|'|
    by 
        'or
end rule

rule replaceTrue
    replace [condition_element]
        'true
    by 
        'True
end rule

rule replaceFalse
    replace [condition_element]
        'false
    by 
        'False
end rule