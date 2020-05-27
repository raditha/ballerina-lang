/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.ballerinalang.compiler.internal.parser;

/**
 * Parser rule contexts that represent each point in the grammar.
 * These represents the current scope during the parsing.
 *
 * @since 1.2.0
 */
public enum ParserRuleContext {

    // Productions
    COMP_UNIT("comp-unit"),
    EOF("eof"),
    TOP_LEVEL_NODE("top-level-node"),
    TOP_LEVEL_NODE_WITHOUT_METADATA("top-level-node-without-metadata"),
    TOP_LEVEL_NODE_WITHOUT_MODIFIER("top-level-node-without-modifier"),
    FUNC_DEF("func-def"),
    FUNC_DEF_OR_FUNC_TYPE("func-def-or-func-type"),
    PARAM_LIST("parameters"),
    PARAMETER_START("parameter"),
    PARAMETER_WITHOUT_ANNOTS("parameter-without-annots"),
    PARAM_END("param-end"),
    REQUIRED_PARAM("parameter"),
    DEFAULTABLE_PARAM("defaultable-parameter"),
    REST_PARAM("rest-parameter"),
    AFTER_PARAMETER_TYPE("after-parameter-type"),
    PARAMETER_NAME_RHS("parameter-name-rhs"),
    REQUIRED_PARAM_NAME_RHS("required-param-name-rhs"),
    FUNC_OPTIONAL_RETURNS("func-optional-returns"),
    FUNC_BODY("func-body"),
    OBJECT_FUNC_BODY("object-method-body"),
    FUNC_BODY_OR_TYPE_DESC_RHS("func-body-or-type-desc-rhs"),
    ANON_FUNC_BODY("annon-func-body"),
    FUNC_TYPE_DESC_END("func-type-desc-end"),

