package com.kiibos.mongoexport.file;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MongoImportTest
 * @Description
 * @Author cl
 * @Date 2018/12/28 下午4:37
 **/
public class MongoImportTest {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("/Users/cl/ideaProjects/paascloud-chenli/mongo-export/src/test/java/com/kiibos/mongoexport/file/product.json");
        String text = IOUtils.toString(inputStream,"utf8");
        String prefix = "[";
        String subfix = "]";
        String spliter = "},{";
        text = prefix+text+subfix;
        String reg = "\\}[\\s]*\\{";
        System.out.println(reg);
        text = text.replaceAll(reg,spliter);
        text = text.replaceAll("ISODate\\(","");
        text = text.replaceAll("\\)","");
        System.out.println("=======>"+text);
        List<Object>  list = JSON.parseArray(text);
        for (Object obj : list){
            String name = (String) JSONPath.eval(obj,"$.name");
            System.out.println("=====>"+name);
            JSONPath.set(obj,"$.demo","chenli");
            System.out.println(obj.toString());
        }
        System.out.println(list.toString());
    }


    @Data
    class DateTest{
        private Date create;
        private Date update;
        private String des;
    }

    @Test
    public void testDateSerial(){
        Date date = new Date();
        DateTest dateTest = new DateTest();
        dateTest.setCreate(date);
        dateTest.setUpdate(date);
        dateTest.setDes("hello date test");
        System.out.println(JSONObject.toJSONString(dateTest));
    }


    @Test
    public void testDate() throws ParseException {
        String dstr = "2018-10-31T05:51:45.239Z";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = simpleDateFormat.parse(dstr);
        System.out.println(date.getTime());
    }

    @Test
    public void testJsonPathGetValue(){

    }

    @Test
    public void testJsonPathAddField(){

    }


}
