%--------------------%
%     Classes        %
%--------------------%
%decl and inheritance and external imports
rule replaceConcreteClassesWithInheritance
    replace $ [concrete_class_declaration]
        _ [acess_modifier] 'class className [nested_identifier] inheritances [repeat inheritance_list] '{ classBody [class_body_decl] '} 
    export className 
    construct inheritanceClasses [list nested_identifier]
        _ [extractInheritanceBlockClasses each inheritances]
    construct translatedBody [class_body_decl]
        classBody  [replaceClassBody]
    construct imports [repeat import_statement]
        _ [createImports classBody inheritances translatedBody]
    by
        imports 'class className '( inheritanceClasses ')':  translatedBody
end rule

%inheritance  imports
rule replaceInterfacesWithInheritance
    replace [interface_declaration]
        _ [acess_modifier] 'interface className [nested_identifier] inheritances [repeat inheritance_list] '{ classBody [class_body_decl] '} 
    export className
    construct inheritanceClasses [list nested_identifier]
        _ [extractInheritanceBlockClasses each inheritances]
    construct translatedBody [class_body_decl]
        classBody  [replaceClassBody] [replaceInterfaceBody]
    construct imports [repeat import_statement]
        _ [createImports classBody inheritances translatedBody]
    construct AbcClass [list nested_identifier]
        'ABC
    construct finalInheritances [list nested_identifier]
        AbcClass [, inheritanceClasses]
    by
        'from 'abc 'import 'ABC, 'abstractmethod imports 'class className '(  finalInheritances '):  translatedBody
end rule

function replaceInterfaceBody
   replace [class_body_decl]
        elements [repeat class_body_element]
    construct declarations [repeat member_variable_declaration]
        _ [^ elements]
    export enumeratorDeclerations [repeat enum_declaration]
        _ [^ elements]
    construct memberVariables [repeat id]
        _ [addMemberVariable each declarations]
    construct listMemberVariables [repeat id]
        _ [addListMemberVariable each declarations]
    by
        '@abstractmethod 'def '__init__(self): 'pass elements [replaceAllMethods memberVariables]
end function

function replaceClassBody
    replace [class_body_decl]
        body [class_body_decl]
    deconstruct body 
        elements [repeat class_body_element]
    construct declarations [repeat member_variable_declaration]
        _ [^ elements]
    construct transientMembers [repeat id]
        _ [addIfTransient each declarations]
    export transientMembers
    construct memberVariables [repeat id]
        _ [addMemberVariable each declarations]
    export listMemberVariables [repeat id]
        _ [addListMemberVariable each declarations]
    export enumeratorDeclerations [repeat enum_declaration]
        _ [^ elements]
    export classMethods [repeat method_declaration]
        _ [^ elements]
    export classMethodNames [repeat id]
        _ [extractClassMethodName each classMethods]
    construct possibleFunctionImports [repeat id]
        _ [extractPossibleFunctionImports body each declarations]
    export possibleFunctionImports
    by
        elements [exportConstructorCount] [removeMemberVariableDeclarations] [replaceEnumDeclaration] 
            [replaceAllMethods memberVariables]
            [replaceAllMemberVariableNames memberVariables] 
end function

function extractClassMethodName method [method_declaration]
    replace [repeat id]
        result [repeat id]
    by
        result [extractConcreteMethodName method] [extractAbstractMethodName method]
end function

function extractConcreteMethodName method [method_declaration]
    replace [repeat id]
        result [repeat id]
    deconstruct method
        _[opt decorator] _[acess_modifier] _[opt static] _[nested_identifier] methodName [id] '( _[list method_parameter] ') _[opt throws] '{ _[repeat statement] '}
    by
        result [. methodName]
end function

function extractAbstractMethodName method [method_declaration]
    replace [repeat id]
        result [repeat id]
    deconstruct method
        _[acess_modifier] _[nested_identifier] methodName [id] '( _ [list method_parameter] ');
    by
        result [. methodName]
end function

function getTransientMembers decls [repeat member_variable_declaration]
    replace [repeat id]
        result [repeat id]
    by
        result [addIfTransient each decls]
end function

function addIfTransient decl [member_variable_declaration]
    replace [repeat id]
        result [repeat id]
    deconstruct decl
        _ [opt acess_modifier] 'transient _ [opt static] _ [opt volatile] _ [variable_declaration]
    by
        result [addMemberVariable decl]
end function

function removeMemberVariableDeclarations
    replace [repeat class_body_element]
        elems [repeat class_body_element]
    construct empty [repeat class_body_element]
        _
    by
        empty [addIfNotMemberDecleration each elems]
end function

function addIfNotMemberDecleration elem [class_body_element]
    replace [repeat class_body_element]
        keepers [repeat class_body_element]
    construct declerations [repeat member_variable_declaration]
        _ [^ elem]
    construct declerationSize [number]
        _ [length declerations]
    where
        declerationSize [= 0]
    by
        keepers [. elem]
end function

function addMemberVariable MemberVariable [member_variable_declaration]
    replace [repeat id]
        SequenceSoFar [repeat id]
    deconstruct MemberVariable
        _[opt acess_modifier] _[opt transient] _[opt static] _[opt volatile]  decl [variable_declaration]
    deconstruct decl
        _ [nested_identifier] memberName [id]';
    by
        SequenceSoFar [. memberName]
end function

function exportConstructorCount
    match [repeat class_body_element]
        rep [repeat class_body_element]
    construct zero [number]
        '0
    construct constructorCount [number]
        zero [incrementIfConstructor each rep]
    export constructorCount
end function

function incrementIfConstructor elem [class_body_element]
    replace [number]
        count [number]
    deconstruct elem
        _ [constructor]
    by
        count [+ '1]
end function