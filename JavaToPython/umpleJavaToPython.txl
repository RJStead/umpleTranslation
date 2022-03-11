include "Java.Grm"
include "Python.Grm"


function main
    replace [program] 
	P [program]
    by
	P [replaceClasses]
end function

define program
    [repeat class_decl]
end define

% Rule to reformat the classes
% attributes holds the classes var name
rule replaceClasses
    replace $ [class_decl]
        _ [acess_modifier] 'class className [id] '{ classBody [class_body_decl] '} 
    by
    'class className ':  classBody [replaceClassBody] 
end rule

% Rule to generate a class containing attributes
% TODO: For each attribute, initialize it if previously initialized
function replaceClassBody
    replace [class_body_decl]
        declarations [repeat member_variable_declaration] constructor [opt constructor] methods [repeat method_decl]
    by
        constructor [replaceContructor] methods
end function

function replaceContructor
    replace [constructor]
         mod [acess_modifier] className [id]'( params [list method_parameter] ') '{ statements [repeat statement]  '} 
    by
        mod className'( params ')  '{  statements '} 
end function
