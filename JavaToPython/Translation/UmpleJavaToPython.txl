include "../Grammers/Python.Grm"
include "BooleanTranslation.txl"
include "StatementTranslation.txl"
include "MethodTranslation.txl"
include "ListTranslation.txl"
include "ImportProcessing.txl"
%--------------------%s
%     Top level      %
%--------------------%
function main
    replace [program] 
	 _[repeat package_statement] 
     _[repeat import_statement]
     Classes [repeat class_declaration]
    by
	Classes
        [replaceConcreteClassesWithInheritance] 
        [replaceConcreteClassesNoInheritance]
        [replaceInterfacesWithInheritance]
        [replaceInterfacesNoInheritance] 
        
end function


%--------------------%
%     Classes        %
%--------------------%
rule replaceConcreteClassesWithInheritance
    replace $ [concrete_class_declaration]
        _ [acess_modifier] 'class className [class_name] inheritances [repeat inheritance_list+] '{ classBody [class_body_decl] '} 
    deconstruct classBody
        decls [repeat member_variable_declaration] _ [opt constructor] _ [repeat method_declaration]
    construct declarationClassesToImport [repeat id]
        _ [getClassesToImport each decls]
    construct allClassesToImport [repeat id]
        _ [extractInheritanceImportClasses each inheritances] [concatenateRepeatNoDuplicates declarationClassesToImport]
    construct inheritanceClasses [list class_name]
        _ [extractInheritanceBlockClasses each inheritances]
    construct imports [repeat import_statement]
        _ [addImportStatement each allClassesToImport]
    by
        imports 'class className '( inheritanceClasses ')':  classBody  [replaceClassBody]
end rule

rule replaceConcreteClassesNoInheritance
    replace $ [concrete_class_declaration]
        _ [acess_modifier] 'class className [class_name] '{ classBody [class_body_decl] '}
    deconstruct classBody
        decls [repeat member_variable_declaration] _ [opt constructor] _ [repeat method_declaration]
    construct declarationClassesToImport [repeat id]
        _ [getClassesToImport each decls] 
    construct imports [repeat import_statement]
        _ [addImportStatement each declarationClassesToImport]
    by
    imports 'class className ':  classBody  [replaceClassBody]
end rule


function extractInheritanceBlockClasses inheritanceList [inheritance_list]
    replace [list class_name]
        classes [list class_name]
    deconstruct inheritanceList
        _[inheritance_statement] classesToAdd [list class_name]
    by
        classes [, classesToAdd] 
end function

function extractInheritanceImportClasses inheritanceList [inheritance_list]
    replace [repeat id]
        classesToImport [repeat id]
    deconstruct inheritanceList
        _[inheritance_statement] classesToAdd [list class_name]
    construct classIds [repeat id]
        _ [extractListClass each classesToAdd] [extractRegularClass each classesToAdd]
    by
        classesToImport [. classIds] 
end function


function addImportStatement a [id]
    replace [repeat import_statement]
        imports [repeat import_statement]
    construct newImport [import_statement]
        'from a 'import a
    by
        imports [. newImport]
end function

rule replaceInterfacesNoInheritance
    replace [interface_declaration]
        _ [acess_modifier] 'interface className [class_name] '{ classBody [class_body_decl] '} 
    by
        'from 'abc 'import 'ABC, 'abstractmethod 'class className '(ABC):  classBody [replaceClassBody] [replaceInterfaceBody]
end rule

rule replaceInterfacesWithInheritance
    replace [interface_declaration]
        _ [acess_modifier] 'interface className [class_name] inheritances [repeat inheritance_list+] '{ classBody [class_body_decl] '} 
    construct inheritanceClasses [list class_name]
        _ [extractInheritanceBlockClasses each inheritances]
    construct classesToImport [repeat id]
        _ [extractInheritanceImportClasses each inheritances]
    construct imports [repeat import_statement]
        _ [addImportStatement each classesToImport]
    by
        'from 'abc 'import 'ABC, 'abstractmethod imports 'class className '(ABC, inheritanceClasses '):  classBody [replaceClassBody] [replaceInterfaceBody]
end rule

function replaceInterfaceBody
   replace [class_body_decl]
        declarations [repeat member_variable_declaration] methods [repeat method_declaration]
    construct memberVariables [repeat id]
        _ [addMemberVariable each declarations]
    construct listMemberVariables [repeat id]
        _ [addListMemberVariable each declarations]
    by
        '@abstractmethod 'def '__init__(self): 'pass methods [replaceAllMethods memberVariables listMemberVariables]
end function

function replaceClassBody
    replace [class_body_decl]
        declarations [repeat member_variable_declaration] oldConstructor [constructor] methods [repeat method_declaration]
    construct memberVariables [repeat id]
        _ [addMemberVariable each declarations]
    construct listMemberVariables [repeat id]
        _ [addListMemberVariable each declarations]
    construct newContructor [constructor]
        oldConstructor 
            [replaceAllLists listMemberVariables]
            [replaceAllMemberVariableNames memberVariables] 
            [replaceContructor] 
            [replaceContructorNoArgs]
    by
        newContructor methods [replaceAllMethods memberVariables listMemberVariables]
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
