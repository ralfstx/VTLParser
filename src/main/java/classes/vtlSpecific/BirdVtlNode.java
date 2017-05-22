package classes.vtlSpecific;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import classes.NodeType;
import classes.abstractClasses.AbstractNode;
import ecb.interfaces.VtlCode;
import functions.Functions;
import staticObjects.StaticObjects;

/**
 * Vtl implementation of {@link AbstractNode} class.
 * 
 * @author lindomi
 * @version 0.1
 * @param <T>
 *            the type of node extending the {@link NodeType} class
 */
public class BirdVtlNode<T extends NodeType> extends AbstractNode<T> implements VtlCode {

    private String vtlCode;

    /**
     * Reorders the children of this node such that the final order reflects the
     * order in which the terms of the children are present in the expression of
     * this node.<br>
     * This method is not applied to origin or EOF nodes.
     */
    public void reOrderChildren() {
	if (getChildren() != null && !getChildren().isEmpty()) {
	    String expression = getData().getExpression();
	    if (!expression.equals(StaticObjects.origin) && !expression.equals("<" + StaticObjects.EOF + ">")) {
		if (expression != null && !expression.isEmpty()) {
		    Map<Integer, Boolean> isOccupied = new HashMap<>();
		    for (int i = 0; i < expression.length(); i++) {
			isOccupied.put(i, false);
		    }
		    SortedMap<Integer, BirdVtlNode<T>> map = new TreeMap<>();
		    List<AbstractNode<T>> children = getChildren();
		    Iterator<AbstractNode<T>> it = children.iterator();
		    while (it.hasNext()) {
			AbstractNode<T> child = it.next();
			String s = child.getData().getExpression();
			boolean found = false;
			int position = 0;
			do {
			    position = expression.indexOf(s, position);
			    if (position >= 0) {
				if (!isOccupied.get(position)) {
				    found = true;
				    map.put(position, (BirdVtlNode<T>) child);
				    for (int i = 0; i < s.length(); i++) {
					isOccupied.put(position + i, true);
				    }
				} else {
				    position++;
				}
			    } else {
				System.err.println("WARNING: could not find string '" + s + "' in expression '"
					+ expression + "'");
				found = true;
			    }
			} while (!found && position >= 0);
		    }
		    List<BirdVtlNode<T>> orderedChildren = null;
		    if (map != null && !map.isEmpty()) {
			orderedChildren = new ArrayList<>();
			for (Entry<Integer, BirdVtlNode<T>> entry : map.entrySet()) {
			    orderedChildren.add(entry.getValue());
			}
			Iterator<BirdVtlNode<T>> childIterator = orderedChildren.iterator();
			while (childIterator.hasNext()) {
			    BirdVtlNode<T> child = childIterator.next();
			    childIterator.remove();
			    removeChild(child);
			    addChild(child);
			}
		    } else {
			System.err.println("ERROR: could not initiate map!");
		    }
		}
	    }
	}
    }

    /**
     * Reverses the order of this node's children.
     */
    public void reverseOrderOfChildren() {
	List<AbstractNode<T>> refChildren = Functions.reverseOrder(getChildren());
	Iterator<AbstractNode<T>> it = refChildren.iterator();
	while (it.hasNext()) {
	    AbstractNode<T> child = it.next();
	    removeChild(child);
	    addChild(child);
	    it.remove();
	}
    }

    public void letSiblingsBecomeChildren() {
	letSiblingsBecomeChilren(true);
    }

    /**
     * Reorders the structure of this node such that the siblings of this node
     * become this node's children after this method is executed.
     * 
     * @param insertFirst
     *            a boolean variable specifying if the siblings should be
     *            inserted before any (already) existing children
     */
    public void letSiblingsBecomeChilren(boolean insertFirst) {
	if (getParent() != null) {
	    AbstractNode<T> parent = getParent();
	    List<AbstractNode<T>> siblings = (List<AbstractNode<T>>) parent.getChildren();
	    Iterator<AbstractNode<T>> it = siblings.iterator();
	    int index = 0;
	    while (it.hasNext()) {
		AbstractNode<T> node = it.next();
		if (!node.equals(this)) {
		    it.remove();
		    parent.removeChild(node);
		    if (insertFirst) {
			addChild(node, index);
			index++;
		    } else {
			addChild(node);
		    }
		}
	    }
	    parent.addChild(this);
	}
    }

    @Override
    public String toString() {
	String rString = new String();
	String comment = getData().getComment();
	if (comment != null && !comment.isEmpty()) {
	    rString += comment + "\n";
	}
	rString += data.toString();
	return rString;
    }

    @Override
    public String getVtlCode() {
	String comment = getData().getComment();
	if (comment != null && !comment.isEmpty()) {
	    return comment + vtlCode;
	} else {
	    return vtlCode;
	}
    }

    @Override
    public String getHtmlVtlCode() {
        return getVtlCode();
    }

    public void setVtlCode(String vtlCode) {
	this.vtlCode = vtlCode;
    }

}
