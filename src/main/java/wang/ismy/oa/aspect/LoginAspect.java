package wang.ismy.oa.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wang.ismy.oa.dto.Result;
import wang.ismy.oa.enums.ResultState;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class LoginAspect {

    //对所有@LoginOnly进行登录验证
    @Pointcut("@annotation(wang.ismy.oa.annotations.LoginOnly)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取当前请求session
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);


        if(session.getAttribute("user")==null){
            return new Result<>(ResultState.ERROR,"尚未登录");
        }else{
            return joinPoint.proceed(joinPoint.getArgs());
        }
    }
}
