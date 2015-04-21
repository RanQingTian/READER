//
//import java.util.*;
//import java.util.Map.Entry;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//
//public class JsonTest {
//	public static void main(String[] args) {
//	       Information inform=new Information();
//	       inform.SetContent("test");
//	       Information inform2=new Information();
//	       inform2.SetContent("Second");
//	       inform2.setImportance("high");
//	       inform2.setType("String");
//	       ArrayList<Information> list_inform= new ArrayList<Information>();
//	       list_inform.add(inform);
//	       list_inform.add(inform2);
//	       JSONArray json = new JSONArray();
//	       JSONObject jsob;
//	       for(Information bean:list_inform) {
//	    	   jsob=new JSONObject();
//	    	   jsob.put("importance",bean.importance);
//	    	   jsob.put("type",bean.type);
//	    	   jsob.put("reply",bean.reply);
//	    	   jsob.put("content",bean.content);
//	    	   json.add(jsob);
////	    	   System.out.println(jsob);
//	       }
//	       for(int i=0;i<json.size();i++){
//	    	   jsob=(JSONObject)json.get(i);
//	    	   Map<String,Integer> map=new HashMap<String,Integer>();
////	    	   Entry entry=new Entry();
//	    	   map.put("aaa", 1);
////	    	   for(Entry<String, Integer> entry:map.entrySet())
////	    		   System.out.println(entry.getKey()+"\t"+entry.getValue());
//	    	   
////	    	   Content content= jsob.entrySet();
//	    	   Iterator<Entry> iterator=jsob.entrySet().iterator();
//	    	   System.out.println("----------------content");
//	    	   for(;iterator.hasNext();) {
//	    		   Entry temp=iterator.next();
//	    		   System.out.println("!!"+temp.getKey()+" is "+temp.getValue());
//	    	   }
//	    	   
//	       }
//	       /**
//	        * 使用集合类
//	        */
////	       List<String> list = new ArrayList<String>();
////	       list.add("Jack");
////	       list.add("Rose");
////	       json = JSONArray.fromObject(list);
//	     
////	       System.err.println(json);
//	       
//	       System.out.println("------------------Class");
//	       Information inform_getted;
//	       ArrayList<Information> list_getted=new ArrayList<>();
//	       for(int i=0;i<json.size();i++){
////	    	   jsob=(JSONObject)json.get(i);
//	    	   jsob=(JSONObject)json.get(i);
//	    	   inform_getted=new Information();
//	    	   inform_getted.setImportance(jsob.getString("importance"));
//	    	   inform_getted.setType(jsob.getString("type"));
//	    	   inform_getted.SetContent(jsob.getString("content"));
//	    	   list_getted.add(inform_getted);
//	       }
//	       for(Information temp:list_getted){
//	    	   System.out.println("Here:"+temp.getContent());
//	       }
//	       /**
//	        * 使用 set 集
////	        */
////	       Set<Object> set = new HashSet<Object>();
////	       set.add("Hello");
////	       set.add(true);
////	       set.add(99);
////	       json = JSONArray.fromObject(set);
////	       System.err.println(json);
//	   }
//}
