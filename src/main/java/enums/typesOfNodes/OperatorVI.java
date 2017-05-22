package enums.typesOfNodes;

import classes.abstractClasses.AbstractNode;
import ecb.interfaces.NodeTypeGetters;
import ecb.interfaces.TypeOfNode;
import ecb.interfaces.VtlBuild;
import ecb.interfaces.VtlCode;

public enum OperatorVI implements TypeOfNode, VtlBuild {
    RULE_ID("RuleID");

    private String typeOfNode;
    private boolean hasChildren = true;
    private Integer numberOfChildren = null;
    private Bracket bracket = Bracket.NONE;
    private String separator = " ";
    private String symbolBetweenOperatorAndChildren = " ";
    private String EOL = ";";

    OperatorVI(String typeOfNode) {
	this.typeOfNode = typeOfNode;
    }

    public String toString() {
	return typeOfNode;
    }

    @SuppressWarnings("hiding")
    @Override
    public <T extends AbstractNode<S>, VtlBuild, S extends NodeTypeGetters> String buildVtlCode(T node,
	    boolean htmlConfig) {
	String rString = null;
	try {
	    rString = "";
	    rString = node.getData().getExpression() + ":" + symbolBetweenOperatorAndChildren;
	    for (int i = 0; i < node.getNumberOfChildren(); i++) {
		if (i == 0) {
		    rString += (htmlConfig) ? ((VtlCode) node.getChildAt(i)).getHtmlVtlCode()
			    : ((VtlCode) node.getChildAt(i)).getVtlCode();
		} else if (i == node.getNumberOfChildren() - 1) {
		    rString += getSeparator();
		    rString += (htmlConfig) ? ((VtlCode) node.getChildAt(i)).getHtmlVtlCode()
			    : ((VtlCode) node.getChildAt(i)).getVtlCode();
		} else {
		    rString += getSeparator();
		    rString += (htmlConfig) ? ((VtlCode) node.getChildAt(i)).getHtmlVtlCode() + "</br>"
			    : ((VtlCode) node.getChildAt(i)).getVtlCode() + "\n";
		}
	    }
	    rString += EOL;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rString;
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
}
