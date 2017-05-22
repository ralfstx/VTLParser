package enums.webcontent;

public enum WebContent implements interfaces.webcontent.WebContent {
    CUBE("cubes", "cube"),
    VARIABLE("variables", "variable"),
    DOMAIN("domains", "domain"),
    RULESET("transformations", "ruleset"),
    FUNCTION("transformations", "function"),
    PROCEDURE("transformations", "procedure"),
    SCHEME("transformations", "transformation")
    ;

    private String directory;
    private String typeOfContent;

    WebContent(String directory, String typeOfContent) {
	this.directory = directory;
	this.typeOfContent = typeOfContent;
    }

    @Override
    public String getDirectory() {
	return directory;
    }

    @Override
    public String getTypeOfContent() {
	return typeOfContent;
    }



}
