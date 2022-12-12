package tcp.testW;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端线程，获取输入输出流进行操作
 */
public class ServerThread extends Thread{
    Socket socket=null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        OutputStream outputStream=null;
        PrintWriter printWriter=null;
        String info =null;
        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            while((info=bufferedReader.readLine())!=null)
                System.out.println("服务端收到："+info);
            socket.shutdownInput();
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.write("T:010*C:020000123456");
            System.out.println("客户端发送：T:010*C:020000123456");
            System.out.println();
//            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert printWriter != null;
                printWriter.close();
                bufferedReader.close();
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
