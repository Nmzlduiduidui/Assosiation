package com.association.dao;

import com.association.model.SysDictData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/4/9 23:18
 */
public interface SysDictDataDao extends JpaRepository<SysDictData,Long>{
    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Query("select s from SysDictData s ")
    List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Query("select s from SysDictData s where s.dictType=:dictType")
    List<SysDictData> selectDictDataByType(@Param("dictType")String dictType);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Query("select s from SysDictData s where s.dictType=:dictType and s.dictValue=:dictValue")
    String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Query("select s from SysDictData s where s.dictCode=:dictCode")
    SysDictData selectDictDataById(@Param("dictCode")Long dictCode);

    /**
     * 查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据
     */
    @Query("select s from SysDictData s where s.dictType=:dictType")
    int countDictDataByType(@Param("dictType")String dictType);

    /**
     * 通过字典ID删除字典数据信息
     *
     * @param dictCode 字典数据ID
     * @return 结果
     */
    @Query("delete from SysDictData s where s.dictCode=:dictCode")
    int deleteDictDataById(@Param("dictCode")Long dictCode);

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     * @return 结果
     */
    @Query("delete from SysDictData s where s.dictCode in (?1)")
    int deleteDictDataByIds(@Param("dictCodes")Long[] dictCodes);

    /**
     * 新增字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    // int insertDictData(SysDictData dictData);

    /**
     * 修改字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
   //  int updateDictData(SysDictData dictData);

    /**
     * 同步修改字典类型
     * @param dictType 新字典类型
     * @return 结果
     */
    @Query("update SysDictData s set s.dictType=:dictType")
     int updateDictDataType( @Param("dictType") String dictType);
}
