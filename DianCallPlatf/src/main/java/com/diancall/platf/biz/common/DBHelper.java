package com.diancall.platf.biz.common;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-16
 * Time: 20:57
 */
public class DBHelper <T>{
    private Class<T> clazz;

    private BaseMapper<?> baseMapper;

    private DBHelper(Class clazz){
        this.clazz = clazz;
//        this.baseMapper = SpringContextH
    }
}
