import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RelatorioProgarden extends JFrame {
    private JTextField nomeClienteField;
    private JTextField nomeProfissionalField;
    private JComboBox<String> tipoServicoComboBox;
    private JTextField dataField;
    private JTextField horaField;
    private JTextField localField;
    private JTextField valorField;
    private JTextArea relatorioArea;

    public RelatorioProgarden() {
        setTitle("Relatório de Jardinagem - ProGarden");
        setSize(390, 844);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        // Status Bar
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

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        iconPanel.setBackground(Color.WHITE);
        statusBar.add(timeLabel, BorderLayout.WEST);
        statusBar.add(iconPanel, BorderLayout.EAST);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Logo
        ImageIcon logoIcon = new ImageIcon("C:/Users/Lepon/Downloads/progarden/Marca ProGarden.jpg");
        Image img = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(img);
        JLabel logoLabel = new JLabel(logoIcon, JLabel.CENTER);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Input Fields with FocusListener for Placeholder Text
        nomeClienteField = new JTextField("Nome do Cliente");
        nomeProfissionalField = new JTextField("Nome do Profissional");
        tipoServicoComboBox = new JComboBox<>(new String[]{"Manutenção", "Controle de Pragas", "Montagem de Jardim", "Limpeza"});
        dataField = new JTextField("Data (dd/mm/yyyy)");
        horaField = new JTextField("Hora");
        localField = new JTextField("Local");
        valorField = new JTextField("Valor");

        JTextField[] fields = {nomeClienteField, nomeProfissionalField, dataField, horaField, localField, valorField};
        
        for (JTextField field : fields) {
            String placeholder = field.getText();
            field.setFont(new Font("Arial", Font.PLAIN, 14));
            field.setMaximumSize(new Dimension(300, 40));
            field.setAlignmentX(Component.CENTER_ALIGNMENT);
            field.setForeground(Color.GRAY);
            field.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (field.getText().equals(placeholder)) {
                        field.setText("");
                        field.setForeground(Color.BLACK);
                    }
                }
                @Override
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

        tipoServicoComboBox.setMaximumSize(new Dimension(300, 40));
        tipoServicoComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(tipoServicoComboBox);
        mainPanel.add(Box.createVerticalStrut(20));

        // Generate Report Button
        JButton gerarRelatorioButton = new JButton("Gerar Relatório");
        gerarRelatorioButton.setBackground(new Color(157, 204, 57));
        gerarRelatorioButton.setForeground(Color.WHITE);
        gerarRelatorioButton.setFont(new Font("Arial", Font.BOLD, 16));
        gerarRelatorioButton.setMaximumSize(new Dimension(300, 40));
        gerarRelatorioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        gerarRelatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });

        mainPanel.add(gerarRelatorioButton);
        mainPanel.add(Box.createVerticalStrut(30));

        // Report Area
        relatorioArea = new JTextArea();
        relatorioArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(relatorioArea);
        scrollPane.setPreferredSize(new Dimension(350, 300));
        mainPanel.add(scrollPane);

        // Add Panels to Frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(statusBar, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void gerarRelatorio() {
        String nomeCliente = nomeClienteField.getText();
        String nomeProfissional = nomeProfissionalField.getText();
        String tipoServico = (String) tipoServicoComboBox.getSelectedItem();
        String data = dataField.getText();
        String hora = horaField.getText();
        String local = localField.getText();
        String valor = valorField.getText();

        String relatorio = String.format(
                "Relatório de Jardinagem - ProGarden\n\n" +
                "Nome do Cliente: %s\n" +
                "Nome do Profissional: %s\n" +
                "Tipo de Serviço: %s\n" +
                "Data do Serviço: %s\n" +
                "Hora do Serviço: %s\n" +
                "Local do Serviço: %s\n" +
                "Valor do Serviço: %s\n",
                nomeCliente, nomeProfissional, tipoServico, data, hora, local, valor
        );

        relatorioArea.setText(relatorio);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RelatorioProgarden frame = new RelatorioProgarden();
            frame.setVisible(true);
        });
    }
}
