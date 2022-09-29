%--------------------%
%     Classes        %
%--------------------%


rule replaceConcreteClassesWithInheritance
    replace $ [concrete_class_declaration]
        _ [acess_modifier] 'class className [class_name] inheritances [repeat inheritance_list+] '{ classBody [class_body_decl] '} 
    deconstruct classBody
        _ [repeat enum_declaration] decls [repeat member_variable_declaration] _ [opt constructor] _ [repeat method_declaration]
    construct declarationClassesToImport [repeat id]
        _ [getClassesToImport classBody each decls]
    construct allClassesToImport [repeat id]
        _ [extractInheritanceImportClasses classBody each inheritances] [concatenateRepeatNoDuplicates declarationClassesToImport]
    construct inheritanceClasses [list class_name]
        _ [extractInheritanceBlockClasses each inheritances]
    construct imports [repeat import_statement]
        _ [addImportStatement each allClassesToImport] [addExternalImports classBody]
    by
        imports 'class className '( inheritanceClasses ')':  classBody  [replaceClassBody]
end rule

rule replaceConcreteClassesNoInheritance
    replace $ [concrete_class_declaration]
        _ [acess_modifier] 'class className [class_name] '{ classBody [class_body_decl] '}
    deconstruct classBody
        _ [repeat enum_declaration] decls [repeat member_variable_declaration] _ [opt constructor] _ [repeat method_declaration]
    construct declarationClassesToImport [repeat id]
        _ [getClassesToImport classBody each decls] 
    construct imports [repeat import_statement]
        _ [addImportStatement each declarationClassesToImport] [addExternalImports classBody]
    by
    imports 'class className ':  classBody  [replaceClassBody]
end rule

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
        _ [extractInheritanceImportClasses classBody each inheritances]
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
        enums [repeat enum_declaration] declarations [repeat member_variable_declaration] oldConstructor [constructor] methods [repeat method_declaration]
    construct memberVariables [repeat id]
        _ [addMemberVariable each declarations]
    construct listMemberVariables [repeat id]
        _ [addListMemberVariable each declarations]
    construct newConstructor [constructor]
        oldConstructor 
            [replaceAllLists listMemberVariables]
            [replaceAllMemberVariableNames memberVariables] 
            [replaceConstructor] 
            [replaceConstructorNoArgs]
    construct newEnums [repeat enum_declaration]
    by
        enums [replaceEnumDeclaration] newConstructor methods [replaceAllMethods memberVariables listMemberVariables]
end function




