package arcade.security.etc;
/**
 * SecurityFrameData object is used to create JInternalFrame in the SecurityDesktop by the 
 * class name of the frame using reflection.
 * The String fields <code>name</code> and <code>clazz</code> are specified in the properties
 * files located in the resources/users/ file. 
 * @author Meng Li
 *
 */
public class SecurityFrameData {
	private String name;
	private String clazz;


	/**
	 * Creates a new SecurityData object.
	 */
	public SecurityFrameData() {
	}

	/**
	 * Creates a new SecurityData object.
	 *
	 * @param name the name or title of this security frame
	 * @param clazz the class name of this security frame

	 */      
	public SecurityFrameData(String name, String clazz) {
		this.name = name;
		this.clazz = clazz;

	}

	/**
	 * @return the clazz the class name as a string
	 */
	public String getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}




}
