package wang.ismy.oa.valid;

import org.springframework.stereotype.Component;
import wang.ismy.oa.dto.ValidResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ValidationProcessor {

    public List<Class> getValidateClass(Parameter[] parameters){

        List<Class> list=new ArrayList<Class>();

        for (Parameter parameter : parameters) {
            Annotation[] annotations=parameter.getAnnotations();
            for (Annotation annotation : annotations) {
                if(annotation instanceof CustomValid){
                    list.add(parameter.getType());
                }
            }
        }
        return list;
    }

    private HashMap<Class<? extends Annotation>,ValidInterface> validMapping=new HashMap<>();

    {
        //注册验证注解实现
        validMapping.put(CustomNotNull.class, new CustomNotNull.Validation());
        validMapping.put(CustomEmail.class,new CustomEmail.validation());
    }

    public ValidResult valid(Field[] fields, Object target) throws IllegalAccessException {

        for (Field field : fields) {
            //获取每个成员的注解
            Annotation[] annotations=field.getDeclaredAnnotations();

//            判断注解是否在validMapping里面
            for (Annotation annotation : annotations) {
                ValidInterface validInterface=validMapping.get(annotation.annotationType());
                //如果validMapping拥有相关实现
                if(validInterface!=null){
                    field.setAccessible(true);
                    //如果这个成员变量注解验证失败
                    if(! validInterface.valid(field.get(target))){
                        return new ValidResult(false,validInterface.getErrorMessage(field.getName()));
                    }
                }
            }

        }

        return new ValidResult(true);
    }
}
