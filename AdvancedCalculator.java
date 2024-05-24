import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private double temp, temp1, result, memory;
    private char operator;

    public AdvancedCalculator() {
        setTitle("Advanced Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        c.add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 5, 5, 5));

        String[] buttonLabels = {
            "7", "8", "9", "/", "C",
            "4", "5", "6", "*", "√",
            "1", "2", "3", "-", "^",
            "0", ".", "=", "+", "%",
            "M+", "M-", "MC", "MR",
            "x²", "x³", "∛", "π", "e"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
            panel.add(button);
        }

        c.add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "C":
                display.setText("");
                temp = 0;
                temp1 = 0;
                operator = ' ';
                break;
            case "=":
                calculate();
                display.setText(String.valueOf(result));
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "^":
                if (!display.getText().isEmpty()) {
                    temp = Double.parseDouble(display.getText());
                    operator = command.charAt(0);
                    display.setText("");
                }
                break;
            case "√":
                if (!display.getText().isEmpty()) {
                    temp = Double.parseDouble(display.getText());
                    result = Math.sqrt(temp);
                    display.setText(String.valueOf(result));
                }
                break;
            case "M+":
                if (!display.getText().isEmpty()) {
                    memory += Double.parseDouble(display.getText());
                }
                break;
            case "M-":
                if (!display.getText().isEmpty()) {
                    memory -= Double.parseDouble(display.getText());
                }
                break;
            case "MC":
                memory = 0;
                break;
            case "MR":
                display.setText(String.valueOf(memory));
                break;
            case "x²":
                if (!display.getText().isEmpty()) {
                    temp = Double.parseDouble(display.getText());
                    result = Math.pow(temp, 2);
                    display.setText(String.valueOf(result));
                }
                break;
            case "x³":
                if (!display.getText().isEmpty()) {
                    temp = Double.parseDouble(display.getText());
                    result = Math.pow(temp, 3);
                    display.setText(String.valueOf(result));
                }
                break;
            case "∛":
                if (!display.getText().isEmpty()) {
                    temp = Double.parseDouble(display.getText());
                    result = Math.cbrt(temp);
                    display.setText(String.valueOf(result));
                }
                break;
            case "π":
                display.setText(String.valueOf(Math.PI));
                break;
            case "e":
                display.setText(String.valueOf(Math.E));
                break;
            default:
                display.setText(display.getText() + command);
                break;
        }
    }

    private void calculate() {
        temp1 = Double.parseDouble(display.getText());
        switch (operator) {
            case '+':
                result = temp + temp1;
                break;
            case '-':
                result = temp - temp1;
                break;
            case '*':
                result = temp * temp1;
                break;
            case '/':
                if (temp1 != 0) {
                    result = temp / temp1;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
            case '%':
                result = temp % temp1;
                break;
            case '^':
                result = Math.pow(temp, temp1);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdvancedCalculator().setVisible(true);
            }
        });
    }
}
