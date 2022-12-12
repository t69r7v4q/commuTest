package serial;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 监听数据添加CRC8校验回传到对应的串口
 */
public class COM11 extends Thread implements SerialPortEventListener {

    static CommPortIdentifier portId; // 通用的通信端口类
    static Enumeration<?> portList; // 有效连接上的端口枚举
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
                        //例："57(42321138)(22)47";57开头 47结尾 4232113-包内容 22-CRC8
                        System.out.println("hexString："+hexString);
                        //截取数据字符串 例：42321138
                        String substring = hexString.substring(2, 10);
                        //计算截取数据的CRC8
                        int crcRes1 = Crc8.calcCrc8(Crc8.hexStrToBinaryStr(substring));
                        String crcHex1 = Integer.toHexString((crcRes1 & 0x000000ff) | 0xffffff00).substring(6).toUpperCase();
                        System.out.println("截取的数据："+substring+"---计算crcHex：："+ crcHex1);
                        //截取接受的数据-5742321138(22)47，对比计算的CRC8-22 ，成功将数据添加格口并回传
                        if(hexString.substring(10,12).toUpperCase().equals(crcHex1)){
                            String random1 = StringUtils.getRandom1(2);
                            System.out.println("数据："+substring+"---CRC8验证通过，添加的格口："+random1+"---开始回传");
                            String responseString = substring+ random1;
                            //数据添加格口，重写计算CRC8 57(4232113)(88)(79)047 88-格口 79-新的CRC8 4232113-原数据
                            int crcRes2 = Crc8.calcCrc8(Crc8.hexStrToBinaryStr(responseString));
                            String crcHex2 = Integer.toHexString((crcRes2 & 0x000000ff) | 0xffffff00) .substring(6).toUpperCase();
                            String str = "57" + responseString + crcHex2 + "47";
                            System.out.println("回传数据："+str);
                            System.out.println();
                            byte [] hexByte = Crc8.hexStrToBinaryStr(str);
                            //向对应串口发送数据
                            outputStream.write(hexByte,0,hexByte.length);
                        }else {
                            //CRC8未通过时
                            String str = "数据：" + hexString + "---CRC8验证未通过";
                            System.out.println();
                            byte [] hexByte = Crc8.hexStrToBinaryStr(str);
                            outputStream.write(hexByte,0,hexByte.length);
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
            portId= (CommPortIdentifier) portList.nextElement();
            System.out.println("设备类型：--->" + portId.getPortType());
            System.out.println("设备名称：---->" + portId.getName());
            System.out.println();
            //如果通信端口类的类型为串口
            if(portId.getPortType()==CommPortIdentifier.PORT_SERIAL) {
                //验证串口名为：COM1 此处COM1,COM2为一对
                if (Objects.equals(portId.getName(), "COM1")) {
                    try {
                        // 打开串口名字为COM1(名字任意),延迟为2毫秒，获取输入输出流
                        serialPort = (SerialPort) portId.open("COM1", 2000);
                        inputStream = serialPort.getInputStream();
                        outputStream = serialPort.getOutputStream();
                        //添加串口事件监听器
                        serialPort.addEventListener(this);
                        // 设置监听器生效，即：当有数据时通知
                        serialPort.notifyOnDataAvailable(true);
                        //添加串口参数，比特率、数据位、停止位、奇偶校验位
                        serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
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

    @Override
    //保持线程活跃，持续监听
    public void run() {
        while(true){}
    }

    public static void main(String[] args) {
        COM11 com1 = new COM11();
        int i = com1.startComPort();
        if(i==1){
            com1.start();
        }
    }
}
//        https://docs.oracle.com/cd/E17802_01/products/products/javacomm/reference/api/javax/comm/SerialPortEvent.html  串口事件类型
//        static int	BI//        Break interrupt.
//        static int	CD//        Carrier detect.
//        static int	CTS//        Clear to send.
//        static int	DATA_AVAILABLE//        Data available at the serial port.
//        static int	DSR//        Data set ready.
//        int	eventType//        Deprecated. Replaced by getEventType method
//        static int	FE//        Framing error.
//        static int	OE//        Overrun error.
//        static int	OUTPUT_BUFFER_EMPTY//        Output buffer is empty.
//        static int	PE//        Parity error.
//        static int	RI//        Ring indicator.