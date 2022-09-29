
%--------------------%
%     Statements     %
%--------------------%
function replaceStatements
    replace [repeat statement]
        statements [repeat statement]
    by 
        statements 
            [replaceForLoop]
            [replaceForInLoop]
            [replaceAssignmentStatement] 
            [replaceReturn] 
            [replaceNoStateMents] 
            [addSelfToOwnMethodCalls]
            [replaceThisFunctionCall]
            [replaceNestedStatement]
            [replaceDecleration]
            [replaceTernary]
            [replaceAllBoolean]
            [replaceDeclerationWithAssignment]
            [replaceIf]
            [replaceElseIf]
            [replaceElse]
            [replaceWhile]
            [replaceNull]
            [replaceThis]
            [replaceIncrement]
            [replaceDecrement]
            [replaceThrowError]
            [replaceNewCall]
            [replaceCasting]
            [correctSuperInit]
            [correctSuperFunctions]
            [replaceSuperToString]
            [replaceNewLine]
            [replaceHexIdentity]
            
end function


function replaceNoStateMents
    replace [repeat statement]
        _ [empty]
    by 
        'pass
end function

rule replaceAssignmentStatement
    replace [statement]
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
    replace [nested_identifier]
        funcName [id] '( values [list value]') rep [repeat attribute_access]
    by
        'self '. funcName '( values') rep
end rule

rule replaceThisFunctionCall
    replace [nested_identifier]
        'this. funcName [id] '( values [list value]')
    by
        'self '. funcName '( values')
end rule

rule replaceNestedStatement
    replace [statement]
        stmt [statement]
    deconstruct stmt
        value [nested_identifier] ';
    by
        value
end rule


rule replaceThis
    replace [value]
        'this
    by 
        'self
end rule

rule replaceDeclerationWithAssignment
    replace [variable_declaration]
        _ [class_name] assignment [assignment] ';
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
        'if '( bool [value] ') '{ statements [repeat statement]  '}
    by 
        'if bool ': statements
end rule

rule replaceElseIf
    replace [else_if]
        'else 'if '( bool [value] ') '{  statements [repeat statement]  '} 
    by 
        'elif bool ': statements
end rule


rule replaceElse
    replace [else]
        'else '{  statements [repeat statement]  '} 
    by 
        'else ': statements
end rule


rule replaceTernary
    replace [ternary]
        condition [value_no_recursion] '? opt1 [value] ': opt2 [value]
    by
        '( opt1 ') 'if condition 'else opt2
end rule

rule replaceWhile
    replace [while_loop]
        'while( val [value] ')  '{ statements [repeat statement] '} 
    by
        'while val ':  statements 
end rule

rule replaceNull
    replace [value]
        'null
    by
        'None
end rule


rule replaceDecrement
    replace [assignment]
        nest [nestable_value] '--
    construct test [arithmatic_expression]
        nest '- '1
    by 
        nest '= test
end rule

rule replaceIncrement
    replace [assignment]
        nest [nestable_value] '++
    by 
        nest '= nest '+ '1
end rule

rule replaceForLoop
    replace [statement]
        'for( decl [variable_declaration] goal [value]'; assignment [assignment]') '{  stmts[repeat statement]  '} 
    deconstruct decl
        _[class_name] name [id] '= start [value] ';
    construct declaration [variable_declaration]
        name '= start
    construct newStatements [repeat statement]
        assignment
    by 
        declaration 'while goal ':  stmts  [. newStatements]
end rule

rule replaceForInLoop
    replace [for_in_loop]
        'for( _[class_name] var [id] ': nested [nested_identifier]')'{ stmts [repeat statement] '} 
    by 
        'for var 'in  nested':  stmts
end rule

rule replaceThrowError
    replace [throw_statement]
        'throw 'new _[id] '( message [stringlit] ');
    by
        'raise 'RuntimeError(  message ') 
end rule 

rule replaceCasting
    replace [value]
        '( _ [class_name]') name [value]
    by 
        name 
end rule

rule replaceNewCall
    replace [value]
        'new class [class_name] '( vals [list value] ')
    deconstruct class
        id [id]
    by
        id '( vals ')
end rule

rule correctSuperInit
    replace [nested_identifier]
        'super( params [list value] ')
    by
        'super().__init__( params ')
end rule

rule correctSuperFunctions
    replace [nested_identifier]
        'super rep [repeat attribute_access]
    by
        'super() rep
end rule

rule replaceSuperToString
    replace [nested_identifier]
        'super().toString() rep [repeat attribute_access]
    by
        'super().__str__() rep
end rule

rule replaceNewLine
    replace [nested_identifier]
        'System.getProperties().getProperty("line.separator")
    by
        'os.linesep
end rule 

rule replaceHexIdentity
    replace [nested_identifier]
        'Integer.toHexString(System.identityHashCode( val [value_no_recursion] '))
    by
        'format( 'id( val '), '"x")
end rule

