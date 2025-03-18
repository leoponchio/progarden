import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AvaliarProfissional extends JFrame {

    private JComboBox<String> profissionaisComboBox;
    private JSlider notaSlider;

    public AvaliarProfissional() {
        setTitle("Avaliar Profissional - ProGarden");
        setSize(390, 844);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        // Barra de status com ícones e hora
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

        // Carregar ícones de sinal, internet e bateria
        JLabel signalLabel = loadIcon("C:/Users/Lepon/Downloads/progarden/2313339.png");
        JLabel wifiLabel = loadIcon("C:/Users/Lepon/Downloads/progarden/png-transparent-computer-icons-wi-fi-signal-wifi-miscellaneous-logo-black-thumbnail.png");
        JLabel batteryLabel = loadIcon("C:/Users/Lepon/Downloads/progarden/png-transparent-computer-icons-battery-iphone-choose-the-color-battery-electronics-rectangle-black-thumbnail.png");

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        iconPanel.setBackground(Color.WHITE);
        iconPanel.add(wifiLabel);
        iconPanel.add(signalLabel);
        iconPanel.add(batteryLabel);

        statusBar.add(timeLabel, BorderLayout.WEST);
        statusBar.add(iconPanel, BorderLayout.EAST);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Logo ProGarden
        ImageIcon logoIcon = new ImageIcon("C:/Users/Lepon/Downloads/progarden/Marca ProGarden.jpg");
        Image img = logoIcon.getImage();
        Image resizedLogo = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedLogo);

        JLabel logoLabel = new JLabel(logoIcon, JLabel.CENTER);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createVerticalStrut(30));

        JLabel welcomeLabel = new JLabel("Avaliar Profissional", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(welcomeLabel);
        mainPanel.add(Box.createVerticalStrut(30));

        // Combo box para escolher o profissional
        String[] profissionais = { "Profissional 1", "Profissional 2", "Profissional 3", "Profissional 4" };
        profissionaisComboBox = new JComboBox<>(profissionais);
        profissionaisComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        profissionaisComboBox.setMaximumSize(new Dimension(300, 40));
        profissionaisComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(profissionaisComboBox);
        mainPanel.add(Box.createVerticalStrut(30));

        // Slider para avaliar de 1 a 5
        JLabel notaLabel = new JLabel("Avaliação (1 a 5):", JLabel.CENTER);
        notaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        notaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(notaLabel);
        mainPanel.add(Box.createVerticalStrut(10));

        notaSlider = new JSlider(1, 5, 3); // Valor inicial 3
        notaSlider.setMajorTickSpacing(1);
        notaSlider.setPaintTicks(true);
        notaSlider.setPaintLabels(true);
        notaSlider.setMaximumSize(new Dimension(300, 50));
        mainPanel.add(notaSlider);
        mainPanel.add(Box.createVerticalStrut(30));

        // Botão de enviar avaliação
        JButton enviarButton = new JButton("Enviar Avaliação");
        enviarButton.setBackground(new Color(157, 204, 57));
        enviarButton.setForeground(Color.WHITE);
        enviarButton.setFont(new Font("Arial", Font.BOLD, 16));
        enviarButton.setMaximumSize(new Dimension(300, 40));
        enviarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enviarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String profissionalSelecionado = (String) profissionaisComboBox.getSelectedItem();
                int nota = notaSlider.getValue();
                JOptionPane.showMessageDialog(null, "Você avaliou o " + profissionalSelecionado + " com a nota: " + nota);
            }
        });

        mainPanel.add(enviarButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(statusBar, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    // Método para carregar ícones com verificação
    private JLabel loadIcon(String filePath) {
        ImageIcon icon = new ImageIcon(filePath);
        if (icon.getIconWidth() == -1) {
            System.out.println("Erro ao carregar o ícone: " + filePath);
            return new JLabel(); // Retorna um JLabel vazio em caso de erro
        }
        return new JLabel(icon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AvaliarProfissional avaliarScreen = new AvaliarProfissional();
            avaliarScreen.setVisible(true);
        });
    }
}
