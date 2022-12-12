package ztInterface.module;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 流水线开停状态
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class PIPELINE_STATUS {
    //分拣线编码
    public String pipeline;
    //状态切换时间
    public Long switchTime;
    //流水线状态
    public String status;
    //分拣模式
    public String sortMode;

    @Override
    public String toString() {
        return "{" +
                "pipeline:'" + pipeline + '\'' +
                ", switchTime:" + switchTime +
                ", status:'" + status + '\'' +
                ", sortMode:'" + sortMode + '\'' +
                '}';
    }
}
