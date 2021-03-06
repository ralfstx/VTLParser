package ecb.birdVtlParser.basicObjects.parserObjects;

import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr4.VtlLexer;
import antlr4.VtlParser;
import classes.NodeType;
import classes.vtlSpecific.BirdVtlNode;
import classes.vtlSpecific.BirdVtlTree;
import staticObjects.StaticObjects;

/**
 * A "compiler" class containing methods to extract:<br>
 * (i) {@link ParseTree} object (see {@link #compile(String)})<br>
 * (ii) {@link BirdVtlTree} object (see {@link #extractTree(String)})<br>
 * from a valid vtl statement.<br>
 * Please note that the he term <b>valid</b> means "in line with the bird vtl
 * grammar".
 * 
 * @author lindomi
 * @version 0.1
 */
public class BirdVtlCompiler extends AbstractCompiler {

  private StringBuilder errorLog = new StringBuilder();
  private ANTLRErrorListener errorListener = new CollectingErrorListener();
  private int[] lineLengths;

    /**
     * an {@link ArrayList} of {@link String} objects representing the tokens of
     * the grammar
     */
    public static ArrayList<String> tokens = new ArrayList<String>();

    /**
     * sets up {@link #tokens}. TODO: clean up extraction of tokens, see
     * http://stackoverflow.com/questions/15006720/antlr4-obtaining-list-of-tokens-for-a-specific-rule-in-listener
     */
    public void setup() {
	Vocabulary voc = VtlParser.VOCABULARY;
	try {
	    for (int i = 0; i < 1000; i++) {
		if (voc.getLiteralName(i) != null) {
		    tokens.add(voc.getLiteralName(i));
		}
	    }
	} catch (Exception e) {
	}
    }

    @Override
    public ParseTree compile(String expression) {
      errorLog.setLength(0);
      lineLengths = calculateLineLengths(expression);
    	ANTLRInputStream input = new ANTLRInputStream(expression.replace("\n", " "));
    	VtlLexer lexer = new VtlLexer(input);
    	lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
    	lexer.addErrorListener(errorListener);
    	CommonTokenStream tokens = new CommonTokenStream(lexer);
    	VtlParser parser = new VtlParser(tokens);
    	parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
      parser.addErrorListener(errorListener);
    	ParseTree tree = parser.start();
    	return tree;
    }

    private int[] calculateLineLengths(String expression) {
      String[] lines = expression.split("\n");
      int[] lengths = new int[lines.length];
      for (int i = 0; i < lines.length; i++) {
        lengths[i] = lines[i].length() + 1;
      }
      return lengths;
    }

    /**
     * Extracts a {@link BirdVtlTree} object from a valid vtl expression. In
     * case the expression is not valid a warning is displayed on the console
     * and <code>null</code> is returned.
     * 
     * @param expression
     *            a valid vtl expression
     * @return a {@link BirdVtlTree} object reflecting the vtl expression
     */
    public BirdVtlTree<BirdVtlNode<NodeType>, NodeType> extractTree(String expression) throws ParseException {
	BirdVtlTree<BirdVtlNode<NodeType>, NodeType> tree = null;
	ParseTree parseTree = compile(expression);
	if (parseTree != null) {
	    setup();
	    tree = new BirdVtlTree<BirdVtlNode<NodeType>, NodeType>();
	    BirdVtlNode<NodeType> origin = new BirdVtlNode<NodeType>();
	    origin.setData(new NodeType("origin", "origin"));
	    visit(parseTree, origin);
	    tree.setRoot(origin);
	    if (tree.findAllByType(StaticObjects.errorNodeImpl) != null) {
		tree = null;
	    } else {
		tree.transformTreeStructure();
	    }
	}
  if (tree == null) {
    throw new ParseException(errorLog.toString());
  }
	return tree;
    }

    /**
     * Visits each node of the <code>inputTree</code> and generates related
     * {@link BirdVtlNode} objects with the (input) <code>node</code> as it's
     * root.
     * 
     * @param inputTree
     *            a {@link ParseTree} object that will be visited
     * @param node
     *            a {@link BirdVtlNode} representing the root of the
     *            (sub){@link BirdVtlTree} that will be created by this method
     */
    @SuppressWarnings("unchecked")
    public static <T extends NodeType> void visit(ParseTree inputTree, BirdVtlNode<T> node) {
	if (inputTree.getChildCount() > 0) {
	    for (int i = 0; i < inputTree.getChildCount(); i++) {
		ParseTree child = inputTree.getChild(i);

		String type = child.getClass().getSimpleName().replace("Context", "");
		String expression = child.getText();
		NodeType nodeType = new NodeType(type, expression);

		if (type.equals(StaticObjects.terminalNodeImpl)) {
		    if (tokens.contains("'" + expression + "'")) {
			nodeType.setType(expression);
		    }
		}

		BirdVtlNode<T> childNode = new BirdVtlNode<T>();
		childNode.setData((T) nodeType);
		node.addChild(childNode);
		visit(child, childNode);
	    }
	} else {
	    // a leaf of the tree
	    String type = inputTree.getClass().getSimpleName().replaceAll("Context", "");
	    String expression = inputTree.getText();
	    NodeType nodeType = new NodeType(type, expression);
	    BirdVtlNode<T> childNode = new BirdVtlNode<T>();
	    childNode.setData((T) nodeType);
	    node.addChild(childNode);
	}
    }
    
    class CollectingErrorListener extends BaseErrorListener {
      public void syntaxError(Recognizer<?, ?> recognizer,
                  Object offendingSymbol,
                  int line,
                  int charPositionInLine,
                  String msg,
                  RecognitionException e)
      {
        int column = charPositionInLine;
        for (int i = 0; i < lineLengths.length; i++) {
          if (column < lineLengths[i]) {
            break;
          }
          line++;
          column -= lineLengths[i];
        }
        errorLog.append("line " + line + ":" + column + " " + msg + "\n");
      }

    }
}
