public class ClassB extends ClassA implements InterfaceB { // ClassB继承A且实现B接口
    public void methodB() { System.out.println("ClassB.methodB()");} // 实现了methodB [cite: 73, 75]
    // 思考：ClassB里有methodA吗？有的，因为它继承了ClassA的methodA！ 
}
