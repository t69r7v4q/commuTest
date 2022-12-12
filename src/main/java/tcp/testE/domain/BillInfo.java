package tcp.testE.domain;

import java.io.Serializable;
import java.util.Date;

public class BillInfo implements Serializable {
	// id
	private Integer id;
	// 单号
	private String barCode;
	// 副单号
	private String barCode2;
	// 长
	private Integer length;
	// 宽
	private Integer width;
	// 高
	private Integer height;
	// 体积
	private Float volume;
	// 重量
	private Float weight;
	// 扫描时间
	private Date scanTime;
	// 分拣时间
	private Date sortingTime;
	// 创建时间戳
	private Long createTime;

	private String marked;
	// 格口号
	private String chute;
	private String transId;
	// 状态位
	private String status;

	private String extend1;
	private String extend2;
	private String extend3;
	private String extend4;
	private Integer uploadTimes;
	private String picPath;
	
	
	public BillInfo() {
		super();
	}


	public BillInfo(Integer id, String barCode, String barCode2, Integer length, Integer width, Integer height,
			Float volume, Float weight, Date scanTime, Date sortingTime, Long createTime, String marked, String chute,
			String transId, String status, String extend1, String extend2, String extend3, String extend4,
			Integer uploadTimes, String picPath) {
		super();
		this.id = id;
		this.barCode = barCode;
		this.barCode2 = barCode2;
		this.length = length;
		this.width = width;
		this.height = height;
		this.volume = volume;
		this.weight = weight;
		this.scanTime = scanTime;
		this.sortingTime = sortingTime;
		this.createTime = createTime;
		this.marked = marked;
		this.chute = chute;
		this.transId = transId;
		this.status = status;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
		this.extend4 = extend4;
		this.uploadTimes = uploadTimes;
		this.picPath = picPath;
	}
	
	public BillInfo(Integer id, String chute) {
		super();
		this.id = id;
		this.chute = chute;
	}

	public BillInfo(Integer id, String chute,String barCode2,String transId) {
		super();
		this.id = id;
		this.chute = chute;
		this.barCode2 = barCode2;
		this.transId = transId;
	}
	
	public BillInfo(Integer id, String chute,String barCode2,String transId,String extend3,String extend2,String extend1) {
		super();
		this.id = id;
		this.chute = chute;
		this.barCode2 = barCode2;
		this.transId = transId;
		this.extend3 =extend3;
		this.extend2 =extend2;
		this.extend1 =extend1;
	}

	public BillInfo(Integer id, String barCode, String barCode2, Integer length, Integer width, Integer height,
			Float volume, Float weight, Date scanTime, Date sortingTime, Long createTime, String marked, String chute,
			String transId, String status, String extend1, String extend2, String extend3, String picPath) {
		super();
		this.id = id;
		this.barCode = barCode;
		this.barCode2 = barCode2;
		this.length = length;
		this.width = width;
		this.height = height;
		this.volume = volume;
		this.weight = weight;
		this.scanTime = scanTime;
		this.sortingTime = sortingTime;
		this.createTime = createTime;
		this.marked = marked;
		this.chute = chute;
		this.transId = transId;
		this.status = status;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
		this.picPath = picPath;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getBarCode() {
		return barCode;
	}


	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}


	public String getBarCode2() {
		return barCode2;
	}


	public void setBarCode2(String barCode2) {
		this.barCode2 = barCode2;
	}


	public Integer getLength() {
		return length;
	}


	public void setLength(Integer length) {
		this.length = length;
	}


	public Integer getWidth() {
		return width;
	}


	public void setWidth(Integer width) {
		this.width = width;
	}


	public Integer getHeight() {
		return height;
	}


	public void setHeight(Integer height) {
		this.height = height;
	}


	public Float getVolume() {
		return volume;
	}


	public void setVolume(Float volume) {
		this.volume = volume;
	}


	public Float getWeight() {
		return weight;
	}


	public void setWeight(Float weight) {
		this.weight = weight;
	}


	public Date getScanTime() {
		return scanTime;
	}


	public void setScanTime(Date scanTime) {
		this.scanTime = scanTime;
	}


	public Date getSortingTime() {
		return sortingTime;
	}


	public void setSortingTime(Date sortingTime) {
		this.sortingTime = sortingTime;
	}


	public Long getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}


	public String getMarked() {
		return marked;
	}


	public void setMarked(String marked) {
		this.marked = marked;
	}


	public String getChute() {
		return chute;
	}


	public void setChute(String chute) {
		this.chute = chute;
	}


	public String getTransId() {
		return transId;
	}


	public void setTransId(String transId) {
		this.transId = transId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getExtend1() {
		return extend1;
	}


	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}


	public String getExtend2() {
		return extend2;
	}


	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}


	public String getExtend3() {
		return extend3;
	}


	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}


	public String getExtend4() {
		return extend4;
	}


	public void setExtend4(String extend4) {
		this.extend4 = extend4;
	}


	public Integer getUploadTimes() {
		return uploadTimes;
	}


	public void setUploadTimes(Integer uploadTimes) {
		this.uploadTimes = uploadTimes;
	}


	public String getPicPath() {
		return picPath;
	}


	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}


	@Override
	public String toString() {
		return "BillInfo [id=" + id + ", barCode=" + barCode + ", barCode2=" + barCode2 + ", length=" + length
				+ ", width=" + width + ", height=" + height + ", volume=" + volume + ", weight=" + weight
				+ ", scanTime=" + scanTime + ", sortingTime=" + sortingTime + ", createTime=" + createTime + ", marked="
				+ marked + ", chute=" + chute + ", transId=" + transId + ", status=" + status + ", extend1=" + extend1
				+ ", extend2=" + extend2 + ", extend3=" + extend3 + ", extend4=" + extend4 + ", uploadTimes="
				+ uploadTimes + ", picPath=" + picPath + "]";
	}
	
}
