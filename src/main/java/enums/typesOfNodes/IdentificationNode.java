package enums.typesOfNodes;

import classes.abstractClasses.AbstractNode;
import ecb.interfaces.NodeTypeGetters;
import ecb.interfaces.TypeOfNode;
import ecb.interfaces.VtlBuild;

public enum IdentificationNode implements TypeOfNode, VtlBuild {
    DEFINE_DATAPOINT_RULESET("DefineDatapointRuleset"),
    CREATE_FUNCTION("create function"),
    DEFINE_PROCEDURE("define procedure"),
    COMMENT ("Comment"),
    SET_MEMBER_LIST ("SetMemberList"),
    PROCEDURE_ARGUMENT_LIST ("ProcedureArgList"),
    PROCEDURE_ARGUMENT ("ProcedureArg"),
    RULESET_ARGUMENT_LIST ("RulesetArgList"),
    RULESET_ARGUMENT ("RulesetArg"),
    ARGUMENT_LIST ("ArgList"),
    STATEMENT ("Statement"),
    DEF_FUNCTION ("DefFunction"),
    DEF_PROCEDURE ("DefProcedure"),
    DEF_RULESET ("DefDatapoint"),
    EXPRESSION_EQUATION ("ExprEq"),
    DATASET_ALIAS ("DatasetAlias"),
    ARGUMENT("Arg"),
    PROCEDURE_INPUT_LIST("ProcedureInputList"),
    PROCEDURE_CALL_BODY("ProcedureCallBody"),
    PROCEDURE_INPUT("ProcedureInput"),
    STR_EXPR_PARAM("strExprParam")
    ;
    
    private String typeOfNode;
    private boolean hasChildren = false;
    private Integer numberOfChildren = null;
    private Bracket bracket = Bracket.NONE;
    private String separator = " ";

    IdentificationNode(String typeOfNode) {
	this.typeOfNode = typeOfNode;
    }

    @Override
    public String toString() {
	return typeOfNode;
    }

    @Override
    public boolean isHasChildren() {
	return hasChildren;
    }

    @Override
    public Integer getNumberOfChildren() {
	return numberOfChildren;
    }

    @Override
    public Bracket getBracket() {
	return bracket;
    }

    @Override
    public String getSeparator() {
	return separator;
    }

    @SuppressWarnings("hiding")
    @Override
    public <T extends AbstractNode<S>, VtlBuild, S extends NodeTypeGetters> String buildVtlCode(T node, boolean htmlConfig) {
	return null;
    }

}
