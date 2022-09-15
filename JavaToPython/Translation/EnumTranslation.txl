rule replaceEnumDeclaration
    replace [enum_declaration]
        _ [opt acess_modifier] 'enum enumName [id] '{ vals [list id] '}
    construct enumValDeclarations [repeat variable_declaration]
        _ [generateEnumValues vals]
    by
        'class enumName '(Enum'):
            'def '_generate_next_value_(name, 'start, 'count, 'last_values):
                'return 'name
        enumValDeclarations
end rule

function generateEnumValues vals [list id]
    replace [repeat variable_declaration]
        decls [repeat variable_declaration]
    by
        decls [generateEnumValue each vals]
end function

function generateEnumValue val [id]
    replace [repeat variable_declaration]
        decls [repeat variable_declaration]
    construct decl [variable_declaration]
        val '= 'auto()
    by decls [. decl]
end function