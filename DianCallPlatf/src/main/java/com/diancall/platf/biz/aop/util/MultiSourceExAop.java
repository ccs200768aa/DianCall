package com.diancall.platf.biz.aop.util;

import com.diancall.platf.config.datasource.DataSource;
import com.diancall.platf.config.db.DBContextHolder;
import com.diancall.platf.config.db.DBTypeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-07
 * Time: 10:00
 */
@Aspect
@Order(-1)
@Component
public class MultiSourceExAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.diancall.platf.config.datasource.DataSource)")
    private void cut() {
    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;

        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

//        GenericSignature.TypeSignature typeSignature = null;
//        if (!(signature instanceof GenericSignature)) {
//            throw new IllegalArgumentException("该注解只能用于类");
//        }
//        Object target = point.getTarget();
//        Class currentClass = target.getClass();
        DataSource datasource = currentMethod.getAnnotation(DataSource.class);
        if (datasource != null) {
            if (datasource.name().equals("custDataSource")) {
                DBContextHolder.setDBType(DBTypeEnum.CUST_DATA_SOURCE);
            }
            if (datasource.name().equals("merchDataSource")) {
                DBContextHolder.setDBType(DBTypeEnum.MERCH_DATA_SOURCE);
            }
            logger.debug("设置数据源为：" + datasource.name());
        } else {
            DBContextHolder.setDBType(DBTypeEnum.MERCH_DATA_SOURCE);
            logger.debug("设置数据源为：dataSourceCurrent");
        }

        try {
            return point.proceed();
        } finally {
            logger.debug("清空数据源信息！");
            DBContextHolder.clearDBType();
        }
    }

}
