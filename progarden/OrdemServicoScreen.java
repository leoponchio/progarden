import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

public class OrdemServicoScreen extends JFrame {

    private JTextField dataHoraField;
    private JTextField enderecoField;
    private JComboBox<String> servicoComboBox;
    private JComboBox<String> profissionalComboBox;

    public OrdemServicoScreen() {
        setTitle("Ordem de Serviço - ProGarden");
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

        JLabel titleLabel = new JLabel("Ordem de Serviço", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));

        String[] profissionais = {"Profissional 1", "Profissional 2", "Profissional 3"};
        profissionalComboBox = new JComboBox<>(profissionais);
        profissionalComboBox.setMaximumSize(new Dimension(300, 40));
        profissionalComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(profissionalComboBox);
        mainPanel.add(Box.createVerticalStrut(20));

        String[] servicos = {"Manutenção", "Controle de Pragas", "Montagem de Jardim", "Limpeza"};
        servicoComboBox = new JComboBox<>(servicos);
        servicoComboBox.setMaximumSize(new Dimension(300, 40));
        servicoComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(servicoComboBox);
        mainPanel.add(Box.createVerticalStrut(20));

        dataHoraField = new JTextField("Data e Hora");
        enderecoField = new JTextField("Endereço");

        JTextField[] fields = {dataHoraField, enderecoField};
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

        JButton cadastrarButton = new JButton("Cadastrar Ordem de Serviço");
        cadastrarButton.setBackground(new Color(157, 204, 57));
        cadastrarButton.setForeground(Color.WHITE);
        cadastrarButton.setFont(new Font("Arial", Font.BOLD, 16));
        cadastrarButton.setMaximumSize(new Dimension(300, 40));
        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dataHora = dataHoraField.getText();
                String endereco = enderecoField.getText();
                String servico = (String) servicoComboBox.getSelectedItem();
                String profissional = (String) profissionalComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(null, "Ordem de Serviço cadastrada com sucesso!\n"
                        + "Profissional: " + profissional + "\n"
                        + "Data e Hora: " + dataHora + "\n"
                        + "Serviço: " + servico + "\n"
                        + "Endereço: " + endereco);
            }
        });

        mainPanel.add(cadastrarButton);
        mainPanel.add(Box.createVerticalStrut(30));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(statusBar, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrdemServicoScreen ordemServicoScreen = new OrdemServicoScreen();
            ordemServicoScreen.setVisible(true);
        });
    }
}
