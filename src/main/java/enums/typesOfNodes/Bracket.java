package enums.typesOfNodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public enum Bracket {
    ROUND("(", ")", new HashMap<Boolean, String>() {
	{
	    put(true, "");
	    put(false, "");
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("", "")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("", "")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }),

    ROUND_NEXTLINE("(", ")", new HashMap<Boolean, String>() {
	{
	    put(true, "</br>");
	    put(false, "\n");
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("", "")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("<div style=\"font-family:Courier New\">", "</div>")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }),
    ROUND_NEXTLINE_INDENT("(", ")", new HashMap<Boolean, String>() {
	{
	    put(true, "</br>");
	    put(false, "\n");
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("<div style=\"margin-left: 3em\">", "</div>")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("<div style=\"font-family:Courier New\">", "</div>")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }),

    SQUARE("[", "]", new HashMap<Boolean, String>() {
	{
	    put(true, "");
	    put(false, "");
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("", "")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("", "")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }),

    SQUARE_NEXTLINE("[", "]", new HashMap<Boolean, String>() {
	{
	    put(true, "</br>");
	    put(false, "\n");
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("", "")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("<div style=\"font-family:Courier New\">", "</div>")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }),
    SQUARE_NEXTLINE_INDENT("{", "}", new HashMap<Boolean, String>() {
	{
	    put(true, "</br>");
	    put(false, "\n");
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("<div style=\"margin-left: 3em\">", "</div>")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("<div style=\"font-family:Courier New\">", "</div>")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }),

    CURLED("{", "}", new HashMap<Boolean, String>() {
	{
	    put(true, "");
	    put(false, "");
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("", "")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("", "")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }),

    CURLED_NEXTLINE("{", "}", new HashMap<Boolean, String>() {
	{
	    put(true, "</br>");
	    put(false, "\n");
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("", "")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("<div style=\"font-family:Courier New\">", "</div>")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }),
    CURLED_NEXTLINE_INDENT("{", "}", new HashMap<Boolean, String>() {
	{
	    put(true, "</br>");
	    put(false, "\n");
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("<div style=\"margin-left: 3em\">", "</div>")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("<div style=\"font-family:Courier New\">", "</div>")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }),

    NONE("", "", new HashMap<Boolean, String>() {
	{
	    put(true, "");
	    put(false, "");
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("", "")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }, new HashMap<Boolean, List<String>>() {
	{
	    put(true, new ArrayList<>(Arrays.asList("", "")));
	    put(false, new ArrayList<>(Arrays.asList("", "")));
	}
    }),

    ;

    public Map<Boolean, String> newLineMap;
    public Map<Boolean, List<String>> indentMap;
    public Map<Boolean, List<String>> fontFamilyMap;

    public String left;
    public String right;

    Bracket(String left, String right, Map<Boolean, String> newLineMap, Map<Boolean, List<String>> indentMap,
	    Map<Boolean, List<String>> fontFamily) {
	this.left = left;
	this.right = right;
	this.newLineMap = newLineMap;
	this.indentMap = indentMap;
	this.fontFamilyMap = fontFamily;
    }

    Bracket(String left, String right) {
	this.left = left;
	this.right = right;
    }

    Bracket(String left, String right, boolean newLine) {
	this.left = left;
	this.right = right;
    }

    public Map<Boolean, List<String>> getFontFamilyMap() {
	return fontFamilyMap;
    }

    public List<String> getFontFamily(boolean htmlConfig) {
	return getFontFamilyMap().get(htmlConfig);
    }

    public String getFontFamilyPre(boolean htmlConfig) {
	return getFontFamily(htmlConfig).get(0);
    }

    public String getFontFamilyPost(boolean htmlConfig) {
	return getFontFamily(htmlConfig).get(1);
    }

    public Map<Boolean, List<String>> getIndentMap() {
	return indentMap;
    }

    public List<String> getIndent(boolean htmlConfig) {
	return getIndentMap().get(htmlConfig);
    }

    public String getIndentPre(boolean htmlConfig) {
	return getIndent(htmlConfig).get(0);
    }

    public String getIndentPost(boolean htmlConfig) {
	return getIndent(htmlConfig).get(1);
    }

    public Map<Boolean, String> getNewLineMap() {
	return newLineMap;
    }

    public String getNewLine(boolean htmlConfig) {
	return getNewLineMap().get(htmlConfig);
    }

    public String getRight() {
	return right;
    }

    public String getLeft() {
	return left;
    }
}
