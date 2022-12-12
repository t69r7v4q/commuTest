package tcp.testE;

import serial.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 发送条形码给下位机，与PLC服务端对应
 */
public class BarCode3 extends Thread {

    @Override
    public void run() {
        String trayId = null;
        int i=0;
        try {
            Socket socket=new Socket("localhost",89);
            System.out.println("3条形码客户端已启动...");
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printStream = new PrintWriter(outputStream);
            while(true){
                trayId = StringUtils.getRandom1(2);
                printStream.write("9"+trayId);
                printStream.flush();
                i++;
                System.out.println("第["+i+"]次3发送：条形码号：9"+trayId);
                Thread.sleep(3000);
                System.out.println();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BarCode3 barCode3 = new BarCode3();
        barCode3.start();
    }
}