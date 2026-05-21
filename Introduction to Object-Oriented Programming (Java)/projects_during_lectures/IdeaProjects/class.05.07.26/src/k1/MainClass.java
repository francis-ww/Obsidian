package k1;

public class MainClass {

    public static void main(String[] args) {

        // 使用 Lambda 表达式实现接口
        ILambdaTest test = (name, age) -> {
            System.out.println(name + age + "岁了！");
            return age + 1;
        };

        // 调用接口方法并接收返回值
        int age = test.doSomething1("小明", 18);

        // 打印返回值
        System.out.println(age);
    }
}