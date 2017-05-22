package enums.typesOfNodes;

import classes.abstractClasses.AbstractNode;
import ecb.interfaces.NodeTypeGetters;
import ecb.interfaces.TypeOfNode;
import ecb.interfaces.VtlBuild;
import ecb.interfaces.VtlCode;
import functions.XmlFunctions;

/**
 * Operators of type II: operator is not visible, children are covered in
 * brackets<br>
 * The vtl code of such an operator is created as follows:<br>
 * vtl code = {@link #bracket}.left <code>child(0).getVtlCode()</code>
 * {@link #separator} <code>child(1).getVtlCode</code> {@link #separator} ...
 * {@link #bracket}.right<br>
 * example: (x,y,...) where "x" & "y" are children, "," is the separator
 * 
 * @author lindomi
 * @version 0.1
 */
public enum OperatorII implements TypeOfNode, VtlBuild {
    PARAMETERS("Parameters", Bracket.ROUND, ", "),
    IF_THEN_ELSE("IfThenElseExpr", Bracket.ROUND_NEXTLINE_INDENT, " </br>"),
    JOIN_CLAUSE("JoinClause", true, 3, Bracket.SQUARE),
    TYPE_OF_JOIN("Type of join", true, 1),
    DATASETS_TO_BE_JOINED("Datasets to be joined", Bracket.NONE, ", "),
    JOIN_BODY("JoinBody", true, null, Bracket.CURLED),
    JOIN_EXPRESSION("JoinExpr", true, 2),
    FUNCTION_BODY("FunctionBody", true, 1, Bracket.CURLED_NEXTLINE_INDENT),
    PROCEDURE_BODY("ProcedureBody", true, null, Bracket.CURLED_NEXTLINE_INDENT, " </br>"),
    RULESET_BODY("RulesetBody", true, null, Bracket.CURLED_NEXTLINE_INDENT, " </br>"),
    FUNCTION_DEFINITION("Function definition"),
    RULESET_DEFINITION("Ruleset definition"),
    PROCEDURE_DEFINITION("Procedure definition"),
    FUNCTION_CALL("FunctionCall", true, 2, Bracket.ROUND),
    AGGREGATE_FUNCTION("AggregateFunction", true, 2, Bracket.NONE);

    private String typeOfNode;
    private boolean hasChildren = true;
    private Integer numberOfChildren = null;
    private Bracket bracket = Bracket.NONE;
    private String separator = " ";

    OperatorII(String typeOfNode) {
	this.typeOfNode = typeOfNode;
    }

    OperatorII(String typeOfNode, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.bracket = bracket;
    }

    OperatorII(String typeOfNode, Bracket bracket, String separator) {
	this.typeOfNode = typeOfNode;
	this.bracket = bracket;
	this.separator = separator;
    }

    OperatorII(String typeOfNode, boolean hasChildren, Integer numberOfChildren) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
    }

    OperatorII(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.bracket = bracket;
    }

    OperatorII(String typeOfNode, boolean hasChildren, Integer numberOfChildren, String separator) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.separator = separator;
    }

    OperatorII(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket, String separator) {
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

    @SuppressWarnings({ "unchecked", "hiding" })
    @Override
    public <T extends AbstractNode<S>, VtlBuild, S extends NodeTypeGetters> String buildVtlCode(T node,
	    boolean htmlConfig) {
	String rString = null;
	try {
	    rString = "";
	    for (int i = 0; i < node.getNumberOfChildren(); i++) {
		T child = (T) node.getChildAt(i);
		if (i == 0) {
		    if (htmlConfig) {
			rString += ((VtlCode) child).getHtmlVtlCode();
		    } else {
			rString += ((VtlCode) child).getVtlCode();
		    }
		} else {
		    if (htmlConfig) {
			rString += getSeparator() + ((VtlCode) child).getHtmlVtlCode();
		    } else {
			rString += getSeparator() + ((VtlCode) child).getVtlCode();
		    }
		}
	    }
	    rString = XmlFunctions.coverInBracket(rString, htmlConfig, getBracket());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rString;
    }

}
