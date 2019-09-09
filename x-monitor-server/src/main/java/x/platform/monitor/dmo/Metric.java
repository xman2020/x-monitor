package x.platform.monitor.dmo;

import java.util.Date;

public class Metric {

    private String mainKey;

    private String subKey;

//    private String values;

    private String objType;

    private String objNo;

    private String mainType;

    private String mainTypeExt;

    private String subType;

    private String valueType;

    private Date collectTime;

    private Date createTime;

    private Date updateTime;

    public String getMainKey() {
        return mainKey;
    }

    public void setMainKey(String mainKey) {
        this.mainKey = mainKey;
    }

    public String getSubKey() {
        return subKey;
    }

    public void setSubKey(String subKey) {
        this.subKey = subKey;
    }

//    public String getValues() {
//        return values;
//    }
//
//    public void setValues(String values) {
//        this.values = values;
//    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getObjNo() {
        return objNo;
    }

    public void setObjNo(String objNo) {
        this.objNo = objNo;
    }

    public String getMainType() {
        return mainType;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }

    public String getMainTypeExt() {
        return mainTypeExt;
    }

    public void setMainTypeExt(String mainTypeExt) {
        this.mainTypeExt = mainTypeExt;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    //---------------------------------------------------------

    private String id;

    private String name;

    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //---------------------------------------------------------

    private Date collectTimeFrom;

    private Date collectTimeTo;

    public Date getCollectTimeFrom() {
        return collectTimeFrom;
    }

    public void setCollectTimeFrom(Date collectTimeFrom) {
        this.collectTimeFrom = collectTimeFrom;
    }

    public Date getCollectTimeTo() {
        return collectTimeTo;
    }

    public void setCollectTimeTo(Date collectTimeTo) {
        this.collectTimeTo = collectTimeTo;
    }

}
