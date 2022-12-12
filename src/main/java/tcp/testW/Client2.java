package tcp.testW;

import java.io.*;
import java.net.Socket;

/**
 * 客户端循环延迟2秒创建请求连接，获取输入输出流进行操作
 */
public class Client2 extends Thread {

    @Override
    public void run() {
        Socket socket = null;
        OutputStream outputStream = null;
        PrintWriter printStream = null;
        InputStream inputStream = null;
        String info=null;

        while (true){
            try {
                socket = new Socket("localhost", 8888);
                outputStream = socket.getOutputStream();
                printStream = new PrintWriter(outputStream);
                printStream.write("T:001*E:000");
                System.out.println("客户端发送：T:001*E:000");
                printStream.flush();
                //客户端输出的数据都将被发送
                socket.shutdownOutput();
                inputStream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while((info=bufferedReader.readLine())!=null){
                    System.out.println("客户端收到："+info);
                }
                System.out.println();
                bufferedReader.close();
                inputStream.close();
                printStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Client2 client2 = new Client2();
        client2.start();
    }
}
