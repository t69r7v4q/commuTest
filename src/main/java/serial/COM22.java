package serial;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 发送数据，并接受回传数据进行CRC8校验
 */
public class COM22 extends Thread implements SerialPortEventListener {

    static CommPortIdentifier portId; // 通用的通信端口类
    static Enumeration<?> portList; // 有效连接上的端口的枚举
    InputStream inputStream; // 从串口来的输入流
    static OutputStream outputStream;// 向串口输出的流
    static SerialPort serialPort; // 串口的引用

    //监听串口方法
    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        //当串口事件为(有数据可用)时
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            byte[] readBuffer = null;
            int num = -1;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            try {
                //当串口接收到数据时
                while (inputStream.available() > 0) {
                    readBuffer = new byte[inputStream.available()];
                    num = inputStream.read(readBuffer);
                    if (num != -1) {
                        String hexString = StringUtils.bytesToHex(readBuffer);
                        //例：57(4232113887)(90)47 57开头 47结尾 90-CRC8 4232113887-包内容
                        System.out.println("hexString："+hexString);
                        //截取数据字符串：4232113887
                        String substring = hexString.substring(2, 12);
                        //计算截取数据的CRC8
                        int crcRes1 = Crc8.calcCrc8(Crc8.hexStrToBinaryStr(substring));
                        String crcHex1 = Integer.toHexString((crcRes1 & 0x000000ff) | 0xffffff00) .substring(6).toUpperCase();
                        System.out.println("截取的数据："+substring+"---计算crcHex：："+ crcHex1.toUpperCase());
                        //对比计算的CRC8-90，验证通过输出
                        if(hexString.substring(12,14).toUpperCase().equals(crcHex1)){
                            System.out.println("数据："+substring+"  CRC8验证通过");
                            String responseString = substring+"12";
                            int crcRes2 = Crc8.calcCrc8(Crc8.hexStrToBinaryStr(substring));
                            String crcHex2 = Integer.toHexString((crcRes2 & 0x000000ff) | 0xffffff00) .substring(6).toUpperCase();
                            System.out.println();
                        }else {
                            //CRC8未通过时
                            System.out.println("数据："+hexString+"   CRC8验证未通过");
                            System.out.println();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //串口初始化
    public int startComPort(){
        //获取有效连接上的端口枚举
        portList = CommPortIdentifier.getPortIdentifiers();
        while(portList.hasMoreElements()){
            //获取通信端口类
            portId = (CommPortIdentifier) portList.nextElement();
            System.out.println("设备类型：--->" + portId.getPortType());
            System.out.println("设备名称：---->" + portId.getName());
            System.out.println();
            //如果通信端口类的类型为串口
            if(portId.getPortType()==CommPortIdentifier.PORT_SERIAL){
                //验证串口名为：COM2 此处COM1,COM2为一对
                if(portId.getName().equals("COM2")){
                    try {
                        // 打开串口名字为COM2(名字任意),延迟为2毫秒，，获取输入输出流
                        serialPort= (SerialPort) portId.open("COM2",2000);
                        inputStream=serialPort.getInputStream();
                        outputStream=serialPort.getOutputStream();
                        // 设置监听器生效，即：当有数据时通知
                        serialPort.notifyOnDataAvailable(true);
                        //添加串口事件监听器
                        serialPort.addEventListener(this);
                        //添加串口参数，比特率、数据位、停止位、奇偶校验位
                        serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                    } catch (PortInUseException | IOException | TooManyListenersException | UnsupportedCommOperationException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    //保持线程活跃，持续监听
    @Override
    public void run() {
        String str;
        //持续向对应端口发送数据
        while (true){
            String ramStr= StringUtils.getRandom1(8);
            //计算CRC8
            int crcRes3 = Crc8.calcCrc8(Crc8.hexStrToBinaryStr(ramStr));
            String crcHex3 = Integer.toHexString((crcRes3 & 0x000000ff) | 0xffffff00) .substring(6).toUpperCase();
            //拼接数据字符串 例："57(42321138)(22)47";57开头 47结尾 4232113-包内容 22-CRC8
            str=new String("57"+ramStr+crcHex3+"47");
            try {
                System.out.println("发送的数据："+str);
                System.out.println();
                byte [] hexByte = Crc8.hexStrToBinaryStr(str);
                outputStream.write(hexByte,0,hexByte.length);
                Thread.sleep(2000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        COM22 com2 = new COM22();
        int i=com2.startComPort();
        if(i==1){
            com2.start();
        }
    }
}
