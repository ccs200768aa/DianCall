package com.diancall.platf.biz.common;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.diancall.core.util.ToolUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-16
 * Time: 20:57
 */
public class DBHelper<T> {
    private Class<T> clazz;

    private BaseMapper<?> baseMapper;

    private DBHelper(Class clazz) {
        this.clazz = clazz;
        this.baseMapper = (BaseMapper<?>) SpringContextHelper.getBean(clazz);
    }

    public static <T> DBHelper<T> create(Class<T> clazz) {
        return new DBHelper<T>(clazz);
    }

    public BaseMapper<?> getBaseMapper() {
        return this.baseMapper;
    }

    public static <T> T getMapper(Class<T> clazz) {
        return SpringContextHelper.getBean(clazz);
    }

    public <E> E selectOneByCon(String condition, Object value) {
        List<E> results = selectOneByConList(condition, value);
        if (!ToolUtils.isEmpty(results)) {
            return results.get(0);
        }
        return null;
    }


    public <E> List<E> selectOneByConList(String condition, Object value) {
        HashMap<String, Object> conditionMap = new HashMap<>();
        conditionMap.put(condition, value);

        List<E> results = (List<E>) this.baseMapper.selectByMap(conditionMap);
        if (!ToolUtils.isEmpty(results)) {
            return results;
        }
        return null;
    }
}
