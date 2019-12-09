package reflection;

import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) {
        try {
            Class football = Class.forName("netcracker.hobbies.Football");
            Constructor[] constructors = football.getConstructors();
            printList("Конструкторы", constructors);
            Method[] methods = football.getDeclaredMethods();
            printList("\nМетоды", methods);
            Field[] fields = football.getDeclaredFields();
            printList("\nПоля", fields);
            Object instance = constructors[0].newInstance("Football", 5.5f, 3);

            System.out.println("\nВызов метода test");
            Class[] param = new Class[] {int.class, String.class, double.class};
            Method test = football.getDeclaredMethod("test", param);
            test.setAccessible(true);
            test.invoke(instance, 10, "test", 20.5);

            Field f = football.getDeclaredField("privateField");
            f.setAccessible(true);
            System.out.println("\nЗначение поля " + f.getName() + " до изменения: " + f.get(instance));
            f.setInt(instance, 1);
            System.out.println("Значение поля " + f.getName() + " после изменения: " + f.get(instance));
        } catch (Exception ex) {
            System.out.println("Ошибка");
        }
    }

    static void printList (String s, Object[] o) {
        System.out.println(s);
        for (int i = 0; i < o.length; i++) {
            System.out.println(o[i]);
        }
    }
}
