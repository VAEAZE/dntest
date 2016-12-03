package myProxy;

public class Test {
    
    public static void main(String[] args) {
        DataService service = (DataService)MyProxy.newProxyInstance(Test.class.getClassLoader(),
                new Class<?>[] {DataService.class},
                new DataInvacationHandler(new DataServiceImpl()));
        
        try {
            service.save(null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
