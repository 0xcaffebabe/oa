package wang.ismy.oa.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/*
* 被此注解注解的成员变量不能为空,否则抛出异常
* */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomNotNull {

    class Validation implements ValidInterface{
        @Override
        public boolean valid(Object object) {
            return object!=null;
        }

        @Override
        public String getErrorMessage(String fieldName) {
            return fieldName+"不能为空";
        }
    }
}
