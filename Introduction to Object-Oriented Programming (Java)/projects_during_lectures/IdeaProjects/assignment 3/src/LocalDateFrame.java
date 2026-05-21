import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class LocalDateFrame extends JFrame {
    private JComboBox<Integer> yearCombo;
    private JComboBox<Integer> monthCombo;
    private JPanel calendarPanel;
    private final String STU_INFO = "2024141050106_王海天";

    public LocalDateFrame() {
        setTitle(STU_INFO);
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // 获取当前日期
        LocalDate now = LocalDate.now();

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("年:"));
        yearCombo = new JComboBox<>();
        for (int i = 2026; i <= 2035; i++) {
            yearCombo.addItem(i);
        }
        yearCombo.setSelectedItem(now.getYear());
        yearCombo.setEditable(false);

        topPanel.add(yearCombo);
        topPanel.add(new JLabel("月:"));
        monthCombo = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            monthCombo.addItem(i);
        }
        monthCombo.setSelectedItem(now.getMonthValue());
        monthCombo.setEditable(false);
        topPanel.add(monthCombo);

        add(topPanel, BorderLayout.NORTH);

        calendarPanel = new JPanel();
        add(calendarPanel, BorderLayout.CENTER);

        ActionListener listener = e -> updateCalendar();
        yearCombo.addActionListener(listener);
        monthCombo.addActionListener(listener);

        updateCalendar();

        setVisible(true);
    }

    private void updateCalendar() {
        calendarPanel.removeAll();
        // 使用网格布局：第一行是星期，后面是日期
        calendarPanel.setLayout(new GridLayout(0, 7, 5, 5));

        int year = (int) yearCombo.getSelectedItem();
        int month = (int) monthCombo.getSelectedItem();
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate today = LocalDate.now();

        String[] weekDays = {"Mon", "Tues", "Wed", "Thur", "Fri", "Sat", "Sun"};
        for (String day : weekDays) {
            JLabel label = new JLabel(day, JLabel.CENTER);
            label.setFont(new Font("Monospaced", Font.BOLD, 12));
            calendarPanel.add(label);
        }

        int startOffset = firstDayOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i < startOffset; i++) {
            calendarPanel.add(new JLabel(""));
        }

        int daysInMonth = firstDayOfMonth.lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate current = LocalDate.of(year, month, day);
            String text = String.valueOf(day);

            // 如果是今天，前面加 *
            if (current.equals(today)) {
                text = "*" + text;
            }

            JLabel dayLabel = new JLabel(text, JLabel.CENTER);
            calendarPanel.add(dayLabel);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }
}