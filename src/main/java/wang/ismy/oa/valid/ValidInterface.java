package wang.ismy.oa.valid;

import java.lang.annotation.Annotation;

/*
* 每个验证器必须提供此接口的实现
* */
public interface ValidInterface {

     boolean valid(Object object);

     String getErrorMessage(String fieldName);
}
