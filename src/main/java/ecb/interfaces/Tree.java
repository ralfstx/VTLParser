package ecb.interfaces;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import classes.abstractClasses.AbstractNode;

/**
 * tree interface containing methods of a tree structure
 * 
 * @author lindomi
 * @version 0.1
 * @param <T>
 *            the type of node
 * @param <S>
 *            the data type of the node <b>T</b>
 */
public interface Tree<T extends AbstractNode<S>, S> {
    public T getRoot();

    public void setRoot(T root);

    default public boolean isEmpty() {
	return (getRoot() == null);
    };

    default public boolean exists(S dataToFind) {
	return (find(dataToFind) != null);
    }

    default public T find(S dataToFind) {
	T returnNode = null;
	if (getRoot() != null) {
	    returnNode = auxiliaryFind(getRoot(), dataToFind);
	}
	return returnNode;
    };

    @SuppressWarnings("unchecked")
    default public T auxiliaryFind(T currentNode, S dataToFind) {
	T returnNode = null;
	int i = 0;
	if (currentNode.getData().equals(dataToFind)) {
	    returnNode = currentNode;
	} else if (currentNode.hasChildren()) {
	    while (returnNode == null && i < currentNode.getNumberOfChildren()) {
		returnNode = auxiliaryFind((T) currentNode.getChildAt(i), dataToFind);
		i++;
	    }
	}
	return returnNode;
    };

    default public int getNumberOfNodes() {
	int numberOfNodes = 0;
	if (getRoot() != null) {
	    numberOfNodes = auxiliaryGetNumberOfNodes(getRoot()) + 1;
	}
	return numberOfNodes;
    };

    @SuppressWarnings("unchecked")
    default public int auxiliaryGetNumberOfNodes(T node) {
	int numberOfNodes = node.getNumberOfChildren();
	for (AbstractNode<S> child : node.getChildren()) {
	    numberOfNodes += auxiliaryGetNumberOfNodes((T) child);
	}
	return numberOfNodes;
    }

    default List<T> findAll(S dataToFind) {
	List<T> returnList = null;
	if (getRoot() != null) {
	    List<T> rList = null;
	    rList = auxiliaryFindAll(getRoot(), dataToFind);
	    if (rList != null && !rList.isEmpty()) {
		returnList = new ArrayList<T>();
		returnList.addAll(rList);
	    }
	}
	return returnList;
    };
    
    @SuppressWarnings("unchecked")
    default List<T> auxiliaryFindAll(T currentNode, S dataToFind) {
	List<T> returnList = null;
	int i = 0;
	if (currentNode.getData().equals(dataToFind)) {
	    returnList = new ArrayList<T>();
	    returnList.add(currentNode);
	}
	if (currentNode.hasChildren()) {
	    while (i < currentNode.getNumberOfChildren()) {
		List<T> rList = null;
		rList = auxiliaryFindAll((T) currentNode.getChildAt(i), dataToFind);
		if ((rList != null) && !rList.isEmpty()) {
		    if (returnList != null) {
			returnList.addAll(rList);
		    } else {
			returnList = rList;
		    }
		}
		i++;
	    }
	}
	return returnList;
    }

    default public List<T> build(boolean order) {
	List<T> returnList = null;
	if (getRoot() != null) {
	    returnList = build(getRoot(), order);
	}
	return returnList;
    };

    default public List<T> build(T node, boolean order) {
	List<T> result = new ArrayList<T>();
	if (order) {
	    buildPreOrder(node, result);
	} else {
	    buildPostOrder(node, result);
	}
	return result;
    }

    @SuppressWarnings("unchecked")
    default public void buildPreOrder(T node, List<T> result) {
	result.add(node);
	for (AbstractNode<S> child : node.getChildren()) {
	    buildPreOrder((T) child, result);
	}
    }

    default public void buildPostOrder(T node, List<T> result) {
	int iMax = node.getNumberOfChildren();
	for (int i = iMax; i >= 0; i--) {
	    @SuppressWarnings("unchecked")
	    T child = (T) node.getChildAt(i);
	    result.add(child);
	}
	result.add(node);
    }

    default public Map<T, Integer> buildWithDepth(boolean order) {
	Map<T, Integer> rMap = null;
	if (getRoot() != null) {
	    rMap = buildWithDepth(getRoot(), order);
	}
	return rMap;
    }

    default Map<T, Integer> buildWithDepth(T node, boolean order) {
	Map<T, Integer> result = new LinkedHashMap<T, Integer>();
	if (order) {
	    buildPreOrderWithDepth(node, result, 0);
	} else {
	    buildPostOrderWithDepth(node, result, 0);
	}
	return result;
    }

    @SuppressWarnings("unchecked")
    default public void buildPreOrderWithDepth(T node, Map<T, Integer> result, int depth) {
	result.put(node, depth);
	for (AbstractNode<S> child : node.getChildren()) {
	    buildPreOrderWithDepth((T) child, result, depth + 1);
	}
    };

    default public void buildPostOrderWithDepth(T node, Map<T, Integer> result, int depth) {
	int iMax = node.getNumberOfChildren();
	for (int i = iMax; i >= 0; i--) {
	    @SuppressWarnings("unchecked")
	    T child = (T) node.getChildAt(i);
	    buildPostOrderWithDepth(child, result, depth + 1);
	}
	result.put(node, depth);
    }

    @SuppressWarnings("unchecked")
    default public int getDistanceToRoot(T node) {
	int distance = 0;
	T parent = (T) node.getParent();
	while (parent!=null) {
	    distance++;
	    parent = (T) parent.getParent();
	}
	return distance;
    };    
}
