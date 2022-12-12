package ztInterface;

/**
 * 各接口共同的参数部分
 */
@lombok.Data
public class ComData {
    //md5(data+key)
    public String data_digest;
    //消息类型
    public String msg_type;
    //公司英文缩写
    public String company_id;

    public ComData(String data_digest, String msg_type, String company_id) {
        this.data_digest = data_digest;
        this.msg_type = msg_type;
        this.company_id = company_id;
    }

    @Override
    public String toString() {
        return "{" +
                "data_digest:'" + data_digest + '\'' +
                ", msg_type:'" + msg_type + '\'' +
                ", company_id:'" + company_id + '\'' +
                '}';
    }
}
