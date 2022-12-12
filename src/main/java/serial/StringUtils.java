package serial;

public class StringUtils {

    /**
     *
     * @param bytes 字节流数组
     * @return 16进制字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        //转16进制+去除換行符0D0A
        for(int i = 0; i < bytes.length&&bytes[i]!='d'&&bytes[i]!='a'; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString().trim();
    }

    /**
     *
     * @param len 随机数长度
     * @return 随机数字符串
     */
    public static String getRandom1(int len) {
        int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1));
        return String.valueOf(rs);
    }
}
