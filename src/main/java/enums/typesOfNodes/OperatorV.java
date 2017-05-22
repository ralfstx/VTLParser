package enums.typesOfNodes;

import classes.abstractClasses.AbstractNode;
import ecb.interfaces.NodeTypeGetters;
import ecb.interfaces.TypeOfNode;
import ecb.interfaces.VtlBuild;
import ecb.interfaces.VtlCode;
import functions.Functions;
import functions.XmlFunctions;

public enum OperatorV implements TypeOfNode, VtlBuild {
    FILTER("filter"),
    DROP("drop", Bracket.NONE),
    KEEP("keep", Bracket.NONE),
    RENAME("rename", Bracket.NONE),
    CALC("calc", Bracket.NONE);

    private String typeOfNode;
    private boolean hasChildren = true;
    private Integer numberOfChildren = 2;
    private Bracket bracket = Bracket.ROUND;
    private String separator = " ";

    OperatorV(String typeOfNode) {
	this.typeOfNode = typeOfNode;
    }

    OperatorV(String typeOfNode, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.bracket = bracket;
    }

    OperatorV(String typeOfNode, boolean hasChildren, Integer numberOfChildren) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
    }

    OperatorV(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.bracket = bracket;
    }

    OperatorV(String typeOfNode, boolean hasChildren, Integer numberOfChildren, String separator) {
	this.typeOfNode = typeOfNode;
	this.hasChildren = hasChildren;
	this.numberOfChildren = numberOfChildren;
	this.separator = separator;
    }

    OperatorV(String typeOfNode, boolean hasChildren, Integer numberOfChildren, Bracket bracket, String separator) {
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
	    // TODO statements containing get and multiple clause operators
	    T child0 = (T) node.getChildAt(0);
	    T child1 = (T) node.getChildAt(1);

	    String possibleDataset = (htmlConfig) ? ((VtlCode) child0).getHtmlVtlCode()
		    : ((VtlCode) child0).getVtlCode();
	    boolean isJoinedSet = (possibleDataset.startsWith("[")) ? true : false;
	    boolean containsClauseOp = Functions.contains(possibleDataset, OperatorV.values());
	    boolean isGetSet = (possibleDataset.startsWith("get")) ? true : false;
	    String symbolToFind = (isJoinedSet) ? "}" : "]";
	    symbolToFind = (isGetSet) ? ")" : symbolToFind;

	    String expression1 = (htmlConfig) ? ((VtlCode) child0).getHtmlVtlCode() : ((VtlCode) child0).getVtlCode();
	    String expression2 = (htmlConfig) ? ((VtlCode) child1).getHtmlVtlCode() : ((VtlCode) child1).getVtlCode();
	    rString = buildVtlCode(isJoinedSet, isGetSet, containsClauseOp, expression1, expression2, htmlConfig);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rString;
    }

    private String buildVtlCode(boolean isJoinedSet, boolean isGetSet, boolean containsClauseOp, String term1,
	    String term2, boolean htmlConfig) {
	String rString = new String();
	String symbolToFind = "";

	if (containsClauseOp) {
	    if (isJoinedSet && !isGetSet) {
		int pos = Functions.getPosition(term1, OperatorV.values());
		if (pos > 0) {
		    String transformation = term1;
		    rString = transformation.substring(0, pos) + toString() + separator
			    + XmlFunctions.coverInBracket(term2, htmlConfig, getBracket()) + ", "
			    + transformation.substring(pos, transformation.length());
		}
	    } else if (!isJoinedSet && isGetSet) {
		symbolToFind = ")";
		int pos = term1.lastIndexOf(symbolToFind);
		if (pos > 0) {
		    String transformation = term1;
		    rString = transformation.substring(0, pos) + ", " + toString() + separator
			    + XmlFunctions.coverInBracket(term2, htmlConfig, getBracket()) + symbolToFind;
		}
	    } else if (!isJoinedSet && !isGetSet) {
		symbolToFind = "]";
		int pos = term1.lastIndexOf(symbolToFind);
		if (pos > 0) {
		    String transformation = term1;
		    rString = transformation.substring(0, pos) + ", " + toString() + separator
			    + XmlFunctions.coverInBracket(term2, htmlConfig, getBracket()) + symbolToFind;
		}
	    } else {
		System.err.println(
			"[buildVtlCode()] ERROR: expression can not be a joined set and a get set at the same time!");
	    }
	} else {
	    // no clause operator found
	    if (isJoinedSet && !isGetSet) {
		symbolToFind = "}";
		int pos = term1.lastIndexOf(symbolToFind);
		if (pos > 0) {
		    String transformation = term1;
		    if (transformation.subSequence(pos - 1, pos).equals("{")) {
			rString = transformation.substring(0, pos) + toString() + separator
				+ XmlFunctions.coverInBracket(term2, htmlConfig, getBracket()) + symbolToFind;
		    } else {
			rString = transformation.substring(0, pos) + ", " + toString() + separator
				+ XmlFunctions.coverInBracket(term2, htmlConfig, getBracket()) + symbolToFind;
		    }
		}
	    } else if (!isJoinedSet && isGetSet) {
		symbolToFind = ")";
		int pos = term1.lastIndexOf(symbolToFind);
		if (pos > 0) {
		    String transformation = term1;
		    rString = transformation.substring(0, pos) + ", " + toString() + separator
			    + XmlFunctions.coverInBracket(term2, htmlConfig, getBracket()) + symbolToFind;
		}
	    } else if (!isJoinedSet && !isGetSet) {
		rString = term1 + separator + "[" + toString() + separator
			+ XmlFunctions.coverInBracket(term2, htmlConfig, getBracket()) + "]";
	    } else {
		System.err.println(
			"[buildVtlCode()] ERROR: expression can not be a joined set and a get set at the same time!");
	    }
	}
	return rString;
    }
}
