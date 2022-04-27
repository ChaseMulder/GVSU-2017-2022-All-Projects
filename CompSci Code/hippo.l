%{

#include <stdio.h>
void yyerror(char const * msg);

%}

%%

BEGIN 		{ printf("BEGIN\n");	}
END		{ printf("END\n");	}
-?[0-9]+	{ printf("INTEGER\n");	}
[-\+\*/]	{ printf("OPERATORS\n");}
.		{ yyerror(" I don't understand.\n");}
[ \t\n\r] 	v;

%%

void yyerror(char const * msg){
	fprintf( stderr, "%s\n", msg);
}

int main(int argc, char** argv){
	yylex();
}
