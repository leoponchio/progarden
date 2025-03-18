import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HistoricoOrdemServicoScreen extends JFrame {

    public HistoricoOrdemServicoScreen() {
        setTitle("Histórico de Ordens de Serviço - ProGarden");
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

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        ImageIcon logoIcon = new ImageIcon("C:/Users/Lepon/Downloads/progarden/Marca ProGarden.jpg");
        Image img = logoIcon.getImage();
        Image resizedLogo = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedLogo);

        JLabel logoLabel = new JLabel(logoIcon, JLabel.LEFT);
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(Color.WHITE);
        logoPanel.add(logoLabel);

        JLabel titleLabel = new JLabel("Histórico de Ordens de Serviço", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(Box.createVerticalStrut(20));
        headerPanel.add(logoPanel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(20));

        String[] columnNames = {"Data e Hora", "Endereço", "Serviço", "Profissional"};
        Object[][] data = {
            {"10/10/2024 10:00", "Rua A, 123", "Manutenção", "Profissional 1"},
            {"08/10/2024 17:30", "Av. B, 456", "Limpeza", "Profissional 2"},
            {"01/10/2024 18:00", "Rua C, 493", "Manutenção", "Profissional 1"},
            {"12/10/2024 15:30", "Av. L, 467", "Limpeza", "Profissional 2"},
            {"15/10/2024 09:00", "Praça C, 79", "Montagem de Jardim", "Profissional 3"},
            {"16/10/2024 08:00", "Praça E, 987", "Montagem de Jardim", "Profissional 3"},
    };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(360, 400));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String dataHora = tableModel.getValueAt(selectedRow, 0).toString();
                    String endereco = tableModel.getValueAt(selectedRow, 1).toString();
                    String servico = tableModel.getValueAt(selectedRow, 2).toString();
                    String profissional = tableModel.getValueAt(selectedRow, 3).toString();
                    
                    JOptionPane.showMessageDialog(null, "Detalhes da Ordem de Serviço:\n"
                            + "Data e Hora: " + dataHora + "\n"
                            + "Endereço: " + endereco + "\n"
                            + "Serviço: " + servico + "\n"
                            + "Profissional: " + profissional);
                }
            }
        });

        JButton backButton = new JButton("Voltar");
        backButton.setBackground(new Color(157, 204, 57));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setMaximumSize(new Dimension(300, 40));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(backButton);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(statusBar, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HistoricoOrdemServicoScreen historicoScreen = new HistoricoOrdemServicoScreen();
            historicoScreen.setVisible(true);
        });
    }
}
