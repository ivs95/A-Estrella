package practica1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConfiguracion {

	private JFrame frame;
	private JTextField text_numFilas;
	private JTextField text_numColumnas;
	private JTextField text_filaInicio;
	private JTextField text_columnaInicio;
	private JTextField text_filaMeta;
	private JTextField text_columnaMeta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConfiguracion window = new VentanaConfiguracion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaConfiguracion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 389, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 653, 424);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		text_numFilas = new JTextField();
		text_numFilas.setBounds(178, 86, 86, 20);
		panel.add(text_numFilas);
		text_numFilas.setColumns(10);

		text_numColumnas = new JTextField();
		text_numColumnas.setColumns(10);
		text_numColumnas.setBounds(178, 117, 86, 20);
		panel.add(text_numColumnas);

		JLabel lblNmeroDeFilas = new JLabel("N\u00FAmero de filas");
		lblNmeroDeFilas.setBounds(47, 89, 95, 14);
		panel.add(lblNmeroDeFilas);

		JLabel lblNmeroDeColumnas = new JLabel("N\u00FAmero de columnas");
		lblNmeroDeColumnas.setBounds(47, 120, 121, 14);
		panel.add(lblNmeroDeColumnas);

		text_filaInicio = new JTextField();
		text_filaInicio.setColumns(10);
		text_filaInicio.setBounds(178, 148, 86, 20);
		panel.add(text_filaInicio);

		text_columnaInicio = new JTextField();
		text_columnaInicio.setColumns(10);
		text_columnaInicio.setBounds(178, 179, 86, 20);
		panel.add(text_columnaInicio);

		JLabel lblColumnaInicial = new JLabel("Fila inicial");
		lblColumnaInicial.setBounds(47, 151, 121, 14);
		panel.add(lblColumnaInicial);

		JLabel lblColumnaInicial_1 = new JLabel("Columna inicial");
		lblColumnaInicial_1.setBounds(47, 182, 121, 14);
		panel.add(lblColumnaInicial_1);

		JLabel lblFilaMeta = new JLabel("Fila meta");
		lblFilaMeta.setBounds(47, 217, 121, 14);
		panel.add(lblFilaMeta);

		JLabel lblColumnaMeta = new JLabel("Columna meta");
		lblColumnaMeta.setBounds(47, 253, 121, 14);
		panel.add(lblColumnaMeta);

		text_filaMeta = new JTextField();
		text_filaMeta.setColumns(10);
		text_filaMeta.setBounds(178, 214, 86, 20);
		panel.add(text_filaMeta);

		text_columnaMeta = new JTextField();
		text_columnaMeta.setColumns(10);
		text_columnaMeta.setBounds(178, 250, 86, 20);
		panel.add(text_columnaMeta);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numFilas, numColumnas, filaIni, columnaIni, filaMeta, columnaMeta;
				numFilas = Integer.valueOf(text_numFilas.getText());
				numColumnas = Integer.valueOf(text_numColumnas.getText());
				filaIni = Integer.valueOf(text_filaInicio.getText());
				columnaIni = Integer.valueOf(text_columnaInicio.getText());
				filaMeta = Integer.valueOf(text_filaMeta.getText());
				columnaMeta = Integer.valueOf(text_columnaMeta.getText());
				if (columnaIni <= numColumnas && columnaIni >= 0 && filaIni <= numFilas && filaIni >= 0
						&& columnaMeta <= numColumnas && columnaMeta >= 0 && filaMeta <= numFilas && filaMeta >= 0
						&& (filaIni != filaMeta || columnaIni != columnaMeta)) {
					@SuppressWarnings("unused")
					VentanaSimulacion vc = new VentanaSimulacion(numFilas, numColumnas, filaIni, columnaIni, filaMeta,
							columnaMeta);
				} else {
					JOptionPane.showMessageDialog(panel.getParent(), "Los parámetros de configuración no son correctos");
				}

			}
		});
		btnOK.setBounds(267, 390, 89, 23);
		panel.add(btnOK);
	}
}
