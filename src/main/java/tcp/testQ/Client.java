package tcp.testQ;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TCP连接测试 客户端
 */
public class Client {

    public static void main(String[] args) {
        //连接数量
        final int length = 5;
        String host = "localhost";
        //连接端口
        int port = 8888;
        Socket[] sockets = new Socket[length];
        for(int i=0;i<length;i++){
            try {
                sockets[i]=new Socket(host,port+i);
                //Java格式化控制台输出?
                System.out.println("请求地址:端口："+sockets[i].getLocalAddress()+":"+sockets[i].getLocalPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            //延迟2秒关闭连接
            Thread.sleep(2000);
            for(int i=0;i<length;i++)
                sockets[i].close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