    EXTERNAL_FUNC_BODY("external-func-body"),
    FUNC_BODY_BLOCK("func-body-block"),
    MODULE_TYPE_DEFINITION("type-definition"),
    FIELD_OR_REST_DESCIPTOR_RHS("field-or-rest-descriptor-rhs"),
    FIELD_DESCRIPTOR_RHS("field-descriptor-rhs"),
    RECORD_BODY_START("record-body-start"),
    RECORD_BODY_END("record-body-end"),
    RECORD_FIELD("record-field"),
    RECORD_FIELD_OR_RECORD_END("record-field-orrecord-end"),
    RECORD_FIELD_START("record-field-start"),
    RECORD_FIELD_WITHOUT_METADATA("record-field-without-metadata"),
    TYPE_DESCRIPTOR("type-descriptor"),
    RECORD_TYPE_DESCRIPTOR("record-type-desc"),
    TYPE_REFERENCE("type-reference"),
    ARG_LIST("arguments"),
    ARG_START("argument-start"),
    ARG_LIST_START("("),
    ARG_LIST_END(")"),
    ARG_START_OR_ARG_LIST_END("arg-start-or-args-list-end"),
    NAMED_OR_POSITIONAL_ARG_RHS("named-or-positional-arg"),
    ARG_END("arg-end"),
    OBJECT_TYPE_DESCRIPTOR("object-type-desc"),
    OBJECT_MEMBER("object-member"),
    OBJECT_MEMBER_START("object-member-start"),
    OBJECT_MEMBER_WITHOUT_METADATA("object-member-without-metadata"),
    OBJECT_FUNC_OR_FIELD("object-func-or-field"),
    OBJECT_FUNC_OR_FIELD_WITHOUT_VISIBILITY("object-func-or-field-without-visibility"),
    OBJECT_METHOD_START("object-method-start"),
    OBJECT_FIELD_RHS("object-field-rhs"),
    OBJECT_TYPE_FIRST_QUALIFIER("object-type-qualifier"),
    OBJECT_TYPE_SECOND_QUALIFIER("object-type-second-qualifier"),
    OBJECT_TYPE_DESCRIPTOR_START("object-type-desc-start"),
    IMPORT_DECL("import-decl"),
    IMPORT_ORG_OR_MODULE_NAME("import-org-or-module-name"),
    IMPORT_MODULE_NAME("module-name"),
    IMPORT_VERSION_DECL("import-version-decl"),
    VERSION_NUMBER("sem-ver"),
    IMPORT_SUB_VERSION("import-sub-version"),
    MAJOR_VERSION("major-version"),
    MINOR_VERSION("minor-version"),
    PATCH_VERSION("patch-version"),
    IMPORT_PREFIX("import-prefix"),
    IMPORT_PREFIX_DECL("import-alias"),
    IMPORT_DECL_RHS("import-decl-rhs"),
    AFTER_IMPORT_MODULE_NAME("after-import-module-name"),
    MAJOR_MINOR_VERSION_END("major-minor-version-end"),
    SERVICE_DECL("service-decl"),
    OPTIONAL_SERVICE_NAME("service-rhs"),
    LISTENERS_LIST("listeners-list"),
    RESOURCE_DEF("resource-def"),
    LISTENER_DECL("listener-decl"),
    CONSTANT_DECL("const-decl"),
    CONST_DECL_TYPE("const-decl-type"),
    CONST_DECL_RHS("const-decl-rhs"),
    NIL_TYPE_DESCRIPTOR("nil-type-descriptor"),
    OPTIONAL_TYPE_DESCRIPTOR("optional-type-descriptor"),
    ARRAY_TYPE_DESCRIPTOR("array-type-descriptor"),
    ARRAY_LENGTH("array-length"),
    ANNOT_REFERENCE("annot-reference"),
    ANNOTATIONS("annots"),
    ANNOTATION_END("annot-end"),
    ANNOTATION_REF_RHS("annot-ref-rhs"),
    DOC_STRING("doc-string"),
    QUALIFIED_IDENTIFIER("qualified-identifier"),
    EQUAL_OR_RIGHT_ARROW("equal-or-right-arrow"),
    ANNOTATION_DECL("annotation-decl"),
    ANNOT_DECL_OPTIONAL_TYPE("annot-decl-optional-type"),
    ANNOT_DECL_RHS("annot-decl-rhs"),
    ANNOT_OPTIONAL_ATTACH_POINTS("annot-optional-attach-points"),
    ANNOT_ATTACH_POINTS_LIST("annot-attach-points-list"),
    ATTACH_POINT("attach-point"),
    ATTACH_POINT_IDENT("attach-point-ident"),
    SINGLE_KEYWORD_ATTACH_POINT_IDENT("single-keyword-attach-point-ident"),
    IDENT_AFTER_OBJECT_IDENT("ident-after-object-ident"),
    XML_NAMESPACE_DECLARATION("xml-namespace-decl"),
    XML_NAMESPACE_PREFIX_DECL("namespace-prefix-decl"),
    DEFAULT_WORKER_INIT("default-worker-init"),
    NAMED_WORKERS("named-workers"),
    WORKER_NAME_RHS("worker-name-rhs"),
    DEFAULT_WORKER("default-worker-init"),
    KEY_SPECIFIER("key-specifier"),
    KEY_SPECIFIER_RHS("key-specifier-rhs"),
    TABLE_KEY_RHS("table-key-rhs"),
    LET_EXPR_LET_VAR_DECL("let-expr-let-var-decl"),
    LET_CLAUSE_LET_VAR_DECL("let-clause-let-var-decl"),
    LET_VAR_DECL_START("let-var-decl-start"),
    FUNC_TYPE_DESC("func-type-desc"),
    FUNCTION_KEYWORD_RHS("func-keyword-rhs"),
    END_OF_TYPE_DESC("end-of-type-desc"),
    INFERRED_TYPE_DESC("*"),
    SELECT_CLAUSE("select-clause"),
    WHERE_CLAUSE("where-clause"),
    FROM_CLAUSE("from-clause"),
    LET_CLAUSE("let-clause"),
    AMBIGUOUS_FUNC_TYPE_DESC_RHS("module-func-type-desc-rhs"),
    EXPLICIT_ANON_FUNC_EXPR_BODY_START("explicit-anon-func-expr-body-start"),
    BRACED_EXPR_OR_ANON_FUNC_PARAMS("braced-expr-or-anon-func-params"),
    BRACED_EXPR_OR_ANON_FUNC_PARAM_RHS("braced-expr-or-anon-func-param-rhs"),
    ANON_FUNC_PARAM_RHS("anon-func-param-rhs"),
    IMPLICIT_ANON_FUNC_PARAM("implicit-anon-func-param"),
    OPTIONAL_PEER_WORKER("optional-peer-worker"),
    WORKER_NAME_OR_METHOD_NAME("worker-name-or-method-name"),
    PEER_WORKER_NAME("peer-worker-name"),
    TYPE_DESC_IN_TUPLE_RHS("type-desc-in-tuple-rhs"),
    NIL_OR_PARENTHESISED_TYPE_DESC_RHS("nil-or-parenthesised-tpe-desc-rhs"),
    REMOTE_CALL_OR_ASYNC_SEND_RHS("remote-call-or-async-send-rhs"),
    REMOTE_CALL_OR_ASYNC_SEND_END("remote-call-or-async-send-end"),
    DEFAULT_WORKER_NAME_IN_ASYNC_SEND("default-worker-name-in-async-send"),
    RECEIVE_WORKERS("receive-workers"),
    MULTI_RECEIVE_WORKERS("multi-receive-workers"),
    RECEIVE_FIELD_END("receive-field-end"),
    RECEIVE_FIELD("receive-field"),
    RECEIVE_FIELD_NAME("receive-field-name"),
    INFER_PARAM_END_OR_PARENTHESIS_END("infer-param-end-or-parenthesis-end"),
    LIST_CONSTRUCTOR_MEMBER_END("list-constructor-member-end"),
    TYPED_BINDING_PATTERN("typed-binding-pattern"),
    BINDING_PATTERN("binding-pattern"),
    CAPTURE_BINDING_PATTERN("capture-binding-pattern"),
    REST_BINDING_PATTERN("rest-binding-pattern"),
    LIST_BINDING_PATTERN("list-binding-pattern"),
    LIST_BINDING_PATTERN_CONTENTS("list-binding-pattern-content"),
    LIST_BINDING_PATTERN_END_OR_CONTINUE("list-binding-pattern-end-or-continue"),
    WAIT_KEYWORD_RHS("wait-keyword-rhs"),
    MULTI_WAIT_FIELDS("multi-wait-fields"),
    WAIT_FIELD_NAME("wait-field-name"),
    WAIT_FIELD_NAME_RHS("wait-field-name-rhs"),
    WAIT_FIELD_END("wait-field-end"),
    WAIT_FUTURE_EXPR_END("wait-future-expr-end"),
    ALTERNATE_WAIT_EXPRS("alternate-wait-exprs"),
    ALTERNATE_WAIT_EXPR_LIST_END("alternate-wait-expr-lit-end"),
    DO_CLAUSE("do-clause"),
    MODULE_ENUM_DECLARATION("module-enum-declaration"),
    MODULE_ENUM_NAME("module-enum-name"),
    ENUM_MEMBER_NAME("enum-member-name"),
    MEMBER_ACCESS_KEY_EXPR_END("member-access-key-expr-end"),
    MEMBER_ACCESS_KEY_EXPR("member-access-key-expr"),
    RETRY_KEYWORD_RHS("retry-keyword-rhs"),
    RETRY_TYPE_PARAM_RHS("retry-type-param-rhs"),
    RETRY_BODY("retry-body"),
    ROLLBACK_RHS("rollback-rhs"),
    LIST_BP_OR_TUPLE_TYPE_DESC("list-bp-or-tuple-type-desc"),
    LIST_BP_OR_TUPLE_TYPE_MEMBER("list-bp-or-tuple-type-member"),
    LIST_BP_OR_TUPLE_TYPE_DESC_RHS("list-bp-or-tuple-type-desc-rhs"),
    BRACKETED_LIST("bracketed-list"),
    BRACKETED_LIST_RHS("bracketed-list-rhs"),
    BRACKETED_LIST_MEMBER("bracketed-list-member"),
    BRACKETED_LIST_MEMBER_END("bracketed-list-member-end"),
    LIST_BINDING_MEMBER_OR_ARRAY_LENGTH("list-binding-member-or-array-length"),
    TYPED_BINDING_PATTERN_TYPE_RHS("type-binding-pattern-type-rhs"),
    UNION_OR_INTERSECTION_TOKEN("union-or-intersection"),

