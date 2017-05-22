package classes.abstractClasses;

import java.util.ArrayList;
import java.util.List;

import ecb.interfaces.Node;


/**
 * abstract class representing a node
 * 
 * @author lindomi
 * @version 0.1
 * @param <T>
 *            the type of data / information this node contains
 */
public abstract class AbstractNode<T> implements Node<AbstractNode<T>> {
    protected T data;

    protected AbstractNode<T> parent;

    protected List<AbstractNode<T>> children;

    public AbstractNode() {
	children = new ArrayList<AbstractNode<T>>();
    }

    public T getData() {
	return data;
    }

    public void setData(T data) {
	this.data = data;
    }

    @Override
    public AbstractNode<T> getParent() {
	return parent;
    }

    @Override
    public void setParent(AbstractNode<T> parent, boolean set) {
	this.parent = parent;
	if (parent != null && set) {
	    parent.addChild(this, false);
	}
    }

    @Override
    public List<AbstractNode<T>> getChildren() {
	return children;
    }

    /**
     * sets {@link #children}. Please note that - in opposition to the other
     * methods provided by this class - this method does not set the
     * {@link #parent} of each child.
     */
    @Override
    public void setChilren(List<AbstractNode<T>> children) {
	this.children = children;
    }

    public void addChild(AbstractNode<T> child, boolean add, int position) {
	if (getChildren().contains(child)) {
	    getChildren().set(getChildren().indexOf(child), child);
	} else {
	    try {
		getChildren().add(position, child);
	    } catch (Exception e) {
		System.out.println("WARNING [addChild()]: was not able to insert child at the given position ('"+position+"', '"+getNumberOfChildren()+"')!");
		getChildren().add(child);
	    }
	}
	if (add) {
	    child.setParent(this, false);
	}
    }

    @Override
    public void removeChild(AbstractNode<T> child) {
	if (getChildren().remove(child)) {
	    child.setParent(null);
	}
    }

    @Override
    public String toString() {
	return data.toString();
    }
 
}
