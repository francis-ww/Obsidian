package K1;

/*public class MainClass { // 与文件名必须相同
    public static void main {
        int result;
        ClassA ca = new ClassA();
        result = ca.add(10, 10);
        System.out.println(result);

        ClassSuper cs;
        cs = ca;
        // cs.add(10, 10); // error! 因为 ClassSuper 中没有定义 add 方法

        MyInterface mi;
        mi = ca;
        mi.add(10, 10);

    }
}
*/
// 接口变量可以存储实现这个类的实例, 接口类型变量访问不到除接口定义以外的其他函数