grammar CrudGenerator;

compileUnit
    :   (statement|functionDecl)* EOF
    ;


functionDecl
    :   returnType=(VOID_KEYWORD|TYPE) func=ID START_PARENTHESIS parameterDecl* (COMMA parameterDecl)* END_PARENTHESIS blockStatement
    ;

parameterDecl
    :   TYPE ID
    ;

statement
    :   blockStatement                                                                               # blockStmt
    |   type=(TYPE|VAR_KEYWORD) name=ID (ASSIGN expr)? SEMICOLON                                     # varDeclStmt
    |   ID ASSIGN expr SEMICOLON                                                                     # assignVarStmt
    |   RETURN_KEYWORD expr? SEMICOLON                                                               # returnStmt
    |   IF_KEYWORD START_PARENTHESIS expr END_PARENTHESIS statement (ELSE_KEYWORD statement)?        # ifStmt
    |   WHILE_KEYWORD START_PARENTHESIS expr END_PARENTHESIS statement                               # whileStmt
    |   stmtExpr=expr SEMICOLON                                                                      # exprStmt
    ;

blockStatement
    :   START_BLOCK_STATEMENT statement* END_BLOCK_STATEMENT
    ;

expr
    :   START_PARENTHESIS expr END_PARENTHESIS                                              # parensExpr
    |   value=INT                                                                           # intExpr
    |   value=DOUBLE                                                                        # doubleExpr
    |   value=(BOOLEAN_TRUE|BOOLEAN_FALSE)                                                  # booleanExpr
    |   value=ID                                                                            # variableExpr
    |   func=ID START_PARENTHESIS (expr (COMMA expr)*)? END_PARENTHESIS                     # funcExpr
    |   left=expr op=(OP_MUL|OP_DIV) right=expr                                             # infixExpr
    |   left=expr op=(OP_ADD|OP_SUB) right=expr                                             # infixExpr
    |   left=expr cpr=(GREATER_THAN|GREATER_THAN_EQ|LOWER_THAN|LOWER_THAN_EQ) right=expr    # compareExpr
    |   left=expr cpr=(NOT_EQUALS|EQUALS) right=expr                                        # compareExpr
    |   left=expr op=AND right=expr                                                         # logicalExpr
    |   left=expr op=OR right=expr                                                          # logicalExpr
    ;

OP_ADD         : '+';
OP_SUB         : '-';
OP_MUL         : '*';
OP_DIV         : '/';
ASSIGN         : '=';
NOT_EQUALS     : '!=';
EQUALS         : '==';
GREATER_THAN   : '>';
GREATER_THAN_EQ: '>=';
LOWER_THAN     : '<';
LOWER_THAN_EQ  : '<=';
AND            : '&&';
OR             : '||';

COMMA    : ',';
SEMICOLON: ';';

START_PARENTHESIS : '(';
END_PARENTHESIS   : ')';

START_BLOCK_STATEMENT : '{';
END_BLOCK_STATEMENT   : '}';

BOOLEAN_TRUE : 'true';
BOOLEAN_FALSE: 'false';

DOUBLE_SEPERATION: '.';

VOID_KEYWORD    : 'void';
TYPE            : INT_KEYWORD|DOUBLE_KEYWORD|BOOLEAN_KEYWORD;

WHILE_KEYWORD   : 'while';
IF_KEYWORD      : 'if';
ELSE_KEYWORD    : 'else';
INT_KEYWORD     : 'int';
DOUBLE_KEYWORD  : 'double';
BOOLEAN_KEYWORD : 'boolean';
VAR_KEYWORD     : 'var';
RETURN_KEYWORD  : 'return';

INT    :   [0-9]+;
DOUBLE :   [0-9]+ DOUBLE_SEPERATION [0-9]+;
BOOLEAN:   BOOLEAN_TRUE|BOOLEAN_FALSE;
ID     :   [a-zA-Z][a-zA-Z0-9]*;
WS     :   [ \t\r\n] -> channel(HIDDEN);

COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

