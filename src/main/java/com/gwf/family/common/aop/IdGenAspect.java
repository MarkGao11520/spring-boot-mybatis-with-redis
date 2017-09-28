package com.gwf.family.common.aop;

import com.gwf.family.business.core.entity.IdEntity;
import com.gwf.family.common.util.IdGen;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by gaowenfeng on 2017/9/27.
 */
@Aspect
@Component
public class IdGenAspect {
    @Before("execution(* com.gwf.family.business..*.dao.*Repository.insert*(..))")
    public void beforeInsert(JoinPoint joinPoint){
         Object model = joinPoint.getArgs()[0];
         if(model instanceof IdEntity)
             ((IdEntity) model).setBusinessId(IdGen.uuid());
    }
}
