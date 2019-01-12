package com.lineng.model;


/**
 * 统计渠道数据周/月/季/年统计 合作商和服务点VO
 * @author cailineng
 */
public class OfficePartnerAndChannelVO {
    private Integer channelOfficePartnerId;
    private String code;

    public Integer getChannelOfficePartnerId() {
        return channelOfficePartnerId;
    }

    public void setChannelOfficePartnerId(Integer channelOfficePartnerId) {
        this.channelOfficePartnerId = channelOfficePartnerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
