package myProxy;

import java.lang.reflect.Method;

public class DataInvacationHandler implements MyInvocationHandler {
    
    public DataService service;
    
    public DataInvacationHandler(DataService service) {
        this.service = service;
    }
    
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Exception {
        
        before();
        
        method.invoke(service, args);
        
        after();
        
        return null;
    }
    
    private void before() {
        System.out.println("我是通知类，我是在调用了业务方法前调用的方法--before");
    }
    
    private void after() {
        System.out.println("我是通知类，我是在调用了业务方法后调用的方法--after");
    }
}
