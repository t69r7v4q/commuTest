package ztInterface.module;

import lombok.AllArgsConstructor;

/**
 *补码结果推送
 */
@AllArgsConstructor
public class COMPLEMENT_INFO {
    //运单编号
    public String billCode;
    //分拣线编码
    public String pipeline;
    //快速补码
    public String sortCode;
    //分拣来源
    public String sortSource;

    @Override
    public String toString() {
        return "{" +
                "billCode='" + billCode + '\'' +
                ", pipeline='" + pipeline + '\'' +
                ", sortCode='" + sortCode + '\'' +
                ", sortSource='" + sortSource + '\'' +
                '}';
    }
}
