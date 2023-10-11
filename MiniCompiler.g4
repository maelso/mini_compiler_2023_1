grammar MiniCompiler;

programa : 
	':' 'DECLARACOES' listaDeclaracoes ':' 'ALGORITMO' listaComandos;

listaDeclaracoes : 
	declaracao listaDeclaracoes | 
	declaracao;

declaracao : 
	VARIAVEL ':' tipoVar;

tipoVar : 
	'INTEIRO' | 
	'REAL';

expressaoAritmetica : 
	expressaoAritmetica '+' termoAritmetico | 
	expressaoAritmetica '-' termoAritmetico | 
	termoAritmetico;

termoAritmetico : 
	termoAritmetico '*' fatorAritmetico | 
	termoAritmetico '/' fatorAritmetico | 
	fatorAritmetico;

fatorAritmetico : 
	NUMINT | 
	NUMREAL | 
	VARIAVEL | 
	LEFT_PAR expressaoAritmetica RIGHT_PAR;

expressaoRelacional : 
	expressaoRelacional operadorBooleano termoRelacional | 
	termoRelacional;

termoRelacional : 
	expressaoAritmetica OP_REL expressaoAritmetica | 
	LEFT_PAR expressaoRelacional RIGHT_PAR;

operadorBooleano : 
	'E' | 
	'OU';

listaComandos : 
	comando listaComandos | 
	comando;

comando : 
	comandoAtribuicao | 
	comandoEntrada | 
	comandoSaida | 
	comandoCondicao | 
	comandoRepeticao | 
	subAlgoritmo;

comandoAtribuicao : 
	VARIAVEL '=' expressaoAritmetica;

comandoEntrada :	'LER' VARIAVEL {System.out.println("Lendo variavel " + _input.LT(-1).getText());} 
				;

comandoSaida : 
	'IMPRIMIR' LEFT_PAR (VARIAVEL | CADEIA) RIGHT_PAR;

comandoCondicao : 
	'SE' expressaoRelacional 'ENTAO' comando | 
	'SE' expressaoRelacional 'ENTAO' comando 'SENAO' comando;

comandoRepeticao : 
	'ENQUANTO' expressaoRelacional comando;

subAlgoritmo : 
	'INICIO' listaComandos 'FIM';
	
VARIAVEL :
	[a-z] ([a-z] | [A-Z] | [0-9])*;
	
NUMINT	: [0-9]+
		;
		
NUMREAL	: [0-9]+ ('.' [0-9]+)?
		;
		
LEFT_PAR: '('
		;

RIGHT_PAR: ')'
		;
		
OP_REL	: '>' | '>=' | '<' | '<=' | '==' | '!='
		;
CADEIA	: '"' ([a-z] | [A-Z] | [0-9])* '"'
		;
WHITESPACE	: (' ' | '\n' | '\t' | '\r' | '\r\n') -> skip
			;
LINE_COMMENT : '//' ~[\r\n]* -> skip
			;
MULTI_LINE_COMMENT : '/*' .*? '*/' -> skip ;
		
		