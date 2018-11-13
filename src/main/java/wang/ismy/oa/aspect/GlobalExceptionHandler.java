package wang.ismy.oa.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.dto.ValidResult;
import wang.ismy.oa.enums.ResultState;
import wang.ismy.oa.valid.ValidationProcessor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

@Aspect
@Component
public class GlobalExceptionHandler {


    @Autowired
    private ValidationProcessor validationProcessor;

    private Logger logger=LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* wang.ismy.oa.controller..*.*(..))")
    public void pointCut(){}



    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint){

        //获取所有的方法参数
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();

        Parameter[] parameters=targetMethod.getParameters();

        //获取需要验证的参数类型
        List<Class> list=validationProcessor.getValidateClass(parameters);

        for (Object arg : joinPoint.getArgs()) {
            for (Class aClass : list) {
                //将参数与需要验证的参数类型进行匹配
                if(arg.getClass().getName().equals(aClass.getName())){
                    try {
                        //如果验证失败
                        ValidResult validResult=validationProcessor.valid(arg.getClass().getDeclaredFields(),arg);
                        if(!validResult.isValid()){
                            return new Result<String>(ResultState.ERROR,validResult.getValidFailMessage());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /*
        * 以上代码进行参数校验
        * */

        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return new Result<>(ResultState.ERROR,throwable.getMessage()+":"+throwable.toString());
        }
    }



}
