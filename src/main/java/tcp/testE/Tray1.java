package tcp.testE;

import serial.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 向PLC发送托盘号后，通知条形码发送，并接受绑定后的数据
 * 与PLC客户端相对应
 */
public class Tray1 extends Thread{

    @Override
    public void run() {
        ServerSocket serverSocket;
        int i=0;
        try {
            serverSocket = new ServerSocket(88);
            System.out.println("1托盘服务端已启动...");
            Socket accept = serverSocket.accept();
            OutputStream outputStream = accept.getOutputStream();
            PrintWriter printStream = new PrintWriter(outputStream);
            InputStream inputStream = accept.getInputStream();
            String trayId = null;
            String bindId = null;
            //发送条形码，并监听PLC服务端发送的绑定数据
            while(true){
                byte [] bytes = new byte[1024];
                trayId = StringUtils.getRandom1(2);
                printStream.write("0"+trayId);
                printStream.flush();
                i++;
                System.out.println("第["+i+"]次1发送：托盘号：0"+trayId);
                if (inputStream.available()>0){
                    inputStream.read(bytes);
                    bindId=new String(bytes,0,bytes.length);
                    System.out.println("第["+(i-1)+"]次1收到：绑定的数据为："+bindId.trim());
                }
                Thread.sleep(3000);
                System.out.println();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Tray1 tray1 = new Tray1();
        tray1.start();
    }
}
