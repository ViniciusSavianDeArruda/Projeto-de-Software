import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.EventQueue;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;

public class CadastroGui extends JFrame {

	private static final long serialVersionUID = 1L;
    
	private static final String ARQUIVO = "produtos.txt";
	private static final Color STATUS_OK = new Color(226, 244, 231); 
	private static final Color STATUS_WARN = new Color(255, 243, 205); 
	private static final Color STATUS_TEXT_OK = new Color(18, 84, 33);
	private static final Color STATUS_TEXT_WARN = new Color(133, 100, 4);
	private static final Color STATUS_BG_DEFAULT = new Color(245, 245, 245);

    // componentes da interface
	private JPanel contentPane;      
	private JTextField nomeField;    
	private JTextField codigoField;
	private JTextField descricaoField;
	private JTextField precoField;
	private JTextField quantidadeField;
	private JLabel statusLabel;      

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				CadastroGui frame = new CadastroGui();
				frame.setVisible(true); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public CadastroGui() {
		// configuracao da janela principal
		setTitle("Cadastro de Produto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 640, 360); 
		
		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
		setContentPane(contentPane);

		initComponents();
	}

	// Método responsável por organizar o visual (gerado pelo WindowBuilder)
	private void initComponents() {
		//cabecalho 
		JPanel headerPanel = new JPanel(new BorderLayout());
		JLabel titleLabel = new JLabel("Cadastro de Produto", SwingConstants.CENTER);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		JLabel subtitleLabel = new JLabel("Preencha os dados abaixo", SwingConstants.CENTER);
		subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		headerPanel.add(titleLabel, BorderLayout.NORTH);
		headerPanel.add(subtitleLabel, BorderLayout.SOUTH);
		contentPane.add(headerPanel, BorderLayout.NORTH);

		// formulario (Localizado no centro da janela) 
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBorder(BorderFactory.createTitledBorder("Dados do produto"));
		contentPane.add(formPanel, BorderLayout.CENTER);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 8, 6, 8); 
		gbc.fill = GridBagConstraints.HORIZONTAL; 

		GridBagConstraints gbcNomeLabel = new GridBagConstraints();
		gbcNomeLabel.gridx = 0;
		gbcNomeLabel.gridy = 0;
		gbcNomeLabel.insets = new Insets(6, 8, 6, 8);
		gbcNomeLabel.anchor = GridBagConstraints.EAST;
		gbcNomeLabel.fill = GridBagConstraints.HORIZONTAL;
		formPanel.add(new JLabel("Nome:"), gbcNomeLabel);
		GridBagConstraints gbcNomeField = new GridBagConstraints();
		gbcNomeField.gridx = 1;
		gbcNomeField.gridy = 0;
		gbcNomeField.weightx = 1;
		gbcNomeField.insets = new Insets(6, 8, 6, 8);
		gbcNomeField.anchor = GridBagConstraints.WEST;
		gbcNomeField.fill = GridBagConstraints.HORIZONTAL;
		nomeField = new JTextField(24);
		formPanel.add(nomeField, gbcNomeField);

		GridBagConstraints gbcCodigoLabel = new GridBagConstraints();
		gbcCodigoLabel.gridx = 0;
		gbcCodigoLabel.gridy = 1;
		gbcCodigoLabel.insets = new Insets(6, 8, 6, 8);
		gbcCodigoLabel.anchor = GridBagConstraints.EAST;
		gbcCodigoLabel.fill = GridBagConstraints.HORIZONTAL;
		formPanel.add(new JLabel("Codigo:"), gbcCodigoLabel);
		GridBagConstraints gbcCodigoField = new GridBagConstraints();
		gbcCodigoField.gridx = 1;
		gbcCodigoField.gridy = 1;
		gbcCodigoField.weightx = 1;
		gbcCodigoField.insets = new Insets(6, 8, 6, 8);
		gbcCodigoField.anchor = GridBagConstraints.WEST;
		gbcCodigoField.fill = GridBagConstraints.HORIZONTAL;
		codigoField = new JTextField(24);
		formPanel.add(codigoField, gbcCodigoField);

		GridBagConstraints gbcDescricaoLabel = new GridBagConstraints();
		gbcDescricaoLabel.gridx = 0;
		gbcDescricaoLabel.gridy = 2;
		gbcDescricaoLabel.insets = new Insets(6, 8, 6, 8);
		gbcDescricaoLabel.anchor = GridBagConstraints.EAST;
		gbcDescricaoLabel.fill = GridBagConstraints.HORIZONTAL;
		formPanel.add(new JLabel("Descricao:"), gbcDescricaoLabel);
		GridBagConstraints gbcDescricaoField = new GridBagConstraints();
		gbcDescricaoField.gridx = 1;
		gbcDescricaoField.gridy = 2;
		gbcDescricaoField.weightx = 1;
		gbcDescricaoField.insets = new Insets(6, 8, 6, 8);
		gbcDescricaoField.anchor = GridBagConstraints.WEST;
		gbcDescricaoField.fill = GridBagConstraints.HORIZONTAL;
		descricaoField = new JTextField(24);
		formPanel.add(descricaoField, gbcDescricaoField);

		GridBagConstraints gbcPrecoLabel = new GridBagConstraints();
		gbcPrecoLabel.gridx = 0;
		gbcPrecoLabel.gridy = 3;
		gbcPrecoLabel.insets = new Insets(6, 8, 6, 8);
		gbcPrecoLabel.anchor = GridBagConstraints.EAST;
		gbcPrecoLabel.fill = GridBagConstraints.HORIZONTAL;
		formPanel.add(new JLabel("Preco:"), gbcPrecoLabel);
		GridBagConstraints gbcPrecoField = new GridBagConstraints();
		gbcPrecoField.gridx = 1;
		gbcPrecoField.gridy = 3;
		gbcPrecoField.weightx = 1;
		gbcPrecoField.insets = new Insets(6, 8, 6, 8);
		gbcPrecoField.anchor = GridBagConstraints.WEST;
		gbcPrecoField.fill = GridBagConstraints.HORIZONTAL;
		precoField = new JTextField(10);
		formPanel.add(precoField, gbcPrecoField);

		GridBagConstraints gbcQuantidadeLabel = new GridBagConstraints();
		gbcQuantidadeLabel.gridx = 0;
		gbcQuantidadeLabel.gridy = 4;
		gbcQuantidadeLabel.insets = new Insets(6, 8, 6, 8);
		gbcQuantidadeLabel.anchor = GridBagConstraints.EAST;
		gbcQuantidadeLabel.fill = GridBagConstraints.HORIZONTAL;
		formPanel.add(new JLabel("Quantidade:"), gbcQuantidadeLabel);
		GridBagConstraints gbcQuantidadeField = new GridBagConstraints();
		gbcQuantidadeField.gridx = 1;
		gbcQuantidadeField.gridy = 4;
		gbcQuantidadeField.weightx = 1;
		gbcQuantidadeField.insets = new Insets(6, 8, 6, 8);
		gbcQuantidadeField.anchor = GridBagConstraints.WEST;
		gbcQuantidadeField.fill = GridBagConstraints.HORIZONTAL;
		quantidadeField = new JTextField(10);
		formPanel.add(quantidadeField, gbcQuantidadeField);

		// --- rodape (Localizado no SUL da janela) ---
		JPanel bottomPanel = new JPanel(new BorderLayout(8, 8));
		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		// barra de status: Exibe feedbacks visuais coloridos no rodape
		statusLabel = new JLabel(" ");
		statusLabel.setOpaque(true); 
		statusLabel.setBackground(STATUS_BG_DEFAULT);
		statusLabel.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
		bottomPanel.add(statusLabel, BorderLayout.CENTER);

		// painel de botoes: Organizados da direita para a esquerda 
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
		JButton limparButton = new JButton("Limpar");
		JButton cancelarButton = new JButton("Cancelar");
		JButton salvarButton = new JButton("Salvar");

		//Vinculam o clique do usuário às funçoes do código
		limparButton.addActionListener(e -> limparCampos());
		cancelarButton.addActionListener(e -> dispose()); // Fecha a janela atual
		salvarButton.addActionListener(e -> salvarProduto());

		buttonPanel.add(limparButton);
		buttonPanel.add(cancelarButton);
		buttonPanel.add(salvarButton);
		bottomPanel.add(buttonPanel, BorderLayout.EAST);
	}

