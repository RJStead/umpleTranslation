function getClassesToImport declaration [member_variable_declaration]
    replace [repeat id]
        empty [repeat id]
    deconstruct declaration
        _[opt acess_modifier] _[opt static] _[opt volatile] varDec [variable_declaration]
    deconstruct varDec
        class [class_name] _ [id]';
    construct classesToImport [repeat id]
        _ [extractListClass class] [extractRegularClass class]
    by 
        empty [addToRepeatIfNotThere each classesToImport] 
end function 

function concatenateRepeatNoDuplicates elems [repeat id]
    replace [repeat id]
        currentList [repeat id]
    by
        currentList [addToRepeatIfNotThere each elems] 
end function

function addToRepeatIfNotThere elem [id]
    replace [repeat id]
        currentList [repeat id]
    where not 
        currentList [contains elem]
    by
        currentList [. elem]
end function

function extractListClass class [class_name]
    replace [repeat id]
        empty [repeat id]
    deconstruct class
        _ [id]'< ids [list id] '>
    construct unfiltered [repeat id]
        _ [listToRepeat ids] 
    construct filtered [repeat id]
        _ [filterOutDefaultTypes unfiltered]
    by
        filtered
end function

function filterOutDefaultTypes ids [repeat id]
    replace [repeat id]
        empty [repeat id]
    by 
        empty [addIfNotDefaultType each ids]
end function

function addIfNotDefaultType id [id]
    replace [repeat id]
        current [repeat id]
    where not 
        id [matchDefaultType]
    by
        current [. id] 
end function

rule matchDefaultType
    match [id]
        id [id]
    construct defaults [repeat id]
        'byte 'short 'int 'long 'float 'double 'boolean 'char 'String 'Array
    where   
        defaults [contains id]
    
        
end rule


function extractRegularClass class [class_name]
    replace [repeat id]
        empty [repeat id]
    deconstruct class
        id [id]
    where not 
        id [matchDefaultType]
    by
        empty [. id]
end function

function listToRepeat anys [list id]
    replace [repeat id]
        aRep [repeat id]
    by 
        aRep [addToRepeat each anys]
end function

function addToRepeat a [id]
     replace [repeat id]
        aRep [repeat id]
    by 
        aRep [. a]
end function

function repeatToList aRep [repeat id]
    replace [list id]
        aList [list id]
    by 
        aList [addToList each aRep]

end function


function addToList anys [id]
     replace [list id]
        aRep [list id]
    by 
        aRep [, anys]
end function


function translateParams PreviousParam [method_parameter]
    replace [list id]
        SequenceSoFar [list id]
    deconstruct PreviousParam
        _ [id] paramName [id]
    by
        SequenceSoFar [, paramName]
end function

function addListMemberVariable MemberVariable [member_variable_declaration]
    replace [repeat id]
        SequenceSoFar [repeat id]
    deconstruct MemberVariable
        _[opt acess_modifier] decl [variable_declaration]
    deconstruct decl
        'List '< _ [list id] '> memberName [id]';
    by
        SequenceSoFar [. memberName]
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
        any 
            [replaceMemberVariableNames memberVariables] 
            [replaceMemberVariableNamesWithThis memberVariables]
            [replaceMemberVariableNamesBrackets memberVariables]
end function

rule replaceMemberVariableNames memberVariables [repeat id]
    replace [nested_identifier]
         name [id] rep [repeat attribute_access]
    where 
        memberVariables [contains name]
    construct underscore [id]
        '_
    construct newName [id]
        underscore [+ name] 
    by
        'self '. newName rep
end rule

rule replaceMemberVariableNamesWithThis memberVariables [repeat id]
    replace [nested_identifier]
        'this '. name [id] rep [repeat attribute_access]
    where 
        memberVariables [contains name]
    construct underscore [id]
        '_
    by
        'self '. underscore [+ name] rep
end rule

rule replaceMemberVariableNamesBrackets memberVariables [repeat id]
    replace [nested_identifier]
         name [id] '[ val [value] ']  rep [repeat attribute_access]
    where 
        memberVariables [contains name]
    construct underscore [id]
        '_
    construct newName [id]
        underscore [+ name]
    by
        'self '. newName '[ val ']  rep
end rule


rule contains Object [id]
    match [id]
        Object
end rule