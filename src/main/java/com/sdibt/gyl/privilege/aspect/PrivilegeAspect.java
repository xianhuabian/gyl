package com.sdibt.gyl.privilege.aspect;

import java.util.Collection;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.domain.privilege.Privilege;
import com.sdibt.gyl.privilege.annotation.AnnotationParse;

@Aspect
//@Component
public class PrivilegeAspect {
	@Pointcut("execution(* com.itheima11.gyl.*.action.*.*(..))")
	private void accessMethod(){}
	
	@Around("accessMethod()")
	public Object accessTargetMethod(ProceedingJoinPoint joinPoint) throws Throwable{
		/**
		 * 1、得到用户拥有的功能权限
		 */
		Collection<Privilege> functions = (Collection<Privilege>)ServletActionContext
				.getRequest().getSession().getAttribute("functions");
		/**
		 * 2、获取访问目标方法的注解的name属性的值
		 */
		Class targetClass = joinPoint.getTarget().getClass();
		String methodName = joinPoint.getSignature().getName();
		String accessMethod = AnnotationParse.parse(targetClass, methodName);
		if("".equals(accessMethod)){//如果目标方法上没有写权限的注解，或者写了权限的注解，但是没有写name属性，放行
			return joinPoint.proceed();
		}else{
			/**
			 * 判断用户是否具有该功能权限
			 */
			boolean flag = false;
			for (Privilege privilege : functions) {
				if(privilege.getName().equals(accessMethod)){
					flag = true;
					break;
				}
			}
			if(flag){//有权限
				return joinPoint.proceed();
			}else{
				ActionContext.getContext().getValueStack().push("您没有权限访问");
				return "privilege_error";
			}
		}
	}
}
