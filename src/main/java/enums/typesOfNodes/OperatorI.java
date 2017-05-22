package enums.typesOfNodes;

import classes.abstractClasses.AbstractNode;
import ecb.interfaces.NodeTypeGetters;
import ecb.interfaces.TypeOfNode;
import ecb.interfaces.VtlBuild;
import ecb.interfaces.VtlCode;

/**
 * Operators of type I: operator is illustrated between the first and the second
 * child<br>
 * The vtl code of such an operator is created as follows:<br>
 * vtl code = <code>child(0)getVtlCode()</code> {@link #separator}
 * <code>operator.getType()</code> {@link #separator}
 * <code>child(1).getVtlCode()</code>
 * 
 * @author lindomi
 * @version 0.1
 */
public enum OperatorI implements TypeOfNode, VtlBuild {
    MINUS("-", Bracket.ROUND),
    PLUS("+", Bracket.ROUND),
    MULTIPLY("*", Bracket.ROUND),
    DIVIDE("/", Bracket.ROUND),
    EQUALS("="),
    NOT_EQUALS("<>"),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    AND("and"),
    OR("or", Bracket.ROUND),
    IN("in"),
    NOT_IN("not in"),
    DEF(":=", " ", ";"),
    DOT(".", ""),
    AS("as"),
    ROLE("role"),
    CONCAT("||", Bracket.ROUND),
    ;

    private String typeOfNode;
    private boolean hasChildren = true;
    private Integer numberOfChildren = 2;
    private Bracket bracket = Bracket.NONE;
    private String separator = " ";
    private String EOL = "";

    OperatorI(String typeOfNode) {
	this.typeOfNode = typeOfNode;
    }

    OperatorI(String typeOfNode, String separator) {
	this.typeOfNode = typeOfNode;
	this.separator = separator;
    }

    OperatorI(String typeOfNode, String separator, String EOL) {
	this.typeOfNode = typeOfNode;
	this.separator = separator;
	this.EOL = EOL;
    }

    
    
    OperatorI(String typeOfNode, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.bracket = bracket;
    }

    OperatorI(String typeOfNode, boolean hasChildren, Integer numberOfChildren) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
    }

    OperatorI(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.bracket = bracket;
    }

    OperatorI(String typeOfNode, boolean hasChildren, Integer numberOfChildren, String separator) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.separator = separator;
    }

    OperatorI(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket, String separator) {
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
    public String getEOL() {
	return EOL;
    }

    @SuppressWarnings({ "unchecked", "hiding" })
    @Override
    public <T extends AbstractNode<S>, VtlBuild, S extends NodeTypeGetters> String buildVtlCode(T node, boolean htmlConfig) {
	String rString = null;
	try {
	    T child0 = (T) node.getChildAt(0);
	    T child1 = (T) node.getChildAt(1);
	    if (htmlConfig) {
		    rString = ((VtlCode) child0).getHtmlVtlCode() + separator + node.getData().getType() + separator
			    + ((VtlCode) child1).getHtmlVtlCode() + getEOL();
	    } else {
		    rString = ((VtlCode) child0).getVtlCode() + separator + node.getData().getType() + separator
			    + ((VtlCode) child1).getVtlCode() + getEOL();		
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rString;
    }

}
