import javax.swing.*;
import java.awt.*;

public class MainClass {
    public static void main1(String[] args) {
        System.out.println("aaaaaa");
    }
    public static void main2(String[] args) {
        JFrame frame;
        frame = new JFrame("Example");
        frame.setSize(800,400); //宽和高
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); //设置窗体可见
        frame.setLocation(480,300);
    }
    public static void main3(String[] args) {
        JFrame frame = new JFrame("示例窗口");
        JButton button = new JButton("确定");
        JButton button1 = new JButton("取消");
        button.setMnemonic('o'); //创建一个快捷键，alt+o
        button.setToolTipText("Press me!");
        button.setBounds(250,200, 60, 30);
        button1.setBounds(500,200,60,30);
        frame.add(button);
        frame.add(button1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(800,400);
        frame.setVisible(true);
    }
    public static void main4(String[] args) {
        JFrame frame = new JFrame("示例窗口");
        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }

        public static void main5(String[] args) {
            JFrame frame = new JFrame("示例窗口");
            JPanel panel = new JPanel();
            JPanel panel1 = new JPanel();
            panel1.setBounds(0,0,50,200);
            frame.setContentPane(panel); //将panel设置为窗体的内容面板
            BorderLayout layout = new BorderLayout(); //新建BorderLayout
            panel.setLayout(layout); //设置panel布局管理器为layout
            JButton button1 = new JButton("1");
            JButton button2 = new JButton("2");
            JButton button3 = new JButton("3");
            JButton button4 = new JButton("4");
            JButton button5 = new JButton("5");
            panel.add(button1,BorderLayout.NORTH);
            panel.add(button2,BorderLayout.SOUTH);
            panel.add(button3,BorderLayout.WEST);
            panel.add(button4,BorderLayout.EAST);
            panel.add(button5,BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setVisible(true);
        }
    public static void main6(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);
        BorderLayout layout = new BorderLayout();
        frame.setLayout(layout);

        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        frame.add(button1,BorderLayout.NORTH);
        frame.add(button2,BorderLayout.EAST);
        frame.add(button3,BorderLayout.SOUTH);
        frame.add(button4,BorderLayout.CENTER);

        JPanel panel = new JPanel();
        GridLayout layout_west =new GridLayout(3,1);
        panel.setLayout(layout_west);
        JButton button51 = new JButton("w1");
        JButton button52 = new JButton("w1");
        JButton button53 = new JButton("w1");
        panel.add(button51);
        panel.add(button52);
        panel.add(button53);
        frame.add(panel,BorderLayout.WEST);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);
        JPanel mainPanel = new JPanel();
        frame.add(mainPanel);
        BorderLayout layout = new BorderLayout();
        mainPanel.setLayout(layout);

        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        mainPanel.add(button1,BorderLayout.NORTH);
        mainPanel.add(button2,BorderLayout.EAST);
        mainPanel.add(button3,BorderLayout.SOUTH);
        mainPanel.add(button4,BorderLayout.CENTER);

        JPanel westPanel = new JPanel();
        GridLayout layout_west =new GridLayout(3,1);
        westPanel.setLayout(layout_west);
        JButton button51 = new JButton("w1");
        JButton button52 = new JButton("w1");
        JButton button53 = new JButton("w1");
        westPanel.add(button51);
        westPanel.add(button52);
        westPanel.add(button53);
        mainPanel.add(westPanel,BorderLayout.WEST);
    }
}
