package staticObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import classes.Node;
import enums.typesOfNodes.OperatorI;
import enums.typesOfNodes.OperatorII;
import enums.typesOfNodes.OperatorIII;
import enums.typesOfNodes.OperatorIV;
import enums.typesOfNodes.OperatorV;
import enums.typesOfNodes.OperatorVI;
import enums.typesOfNodes.TreeLeaf;

public class StaticObjects {

    // -------------------------------------------------------------
    // html, xml utility
    // -------------------------------------------------------------

    public static Map<String, String> objectMap;
    
    public static List<String> linkedObjects;

    public static Map<String, ArrayList<String>> linkedObjectsMap;

    public static String transformation = "Transformation";

    public static String transformationScheme = "TransformationScheme";

    // -------------------------------------------------------------
    // grammar related objects
    // -------------------------------------------------------------

    /**
     * grammar: used to identify string objects in vtl expressions
     */
    public static String stringConstant = "StringC";

    public static String errorNodeImpl = "ErrorNodeImpl";

    public static String origin = "origin";

    public static String EOF = "EOF";

    /**
     * {@link String} used for {@link Node}s representing data sets
     */
    public static String dataSet = "Dataset";

    // -----------------------------------------------------------------------------------------------------------------------------------------------------
    // strings related to vtl tree structure

    public static String errorCodeMessage = "errorCodeMessage";

    public static String groupBy = "group by";

    public static String rulesetBody = "RulesetBody";

    public static String setRule = "SetRule";

    public static String joinFunction = "JoinFunction";

    public static String joinBody = "JoinBody";

    public static String procedureBody = "ProcedureBody";

    public static String ifThenElseExpression = "IfThenElseExpression";

    public static String ifThenElseStructure = "IfThenElseStructure";

    public static String listOfValues = "ListOfValues";

    public static String valueRepresentation = "ValueRepresentation";

    public static String datapointRulesetCall = "DatapointRulesetCall";

    public static String getFunction = "GetFunction";

    // -----------------------------------------------------------------------------------------------------------------------------------------------------
    // prefix for json objects

    public static String scalarJson = "_scalar";

    public static String connectionJson = "_connection";

    public static String json = ".json";

    // -----------------------------------------------------------------------------------------------------------------------------------------------------
    // column names of (excel) input files containing information about
    // transformation schemes, transformations, functions, rule sets, etc.

    public static String nameColumn = "ID";

    public static String contentColumn = "EXPRESSION";

    public static String commentColumn = "COMMENT";

    public static String transformationSchemeIdColumn = "TRANSFORMATION_SCHEME_ID";

    public static String orderColumn = "ORDER";

    public static String descriptionColumn = "DESCRIPTION";

    public static String phaseColumn = "PHASE";

    public static String typeColumn = "TYPE";

    public static String subtypeColumn = "SUBTYPE";

    public static String relatedEntityColumn = "RELATED_ENTITY";

    public static String detailedDescriptionColumn = "NATURAL_LANGUAGE";
    
    public static String subPhase = "SUBPHASE";
    
    public static String phaseNumber = "phaseNumber";
    
    public static String subPhaseNumber = "subphaseNumber";

    // -----------------------------------------------------------------------------------------------------------------------------------------------------
    // TODO create tree structure containing the table / column information;
    // create inserts from this tree structure
    // table names in the BIRD MS access database

    public static String createTable = "CREATE TABLE ";

    public static String dropTable = "DROP TABLE ";

    public static String insertInto = "INSERT INTO ";

    public static List<String> databaseTables = new ArrayList<String>();

    /**
     * name of access database table containing information about
     * {@link TransformationScheme} objects
     */
    public static String transformationSchemeTable = "TRANSFORMATION_SCHEME";

    /**
     * name of access database table containing information about
     * {@link TransformationScheme} objects representing functions
     */
    public static String functionTable = "FUNCTIONS";

    /**
     * name of access database table containing information about
     * {@link TransformationScheme} objects representing procedures
     */
    public static String procedureTable = "PROCEDURES";

    /**
     * name of access database table containing information about
     * {@link TransformationScheme} objects representing rule sets
     */
    public static String rulesetTable = "RULESETS";

    /**
     * name of access database table containing information about
     * {@link Transformation} objects
     */
    public static String transformationTable = "TRANSFORMATION";

    /**
     * name of access database table containing information about
     * {@link TransformationElement} objects
     */
    public static String transformationElementTable = "TRANSFORMATION_NODE";

