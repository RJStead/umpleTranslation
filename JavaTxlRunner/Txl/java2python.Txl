% yava2python project
% main program
% Mykhaylo Sorochan, January 2009
% msorc@bigmir.net, macroexpand.org

% Copyright 2009 Mykhaylo Sorochan
%
% Redistribution and use in source and binary forms, with or without
% modification, are permitted provided that the following conditions are met:
%
%    Redistributions of source code must retain the above copyright notice,
%    this list of conditions and the following disclaimer.
%    Redistributions in binary form must reproduce the above copyright notice,
%    this list of conditions and the following disclaimer in the documentation
%    and/or other materials provided with the distribution.
%
%    THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
%    INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
%    AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
%    AUTHORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
%    OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
%    SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
%    INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
%    CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
%    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
%    POSSIBILITY OF SUCH DAMAGE.



% The base Java grammar and grammar overrides
include "Java.Grm"
include "pythonAddons.Grm"

%include "JavaCommentOverrides.Grm"
%#pragma -comment

include "java2pythonRules.Txl"


% Main translation rule 
function main
    replace [program] 
	P [program]
    by
	P [java2python]
end function

function java2python
    replace [program]
	P [program]
    by
	P [preparationTransformations]
 	  [transformImpEx] 
 	  [classTransforms]
	  [expressionTransforms]
	  [finalTransformations]
end function


function preparationTransformations
    replace [program]
	P [program]
    by
	P [exportClass]
	  [removeCastExpressions]
	  [expandJavaSwitch]
	  [javaPreUnary2PostUnary]
	  
	  [transformVariables]

	  [transformLiterals]

	  [transformBooleanOps]

	  [transformForToWhile]
	  [transformConditionalChoice]

	  [addMainIf]
end function

function finalTransformations
    replace [program]
	P [program]
    by
	P [transformSinglePostfixes]
	  [transformEmptyArray]
	  [systemOutPrintln2Print]
   	  [systemOutPrint2Print]
end function