    // Statements
    STATEMENT("statement"),
    STATEMENT_WITHOUT_ANNOTS("statement-without-annots"),
    ASSIGNMENT_STMT("assignment-stmt"),
    VAR_DECL_STMT("var-decl-stmt"),
    VAR_DECL_STMT_RHS("var-decl-rhs"),
    STATEMENT_START_IDENTIFIER("type-or-var-name"),
    ASSIGNMENT_OR_VAR_DECL_STMT("assign-or-var-decl"),
    ASSIGNMENT_OR_VAR_DECL_STMT_RHS("assign-or-var-decl-rhs"),
    IF_BLOCK("if-block"),
    BLOCK_STMT("block-stmt"),
    ELSE_BLOCK("else-block"),
    ELSE_BODY("else-body"),
    WHILE_BLOCK("while-block"),
    CALL_STMT("call-statement"),
    CALL_STMT_START("call-statement-start"),
    CONTINUE_STATEMENT("continue-statement"),
    BREAK_STATEMENT("break-statement"),
    PANIC_STMT("panic-statement"),
    RETURN_STMT("return-stmt"),
    RETURN_STMT_RHS("return-stmt-rhs"),
    COMPOUND_ASSIGNMENT_STMT("compound-assignment-statement"),
    LOCAL_TYPE_DEFINITION_STMT("local-type-definition-statement"),
    STMT_START_IDENTIFIER_RHS("stmt-start-identifier-rhs"),
    STMT_START_WITH_EXPR_RHS("stmt-start-with-expr-rhs"),
    EXPRESSION_STATEMENT("expression-statement"),
    EXPRESSION_STATEMENT_START("expression-statement-start"),
    LOCK_STMT("lock-stmt"),
    NAMED_WORKER_DECL("named-worker-decl"),
    FORK_STMT("fork-stmt"),
    FOREACH_STMT("foreach-stmt"),
    TRANSACTION_STMT("transaction-stmt"),
    RETRY_STMT("retry-stmt"),
    ROLLBACK_STMT("rollback-stmt"),
    AMBIGUOUS_STMT("ambiguous-stmt"),

