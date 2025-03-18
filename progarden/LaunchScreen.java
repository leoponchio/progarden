import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class LaunchScreen extends JFrame {

    public LaunchScreen() {
        setTitle("Launch Screen - ProGarden");
        setSize(390, 844);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setPreferredSize(new Dimension(390, 40));
        statusBar.setBackground(Color.WHITE);

        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        timeLabel.setHorizontalAlignment(SwingConstants.LEFT);

        Timer timeTimer = new Timer(1000, e -> {
            timeLabel.setText(new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date()));
        });
        timeTimer.start();

        ImageIcon signalIcon = new ImageIcon("C:/Users/Lepon/Downloads/progarden/2313339.png");
        ImageIcon batteryIcon = new ImageIcon("C:/Users/Lepon/Downloads/progarden/png-transparent-computer-icons-battery-iphone-choose-the-color-battery-electronics-rectangle-black-thumbnail.png");
        ImageIcon wifiIcon = new ImageIcon("C:/Users/Lepon/Downloads/progarden/png-transparent-computer-icons-wi-fi-signal-wifi-miscellaneous-logo-black-thumbnail.png");

        Image signalImg = signalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image batteryImg = batteryIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image wifiImg = wifiIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        signalIcon = new ImageIcon(signalImg);
        batteryIcon = new ImageIcon(batteryImg);
        wifiIcon = new ImageIcon(wifiImg);

        JLabel signalLabel = new JLabel(signalIcon);
        JLabel wifiLabel = new JLabel(wifiIcon);
        JLabel batteryLabel = new JLabel(batteryIcon);

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        iconPanel.setBackground(Color.WHITE);
        iconPanel.add(wifiLabel);
        iconPanel.add(signalLabel);
        iconPanel.add(batteryLabel);

        statusBar.add(timeLabel, BorderLayout.WEST);
        statusBar.add(iconPanel, BorderLayout.EAST);

        ImageIcon logoIcon = new ImageIcon("C:/Users/Lepon/Downloads/progarden/Marca ProGarden.jpg");
        Image img = logoIcon.getImage();
        Image resizedImage = img.getScaledInstance(300, 265, java.awt.Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedImage);

        JLabel logoLabel = new JLabel(logoIcon, JLabel.CENTER);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setVerticalAlignment(SwingConstants.CENTER);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(Box.createVerticalStrut(200), BorderLayout.NORTH);
        contentPanel.add(logoLabel, BorderLayout.CENTER);
        contentPanel.add(Box.createVerticalStrut(200), BorderLayout.SOUTH);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(statusBar, BorderLayout.NORTH);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openLoginScreen();
                dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void openLoginScreen() {
        CadastroScreen cadastroScreen = new CadastroScreen();
        cadastroScreen.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LaunchScreen splash = new LaunchScreen();
            splash.setVisible(true);
        });
    }

    class CadastroScreen extends JFrame {
        private JTextField nomeField;
        private JTextField emailField;
        private JTextField dataNascimentoField;
        private JPasswordField passwordField;
        private JComboBox<String> userTypeComboBox;

        public CadastroScreen() {
            setTitle("Cadastro - ProGarden");
            setSize(390, 844);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setUndecorated(true);

            JPanel statusBar = new JPanel(new BorderLayout());
            statusBar.setPreferredSize(new Dimension(390, 40));
            statusBar.setBackground(Color.WHITE);

            JLabel timeLabel = new JLabel();
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
            Timer timeTimer = new Timer(1000, e -> {
                timeLabel.setText(new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date()));
            });
            timeTimer.start();

            ImageIcon signalIcon = new ImageIcon("C:/Users/Lepon/Downloads/progarden/2313339.png");
            ImageIcon batteryIcon = new ImageIcon("C:/Users/Lepon/Downloads/progarden/png-transparent-computer-icons-battery-iphone-choose-the-color-battery-electronics-rectangle-black-thumbnail.png");
            ImageIcon wifiIcon = new ImageIcon("C:/Users/Lepon/Downloads/progarden/png-transparent-computer-icons-wi-fi-signal-wifi-miscellaneous-logo-black-thumbnail.png");

            Image signalImg = signalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            Image batteryImg = batteryIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            Image wifiImg = wifiIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            signalIcon = new ImageIcon(signalImg);
            batteryIcon = new ImageIcon(batteryImg);
            wifiIcon = new ImageIcon(wifiImg);

            JLabel signalLabel = new JLabel(signalIcon);
            JLabel wifiLabel = new JLabel(wifiIcon);
            JLabel batteryLabel = new JLabel(batteryIcon);

            JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            iconPanel.setBackground(Color.WHITE);
            iconPanel.add(wifiLabel);
            iconPanel.add(signalLabel);
            iconPanel.add(batteryLabel);

            statusBar.add(timeLabel, BorderLayout.WEST);
            statusBar.add(iconPanel, BorderLayout.EAST);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBackground(Color.WHITE);

            ImageIcon logoIcon = new ImageIcon("C:/Users/Lepon/Downloads/progarden/Marca ProGarden.jpg");
            Image img = logoIcon.getImage();
            Image resizedLogo = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(resizedLogo);

            JLabel logoLabel = new JLabel(logoIcon, JLabel.CENTER);
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            mainPanel.add(Box.createVerticalStrut(30));
            mainPanel.add(logoLabel);
            mainPanel.add(Box.createVerticalStrut(30));

            JLabel welcomeLabel = new JLabel("Cadastro", JLabel.CENTER);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
            welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(welcomeLabel);
            mainPanel.add(Box.createVerticalStrut(30));

            nomeField = new JTextField("Nome");
            emailField = new JTextField("E-mail");
            dataNascimentoField = new JTextField("(dd/mm/yyyy)");
            passwordField = new JPasswordField("Senha");

            JTextField[] fields = {nomeField, emailField, dataNascimentoField};
            JPasswordField[] passwordFields = {passwordField};

            for (JTextField field : fields) {
                String placeholder = field.getText();
                field.setFont(new Font("Arial", Font.PLAIN, 14));
                field.setMaximumSize(new Dimension(300, 40));
                field.setAlignmentX(Component.CENTER_ALIGNMENT);
                field.setForeground(Color.GRAY);
                field.addFocusListener(new FocusListener() {
                    public void focusGained(FocusEvent e) {
                        if (field.getText().equals(placeholder)) {
                            field.setText("");
                            field.setForeground(Color.BLACK);
                        }
                    }

                    public void focusLost(FocusEvent e) {
                        if (field.getText().isEmpty()) {
                            field.setText(placeholder);
                            field.setForeground(Color.GRAY);
                        }
                    }
                });
                mainPanel.add(field);
                mainPanel.add(Box.createVerticalStrut(20));
            }

            for (JPasswordField field : passwordFields) {
                String placeholder = new String(field.getPassword());
                field.setFont(new Font("Arial", Font.PLAIN, 14));
                field.setMaximumSize(new Dimension(300, 40));
                field.setAlignmentX(Component.CENTER_ALIGNMENT);
                field.setEchoChar((char) 0);
                field.setForeground(Color.GRAY);
                field.addFocusListener(new FocusListener() {
                    public void focusGained(FocusEvent e) {
                        if (new String(field.getPassword()).equals(placeholder)) {
                            field.setText("");
                            field.setEchoChar('●');
                            field.setForeground(Color.BLACK);
                        }
                    }

                    public void focusLost(FocusEvent e) {
                        if (new String(field.getPassword()).isEmpty()) {
                            field.setEchoChar((char) 0);
                            field.setText(placeholder);
                            field.setForeground(Color.GRAY);
                        }
                    }
                });
                mainPanel.add(field);
                mainPanel.add(Box.createVerticalStrut(20));
            }

            String[] userTypes = {"Cliente", "Profissional"};
            userTypeComboBox = new JComboBox<>(userTypes);
            userTypeComboBox.setMaximumSize(new Dimension(300, 40));
            userTypeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(userTypeComboBox);
            mainPanel.add(Box.createVerticalStrut(30));

            JButton cadastrarButton = new JButton("Cadastrar");
            cadastrarButton.setBackground(new Color(157, 204, 57));
            cadastrarButton.setForeground(Color.WHITE);
            cadastrarButton.setFont(new Font("Arial", Font.BOLD, 16));
            cadastrarButton.setMaximumSize(new Dimension(300, 40));
            cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            cadastrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nome = nomeField.getText();
                    String email = emailField.getText();
                    String dataNascimento = dataNascimentoField.getText();
                    String password = new String(passwordField.getPassword());
                    String userType = (String) userTypeComboBox.getSelectedItem();
                    saveUserToCSV(nome, email, dataNascimento, password, userType);
                }
            });

            mainPanel.add(cadastrarButton);
            mainPanel.add(Box.createVerticalStrut(30));

            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(statusBar, BorderLayout.NORTH);
            getContentPane().add(mainPanel, BorderLayout.CENTER);
        }

        private void saveUserToCSV(String nome, String email, String dataNascimento, String password, String userType) {
            try {
                File file = new File("C:\\Users\\Lepon\\Downloads\\progarden\\cadastros.csv");
                file.getParentFile().mkdirs();
                FileWriter writer = new FileWriter(file, true);
                writer.append(nome);
                writer.append(",");
                writer.append(email);
                writer.append(",");
                writer.append(dataNascimento);
                writer.append(",");
                writer.append(password);
                writer.append(",");
                writer.append(userType);
                writer.append("\n");
                writer.close();
                JOptionPane.showMessageDialog(this, "Cadastro salvo com sucesso!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar o cadastro.", "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}
