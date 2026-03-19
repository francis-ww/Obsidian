import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

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

    public static void main3(String[] args) {
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

        Border b1 = new CompoundBorder(new TitledBorder(("compoud")),
                new LineBorder(Color.BLACK));
        b1 = new CompoundBorder(b1, new LineBorder(Color.BLACK));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main4(String[] args) {
        JFrame frame= new JFrame("按钮示例");
        frame.setSize(350,150);
        frame.setLayout(new FlowLayout());//为窗体设置一个流式布局管理器

        //创建普通按钮，将其放在窗体上
        JButton button = new JButton("JButton");
        frame.add(button);

        //创建状态按钮，将其放在窗体上
        JToggleButton buttonToggle = new JToggleButton("JToggleButton");
        frame.add(buttonToggle);

        //创建复选择按钮，将其放在窗体上
        JCheckBox buttonCheckbox = new JCheckBox("JCheckBox");
        frame.add(buttonCheckbox);

        /*创建4个箭头按钮，将其放在窗体上*/
        //创建向上箭头按钮
        BasicArrowButton buttonUp = new BasicArrowButton(BasicArrowButton.NORTH);
        //创建向下箭头按钮
        BasicArrowButton buttonDown = new BasicArrowButton(BasicArrowButton.SOUTH);
        //创建向左箭头按钮
        BasicArrowButton buttonLeft = new BasicArrowButton(BasicArrowButton.WEST);
        //创建向右箭头按钮
        BasicArrowButton buttonRight = new BasicArrowButton(BasicArrowButton.EAST);
        //创建面板组件
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,3));
        //为面板组件添加边框
        panel1.setBorder(new TitledBorder("JArrowButton"));
        //将4个箭头按钮组件添加到面板上
        panel1.add(buttonUp);
        panel1.add(buttonDown);
        panel1.add(buttonRight);
        panel1.add(buttonLeft);
        //将添加了箭头按钮的面板组件添加到窗体中
        frame.add(panel1);

        /*创建单选按钮，将其放在窗体上*/
        JRadioButton buttonRadio1 = new JRadioButton("choice1");
        JRadioButton buttonRadio2 = new JRadioButton("choice2");
        buttonRadio2.setSelected(true);//将单选按钮2设为选中状态
        //创建单选按钮组，将两个单选按钮放在同一个组内
        ButtonGroup group = new ButtonGroup();
        group.add(buttonRadio1);
        group.add(buttonRadio2);
        //创建一个带边框的面板组件，将两个单选按钮放在面板组件内
        JPanel panel2 = new JPanel();
        panel2.setBorder(new TitledBorder("JRadioButton"));
        panel2.add(buttonRadio1);
        panel2.add(buttonRadio2);
        //将添加了单选按钮的面板板组件添加到窗体中
        frame.add(panel2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main5(String[] args) {
        JFrame frame = new JFrame("文本窗体");
        frame.setSize(400,300);//设置窗体的宽和高
        frame.setLayout(null);//使用绝对位置布局，不用布局管理器

        JLabel label1 = new JLabel("登录名：");//新建标签组件
        label1.setLocation(20,20);//设置标签组件位置
        label1.setSize(100,20);//设置标签组件大小
        frame.add(label1);//将标签组件添加到窗体上

        JTextField textField = new JTextField();//新建一个文本框组件
        textField.setLocation(20,40);//设置文本框的位置
        textField.setSize(150,30);//设置文本框的大小
        frame.add(textField);//将文本框添加到窗体

        JLabel label2 = new JLabel("密码：");//新建标签组件
        label2.setLocation(200,20);//设置标签组件位置
        label2.setSize(100,20);//设置标签组件大小
        frame.add(label2);//将标签组件添加到窗体上

        JPasswordField passwordField = new JPasswordField();//新建一个密码框组件
        passwordField.setLocation(200,40);//设置密码框的位置
        passwordField.setSize(150,30);//设置密码框的大小
        frame.add(passwordField);//将密码框添加到窗体
        passwordField.setEchoChar('!');

        JLabel label3 = new JLabel("文本区域：");//新建标签组件
        label3.setLocation(20,70);//设置标签组件位置
        label3.setSize(100,20);//设置标签组件大小
        frame.add(label3);//将标签组件添加到窗体上

        JTextArea textArea1 = new JTextArea();//新建一个文本区域组件
        textArea1.setLocation(20,100);//设置文本区域的位置
        textArea1.setSize(150,150);//设置文本区域的大小
        frame.add(textArea1);//将文本区域添加到窗体

        JLabel label4 = new JLabel("滚动文本域：");//新建标签组件
        label4.setLocation(200,70);//设置标签组件位置
        label4.setSize(100,20);//设置标签组件大小
        frame.add(label4);//将标签组件添加到窗体上

        JTextArea textArea2 = new JTextArea();//新建一个文本区域组件
        //新建一个滚动面板，将文本区域放到滚动面板中，垂直滚动条总是显示，水平滚动条只有在需要时显示
        JScrollPane panel = new JScrollPane(textArea2,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setLocation(200,100);//设置滚动面板的位置
        panel.setSize(150,150);//设置滚动面板的大小
        frame.add(panel);//    将滚动区域添加到窗体

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//设置窗体可见
    }

    public static void main6(String[] args) {
        String[] counties = {"中国","美国","俄罗斯","新加坡","英国","法国","德国","意套利"};
        String[] cities = {"北京","上海","广州","深圳","成都","武汉","南京","天津"};
        String[] provinces = {"Sichuan", "Hunan", "Guangdong", "Hubei", "Tibet", "Xinjiang"};
        JFrame frame = new JFrame("列表窗体");
        frame.setSize(400,300);//设置窗体的宽和高
        frame.setLayout(null);//使用绝对位置布局，不用布局管理器

        JLabel label1 = new JLabel("国家：");//新建标签组件
        label1.setLocation(20,20);//设置标签组件位置
        label1.setSize(100,20);//设置标签组件大小
        frame.add(label1);//将标签组件添加到窗体上

        JLabel label3 = new JLabel("Provinces:");
        label3.setLocation(20,120);//设置标签组件位置
        label3.setSize(100,20);//设置标签组件大小
        frame.add(label3);//将标签组件添加到窗体上

        JComboBox<String> comboBox = new JComboBox<String>();//新建一个下拉列表组件
        for(int i = 0;i<counties.length;i++) {//向下拉列表选项中添加组件
            comboBox.addItem(counties[i]);
        }
        comboBox.setLocation(20,40);//设置下拉列表组件的位置
        comboBox.setSize(150,30);//设置下拉列表组件的大小
        comboBox.setEditable(false);//设置下拉列表组件为可编辑
        frame.add(comboBox);//将下拉列表组件添加到窗体

        JComboBox<String> comboBox2 = new JComboBox<String>();//新建一个下拉列表组件
        for(int i = 0;i<provinces.length;i++) {//向下拉列表选项中添加组件
            comboBox2.addItem(provinces[i]);
        }
        comboBox2.setLocation(20,140);//设置下拉列表组件的位置
        comboBox2.setSize(150,30);//设置下拉列表组件的大小
        comboBox2.setEditable(false);//设置下拉列表组件为可编辑
        frame.add(comboBox2);//将下拉列表组件添加到窗体

        JLabel label2 = new JLabel("城市：");//新建标签组件
        label2.setLocation(200,20);//设置标签组件位置
        label2.setSize(100,20);//设置标签组件大小
        frame.add(label2);//将标签组件添加到窗体上

        JList<String> list = new JList<String>(cities);//新建列表组件
        list.setLocation(200,40);//设置列表组件的位置
        list.setSize(150,200);//设置列表组件的大小
        frame.add(list);//将列表组件添加到窗体

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//设置窗体可见
    }

    public static void main7(String[] args) {
        String[] tabNames = {
                "black","green","blue",
                "red","yellow","pink","gray","cyan"};
        Color[] colorNames = {
                Color.BLACK,Color.GREEN,Color.BLUE,
                Color.RED,Color.YELLOW,Color.PINK,Color.GRAY,Color.CYAN};
        Component[] com = new Component[8];
        com[0] = new JButton();
        com[1] = new JCheckBox();
        com[2] = new JRadioButton();
        com[3] = new JTextField();
        com[4] = new JTextArea();
        com[5] = new JCheckBox();
        com[6] = new JCheckBox();
        com[7] = new JCheckBox();
        JFrame frame = new JFrame("示例窗体");
        //按默认方式创建页签面板
        JTabbedPane tabs = new JTabbedPane();
        //按定制方式创建页签面板
        //JTabbedPane tabs = new JTabbedPane(JTabbedPane.BOTTOM,
        //        JTabbedPane.SCROLL_TAB_LAYOUT);
        for(int i = 0 ;i<tabNames.length;i++) {
            JPanel panel = new JPanel();
            panel.setBackground(colorNames[i]);
            tabs.addTab(tabNames[i], panel);
        }
        frame.add(tabs);
        for(int i = 0 ; i < tabNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(colorNames[i]);

            // 将对应的组件添加到当前的面板中
            panel.add(com[i]);

            // 将面板添加到标签页
            tabs.addTab(tabNames[i], panel);
        }
        frame.add(tabs);
        //设置窗体关闭行为，当用户点击窗体的关闭图标时，结束程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250,250);//设置窗体的宽和高
        frame.setVisible(true);//设置窗体可见
    }

       
}