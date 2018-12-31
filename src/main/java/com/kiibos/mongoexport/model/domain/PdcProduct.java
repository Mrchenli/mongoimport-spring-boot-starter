/*
 * Copyright (c) 2018. iotbull.com All Rights Reserved.
 * 项目名称：公牛智能家居云服务平台
 * 类名称：PdcProduct.java
 * 创建人：chudk
 * 联系方式：chudingkun@gongniu.cn
 */
package com.kiibos.mongoexport.model.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther: chudk
 * @Date: 9/14/18 16:33
 * @Description:
 */
@Document(collection = "product")
@Data
public class PdcProduct {
    @Id
    private String id = UUID.randomUUID().toString().replace("-","");//产品id
    // 产品品类
    @Field(value = "product_category_id")
    private  String productCategoryId;

    @Field(value = "product_category_name")
    private  String productCategoryName;

    // 产品名称
    @Field(value = "name")
    private  String name;

    // 产品型号
    @Field(value = "model")
    private  String model;

    // 产品图片
    @Field(value = "image")
    private  String image;

    // 协议类型 enum
    @Field(value = "protocol_type")
    private  Integer protocolType;

    // 节点类型(网关设备 子设备 普通设备) enum
    @Field(value = "device_type")
    private  Integer deviceType;

    // 通讯方式(跟产品硬件相关  与云端通讯 Wi-Fi  与网关通讯 ZigBee) enum
    @Field(value = "link_type")
    private  Integer linkType;

    // (已发布等状态) enum
    @Field(value = "status")
    private Integer status;

    // 配网信息
    @Field(value = "networks")
    private java.util.List<PdcNetWork> netWorks;

    //备注信息
    @Field(value = "comment")
    private String comment;

    // 创建人
    @Field(value = "creator")
    private String creator;

    // 创建时间
    @Field(value = "create_time")
    private Date createTime;

    // 更新时间
    @Field(value = "update_time")
    private Date updatetime;

    // 是否删除 true 删除, false 没删除
    @Field(value = "is_delete")
    private Boolean deleted;

    @Field(value = "updator")
    private String updator;//更新人


}
