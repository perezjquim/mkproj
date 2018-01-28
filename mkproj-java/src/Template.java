package mkproj;

/* PROJECT TEMPLATE */
public class Template
{
	// Ex.: "Node.js"
	private String label;
	
	// Ex.: "nodejs" (as in "mkmake nodejs")
	private String cmdArgument;
	
	// String containing the source code to be put into the project
	private String srcCode;
	
	// Ex.: ".c"
	private String srcExtension;
	
	public Template(String label, String cmdArgument, String srcExtension, String srcCode)
	{
		this.label = label;	
		this.cmdArgument = cmdArgument;
		this.srcExtension = srcExtension;
		this.srcCode = srcCode;
	}
	
	// Getters
	public String getLabel() { return label; }
	public String getCmdArgument() { return cmdArgument; }
	public String getSrcCode() { return srcCode; }
	public String getSrcExtension() { return srcExtension; }
	
	// Adapts the source code, according to the project name
	public void adaptCode(String projName)
	{
		srcCode = srcCode.replace("projName",projName);
	}
}
