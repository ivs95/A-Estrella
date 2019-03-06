package practica1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.awt.event.ActionEvent;

public class VentanaSimulacion {

	private JFrame frame;
	private int numFilas = 1;
	private int numColumnas = 0;
	private int filaIni;
	private int columnaIni;
	private int filaMeta;
	private int columnaMeta;
	final private int HBOARD = 804;
	final private int VBOARD = 574;
	private Estado estado;
	private static int numWaypoints;
	private static JPanel panel;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSimulacion window = new VentanaSimulacion(10, 5, 1, 1, 1, 1);
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
	public VentanaSimulacion(int numFilas, int numColumnas, int filaIni, int columnaIni, int filaMeta,
			int columnaMeta) {
		VentanaSimulacion.numWaypoints = 1;
		this.numFilas = numFilas;
		this.numColumnas = numColumnas;
		this.filaIni = filaIni - 1;
		this.columnaIni = columnaIni - 1;
		this.filaMeta = filaMeta - 1;
		this.columnaMeta = columnaMeta - 1;
		this.estado = Estado.Libre;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 820, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 820, 27);
		frame.getContentPane().add(toolBar);

		JButton btnInicioSimulacion = new JButton("");
		btnInicioSimulacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Simulador.inicia(numFilas, numColumnas, filaIni, columnaIni, filaMeta, columnaMeta);
			}
		});
		btnInicioSimulacion.setSize(new Dimension(44, 18));
		ImageIcon iconoPlayOriginal = new ImageIcon(VentanaSimulacion.class.getResource("/practica1/play.png"));
		Image imgPlay = iconoPlayOriginal.getImage();
		Image imgPlay2 = imgPlay.getScaledInstance(btnInicioSimulacion.getWidth(), btnInicioSimulacion.getHeight(),
				java.awt.Image.SCALE_AREA_AVERAGING);
		iconoPlayOriginal = new ImageIcon(imgPlay2);
		btnInicioSimulacion.setIcon(iconoPlayOriginal);
		toolBar.add(btnInicioSimulacion);

		JButton btnAñadeProhibido = new JButton("");

		panel = new JPanel();
		panel.setBounds(0, 27, 804, 574);

		frame.getContentPane().add(panel);
		GridLayout gr = new GridLayout();
		gr.setColumns(this.numColumnas);
		gr.setRows(this.numFilas);
		panel.setLayout(gr);
		for (int i = 0; i < numColumnas * numFilas; i++) {
			JButtonSimulador jb = new JButtonSimulador(numFilas, numColumnas, HBOARD, VBOARD, i);
			jb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					jb.ponSeñal(numWaypoints, estado);
				}

			});
			panel.add(jb);
		}
		JButton jb = (JButton) panel.getComponent((filaIni) * numColumnas + columnaIni);
		ImageIcon iconoIni = new ImageIcon(VentanaSimulacion.class.getResource("/practica1/ini.png"));
		Image imgIni = iconoIni.getImage();
		Image imIni2 = imgIni.getScaledInstance(HBOARD / numColumnas, VBOARD / numFilas,
				java.awt.Image.SCALE_AREA_AVERAGING);
		iconoIni = new ImageIcon(imIni2);
		jb.setIcon(iconoIni);
		jb = (JButton) panel.getComponent((filaMeta) * numColumnas + columnaMeta);
		ImageIcon iconoMeta = new ImageIcon(VentanaSimulacion.class.getResource("/practica1/meta.png"));
		Image imgMeta = iconoMeta.getImage();
		Image imgMeta2 = imgMeta.getScaledInstance(HBOARD / numColumnas, VBOARD / numFilas,
				java.awt.Image.SCALE_AREA_AVERAGING);
		iconoMeta = new ImageIcon(imgMeta2);
		jb.setIcon(iconoMeta);
		JButton btnAñadeWaypoint = new JButton("");
		btnAñadeWaypoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estado = Estado.Waypoint;
			}
		});

		btnAñadeProhibido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estado = Estado.Prohibido;
			}
		});
		btnAñadeProhibido.setSize(new Dimension(44, 18));
		ImageIcon iconoProhibido = new ImageIcon(VentanaSimulacion.class.getResource("/practica1/prohibido.png"));
		Image imgProhibido = iconoProhibido.getImage();
		Image imgProhibido2 = imgProhibido.getScaledInstance(btnAñadeProhibido.getWidth(),
				btnAñadeProhibido.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
		iconoProhibido = new ImageIcon(imgProhibido2);
		btnAñadeProhibido.setIcon(iconoProhibido);

		toolBar.add(btnAñadeProhibido);

		btnAñadeWaypoint.setSize(new Dimension(44, 18));
		ImageIcon iconoWaypoint = new ImageIcon(VentanaSimulacion.class.getResource("/practica1/waypoint.png"));
		Image imgWaypoint = iconoWaypoint.getImage();
		Image imgWaypoint2 = imgWaypoint.getScaledInstance(btnAñadeWaypoint.getWidth(), btnAñadeWaypoint.getHeight(),
				java.awt.Image.SCALE_AREA_AVERAGING);
		iconoWaypoint = new ImageIcon(imgWaypoint2);
		btnAñadeWaypoint.setIcon(iconoWaypoint);

		toolBar.add(btnAñadeWaypoint);
	}

	public static void incrementWaypoint() {
		numWaypoints++;
	}

	public static void pinta(Stack<Casilla> ruta) {
		
		while (!ruta.isEmpty()) {
			Casilla act = ruta.pop();
			panel.getComponent(act.getPosicionEnTablero()).setBackground(Color.GREEN);
		}
		
	}

	public static void metaImposible() {
		JOptionPane.showMessageDialog(panel.getParent(), "No es posible alcanzar la meta");
		
	}
}
