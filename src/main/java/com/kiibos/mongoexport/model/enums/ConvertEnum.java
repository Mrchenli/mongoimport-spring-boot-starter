package com.kiibos.mongoexport.model.enums;

import java.text.SimpleDateFormat;

/**
 * @ClassName ConvertEnum
 * @Description TODO
 * @Author cl
 * @Date 2018/12/29 下午2:04
 **/
public enum  ConvertEnum {

    StringToInt("StringToInt"),
    IntToString("IntToString"),
    StringToDouble("StringToDouble"),
    DoubleToString("DoubleToString"),
    DateToString("DateToString"),
    StringToDate("StringToDate");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ConvertEnum(String name) {
        this.name = name;
    }

    public static ConvertEnum getConverEnum(String type){
        for (ConvertEnum convertEnum:values()){
            if(convertEnum.getName().equals(type)){
                return convertEnum;
            }
        }
        return null;
    }

    public static Object convert(Object value,String tp,String dateFormat) {
        try {
            ConvertEnum type = getConverEnum(tp);
            if(type==null){
                return value;
            }
            if(type.equals(StringToInt)){
                return Integer.valueOf((String) value);
            }else if(type.equals(IntToString)){
                return String.valueOf(value);
            }else if(type.equals(StringToDouble)){
                return Double.valueOf((Double) value);
            }else if(type.equals(DoubleToString)){
                return String.valueOf(value);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            if(type.equals(StringToDate)){
                return simpleDateFormat.parse((String) value);
            }
            if(type.equals(DateToString)){
                return simpleDateFormat.format(value);
            }
            return value;
        }catch (Exception e){
            e.printStackTrace();
            return value;
        }
    }


}
