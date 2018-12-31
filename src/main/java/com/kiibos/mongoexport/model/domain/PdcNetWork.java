/*
 * Copyright (c) 2018. iotbull.com All Rights Reserved.
 * 项目名称：公牛智能家居云服务平台
 * 类名称：PdcNetWork.java
 * 创建人：chudk
 * 联系方式：chudingkun@gongniu.cn
 */
package com.kiibos.mongoexport.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

/**
 * @Auther: chudk
 * @Date: 9/14/18 16:17
 * @Description: 配网信息类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdcNetWork {

    // 配网方式 1.一键配网  2.zigbee配网  3.softAp配网  4.蓝牙配对 5.蓝牙mesh
    @Field(value = "network_type")
    @NotNull(message = "配网方式不能为空")
    @Range(min = 1,max = 5,message = "配网方式类型必须是1-5")
    private Integer networkType;

    // 配网图片
    @Field(value = "network_image")
    @NotEmpty(message = "配网图片地址不能为空")
    @Length(min = 1,max = 500,message = "配网图片地址长度1-500")
    private String networkImage;

    // 配网描述
    @Field(value = "network_desc")
    @NotEmpty(message = "配网描述不能为空")
    @Length(min = 1,max = 30,message = "配网描述长度1-30")
    private String networkDesc;

    // 配网语音提示
    @Field(value = "voice_desc")
    @NotEmpty(message = "配网语音提示不能为空")
    @Length(min = 1,max = 50,message = "配网语音提示长度1-50")
    private String voiceDesc;
}

