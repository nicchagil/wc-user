package com.nicchagil.util.dubbo.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.rpc.RpcContext;

@Aspect
@Configuration /* 本来想用此AOP在调用提供者服务时隐式将SESSION ID传递过去，但因消费方无法通过结果在调用时拦截，需另寻他法。另一方面，觉得如需在提供者获取登录用户，因并不是任何提供者服务网都有此需求，由此需求的应用再显式传参也可 */
public class RpcContextAop {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${dubbo.token}")
	private String dubboToken;
    
	/* com.nicchagil.dubbo.interfaces包及其子包下的所有方法 */
    @Pointcut("execution(* com.nicchagil.dubbo.interfaces..*.*(..))")
    public void dubboServiceMethodPointcut() {}
    
    @Around("dubboServiceMethodPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	
    	Object target = proceedingJoinPoint.getTarget();
    	this.logger.info("AOP Object : {}, AOP Object Type : {}", target, target.getClass());
    	
    	if (StringUtils.isNotBlank(dubboToken) && !Boolean.FALSE.toString().equalsIgnoreCase(dubboToken)) {
    		RpcContext.getContext().setAttachment("token", dubboToken);
    	}
    	
    	Object result = proceedingJoinPoint.proceed();
    	return result;
    }

}
