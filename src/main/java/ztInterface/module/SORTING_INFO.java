package ztInterface.module;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分拣信息请求
 */
@AllArgsConstructor
@NoArgsConstructor
public class SORTING_INFO {
    public String billCode;
    public String pipeline;
    public Integer turnNumber;
    public Long requestTime;
    public float weight;
    public String incomeClerk;
    public String scanMan;
    public String trayCode;
    public String infocamareCode;
    public String supplyCode;
    public int layers;
    public List<String> allBills;
    public int repeat;

    @Override
    public String toString() {
        return "{" +
                "billCode:'" + billCode + '\'' +
                ", pipeline:'" + pipeline + '\'' +
                ", turnNumber:" + turnNumber +
                ", requestTime:" + requestTime +
                ", weight:" + weight +
                ", incomeClerk:'" + incomeClerk + '\'' +
                ", scanMan:'" + scanMan + '\'' +
                ", trayCode:'" + trayCode + '\'' +
                ", infocamareCode:'" + infocamareCode + '\'' +
                ", supplyCode:'" + supplyCode + '\'' +
                ", layers:" + layers +
                ", allBills:" + allBills +
                ", repeat:" + repeat +
                '}';
    }
}
