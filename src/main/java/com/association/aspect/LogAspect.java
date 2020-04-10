package com.association.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

//组件扫描
@Aspect
@Component
public class LogAspect {

    //日志记录器
    private final Logger looger = LoggerFactory.getLogger(this.getClass());

    //声明它是切面，execution设定拦截的类
    //*为任何的类，web下所有的类所有的方法，拦截
    @Pointcut("execution(* com.association.web.*.*(..))")
    public  void log(){

    }

    @Before("log()")  //切面之前执行的方法
    public void doBefore(JoinPoint joinPoint){  //

        //
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURI().toString(); // 获取url
        String ip = request.getRemoteAddr(); // 获取ip地址

        //拦截的某一个方法名，通过切面对象可获取类名方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        looger.info("Request：{}",requestLog);

    }

    @After("log()") //切面之后执行的方法
    public void doAfter(){
       // looger.info("=============doAfter=============");
    }

    @AfterReturning(returning = "result",pointcut = "log()") //拦截返回之后
    public void doAfterReturn(Object result){
        looger.info("Result:{}",result);

    }

    private class RequestLog{
        private String url;
        private  String ip;
        private String classMathod;
        private  Object[] args;

        public RequestLog(String url, String ip, String classMathod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMathod = classMathod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMathod='" + classMathod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
