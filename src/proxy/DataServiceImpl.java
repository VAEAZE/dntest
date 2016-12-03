package proxy;

/** 
 * @Description 这个类就是我们的真正的业务类 
 * @ClassName   DataServiceImpl 
 * @Date        2016年10月3日 下午8:45:32 
 * @Author      dongnao.jack 
 */
public class DataServiceImpl implements DataService {
    
    public void save(String param) {
        System.out.println("我是被代理类：这里是真正的业务代码!--save");
    }
    
    public void update(String param) {
        System.out.println("我是被代理类：这里是真正的业务代码!--update");
    }
}
