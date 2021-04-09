package com.example.demo.enumenteri;

public enum  ProductStateEnum {
    MTJZP("0100","煤炭及制品"),
    SY("0200","石油、天然气及制品"),
    JSKS("0300","金属矿石"),
    QT("1700","其他");
    private String code;
    private String desc;
    ProductStateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public  String getValueByCode(String code){
        for(ProductStateEnum platformFree:ProductStateEnum.values()){
            if(code.equals(platformFree.getCode())){
                return platformFree.getDesc();
            }
        }
        return  null;
    }
}
