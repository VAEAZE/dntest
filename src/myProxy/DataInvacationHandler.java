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
        System.out.println("����֪ͨ�࣬�����ڵ�����ҵ�񷽷�ǰ���õķ���--before");
    }
    
    private void after() {
        System.out.println("����֪ͨ�࣬�����ڵ�����ҵ�񷽷�����õķ���--after");
    }
}
