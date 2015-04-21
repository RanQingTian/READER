package DataInteract;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

//使用方法参照main方法中的调用，如new server(开启的端口,"用于保存所接收文件的路径").start()
//注意，用于保存所接收文件的路径需要像这样 ： String path = "C:\\Users\\steven\\Desktop\\json\\jk\\"
public class Server {
	final static String path = "C:\\Users\\steven\\Desktop\\json\\jk\\";
    private int listenPort;
    private String savePath; 
	   
//    public static void main(String[] args) throws Exception {  
//        int port = 8000;  
//        new Server(port, path).start();    
//    }  
   
 
   
    /** 
     * 构造方法 
     * 
     * @param listenPort 侦听端口 
     * @param savePath   接收的文件要保存的路径 
     * 
     * @throws IOException 如果创建保存路径失败 
     */  
    public Server(int listenPort, String savePath) throws IOException {  
        this.listenPort = listenPort;  
        this.savePath = savePath;  
   
        File file = new File(savePath);
        if (!file.exists() && !file.mkdirs()) {  
            throw new IOException("无法创建文件夹 " + savePath);  
        }  
    }  
   
    // 开始侦听   
    public void start() {  
        new ListenThread().start();  
    }  
   
    // 网上抄来的，将字节转成 int。b 长度不得小于 4，且只会取前 4 位。   
    public static int b2i(byte[] b) {  
        int value = 0;  
        for (int i = 0; i < 4; i++) {  
            int shift = (4 - 1 - i) * 8;  
            value += (b[i] & 0x000000FF) << shift;  
        }  
        return value;  
    }  
   
   
    /** 
     * 侦听线程 
     */  
    private class ListenThread extends Thread {  
   
        @Override  
        public void run() {  
            try {  
                ServerSocket server = new ServerSocket(listenPort);  
   
                // 开始循环   
                while (true) {  
                    Socket socket = server.accept();  
                    new HandleThread(socket).start();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
   
    /** 
     * 读取流并保存文件的线程 
     */  
    private class HandleThread extends Thread {  
   
        private Socket socket;  
        String filename ;
   
        private HandleThread(Socket socket) {  
            this.socket = socket;  
        }  
   
        @Override  
        public void run() {  
            try {  
                DataInputStream is = new DataInputStream(socket.getInputStream());  
                readAndSave(is);  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    socket.close();  
                } catch (IOException e) {  
                    // nothing to do   
                }  
            }  
        }  
   
        // 从流中读取内容并保存   
        private void readAndSave(DataInputStream is) throws IOException {  
            filename = getFileName(is);			
            int file_len = readInteger(is);  
            System.out.println("接收文件：" + filename + "，长度：" + file_len);  
   
            FileWriter writer=new FileWriter(getFileOS(savePath+filename),false);
    		BufferedWriter bu=new BufferedWriter(writer);
            byte[] buffer = new byte[is.available()];  
            int count = 0;  
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String get;
            while((get=br.readLine())!=null) {  
                // 这里没有考虑 n = -1 的情况   
                bu.write(get);  
                 
            }
            bu.close();
   
            System.out.println("文件保存成功（" + file_len + "字节）。");  
        }  
  
   
        // 读取文件名   
        private String getFileName(DataInputStream is) throws IOException {  
            int name_len = readInteger(is);  
            byte[] result = new byte[name_len];  
            is.read(result);  
            return new String(result);  
        }  
   
        // 读取一个数字   
        private int readInteger(DataInputStream is) throws IOException {  
            byte[] bytes = new byte[4];  
            is.read(bytes);  
            return b2i(bytes);  
        }  
   
        // 创建文件并返回输出流   
        private File getFileOS(String path) throws IOException {
			File file = new File(path);
			if (!file.exists()) {  
                file.createNewFile();  
            }  
			return file;
        }
		
		//private void 

	}
} 



