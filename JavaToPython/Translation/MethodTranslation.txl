%--------------------%
%     Methods        %
%--------------------%

function replaceAllMethods memberVariables [repeat id] memberLists [repeat id]
    replace [repeat method_declaration]
        methods [repeat method_declaration]
    by
        methods 
            [replaceToString]
            [replaceAllLists memberLists]
            [replaceAllMemberVariableNames memberVariables] 
            [replaceAbstractMethod]
            [replaceAbstractMethodNoArgs]
            [replaceConcreteMethod] 
            [replaceConcreteMethodNoArgs]
            [replaceStaticMethod]
end function

rule replaceConcreteMethod
    replace [concrete_method_declaration]
        _[acess_modifier] _[class_name] methodName [id]'( params [list method_parameter +] ') '{ statements [repeat statement] '}
    construct newParams [list id]
    by
        'def methodName '(self, newParams [translateParams each params] '):  statements [replaceStatements]
end rule

rule replaceConcreteMethodNoArgs
    replace [concrete_method_declaration]
        _[acess_modifier] _[class_name] methodName [id]'() '{ statements [repeat statement] '}
    by
        'def methodName '(self):  statements [replaceStatements]
end rule

rule replaceAbstractMethod
    replace [abstract_method_declaration]
        _[acess_modifier] _[class_name] methodName [id] '( params [list method_parameter +] ');
    construct newParams [list id]
    by
        '@abstractmethod 'def methodName '(self, newParams [translateParams each params] '): 'pass
end rule

rule replaceAbstractMethodNoArgs
    replace [abstract_method_declaration]
        _[acess_modifier] _[class_name] methodName [id]'();
    by
        '@abstractmethod 'def methodName '(self): 'pass
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
