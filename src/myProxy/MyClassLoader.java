package myProxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
    
    public String dir;
    
    MyClassLoader(String dir) {
        this.dir = dir;
    }
    
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        
        File file = new File(dir + name + ".class");
        
        FileInputStream in = null;
        
        if (file.exists()) {
            
            try {
                in = new FileInputStream(file);
            }
            catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            
            byte[] buffer = new byte[1024];
            
            int len;
            
            try {
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            return this.defineClass("myProxy." + name,
                    out.toByteArray(),
                    0,
                    out.size());
        }
        
        return super.findClass(name);
    }
}
