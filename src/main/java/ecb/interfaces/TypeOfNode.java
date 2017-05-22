package ecb.interfaces;

import java.util.Map;

import enums.typesOfNodes.Bracket;


public interface TypeOfNode {
    /**
     * 
     * @return the string representation of this type of node
     */
    public String toString();

    /**
     * 
     * @return <code>true</code> if this type of node has children,
     *         <code>false</code> otherwise
     */
    public boolean isHasChildren();

    /**
     * In case {@link #isHasChildren()} equals <code>true</code> this methods
     * returns an {@link Integer} object. Otherwise this method returns
     * <code>null</code>.
     * 
     * @return the number of children of this type of node
     */
    public Integer getNumberOfChildren();

    /**
     * 
     * @return the type of {@link Bracket} this type of node's children are
     *         covered in
     */
    public Bracket getBracket();

    /**
     * 
     * @return the {@link String} that is used as a separator between an
     *         operator and it's children
     */
    default public String getSymbolBetweenOperatorAndChildren() {
	return " ";
    };

    /**
     * 
     * @return a {@link String} that is used to separate this type of node's
     *         children
     */
    public String getSeparator();

    /**
     * 
     * @return the {@link String} that represents the end of line (EOL) symbol
     */
    default public String getEOL() {
	return ";";
    };

    /**
     * 
     * @param <T>
     *            an object implementing {@link TypeOfNode} and {@link VtlBuild}
     * @return a map containing information about the children of this type of
     *         node
     */
    @SuppressWarnings("hiding")
    default public <T extends TypeOfNode, VtlBuild> Map<Integer, T> getInfoMap() {
	return null;
    };
}