    // Keywords
    RETURNS_KEYWORD("returns"),
    TYPE_KEYWORD("type"),
    PUBLIC_KEYWORD("public"),
    PRIVATE_KEYWORD("private"),
    REMOTE_KEYWORD("remote"),
    FUNCTION_KEYWORD("function"),
    EXTERNAL_KEYWORD("external"),
    RECORD_KEYWORD("record"),
    OBJECT_KEYWORD("object"),
    ABSTRACT_KEYWORD("abstract"),
    CLIENT_KEYWORD("client"),
    IF_KEYWORD("if"),
    ELSE_KEYWORD("else"),
    WHILE_KEYWORD("while"),
    CONTINUE_KEYWORD("continue"),
    BREAK_KEYWORD("break"),
    PANIC_KEYWORD("panic"),
    IMPORT_KEYWORD("import"),
    VERSION_KEYWORD("version"),
    AS_KEYWORD("as"),
    RETURN_KEYWORD("return"),
    SERVICE_KEYWORD("service"),
    ON_KEYWORD("on"),
    RESOURCE_KEYWORD("resource"),
    FINAL_KEYWORD("final"),
    LISTENER_KEYWORD("listener"),
    CONST_KEYWORD("const"),
    TYPEOF_KEYWORD("typeof"),
    IS_KEYWORD("is"),
    MAP_KEYWORD("map"),
    FUTURE_KEYWORD("future"),
    TYPEDESC_KEYWORD("typedesc"),
    NULL_KEYWORD("null"),
    LOCK_KEYWORD("lock"),
    ANNOTATION_KEYWORD("annotation"),
    SOURCE_KEYWORD("source"),
    XMLNS_KEYWORD("xmlns"),
    WORKER_KEYWORD("worker"),
    FORK_KEYWORD("fork"),
    TRAP_KEYWORD("trap"),
    IN_KEYWORD("in"),
    FOREACH_KEYWORD("foreach"),
    TABLE_KEYWORD("table"),
    KEY_KEYWORD("key"),
    ERROR_KEYWORD("error"),
    LET_KEYWORD("let"),
    STREAM_KEYWORD("stream"),
    XML_KEYWORD("xml"),
    STRING_KEYWORD("string"),
    NEW_KEYWORD("new"),
    FROM_KEYWORD("from"),
    WHERE_KEYWORD("where"),
    SELECT_KEYWORD("select"),
    START_KEYWORD("start"),
    FLUSH_KEYWORD("flush"),
    DEFAULT_KEYWORD("default"),
    WAIT_KEYWORD("wait"),
    DO_KEYWORD("do"),
    TRANSACTION_KEYWORD("transaction"),
    COMMIT_KEYWORD("commit"),
    RETRY_KEYWORD("retry"),
    ROLLBACK_KEYWORD("rollback"),
    TRANSACTIONAL_KEYWORD("transactional"),
    ENUM_KEYWORD("enum"),
    BASE16_KEYWORD("base16"),
    BASE64_KEYWORD("base64"),

