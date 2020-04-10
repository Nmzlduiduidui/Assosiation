package com.association.dao;

import com.association.model.SysDictType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/4/9 23:17
 */
public interface SysDictTypeDao extends JpaRepository<SysDictType, Long> {
   /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
   @Query("select s from SysDictType s where s.dictType=dictType")
    List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Query("select s from SysDictType s")
    List<SysDictType> selectDictTypeAll();

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Query("select s from SysDictType s where s.dictId=:dictId")
    SysDictType selectDictTypeById(@Param("dictId")Long dictId);

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Query("select s from SysDictType s where s.dictType=:dictType")
    SysDictType selectDictTypeByType(@Param("dictType")String dictType);

    /**
     * 通过字典ID删除字典信息
     *
     * @param dictId 字典ID
     * @return 结果
     */
    @Query("delete from SysDictType s where s.dictId=:dictId")
    int deleteDictTypeById(@Param("dictId")Long dictId);

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    @Query("delete from SysDictType s where s.dictId in (?1)")
     int deleteDictTypeByIds(@Param("dictIds")Long[] dictIds);

    /**
     * 新增字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
//    int insertDictType(SysDictType dictType);

    /**
     * 修改字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
 //   int updateDictType(SysDictType dictType);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    @Query("select s from SysDictType s where s.dictType=:dictType")
    SysDictType checkDictTypeUnique(@Param("dictType")String dictType);
}
