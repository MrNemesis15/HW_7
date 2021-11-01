import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class StartTest {
    public StartTest() {
    }

    public static void start(Class c) {
        List<Method> methods = new ArrayList();
        Method[] classMethods = c.getDeclaredMethods();
        Method[] var3 = classMethods;
        int var4 = classMethods.length;

        int var5;
        Method m;
        for(var5 = 0; var5 < var4; ++var5) {
            m = var3[var5];
            if (m.isAnnotationPresent(Test.class)) {
                methods.add(m);
            }
        }

        methods.sort(Comparator.comparingInt((Method i) -> {
            return ((Test)i.getAnnotation(Test.class)).priority();
        }).reversed());
        var3 = classMethods;
        var4 = classMethods.length;

        for(var5 = 0; var5 < var4; ++var5) {
            m = var3[var5];
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (methods.size() > 0 && ((Method)methods.get(0)).isAnnotationPresent(BeforeSuite.class)) {
                    throw new RuntimeException("@BeforeSuite annotation method > 1");
                }

                methods.add(0, m);
            }
        }

        var3 = classMethods;
        var4 = classMethods.length;

        for(var5 = 0; var5 < var4; ++var5) {
            m = var3[var5];
            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (methods.size() > 0 && ((Method)methods.get(methods.size() - 1)).isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException("@AfterSuite annotation method > 1");
                }

                methods.add(m);
            }
        }

        Iterator var9 = methods.iterator();

        while(var9.hasNext()) {
            m = (Method) var9.next ();

            try {
                m.invoke((Object)null);
            } catch (IllegalAccessException var7) {
                var7.printStackTrace();
            } catch (InvocationTargetException var8) {
                var8.printStackTrace();
            }
        }

    }
}