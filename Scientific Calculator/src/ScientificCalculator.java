import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private String operator = "";
    private double num1, num2, result;

    public ScientificCalculator() {
        setTitle("Made by Sahrior");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);


        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 4));
        panel.setBackground(new Color(30, 30, 30));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "sin", "cos", "tan", "exp",
                "C", "(", ")", "nCr",
                "nPr", "sqrt", "%", "log"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBackground(new Color(50, 50, 50));
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        getContentPane().setBackground(new Color(20, 20, 20));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("");
            operator = "";
        } else if (command.equals("=")) {
            if (!operator.isEmpty()) {
                num2 = Double.parseDouble(display.getText());
                calculate();
                display.setText(String.valueOf(result));
                operator = "";
            }
        } else {
            if (!operator.isEmpty()) {
                num2 = Double.parseDouble(display.getText());
                calculate();
                display.setText(String.valueOf(result));
            }
            operator = command;
            if (!command.equals("nCr") && !command.equals("nPr")) {
                num1 = Double.parseDouble(display.getText());
                display.setText("");
            }
        }


        if (command.equals("sin")) {
            result = Math.sin(Math.toRadians(Double.parseDouble(display.getText())));
            display.setText(String.valueOf(result));
        } else if (command.equals("cos")) {
            result = Math.cos(Math.toRadians(Double.parseDouble(display.getText())));
            display.setText(String.valueOf(result));
        } else if (command.equals("tan")) {
            result = Math.tan(Math.toRadians(Double.parseDouble(display.getText())));
            display.setText(String.valueOf(result));
        } else if (command.equals("sqrt")) {
            result = Math.sqrt(Double.parseDouble(display.getText()));
            display.setText(String.valueOf(result));
        } else if (command.equals("exp")) {
            result = Math.exp(Double.parseDouble(display.getText()));
            display.setText(String.valueOf(result));
        } else if (command.equals("log")) {
            result = Math.log(Double.parseDouble(display.getText()));
            display.setText(String.valueOf(result));
        } else if (command.equals("nCr")) {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = "nCr";
                display.setText("");
            }
        } else if (command.equals("nPr")) {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = "nPr";
                display.setText("");
            }
        }
    }

    private void calculate() {
        switch (operator) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> result = num1 / num2;
            case "%" -> result = num1 % num2;
            case "nCr" -> result = nCr((int) num1, (int) num2);
            case "nPr" -> result = nPr((int) num1, (int) num2);
        }
    }

    private double nCr(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    private double nPr(int n, int r) {
        return factorial(n) / factorial(n - r);
    }

    private double factorial(int num) {
        if (num <= 1) return 1;
        return num * factorial(num - 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScientificCalculator calculator = new ScientificCalculator();
            calculator.setVisible(true);
        });
    }
}
