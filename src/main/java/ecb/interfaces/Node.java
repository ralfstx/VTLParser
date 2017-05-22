package ecb.interfaces;

import java.util.List;

/**
 * Node interface containing methods of the node structure
 * 
 * @author lindomi
 * @version 0.1
 * @param <T>
 *            the data type of the node
 */
public interface Node<T> {
    public T getParent();

    public void setParent(T parent, boolean set);

    default public void setParent(T parent) {
	setParent(parent, true);
    };

    public List<T> getChildren();

    public void setChilren(List<T> children);

    default public T getChildAt(int index) throws IndexOutOfBoundsException {
	T child = null;
	if (hasChildren()) {
	    child = getChildren().get(index);
	}
	return child;
    };

    default public boolean hasChildren() {
	return (getChildren() != null && !getChildren().isEmpty());
    };

    default public int getNumberOfChildren() {
	int rValue = 0;
	if (hasChildren()) {
	    rValue = getChildren().size();
	}
	return rValue;
    };

    default public void addChild(T child, boolean add){
	addChild(child, add, getChildren().size());
    };

    default public void addChild(T child, int position) {
	addChild(child, true, position);
    };
    
    default public void addChild(T child) {
	addChild(child, true);
    }
    
    public void addChild(T child, boolean add, int position);

    public void removeChild(T child);
    
}
