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
	
	// Indicates if the language requires 'bin' and 'src' folders
	private boolean folderStructure;
	
	public Template(String label, String cmdArgument, String srcExtension, boolean folderStructure, String srcCode)
	{
		this.label = label;	
		this.cmdArgument = cmdArgument;
		this.srcExtension = srcExtension;
		this.folderStructure = folderStructure;
		this.srcCode = srcCode;
	}
	
	// Getters
	public String getLabel() { return label; }
	public String getCmdArgument() { return cmdArgument; }
	public String getSrcExtension() { return srcExtension; }
	public boolean hasFolderStructure() { return folderStructure; }
	public String getSrcCode() { return srcCode; }
	
	// Adapts the source code, according to the project name
	public void adaptCode(String projName)
	{
		srcCode = srcCode.replace("projName",projName);
	}
}
