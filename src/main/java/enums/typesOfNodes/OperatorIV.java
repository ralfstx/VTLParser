package enums.typesOfNodes;

import classes.abstractClasses.AbstractNode;
import ecb.interfaces.NodeTypeGetters;
import ecb.interfaces.TypeOfNode;
import ecb.interfaces.VtlBuild;
import ecb.interfaces.VtlCode;
import functions.XmlFunctions;

/**
 * Operators of type IV: method information nodes<br>
 * The vtl code of such an operator is created as follows:<br>
 * vtl code = <code>child(0).getVtlCode()</code> {@link #separator}
 * {@link #bracket}.left <code>child(1).getVtlCode</code>
 * {@link #bracket}.right<br>
 * example: distance (x, y) where "distance" is the first child, "x, y" the
 * second child and "(" / ")" are the brackets
 * 
 * @author lindomi
 *
 */
public enum OperatorIV implements TypeOfNode, VtlBuild {
    PROCEDURE_INFO("Procedure information", "define procedure ", Bracket.NONE),
    FUNCTION_INFO("Function information", "create function ", Bracket.NONE),
    RULESET_INFO("Ruleset information", "define datapoint ruleset ", Bracket.NONE);

    private String typeOfNode;
    private boolean hasChildren = true;
    private Integer numberOfChildren = 2;
    private Bracket bracket = Bracket.ROUND;
    private String separator = " ";
    private String alias = "";

    OperatorIV(String typeOfNode) {
	this.typeOfNode = typeOfNode;
    }

    OperatorIV(String typeOfNode, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.bracket = bracket;
    }

    OperatorIV(String typeOfNode, String alias, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.alias = alias;
	this.bracket = bracket;
    }

    OperatorIV(String typeOfNode, boolean hasChildren, Integer numberOfChildren) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
    }

    OperatorIV(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.bracket = bracket;
    }

    OperatorIV(String typeOfNode, boolean hasChildren, Integer numberOfChildren, String separator) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.separator = separator;
    }

    OperatorIV(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket, String separator) {
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
	    T child0 = (T) node.getChildAt(0);
	    T child1 = (T) node.getChildAt(1);
	    // check for comment
	    String expression = (htmlConfig) ? ((VtlCode) child0).getHtmlVtlCode() : ((VtlCode) child0).getVtlCode();
	    if (expression.startsWith("/*")) {
		int pos = expression.indexOf("*/");
		if (pos > 0) {
		    rString = expression.substring(0, pos + 2) + alias
			    + expression.substring(pos + 2, expression.length()) + separator
			    + XmlFunctions.coverInBracket(((VtlCode) child1).getVtlCode(), htmlConfig, getBracket());
		}
	    } else {
		if (htmlConfig) {
		    rString = alias + ((VtlCode) child0).getHtmlVtlCode() + separator
			    + XmlFunctions.coverInBracket(((VtlCode) child1).getHtmlVtlCode(), htmlConfig, getBracket());
		} else {
		    rString = alias + ((VtlCode) child0).getVtlCode() + separator
			    + XmlFunctions.coverInBracket(((VtlCode) child1).getVtlCode(), htmlConfig, getBracket());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rString;
    }
}
