import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StartTest {
    public static void start(Class c){
        List<Method> methods = new ArrayList<> ();
        Method[] classMethods = c.getDeclaredMethods ();

        for (Method m: classMethods){
            if(m.isAnnotationPresent (Test.class)){
                methods.add (m);
            }
        }
        

    }
}
