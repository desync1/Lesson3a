import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    static class Test {
        @MyAnnotation(a = 2, b = 4)
        public void mul(int a, int b) {
            System.out.println(a * b);
        }
    }

    public static void main(String[] args) {
        Class<?> cls = Test.class;
        try {
            Method[] methods = cls.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(MyAnnotation.class)) {
                    MyAnnotation an = method.getAnnotation(MyAnnotation.class);
                    method.invoke(new Test(), an.a(), an.b());
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
