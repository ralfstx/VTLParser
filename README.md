# README #

### Overview ###

Java project that allows to parse valid VTL expressions and transforms such an expression into it's *BIRD VTL tree structure* which is a tree structure reflecting the SDMX information model for transformations (see SDMX Technical Specifications, Section 2 – Information model, 13.2.1 Class Diagram (https://sdmx.org/)).
The parser is created using ANTLR (see http://www.antlr.org/) and the official VTL grammar (see https://github.com/vtl-sdmx-task-force/sdmx-vtl/).

### How do I get set up? ###

1. Clone the repository
2. Configure the string *expression* in the class *VtlBirdParser* containing a valid VTL expression (e.g. expression = "result := coordinates [calc sqrt(x*x+y*y) as \"distance\"];")
3. Run the **main()** method of the class *VtlBirdParser*
4. The *Abstract syntax tree* and the *BIRD VTL tree structure* of the given expression will be illustrated on the console; the object *tree* represents the *BIRD VTL tree*  

### Remarks ###

1. Please note that due to ongoing developments of the official VTL grammar we needed to adjust this grammar in order to fit our purposes (all adjustments made are commented in the grammar (i.e. Vtl.g4 and VtlTokens.g4)). 
2. Please note that the *BIRD VTL tree structure* is the BIRD group's interpretation of the SDMX information model and not an official structure documented by the SDMX community.
3. Please also note that the current (directory) structure of this project is under construction and will be subject to changes.

### Contact ###

BIRD@ecb.europa.eu