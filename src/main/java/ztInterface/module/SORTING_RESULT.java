package ztInterface.module;

import lombok.AllArgsConstructor;

/**
 * 分拣结果推送
 */
@AllArgsConstructor
public class SORTING_RESULT {
    public String trayCode;
    public String billCode;
    public String pipeline;
    public Long sortTime;
    public Integer turnNumber;
    public String sortPortCode;
    public String sortSource;
    public String packageCode;
    public String scanUser;
    public String endsortcamCode;
    public String endcamareCode;
    public String supplyCode;
    public String sortCode;
    public int layers;
    public int repeat;

    @Override
    public String toString() {
        return "{" +
                "trayCode='" + trayCode + '\'' +
                ", billCode='" + billCode + '\'' +
                ", pipeline='" + pipeline + '\'' +
                ", sortTime=" + sortTime +
                ", turnNumber=" + turnNumber +
                ", sortPortCode='" + sortPortCode + '\'' +
                ", sortSource='" + sortSource + '\'' +
                ", packageCode='" + packageCode + '\'' +
                ", scanUser='" + scanUser + '\'' +
                ", endsortcamCode='" + endsortcamCode + '\'' +
                ", endcamareCode='" + endcamareCode + '\'' +
                ", supplyCode='" + supplyCode + '\'' +
                ", sortCode='" + sortCode + '\'' +
                ", layers=" + layers +
                ", repeat=" + repeat +
                '}';
    }
}
