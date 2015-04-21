package DataInteract;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
//使用方法参照main方法中的调用，如new Client().sendFile("目标IP",目标端口,"即将发送文件的路径")
//注意路径的格式,应如"C:\\Users\\steven\\Desktop\\json\\test.txt"
public class Client {
	   
//		public static void main(String args[]) throws IOException{
//			new Client().sendFile("127.0.0.1", 8000, "C:\\Users\\steven\\Desktop\\json\\test.txt");
//		}


	    /** 
	     * 发送文件。文件大小不能大于 {@link Integer#MAX_VALUE} 
	     * 
	     * @param hostname 接收端主机名或 IP 地址 
	     * @param port     接收端端口号 
	     * @param filepath 文件路径 
	     * 
	     * @throws IOException 如果读取文件或发送失败 
	     */  
		
		 public static byte[] i2b(int i) {  
		        return new byte[]{  
		                (byte) ((i >> 24) & 0xFF),  
		                (byte) ((i >> 16) & 0xFF),  
		                (byte) ((i >> 8) & 0xFF),  
		                (byte) (i & 0xFF)  
		        };  
		 }
		 
	    public void sendFile(String hostname, int port, String filepath) throws IOException {  
	        File file = new File(filepath);  
	        System.out.println(file.exists());
	        FileInputStream is = new FileInputStream(file);  
	   
	        Socket socket = new Socket(hostname, port);  
	        DataOutputStream os = new DataOutputStream(socket.getOutputStream());  
	   
	        try {  
	            int length = (int) file.length();  
	            System.out.println("发送文件：" + file.getName() + "，长度：" + length);  
	   
	            // 发送文件名和文件内容   
	            writeFileName(file, os);  
	            writeFileContent(is, os, length);  
	        } finally {  
	            os.close();  
	            is.close();  
	        }  
	    }  
	   
	    // 输出文件内容   
	    private void writeFileContent(InputStream is, DataOutputStream os, int length) throws IOException {  
	        // 输出文件长度   
	        os.write(i2b(length));  
	   
	        // 输出文件内容   
	        byte[] buffer = new byte[is.available()];  
	        int size;  
	        while ((size = is.read(buffer,0,buffer.length)) != -1) {  
	            os.write(buffer);  
	        }  
	    }  
	   
	    // 输出文件名   
	    private void writeFileName(File file, DataOutputStream os) throws IOException {  
	        byte[] fn_bytes = file.getName().getBytes();  
	   
	        os.write(i2b(fn_bytes.length));         // 输出文件名长度   
	        os.write(fn_bytes);    // 输出文件名   
	    }  
}
