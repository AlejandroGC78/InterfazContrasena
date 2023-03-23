
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class password extends JFrame implements ActionListener {

    private JTextField passwordField;
    private JLabel resultLabel;
    private String resultMessage;

    public password() {
        super("Password Checker");

        // Configura los elementos de la interfaz
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JTextField(20);
        JButton checkButton = new JButton("Check Password");
        checkButton.addActionListener(this);
        resultLabel = new JLabel("");

        // Agrega los elementos a un panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(checkButton);
        panel.add(resultLabel);

        // Agrega el panel a la ventana
        getContentPane().add(panel);

        // Configura la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Obtiene la contraseña ingresada por el usuario
        String password = passwordField.getText();
        if (password.length() < 8) {
            resultMessage = "La contraseña debe tener al menos 8 caracteres";
        } else if (!password.matches(".*[a-z].*")) {
            resultMessage = "La contraseña debe contener al menos una letra minúscula";
        } else if (!password.matches(".*[A-Z].*")) {
            resultMessage = "La contraseña debe contener al menos una letra mayúscula";
        } else if (!password.matches(".*[0-9].*")) {
            resultMessage = "La contraseña debe contener al menos un dígito";
        } else if (!password.matches(".*[!@#$%^&*()].*")) {
            resultMessage = "La contraseña debe contener al menos un carácter especial";
        } else {
            resultMessage = "Password es seguro";
        }
        resultLabel.setText(resultMessage);

    }

    @Test
    @DisplayName("Prueba de seguridad de la contraseña")
    public void testPasswordStrength() {
        String password = "MyPassword!";

        Assertions.assertAll("Seguridad de la contraseña",
                () -> Assertions.assertTrue(password.length() >= 8, "La contraseña debe tener al menos 8 caracteres"),
                () -> Assertions.assertTrue(password.matches(".*[a-z].*"), "La contraseña debe contener al menos una letra minúscula"),
                () -> Assertions.assertTrue(password.matches(".*[A-Z].*"), "La contraseña debe contener al menos una letra mayúscula"),
                () -> Assertions.assertTrue(password.matches(".*[0-9].*"), "La contraseña debe contener al menos un dígito"),
                () -> Assertions.assertTrue(password.matches(".*[!@#$%^&*()].*"), "La contraseña debe contener al menos un carácter especial"));
    }

    public static void main(String[] args) {
        new password();
    }
}
