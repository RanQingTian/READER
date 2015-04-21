import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class Information {
	private int message_id;
	private int publisher;
	private ArrayList<Integer> receiver;
	@SuppressWarnings("unused")
	private String date_time;
	private String title;
	private String content;
	private Map<Integer, Integer> reply;
	
	
	Information(int publisher,ArrayList<Integer> receiver,String title,String content){
	this.publisher=publisher;
	this.title=title;
	this.content=content;
//	for(Integer i:receiver) 
//		if(i!=null) this.receiver.add(i);
	}
	
	final public boolean setMessage_id(int id){
		message_id=id;
		if(message_id==id) return true;
		else return false;
	} 
	
	final public boolean setPublisher(int publisher){
		this.publisher=publisher;
		if(this.publisher==publisher) return true;
		else return false;
	}
	
	final public void addReceiver(ArrayList<Integer> receiver){
		for(Integer i:receiver)
			if(this.receiver.contains(i)==false) this.receiver.add(i);
	}
	final public void setTitle(String title){
		this.title=title;
	}
	final public String getTitle(){
		return title;
	}
	
	final public void setContent(String content){
		this.content=content;
	}
	final public String getContent(){
		return content;
	}
	
	final public Map<Integer, Integer> getReply(){
		return reply;
	}
	
	final public void setDatetime(){
		date_time=getCurrentDate();
	}
	
	static final private String getCurrentDate(){
		Date date=new Date();
		DateFormat date_format=DateFormat.getDateTimeInstance();
		return date_format.format(date);
	}
	
}
