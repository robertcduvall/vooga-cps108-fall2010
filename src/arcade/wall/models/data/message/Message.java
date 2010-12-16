package arcade.wall.models.data.message;

import arcade.wall.models.data.Datum;

/**
 * A Message represents a VOOGA Message - it is from a particular user, sent to a particular user, and
 * it contains textual content.
 * @author John Kline
 *
 */
public class Message extends Datum {

	private String mySender;
	private String myReceiver;
	private String myContent;
	
	public Message(String id, String sender, String receiver, String content) {
		super(id);
		this.mySender = sender;
		this.myReceiver = receiver;
		this.myContent = content;
	}
	
	public String getSender() {
		return mySender;
	}
	
	public String getReceiver() {
		return myReceiver;
	}
	
	public String getContent() {
		return myContent;
	}

}
