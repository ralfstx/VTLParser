package classes.abstractClasses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import ecb.interfaces.Tree;


/**
 * abstract class representing a tree structure
 * 
 * @author lindomi
 * @version 0.1
 * @param <T>
 *            the type of node
 * @param <S>
 *            the type of data / information the node contains
 */
public abstract class AbstractTree<T extends AbstractNode<S>, S> implements Tree<T, S> {

    private static final String tab = "\t";

    private T root;

    @Override
    public T getRoot() {
	return root;
    }

    @Override
    public void setRoot(T root) {
	this.root = root;
    }

    public List<T> getLeafs() {
	List<T> leafs = null;
	if (getRoot() != null) {
	    leafs = new ArrayList<T>();
	    leafs.addAll(auxiliaryGetLeafs(getRoot()));
	}
	return leafs;
    }

    public List<T> auxiliaryGetLeafs(T node) {
	List<T> leafs = new ArrayList<T>();
	if (node.hasChildren()) {
	    for (AbstractNode<S> nodeChild : node.getChildren()) {
		@SuppressWarnings("unchecked")
		T child = (T) nodeChild;
		if (child.hasChildren()) {
		    leafs.addAll(auxiliaryGetLeafs(child));
		} else {
		    leafs.add(child);
		}
	    }
	}
	return leafs;
    }

    @Override
    public String toString() {
	String rString = new String();
	if (getRoot() != null) {
	    rString = build(true).toString();
	}
	return rString;
    }

    public String toStringWithDepth() {
	return toStringWithDepth(true);
    }

    public String toStringWithDepth(boolean order) {
	String rString = new String();
	if (getRoot() != null) {
	    for (Entry<T, Integer> entry : buildWithDepth(order).entrySet()) {
		for (int i = 0; i < entry.getValue(); i++) {
		    System.out.print(tab);
		}
		System.out.println(entry.getKey().toString().replace("\n", "").replace("\t", ""));
	    }
	}
	return rString;
    }

    @SuppressWarnings("unchecked")
    public Set<T> getAllNodes(boolean includeRoot) {
	Set<T> rSet = new HashSet<>();
	List<T> leafs = getLeafs();
	Iterator<T> it = leafs.iterator();
	while (it.hasNext()) {
	    T node = it.next();
	    it.remove();
	    rSet.add(node);
	    T parent = (T) node.getParent();
	    while (parent != null) {
		rSet.add(parent);
		parent = (T) parent.getParent();
	    }
	}
	if (includeRoot) {
	    rSet.add(getRoot());
	}
	return rSet;
    }

    public Set<T> getAllNodes() {
	return getAllNodes(false);
    }

    public int getMaximumDistance() {
	int distance = 0;
	List<T> leafs = getLeafs();
	if (leafs != null && !leafs.isEmpty()) {
	    Iterator<T> it = leafs.iterator();
	    while (it.hasNext()) {
		T leaf = it.next();
		it.remove();
		if (getDistanceToRoot(leaf) > distance) {
		    distance = getDistanceToRoot(leaf);
		}
	    }
	}
	return distance;
    }

    public Set<T> getAllNodesWithDistance(int distance) {
	Set<T> rSet = new HashSet<>();
	Set<T> nodes = getAllNodes();
	Iterator<T> it = nodes.iterator();
	while (it.hasNext()) {
	    T node = it.next();
	    it.remove();
	    if (getDistanceToRoot(node) == distance) {
		rSet.add(node);
	    }
	}
	return rSet;
    }

}
