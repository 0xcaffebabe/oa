package wang.ismy.oa.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* 自定义的验证注解，
* 被此注解注解的方法参数会在aop的前置通知进行参数验证，
* 若参数不符合其内部注解验证，会抛出异常
* */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface CustomValid {
}
