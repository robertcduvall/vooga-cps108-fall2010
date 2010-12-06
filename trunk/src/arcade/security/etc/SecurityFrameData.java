package arcade.security.etc;
/**
 * 
 * @author Meng Li
 *
 */
public class SecurityFrameData {
	private String name;
	private String clazz;


	/**
	 * Creates a new Application object.
	 */
	public SecurityFrameData() {
	}

	/**
	 * Creates a new Application object.
	 *
	 * @param name
	 * @param clazz
	 * @param role
	 * @param defaultWindow
	 * @param startup
	 * @param actionClass
	 */      
	public SecurityFrameData(String name, String clazz) {
		this.name = name;
		this.clazz = clazz;

	}

	/**
	 * @return the clazz
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
