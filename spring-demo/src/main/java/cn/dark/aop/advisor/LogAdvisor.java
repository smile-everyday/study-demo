package cn.dark.aop.advisor;

import cn.dark.aop.annotation.Log;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author lwj
 * @date 2021-01-28
 */
@Component
public class LogAdvisor implements PointcutAdvisor, Pointcut, MethodMatcher {
    @Override
    public Pointcut getPointcut() {
        return this;
    }

    @Override
    public Advice getAdvice() {
        return new LogAdvice();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    /**
     * 这个方法会被调用两次：
     *     第一次：收集完Advisor后需要判断切面能否应用到当前bean上，此时传入的method是当前bean的method，所以从该方法上能获取到注解
     *     第二次：生成代理对象获取拦截链时需要判断是否匹配，这时method是接口的method，所以拿不到注解
     */
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        Method method1 = AopUtils.getMostSpecificMethod(method, aClass);
        Log annotation1 = method1.getAnnotation(Log.class);
        if (annotation1 != null) {
            System.out.println("method1:" + annotation1);
            Log annotation = method.getAnnotation(Log.class);
            System.out.println("method:" + annotation);
            return true;
        }
        return false;
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> aClass, Object... objects) {
        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    public class LogAdvice implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            return invocation.proceed();
        }
    }
}