    // Syntax tokens
    OPEN_PARENTHESIS("("),
    CLOSE_PARENTHESIS(")"),
    OPEN_BRACE("{"),
    CLOSE_BRACE("}"),
    ASSIGN_OP("="),
    SEMICOLON(";"),
    COLON(":"),
    COMMA(","),
    ELLIPSIS("..."),
    QUESTION_MARK("?"),
    ASTERISK("*"),
    CLOSED_RECORD_BODY_START("{|"),
    CLOSED_RECORD_BODY_END("|}"),
    DOT("."),
    OPEN_BRACKET("["),
    CLOSE_BRACKET("]"),
    SLASH("/"),
    AT("@"),
    RIGHT_ARROW("->"),
    GT(">"),
    LT("<"),
    PIPE("|"),
    TEMPLATE_START("`"),
    TEMPLATE_END("`"),
    LT_TOKEN("<"),
    GT_TOKEN(">"),
    ERROR_TYPE_PARAM_START("<"),
    PARENTHESISED_TYPE_DESC_START("("),
    BITWISE_AND_OPERATOR("&"),
    EXPR_FUNC_BODY_START("=>"),
    PLUS_TOKEN("+"),
    MINUS_TOKEN("-"),
    TUPLE_TYPE_DESC_START("["),
    SYNC_SEND_TOKEN("->>"),
    LEFT_ARROW_TOKEN("<-"),
    ANNOT_CHAINING_TOKEN(".@"),
    OPTIONAL_CHAINING_TOKEN("?."),

