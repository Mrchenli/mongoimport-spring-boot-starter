package com.kiibos.mongoexport.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.kiibos.mongoexport.model.enums.ConvertEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;

@Service
@Slf4j
public class MongoBatchImportServiceImpl implements MongoBatchImportService{


    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public String batchImport(HttpServletRequest request) {
        try {
            List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                    .getFiles("file");

            Map<String,String> data = new HashMap<>();
            Map<String,Properties> properties = new HashMap<>();
            for (MultipartFile file:files){
                String name = file.getOriginalFilename();
                String key=null;
                InputStream inputStream = file.getInputStream();
                if(name.contains(".json")){
                    key = name.substring(0,name.lastIndexOf(".json"));
                    data.put(key,IOUtils.toString(inputStream,"utf-8"));
                }else if(name.contains(".properties")){
                    key = name.substring(0,name.lastIndexOf(".properties"));
                    Properties propertie =new Properties();
                    propertie.load(inputStream);
                    properties.put(key,propertie);
                }
            }

            for (Map.Entry<String,Properties> entry : properties.entrySet()){
                String key = entry.getKey();
                Properties prop = entry.getValue();
                String clazz = prop.getProperty("CLASS");
                Class clzz = Class.forName(clazz);
                batchInsertOneCollection(clzz,data.get(key),prop);
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("导入失败 e==>{}",e);
            return "导入失败";
        }
        return "导入成功";
    }

    private <T> void batchInsertOneCollection(Class<T> clzz ,String text,Properties prop){
        String prefix = "[";
        String subfix = "]";
        String spliter = "},{";
        text = prefix+text+subfix;
        String reg = "\\}[\\s]*\\{";
        text = text.replaceAll(reg,spliter);
        text = text.replaceAll("ISODate\\(","");
        text = text.replaceAll("\\)","");
        List<Object> list = JSON.parseArray(text);
        List<T> rets = new ArrayList<>();
        for (Object obj : list){
            for (String key : prop.stringPropertyNames()){
                String value = prop.getProperty(key);
                if(key.contains("CLASS")){
                    continue;
                }
                String[] dsfs = value.split("&");
                String realValue = dsfs[0];
                String type = dsfs.length>=2?dsfs[1]:null;
                String dateFormate = dsfs.length>=3?dsfs[2]:null;

                Object ret = null;
                if(realValue.contains("$")){
                    ret = JSONPath.eval(obj,realValue);
                }else{
                    ret= realValue;
                }
                ret = ConvertEnum.convert(ret,type,dateFormate);
                JSONPath.set(obj,key,ret);
            }
            rets.add(JSON.parseObject(obj.toString(),clzz));
        }
        //mongoTemplate.insert(rets,clzz);
        mongoTemplate.insertAll(rets);
    }
}
