package K1;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FrameEvent1 extends JFrame implements ActionListener{
    private JTextField textField1;
    private JTextField textField2;
    private JButton button;

    public FrameEvent1() {
        super("事件监听示例");//调用父类JFrame的构造方法
        setLayout(new GridLayout(1,3));
        textField1 = new JTextField(10);//新建第一个文本框
        button = new JButton("复制");//新建复制按钮
        textField2 = new JTextField(10);//新建第二个文本框
        //注册当前类作为按钮点击的事件监听器
        button.addActionListener(this);
        add(textField1);
        add(button);
        add(textField2);
        setSize(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        textField2.setText(textField1.getText());
    }


}