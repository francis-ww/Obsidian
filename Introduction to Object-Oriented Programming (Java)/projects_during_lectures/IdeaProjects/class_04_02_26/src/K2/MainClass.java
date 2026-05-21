package K2;

public class MainClass {
    public static void main(String[] args) {
        MyClass c = new MyClass();

        c.f1();
        c.f2();
        c.f3();
        c.method1();

        MyI intf = c;
        intf.f1();
        intf.f2();
        intf.f3();

    }
}
