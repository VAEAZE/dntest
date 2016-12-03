package proxy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

import sun.misc.ProxyGenerator;

public class Test {
    
    public static void main(String[] args) {
        
        DataService service1 = new DataServiceImpl();
        
        DataService service = (DataService)Proxy.newProxyInstance(Test.class.getClassLoader(),
                new Class<?>[] {DataService.class},
                new DataInvacationHandler(new DataServiceImpl()));
        
        //        createProxyClass();
        //        
        //        service1.save(null);
        //        
        //        System.out.println("----------------------------------");
        
        service.save(null);
        
    }
    
    public static void createProxyClass() {
        
        byte[] classFile = ProxyGenerator.generateProxyClass("$proxy0",
                new Class<?>[] {DataService.class});
        
        try {
            FileOutputStream out = new FileOutputStream("$Proxy0.class");
            
            out.write(classFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
