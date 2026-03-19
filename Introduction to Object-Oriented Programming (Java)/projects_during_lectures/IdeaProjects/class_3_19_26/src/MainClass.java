import javax.swing.*;
import java.awt.*;

public class MainClass {
    public static void main(String[] args) {
        JFrame frame = new JFrame("网格布局管理器");//新建JFrame窗体
        frame.setSize(300,100);//设置窗体宽高
        JPanel panel = new JPanel();//新建JPanel面板
        frame.setContentPane(panel);//将panel设为窗体的内容面板
        CardLayout layout = new CardLayout(20,10);//新建一个卡片布局管理器
        panel.setLayout(layout);//设置layout设置为panel的布局管理器

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel.add(panel1, "面板1");
        panel.add(panel2, "面板2");
        panel.add(panel3, "面板3");

        JButton button1 = new JButton("按钮---1");//新建按钮1
        JButton button2 = new JButton("按钮---2");//新建按钮2
        JButton button3 = new JButton("按钮---3");//新建按钮3

        panel.add(button1,"card1");//向panel中添加按钮1
        panel.add(button2,"card2");//向panel中添加按钮2
        panel.add(button3,"card3");//向panel中添加按钮3
        layout.show(panel,"card3");//显示卡片2

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭响应方式
        frame.setVisible(true);//显示窗体
    }

}