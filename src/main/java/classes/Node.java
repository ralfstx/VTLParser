package classes;

import classes.abstractClasses.AbstractNode;

public class Node<T> extends AbstractNode<T> {
    public int getDistanceToRoot() {
	int distance = 0;
	AbstractNode<T> parent = getParent();
	while (parent != null) {
	    parent = parent.getParent();
	    distance++;
	}
	return distance;
    }

}
