import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraBasica extends JFrame implements ActionListener {

    private JTextField display;
    private double num1, num2, result;
    private char operator;

    public CalculadoraBasica() {
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Segoe UI", Font.PLAIN, 24));

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                ".", "0", "=", "+",
                "C"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
                display.setText(display.getText() + command);
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(display.getText());
                calculate();
                display.setText(String.valueOf(result));
            } else {
                operator = command.charAt(0);
                num1 = Double.parseDouble(display.getText());
                display.setText("");
            }
        } catch (NumberFormatException ex) {
            display.setText("Error");
        }
    }

    private void calculate() {
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CalculadoraBasica().setVisible(true);
        });
    }
}