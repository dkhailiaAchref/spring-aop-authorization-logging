package net.achrefdkhailia.springboot2.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class AuthorizationAspect {

    @Autowired
    AuthorizationImpl authBean;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(* net.achrefdkhailia.springboot2.controller.*Controller.*(..)) && @annotation(net.achrefdkhailia.springboot2.aspect.Authorized)")
    public void webLayerAnnotatedAuthorized() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }


    @Before("webLayerAnnotatedAuthorized()")
    //public  void before(HttpServletRequest request){
        public  void before(JoinPoint joinPoint){

        HttpServletRequest request;
        Optional<Object> opt =Arrays.asList(joinPoint.getArgs()).stream().filter(param-> param  instanceof HttpServletRequest).findFirst() ;
        if (!opt.isPresent()){
            throw
                    new RuntimeException("request should be HttpServletRequesttype");
        }else{
             request=(HttpServletRequest)opt.get();
        }

        if(authBean.authorize(request.getHeader("Authorization"))){
            log.info(
                    "userSession"+
                    "session information which cann be acces in controller"
            );
        }else {
            throw new RuntimeException("auth error..!!!");
        }

    }
}
