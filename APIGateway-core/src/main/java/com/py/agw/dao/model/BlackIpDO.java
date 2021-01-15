package com.py.agw.dao.model;

import java.util.Date;

/**
 * description：对应 agw_black_ip 表
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2021/1/14
 */
public class BlackIpDO {

    private Long id;

    private Date createdTime;

    private Date modifiedTime;

    private Integer version;

    private Long outId;

    private String ip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}