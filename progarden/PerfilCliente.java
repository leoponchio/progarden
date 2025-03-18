import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class PerfilCliente extends JFrame {

    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField telefoneField;

    public PerfilCliente() {
        setTitle("Perfil do Cliente - ProGarden");
        setSize(390, 844);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        //------------------------------------------------
        // Barra de Status com Ícones e Hora
        //------------------------------------------------
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setPreferredSize(new Dimension(390, 40));
        statusBar.setBackground(Color.WHITE);

        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        timeLabel.setHorizontalAlignment(SwingConstants.LEFT);

        Timer timeTimer = new Timer(1000, e -> {
            timeLabel.setText(new SimpleDateFormat("HH:mm").format(new Date()));
        });
        timeTimer.start();

        // Carrega e redimensiona os ícones
        ImageIcon wifiIcon = loadIcon("wifi.png", 20, 20);
        ImageIcon batteryIcon = loadIcon("battery.png", 20, 20);
        ImageIcon profileIcon = loadIcon("profile.jpg", 100, 100);

        JLabel wifiLabel = new JLabel(wifiIcon);
        JLabel batteryLabel = new JLabel(batteryIcon);

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        iconPanel.setBackground(Color.WHITE);
        iconPanel.add(wifiLabel);
        iconPanel.add(batteryLabel);

        statusBar.add(timeLabel, BorderLayout.WEST);
        statusBar.add(iconPanel, BorderLayout.EAST);

        add(statusBar, BorderLayout.NORTH);

        //------------------------------------------------
        // Painel Principal do Perfil
        //------------------------------------------------
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //------------------------------------------------
        // Campos de Informações do Cliente
        //------------------------------------------------
        nomeField = createPlaceholderField("Nome");
        cpfField = createPlaceholderField("CPF");
        telefoneField = createPlaceholderField("Telefone");

        mainPanel.add(nomeField);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(cpfField);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(telefoneField);
        mainPanel.add(Box.createVerticalStrut(30));

        //------------------------------------------------
        // Botão Salvar na Parte Inferior
        //------------------------------------------------
        JButton saveButton = new JButton("Salvar");
        saveButton.setBackground(new Color(157, 204, 57));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.setMaximumSize(new Dimension(200, 40));
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> saveProfileToCSV());

        mainPanel.add(saveButton);
        mainPanel.add(Box.createVerticalStrut(50));

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    //------------------------------------------------
    // Método para carregar ícone com redimensionamento
    //------------------------------------------------
    private ImageIcon loadIcon(String path, int width, int height) {
        try {
            return new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            System.err.println("Erro ao carregar ícone: " + path);
            return null;
        }
    }

    //------------------------------------------------
    // Método para criar campo com placeholder
    //------------------------------------------------
    private JTextField createPlaceholderField(String placeholder) {
        JTextField field = new JTextField(placeholder);
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
        return field;
    }

    //------------------------------------------------
    // Método para salvar dados do perfil em CSV
    //------------------------------------------------
    private void saveProfileToCSV() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String telefone = telefoneField.getText();

        if (nome.equals("Nome") || cpf.equals("CPF") || telefone.equals("Telefone")) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            File file = new File("perfil_cliente.csv");
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file, true);
            writer.append(nome).append(",").append(cpf).append(",").append(telefone).append("\n");
            writer.close();
            JOptionPane.showMessageDialog(this, "Perfil salvo com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o perfil.", "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PerfilCliente::new);
    }
}
