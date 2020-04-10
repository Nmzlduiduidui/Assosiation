package com.association.service.Impl;

import com.association.common.utils.StringUtils;
import com.association.dao.SysDictDataDao;
import com.association.mapper.SysDictDataMapper;
import com.association.model.SysDictData;
import com.association.service.ISysDictDataService;
import com.association.util.UpdateUtil;
import com.association.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典 业务层处理
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService
{
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private SysDictDataDao sysDictDataDao;

    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData)
    {
        return sysDictDataDao.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        return sysDictDataDao.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue)
    {
        return sysDictDataDao.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode)
    {
        return sysDictDataDao.selectDictDataById(dictCode);
    }

    /**
     * 通过字典ID删除字典数据信息
     * 
     * @param dictCode 字典数据ID
     * @return 结果
     */
    @Override
    public int deleteDictDataById(Long dictCode)
    {
        return sysDictDataDao.deleteDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据信息
     * 
     * @param dictCodes 需要删除的字典数据ID
     * @return 结果
     */
    public int deleteDictDataByIds(Long[] dictCodes)
    {
        return sysDictDataDao.deleteDictDataByIds(dictCodes);
    }

    /**
     * 新增保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData dictData)
    {
        Long dictCode = dictData.getDictCode();

        if (StringUtils.isNotNull(dictCode)){

            sysDictDataDao.save(dictData);
        }

        return 1;
    }

    /**
     * 修改保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData dictData)
    {

        Long dictCode = dictData.getDictCode();

        if (StringUtils.isNotNull(dictCode)){

            //获取修改前的字典数据实体
            SysDictData oldDictData = sysDictDataDao.selectDictDataById(dictCode);

            //将进行修改的字段覆盖修改前的实体字段
            UpdateUtil.copyNullProperties(dictCode,oldDictData);

            //保存更新
            sysDictDataDao.save(oldDictData);
        }

        return 1;
    }
}
