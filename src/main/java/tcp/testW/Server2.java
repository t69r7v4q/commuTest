package tcp.testW;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端循环接受请求，创建并启动服务线程，输出服务线程信息
 */
public class Server2 {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = null;
            //统计请求数
            int count=0;
            System.out.println("服务器启动，等待客户端连接...");
            while(true){
                socket=serverSocket.accept();
                InetAddress inetAddress = socket.getInetAddress();
                count++;
                System.out.println("客户端数量："+count+" 客户端IP："+inetAddress.getHostAddress());
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                //控制栏打印文字在后，会等待接受请求后输出？
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
