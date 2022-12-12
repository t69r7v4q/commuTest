package tcp.testQ;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP连接测试 服务端
 */
public class Server {

    private final ServerSocket serverSocket;

    public Server() throws IOException {
        //连接端口
        int port = 8888;
        //连接端口队列，backlog设置队列长度
        serverSocket = new ServerSocket(port,3);
        System.out.println("服务端启动。。。");
    }

    /**
     * 接受客户端请求并输出
     */
    public void service(){
        while(true){
            Socket socket = null;
            try {
                //服务端开启8888端口，并监听着，时刻等待着客户端的连接请求
                socket = serverSocket.accept();
                System.out.println("连接地址:端口："+socket.getInetAddress()+":"+socket.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(socket!=null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        try {
            //睡眠5秒，5秒内当客户端请求大于连接队列长度时，客户端连接失败
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //接受客户端请求并输出
        server.service();
    }
}