    /**
     * creation string to create table {@link #transformationSchemeTable}
     */
    public static String createSchemesTable = createTable + transformationSchemeTable
	    + " (SCHEME_ID varchar(255), EXPRESSION MEMO, DESCRIPTION MEMO, PHASE varchar(255), SUBPHASE varchar(255), TYPE varchar(255), SUBTYPE varchar(255), RELATED_ENTITY varchar(255), NATURAL_LANGUAGE MEMO, PRIMARY KEY (SCHEME_ID))";

    /**
     * creation string to create table {@link #transformationSchemeTable}
     * representing functions
     */
    public static String createFunctionTable = createTable + functionTable
	    + " (SCHEME_ID varchar(255), EXPRESSION MEMO, DESCRIPTION MEMO, PHASE varchar(255), SUBPHASE varchar(255), TYPE varchar(255), SUBTYPE varchar(255), RELATED_ENTITY varchar(255), NATURAL_LANGUAGE MEMO, PRIMARY KEY (SCHEME_ID))";

    /**
     * creation string to create table {@link #transformationSchemeTable}
     * representing procedures
     */
    public static String createProcedureTable = createTable + procedureTable
	    + " (SCHEME_ID varchar(255), EXPRESSION MEMO, DESCRIPTION MEMO, PHASE varchar(255), SUBPHASE varchar(255), TYPE varchar(255), SUBTYPE varchar(255), RELATED_ENTITY varchar(255), NATURAL_LANGUAGE MEMO, PRIMARY KEY (SCHEME_ID))";

    /**
     * creation string to create table {@link #transformationSchemeTable}
     * representing data point rule sets
     */
    public static String createRulesetTable = createTable + rulesetTable
	    + " (SCHEME_ID varchar(255), EXPRESSION MEMO, DESCRIPTION MEMO, PHASE varchar(255), SUBPHASE varchar(255), TYPE varchar(255), SUBTYPE varchar(255), RELATED_ENTITY varchar(255), NATURAL_LANGUAGE MEMO, PRIMARY KEY (SCHEME_ID))";

    public static String createTransformationTable = createTable + transformationTable
	    + " (TRANSFORMATION_ID varchar(255), EXPRESSION MEMO, DESCRIPTION MEMO, SCHEME_ID varchar(255), ORDER int, PRIMARY KEY (TRANSFORMATION_ID))";

//    public static String createTransformationNodeTable = createTable + transformationElementTable
//	    + " (TRANSFORMATION_ID varchar(255), NODE_ID varchar(255), EXPRESSION MEMO, TYPE_OF_NODE varchar(255), LEVEL int, PARENT varchar(255), ORDER varchar(255), PRIMARY KEY(NODE_ID))";

    public static String createTransformationNodeTable = createTable + transformationElementTable
	    + " (TRANSFORMATION_ID varchar(255), NODE_ID varchar(255), EXPRESSION MEMO, TYPE_OF_NODE varchar(255), TYPE_OF_NODE_DETAIL varchar(255), LEVEL int, PARENT varchar(255), ORDER varchar(255), PRIMARY KEY(NODE_ID))";

    
    /**
     * @deprecated
     */
    public static String transformationTransformationElementTable = "TRANSFORMATION_TRANSFORMATION_ELEMENT";

    /**
     * @deprecated
     */
    public static String elementElementTable = "ELEMENT_ELEMENT";

    // static queries for export of objects into the BIRD MS access database
    public static String schemeInsert = insertInto + transformationSchemeTable
	    + " ([SCHEME_ID], [EXPRESSION], [DESCRIPTION], [PHASE], [SUBPHASE], [TYPE], [SUBTYPE], [RELATED_ENTITY], [NATURAL_LANGUAGE]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String functionInsert = insertInto + functionTable
	    + " ([SCHEME_ID], [EXPRESSION], [DESCRIPTION], [PHASE], [SUBPHASE], [TYPE], [SUBTYPE], [RELATED_ENTITY], [NATURAL_LANGUAGE]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String procedureInsert = insertInto + procedureTable
	    + " ([SCHEME_ID], [EXPRESSION], [DESCRIPTION], [PHASE], [SUBPHASE], [TYPE], [SUBTYPE], [RELATED_ENTITY], [NATURAL_LANGUAGE]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String rulesetInsert = insertInto + rulesetTable
	    + " ([SCHEME_ID], [EXPRESSION], [DESCRIPTION], [PHASE], [SUBPHASE], [TYPE], [SUBTYPE], [RELATED_ENTITY], [NATURAL_LANGUAGE]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String transformationInsert = insertInto + transformationTable
	    + " ([TRANSFORMATION_ID], [EXPRESSION], [DESCRIPTION], [SCHEME_ID], [ORDER]) VALUES (?, ?, ?, ?, ?)";

