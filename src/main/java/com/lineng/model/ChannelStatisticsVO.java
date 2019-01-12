package com.lineng.model;

import java.util.List;


/**
 * 统计渠道数据周/月/季/年统计VO
 * @author cailineng
 *
 */
public class ChannelStatisticsVO {
	//统计时间
	private String timeType;
	//统计时间自定义开始
	private String timeBegin;
	//统计时间自定义结束
	private String timeEnd;
	//渠道所属人
	private String belonger;
	//合作商服务点
	private List<OfficePartnerAndChannelVO> officePartnerAndChannel ;

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(String timeBegin) {
		this.timeBegin = timeBegin;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

    public String getBelonger() {
        return belonger;
    }

    public void setBelonger(String belonger) {
        this.belonger = belonger;
    }

    public List<OfficePartnerAndChannelVO> getOfficePartnerAndChannel() {
		return officePartnerAndChannel;
	}

	public void setOfficePartnerAndChannel(List<OfficePartnerAndChannelVO> officePartnerAndChannel) {
		this.officePartnerAndChannel = officePartnerAndChannel;
	}
}