package mkproj;

public class Template
{
	private String name;
	private String label;
	private String srcCode;
	private String srcExtension;
	
	public Template(String name, String label, String srcExtension, String srcCode)
	{
		this.name = name;
		this.label = label;
		this.srcExtension = srcExtension;
		this.srcCode = srcCode;
	}
	
	public String getName() { return name; }
	public String getLabel() { return label; }
	public String getSrcCode() { return srcCode; }
	public String getSrcExtension() { return srcExtension; }
	
	public void adaptCode(String projName)
	{
		srcCode = srcCode.replace("projName",projName);
	}
}
