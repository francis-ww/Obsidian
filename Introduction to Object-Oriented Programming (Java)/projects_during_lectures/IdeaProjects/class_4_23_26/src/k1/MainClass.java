package k1;

public class MainClass {
    public static void fm(ClassA a) {
        a.fa();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        K1.ClassA a = new ClassA();
        fm(a);
        a = new K1.ClassB();
        fm(a);
    }
}