package tcp.testE;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * PLC服务端已启动-对应条形码客户端，接收条形码存储到PLC共享数组中
 * 与条形码客户端相对应
 */
public class PLCServer2 extends Thread{

    public static ArrayList<String> arrayList = new ArrayList<>();

    @Override
    public void run() {
        int i=0;
        String barCode;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(89);
            System.out.println("2PLC服务端已启动...");
            Socket accept = serverSocket.accept();
            InputStream inputStream2 = accept.getInputStream();
            byte [] bytes = new byte[1024];
            //监听条形码客户端的输出，存入PLC共享数组，PLC客户端服务端需在一个进程内启动
            while(true){
                int size2= inputStream2.available();
                if(size2>0){
                    i++;
                    inputStream2.read(bytes);
                    barCode = new String(bytes,0,size2);
                    System.out.println("第["+i+"]次2收到：条形码ID："+barCode);
                    arrayList.add(barCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        PLCServer plcServer = new PLCServer();
//        plcServer.start();
//    }
}
