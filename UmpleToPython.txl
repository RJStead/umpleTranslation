include "Umple.Grm"
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
        classHeader [class_header] '{ classBody [class_body_decl] '}
    construct emptyAttributes [list id]
    export attributes [list id]
        emptyAttributes
    by
        classHeader':
        classBody [extractVars] [class_atts] [class_no_atts]
end rule

function generateConstInits attr [id]
    replace [repeat statement]
        inits [repeat statement]
    construct initOutline [statement]
        'self. attr '= attr
    by
       inits [. initOutline]           
end function

% Creates the setters for a class
function generateSetter attr [id]
    import attributes [list id]
    replace [repeat statement_or_method]
        sets [repeat statement_or_method]
    % Setup attribute parameter as _VAR
    construct underscore [id]
        '_
    construct param [id]
        underscore [+ attr]
    % Setup function name as get_VAR
    construct setLit [id]
        'set
    construct funcName [id]
        setLit [_ attr]
    construct setOutline [statement_or_method]
        'def funcName '(self, param '):
            'self. attr '= param 
    by
       sets [. setOutline]
end function

% Creates the getters for a class
function generateGetter attr [id]
    import attributes [list id]
    replace [repeat statement_or_method]
        gets [repeat statement_or_method]
    % Setup attribute parameter as _VAR
    construct underscore [id]
        '_
    construct param [id]
        underscore [+ attr]
    % Setup function name as get_VAR
    construct getLit [id]
        'get
    construct funcName [id]
        getLit [_ attr]
    construct getOutline [statement_or_method]
        'def funcName '(self):
            'return 'self. attr 
    by
       gets [. getOutline]
end function

% Rule to generate a class containing attributes
% TODO: For each attribute, initialize it if previously initialized
function class_atts
    import attributes [list id]
    construct attSize [number]
        _ [length attributes]
    where 
        attSize [> 0]


    construct emptyBody [repeat statement_or_method]
	construct constInits [repeat statement]
        _ [generateConstInits each attributes]
    construct const [statement_or_method]
        'def '__init__(self, attributes '):
            constInits
    construct setters [repeat statement_or_method]
        _ [generateSetter each attributes]
    construct getters [repeat statement_or_method]
        _ [generateGetter each attributes]
    construct destructor [statement_or_method]
        'def '__del__(self):
            'pass
    construct genBody [repeat statement_or_method]
        emptyBody [. const] [. setters] [. getters] [. destructor]
    replace [class_body_decl]
        stmts [repeat statement_or_method]
    by
        genBody

        % Missing toString
        % __str__
end function

% Rule to generate a class containing NO attributes
function class_no_atts
    import attributes [list id]
    construct attSize [number]
        _ [length attributes]
    where 
        attSize [= 0]

    replace [class_body_decl]
        stmts [repeat statement_or_method]
    by
        'def '__init__(self'):
            'pass

        'def '__del__(self):
            'pass
end function


% Deletes all variable declarations for now
% Stores the name of the variable into 'attributes'
% TODO: move variables if assignment into __init__
rule extractVars
    import attributes [list id]
    replace $ [var_decl]
        type [opt type] vName [var_name] _ [var_assign]
    deconstruct vName
        id [id]
    export attributes
        attributes [, id]
    by

end rule