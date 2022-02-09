include "Umple.Grm"
include "Python.Grm"

function main
    replace [program] 
	P [program]
    by
	P [transformClass]
end function

define program
    [class_declaration]
end define

rule transformClass
    replace [class_declaration]
        'class Name [id] _ [class_body_declaration]
    by
	'class Name ':
    'def '__init__(self):
        'pass
    
    'def '__init__(self):
        'pass 
end rule