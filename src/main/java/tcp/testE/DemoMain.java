package tcp.testE;

public class DemoMain {

    public static void main(String[] args) throws InterruptedException {
        Tray1 tray1 = new Tray1();
        PLCClient4 plcClient4 = new PLCClient4();
        PLCServer2 plcServer2 = new PLCServer2();
        BarCode3 barCode3 = new BarCode3();

        //启动托盘服务端-下位机
        tray1.start();

        //启动条码服务端-上位机
        plcServer2.start();
        Thread.sleep(3000);

        //启动相机客户端-下位机
        barCode3.start();

        //启动托盘客户端-上位机
        plcClient4.start();
    }
}
