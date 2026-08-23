import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Nokia extends JFrame implements ActionListener {

    JTextField display;

    JButton[] buttons = new JButton[12];

    String[] keys = {
            "1",
            "2\nABC",
            "3\nDEF",
            "4\nGHI",
            "5\nJKL",
            "6\nMNO",
            "7\nPQRS",
            "8\nTUV",
            "9\nWXYZ",
            "*",
            "0",
            "#"
    };

    public Nokia() {

        setTitle("Nokia Keypad");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.CENTER);

        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3, 10, 10));

        for (int i = 0; i < 12; i++) {

            buttons[i] = new JButton(
                    "<html><center>" +
                            keys[i].replace("\n", "<br>") +
                            "</center></html>"
            );

            buttons[i].setFont(new Font("Arial", Font.BOLD, 18));
            buttons[i].addActionListener(this);

            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton b = (JButton) e.getSource();

        String text = b.getText();

        text = text.replace("<html><center>", "")
                .replace("</center></html>", "")
                .replace("<br>", " ");

        display.setText(display.getText() + text + " ");
    }

    public static void main(String[] args) {
        new Nokia();
    }
}