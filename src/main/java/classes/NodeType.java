package classes;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import ecb.interfaces.NodeTypeGetters;

public class NodeType implements NodeTypeGetters {
    private String type;
    private String expression;
    private String comment;

    @Override
    public boolean equals(Object obj) {
	boolean rValue = false;
	if (obj instanceof NodeType) {
	    NodeType type = (NodeType) obj;
	    rValue = this.type.equals(type.type) && expression.equals(type.expression);
	}
	return rValue;
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder(17, 31).append(type).append(expression).toHashCode();
    }

    public NodeType(String typeEqualsExpression) {
	setType(typeEqualsExpression);
	setExpression(typeEqualsExpression);
	setComment("");
    }

    public NodeType(String type, String expression) {
	setType(type);
	setExpression(expression);
	setComment("");
    }

    public NodeType(String type, String expression, String comment) {
	setType(type);
	setExpression(expression);
	setComment(comment);
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getExpression() {
	return expression;
    }

    public void setExpression(String expression) {
	this.expression = expression;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    @Override
    public String getComment() {
	return comment;
    }

    public String toString() {
	String rString = new String();
	rString = expression + " [" + type + "]";
	return rString;
    }
}
