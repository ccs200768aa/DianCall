package com.diancall.platf.biz.aop.cust;

import com.alibaba.fastjson.JSONObject;
import com.diancall.core.retcodes.SuccRetCode;
import com.diancall.platf.biz.entity.cust.Custuser;
import com.diancall.platf.biz.service.cust.CustUserServiceI;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-08
 * Time: 09:43
 */
@Aspect
@Component
public class CustUserAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CustUserServiceI custUserService;


    @Pointcut(value = "execution(* com.diancall.platf..controller.cust.*.query*(..))")
    public void cut(){};

    @Around("cut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        String key = "cuList";
        ValueOperations<String,List<Custuser>> op = redisTemplate.opsForValue();

        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            Map<String,Object> map = new HashMap<>();
            map.put("retcode",new SuccRetCode());
            map.put("cuList",op.get(key));
            JSONObject jo = (JSONObject) JSONObject.toJSON(map);
            return jo.toJSONString();
        }else{
            List<Custuser> cuList = custUserService.queryList();
            op.set(key,cuList,10, TimeUnit.DAYS);
        }
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable.getMessage());
        }
    }
}
