grammar CrudGenerator;

compileUnit
    :   (modelDecl)* EOF
    ;

modelDecl
    :
      MODEL_KEYWORD name=ID START_BLOCK_STATEMENT key=modelKey modelFields* (MODEL_REPO MODEL_FIELD_SEPERATOR START_BLOCK_ARRAY modelRepo* END_BLOCK_ARRAY)? END_BLOCK_STATEMENT
    ;

modelKey
    :
      MODEL_KEY MODEL_FIELD_SEPERATOR type=MODEL_TYPES
    ;

modelFields
    :
      name=ID MODEL_FIELD_SEPERATOR type=(MODEL_TYPES|MODEL_COLLECTION|ID) (START_PARANTHESIS collection=ID END_PARAENTHESIS)?
    ;

modelRepo
    :
      name=ID
    ;


MODEL_KEYWORD         : 'model';
MODEL_FIELD_SEPERATOR : ':';
MODEL_TYPES           : 'String'|'Int'|'Long'|'Text'|'Byte'|'Short'|'Double';
MODEL_COLLECTION      : 'List'|'Set';
MODEL_KEY             : 'key';
MODEL_REPO            : 'repo';

START_BLOCK_STATEMENT : '{';
END_BLOCK_STATEMENT   : '}';
EQUALS_SIGN           : '=';
START_PARANTHESIS     : '(';
END_PARAENTHESIS      : ')';
START_BLOCK_ARRAY     : '[';
END_BLOCK_ARRAY       : ']';

WS           :   [ \t\r\n]                 -> channel(HIDDEN);
COMMENT      :            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT :       '//' ~[\r\n]*         -> channel(HIDDEN);
ID           :   [a-zA-Z][a-zA-Z]*;