    public static String elementInsert = insertInto + transformationElementTable
	    + " ([TRANSFORMATION_ID], [NODE_ID], [EXPRESSION], [TYPE_OF_NODE], [TYPE_OF_NODE_DETAIL], [LEVEL], [PARENT], [ORDER]) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    // + " (TRANSFORMATION_ID varchar(255), NODE_ID varchar(255), EXPRESSION
    // MEMO, TYPE_OF_NODE varchar(255), LEVEL int, PARENT varchar(255), ORDER
    // int, PRIMARY KEY(NODE_ID))";

    public static String transforamtionElementInsert = "INSERT INTO " + transformationTransformationElementTable
	    + " ([TRANSFORMATION_ID], [ELEMENT_ID], [ORDER]) VALUES (?, ?, ?)";

    public static String elementElementInsert = "INSERT INTO " + elementElementTable
	    + " ([PARENT_ELEMENT_ID], [CHILD_ELEMENT_ID], [ORDER]) VALUES (?, ?, ?)";
    // -----------------------------------------------------------------------------------------------------------------------------------------------------
    // static strings related to the parse tree result
    public static String functionName = "FunctionName";

    public static String datapointRulesetName = "DatapointRulesetName";

    public static String procedureName = "ProcedureName";

    public static String rulesetName = "RulesetName";

    public static String errorMessage = "ErrorMessage";

    public static String datapointRule = "DatapointRule";

    public static String rulesetDefinition = "RulesetDefinition";

    public static String expressions = "Expressions";

    public static String createFunctionString = "create function";

    public static String terminalNodeImpl = "TerminalNodeImpl";

    public static String listOfInputParameters = "ListOfInputParameters";

    public static String functionDefinitionBody = "FunctionDefinitionBody";

    public static String listOfListItems = "ListOfListItems";

    public static String keepClause = "KeepClause";

    public static String filterClause = "FilterClause";

    public static String renameClause = "RenameClause";

    public static String dropClause = "DropClause";

    public static String clause = "Clause";

    public static String clauses = "Clauses";

    public static String dataDef = "DataDef";

    public static String dSet = "DSet";

    public static String dSetSimpleExpression = "DSetSimpleExpression";

    public static String dSetDotMathExpression = "DSetDotMathExpression";

    public static String dSetSimpleMathExpression = "DSetSimpleMathExpression";

    // -----------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * a {@link List} of {@link String} objects containing symbols which are
     * irrelevant for the comparison of a {@link Node} with it's
     * {@link Node#children}.
     */
    public static List<String> remains = new ArrayList<String>();

    /**
     * a {@link List} of {@link String} objects that need to be escaped when
     * replacing using the {@link String#replaceFirst(String, String)} method.
     */
    public static List<String> escapes = new ArrayList<String>();

    /**
     * a {@link Set} of {@link String} objects representing
     * {@code referenceNodes} (see sdmx information model)
     */
    public static Set<String> referenceNodes = new HashSet<String>();

    /**
     * a {@link Set} of {@link String} objects representing
     * {@code constantNodes} (see sdmx information model)
     */
    public static Set<String> constantNodes = new HashSet<String>();

    /**
     * a {@link Set} of {@link String} objects representing
     * {@code operatorNodes} (see sdmx information model)
     */
    public static Set<String> operatorNodes = new HashSet<String>();

    /**
     * a {@link Map} of {@link String}, {@link Map} of ({@link String},
     * {@link String}) objects containing mapping information to extract
     * (scalar) data from the BIRD MS access db. Please note that scalar
     * information / data in this context means none connection information
     */
    public static Map<String, Map<String, String>> tableMapping = new HashMap<String, Map<String, String>>();

    public static List<Map<String, List<String>>> connectionMap = new ArrayList<Map<String, List<String>>>();

    public static Map<String, Map<String, String>> connectionMapping = new HashMap<String, Map<String, String>>();

    public static Map<String, Map<String, String>> ddlMap = new HashMap<>();

    static {
	remains.add("(");
	remains.add(")");
	remains.add("[");
	remains.add("]");
	remains.add("{");
	remains.add("}");
	remains.add(",");
	remains.add(";");
	remains.add(":");
	remains.add("");

	escapes.add("+");
	escapes.add("*");
	escapes.add(")");
	escapes.add("(");
	escapes.add(".");
	escapes.add("<>");

	databaseTables.add(transformationSchemeTable);
	databaseTables.add(transformationTable);
	databaseTables.add(transformationElementTable);
	databaseTables.add(functionTable);
	databaseTables.add(procedureTable);
	databaseTables.add(rulesetTable);

	Map<String, String> map = new HashMap<>();
	map.put("SCHEME_ID", "varchar(255)");
	map.put("PHASE", "varchar(255)");
	map.put("TYPE", "varchar(255)");
	map.put("SUBTYPE", "varchar(255)");
	map.put("EXPRESSION", "MEMO");
	map.put("RELATED_ENTITY", "varchar(255)");
	ddlMap.put(transformationSchemeTable, map);

	map = new HashMap<>();
	map.put("TRANSFORMATION_ID", "varchar(255)");
	map.put("PHASE", "varchar(255)");
	map.put("TYPE", "varchar(255)");
	map.put("EXPRESSION", "MEMO");
	map.put("SCHEME_ID", "varchar(255)");
	ddlMap.put(transformationTable, map);

	tableMapping = new HashMap<String, Map<String, String>>();
	Map<String, String> mapping = new HashMap<String, String>();
	mapping.put("FRAMEWORK_ID", "name");
	mapping.put("DESCRIPTION", "description");
	tableMapping.put("FRAMEWORK", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("CUBE_ID", "name");
	mapping.put("DESCRIPTION", "description");
	mapping.put("INSTRUCTIONS", "instructions");
	tableMapping.put("CUBE", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("VARIABLE_ID", "name");
	mapping.put("DESCRIPTION", "description");
	mapping.put("DEFINITION", "definition");
	mapping.put("INPUT_LAYER_INSTRUCTIONS", "instructions");
	tableMapping.put("VARIABLE", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("DOMAIN_ID", "name");
	mapping.put("DESCRIPTION", "description");
	mapping.put("DEFINITION", "definition");
	tableMapping.put("DOMAIN", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("SUBDOMAIN_ID", "name");
	mapping.put("DESCRIPTION", "description");
	mapping.put("\"\"", "definition");
	tableMapping.put("SUBDOMAIN", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("MEMBER_TECHNICAL_ID", "name");
	mapping.put("MEMBER_ID", "code");
	mapping.put("DESCRIPTION", "description");
	mapping.put("DEFINITION", "definition");
	mapping.put("\"\"", "definition");
	tableMapping.put("MEMBER", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("CUBE_ID", "name");
	mapping.put("RELATED_ENTITY", "classification");
	connectionMapping.put("CUBE", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("CUBE_ID", "cubeName");
	mapping.put("FRAMEWORK_ID", "frameworkName");
	connectionMapping.put("CUBE", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("SUBDOMAIN_ID", "domainName");
	mapping.put("MEMBER_TECHNICAL_ID", "memberName");
	connectionMapping.put("SUBDOMAIN_ENUMERATION", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("DOMAIN_ID", "domainName");
	mapping.put("MEMBER_TECHNICAL_ID", "memberName");
	connectionMapping.put("MEMBER", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("DOMAIN_ID", "domainName");
	mapping.put("SUBDOMAIN_ID", "subdomainName");
	connectionMapping.put("SUBDOMAIN", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("DOMAIN_ID", "domainName");
	mapping.put("VARIABLE_ID", "variableName");
	connectionMapping.put("VARIABLE", mapping);

	mapping = new HashMap<String, String>();
	mapping.put("CUBE_ID", "cubeName");
	mapping.put("CUBE_VARIABLE_ID", "cvId");
	mapping.put("VARIABLE_ID", "variableName");
	mapping.put("VARIABLE_ROLE", "role");
	mapping.put("SUBDOMAIN_ID", "domainName");
	mapping.put("ATTRIBUTE_ASSOCIATED_VARIABLE", "relatedVariable");
	connectionMapping.put("CUBE_STRUCTURE_ITEM", mapping);

	operatorNodes = new HashSet<>()
	;

	for (OperatorI op : OperatorI.values()) {
	    operatorNodes.add(op.toString());
	}
	for (OperatorII op : OperatorII.values()) {
	    operatorNodes.add(op.toString());
	}
	for (OperatorIII op : OperatorIII.values()) {
	    operatorNodes.add(op.toString());
	}
	for (OperatorIV op : OperatorIV.values()) {
	    operatorNodes.add(op.toString());
	}
	for (OperatorV op : OperatorV.values()) {
	    operatorNodes.add(op.toString());
	}
	for (OperatorVI op : OperatorVI.values()) {
	    operatorNodes.add(op.toString());
	}
	

	referenceNodes = new HashSet<>()
	;

	constantNodes = new HashSet<>()
	;
	constantNodes.add(TreeLeaf.CONSTANT.toString());
	constantNodes.add(TreeLeaf.STRING_CONSTANT.toString());
	constantNodes.add(TreeLeaf.INTEGER_CONSTANT.toString());
	constantNodes.add(TreeLeaf.BOOLEAN_CONSTANT.toString());
	constantNodes.add(TreeLeaf.FLOAT_CONSTANT.toString());
	constantNodes.add(TreeLeaf.NUMBER.toString());
	constantNodes.add(TreeLeaf.INTEGER.toString());
	constantNodes.add(TreeLeaf.STRING.toString());
	constantNodes.add(TreeLeaf.FLOAT.toString());
	constantNodes.add(TreeLeaf.DATE.toString());
	constantNodes.add(TreeLeaf.INPUT_DATASET.toString());
	constantNodes.add(TreeLeaf.IDENTIFIER.toString());
	constantNodes.add(TreeLeaf.MEASURE.toString());
	constantNodes.add(TreeLeaf.ATTRIBUTE.toString());

	for (TreeLeaf leaf : TreeLeaf.values()) {
	    if (!constantNodes.contains(leaf.toString())) {
		referenceNodes.add(leaf.toString());
	    }
	}

	linkedObjects = new ArrayList<>();
	linkedObjects.add(TreeLeaf.CUBE.toString());
	linkedObjects.add(TreeLeaf.VARIABLE.toString());
	linkedObjects.add(TreeLeaf.FUNCTION.toString());
	linkedObjects.add(TreeLeaf.PROCEDURE.toString());
	linkedObjects.add(TreeLeaf.RULESET.toString());

	linkedObjectsMap = new HashMap<>();

	String styleFont = ", style=\"font-family:Courier New;\"";
	
	ArrayList<String> tList = new ArrayList<>(Arrays.asList("<a href=\"/cubes/", "-cube\""+styleFont+">", "</a>"));
	linkedObjectsMap.put(TreeLeaf.CUBE.toString(), tList);

	tList = new ArrayList<>(Arrays.asList("<a href=\"/variables/", "-variable\""+styleFont+">", "</a>"));
	linkedObjectsMap.put(TreeLeaf.VARIABLE.toString(), tList);

	tList = new ArrayList<>(Arrays.asList("<a href=\"/transformations/", "-ruleset\""+styleFont+">", "</a>"));
	linkedObjectsMap.put(TreeLeaf.RULESET.toString(), tList);

	tList = new ArrayList<>(Arrays.asList("<a href=\"/transformations/", "-function\""+styleFont+">", "</a>"));
	linkedObjectsMap.put(TreeLeaf.FUNCTION.toString(), tList);

//	tList = new ArrayList<>(Arrays.asList("<a href=\"/transformations/", "-procedure\", style=\"font-family:Courier New;\">", "</a>"));
	tList = new ArrayList<>(Arrays.asList("<a href=\"/transformations/", "-procedure\""+styleFont+">", "</a>"));
	linkedObjectsMap.put(TreeLeaf.PROCEDURE.toString(), tList);
	
	
	objectMap = new HashMap<>();
	objectMap.put(TreeLeaf.PERSISTENT_DATASET_ID.toString(), TreeLeaf.CUBE.toString());
	objectMap.put(TreeLeaf.DATASET_ID.toString(), TreeLeaf.CUBE.toString());
	objectMap.put(TreeLeaf.VARIABLE_ID.toString(), TreeLeaf.VARIABLE.toString());
	objectMap.put(TreeLeaf.FUNCTION_ID.toString(), TreeLeaf.FUNCTION.toString());
	objectMap.put(TreeLeaf.PROCEDURE_ID.toString(), TreeLeaf.PROCEDURE.toString());
	objectMap.put(TreeLeaf.RULESET_ID.toString(), TreeLeaf.RULESET.toString());

    }
}