    // Other terminals
    FUNC_NAME("function-name"),
    VARIABLE_NAME("variable"),
    SIMPLE_TYPE_DESCRIPTOR("simple-type-desc"),
    BINARY_OPERATOR("binary-operator"),
    TYPE_NAME("type-name"),
    BOOLEAN_LITERAL("boolean-literal"),
    CHECKING_KEYWORD("checking-keyword"),
    SERVICE_NAME("service-name"),
    COMPOUND_BINARY_OPERATOR("compound-binary-operator"),
    UNARY_OPERATOR("unary-operator"),
    FUNCTION_IDENT("func-ident"),
    FIELD_IDENT("field-ident"),
    OBJECT_IDENT("object-ident"),
    RESOURCE_IDENT("resource-ident"),
    RECORD_IDENT("record-ident"),
    ANNOTATION_TAG("annotation-tag"),
    ATTACH_POINT_END("attach-point-end"),
    IDENTIFIER("identifier"),
    NAMESPACE_PREFIX("namespace-prefix"),
    WORKER_NAME("worker-name"),

    // Expressions
    EXPRESSION("expression"),
    TERMINAL_EXPRESSION("terminal-expression"),
    EXPRESSION_RHS("expression-rhs"),
    FUNC_CALL("func-call"),
    BASIC_LITERAL("basic-literal"),
    ACCESS_EXPRESSION("access-expr"),   // method-call, field-access, member-access
    DECIMAL_INTEGER_LITERAL("decimal-int-literal"),
    VARIABLE_REF("var-ref"),
    STRING_LITERAL("string-literal"),
    MAPPING_CONSTRUCTOR("mapping-constructor"),
    MAPPING_FIELD("maping-field"),
    FIRST_MAPPING_FIELD("first-mapping-field"),
    MAPPING_FIELD_NAME("maping-field-name"),
    SPECIFIC_FIELD_RHS("specific-field-rhs"),
    COMPUTED_FIELD_NAME("computed-field-name"),
    MAPPING_FIELD_END("mapping-field-end"),
    TYPEOF_EXPRESSION("typeof-expr"),
    UNARY_EXPRESSION("unary-expr"),
    HEX_INTEGER_LITERAL("hex-integer-literal"),
    NIL_LITERAL("nil-literal"),
    CONSTANT_EXPRESSION("constant-expr"),
    CONSTANT_EXPRESSION_START("constant-expr-start"),
    DECIMAL_FLOATING_POINT_LITERAL("decimal-floating-point-literal"),
    HEX_FLOATING_POINT_LITERAL("hex-floating-point-literal"),
    LIST_CONSTRUCTOR("list-constructor"),
    LIST_CONSTRUCTOR_FIRST_MEMBER("list-constructor-first-member"),
    TYPE_CAST("type-cast"),
    TYPE_CAST_PARAM("type-cast-param"),
    TYPE_CAST_PARAM_RHS("type-cast-param-rhs"),
    TABLE_CONSTRUCTOR("table-constructor"),
    TABLE_KEYWORD_RHS("table-keyword-rhs"),
    ROW_LIST_RHS("row-list-rhs"),
    TABLE_ROW_END("table-row-end"),
    NEW_KEYWORD_RHS("new-keyword-rhs"),
    IMPLICIT_NEW("implicit-new"),
    LET_EXPRESSION("let-expr"),
    ANON_FUNC_EXPRESSION("anon-func-expression"),
    TABLE_CONSTRUCTOR_OR_QUERY_EXPRESSION("table-constructor-or-query-expr"),
    TABLE_CONSTRUCTOR_OR_QUERY_START("table-constructor-or-query-start"),
    TABLE_CONSTRUCTOR_OR_QUERY_RHS("table-constructor-or-query-rhs"),
    QUERY_EXPRESSION("query-expr"),
    FIELD_ACCESS_IDENTIFIER("field-access-identifier"),
    QUERY_PIPELINE_RHS("query-pipeline-rhs"),
    LET_CLAUSE_END("let-clause-end"),
    CONDITIONAL_EXPRESSION("conditional-expr"),
    SERVICE_CONSTRUCTOR_EXPRESSION("service-constructor-expression"),

