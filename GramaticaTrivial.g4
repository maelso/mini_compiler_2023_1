grammar GramaticaTrivial;

exp		:	exp OP termo |
			termo
		;

termo	:	ID | NUM
		;
		
OP		:	'+' | '-' | '*' | '/'
		;
		
ID		:	[a-z]([a-z] | [A-Z] | [0-9])*
		;
		
NUM		:	[0-9]+
		;
		
WHITESPACE	: (' ' | '\t' | '\n' | '\r' | '\r\n') -> skip
			;
		
		
		
		
		
		
		