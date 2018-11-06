package wang.ismy.oa.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomEmail {

    class validation implements ValidInterface{

        @Override
        public boolean valid(Object object) {
            if(object==null){
                return false;
            }

            if(object instanceof String){

                return ((String) object).contains("@");
            }else{
                return false;
            }

        }

        @Override
        public String getErrorMessage(String fieldName) {
            return fieldName+"不是为email格式";
        }
    }
}
