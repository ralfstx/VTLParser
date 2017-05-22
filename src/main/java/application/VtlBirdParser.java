package application;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.tree.ParseTree;

import classes.NodeType;
import classes.vtlSpecific.BirdVtlNode;
import classes.vtlSpecific.BirdVtlTree;
import ecb.birdVtlParser.basicObjects.parserObjects.AST;
import ecb.birdVtlParser.basicObjects.parserObjects.BirdVtlCompiler;
import functions.Functions;

public class VtlBirdParser {

    public static <T extends BirdVtlNode<S>, S extends NodeType> void main(String[] agrs) {
	LocalDateTime start = LocalDateTime.now();
	System.out.println("*********************************");
	System.out.println(start);

	String expression = "/*this is a test expression*/ result := coordinates [calc sqrt(x*x+y*y) as \"distance\"];"
			;

	System.out.println("******************************************************************");
	System.out.println("* Expression:");
	System.out.println(expression);
	System.out.println("******************************************************************");

	BirdVtlCompiler compiler = new BirdVtlCompiler(); // initiate compiler

	ParseTree parseTree = compiler.compile(expression); // create parse tree

	AST ast = new AST(parseTree); // create abstract syntax tree
	System.out.println("* Abstract syntax tree:");
	System.out.println(ast.toString()); // visualize abstract syntax tree
	System.out.println("******************************************************************");

	@SuppressWarnings("rawtypes")
	/*
	 * create Bird vtl tree structure (i.e. tree structure reflecting the
	 * sdmx information model for transformations)
	 */
	BirdVtlTree tree = compiler.extractTree(expression);

	System.out.println("* Bird Vtl tree structure:");
	System.out.println(tree.toStringWithDepth(true)); // visualize bird vtl
							  // tree

	System.out.println("******************************************************************");

	LocalDateTime end = LocalDateTime.now();
	System.out.println(end);
	System.out.println("*********************************");
	Functions.showDifferenceInTime(start, end);
    }

}
