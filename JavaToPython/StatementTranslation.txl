%--------------------%
%     Statements     %
%--------------------%
function replaceStatements
    replace [repeat statement]
        statements [repeat statement]
    by 
        statements
            [replaceDefaultReadObject]
            [replaceSwitchCase]
            [addClassPrefixToEnum]
            [replaceForLoop]
            [replaceForInLoop]
            [replaceAssignementIncrementBefore]
            [replaceAssignementDecrementBefore]
            [replaceAssignementIncrementAfter]
            [replaceAssignementDecrementAfter]
            [replaceAssignmentStatement] 
            [replaceReturn] 
            [replaceNoStatements] 
            [addSelfToOwnMethodCalls]
            [replaceThisFunctionCall]
            [replaceNestedStatement]
            [replaceDecleration]
            [replaceTernary]
            [replaceAllBoolean]
            [replaceDeclerationWithAssignment]
            [replaceInlineIf]
            [replaceIf]
            [replaceElseIf]
            [replaceElse]
            [replaceWhile]
            [replaceNull]
            [replaceThis]
            [replaceIncrementBefore]
            [replaceDecrementBefore]
            [replaceIncrementAfter]
            [replaceDecrementAfter]
            [replaceThrowError]
            [replaceNewCall]
            [replaceCasting]
            [correctSuperInit]
            [correctSuperFunctions]
            [replaceNewLine]
            [replaceHexIdentity]
            [replaceComparator]
            [translateToStringCall]
            [translateEqualsCall]
end function

function replaceNoStatements
    replace [repeat statement]
    by 
        'pass  
end function

rule replaceAssignementIncrementAfter
    replace [statement]
        nest1 [nested_identifier] '= nest2 [nested_identifier] '++ ';
    by
        nest1 ', nest2 '= nest2 ', nest2 '+ '1
end rule

rule replaceAssignementDecrementAfter
    replace [statement]
        nest1 [nested_identifier] '= nest2 [nested_identifier] '-- ';
    by
        nest1 ', nest2 '= nest2 ', nest2 '- '1
end rule

rule replaceAssignementIncrementBefore
    replace [statement]
        nest1 [nested_identifier] '= '++ nest2 [nested_identifier]';
    by
        nest1 '= nest2 '= nest2 '+ '1
end rule

rule replaceAssignementDecrementBefore
    replace [statement]
        nest1 [nested_identifier] '= '-- nest2 [nested_identifier]';
    by
        nest1 '= nest2 '= nest2 '- '1
