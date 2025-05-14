import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Calculator implements ActionListener {

    boolean isOperatorClicked = false;
    double oldValue, newValue;
    int calculation;

    JFrame jf;
    JLabel displayLabel;
    JButton sevenButton, eightButton, nineButton,
            plusButton, fourButton, fiveButton, sixButton,
            minusButton, oneButton, twoButton, threeButton, mulButton,
            dotButton, zeroButton, equalButton, divButton,
            clearButton, backspaceButton;

    public Calculator() {
        jf = new JFrame("Calculator");
        jf.setBounds(0, 0, 500, 550);
        jf.setLayout(null);
        jf.getContentPane().setBackground(Color.BLACK);

        displayLabel = new JLabel();
        displayLabel.setBounds(20, 30, 440, 90);
        displayLabel.setBackground(Color.WHITE);
        displayLabel.setOpaque(true);
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayLabel.setForeground(Color.BLACK);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        jf.add(displayLabel);

        String[] btnTexts = {"7", "8", "9", "+",
                             "4", "5", "6", "-",
                             "1", "2", "3", "x",
                             ".", "0", "=", "/"};

        JButton[] buttons = new JButton[16];

        int x = 20, y = 150;
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(btnTexts[i]);
            buttons[i].setBounds(x, y, 100, 70);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            buttons[i].addActionListener(this);
            buttons[i].setBackground(btnTexts[i].matches("[0-9]|\\.") ? Color.WHITE : Color.YELLOW);
            jf.add(buttons[i]);
            x += 110;
            if ((i + 1) % 4 == 0) {
                x = 20;
                y += 80;
            }
        }

        sevenButton = buttons[0];
        eightButton = buttons[1];
        nineButton = buttons[2];
        plusButton = buttons[3];
        fourButton = buttons[4];
        fiveButton = buttons[5];
        sixButton = buttons[6];
        minusButton = buttons[7];
        oneButton = buttons[8];
        twoButton = buttons[9];
        threeButton = buttons[10];
        mulButton = buttons[11];
        dotButton = buttons[12];
        zeroButton = buttons[13];
        equalButton = buttons[14];
        divButton = buttons[15];

        clearButton = new JButton("Clear");
        clearButton.setBounds(20, 470, 220, 40);
        clearButton.setFont(new Font("Arial", Font.PLAIN, 20));
        clearButton.addActionListener(this);
        clearButton.setBackground(Color.RED);
        jf.add(clearButton);

        backspaceButton = new JButton("Backspace");
        backspaceButton.setBounds(250, 470, 210, 40);
        backspaceButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backspaceButton.addActionListener(this);
        backspaceButton.setBackground(Color.GRAY);
        jf.add(backspaceButton);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        JButton[] digitButtons = {zeroButton, oneButton, twoButton, threeButton, fourButton,
                                  fiveButton, sixButton, sevenButton, eightButton, nineButton, dotButton};
        for (JButton btn : digitButtons) {
            if (source == btn) {
                displayLabel.setText(displayLabel.getText() + btn.getText());
                return;
            }
        }

        if (source == plusButton) {
            oldValue = Double.parseDouble(displayLabel.getText());
            calculation = 1;
            isOperatorClicked = true;
            displayLabel.setText(displayLabel.getText() + "+");
        } else if (source == minusButton) {
            oldValue = Double.parseDouble(displayLabel.getText());
            calculation = 2;
            isOperatorClicked = true;
            displayLabel.setText(displayLabel.getText() + "-");
        } else if (source == mulButton) {
            oldValue = Double.parseDouble(displayLabel.getText());
            calculation = 3;
            isOperatorClicked = true;
            displayLabel.setText(displayLabel.getText() + "x");
        } else if (source == divButton) {
            oldValue = Double.parseDouble(displayLabel.getText());
            calculation = 4;
            isOperatorClicked = true;
            displayLabel.setText(displayLabel.getText() + "/");
        } else if (source == equalButton) {
            String fullText = displayLabel.getText();
            String secondPart = "";

            try {
                if (calculation == 1) secondPart = fullText.substring(fullText.indexOf("+") + 1);
                else if (calculation == 2) secondPart = fullText.substring(fullText.indexOf("-") + 1);
                else if (calculation == 3) secondPart = fullText.substring(fullText.indexOf("x") + 1);
                else if (calculation == 4) secondPart = fullText.substring(fullText.indexOf("/") + 1);

                newValue = Double.parseDouble(secondPart);

                double result = 0;
                switch (calculation) {
                    case 1: result = oldValue + newValue; break;
                    case 2: result = oldValue - newValue; break;
                    case 3: result = oldValue * newValue; break;
                    case 4: result = oldValue / newValue; break;
                }

                String resultStr = Double.toString(result);
                if (resultStr.endsWith(".0")) resultStr = resultStr.replace(".0", "");
                displayLabel.setText(resultStr);

            } catch (Exception ex) {
                displayLabel.setText("Error");
            }

        } else if (source == clearButton) {
            displayLabel.setText("");
        } else if (source == backspaceButton) {
            String text = displayLabel.getText();
            if (!text.isEmpty()) {
                displayLabel.setText(text.substring(0, text.length() - 1));
            }
        }
    }
}