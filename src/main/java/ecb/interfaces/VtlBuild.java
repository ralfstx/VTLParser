package ecb.interfaces;

import classes.abstractClasses.AbstractNode;

public interface VtlBuild {
    @SuppressWarnings("hiding")
    default public <T extends AbstractNode<S>, VtlBuild, S extends NodeTypeGetters> String buildVtlCode(T node) {
	return buildVtlCode(node, true);
    };
    
    @SuppressWarnings("hiding")
    public <T extends AbstractNode<S>, VtlBuild, S extends NodeTypeGetters> String buildVtlCode(T node, boolean htmlConfig);
}
