package classes.vtlSpecific;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import classes.Node;
import classes.NodeType;
import classes.Tree;
import classes.abstractClasses.AbstractNode;
import classes.abstractClasses.AbstractTree;
import ecb.interfaces.TypeOfNode;
import enums.typesOfNodes.IdentificationNode;
import enums.typesOfNodes.OperatorI;
import enums.typesOfNodes.OperatorII;
import enums.typesOfNodes.OperatorIII;
import enums.typesOfNodes.OperatorIV;
import enums.typesOfNodes.OperatorV;
import enums.typesOfNodes.OperatorVI;
import enums.typesOfNodes.TreeLeaf;
import staticObjects.StaticObjects;

/**
 * Bird implementation of {@link AbstractTree} class representing the tree
 * structure of vtl syntax.
 * 
 * @author lindomi
 * @version 0.1
 * @param <T>
 *            the type of node of this tree (i.e. a class extending
 *            {@link BirdVtlNode})
 * @param <S>
 *            the node's type (i.e. a class extending {@link NodeType})
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BirdVtlTree<T extends BirdVtlNode<S>, S extends NodeType> extends AbstractTree<T, S> {

    /**
     * a set of types of nodes where (initially) the next sibling becomes the
     * node's child.
     */
    public Set<String> nextSiblingToChildTypes;

    /**
     * a set of types of nodes where (initially) the previous and the next
     * sibling become the node's children.
     */
    public Set<String> previousAndNextSiblingToChildTypes;

    /**
     * a set of types of nodes which should (finally) not be removed from this
     * tree structure.
     */
    public Set<String> typesToKeep;

    /**
     * a set of types of nodes where the second child should be a container for
     * one or more members.
     */
    public Set<String> nodesWhereSecondChildIsContainer;

    /**
     * a set of types of nodes where the first child should be a container for
     * one or more members.
     */
    public Set<String> nodesWhereFirstChildIsContainer;

    /**
     * a set of types of nodes which qualify for a comment in case the previous
     * sibling is a <code>Comment</code> node.
     */
    public Set<String> nextSiblingComment;

    /**
     * a set of types of nodes which are used as indicators for identification
     * of method (i.e. function, procedure, rule set) comments.
     */
    public Set<String> commentQualifyingIndicators;

    /**
     * a set of types of nodes which qualify as targets for comments.
     */
    public Set<String> commentQualifyingMethodsTarget;

    /**
     * a set of types of nodes containing all valid types of joins (e.g.
     * <code>inner</code>).
     */
    public Set<String> typesOfJoin;

    /**
     * a set of types of nodes containing all clause operators (e.g. keep,
     * filter, etc.)
     */
    public Set<String> clauseOperators;

    /**
     * a set of types of nodes containing mathematical operators
     */
    public Set<String> mathOperators;

    /**
     * a set of types of nodes containig all dataset operators (e.g. union,
     * setdiff, etc.)
     */
    public Set<String> datasetOperations;

    // ---------------------------------------------------------------------------------------------------------
    // getters
    // ---------------------------------------------------------------------------------------------------------

    /**
     * 
     * @param <V>
     * @return s set of string objects containing the string representation of
     *         all nodes which will (finally) be kept and not removed from this
     *         tree structure.
     */
    public <V extends TypeOfNode, VtlBuild> Set<String> getTypesToKeep() {
	if (typesToKeep == null) {
	    typesToKeep = new HashSet<>();
	    Set<V> set = new HashSet<>();
	    for (TreeLeaf leaf : TreeLeaf.values()) {
		set.add((V) leaf);
	    }
	    for (OperatorI op : OperatorI.values()) {
		set.add((V) op);
	    }
	    for (OperatorII op : OperatorII.values()) {
		set.add((V) op);
	    }
	    for (OperatorIII op : OperatorIII.values()) {
		set.add((V) op);
	    }
	    for (OperatorIV op : OperatorIV.values()) {
		set.add((V) op);
	    }
	    for (OperatorV op : OperatorV.values()) {
		set.add((V) op);
	    }
	    for (OperatorVI op : OperatorVI.values()) {
		set.add((V) op);
	    }
	    for (V element : set) {
		typesToKeep.add(element.toString());
	    }
	}
	return typesToKeep;
    }

    /**
     * 
     * @param <V>
     * @return a set of string objects containing the string representation of
     *         all nodes where the next sibling should (initially) become such a
     *         node's child.
     */
    public <V extends TypeOfNode, VtlBuild> Set<String> getNextSiblingToChildTypes() {
	if (nextSiblingToChildTypes == null) {
	    nextSiblingToChildTypes = new HashSet<>();
	    Set<V> nodes = new HashSet<>();
	    for (OperatorV op : OperatorV.values()) {
		nodes.add((V) op);
	    }

	    nodes.add((V) OperatorIII.CALL);
	    nodes.add((V) OperatorIII.COUNT);
	    nodes.add((V) OperatorIII.ELSE);
	    nodes.add((V) OperatorIII.ERRORCODE);
	    nodes.add((V) OperatorIII.GET);
	    nodes.add((V) OperatorIII.GROUP_BY);
	    nodes.add((V) OperatorIII.ISNULL);
	    nodes.add((V) OperatorIII.NOT_ISNULL);
	    nodes.add((V) OperatorIII.MIN);
	    nodes.add((V) OperatorIII.MAX);
	    nodes.add((V) OperatorIII.SUM);
	    nodes.add((V) OperatorIII.SQRT);
	    nodes.add((V) OperatorIII.AVG);
	    nodes.add((V) OperatorIII.THEN);
	    nodes.add((V) OperatorIII.WHEN);
	    nodes.add((V) OperatorIII.NOT);
	    nodes.add((V) OperatorIII.IF);
	    nodes.add((V) OperatorIII.ELSEIF);
	    nodes.add((V) OperatorIII.CHECK);
	    nodes.add((V) OperatorIII.HIERARCHY);
	    nodes.add((V) OperatorIII.TRANSCODE);
	    nodes.add((V) OperatorIII.PUT);
	    nodes.add((V) OperatorIII.REPLACE);
	    nodes.add((V) OperatorIII.SUBSTR);

	    for (V node : nodes) {
		String s = node.toString();
		nextSiblingToChildTypes.add(s);
	    }
	}
	return nextSiblingToChildTypes;
    }

    /**
     * 
     * @param <V>
     * @return a set of string objects containing the string representation of
     *         all nodes where the previous and the next sibling should
     *         (initially) become such a node's child.
     */
    public <V extends TypeOfNode, VtlBuild> Set<String> getPreviousAndNextSiblingToChildTypes() {
	if (previousAndNextSiblingToChildTypes == null) {
	    previousAndNextSiblingToChildTypes = new HashSet<>();
	    Set<V> nodes = new HashSet<>();

	    nodes.add((V) OperatorI.DEF);
	    nodes.add((V) OperatorI.DOT);
	    nodes.add((V) OperatorI.MINUS);
	    nodes.add((V) OperatorI.PLUS);
	    nodes.add((V) OperatorI.MULTIPLY);
	    nodes.add((V) OperatorI.DIVIDE);
	    nodes.add((V) OperatorI.EQUALS);
	    nodes.add((V) OperatorI.NOT_EQUALS);
	    nodes.add((V) OperatorI.GT);
	    nodes.add((V) OperatorI.GE);
	    nodes.add((V) OperatorI.LT);
	    nodes.add((V) OperatorI.LE);
	    nodes.add((V) OperatorI.AND);
	    nodes.add((V) OperatorI.OR);
	    nodes.add((V) OperatorI.IN);
	    nodes.add((V) OperatorI.NOT_IN);
	    nodes.add((V) OperatorI.CONCAT);

	    for (V node : nodes) {
		String s = node.toString();
		previousAndNextSiblingToChildTypes.add(s);
	    }
	}
	return previousAndNextSiblingToChildTypes;
    }

    /**
     * 
     * @return a set of string objects containing the string representation of
     *         all nodes where the first child should be a container for one or
     *         more members.
     */
    public Set<String> getNodesWhereFirstChildIsContainer() {
	if (nodesWhereFirstChildIsContainer == null) {
	    nodesWhereFirstChildIsContainer = new HashSet<>();
	    nodesWhereFirstChildIsContainer.add(OperatorV.KEEP.toString());
	    nodesWhereFirstChildIsContainer.add(OperatorV.RENAME.toString());
	    nodesWhereFirstChildIsContainer.add(OperatorIII.GROUP_BY.toString());
	    nodesWhereFirstChildIsContainer.add(OperatorIII.UNION.toString());
	    nodesWhereFirstChildIsContainer.add(OperatorIII.SET_DIFF.toString());
	    nodesWhereFirstChildIsContainer.add(OperatorIII.SYM_DIFF.toString());
	    nodesWhereFirstChildIsContainer.add(OperatorIII.INTERSECT.toString());
	    nodesWhereFirstChildIsContainer.add(OperatorIII.REPLACE.toString());
	    nodesWhereFirstChildIsContainer.add(OperatorIII.SUBSTR.toString());

	}
	return nodesWhereFirstChildIsContainer;
    }

    /**
     * 
     * @return a set of string objects containing the string representation of
     *         all nodes where the second child should be a container for one or
     *         more members.
     */
    public Set<String> getNodesWhereSecondChildIsContainer() {
	if (nodesWhereSecondChildIsContainer == null) {
	    nodesWhereSecondChildIsContainer = new HashSet<>();
	    nodesWhereSecondChildIsContainer.add(OperatorI.IN.toString());
	    nodesWhereSecondChildIsContainer.add(OperatorI.NOT_IN.toString());
	    nodesWhereSecondChildIsContainer.add(OperatorII.FUNCTION_CALL.toString());
	    nodesWhereSecondChildIsContainer.add(IdentificationNode.PROCEDURE_CALL_BODY.toString());
	}
	return nodesWhereSecondChildIsContainer;
    }

    /**
     * 
     * @return a set of string objects containing the string representation of
     *         all nodes which qualify for a comment in case the previous
     *         sibling is a <code>Comment</code> node.
     */
    public Set<String> getNextSiblingComment() {
	if (nextSiblingComment == null) {
	    nextSiblingComment = new HashSet<>();
	    nextSiblingComment.add(OperatorVI.RULE_ID.toString());
	    nextSiblingComment.add(OperatorIII.IF.toString());
	    nextSiblingComment.add(OperatorIII.ELSEIF.toString());
	    nextSiblingComment.add(OperatorIII.ELSE.toString());
	}
	return nextSiblingComment;
    }

    /**
     * 
     * @return a set of string objects containing the string representation of
     *         all nodes which are used as indicators of methods for comment
     *         transfer.
     */
    public Set<String> getCommentQualifyingIndicators() {
	if (commentQualifyingIndicators == null) {
	    commentQualifyingIndicators = new HashSet<>();
	    commentQualifyingIndicators.add(IdentificationNode.DEF_FUNCTION.toString());
	    commentQualifyingIndicators.add(IdentificationNode.DEF_PROCEDURE.toString());
	    commentQualifyingIndicators.add(IdentificationNode.DEF_RULESET.toString());
	}
	return commentQualifyingIndicators;
    }

    /**
     * 
     * @return a set of string objects containing the string representation of
     *         nodes which qualify as target for method comments.
     */
    public Set<String> getCommentQualifyingMethodsTarget() {
	if (commentQualifyingMethodsTarget == null) {
	    commentQualifyingMethodsTarget = new HashSet<>();
	    commentQualifyingMethodsTarget.add(TreeLeaf.FUNCTION_ID.toString());
	    commentQualifyingMethodsTarget.add(TreeLeaf.PROCEDURE_ID.toString());
	    commentQualifyingMethodsTarget.add(TreeLeaf.RULESET_ID.toString());
	}
	return commentQualifyingMethodsTarget;
    }

    /**
     * 
     * @return a set of string objects containing the string representation of
     *         all valid join types.
     */
    public Set<String> getTypesOfJoin() {
	if (typesOfJoin == null) {
	    typesOfJoin = new HashSet<>();
	    typesOfJoin.add(TreeLeaf.INNER.toString());
	    typesOfJoin.add(TreeLeaf.OUTER.toString());
	    typesOfJoin.add(TreeLeaf.LEFT.toString());
	    typesOfJoin.add(TreeLeaf.RIGHT.toString());
	    typesOfJoin.add(TreeLeaf.CROSS.toString());
	}
	return typesOfJoin;
    }

    /**
     * 
     * @return a set of string objects containing the string representation of
     *         all clause operators
     */
    public Set<String> getClauseOperators() {
	if (clauseOperators == null) {
	    clauseOperators = new HashSet<>();
	    for (OperatorV op : OperatorV.values()) {
		clauseOperators.add(op.toString());
	    }
	}
	return clauseOperators;
    }

    /**
     * 
     * @return a set of string objects containing the string representation of
     *         all set operators
     */
    public Set<String> getDatasetOperators() {
	if (datasetOperations == null) {
	    datasetOperations = new HashSet<>();
	    datasetOperations.add(OperatorIII.UNION.toString());
	    datasetOperations.add(OperatorIII.SET_DIFF.toString());
	    datasetOperations.add(OperatorIII.SYM_DIFF.toString());
	    datasetOperations.add(OperatorIII.INTERSECT.toString());
	}
	return datasetOperations;
    }

    /**
     * 
     * @return a set of string object containing the string representation of
     *         math operators
     */
    public Set<String> getMathOperators() {
	if (mathOperators == null) {
	    mathOperators = new HashSet<>();
	    mathOperators.add(OperatorI.PLUS.toString());
	    mathOperators.add(OperatorI.MINUS.toString());
	    mathOperators.add(OperatorI.MULTIPLY.toString());
	    mathOperators.add(OperatorI.DIVIDE.toString());
	}
	return mathOperators;
    }

    /**
     * 
     * @return a set containing all nodes of this tree
     */
    public Set<T> getAllNodes() {
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
	return rSet;
    }

    /**
     * Identifies and returns all nodes of this tree matching the given input
     * type
     * 
     * @param type
     *            the type of node
     * @return a set of nodes matching the given input type
     */
    public Set<T> getAllNodesOfType(String type) {
	Set<T> rSet = null;
	if (getAllNodes() != null && !getAllNodes().isEmpty()) {
	    rSet = new HashSet<>();
	    Set<T> nodes = getAllNodes();
	    Iterator<T> it = nodes.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		it.remove();
		if (node.getData().getType().equals(type)) {
		    rSet.add(node);
		}
	    }
	}
	return rSet;
    }

    /**
     * Identifies and returns all nodes of this tree matching one of the given
     * input type
     * 
     * @param types
     *            the types of nodes that qualify
     * @return a set of nodes matching one of the given input types
     */
    public Set<T> getAllNodes(List<String> types) {
	Set<T> rSet = null;
	if (getAllNodes() != null && !getAllNodes().isEmpty()) {
	    rSet = new HashSet<>();
	    for (String type : types) {
		Set<T> nodes = getAllNodesOfType(type);
		if (nodes != null && !nodes.isEmpty()) {
		    rSet.addAll(nodes);
		}
	    }
	}
	return rSet;
    }

    // ---------------------------------------------------------------------------------------------------------
    // (generic) methods in order to search for specific nodes in this tree
    // structure
    // ---------------------------------------------------------------------------------------------------------

    public List<T> findAllByType(String type) {
	List<T> returnList = null;
	if (getRoot() != null) {
	    List<T> rList = null;
	    rList = auxiliaryFindAllByType(getRoot(), type);
	    if (rList != null && !rList.isEmpty()) {
		returnList = new ArrayList<T>();
		returnList.addAll(rList);
	    }
	}
	return returnList;
    }

    public List<T> auxiliaryFindAllByType(T currentNode, String type) {
	List<T> returnList = null;
	int i = 0;
	if (currentNode.getData().getType().equals(type)) {
	    returnList = new ArrayList<T>();
	    returnList.add(currentNode);
	}
	if (currentNode.hasChildren()) {
	    while (i < currentNode.getNumberOfChildren()) {
		List<T> rList = null;
		rList = auxiliaryFindAllByType((T) currentNode.getChildAt(i), type);
		if (rList != null && !rList.isEmpty()) {
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

    public List<T> findAllContainingExpression(String expression) {
	List<T> returnList = null;
	if (getRoot() != null) {
	    List<T> rList = null;
	    rList = auxiliaryFindAllContainingExpression(getRoot(), expression);
	    if (rList != null && !rList.isEmpty()) {
		returnList = new ArrayList<>();
		returnList.addAll(rList);
	    }
	}
	return returnList;
    }

    public List<T> auxiliaryFindAllContainingExpression(T currentNode, String expression) {
	List<T> returnList = null;
	int i = 0;
	if (currentNode.getData().getExpression().contains(expression)) {
	    returnList = new ArrayList<>();
	    returnList.add(currentNode);
	}
	if (currentNode.hasChildren()) {
	    while (i < currentNode.getNumberOfChildren()) {
		List<T> rList = null;
		rList = auxiliaryFindAllContainingExpression((T) currentNode.getChildAt(i), expression);
		if (rList != null && !rList.isEmpty()) {
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

    public List<T> findAllContainingType(String type) {
	List<T> returnList = null;
	if (getRoot() != null) {
	    List<T> rList = null;
	    rList = auxiliaryFindAllContainingType(getRoot(), type);
	    if (rList != null && !rList.isEmpty()) {
		returnList = new ArrayList<>();
		returnList.addAll(rList);
	    }
	}
	return returnList;
    }

    public List<T> auxiliaryFindAllContainingType(T currentNode, String type) {
	List<T> returnList = null;
	int i = 0;
	if (currentNode.getData().getType().contains(type)) {
	    returnList = new ArrayList<>();
	    returnList.add(currentNode);
	}
	if (currentNode.hasChildren()) {
	    while (i < currentNode.getNumberOfChildren()) {
		List<T> rList = null;
		rList = auxiliaryFindAllContainingType((T) currentNode.getChildAt(i), type);
		if (rList != null && !rList.isEmpty()) {
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

    public List<T> findAllByType(List<String> list) {
	List<T> returnList = null;
	if (getRoot() != null) {
	    if (list != null && !list.isEmpty()) {
		List<T> rList = null;
		rList = auxiliaryFindAllByType(getRoot(), list);
		if (rList != null && !rList.isEmpty()) {
		    returnList = new ArrayList<>();
		    returnList.addAll(rList);
		}
	    }
	}
	return returnList;
    }

    public List<T> auxiliaryFindAllByType(T currentNode, List<String> list) {
	List<T> returnList = null;
	int i = 0;
	if (list.contains(currentNode.getData().getType())) {
	    returnList = new ArrayList<>();
	    returnList.add(currentNode);
	}
	if (currentNode.hasChildren()) {
	    while (i < currentNode.getNumberOfChildren()) {
		List<T> rList = null;
		rList = auxiliaryFindAllByType((T) currentNode.getChildAt(i), list);
		if (rList != null && !rList.isEmpty()) {
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

    public T auxiliaryFindByType(T currentNode, List<String> list) {
	T rNode = null;
	int i = 0;
	if (list.contains(currentNode.getData().getType())) {
	    rNode = currentNode;
	}
	if (currentNode.hasChildren()) {
	    while (i < currentNode.getNumberOfChildren() && rNode == null) {
		rNode = auxiliaryFindByType((T) currentNode.getChildAt(i), list);
		i++;
	    }
	}
	return rNode;
    }

    // ---------------------------------------------------------------------------------------------------------
    // (generic) methods for tree structure manipulation
    // ---------------------------------------------------------------------------------------------------------

    /**
     * Replaces a node (i.e. <b>futureChild</b>) by another node (i.e.
     * <b>node</b>) in this tree. The replaced node becomes a child of the other
     * node (placed in the first position) while this other node takes the
     * (former) position of the futureChild (node).
     * 
     * @param futureChild
     *            the node becoming the child node
     * @param node
     *            the node taking the place of the (input parameter) futureChild
     */
    public void makeNodeChild(T futureChild, T node) {
	if (futureChild.getParent() != null) {
	    T parent = (T) futureChild.getParent();
	    int position = parent.getChildren().indexOf(futureChild);
	    parent.removeChild(futureChild);
	    node.getParent().removeChild(node);
	    parent.addChild(node, position);
	    node.addChild(futureChild, 0);
	} else {
	    System.out.println("WARNING [makeNodeChild]: future child has no parent!");
	}
    }

    /**
     * Replaces a node in the sense that this node's children remain in the tree
     * structure and become children of this node's parent. The (new) children
     * are placed in the position where the node was removed.
     * 
     * @param node
     *            the node to be replaced
     */
    public void omitNode(T node) {
	if (node.getParent() != null) {
	    T parent = (T) node.getParent();
	    int index = parent.getChildren().indexOf(node);
	    if (node.hasChildren()) {
		List<AbstractNode<S>> children = node.getChildren();
		Iterator<AbstractNode<S>> it = children.iterator();
		while (it.hasNext()) {
		    T child = (T) it.next();
		    it.remove();
		    node.removeChild(child);
		    parent.addChild(child, index);
		    index++;
		}
	    }
	    parent.removeChild(node);
	}
    }

    public T getNextSibling(T node) {
	return getSibling(node, true);
    }

    public T getPreviousSibling(T node) {
	return getSibling(node, false);
    }

    /**
     * Extracts the next or previous sibling of the (input parameter) node.
     * 
     * @param node
     *            the node who's sibling should be returned
     * @param nextOrPrevious
     *            <code>true</code> for next
     * @return
     */
    public T getSibling(T node, boolean nextOrPrevious) {
	T sibling = null;
	int nOp = (-1);
	if (nextOrPrevious) {
	    nOp = 1;
	}
	if (node != null) {
	    if (node.getParent() != null) {
		T parent = (T) node.getParent();
		int index = parent.getChildren().indexOf(node);
		if (parent.getChildAt(index + nOp) != null) {
		    sibling = (T) parent.getChildAt(index + nOp);
		} else {
		    System.out.println("WARNING [getSibling]: node has no next / previous sibling!");
		}
	    } else {
		System.out.println(
			"WARNING [getSibling]: tried to get a sibling from a node having no parent (i.e. the root of this tree)!");
	    }
	}
	return sibling;
    }

    public void makeNextSiblingChild(T node) {
	makeSiblingChild(node, true);
    }

    public void makePreviousSiblingChild(T node) {
	makeSiblingChild(node, false);
    }

    public void makeNextSiblingChild(T node, int position) {
	makeSiblingChild(node, true, position);
    }

    public void makePreviousSiblingChild(T node, int position) {
	makeSiblingChild(node, false, position);
    }

    public void makeSiblingChild(T node, boolean next) {
	makeSiblingChild(node, next, node.getNumberOfChildren());
    }

    /**
     * Rearranges this tree structure such that the next sibling of the (input
     * parameter) node becomes this (input parameter) node's child after this
     * method is called
     * 
     * @param node
     *            the node getting a child
     * @param next
     *            boolean variable specifying if the next or the previous
     *            sibling should become the child of the (input parameter) node.
     *            <b>true</b> if next.
     * @param position
     *            the prefered position where the sibling should be placed when
     *            becoming a child
     */
    public void makeSiblingChild(T node, boolean next, int position) {
	int nOp = (-1);
	if (next) {
	    nOp = 1;
	}
	if (node != null) {
	    if (node.getParent() != null) {
		T parent = (T) node.getParent();
		int index = parent.getChildren().indexOf(node);
		if (parent.getChildAt(index + nOp) != null) {
		    T sibling = (T) parent.getChildAt(index + nOp);
		    becomeChild(node, sibling, position);
		} else {
		    System.out.println("WARNING [makeSiblingChild]: node has no next / previous sibling!");
		}
	    } else {
		System.out.println(
			"WARNING [makeSiblingChild]: tried to add a child to a node having no parent (i.e. the root of this tree)!");
	    }
	}
    }

    public void makeAllSiblingsChildren(T node) {
	node.letSiblingsBecomeChildren();
    }

    public void becomeChild(T parent, T child) {
	becomeChild(parent, child, parent.getNumberOfChildren());
    }

    /**
     * Rearranges this tree such that the (input parameter) child becomes the
     * (input parameter) parent's child.
     * 
     * @param parent
     *            the future parent
     * @param child
     *            the future child
     */
    public void becomeChild(T parent, T child, int position) {
	if (parent != null && child != null) {
	    if (child.getParent() != null) {
		T currentParent = (T) child.getParent();
		currentParent.removeChild(child);
		parent.addChild(child, position);
	    } else {
		System.out.println(
			"WARNING [becomeChild]: tried to make a node having no parent (i.e. the root of this tree) a child!");
	    }
	}
    }

    /**
     * rearranges the siblings of the given node such that they become children
     * of this node after this method is called
     * 
     * @param node
     *            the node who's siblings will be rearranged to children
     */
    public void siblingsToChildren(T node) {
	node.letSiblingsBecomeChildren();
    }

    /**
     * Searches for specific types of nodes in the tree structure (i.e.
     * <code>toFind</code>). If such a node is found this methods searches for
     * another specific node (<code>futureParent</code>) having the same parent
     * (i.e. a sibling) and performs the operations necessary to make the toFind
     * node the child of the futureParent node.
     * 
     * @param toFind
     *            the type of node that should be found
     * @param futureParent
     *            the other type of node that should be found
     * @param backwards
     *            <code>true</code> if the position of toFind is smaller then
     *            the position of the futureParent
     */
    public void findAndBecomeChildOf(S toFind, S futureParent, boolean backwards) {
	List<T> nodes = findAll(toFind);
	if (nodes != null && !nodes.isEmpty()) {
	    Iterator<T> it = nodes.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		if (node.getParent() != null) {
		    T parent = (T) node.getParent();
		    int index = parent.getChildren().indexOf(node);
		    T futureParentNode = null;
		    boolean found = false;
		    boolean condition = false;
		    int i = index;
		    i = (backwards) ? i - 1 : i + 1;

		    int iMin = 0;
		    int iMax = parent.getNumberOfChildren();
		    do {
			T currentNode = (T) parent.getChildAt(i);
			if (currentNode.getData().equals(futureParent)) {
			    found = true;
			    futureParentNode = currentNode;
			} else {
			    i = (backwards) ? i - 1 : i + 1;
			}
			condition = (backwards) ? i >= iMin : i < iMax;
		    } while (condition && !found);
		    if (found) {
			it.remove();
			parent.removeChild(node);
			futureParentNode.addChild(node);
		    }
		}
	    }
	}
    }

    // ---------------------------------------------------------------------------------------------------------
    // specific methods ...
    // ---------------------------------------------------------------------------------------------------------

    /**
     * Applies the method {@link #reduceLeaf(BirdVtlNode)} to all leafs of this
     * tree.
     */
    public void reduceLeafs() {
	List<T> leafs = getLeafs();
	for (T leaf : leafs) {
	    reduceLeaf(leaf);
	}
    }

    /**
     * Reduces a leaf (i.e. a {@link Node} without any child) of this
     * {@link Tree}. The leaf is reduced in the sense that if the parent of the
     * leaf is a node having only one child (which is the given leaf) and the
     * content (i.e. expression) of this node is equal to the content of the
     * leaf then the node is deleted from the {@link Tree}.<br>
     * Additionally if the type of the leaf equals
     * {@link StaticObjects#terminalNodeImpl} and the type of the node is
     * different then the leafs type is set equal to the node's type.
     * 
     * @param node
     *            a {@link BirdVtlNode} of this tree
     */
    public void reduceLeaf(T node) {
	T parent = null;
	boolean process = true;
	while (process) {
	    parent = (T) node.getParent();
	    if (parent != null && node != null && node.getData() != null && parent.getData() != null) {
		String parentExpression = parent.getData().getExpression();
		String nodeExpression = node.getData().getExpression();

		if (parentExpression.equals(nodeExpression)) {
		    T pNode = (T) parent.getParent();
		    pNode.removeChild(parent);
		    pNode.addChild(node);
		    if (node.getData().getType().equals(StaticObjects.terminalNodeImpl)) {
			if (!parent.getData().getType().equals(StaticObjects.terminalNodeImpl)) {
			    node.getData().setType(parent.getData().getType());
			}
		    }
		} else if (parentExpression.startsWith("(") && parentExpression.endsWith(")")
			&& parentExpression.substring(1, parentExpression.length() - 1).equals(nodeExpression)) {
		    T pNode = (T) parent.getParent();
		    pNode.removeChild(parent);
		    pNode.addChild(node);
		    if (node.getData().getType().equals(StaticObjects.terminalNodeImpl)) {
			if (!parent.getData().getType().equals(StaticObjects.terminalNodeImpl)) {
			    node.getData().setType(parent.getData().getType());
			}
		    }
		} else {
		    process = false;
		}
	    } else {
		process = false;
	    }
	}
	String nodeExpression = node.getData().getExpression();
	if (nodeExpression.replace("\n", "").trim().isEmpty() || nodeExpression.equals("<" + StaticObjects.EOF + ">")) {
	    parent = (T) node.getParent();
	    parent.removeChild(node);
	}
    }

    /**
     * applies the method {@link #reduceBranche(Node)} to all {@link Node}s of
     * this {@link Tree}
     */
    public void reduceBranches() {
	List<T> leafes = getLeafs();
	for (T leaf : leafes) {
	    if (leaf.getParent() != null) {
		T parent = (T) leaf.getParent();
		while (parent != null) {
		    reduceBranche(parent);
		    parent = (T) parent.getParent();
		}
	    }
	}
    }

    /**
     * Reduces a branch (i.e. a {@link Node} object having a parent and at least
     * one child) of the {@link Tree}. This branch is reduced in the sense that
     * if the content (i.e. expression) of this branch is equal to the content
     * (i.e. expression) of the parent this branch is deleted. Please note that
     * for the term equal in this context a {@code newline} or round brackets
     * (i.e. "(", ")") at the beginning and end or a semicolon (i.e. ";") at the
     * end of the expression are not relevant.
     * 
     * @param node
     *            a {@link Node} of a this tree
     */
    public void reduceBranche(T node) {
	T parent = null;
	boolean process = true;
	while (process) {
	    parent = (T) node.getParent();
	    if (parent != null && parent.getData() != null && node != null && node.getData() != null) {
		String parentExpression = parent.getData().getExpression();
		String nodeExpression = node.getData().getExpression();

		if (parentExpression.equals(nodeExpression)) {
		    T pNode = (T) parent.getParent();
		    int index = pNode.getChildren().indexOf(parent);
		    pNode.removeChild(parent);
		    pNode.addChild(node, index);
		} else if (parentExpression.replace("\n", "").equals(nodeExpression.replace("\n", ""))) {
		    T pNode = (T) parent.getParent();
		    int index = pNode.getChildren().indexOf(parent);
		    pNode.removeChild(parent);
		    pNode.addChild(node, index);
		} else if (parentExpression.startsWith("(") && parentExpression.endsWith(")")
			&& parentExpression.substring(1, parentExpression.length() - 1).equals(nodeExpression)) {
		    T pNode = (T) parent.getParent();
		    int index = pNode.getChildren().indexOf(parent);
		    pNode.removeChild(parent);
		    pNode.addChild(node, index);
		} else if ((parentExpression + ";").equals(nodeExpression)) {
		    T pNode = (T) parent.getParent();
		    int index = pNode.getChildren().indexOf(parent);
		    pNode.removeChild(parent);
		    pNode.addChild(node, index);
		} else {
		    process = false;
		}
	    } else {
		process = false;
	    }
	}
    }

    /**
     * Removes leafs of this {@link Tree} in case the leaf's (i.e. the
     * {@link Node} without {@link Node#children}) expression contains only a
     * specific term (see {@link StaticObjects#remains}
     */
    public void removeSymbols() {
	List<T> leafes = getLeafs();
	for (T leaf : leafes) {
	    removeSymbol(leaf);
	}
    }

    /**
     * If this {@link Node}'s expression equals an element of the List
     * {@link StaticObjects#remains} this {@link Node} is deleted.
     * 
     * @param node
     *            a {@link Node} of a {@link Tree}
     */
    public void removeSymbol(T node) {
	if (StaticObjects.remains.contains(node.getData().getExpression())) {
	    T parent = (T) node.getParent();
	    parent.removeChild(node);
	}
    }

    /**
     * Reorders all {@link Node}s of this {@link Tree} with respect to the
     * expression of the parent node
     */
    public void reorderAllChildren() {
	T root = getRoot();
	if (root != null) {
	    if (root.getChildren() != null && !root.getChildren().isEmpty()) {
		for (AbstractNode<S> child : root.getChildren()) {
		    reorderChild((T) child);
		}
	    }
	}
    }

    /**
     * Reorders the {@link Node#children} of the input {@link Node} in the
     * "correct" order using {@link Node#reorderChildren()}
     * 
     * @param node
     */
    public void reorderChild(T node) {
	node.reOrderChildren();
	if (node.getChildren() != null && !node.getChildren().isEmpty()) {
	    for (AbstractNode<S> child : node.getChildren()) {
		reorderChild((T) child);
	    }
	}
    }

    // ---------------------------------------------------------------------------------------------------------
    // specific (application of) methods for tree structure manipulation
    // ---------------------------------------------------------------------------------------------------------

    /**
     * Searches for all nodes with type in {@link #nextSiblingToChildTypes} and
     * applies {@link #nextSiblingToChild(List)}.
     */
    public void nextSiblingToChild() {
	for (String nodeType : getNextSiblingToChildTypes()) {
	    List<T> nodes = findAllByType(nodeType);
	    nextSiblingToChild(nodes);
	}
    }

    /**
     * Applies the method {@link #makeNextSiblingChild(BirdVtlNode)} to each
     * member of the input list.
     * 
     * @param list
     *            a list of nodes
     */
    public void nextSiblingToChild(List<T> list) {
	if (list != null && !list.isEmpty()) {
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		it.remove();
		makeNextSiblingChild(node);
	    }
	}
    }

    /**
     * Searches for all nodes with a type in
     * {@link #previousAndNextSiblingToChildTypes} and applies the method
     * {@link #previousAndNextSiblingToChild(List)}.
     */
    public void previousAndNextSiblingToChild() {
	for (String nodeType : getPreviousAndNextSiblingToChildTypes()) {
	    List<T> nodes = findAllByType(nodeType);
	    previousAndNextSiblingToChild(nodes);
	}
    }

    /**
     * Applies the methods {@link #makePreviousSiblingChild(BirdVtlNode)} and
     * {@link #makeNextSiblingChild(BirdVtlNode)} to each member of the input
     * list.
     * 
     * @param list
     *            a list of nodes
     */
    public void previousAndNextSiblingToChild(List<T> list) {
	if (list != null && !list.isEmpty()) {
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		it.remove();
		makePreviousSiblingChild(node);
		makeNextSiblingChild(node);
	    }
	}
    }

    /**
     * Removes all nodes from this tree structure that are not matching an
     * element in the list {@link #typesToKeep}.
     */
    public void removeNodes() {
	Set<T> nodes = getAllNodes();
	if (nodes != null && !nodes.isEmpty()) {
	    Iterator<T> it = nodes.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		it.remove();
		if (!getTypesToKeep().contains(node.getData().getType())) {
		    omitNode(node);
		}
	    }
	}
    }

    /**
     * Uses the method
     * {@link #findAndBecomeChildOf(VtlNodeType, VtlNodeType, boolean)} to
     * search for if / else if and then nodes under the same parent. If such a
     * constellation is found the then node becomes a child of the if / else if
     * node.
     */
    public void restructureIfElseIfElseOperators() {
	String ifTypeString = OperatorIII.IF.toString();
	String elseifTypeString = OperatorIII.ELSEIF.toString();
	String thenTypeString = OperatorIII.THEN.toString();

	NodeType ifType = new NodeType(ifTypeString);
	NodeType elseifType = new NodeType(elseifTypeString);
	NodeType thenType = new NodeType(thenTypeString);
	findAndBecomeChildOf((S) thenType, (S) ifType, true);
	findAndBecomeChildOf((S) thenType, (S) elseifType, true);

	String ifThenElse = OperatorII.IF_THEN_ELSE.toString();
	List<T> ifThenElseStructures = findAllByType(ifThenElse);
	if (ifThenElseStructures != null && !ifThenElseStructures.isEmpty()) {
	    Iterator<T> it = ifThenElseStructures.iterator();
	    while (it.hasNext()) {
		T structure = it.next();
		it.remove();
		structure.getData().setExpression(ifThenElse);
		structure.getData().setType(ifThenElse);
	    }
	}
    }

    /**
     * Applies the method {@link #restructureMethod(String)} for functions,
     * procedures and rule sets.
     */
    public void restructureMethods() {
	// restructure function definition
	String functionDef = IdentificationNode.CREATE_FUNCTION.toString();
	restructureMethod(functionDef);

	// restructure procedure definition
	String procedureDef = IdentificationNode.DEFINE_PROCEDURE.toString();
	restructureMethod(procedureDef);

	// restructure rule set definition
	String rulesetDef = IdentificationNode.DEFINE_DATAPOINT_RULESET.toString();
	restructureMethod(rulesetDef);
    }

    /**
     * Identifies <code>create function</code>, <code>define procedure</code>
     * and <code>DefineDatapointRuleset</code> nodes and applies the methods
     * {@link #restructureFunctionDef(BirdVtlNode, BirdVtlNode, BirdVtlNode, BirdVtlNode, BirdVtlNode)},
     * {@link #restructureProcedureDef(BirdVtlNode, BirdVtlNode, BirdVtlNode, BirdVtlNode, BirdVtlNode)},
     * {@link #restructureRulesetDef(BirdVtlNode, BirdVtlNode, BirdVtlNode, BirdVtlNode, BirdVtlNode)}
     * respectively.
     * 
     * @param methodDef
     *            <code>define procedure</code> for procedures,
     *            <code>create function</code> for functions and
     *            <code>DefineDatapointRuleset</code> for rule sets
     */
    public void restructureMethod(String methodDef) {
	String functionDef = IdentificationNode.CREATE_FUNCTION.toString();
	String procedureDef = IdentificationNode.DEFINE_PROCEDURE.toString();
	String rulesetDef = IdentificationNode.DEFINE_DATAPOINT_RULESET.toString();

	List<T> methodDefs = findAllByType(methodDef);
	if (methodDefs != null && !methodDefs.isEmpty()) {
	    Iterator<T> it = methodDefs.iterator();
	    while (it.hasNext()) {
		T methodDefNode = it.next();
		it.remove();
		T parent = (T) methodDefNode.getParent();
		if (parent != null) {
		    int index = parent.getChildren().indexOf(methodDefNode);
		    T methodID = (T) parent.getChildAt(index + 1);
		    T parameters = (T) parent.getChildAt(index + 2);
		    T body = (T) parent.getChildAt(index + 3);

		    if (methodDef.equals(functionDef)) {
			restructureFunctionDef(parent, methodDefNode, methodID, parameters, body);
		    } else if (methodDef.equals(procedureDef)) {
			restructureProcedureDef(parent, methodDefNode, methodID, parameters, body);
		    } else if (methodDef.equals(rulesetDef)) {
			restructureRulesetDef(parent, methodDefNode, methodID, parameters, body);
		    }
		} else {
		    System.err.println("[restructureMethod('" + methodDef + "')] WARNING: no parent!");
		}
	    }
	}
    }

    /**
     * 
     * Restructures nodes representing the definition of a procedure (including
     * the manipulation of <code>input</code> / <code>output</code> nodes). This
     * method transforms the structure of this tree structure reflecting the
     * parse tree structure into the Bird interpretation of the sdmx information
     * model for procedures.
     * 
     * @param parent
     *            the parent of all other (input) nodes (i.e. the
     *            <code>DefProcedure</code> node)
     * @param pDef
     *            the <code>define procedure</code> node
     * @param pID
     *            the <code>ProcedureID</code> node
     * @param parameters
     *            the node containing the parameters of the procedure. Depending
     *            on the number of input parameters its one of the following
     *            type of nodes: <code>ProcedureArgList</code>,
     *            <code>ProcedureArg</code>
     * @param body
     *            the <code>ProcedureBody</code> node
     */
    public void restructureProcedureDef(T parent, T pDef, T pID, T parameters, T body) {
	String procedureDef = OperatorII.PROCEDURE_DEFINITION.toString();
	String procedureInfo = OperatorIV.PROCEDURE_INFO.toString();
	String procedureBody = OperatorII.PROCEDURE_BODY.toString();
	String input = OperatorIII.INPUT.toString();
	String output = OperatorIII.OUTPUT.toString();
	String as = OperatorI.AS.toString();
	String dataset = TreeLeaf.INPUT_DATASET.toString();
	String params = OperatorII.PARAMETERS.toString();

	String virtualDataset = TreeLeaf.DATASET_ID.toString();
	String virtualVariable = TreeLeaf.VARIABLE_ID.toString();

	pDef.getData().setExpression(procedureDef);
	pDef.getData().setType(procedureDef);

	BirdVtlNode infoNode = new BirdVtlNode();
	infoNode.setData(new NodeType(procedureInfo));

	pDef.addChild(infoNode, 0);

	// transport from parent to info
	parent.removeChild(pID);
	infoNode.addChild((T) pID, 0);
	parameters.getData().setType(params);
	parameters.getData().setExpression(params);
	parent.removeChild(parameters);
	infoNode.addChild((T) parameters, 1);

	// handle input parameters of procedure definition
	List<T> inputNodes = auxiliaryFindAllByType(parameters, input);
	List<T> outputNodes = auxiliaryFindAllByType(parameters, output);
	List<T> nodes = new ArrayList<>();
	if (inputNodes != null) {
	    nodes.addAll(inputNodes);
	}
	if (outputNodes != null) {
	    nodes.addAll(outputNodes);
	}
	if (nodes != null && !nodes.isEmpty()) {
	    Iterator<T> it = nodes.iterator();
	    while (it.hasNext()) {
		T inputNode = it.next();
		it.remove();
		makeNextSiblingChild(inputNode);
	    }
	}

	// handle TerminalNodeImpl
	nodes = auxiliaryFindAllByType(parameters, StaticObjects.terminalNodeImpl);
	if (nodes != null && !nodes.isEmpty()) {
	    Iterator<T> it = nodes.iterator();
	    while (it.hasNext()) {
		T varNode = it.next();
		it.remove();
		T asNode = (T) varNode.getParent();
		if (asNode.getData().equals(new NodeType(as, as))) {
		    T type = (T) asNode.getChildAt(1);
		    String inputType = (type.getData().getType().equals(dataset)) ? virtualDataset : virtualVariable;
		    varNode.getData().setType(inputType);
		} else {
		    System.err.println("[] WARNING: TerminalNodeImpl's parent is not an 'as' node!");
		}
	    }
	}

	body.getData().setExpression(procedureBody);
	parent.removeChild(body);
	pDef.addChild(body);
    }

    /**
     * Restructures nodes representing the definition of a rule set in the sense
     * that the given structure (reflecting the parse tree) is transformed into
     * the Bird interpretation of the sdmx information model for rule sets.
     * 
     * @param parent
     *            the parent of all (input) nodes
     * @param rDef
     *            the <code>DefineDatapointRuleset</code> node
     * @param rID
     *            the <code>RulesetID</code> node
     * @param parameters
     *            the node containing the parameters of the ruleset. Depending
     *            on the number of input parameters its one of the following
     *            type of nodes: <code>RulesetArgList</code>,
     *            <code>RulesetArg</Code>, <code>VarID</code>
     * @param body
     *            the <code>RulesetBody</code> node
     */
    public void restructureRulesetDef(T parent, T rDef, T rID, T parameters, T body) {
	String rulesetDef = OperatorII.RULESET_DEFINITION.toString();
	String rulesetInfo = OperatorIV.RULESET_INFO.toString();
	String rulesetBody = OperatorII.RULESET_BODY.toString();
	String params = OperatorII.PARAMETERS.toString();

	String varID = TreeLeaf.VARIABLE_ID.toString();
	String ruleID = OperatorVI.RULE_ID.toString();

	rDef.getData().setExpression(rulesetDef);
	rDef.getData().setType(rulesetDef);

	body.getData().setExpression(rulesetBody);

	BirdVtlNode infoNode = new BirdVtlNode();
	infoNode.setData(new NodeType(rulesetInfo));

	parent.removeChild(rID);
	infoNode.addChild(rID, 0);
	parent.removeChild(parameters);

	String typeOfParam = parameters.getData().getType();
	if (typeOfParam.equals(varID)) {
	    // create parameters node
	    BirdVtlNode param = new BirdVtlNode();
	    param.setData(new NodeType(params));
	    parent.removeChild(parameters);
	    // add to info node
	    param.addChild(parameters);
	    infoNode.addChild(param, 1);
	} else {
	    parameters.getData().setType(params);
	    parameters.getData().setExpression(params);
	    infoNode.addChild(parameters, 1);
	}
	rDef.addChild(infoNode, 0);
	parent.removeChild(body);
	rDef.addChild(body);

	// restructure ruleID content
	List<T> rules = auxiliaryFindAllByType(body, ruleID);
	if (rules != null && !rules.isEmpty()) {
	    Iterator<T> it = rules.iterator();
	    while (it.hasNext()) {
		T rule = it.next();
		it.remove();
		makeAllSiblingsChildren(rule);
	    }
	}
    }

    /**
     * Restructures nodes representing the definition of a function (including
     * the manipulation of <code>TerminalNodeImpl</code>) in the sense that this
     * tree structure (still reflecting the parse tree structure) is transformed
     * into the Bird interpretation of the sdmx information model for functions.
     * 
     * @param parent
     *            the parent of all (input) parameters
     * @param fDef
     *            the <code>create function</code> node
     * @param fID
     *            the <code>FunctionID</code> node
     * @param parameters
     *            the node containing the parameters of the procedure. Depending
     *            on the number of input parameters its one of the following
     *            type of nodes: <code>ArgList</code>, <code>Arg</code>
     * @param body
     *            the <code>returns</code> node
     */
    public void restructureFunctionDef(T parent, T fDef, T fID, T parameters, T body) {
	String functionDef = OperatorII.FUNCTION_DEFINITION.toString();
	String functionInfo = OperatorIV.FUNCTION_INFO.toString();
	String functionBody = OperatorII.FUNCTION_BODY.toString();
	String params = OperatorII.PARAMETERS.toString();

	String as = OperatorI.AS.toString();
	String dataset = TreeLeaf.INPUT_DATASET.toString();

	String virtualDataset = TreeLeaf.DATASET_ID.toString();
	String virtualVariable = TreeLeaf.VARIABLE_ID.toString();

	fDef.getData().setExpression(functionDef);
	fDef.getData().setType(functionDef);

	T param = null;
	if (!parameters.getData().getType().equals(IdentificationNode.ARGUMENT.toString())) {
	    parameters.getData().setExpression(params);
	    parameters.getData().setType(params);
	} else {
	    param = (T) new BirdVtlNode();
	    param.setData((S) new NodeType(params));
	    int index = parent.getChildren().indexOf(parameters);
	    parent.removeChild(parameters);
	    parent.addChild(param, index);
	    param.addChild(parameters);
	}

	List<String> relevantTypes = new ArrayList<>();
	relevantTypes.add(StaticObjects.terminalNodeImpl);
	relevantTypes.add(IdentificationNode.ARGUMENT.toString());

	List<T> nodes = auxiliaryFindAllByType(parameters, relevantTypes);
	if (nodes != null && !nodes.isEmpty()) {
	    Iterator<T> it = nodes.iterator();
	    while (it.hasNext()) {
		T varNode = it.next();
		it.remove();
		T asNode = (T) varNode.getParent();
		if (asNode.getData().equals(new NodeType(as))) {
		    T type = (T) asNode.getChildAt(1);
		    String inputType = (type.getData().getType().equals(dataset)) ? virtualDataset : virtualVariable;
		    varNode.getData().setType(inputType);
		} else if (varNode.getData().getType().equals(IdentificationNode.ARGUMENT.toString())) {
		    varNode.getData().setType(virtualVariable);
		} else {
		    System.err.println(
			    "[restructureFunctionDef] WARNING: TerminalNodeImpl's parent is not an 'as' node and varNode is not an 'Arg' node!");
		}
	    }
	}

	BirdVtlNode infoNode = new BirdVtlNode();
	infoNode.setData(new NodeType(functionInfo));

	parent.removeChild(fID);
	infoNode.addChild(fID);
	if (param != null) {
	    parent.removeChild(param);
	    infoNode.addChild(param);
	} else {
	    parent.removeChild(parameters);
	    infoNode.addChild(parameters);
	}

	fDef.addChild(infoNode, 0);

	// add return value to return node
	makeNextSiblingChild(body);

	BirdVtlNode fBody = new BirdVtlNode();
	fBody.setData(new NodeType(functionBody));

	parent.removeChild(body);
	fBody.addChild(body);

	fDef.addChild(fBody, 1);
    }

    /**
     * Searches this tree structure for nodes where the 'right' child is a
     * container for one or many other children (e.g. the second child of an
     * <code>in</code> node). The following nodes are considered:<br>
     * <code>in</code>, <code>not in</code>, <code>keep</code>.
     */
    public void checkForParentsWithContainerChild() {
	List<String> typesToFind = new ArrayList<>();
	typesToFind.addAll(getNodesWhereSecondChildIsContainer());
	List<T> list = findAllByType(typesToFind);
	checkIfChildIsContainer(list, 1);

	typesToFind = new ArrayList<>();
	typesToFind.addAll(getNodesWhereFirstChildIsContainer());
	list = findAllByType(typesToFind);
	checkIfChildIsContainer(list, 0);
    }

    /**
     * Applies the method {@link #checkIfChildIsContainer(BirdVtlNode)} to all
     * members of the input list (i.e. <code>nodes</code>).
     * 
     * @param nodes
     *            a list of nodes
     */
    public void checkIfChildIsContainer(List<T> nodes, int position) {
	if (nodes != null && !nodes.isEmpty()) {
	    Iterator<T> it = nodes.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		it.remove();
		checkIfChildIsContainer(node, position);
	    }
	}
    }

    /**
     * Checks if the child of a node (i.e. <code>node</code>) in a specific
     * position (i.e. <code>position</code>) is of type
     * {@link OtherNode#SET_MEMBER_LIST#getNodeType()}. If this is the case the
     * expression of this child is set to
     * {@link BirdObjectNode#PARAMETERS#getNodeType()}. Otherwise such a
     * 'parameter' node is created and included between the (input) node and the
     * child (at the specific position).
     * 
     * @param node
     *            the parent node where the child at position is to be checked
     * @param position
     *            the position of the child which should be a container
     */
    public void checkIfChildIsContainer(T node, int position) {
	String setMemberList = IdentificationNode.SET_MEMBER_LIST.toString();
	String procedureInputList = IdentificationNode.PROCEDURE_INPUT_LIST.toString();
	String strExprParam = IdentificationNode.STR_EXPR_PARAM.toString();
	String parameters = OperatorII.PARAMETERS.toString();

	T secondChild = (T) node.getChildAt(position);
	if (secondChild.getData().getType().equals(setMemberList)
		|| secondChild.getData().getType().equals(procedureInputList)
		|| secondChild.getData().getType().equals(strExprParam)) {
	    secondChild.getData().setExpression(parameters);
	    secondChild.getData().setType(parameters);
	} else {
	    BirdVtlNode memberList = new BirdVtlNode();
	    memberList.setData(new NodeType(parameters));
	    int index = node.getChildren().indexOf(secondChild);
	    int numberOfChildren = node.getNumberOfChildren();
	    List<T> children = new ArrayList<>();
	    for (int i = index; i < numberOfChildren; i++) {
		children.add((T) node.getChildAt(i));
	    }
	    Iterator<T> it = children.iterator();
	    while (it.hasNext()) {
		T child = it.next();
		it.remove();
		node.removeChild(child);
		memberList.addChild(child);
	    }
	    // node.removeChild(secondChild);
	    // memberList.addChild(secondChild);
	    node.addChild(memberList, index);
	}
    }

    /**
     * Searches for <code>Comment</code> nodes and applies the method
     * {@link #transferComments(List)}.
     */
    public void restructureComments() {
	String commentString = IdentificationNode.COMMENT.toString();
	List<T> nodes = findAllByType(commentString);
	transferComments(nodes);
    }

    /**
     * Applies the method {@link #transferComment(BirdVtlNode)} to all members
     * of the input list.
     * 
     * @param commentNodes
     *            a list of <code>Comment</code> nodes
     */
    public void transferComments(List<T> commentNodes) {
	if (commentNodes != null && !commentNodes.isEmpty()) {
	    Iterator<T> it = commentNodes.iterator();
	    while (it.hasNext()) {
		T commentNode = it.next();
		it.remove();
		transferComment(commentNode);
	    }
	}
    }

    /**
     * Transfers the expression of the input node (i.e.
     * <code>commentNode</code>) either to the next sibling (if the type of node
     * of this sibling is contained in {@link #nextSiblingComment}) or to
     * function-, procedure- or ruleset definitions (in case the type of node of
     * the next sibling is contained in {@link #commentQualifyingIndicators}) or
     * to the next statement.
     * 
     * @param commentNode
     *            a <code>Comment</code> node
     */
    public void transferComment(T commentNode) {
	boolean isCommentSet = false;
	String statement = IdentificationNode.STATEMENT.toString();
	String def = OperatorI.DEF.toString();

	T nextSibling = getNextSibling(commentNode);
	String comment = commentNode.getData().getExpression();
	String typeOfSibling = nextSibling.getData().getType();

	if (getNextSiblingComment().contains(typeOfSibling)) {
	    // comments for if, elseif, else and ruleID nodes
	    nextSibling.getData().setComment(comment);
	    isCommentSet = true;
	} else if (getCommentQualifyingIndicators().contains(typeOfSibling)) {
	    // comments for functions, procedures and rule sets
	    T methodNode = (T) nextSibling.getChildAt(1);
	    String method = methodNode.getData().getType();
	    if (getCommentQualifyingMethodsTarget().contains(method)) {
		methodNode.getData().setComment(comment);
		isCommentSet = true;
	    }
	} else if (statement.equals(typeOfSibling)) {
	    // comments for statement nodes
	    T defNode = (T) nextSibling.getChildAt(0);
	    String defType = defNode.getData().getType();
	    if (defType.equals(def)) {
		T var = (T) defNode.getChildAt(0);
		var.getData().setComment(comment);
		isCommentSet = true;
	    }
	}

	if (!isCommentSet) {
	    System.out.println("[transferComment] WARNING: could not transfer comment ('" + comment + "')!");
	}
    }

    /**
     * Searches for <code>JoinExpr</code> nodes and applies the method
     * {@link #restructureJoins(List)}.
     */
    public void restructureJoins() {
	String joinExpr = OperatorII.JOIN_EXPRESSION.toString();
	List<T> nodes = findAllByType(joinExpr);
	restructureJoins(nodes);
    }

    /**
     * Applies the method {@link #restructureJoinNode(BirdVtlNode)} to all
     * members of the input list.
     * 
     * @param joins
     *            a list of <code>JoinExpr</code> nodes
     */
    public void restructureJoins(List<T> joins) {
	String joinExpression = OperatorII.JOIN_EXPRESSION.toString();
	if (joins != null && !joins.isEmpty()) {
	    Iterator<T> it = joins.iterator();
	    while (it.hasNext()) {
		T join = it.next();
		it.remove();
		join.setData((S) new NodeType(joinExpression));
		restructureJoinNode(join);
	    }
	}
    }

    /**
     * 
     * @param joinNode
     *            a <code>JoinExpr</code> node
     */
    public void restructureJoinNode(T joinNode) {
	String joinClause = OperatorII.JOIN_CLAUSE.toString();
	String joinBody = OperatorII.JOIN_BODY.toString();
	String onString = OperatorIII.ON.toString();

	List<String> joinTypes = new ArrayList<>();
	joinTypes.addAll(getTypesOfJoin());

	int numberOfChildren = joinNode.getNumberOfChildren();
	T clause = (T) joinNode.getChildAt(0);
	clause.getData().setExpression(joinClause);

	T joinType = auxiliaryFindByType(clause, joinTypes);
	int index = 0;
	if (joinType == null) {
	    joinType = getDefaultJoinTypeNode();
	}
	clause.removeChild(joinType);
	T typeOfJoin = getTypeOfJoinNode();
	typeOfJoin.addChild(joinType);

	T dSetNode1 = null;
	T dSetNode2 = null;
	dSetNode1 = (T) clause.getChildAt(index);
	dSetNode2 = (T) clause.getChildAt(index + 1);
	String dSetName1 = getDatasetName(dSetNode1);
	String dSetName2 = getDatasetName(dSetNode2);

	List<T> onList = auxiliaryFindAllByType(clause, onString);

	T on = onList.get(0);
	int position = clause.getChildren().indexOf(on);
	List<T> followingNodes = new ArrayList<>();
	for (int i = position + 1; i < clause.getNumberOfChildren(); i++) {
	    followingNodes.add((T) clause.getChildAt(i));
	}
	restructureOnNodeInJoin(on, followingNodes, dSetName1, dSetName2);
	clause.removeChild(on);

	T body = null;
	if (numberOfChildren == 1) {
	    body = (T) new BirdVtlNode();
	    body.setData((S) new NodeType(joinBody));
	} else if (numberOfChildren == 2) {
	    body = (T) joinNode.getChildAt(1);
	    body.getData().setExpression(joinBody);
	} else {
	    System.err.println("[restructureJoinNode] WARNING: joinExpr with #(children) != 1 or 2");
	}

	T datasetsToBeJoined = getDatasetsToBeJoinedNode();
	clause.removeChild(dSetNode1);
	clause.removeChild(dSetNode2);
	datasetsToBeJoined.addChild(dSetNode1);
	datasetsToBeJoined.addChild(dSetNode2);

	clause.addChild(typeOfJoin);
	clause.addChild(datasetsToBeJoined);
	clause.addChild(on);

	joinNode.removeChild(clause);
	joinNode.removeChild(body);

	joinNode.addChild(clause);
	joinNode.addChild(body);
    }

    /**
     * Extracts the name of the data set for the following cases:<br>
     * (1) setA --> setA<br>
     * (2) setA as "A" --> A
     * 
     * @param node
     *            either a <code>VarID</code> node or a
     *            <code>DatasetAlias</code> node
     * @return the name of the data set
     */
    public String getDatasetName(T node) {
	String rString = new String();
	String datasetAlias = IdentificationNode.DATASET_ALIAS.toString();
	String varID = TreeLeaf.VARIABLE_ID.toString();
	String stringC = TreeLeaf.STRING_CONSTANT.toString();

	String type = node.getData().getType();
	if (type.equals(varID)) {
	    rString = node.getData().getExpression();
	} else if (type.equals(datasetAlias)) {
	    List<T> nameNode = auxiliaryFindAllByType(node, stringC);
	    rString = nameNode.get(0).getData().getExpression().replace("\"", "");
	} else {
	    rString = null;
	}
	return rString;
    }

    /**
     * Manipulates the (node) structure of an <code>on</code> node in the sense
     * that an expression like "on x, y" gets transformed into "on
     * dSet1.x=dSet2.x and dSet1.y=dSet2.y".
     * 
     * @param onNode
     *            an <code>on</code> node
     * @param onFollowingNodes
     *            a list of all nodes following the <code>on</code> node having
     *            the same parent
     * @param dSet1
     *            the (string) name of the first data set
     * @param dSet2
     *            the (string) name of the second data set
     */
    public void restructureOnNodeInJoin(T onNode, List<T> onFollowingNodes, String dSet1, String dSet2) {
	String terminalNodeImp = StaticObjects.terminalNodeImpl;
	String componentID = TreeLeaf.COMPONENT_ID.toString();
	String and = OperatorI.AND.toString();
	String varID = TreeLeaf.VARIABLE_ID.toString();

	T firstFollowingNode = (T) onFollowingNodes.get(0);
	String type = firstFollowingNode.getData().getType();

	if (type.equals(terminalNodeImp) || type.equals(varID)) {
	    T parent = (T) onNode.getParent();
	    List<T> conditions = new ArrayList<>();
	    for (T node : onFollowingNodes) {
		node.getData().setType(componentID);
		parent.removeChild(node);
		BirdVtlNode equalNode = generateEqualsNode(node, dSet1, dSet2);
		conditions.add((T) equalNode);
	    }
	    if (conditions.size() > 1) {
		Iterator<T> it = conditions.iterator();
		BirdVtlNode currentAndNode = null;
		BirdVtlNode andNode = new BirdVtlNode();
		andNode.setData(new NodeType(and));
		while (it.hasNext()) {
		    T node = it.next();
		    it.remove();
		    if (andNode.getNumberOfChildren() == 0) {
			if (currentAndNode == null) {
			    andNode.addChild(node);
			} else {
			    andNode.addChild(currentAndNode);
			    andNode.addChild(node);
			    currentAndNode = andNode;
			    andNode = new BirdVtlNode();
			    andNode.setData(new NodeType(and));
			}
		    } else {
			if (currentAndNode == null) {
			    andNode.addChild(node);
			    currentAndNode = andNode;
			    andNode = new BirdVtlNode();
			    andNode.setData(new NodeType(and));
			} else {
			    andNode.addChild(node);
			}
		    }
		}
		onNode.addChild(currentAndNode);
	    } else {
		onNode.addChild(conditions.get(0));
	    }
	} else {
	    makeNextSiblingChild(onNode);
	}
    }

    /**
     * map: "on x" --> (dSet1.x = dSet2.x)
     * 
     * @param node
     *            a node
     * @param dSet1
     *            the (string) name of the first data set
     * @param dSet2
     *            the (string) name of the second data set
     * @return the harmonised node representation of the input node
     */
    public T generateEqualsNode(T node, String dSet1, String dSet2) {
	String varID = TreeLeaf.VARIABLE_ID.toString();
	String dot = OperatorI.DOT.toString();
	String equals = OperatorI.EQUALS.toString();

	BirdVtlNode copy = new BirdVtlNode();
	copy.setData(new NodeType(node.getData().getType(), node.getData().getExpression()));
	BirdVtlNode equalNode = new BirdVtlNode();
	equalNode.setData(new NodeType(equals));
	BirdVtlNode dotNode1 = new BirdVtlNode();
	dotNode1.setData(new NodeType(dot));
	BirdVtlNode dotNode2 = new BirdVtlNode();
	dotNode2.setData(new NodeType(dot));
	BirdVtlNode set1 = new BirdVtlNode();
	set1.setData(new NodeType(varID, dSet1));
	BirdVtlNode set2 = new BirdVtlNode();
	set2.setData(new NodeType(varID, dSet2));
	dotNode1.addChild(set1);
	dotNode1.addChild(node);
	dotNode2.addChild(set2);
	dotNode2.addChild(copy);
	equalNode.addChild(dotNode1);
	equalNode.addChild(dotNode2);
	return (T) equalNode;
    }

    /**
     * Searches for clause nodes (e.g. <code>keep</code>, <code>filter</code>,
     * etc.) and applies the method {@link #restructureClauseOperators(List)}.
     */
    public void restructureClauseOperators() {
	List<String> clauseOperators = new ArrayList<>();
	clauseOperators.addAll(getClauseOperators());
	List<T> clauseNodes = findAllByType(clauseOperators);
	restructureClauseOperators(clauseNodes);
    }

    /**
     * Applies the method {@link #restructureClauseOperator(BirdVtlNode)} to
     * each member of the input list.
     * 
     * @param clauseNodes
     *            a list of clause nodes
     */
    public void restructureClauseOperators(List<T> clauseNodes) {
	if (clauseNodes != null && !clauseNodes.isEmpty()) {
	    Iterator<T> it = clauseNodes.iterator();
	    while (it.hasNext()) {
		T clauseNode = it.next();
		it.remove();
		restructureClauseOperator(clauseNode);
	    }
	}
    }

    /**
     * Restructures a given clause node in the sense that finally the first
     * child of a clause node is the data set the operation is applied to.
     * 
     * @param clauseNode
     *            a clause node (e.g. <code>keep</code>, <code>filter</code>,
     *            etc.)
     */
    public void restructureClauseOperator(T clauseNode) {
	String joinBody = OperatorII.JOIN_BODY.toString();
	String def = OperatorI.DEF.toString();
	List<String> mathOps = new ArrayList<>();
	mathOps.addAll(getMathOperators());

	T parent = (T) clauseNode.getParent();
	String type = parent.getData().getType();
	if (type.equals(joinBody)) {
	    parent.removeChild(clauseNode);
	    T joinExpression = (T) parent.getParent();
	    T joinExpressionParent = (T) joinExpression.getParent();
	    int index = joinExpressionParent.getChildren().indexOf(joinExpression);
	    joinExpressionParent.removeChild(joinExpression);
	    clauseNode.addChild(joinExpression, 0);
	    joinExpressionParent.addChild(clauseNode, index);
	} else if (type.equals(def) || mathOps.contains(type)) {
	    makePreviousSiblingChild(clauseNode, 0);
	} else {
	    System.err.println("[restructureClauseOperator] WARNING: type of parent node is not covered in method!");
	}
    }

    /**
     * Searches for all <code>as</code> nodes in this tree structure and applies
     * the method {@link #restructureAsNodes(List)}.
     */
    public void restructureAsNodes() {
	String asString = OperatorI.AS.toString();
	List<T> asNodes = findAllByType(asString);
	restructureAsNodes(asNodes);

    }

    /**
     * Applies the method {@link #restructureAsNode(BirdVtlNode)} to each member
     * of the input list.
     * 
     * @param list
     *            a list containing <code>as</code> nodes
     */
    public void restructureAsNodes(List<T> list) {
	if (list != null && !list.isEmpty()) {
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T asNode = it.next();
		it.remove();
		restructureAsNode(asNode);
	    }
	}
    }

    /**
     * Restructures a given <code>as</code> node in the sense that in case of a
     * rename operation (i.e. a <code>rename</code> node) the child of the
     * rename node becomes the first child of the <code>as</code> node in
     * contrast to the previous sibling (otherwise).
     * 
     * @param asNode
     *            an <code>as</code> node
     */
    public void restructureAsNode(T asNode) {
	String rename = OperatorV.RENAME.toString();
	T previous = null;

	previous = getPreviousSibling(asNode);
	makeNextSiblingChild(asNode);

	String type = previous.getData().getType();
	if (type.equals(rename)) {
	    T child = (T) previous.getChildAt(0);
	    previous.removeChild(child);
	    asNode.addChild(child, 0);
	} else {
	    makePreviousSiblingChild(asNode, 0);
	}
    }

    /**
     * Searches for all <code>role</code> nodes and applies the method
     * {@link #previousAndNextSiblingToChild(List)}.
     */
    public void restructureRoleNodes() {
	String roleString = OperatorI.ROLE.toString();
	List<T> roleNodes = findAllByType(roleString);
	previousAndNextSiblingToChild(roleNodes);
    }

    /**
     * Searches for all data set operator nodes (e.g. <code>union</code>, etc.)
     * and calls the method {@link #restructureDatasetOperators(List)}.
     */
    public void restructureDatasetOperators() {
	List<String> operators = new ArrayList<>();
	operators.addAll(getDatasetOperators());
	List<T> operatorNodes = findAllByType(operators);
	restructureDatasetOperators(operatorNodes);
    }

    /**
     * Applies the method {@link #restructureDatasetOperator(BirdVtlNode)} to
     * each member of the input list.
     * 
     * @param list
     *            a list of nodes
     */
    public void restructureDatasetOperators(List<T> list) {
	if (list != null && !list.isEmpty()) {
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		it.remove();
		restructureDatasetOperator(node);
	    }
	}
    }

    /**
     * Applies the method {@link #makeNextSiblingChild(BirdVtlNode)} once for
     * each following sibling.
     * 
     * @param operator
     *            a data set operator node
     */
    public void restructureDatasetOperator(T operator) {
	T parent = (T) operator.getParent();
	int index = parent.getChildren().indexOf(operator);
	int numberOfSiblings = parent.getNumberOfChildren();
	for (int i = 0; i < (numberOfSiblings - index) - 1; i++) {
	    makeNextSiblingChild(operator);
	}
    }

    // ---------------------------------------------------------------------------------------------------------
    // setting types of nodes
    // ---------------------------------------------------------------------------------------------------------

    /**
     * Searches for specific constellations in this tree structure and assigns
     * specific types of nodes in case such constellations are found.<br>
     * The following constellations are considered:<br>
     * (i) the first child of a <code>:=</code> node is always a data set (i.e.
     * of type <code>DatasetID</code>)<br>
     * (ii) the first child of a <code>.</code> node is always a data set while
     * the second child is always a variable. Therefore the types of the
     * children will be set to <code>DatasetID</code> and <code>VarID</code>
     * respectively.<br>
     * (iii) the children of a <code>Datasets to be joined</code> node are
     * always data sets. In such a case we also need to consider that a data set
     * can be renamed inside a join operation (e.g. 'setA as "A"')<br>
     * (iv) in case the first child of a clause operator (e.g. keep, filter,
     * etc.) is a <code>VarID</code> node this method changes the type into
     * <code>DatasetID</code><br>
     * (v) in case of a <code>call</code> node where the first child's
     * expression of such a node equals "PRCDR_CHCK_RLST", the children of the
     * second child of this <code>call</code> node's <code>Parameters</code>
     * node are set to <code>DatasetID</code> and <code>RulesetID</code>
     * respectively.
     * 
     */
    public void setDefaultTypes() {
	List<T> defNodes = findAllByType(OperatorI.DEF.toString());
	setTypeOfChildTo(defNodes, 0, TreeLeaf.DATASET_ID.toString());

	List<T> dotNodes = findAllByType(OperatorI.DOT.toString());
	setTypeOfChildTo(dotNodes, 0, TreeLeaf.DATASET_ID.toString());

	dotNodes = findAllByType(OperatorI.DOT.toString());
	setTypeOfChildTo(dotNodes, 1, TreeLeaf.VARIABLE_ID.toString());

	List<T> joinedDatasetNodes = findAllByType(OperatorII.DATASETS_TO_BE_JOINED.toString());
	setJoinedDatasetChildsTo(joinedDatasetNodes);

	List<String> clauseOperators = new ArrayList<>();
	clauseOperators.addAll(getClauseOperators());
	List<T> clauseOperatorNodes = findAllByType(clauseOperators);
	setClauseOperatorChildTo(clauseOperatorNodes);

	List<String> setOperators = new ArrayList<>();
	setOperators.addAll(getDatasetOperators());
	List<T> setOperatorNodes = findAllByType(setOperators);
	setSetOperatorParameterChildrenToDatasetID(setOperatorNodes);

	List<T> equalsNodes = findAllByType(OperatorI.DEF.toString());
	setDatasetEqualsDatasetType(equalsNodes);

	List<T> joinExpressionNodes = findAllByType(OperatorII.JOIN_EXPRESSION.toString());
	handleJoinAliasDatasets(joinExpressionNodes);

    }

    /**
     * Calls the method {@link #handleJoinAliasDataset(BirdVtlNode)} for each
     * member of the input list.
     * 
     * @param list
     *            a list of <code>JoinExpr</code> nodes
     */
    public void handleJoinAliasDatasets(List<T> list) {
	if (list != null && !list.isEmpty()) {
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		handleJoinAliasDataset(node);
	    }
	}
    }

    /**
     * Searches for <code>on</code> nodes being children of the given
     * <code>joinExpressionNode</code> and identifies dataset alias. In case
     * such an alias is found (e.g. ds1 as "A" where A is the alias) the
     * <code>JoinBody</code> node's children are checked if the alias is used.
     * If the alias is used the type of such an alias is changed to
     * <code>DatasetAliasID</code> (from <code>DatasetID</code>).
     * 
     * @param joinExpressionNode
     *            a <code>JoinExpr</code> node
     */
    public void handleJoinAliasDataset(T joinExpressionNode) {
	try {
	    T parent = (T) joinExpressionNode.getParent();
	    T joinClause = (T) joinExpressionNode.getChildAt(0);
	    T datasetsToBeJoined = (T) joinClause.getChildAt(1);
	    T joinBody = (T) joinExpressionNode.getChildAt(1);
	    // TODO: make the selection of as nodes more specific (they have to
	    // be the children of the Datasets to be joined node), consider
	    // selects of selects
	    List<T> asNodes = auxiliaryFindAllByType(datasetsToBeJoined, OperatorI.AS.toString());
	    if (asNodes != null && !asNodes.isEmpty()) {
		Iterator<T> it = asNodes.iterator();
		while (it.hasNext()) {
		    T node = it.next();
		    T aliasNode = (T) node.getChildAt(1);
		    String aliasName = aliasNode.getData().getExpression().replace("\"", "");
		    List<T> aliasNodes = new ArrayList<>();
		    if (getClauseOperators().contains(parent.getData().getType())) {
			List<T> usedAliasNodes = auxiliaryFindAll(parent,
				(S) new NodeType(TreeLeaf.DATASET_ID.toString(), aliasName));
			if (usedAliasNodes != null && !usedAliasNodes.isEmpty()) {
			    aliasNodes.addAll(usedAliasNodes);
			}
		    } else {
			List<T> usedAliasNodes = auxiliaryFindAll(joinBody,
				(S) new NodeType(TreeLeaf.DATASET_ID.toString(), aliasName));
			if (usedAliasNodes != null && !usedAliasNodes.isEmpty()) {
			    aliasNodes.addAll(usedAliasNodes);
			}
			usedAliasNodes = auxiliaryFindAll(joinClause,
				(S) new NodeType(TreeLeaf.DATASET_ID.toString(), aliasName));
			if (usedAliasNodes != null && !usedAliasNodes.isEmpty()) {
			    aliasNodes.addAll(usedAliasNodes);
			}
		    }
		    if (aliasNodes != null && !aliasNodes.isEmpty()) {
			Iterator<T> iterator = aliasNodes.iterator();
			while (iterator.hasNext()) {
			    T usedAliasNode = iterator.next();
			    usedAliasNode.getData().setType(TreeLeaf.DATASET_ALIAS_ID.toString());
			}
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * Sets the Types of the input parameters of specific procedures.<br>
     * This specific treatment should be removed (on the long run) and
     * implemented in BIRDObjectIdentifier (taking into account the procedure
     * definitions)
     */
    public void setupProcedureInputParameters() {
	List<T> callNodes = findAllByType(OperatorIII.CALL.toString());
	List<T> relevantCallNodes = identifyRelevantCallNodes(callNodes, "PRCDR_CHCK_RLST");
	List<T> params = getChildAtPositionIfTypeEquals(relevantCallNodes, 1, OperatorII.PARAMETERS.toString());
	if (params != null && !params.isEmpty()) {
	    Iterator<T> it = params.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		try {
		    T firstChild = (T) node.getChildAt(0);
		    firstChild.getData().setType(TreeLeaf.DATASET_ID.toString());
		    T secondChild = (T) node.getChildAt(1);
		    secondChild.getData().setType(TreeLeaf.RULESET_ID.toString());
		    T thirdChild = (T) node.getChildAt(2);
		    thirdChild.getData().setType(TreeLeaf.DATASET_ID.toString());
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}

	callNodes = findAllByType(OperatorIII.CALL.toString());
	relevantCallNodes = identifyRelevantCallNodes(callNodes, "PRCDR_RFRNTL_INTGRTY");
	params = getChildAtPositionIfTypeEquals(relevantCallNodes, 1, OperatorII.PARAMETERS.toString());
	if (params != null && !params.isEmpty()) {
	    Iterator<T> it = params.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		try {
		    T firstChild = (T) node.getChildAt(0);
		    firstChild.getData().setType(TreeLeaf.DATASET_ID.toString());
		    T secondChild = (T) node.getChildAt(1);
		    secondChild.getData().setType(TreeLeaf.VARIABLE_ID.toString());
		    T thirdChild = (T) node.getChildAt(2);
		    thirdChild.getData().setType(TreeLeaf.DATASET_ID.toString());
		    T fourthChild = (T) node.getChildAt(3);
		    fourthChild.getData().setType(TreeLeaf.VARIABLE_ID.toString());
		    T fifthChild = (T) node.getChildAt(4);
		    fifthChild.getData().setType(TreeLeaf.DATASET_ID.toString());
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    /**
     * 
     * @param list
     *            a list of nodes
     * @param position
     *            the position of the child that should be extracted
     * @param type
     *            the type of the child that should be extracted
     * @return a list of nodes containing the the child at the given
     *         <code>position</code> iff the type of the child equals the given
     *         <code>type</code>
     */
    public List<T> getChildAtPositionIfTypeEquals(List<T> list, int position, String type) {
	List<T> rList = null;
	if (list != null && !list.isEmpty()) {
	    rList = new ArrayList<>();
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		try {
		    T child = (T) node.getChildAt(position);
		    if (child.getData().getType().equals(type)) {
			rList.add(child);
		    }
		} catch (Exception e) {
		    // CONFIG: add exception handling if necessary
		}
	    }
	}
	return rList;
    }

    /**
     * This methods sets the type of node of the right hand side of an equation
     * in case of equations like result := x (where the type of x is set to
     * DatasetID). Additionally this methods takes into account if-then-else
     * structures on the right hand side of equations (in the sense that the
     * child of each <code>then</code> node is a dataset, except the value is
     * null).
     * 
     * @param list
     *            a list of <code>:=</code> nodes
     */
    public void setDatasetEqualsDatasetType(List<T> list) {
	if (list != null && !list.isEmpty()) {
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		if (node.getChildAt(1) != null) {
		    T rightSide = (T) node.getChildAt(1);
		    if (!rightSide.hasChildren()) {
			rightSide.getData().setType(TreeLeaf.DATASET_ID.toString());
		    } else if (rightSide.getData().getType().equals(OperatorII.IF_THEN_ELSE.toString())) {
			List<T> thenNodes = auxiliaryFindAllByType(rightSide, OperatorIII.THEN.toString());
			List<T> elseNodes = auxiliaryFindAllByType(rightSide, OperatorIII.ELSE.toString());
			List<T> nodes = new ArrayList<>();
			if (thenNodes != null) {
			    nodes.addAll(thenNodes);
			}
			if (elseNodes != null) {
			    nodes.addAll(elseNodes);
			}
			Iterator<T> iterator = nodes.iterator();
			while (iterator.hasNext()) {
			    T thenNode = iterator.next();
			    T conditionResult = (T) thenNode.getChildAt(0);
			    if (!conditionResult.getData().getExpression().equals(TreeLeaf.NULL.toString())) {
				conditionResult.getData().setType(TreeLeaf.DATASET_ID.toString());
			    }
			}
		    }
		}
	    }
	}
    }

    /**
     * Identifies all children that are <code>Parameters</code> nodes of each
     * member of the input list and calls
     * {@link #setTypeOfChildTo(List, Integer, String)} on those nodes.
     * 
     * @param list
     *            a list of set operator nodes
     */
    public void setSetOperatorParameterChildrenToDatasetID(List<T> list) {
	if (list != null && !list.isEmpty()) {
	    List<T> paramNodes = new ArrayList<>();
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		if (node.getNumberOfChildren() == 1) {
		    T child = (T) node.getChildAt(0);
		    if (child.getData().getType().equals(OperatorII.PARAMETERS.toString())) {
			paramNodes.add(child);
		    }

		} else {
		    System.err.println(
			    "WARNING: found set operator with getNumberOfChildren() != 1 (the only child should be the Parameters node!)");
		}
	    }
	    if (paramNodes != null && !paramNodes.isEmpty()) {
		setTypeOfChildTo(paramNodes, null, TreeLeaf.DATASET_ID.toString());
	    }
	}
    }

    public List<T> identifyRelevantCallNodes(List<T> callNodes, String procedureName) {
	List<T> rList = new ArrayList<>();
	if (callNodes != null && !callNodes.isEmpty()) {
	    Iterator<T> it = callNodes.iterator();
	    while (it.hasNext()) {
		T callNode = it.next();
		it.remove();
		try {
		    T procedureNode = (T) callNode.getChildAt(0);
		    String expression = procedureNode.getData().getExpression();
		    // TODO: clean up string reference
		    if (expression.equals(procedureName)) {
			rList.add(callNode);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
	return rList;
    }

    /**
     * Identifies those members of the input list where the first child is of
     * type {@link TreeLeaf#VARIABLE_ID#getTypeOfNode()} and applies the method
     * {@link #setTypeOfChildTo(List, Integer, String)}.
     * 
     * @param list
     *            a list of clause nodes
     */
    public void setClauseOperatorChildTo(List<T> list) {
	if (list != null && !list.isEmpty()) {
	    List<T> firstChildIsVarID = new ArrayList<>();
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T clauseNode = it.next();
		it.remove();
		T child = (T) clauseNode.getChildAt(0);
		if (child.getData().getType().equals(TreeLeaf.VARIABLE_ID.toString())) {
		    firstChildIsVarID.add(clauseNode);
		}
	    }
	    setTypeOfChildTo(firstChildIsVarID, 0, TreeLeaf.DATASET_ID.toString());
	}
    }

    /**
     * Applies the method {@link #setJoinedDatasetChildsTo(BirdVtlNode)} to each
     * member of the input list.
     * 
     * @param list
     *            a list of <code>Datasets to be joined</code> nodes
     */
    public void setJoinedDatasetChildsTo(List<T> list) {
	if (list != null && !list.isEmpty()) {
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		it.remove();
		setJoinedDatasetChildsTo(node);
	    }
	}
    }

    /**
     * Identifies the <code>VarID</code> node in the children of the given
     * <code>Datasets to be joined</code> node and replaces the type to
     * <code>DatasetID</code>.
     * 
     * @param node
     *            a <code>Datasets to be joined</code> node
     */
    public void setJoinedDatasetChildsTo(T node) {
	for (int i = 0; i < node.getNumberOfChildren(); i++) {
	    T child = (T) node.getChildAt(i);
	    T varIdNode = getVarIDNode(child);
	    if (varIdNode != null) {
		varIdNode.getData().setType(TreeLeaf.DATASET_ID.toString());
	    }
	}
    }

    /**
     * 
     * @param node
     *            either a <code>VarID</code> node or a <code>as</code> node
     *            having a <code>VarID</code> node as child
     * @return the <code>VarID</code> node; if no <code>VarID</code> node is
     *         present this method returns <code>null</code>
     */
    public T getVarIDNode(T node) {
	String varID = TreeLeaf.VARIABLE_ID.toString();
	String as = OperatorI.AS.toString();
	T variable = null;
	if (node.getData().getType().equals(varID)) {
	    variable = node;
	} else if (node.getData().getType().equals(as)) {
	    List<String> list = new ArrayList<>();
	    list.add(varID);
	    variable = auxiliaryFindByType(node, list);
	} else {
	}
	return variable;
    }

    /**
     * Applies the method {@link #setTypeOfChildTo(List, Integer, String)} to
     * each member of the input list.
     * 
     * @param list
     *            a list of nodes
     * @param childIndex
     *            the index of the child who's type should be changed;
     *            <code>null</code> in case that all children should be changed
     * @param type
     *            the type of node that the child will be set to
     */
    public void setTypeOfChildTo(List<T> list, Integer childIndex, String type) {
	if (list != null && !list.isEmpty()) {
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		it.remove();
		setTypeOfChildTo(node, childIndex, type);
	    }
	}
    }

    /**
     * 
     * @param node
     *            a node who's child's type of node should be changed / set
     * @param childIndex
     *            the index of the child; <code>null</code> in case that all
     *            children should be changed / set
     * @param type
     *            the type of node which will be set
     */
    public void setTypeOfChildTo(T node, Integer childIndex, String type) {
	int min = 0;
	if (childIndex == null) {
	    childIndex = node.getNumberOfChildren() - 1;
	} else {
	    min = childIndex;
	}
	for (int i = min; i <= childIndex; i++) {
	    T child = (T) node.getChildAt(i);
	    child.getData().setType(type);
	}
    }

    /**
     * In case the <code>origin</code> node (i.e. the root) has only one child
     * (i.e. the given expression results in only one transformation scheme)
     */
    public void removeOrigin() {
	if (getRoot() != null) {
	    if (getRoot().getData().equals(new NodeType(StaticObjects.origin))) {
		T root = getRoot();
		if (root.hasChildren() && root.getNumberOfChildren() == 1) {
		    T child = (T) root.getChildAt(0);
		    root.removeChild(child);
		    setRoot(child);
		}
	    }
	}
    }

    /**
     * Renames the expression of specific nodes (e.g. <code>FunctionCall</code>)
     */
    public void renameSpecificNodes() {
	List<T> functionCallNodes = findAllByType(OperatorII.FUNCTION_CALL.toString());
	renameNodeExpression(functionCallNodes, OperatorII.FUNCTION_CALL.toString());

	List<T> procedureInputNodes = findAllByType(IdentificationNode.PROCEDURE_INPUT.toString());
	renameNodeType(procedureInputNodes, TreeLeaf.VARIABLE_ID.toString());
    }

    public void renameNodeType(List<T> list, String type) {
	renameNode(list, type, true);
    }

    public void renameNodeExpression(List<T> list, String expression) {
	renameNode(list, expression, false);
    }

    /**
     * Renames the expression or type of a node
     * 
     * @param list
     *            a list of nodes
     * @param newExpression
     *            the type or expression
     * @param type
     *            <code>true</code> if the type should be renamed,
     *            <code>false</code> otherwise
     */
    public void renameNode(List<T> list, String newExpression, boolean type) {
	if (list != null && !list.isEmpty()) {
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T node = (T) it.next();
		it.remove();
		if (type) {
		    node.getData().setType(newExpression);
		} else {
		    node.getData().setExpression(newExpression);
		}
	    }
	}
    }

    /**
     * Searches for all <code>group by</code> nodes and calls the method
     * {@link #restructureGroupByOperators(List)}.
     */
    public void restructureGroupByOperators() {
	List<T> groupByOps = findAllByType(OperatorIII.GROUP_BY.toString());
	restructureGroupByOperators(groupByOps);
    }

    /**
     * Applies the method {@link #restructureGroupByOperator(BirdVtlNode)} for
     * each member of the input list.
     * 
     * @param list
     *            a list of <code>group by</code> nodes
     */
    public void restructureGroupByOperators(List<T> list) {
	if (list != null && !list.isEmpty()) {
	    Iterator<T> it = list.iterator();
	    while (it.hasNext()) {
		T groupByOp = it.next();
		it.remove();
		restructureGroupByOperator(groupByOp);
	    }
	}
    }

    /**
     * Places an <code>Aggregate Function</code> node in between a
     * <code>group by</code> node and it's parent. Additionally the previous
     * node (with respect to the <code>group by</code> node) is also placed as a
     * child to the <code>Aggregate Function</code> node.
     * 
     * @param node
     *            a <code>group by</code> node
     */
    public void restructureGroupByOperator(T node) {
	T parent = (T) node.getParent();
	T previousSibling = getPreviousSibling(node);
	int index = parent.getChildren().indexOf(previousSibling);
	parent.removeChild(previousSibling);
	parent.removeChild(node);
	T aggregateNode = getAggregateFunctionNode();
	aggregateNode.addChild(previousSibling);
	aggregateNode.addChild(node);
	parent.addChild(aggregateNode, index);
    }

    // ---------------------------------------------------------------------------------------------------------
    // getters for default nodes
    // ---------------------------------------------------------------------------------------------------------

    /**
     * 
     * @return a <code>AggregateFunction</code> node
     */
    public T getAggregateFunctionNode() {
	String aggregate = OperatorII.AGGREGATE_FUNCTION.toString();
	BirdVtlNode rNode = new BirdVtlNode();
	rNode.setData((S) new NodeType(aggregate));
	return (T) rNode;
    }

    /**
     * 
     * @return a <code>Datasets to be joined</code> node
     */
    public T getDatasetsToBeJoinedNode() {
	String text = OperatorII.DATASETS_TO_BE_JOINED.toString();
	BirdVtlNode rNode = new BirdVtlNode();
	rNode.setData((S) new NodeType(text));
	return (T) rNode;
    }

    /**
     * 
     * @return a <code>on</code> node
     */
    public T getOnNode() {
	String onString = OperatorIII.ON.toString();
	BirdVtlNode onNode = new BirdVtlNode();
	onNode.setData(new NodeType(onString));
	return (T) onNode;
    }

    /**
     * 
     * @return the default type of join in case it is not explicitly specified
     */
    public T getDefaultJoinTypeNode() {
	String inner = TreeLeaf.INNER.toString();
	BirdVtlNode joinType = (T) new BirdVtlNode();
	joinType.setData((S) new NodeType(inner));
	return (T) joinType;
    }

    /**
     * 
     * @return the <code>Type of join</code> node
     */
    public T getTypeOfJoinNode() {
	String text = OperatorII.TYPE_OF_JOIN.toString();
	BirdVtlNode typeOfJoin = (T) new BirdVtlNode();
	typeOfJoin.setData((S) new NodeType(text));
	return (T) typeOfJoin;
    }

    // ---------------------------------------------------------------------------------------------------------
    // specific method to start the manipulation of the tree structure in order
    // to retrieve a unambiguous representation of the vtl expression as
    // interpreted by the bird.
    // ---------------------------------------------------------------------------------------------------------

    /**
     * Method that starts the manipulation of this tree structure in order to
     * create a unambiguous tree structure reflecting the vtl syntax.
     */
    public void transformTreeStructure() {
	// reduce leafs of this tree structure
	reduceLeafs();
	// reduce branches of this tree structure
	reduceBranches();
	// remove symbols that do not contain semantic meaning
	removeSymbols();

	// reorder children of each node of this tree structure
	reorderAllChildren();

	// specific nodes: (initial) next sibling --> child
	nextSiblingToChild();

	// specific nodes: (initial) previous sibling, next sibling --> child
	previousAndNextSiblingToChild();

	// data set operators, e.g. union, setdiff, symdiff
	restructureDatasetOperators();

	// as node
	restructureAsNodes();

	// role nodes
	restructureRoleNodes();

	// reorder if-then-elseif-then-else structures
	restructureIfElseIfElseOperators();

	// transfer comments
	restructureComments();

	// restructure functions, procedures and rule set definitions
	restructureMethods();

	// System.out.println(toStringWithDepth());

	// check 'container' nodes (e.g. the right hand side of an 'in' node)
	checkForParentsWithContainerChild();

	// rename specific nodes (e.g. FunctionNode)
	renameSpecificNodes();

	// restructure joins
	restructureJoins();

	// System.out.println("* before removal of unnecessary nodes:");
	// System.out.println(toStringWithDepth());

	// UPDATE: remove all unnecessary nodes of this tree
	removeNodes();

	// System.out.println("* before restructure of clause operators:");
	// System.out.println(toStringWithDepth());

	// handle group by operators
	restructureGroupByOperators();

	// handle filter, keep, drop, etc. operators --> structure
	restructureClauseOperators();

	// set type of nodes for dot, def, clause and joined dataset operators
	setDefaultTypes();

	// set types of input parameters of specific procedures
	setupProcedureInputParameters();

	// remove origin in case this tree contains only one transformation
	removeOrigin();

    }

    // ---------------------------------------------------------------------------------------------------------
    // quick & dirty
    // ---------------------------------------------------------------------------------------------------------

    public Set<String> getTypes() {
	Set<String> rSet = null;
	Set<T> nodes = null;
	if (getRoot() != null) {
	    rSet = new HashSet<>();
	    nodes = new HashSet<>();
	    List<T> leafs = getLeafs();
	    Iterator<T> it = leafs.iterator();
	    while (it.hasNext()) {
		T node = it.next();
		nodes.add(node);
		T parent = (T) node.getParent();
		while (parent != null) {
		    nodes.add(parent);
		    parent = (T) parent.getParent();
		}
	    }
	    for (T node : nodes) {
		rSet.add(node.getData().getType());
	    }
	}
	return rSet;
    }
}
