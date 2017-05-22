package enums.typesOfNodes;

import classes.abstractClasses.AbstractNode;
import ecb.interfaces.NodeTypeGetters;
import ecb.interfaces.TypeOfNode;
import ecb.interfaces.VtlBuild;
import ecb.interfaces.VtlCode;
import functions.XmlFunctions;

/**
 * Operators of type III: operator is visible, children are covered in
 * brackets<br>
 * The vtl code of such an operator is created as follows:<br>
 * vtl code = <code>operator.getType</code> {@link #bracket}.left
 * <code>child(0).getVtlCode()</code> {@link #separator}
 * <code>child(1).getVtlCode</code> {@link #separator} ...
 * {@link #bracket}.right<br>
 * example: union(setA,setB,...) where "setA" & "setB" are children, "," is the
 * separator
 * 
 * @author lindomi
 * @version 0.1
 */
public enum OperatorIII implements TypeOfNode, VtlBuild {
    SET_DIFF("setdiff", Bracket.NONE),
    SYM_DIFF("symdiff", Bracket.NONE),
    INTERSECT("intersect", Bracket.NONE),
    UNION("union", Bracket.NONE),
    COUNT("count", Bracket.ROUND),
    GET("get", true, 1, Bracket.ROUND, " ", ""),
    GROUP_BY("group by"),
    ISNULL("isnull", Bracket.ROUND),
    MIN("min", Bracket.ROUND),
    MAX("max", Bracket.ROUND),
    SUM("sum", Bracket.ROUND),
    SQRT("sqrt", Bracket.ROUND),
    AVG("avg", Bracket.ROUND),
    NOT_ISNULL("not isnull", Bracket.ROUND),
    ON("on"),
    RETURNS("returns"),
    THEN("then"),
    WHEN("when"),
    NOT("not"),
    IF("if", true, 2),
    ELSEIF("elseif", true, 2),
    ELSE("else"),
    ERRORCODE("errorcode", Bracket.ROUND),
    INPUT("input"),
    OUTPUT("output"),
    CALL("call", true, 1, Bracket.NONE, " ", " ", ";"),
    CHECK("check", true, 2, Bracket.ROUND, ", ", " "),
    HIERARCHY("hierarchy", true, 4, Bracket.ROUND, ", ", ""),
    TRANSCODE("transcode", true, 2, Bracket.ROUND, ", ", ""),
    PUT("put", true, 2, Bracket.ROUND, ", ", " "),
    SUBSTR("substr", true, null, Bracket.ROUND, ", ", ""),
    REPLACE("replace", true, null, Bracket.ROUND, ", ", ""),
    ;
    

    private String typeOfNode;
    private boolean hasChildren = true;
    private Integer numberOfChildren = 1;
    private Bracket bracket = Bracket.NONE;
    private String separator = " ";
    private String symbolBetweenOperatorAndChildren = " ";
    private String EOL = "";

    OperatorIII(String typeOfNode) {
	this.typeOfNode = typeOfNode;
    }

    OperatorIII(String typeOfNode, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.bracket = bracket;
    }

    OperatorIII(String typeOfNode, boolean hasChildren, Integer numberOfChildren) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
    }

    OperatorIII(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.bracket = bracket;
    }

    OperatorIII(String typeOfNode, boolean hasChildren, Integer numberOfChildren, String separator) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.separator = separator;
    }

    OperatorIII(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket, String separator,
	    String symbolBetweenOperatorAndChilds) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.bracket = bracket;
	this.separator = separator;
	this.symbolBetweenOperatorAndChildren = symbolBetweenOperatorAndChilds;
    }

    OperatorIII(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket, String separator,
	    String symbolBetweenOperatorAndChilds, String EOL) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.bracket = bracket;
	this.separator = separator;
	this.symbolBetweenOperatorAndChildren = symbolBetweenOperatorAndChilds;
	this.EOL = EOL;
    }

    
    OperatorIII(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket, String separator) {
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

    @Override
    public String getSymbolBetweenOperatorAndChildren() {
	return symbolBetweenOperatorAndChildren;
    }

    @Override
    public String getEOL() {
	return EOL;
    }
    
    @SuppressWarnings({ "hiding" })
    @Override
    public <T extends AbstractNode<S>, VtlBuild, S extends NodeTypeGetters> String buildVtlCode(T node,
	    boolean htmlConfig) {
	String rString = null;
	String comment = node.getData().getComment();
	String thisOpString = "";
	try {
	    rString = "";
	    for (int i = 0; i < node.getNumberOfChildren(); i++) {
		if (i == 0) {
		    if (htmlConfig) {
			rString += ((VtlCode) node.getChildAt(i)).getHtmlVtlCode();
		    } else {
			rString += ((VtlCode) node.getChildAt(i)).getVtlCode();
		    }
		} else {
		    if (htmlConfig) {
			rString += getSeparator() + ((VtlCode) node.getChildAt(i)).getHtmlVtlCode();
		    } else {
			rString += getSeparator() + ((VtlCode) node.getChildAt(i)).getVtlCode();
		    }
		}
	    }
	    if (comment != null && !comment.isEmpty()) {
		if (htmlConfig) {
		    thisOpString = XmlFunctions.includeToolTip(toString(), XmlFunctions.commentStyle(comment));
		} else {
		    thisOpString = comment + toString();
		}
	    } else {
		thisOpString = toString();
	    }
	    rString = thisOpString + getSymbolBetweenOperatorAndChildren()
		    + XmlFunctions.coverInBracket(rString, htmlConfig, this.getBracket()) + getEOL();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rString;
    }

}
