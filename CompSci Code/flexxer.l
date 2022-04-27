%{
#include "bison.tab.h"
#include <stdlib.h>
#include <SDL2/SDL.h>
%}

%%

end                       { return END; }
;                       { return END_STATEMENT; }
point                   { return POINT; }
line                    { return LINE; }
circle                  { return CIRCLE; }
rectangle               { return RECTANGLE; }
set_color               { return SET_COLOR; }
[0-9]{1,4}              { yylval.fVal = atof(yytext); return INT; }
[0-9]{1,4}\.[0-9]{1,5}  { yylval.fVal = atof(yytext); return INT; }
[ \t\n\r]               ;
.			{yyerror("found error");}
%%


