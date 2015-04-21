import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.collections.ArrayStack;
import org.apache.commons.collections.iterators.EntrySetMapIterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/*
	该类用于将json格式的文件转换为Inform类,注意路径的格式,应如"C:\\Users\\steven\\Desktop\\json\\test.txt"
	(1)创建数组链表存储要保存的Inform对象，语法如下
	ArratList<Inform> list_name;
	(2)实例化该类，准备读取文件
	JsonTransformer js_name=new JsonTransformer();
	(3)获取文件内容
	list_name=js_name.transformToBean(getJsonArray(getFile("文件路径")));
 */
public class JsonTransformer {
	static Gson gson = new Gson();
	
	public static void main(String args[]) throws IOException{
		JsonTransformer js_trans=new JsonTransformer();
		ArrayList<Information> informs;
		informs=js_trans.transformToBean(getJsonArray(getFile("C:\\Users\\steven\\Desktop\\json\\test.txt")));
		for(Information bean:informs){
			System.out.println(bean.getContent());
		}
		
	}
	static JSONArray getJsonArray(String content){
		JSONArray js_array=new JSONArray().fromObject(content);
		System.out.println(js_array);
		return  js_array;
	}
	
	ArrayList<Information> transformToBean(JSONArray js_array){
		JSONObject js_object=null;
		ArrayList<Information> informs=new ArrayList<Information>();
		Information inform=null;
		for(int i=0;i<js_array.size();i++){
			js_object=(JSONObject) js_array.get(i);
			inform=gson.fromJson(js_object.toString(),Information.class);
			informs.add(inform); 
			}
		return informs;
		}
	
	static String getFile(String path) throws IOException{
		File file=new File(path);
	    FileReader fw = new FileReader(file);
	    BufferedReader bu=new BufferedReader(fw);
	    String temp=null,content="";
	    while((temp=bu.readLine())!=null){
	    	content+=temp;
	    }
	    bu.close();
	    fw.close();
	    return content;
	}
}
