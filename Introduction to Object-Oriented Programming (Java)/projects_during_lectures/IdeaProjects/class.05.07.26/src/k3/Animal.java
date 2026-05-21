package k3;

public class Animal {
    private String name;
    private int age;

    // 无参数构造器
    public Animal() {
        System.out.println("通过无参数构造器实例化");
    }

    // 有参数构造器
    public Animal(String aname, int aage) {
        this.name = aname;
        this.age = aage; // 修正：将参数值赋给成员变量
        System.out.println("通过有参数构造器实例化，名字：" + aname + "，年龄：" + aage);
    }
}