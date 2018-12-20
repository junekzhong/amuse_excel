package com.zys.amuse.basis;

/**
 * 表字段实体，代表了excel要处理的表对应的字段
 *
 * Created by zhongjunkai on 18/11/16.
 */
public class ColumnInfo {

    /**
     * 字段名称
     */
    private String colName;

    /**
     * 字段描述
     */
    private String colDesc;

    /**
     * 字段类型
     */
    private String colType;

    /**
     * 是否为空，0：不能为空，1：可为空
     */
    private Integer nullTag;

    /**
     * 字段大小
     */
    private Integer colSize;

    public ColumnInfo() {

    }

    public ColumnInfo(String colName, String colDesc, String colType) {
        this.colName = colName;
        this.colDesc = colDesc;
        this.colType = colType;
    }

    public ColumnInfo(String colName, String colDesc, String colType, Integer nullTag, Integer colSize) {
        this.colName = colName;
        this.colDesc = colDesc;
        this.colType = colType;
        this.nullTag = nullTag;
        this.colSize = colSize;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColDesc() {
        return colDesc;
    }

    public void setColDesc(String colDesc) {
        this.colDesc = colDesc;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public Integer getNullTag() {
        return nullTag;
    }

    public void setNullTag(Integer nullTag) {
        this.nullTag = nullTag;
    }

    public Integer getColSize() {
        return colSize;
    }

    public void setColSize(Integer colSize) {
        this.colSize = colSize;
    }
}
