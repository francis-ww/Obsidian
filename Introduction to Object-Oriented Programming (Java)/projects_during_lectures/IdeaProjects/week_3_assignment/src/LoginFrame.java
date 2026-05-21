import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    //继承JFrame的能力
    private JTextField userField; //创建一个可以打字的文本框（输入名字）
    private JPasswordField passField; //将密码隐藏
    private JButton loginBtn; //确定按钮

    public LoginFrame() {
        setTitle("登录界面-王海天-2024141050106");
        setSize(350, 180);
        setLocationRelativeTo(null); //让窗口出现在屏幕正中间
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel loginLabel = new JLabel("登录名：", SwingConstants.LEFT);
        JLabel passwrdLabel = new JLabel("密 码：", SwingConstants.LEFT);
        JButton loginBtn = new JButton("确定");
        JButton cancelBtn = new JButton("取消");
        userField = new JTextField();
        passField = new JPasswordField();
        JLabel emptyLabel = new JLabel("");

        add(loginLabel);
        add(passwrdLabel);
        add(userField);
        add(passField);
        add(loginBtn);
        add(cancelBtn);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = userField.getText();
                String pass = new String(passField.getPassword());

                if (name.equals("王海天") && pass.equals("2024141050106")) {
                    JOptionPane.showMessageDialog(null, "登录成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    // 密码错误 弹出一个红叉叉警告
                    JOptionPane.showMessageDialog(null, "登录名或密码错误", "失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}