end rule

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
    where
        funcName [~= 'str]
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
        _ [nested_identifier] assignment [assignment] ';
    by 
        assignment
end rule

rule replaceDecleration
    replace [variable_declaration]
        _[nested_identifier] varName [id]';
    by 
        varName 
end rule

rule replaceIf
    replace [if]
        'if '( bool [value] ') '{ statements [repeat statement]  '}
    by 
        'if bool ': statements
end rule

rule replaceInlineIf
     replace [if]
        'if '( bool [value] ')  statement [ statement]  
    by 
        'if bool ': statement
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
        condition [boolean_expression] '? opt1 [value] ': opt2 [value]
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

rule replaceDecrementBefore
    replace [statement]
        '-- nest [nested_identifier]';
    by 
        nest '-= 1
end rule

rule replaceIncrementBefore
    replace [statement]
        '++ nest [nested_identifier]';
    by 
        nest '+= '1
end rule

rule replaceDecrementAfter
    replace [statement]
        nest [nested_identifier] '-- ';
    by 
        nest '-= 1
end rule

rule replaceIncrementAfter
    replace [statement]
        nest [nested_identifier] '++ ';
    by 
        nest '+= '1
end rule

rule replaceForLoop
    replace [statement]
        'for( decl [variable_declaration] goal [value]'; assignment [assignment]') '{  stmts[repeat statement]  '} 
    deconstruct decl
        _[nested_identifier] name [id] '= start [value] ';
    construct declaration [variable_declaration]
        name '= start
    construct newStatements [repeat statement]
        assignment
    by 
        declaration 'while goal ':  stmts  [. newStatements]
end rule

rule replaceForInLoop
    replace [for_in_loop]
        'for( _[nested_identifier] var [id] ': nested [nested_identifier]')'{ stmts [repeat statement] '} 
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
        '( _ [nested_identifier]') name [value]
    by 
        name 
end rule

rule replaceNewCall
    replace [value]
        'new funcCall [function_call]
    deconstruct funcCall
        className [callable] '( vals [list value] ')
    by
        className '( vals ')
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

rule replaceSwitchCase
    replace [statement]
        'switch( val [value_no_recursion] ') '{ cases [repeat switch_case_case]  default [opt switch_case_default] '}
    construct firstCase [repeat switch_case_case]
        cases [head 1]
    construct otherCases [repeat switch_case_case]
        cases [tail 2]
    construct ifElses [repeat else_if]
        _ [replaceSwitchCaseCase val each otherCases]
    construct throwAwayIf [if]
        'if 'a: 'pass
    construct realIf [if]
        throwAwayIf [replaceFirstSwitchCaseCase val each firstCase]
    construct else [opt else]
        _ [replaceSwitchCaseDefault default]
    by
        realIf ifElses else
end rule


function replaceFirstSwitchCaseCase switch [value_no_recursion] firstCase [switch_case_case] 
    replace [if]
        _ [if]
    deconstruct firstCase
        'case val [value_no_ternary] ': stmts [repeat statement] 'break;
    construct condition [condition]
        switch '== val [fixEnumValueWithNoEnum]
    construct newIf [if]
        'if condition ': stmts [replaceNoStatements]
    by 
        newIf
end function

function replaceSwitchCaseCase switch [value_no_recursion] aCase [switch_case_case] 
    replace [repeat else_if]
        rep [repeat else_if]
    deconstruct aCase
        'case val [value_no_ternary] ': stmts [repeat statement] 'break;
    construct elseIf [else_if]
        'elif switch '== val [fixEnumValueWithNoEnum] ': stmts [replaceNoStatements]
    by 
        rep [. elseIf]
end function

function replaceSwitchCaseDefault defaultCase [opt switch_case_default]
    replace [opt else]
        _ [opt else]
    deconstruct defaultCase
        'default ': stmts [repeat statement]
    by
        'else: stmts [replaceNoStatements]

end function

function replaceDefaultReadObject
    replace [repeat statement]
        rep [repeat statement]
    construct seeking [statement]
        'in.defaultReadObject();
    deconstruct seeking
        seekingAny [any]
    where 
        rep [containsGeneric seekingAny]
    construct repAny [repeat any]
        _ [repeatStatementToAny each rep]
    construct beforeAfter [repeat sequence_any]
        _ [getBeforeAfter seekingAny repAny]
    deconstruct beforeAfter 
        beforeAfterRep [repeat sequence_any]
    %reparse result to get back to original type
    construct before [repeat sequence_any]
        beforeAfterRep [select 1 1] 
    construct after [repeat sequence_any]
        beforeAfterRep [select 2 2]
    construct beforeReparsed [repeat statement]
        _ [reparse before]
    construct afterReparsed [repeat statement]
        _ [reparse after]
    construct middle [repeat statement]
        'self '.__dict__ '.clear()
        'self '.__dict__ '.clear() '.update(pickle '.load(input) '.__dict__)
    by 
        beforeReparsed [. middle] [. afterReparsed]
end function


function repeatStatementToAny stmt [statement]
    replace [repeat any]
        rep [repeat any]
    deconstruct stmt
        stmtAny [any]
    by
        rep [. stmtAny]
end function

rule replaceComparator
    replace [value]
        'Comparator.comparing( class [nested_identifier] ':: funcName [id] ')
    by
        'lambda 'x ': 'x '. funcName '()
end rule

%--------------------------------%
%  Switch case Enum correction   %
%--------------------------------%

rule fixEnumValueWithNoEnum
    replace $ [value]
        val [value]
    deconstruct val
        _ [id]
    import enumeratorDeclerations [repeat enum_declaration]
    by
        val [fixEnumValueWithNoEnumCheck each enumeratorDeclerations]
end rule

rule fixEnumValueWithNoEnumCheck aEnum [enum_declaration]
    replace [value]
        identifier [id]
    deconstruct aEnum 
        _ [opt acess_modifier] 'enum enumName [id] '{ vals [list id]'}
    where
        identifier [= each vals]
    by
        enumName '. identifier
end rule

rule addClassPrefixToEnum
    replace [value]
        enumName [id] '.  enumVal [id]
    where
        enumName [isAnEnum]
    import className [nested_identifier]
    deconstruct className
        root [nestable_value] accesses [repeat attribute_access]
    construct enumAccess [attribute_access] 
        '. enumName
    construct enumValueAccess [attribute_access] 
        '. enumVal  
    by
        root accesses [. enumAccess] [. enumValueAccess]
end rule

function isAnEnum
    match [id]
        name [id]
    import enumeratorDeclerations [repeat enum_declaration]
    where
        name [isSpecificEnum each enumeratorDeclerations]
end function

function isSpecificEnum aEnum [enum_declaration]
    match [id]
        name [id]
    deconstruct aEnum    
        _ [opt acess_modifier] 'enum enumName [id] '{ _ [list id]'}
    where
        name [= enumName]
end function
%--------------------------------%
%  Attribute access translation  %
%--------------------------------%

rule translateToStringCall
    replace [attribute_access]
        '.toString()
    by
        '.__str__()
end rule

rule translateEqualsCall
    replace [attribute_access]
        '.equals( val [value] ')
    by
        '.__eq__( val ')
end rule


%--------------------------------%
%  Generic Before/after search   %
%--------------------------------%

define sequence_any
    [repeat any]
end define

function getBeforeAfter seeking [any] rep [repeat any]
    replace [repeat sequence_any]
    where 
        rep [containsGeneric seeking]
    deconstruct rep
        repAny [repeat any]
    construct zero [number]
        '0 
    construct index [number]
        zero [getIndex seeking repAny]
    by 
        _ [createBeforeAfter index repAny]
end function

function createBeforeAfter index [number] rep [repeat any]
    replace [repeat sequence_any]
        empty [repeat sequence_any]
    construct beforeIndex [number]
        index
    construct before [sequence_any]
        rep [head beforeIndex]
    construct countWithSkip [number]
        index [+ 2]
    construct after [sequence_any]
        rep [tail countWithSkip]
    by
        empty [. before] [. after]
end function

function getIndex  seeking [any] rep [repeat any]
    replace [number]
        index [number]
    construct repLength [number]
        _ [length rep]
    construct head [repeat any]
        rep [head 1]
    where 
        repLength [> 0]
    construct remaining [repeat any]
        rep [tail 2]
    where not
        head [containsGeneric seeking] 
    by
        index [+ 1] [getIndex seeking remaining]
end function

rule containsGeneric seeking [any]
    match [any]
        seeking
end rule