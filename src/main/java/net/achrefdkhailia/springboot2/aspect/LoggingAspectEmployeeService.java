package net.achrefdkhailia.springboot2.aspect;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 **
 *  * aop provides a solution to implement Cross Cutting , concern as an aspect , and Define point cuts to indicate where the aspect has to be applied.
 *  * there are a few common aspects to implements transversals layers , that apply to all layers with  differents responsibilities (web , busines , data layers ..),
 *  *
 * Aspect for logging execution for all methods on EmployeeService , based on ( spring-aop and aspectjweaver )
 * refer to https://docs.spring.io/spring/docs/4.3.15.RELEASE/spring-framework-reference/html/aop.html
 * refer to  https://www.springboottutorial.com/spring-boot-and-aop-with-spring-boot-starter-aop
 */
@Aspect
@Component
public class LoggingAspectEmployeeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Run before the method execution.
     * @param joinPoint
     */
    @Before("execution(* net.achrefdkhailia.springboot2.service.EmployeeService.addEmployee(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.debug("logBefore running .....");
        log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * Run after the method returned a result.
     * @param joinPoint
     */
    @After("execution(* net.achrefdkhailia.springboot2.service.EmployeeService.addEmployee(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.debug("logAfter running .....");
        log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * Run after the method returned a result, intercept the returned result as well.
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "execution(* net.achrefdkhailia.springboot2.service.EmployeeService.deleteEmployee(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.debug("logAfterReturning running .....");
        log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * Run around the method execution.
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* net.achrefdkhailia.springboot2.service.EmployeeService.getEmployeeById(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("logAround running .....");
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }

    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */

    @AfterThrowing(pointcut = "execution(* net.achrefdkhailia.springboot2.service.EmployeeService.updateEmployee(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.debug("logAfterThrowing running .....");
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), error.getCause() != null ? error.getCause() : "NULL");
    }
}
