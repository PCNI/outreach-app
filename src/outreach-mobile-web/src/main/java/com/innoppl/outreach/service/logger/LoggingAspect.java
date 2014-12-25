package com.innoppl.outreach.service.logger;

import com.innoppl.outreach.logger.LoggerUtils;
import javax.annotation.Resource;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

/**
 *
 * @author Jerald Mejarla
 */
@Aspect
public class LoggingAspect {

    @Resource
    private transient AbstractLogger logger;
    
    private final static String BEFORE_STRING
            = "[ Invoking Method: {0}() ]";
    private final static String BFRE_PARAMS_STRING
            = "[ Invoking Method: {0}({1}) ]";
    private final static String AFTER_THROWING
            = "[ Exception thrown in method: {0}, Exception message {1}, "
            + "Parameters: {2}, Stack Trace: {3} ]";
    private final static String AFTER_RETURNING
            = "[ Response for Method: {0}({2}), Response Returned: {1} ]";
    private final static String AFTER_RETURN_VOID
            = "[ Method: {0} - Finished ]";

    /**
     *
     * @param joinPoint
     * @param loggable
     */
    @Before(value = "@annotation(loggable)", 
            argNames = "joinPoint, loggable")
    public void before(final JoinPoint joinPoint, final InjectLogging loggable) {

        final Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        final String name = joinPoint.getSignature().getName();

        if (ArrayUtils.isEmpty(joinPoint.getArgs())) {
            logger.log(loggable.value(), clazz, null, BEFORE_STRING, name,
                    constructArgumentsString(clazz, joinPoint.getArgs()));
        } else {
            logger.log(loggable.value(), clazz, null, BFRE_PARAMS_STRING, name,
                    constructArgumentsString(clazz, joinPoint.getArgs()));
        }
    }

    /**
     *
     * @param joinPoint
     * @param throwable
     */
    @AfterThrowing(value = "@annotation(com.innoppl.outreach.service.logger.InjectLogging)", 
            throwing = "throwable", 
            argNames = "joinPoint, throwable")
    public void afterThrowing(final JoinPoint joinPoint,
            final Throwable throwable) {

        final Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        final String name = joinPoint.getSignature().getName();
        logger.log(LogLevel.ERROR, clazz, throwable, AFTER_THROWING, name,
                throwable.getMessage(), constructArgumentsString(clazz,
                        joinPoint.getArgs()), LoggerUtils.getStackTrace(throwable));
    }

    /**
     *
     * @param joinPoint
     * @param loggable
     * @param returnValue
     */
    @AfterReturning(value = "@annotation(loggable)",
            returning = "returnValue",
            argNames = "joinPoint, loggable, returnValue")
    public void afterReturning(final JoinPoint joinPoint,
            final InjectLogging loggable, final Object returnValue) {

        final Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        final String name = joinPoint.getSignature().getName();

        if (joinPoint.getSignature() instanceof MethodSignature) {
            final MethodSignature signature
                    = (MethodSignature) joinPoint.getSignature();
            final Class<?> returnType = signature.getReturnType();
            if (returnType.getName().compareTo("void") == 0) {
                logger.log(loggable.value(), clazz, null, AFTER_RETURN_VOID,
                        name, constructArgumentsString(clazz, returnValue));
                return;
            }
        }

        logger.log(loggable.value(), clazz, null, AFTER_RETURNING, name,
                constructArgumentsString(clazz, returnValue),
                constructArgumentsString(clazz, joinPoint.getArgs()));
    }

    /**
     *
     * @param clazz
     * @param arguments
     * @return argument {@link String}
     */
    private String constructArgumentsString(final Class<?> clazz,
            final Object... arguments) {

        final StringBuffer buffer = new StringBuffer();
        for (Object object : arguments) {
            if(buffer.length() > 0) {
                buffer.append(",");
            }
            buffer.append(object);            
        }
        return buffer.toString();
    }
}
