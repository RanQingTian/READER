import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;


//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

//以下为该类的调用方法,注意路径的格式,应如"C:\\Users\\steven\\Desktop\\json\\test.txt"
/*
	(1)将要转换的Inform对象放到数组链表中，语法为
	ArrayList<Inform> list_name=new ArrayList<Inform>();
	list_name.add(Inform对象)
	(2)调用转换器
	InformTransformer transformer_name=new InformTransformer(list_name);
	(3)获取转换结果并保存为文件
	transformer_name.writeFile("要保存到的文件路径",transformer_name.js_array.toString());	
*/
public class InformTransformer {
	ArrayList<String> js_array=new ArrayList<String>();
	Gson gson=new Gson();
	
	public static void main(String args[]) throws IOException{
		ArrayList<Information> list=new ArrayList<Information>();
		ArrayList<Integer> rece=new ArrayList<Integer>();
		Integer i=20;
		rece.add(i);
		Information test=new Information(10, rece, "Title", "Content");
		list.add(test);
		InformTransformer trans=new InformTransformer(list);
		trans.writeFile("C:\\Users\\steven\\Desktop\\my.txt",trans.js_array.toString());
	}
	
	public InformTransformer(ArrayList<Information> informs) {
		for(Information bean:informs){
			js_array.add(this.toJsObject(bean));
		}
	}

	String toJsObject(Information inform){
		return gson.toJson(inform);
	}
	ArrayList<String> getJsonArray(){
		return js_array;
	}
	
	public void writeFile(String filePath, String sets) throws IOException {
		File file=new File(filePath);
	    FileWriter fw = new FileWriter(file);
	    BufferedWriter bu=new BufferedWriter(fw);
	    bu.write(sets);
	    bu.close();
	    fw.close();
	   }
}