	private void setStatus(String message, Color background, Color foreground) {
		statusLabel.setText(message);
		statusLabel.setBackground(background);
		statusLabel.setForeground(foreground);
	}
	
	private void salvarProduto() {
		String nome = nomeField.getText().trim();
		String codigo = codigoField.getText().trim();
		String descricao = descricaoField.getText().trim();
		String precoTexto = precoField.getText().trim();
		String quantidadeTexto = quantidadeField.getText().trim();

		if (nome.isEmpty() || codigo.isEmpty() || descricao.isEmpty()
				|| precoTexto.isEmpty() || quantidadeTexto.isEmpty()) {
			setStatus("Preencha todos os campos.", STATUS_WARN, STATUS_TEXT_WARN);
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
			return;
		}

		double preco;
		int quantidade;
		try {
			preco = Double.parseDouble(precoTexto.replace(',', '.'));
			quantidade = Integer.parseInt(quantidadeTexto);
		} catch (NumberFormatException ex) {
			setStatus("Preco ou quantidade invalida.", STATUS_WARN, STATUS_TEXT_WARN);
			JOptionPane.showMessageDialog(this, "Preco ou quantidade invalida.");
			return;
		}

		Produto produto = new Produto(nome, codigo, descricao, preco, quantidade);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
			bw.write(produto.toString()); 
			bw.newLine(); 
			
			setStatus("Produto salvo: " + produto.getCodigo(), STATUS_OK, STATUS_TEXT_OK);
			JOptionPane.showMessageDialog(this, "Salvo com sucesso.");
			limparCampos(); 
		} catch (IOException ex) {
			setStatus("Erro ao gravar: " + ex.getMessage(), STATUS_WARN, STATUS_TEXT_WARN);
			JOptionPane.showMessageDialog(this, "Erro ao gravar arquivo.");
		}
	}

	private void limparCampos() {
		nomeField.setText("");
		codigoField.setText("");
		descricaoField.setText("");
		precoField.setText("");
		quantidadeField.setText("");
		nomeField.requestFocus(); // Devolve o cursor para o campo "Nome"
		setStatus(" ", STATUS_BG_DEFAULT, getForeground());
	}
}