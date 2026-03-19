import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class MainClass {
    public static void main1(String[] args) {
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

        JButton button1 = new JButton("按钮---1");
        JButton button2 = new JButton("按钮---2");
        JButton button3 = new JButton("按钮---3");
        JButton button4 = new JButton("按钮---4");
        JButton button5 = new JButton("按钮---5");
        JButton button6 = new JButton("按钮---6");

        panel1.add(button1, "card1");
        panel2.add(button2, "card2");
        panel2.add(button3, "card2");
        panel3.add(button4, "card3");
        panel3.add(button5, "card3");
        panel3.add(button6, "card3");
        layout.show(panel,"card3");//显示卡片2

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭响应方式
        frame.setVisible(true);//显示窗体
    }

    public static void main2(String[] args) {
        JFrame frame = new JFrame("网格包布局管理器");//新建JFrame窗体
        frame.setSize(400,200);//设置窗体宽高
        JPanel panel = new JPanel();//新建JPanel面板
        frame.setContentPane(panel);//将panel设为窗体的内容面板

        GridBagLayout layout = new GridBagLayout();//新建网格包布局管理器
        panel.setLayout(layout);//设置panel的布局管理器为layout
        GridBagConstraints constraints = new GridBagConstraints();//新建约束条件
        constraints.fill = GridBagConstraints.BOTH;//组件填满网格
        constraints.weightx = 1.0; // ✅ 新增：水平方向撑开
        constraints.weighty = 1.0;
        constraints.insets = new Insets(2, 2, 2, 2);

        JButton button1 = new JButton("按钮---1");//新建按钮1
        JButton button2 = new JButton("按钮---2");//新建按钮2
        JButton button3 = new JButton("按钮---3");//新建按钮3
        JButton button4 = new JButton("按钮---4");//新建按钮4
        JButton button5 = new JButton("按钮---5");//新建按钮5
        JButton button6 = new JButton("按钮---6");//新建按钮6
        JButton button7 = new JButton("按钮---7");//新建按钮7

        constraints.gridx = 0;//设置组件添加位置列数
        constraints.gridy = 0;//设置组件添加位置行数
        constraints.gridwidth = 2;//设置组件在网格占用的列数
        constraints.gridheight = 1;//设置组件在网格中占用的行数
        panel.add(button1,constraints);//向panel中添加按钮1

        constraints.gridx = 2;//设置组件添加位置列数
        constraints.gridy = 0;//设置组件添加位置行数
        constraints.gridwidth = 2;//设置组件在网格占用的列数
        constraints.gridheight = 1;//设置组件在网格中占用的行数
        panel.add(button2,constraints);//向panel中添加按钮2

        constraints.gridx = 0;//设置组件添加位置列数
        constraints.gridy = 1;//设置组件添加位置行数
        constraints.gridwidth = 1;//设置组件在网格占用的列数
        constraints.gridheight = 1;//设置组件在网格中占用的行数
        panel.add(button3,constraints);//向panel中添加按钮3

        constraints.gridx = 1;//设置组件添加位置列数
        constraints.gridy = 1;//设置组件添加位置行数
        constraints.gridwidth = 2;//设置组件在网格占用的列数
        constraints.gridheight = 2;//设置组件在网格中占用的行数
        panel.add(button4,constraints);//向panel中添加按钮4

        constraints.gridx = 0;//设置组件添加位置列数
        constraints.gridy = 2;//设置组件添加位置行数
        constraints.gridwidth = 1;//设置组件在网格占用的列数
        constraints.gridheight = 1;//设置组件在网格中占用的行数
        panel.add(button5,constraints);//向panel中添加按钮5

        constraints.gridx = 3;//设置组件添加位置列数
        constraints.gridy = 1;//设置组件添加位置行数
        constraints.gridwidth = 1;//设置组件在网格占用的列数
        constraints.gridheight = 1;//设置组件在网格中占用的行数
        panel.add(button6,constraints);//向panel中添加按钮6

        constraints.gridx = 3;//设置组件添加位置列数
        constraints.gridy = 2;//设置组件添加位置行数
        constraints.gridwidth = 1;//设置组件在网格占用的列数
        constraints.gridheight = 1;//设置组件在网格中占用的行数
        panel.add(button7,constraints);//向panel中添加按钮7

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭响应方式
        frame.setVisible(true);//显示窗体
    }

    public static void main(String[] args) {
        JPanel panel[]  = new JPanel[7];//声明一个JPanel数组
        Border border[] = new Border[7];//声明一个Border数组

        JFrame frame= new JFrame("边框示例");
        frame.setSize(350,150);
        frame.setLayout(new GridLayout(2,4));//为窗体设置一个2行4列的网格布局管理器

        border[0] = new TitledBorder("title");//创建一个TitleBorder对象
        border[1] = new EtchedBorder();//创建一个EtchedBorder对象
        border[2] = new LineBorder(Color.BLUE);//创建一个LineBorder对象
//创建一个MatteBorder对象，添加一个宽度为5的红色边框
        border[3] = new MatteBorder(5,5,5,5,Color.RED);
        border[4] = new BevelBorder(BevelBorder.RAISED);//创建一个BevelBorder对象
        //创建一个SoftBevelBorder对象
        border[5] = new SoftBevelBorder(BevelBorder.LOWERED);
        //创建一个下组合边框对象，此边框由两个边框组合而成
        border[6] = new CompoundBorder(new TitledBorder(("compound")),
                new LineBorder(Color.BLACK));

        for(int i=0;i<7;i++) {
            panel[i] = new JPanel();
            panel[i].setBorder(border[i]);
            frame.add(panel[i]);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}