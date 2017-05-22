package functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enums.typesOfNodes.Bracket;
import enums.webcontent.WebContent;


public class XmlFunctions {

    private static String fontStyle = "Courier New";

    private static String prefix = "[simple_tooltip content='";
    private static String midfix = "']";
    private static String postfix = "[/simple_tooltip]";

    private static String iPrefix = "<i>";
    private static String iPostfix = "</i>";

    private static String commentStylePre = "<font style=\"font-family:";
    private static String commentStyleMid = ";\", color=\"Azure\">";
    private static String commentStylePost = "</font>";

    private static String stylePre = "<font style=\"font-family:";
    private static String styleMid = ";\">";
    private static String stylePost = "</font>";

    public static List<String> linkComponents = new ArrayList<>(
	    Arrays.asList("<a href=\"/", "/", "-", "\"", ">", "</a>"));

    public static String includeToolTip(String expression, String comment) {
	return prefix + comment + midfix + iPrefix + expression + iPostfix + postfix;
    }

    public static String commentStyle(String text) {
	return commentStylePre + fontStyle + commentStyleMid + text + commentStylePost;
    }

    public static String lineStyle(String text) {
	return stylePre + fontStyle + styleMid + text + stylePost;
    }

    public static String coverInHtmlLink(String text, WebContent typeOfObject) {
	return coverInHtmlLink(text, text, typeOfObject);
    }
    
    public static String coverInHtmlLink(String text, String link, WebContent typeOfObject) {
	return coverInHtmlLink(text, link, typeOfObject.getDirectory(), typeOfObject.getTypeOfContent());
    }
    
    public static String coverInHtmlLink(String text, String directory, String typeOfContent) {
	return coverInHtmlLink(text, text, directory, typeOfContent);
    }

    public static String coverInHtmlLink(String text, String link, String directory, String typeOfContent) {
	return linkComponents.get(0) + directory + linkComponents.get(1) + link + linkComponents.get(2) + typeOfContent
		+ linkComponents.get(3) + linkComponents.get(4) + text + linkComponents.get(5);
    }
    public static String coverInFontFamily(String toBeCovered, boolean htmlConfig, Bracket bracket) {
	return bracket.fontFamilyMap.get(htmlConfig).get(0) + toBeCovered
		+ bracket.fontFamilyMap.get(htmlConfig).get(1);
    }

    public static String coverInIndention(String toBeCovered, boolean htmlConfig, Bracket bracket) {
	return bracket.indentMap.get(htmlConfig).get(0) + toBeCovered + bracket.indentMap.get(htmlConfig).get(1);
    }

    public static String coverInBracket(String toBeCovered, boolean htmlConfig, Bracket bracket) {
	return bracket.left + coverInIndention(coverInFontFamily(toBeCovered, htmlConfig, bracket),
		htmlConfig, bracket) + bracket.right;
    }

    
}