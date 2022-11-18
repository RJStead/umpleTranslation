%--------------------%
%  List replacement  %
%--------------------%

function replaceAllSpecialTypes lists [repeat id] hashMaps [repeat id]
    replace [any]
        any [any]
    by 
        any
            [replaceRemoveAll lists]
            [replaceListAssignement lists]
            [replaceListUnmutable lists]
            [replaceGetListContent lists]
            [replaceListGetSize lists]
            [replaceArrayLength lists]
            [replaceListGetIndex lists]
            [replaceListContains lists]
            [replaceListAddAtIndex lists]
            [replaceListAddNoIndex lists]
            [replaceListCopy]
            [replaceListSort lists]
            [replaceAddAll lists]
            [replaceToArray lists]
            %[replacePut hashMaps]
end function 

rule replaceListAssignement memberLists [repeat id]
    replace [assignment]
        id [id] '= 'new 'ArrayList '< _[list id] '> '(')
    where
        memberLists [containsId id]
    by 
        id '= '[']
end rule

rule replaceListCopy
    replace [assignment]
        id [id] '= 'new 'ArrayList '< _[list id] '> '( oldList [nested_identifier]') 
    deconstruct oldList
        val [nestable_value] rep [repeat attribute_access]
    construct newNested [repeat attribute_access]
        '.copy()
    by 
        id '= val rep [. newNested]
end rule

rule replaceListUnmutable memberLists [repeat id]
    replace [value]
        'Collections.unmodifiableList( id [id] ')
    where
        memberLists [containsId id]
    by 
        'tuple( id ')
end rule


rule replaceGetListContent memberLists [repeat id]
    replace [nested_identifier]
        id [id] '. 'get( index [value] ')  rest [repeat attribute_access]
    where
        memberLists [containsId id]
    by 
        id '[ index '] rest 
end rule

rule replaceListGetSize memberLists [repeat id]
    replace [nested_identifier]
        id [id] '.size()
    where
        memberLists [containsId id]
    by 
        'len( id ')
end rule

rule replaceArrayLength memberLists [repeat id]
    replace [nested_identifier]
        id [id] '.length
    where
        memberLists [containsId id]
    by 
        'len( id ')
end rule

rule replaceListGetIndex memberLists [repeat id]
    replace [nested_identifier]
        id [id] '.indexOf( val [value] ')
    where
        memberLists [containsId id]
    by 
        id '.index( val ')
end rule

rule replaceListContains memberLists [repeat id]
    replace [value]
        id [id]'. 'contains '( val [value] ') cont [opt value_continuation]
    where
        memberLists [containsId id]
    deconstruct val
        baseContains [base_value] baseCont [opt value_continuation]
    by 
        '( baseContains baseCont ') 'in id cont
end rule

rule replaceListAddAtIndex memberLists [repeat id]
    replace [nested_identifier]
        id [id] '.add( position [value] ', value [value] ')
    where
        memberLists [containsId id]
    by 
        id '.insert( position ', value ')
end rule

rule replaceListAddNoIndex memberLists [repeat id]
    replace [nested_identifier]
        id [id] '.add( value [value] ')
    where
        memberLists [containsId id]
    by 
        id '.append( value ')
end rule

rule replaceListSort memberLists [repeat id]
    replace [nested_identifier]
        'Collections.sort( listId [id] ', priorityFunc [id] ')
    where
        memberLists [containsId listId]
    by 
        listId '.sort( 'key '= priorityFunc ')
end rule

rule replaceAddAll memberLists [repeat id]
    replace [nested_identifier]
        id [id] '.addAll( value [value] ')
    where
        memberLists [containsId id]
    by 
        id '.extend( value ')
end rule

rule replaceToArray memberLists [repeat id]
    replace [nested_identifier]
        id [id] '.toArray( value [value] ')
    where
        memberLists [containsId id]
    by 
        id '.copy()
end rule

rule replaceRemoveAll memberLists [repeat id]
    replace [value]
        id [id] '.removeAll( otherList [value] ') cont [opt value_continuation]
    where
        memberLists [containsId id]
    by
        id '= 'list( 'filter( 'lambda 'a ': 'not 'a 'in otherList ', id ')) cont

end rule

