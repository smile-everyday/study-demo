package cn.dark.aop.advisor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author lwj
 * @date 2021-01-28
 */
@Aspect
public class LogAspect {

    @Pointcut("@annotation(cn.dark.aop.annotation.Log)")
    public void pc() {}

    @Before("pc()")
    public void before(JoinPoint joinPoint) {

    }
}
