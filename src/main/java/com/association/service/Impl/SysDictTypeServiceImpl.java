package com.association.service.Impl;

import com.association.common.utils.StringUtils;
import com.association.constant.UserConstants;
import com.association.dao.SysDictDataDao;
import com.association.dao.SysDictTypeDao;
import com.association.mapper.SysDictDataMapper;
import com.association.mapper.SysDictTypeMapper;
import com.association.model.SysDictType;
import com.association.service.ISysDictTypeService;
import com.association.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典 业务层处理
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService
{
    @Autowired
    private SysDictTypeDao dictTypeDao;

    @Autowired
    private SysDictDataDao dictDataDao;

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType)
    {
        return dictTypeDao.selectDictTypeList(dictType);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll()
    {
        return dictTypeDao.selectDictTypeAll();
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId)
    {
        return dictTypeDao.selectDictTypeById(dictId);
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    public SysDictType selectDictTypeByType(String dictType)
    {
        return dictTypeDao.selectDictTypeByType(dictType);
    }

    /**
     * 通过字典ID删除字典信息
     *
     * @param dictId 字典ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeById(Long dictId)
    {
        return dictTypeDao.deleteDictTypeById(dictId);
    }

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    public int deleteDictTypeByIds(Long[] dictIds)
    {
        return dictTypeDao.deleteDictTypeByIds(dictIds);
    }

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dictType)
    {
        Long dictId = dictType.getDictId();

        if(StringUtils.isNotNull(dictType)&&StringUtils.isNotNull(dictId)){
            dictTypeDao.save(dictType);
        }
        return 1;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDictType(SysDictType dictType)
    {
        Long dictId = dictType.getDictId();

        if(StringUtils.isNotNull(dictType)&&StringUtils.isNotNull(dictId)){

            //获取修改前原字典类型实体
            SysDictType oldDictType = dictTypeDao.selectDictTypeById(dictId);

            //进行修改的字段覆盖原来的字典类型实体
            UpdateUtil.copyNullProperties(dictType,oldDictType);

            //save直接进行更新,未修改的字段不会为null
            dictTypeDao.save(oldDictType);

            //同步修改字典类型
            dictDataDao.updateDictDataType(dictType.getDictType());
            return 1;
        }else {
            return 0;
        }

    }

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict)
    {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = dictTypeDao.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
