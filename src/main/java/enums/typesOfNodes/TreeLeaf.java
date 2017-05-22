package enums.typesOfNodes;

import java.util.ArrayList;

import classes.abstractClasses.AbstractNode;
import ecb.interfaces.NodeTypeGetters;
import ecb.interfaces.TypeOfNode;
import ecb.interfaces.VtlBuild;
import functions.XmlFunctions;
import staticObjects.StaticObjects;

public enum TreeLeaf implements TypeOfNode, VtlBuild {
    CUBE("CUBE_ID"),
    VARIABLE("VARIABLE_ID"),
    MEMBER("MEMBER_ID"),

    PERSISTENT_DATASET_ID("PersistentDatasetID"),
    COMPONENT_ID("ComponentID"),
    VARIABLE_ID("VarID"),
    DATASET_ID("DatasetID"),

    MAPPING_ID("MappingID"),

    CONSTANT("Constant"),
    STRING_CONSTANT("StringC"),
    INTEGER_CONSTANT("IntegerC"),
    FLOAT_CONSTANT("FloatC"),
    BOOLEAN_CONSTANT("BooleanC"),
    NULL("null"),

    IDENTIFIER("Identifier"),
    MEASURE("Measure"),
    ATTRIBUTE("Attribute"),

    // FUNCTION_NAME ("FunctionID"),
    // RULESET_NAME ("RulesetID"),
    // PROCEDURE_NAME ("ProcedureID"),

    FUNCTION("FUNCTION_ID"),
    PROCEDURE("PROCEDURE_ID"),
    RULESET("RULESET_ID"),

    FUNCTION_ID("FunctionID"),
    PROCEDURE_ID("ProcedureID"),
    RULESET_ID("RulesetID"),

    NUMBER("number"),
    INTEGER("integer"),
    STRING("string"),
    FLOAT("float"),
    DATE("date"),
    INPUT_DATASET("dataset"),

    INNER("inner"),
    OUTER("outer"),
    LEFT("left"),
    RIGHT("right"),
    CROSS("cross"),
    
    DATASET_ALIAS_ID("DatasetAliasID")

    ;

    private String typeOfNode;
    private boolean hasChildren = false;
    private Integer numberOfChildren = null;
    private Bracket bracket = Bracket.NONE;
    private String separator = " ";

    TreeLeaf(String typeOfNode) {
	this.typeOfNode = typeOfNode;
    }

    TreeLeaf(String typeOfNode, boolean hasChildren, Integer numberOfChildren) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
    }

    TreeLeaf(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.bracket = bracket;
    }

    TreeLeaf(String typeOfNode, boolean hasChildren, Integer numberOfChildren, String separator) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.separator = separator;
    }

    TreeLeaf(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket, String separator) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.bracket = bracket;
	this.separator = separator;
    }

    @Override
    public String toString() {
	return typeOfNode;
    }

    @Override
    public boolean isHasChildren() {
	return hasChildren;
    }

    @Override
    public Integer getNumberOfChildren() {
	return numberOfChildren;
    }

    @Override
    public Bracket getBracket() {
	return bracket;
    }

    @Override
    public String getSeparator() {
	return separator;
    }

    @SuppressWarnings("hiding")
    @Override
    public <T extends AbstractNode<S>, VtlBuild, S extends NodeTypeGetters> String buildVtlCode(T node,
	    boolean htmlConfig) {
	String rString = "";
	String expression = node.getData().getExpression();
	String comment = node.getData().getComment();
	String type = node.getData().getType();
	if (htmlConfig) {
	    if (StaticObjects.linkedObjects.contains(type)) {
		ArrayList<String> list = StaticObjects.linkedObjectsMap.get(type);
		rString = list.get(0) + expression + list.get(1) + expression + list.get(2);
	    } else {
		// rString = expression;
		rString = XmlFunctions.lineStyle(expression);
	    }
	    if (comment != null && !comment.isEmpty()) {
		if (type.equals(TreeLeaf.MEMBER.toString())) {
		    rString = XmlFunctions.includeToolTip(rString, XmlFunctions.commentStyle(comment));

		} else {
		    rString = XmlFunctions.includeToolTip(rString, XmlFunctions.commentStyle(comment));
		}
	    }
	} else {
	    if (comment != null && !comment.isEmpty()) {
		if (!type.equals(TreeLeaf.MEMBER.toString())) {
		    rString = comment + expression;
		} else {
		    if (!expression.startsWith(comment)) {
			    rString = expression;			
		    }
		}
	    } else {
		rString = expression;
	    }
	}
	return rString;
    }
}
