package com.zys.amuse.jdbc.dao;

import com.zys.amuse.jdbc.entity.ColumnInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table amuse_column_info
     *
     * @mbggenerated Thu Nov 29 18:16:12 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table amuse_column_info
     *
     * @mbggenerated Thu Nov 29 18:16:12 CST 2018
     */
    int insert(ColumnInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table amuse_column_info
     *
     * @mbggenerated Thu Nov 29 18:16:12 CST 2018
     */
    int insertSelective(ColumnInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table amuse_column_info
     *
     * @mbggenerated Thu Nov 29 18:16:12 CST 2018
     */
    ColumnInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table amuse_column_info
     *
     * @mbggenerated Thu Nov 29 18:16:12 CST 2018
     */
    int updateByPrimaryKeySelective(ColumnInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table amuse_column_info
     *
     * @mbggenerated Thu Nov 29 18:16:12 CST 2018
     */
    int updateByPrimaryKey(ColumnInfo record);

    List<ColumnInfo> listColumnBySheetId(@Param("sheetId") Integer sheetId);

    List<ColumnInfo> listColumn();

    void deleteBySheetId(@Param("sheetId") Integer sheetId);
}