    // Contexts that expect a type
    TYPE_DESC_IN_ANNOTATION_DECL("type-desc-annotation-descl"),
    TYPE_DESC_BEFORE_IDENTIFIER("type-desc-before-identifier"), // object/record fields, params, const, listener
    TYPE_DESC_IN_RECORD_FIELD("type-desc-in-record-field"),
    TYPE_DESC_IN_PARAM("type-desc-in-param"),
    TYPE_DESC_IN_TYPE_BINDING_PATTERN("type-desc-in-type-binding-pattern"), // foreach, let-var-decl, var-decl
    TYPE_DESC_IN_TYPE_DEF("type-def-type-desc"),                            // local/mdule type defitions
    TYPE_DESC_IN_ANGLE_BRACKETS("type-desc-in-angle-bracket"),              // type-cast, parameterized-type
    TYPE_DESC_IN_RETURN_TYPE_DESC("type-desc-in-return-type-desc"),
    TYPE_DESC_IN_EXPRESSION("type-desc-in-expression"),
    TYPE_DESC_IN_STREAM_TYPE_DESC("type-desc-in-stream-type-desc"),
    TYPE_DESC_IN_TUPLE("type-desc-in-tuple"),
    TYPE_DESC_IN_PARENTHESIS("type-desc-in-parenthesis"),
    TYPE_DESC_IN_NEW_EXPR("type-desc-in-new-expr"),
    VAR_DECL_STARTED_WITH_DENTIFIER("var-decl-started-with-dentifier"),

    // XML
    XML_CONTENT("xml-content"),
    XML_TAG("xml-tag"),
    XML_START_OR_EMPTY_TAG("xml-start-or-empty-tag"),
    XML_START_OR_EMPTY_TAG_END("xml-start-or-empty-tag-end"),
    XML_END_TAG("xml-end-tag"),
    XML_NAME("xml-name"),
    XML_PI("xml-pi"),
    XML_TEXT("xml-text"),
    XML_ATTRIBUTES("xml-attributes"),
    XML_ATTRIBUTE("xml-attribute"),
    XML_ATTRIBUTE_VALUE_ITEM("xml-attribute-value-item"),
    XML_ATTRIBUTE_VALUE_TEXT("xml-attribute-value-text"),
    XML_COMMENT_START("<!--"),
    XML_COMMENT_END("-->"),
    XML_COMMENT_CONTENT("xml-comment-content"),
    XML_PI_START("<?"),
    XML_PI_END("?>"),
    XML_PI_DATA("xml-pi-data"),
    XML_PI_TARGET_RHS("xml-pi-target-rhs"),
    INTERPOLATION_START_TOKEN("${"),
    INTERPOLATION("interoplation"),
    TEMPLATE_BODY("template-body"),
    TEMPLATE_MEMBER("template-member"),
    TEMPLATE_STRING("template-string"),
    TEMPLATE_STRING_RHS("template-string-rhs"),
    XML_QUOTE_START("xml-quote-start"),
    XML_QUOTE_END("xml-quote-end"),

    //Other
    PARAMETERIZED_TYPE("parameterized-type"),
    TYPEDESC_RHS("type-desc-rhs"),
    ERROR_TYPE_PARAMS("error-type-params"),
    STREAM_TYPE_FIRST_PARAM_RHS("stream-type-params"),
    KEY_CONSTRAINTS_RHS("key-constraints-rhs"),
    ROW_TYPE_PARAM("row-type-param"),
    TABLE_TYPE_DESC_RHS("table-type-desc-rhs"),
    SIGNED_INT_OR_FLOAT_RHS("signed-int-or-float-rhs"),
    ENUM_MEMBER_LIST("enum-member-list"),
    ENUM_MEMBER_RHS("enum-member-rhs"),
    ENUM_MEMBER_INTERNAL_RHS("enum-member-internal-rhs"),
    ENUM_MEMBER_START("enum-member-start"),
    ;

    private String value;

    ParserRuleContext(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
