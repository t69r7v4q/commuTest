package tcp.testE;

import java.io.*;
import java.net.Socket;

/**
 * 接收托盘号，以及查询PLC共享数组中的条形码进行绑定
 * 发送给托盘服务端，与托盘服务端对应
 */
public class PLCClient4 extends Thread{

    @Override
    public void run() {
        int i=0;
        try {
            Socket socket = new Socket("localhost", 88);
            System.out.println("4PLC客户端已启动...");
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter=new PrintWriter(outputStream);
            byte [] bytes = new byte[1024];
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //监听托盘号，收到后与条形码绑定，回传给托盘服务端
            while (true){
                String trayInfo = null;
                int size  = inputStream.available();
                if (size > 0) {
                    i++;
                    inputStream.read(bytes);
                    trayInfo = new String(bytes,0,size);
                    System.out.println("第["+i+"]次4收到：托盘ID："+trayInfo);
                }
                //当PLC共享数组中没条形码时，返回*C999
                if(PLCServer2.arrayList.size()==0){
                    printWriter.write("T:"+trayInfo+"*C:999");
                    System.out.println("第["+i+"]次4发送：绑定的数据为："+"T:"+trayInfo+"*C:999");
                }else {
                    //有条形码时，绑定数据返回给托盘服务端
                    printWriter.write("T:"+trayInfo+"*C"+ PLCServer2.arrayList.get(0));
                    printWriter.flush();
                    System.out.println("第["+i+"]次4发送：绑定的数据为："+"T:"+trayInfo+"*C"+ PLCServer2.arrayList.get(0));
                    PLCServer2.arrayList.remove(0);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
