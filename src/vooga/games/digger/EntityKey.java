package vooga.games.digger;

public class EntityKey {
	
	private Class callingClass;
	private Class requestedClass;
	private String modName;
	
	public EntityKey(Class callingClass, Class requestedClass, String modName){
		this.callingClass = callingClass;
		this.requestedClass = requestedClass;
		this.modName = modName;
	}
	
	public Class getCallingClass(){
		return callingClass;
	}
	
	public Class getRequestedClass(){
		return requestedClass;
	}
	
	public String getModName(){
		return modName;
	}
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof EntityKey)){
			return false;
		} else{
			EntityKey otherKey = (EntityKey) other;
			return getCallingClass().equals(otherKey.getCallingClass()) && getRequestedClass().equals(otherKey.getRequestedClass()) && getModName().equals(otherKey.getModName());
		}
	}
	
	@Override
	public int hashCode(){
		return callingClass.hashCode() * requestedClass.hashCode() * modName.hashCode();
	}

}
