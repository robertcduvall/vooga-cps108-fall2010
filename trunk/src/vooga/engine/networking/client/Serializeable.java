package vooga.engine.networking.client;

public abstract class Serializeable {
	public static Serializeable deserialize(String data){
		return null;
	}
	public static String getIdentifier(){
		return null;
	}
	public abstract String serialize();
}
