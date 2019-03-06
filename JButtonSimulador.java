package practica1;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class JButtonSimulador extends JButton {

	private boolean waypoint = false;
	private boolean prohibido = false;
	private int numFilas;
	private int numColumnas;
	private int largo;
	private int ancho;
	private int pos;

	public JButtonSimulador(int numFilas, int numColumnas, int largo, int ancho, int pos) {
		super();
		this.numFilas = numFilas;
		this.numColumnas = numColumnas;
		this.largo = largo;
		this.ancho = ancho;
		this.pos = pos;

	}

	public void ponSeñal(int orden, Estado estado) {
		switch (estado) {
		case Waypoint:
			ImageIcon iconoWaypoint = new ImageIcon(VentanaSimulacion.class.getResource("/practica1/waypoint.png"));
			Image imgWaypoint = iconoWaypoint.getImage();
			Image imgWaypoint2 = imgWaypoint.getScaledInstance(largo / numColumnas, ancho / numFilas,
					java.awt.Image.SCALE_AREA_AVERAGING);
			iconoWaypoint = new ImageIcon(imgWaypoint2);
			this.setIcon(iconoWaypoint);
			this.waypoint = true;
			Simulador.addWaypoint(pos / numFilas, pos % numFilas, orden);
			VentanaSimulacion.incrementWaypoint();
			if (this.prohibido) {
				Simulador.eliminaProhibido(pos / numFilas, pos % numFilas);
				this.prohibido = false;
			}
			break;
		case Prohibido:
			if (!waypoint) {

				Simulador.addProhibido(pos / numFilas, pos % numFilas);
				ImageIcon iconoProhibido = new ImageIcon(
						VentanaSimulacion.class.getResource("/practica1/prohibido.png"));
				Image imgProhibido2 = iconoProhibido.getImage();
				Image imProhibido2 = imgProhibido2.getScaledInstance(largo / numColumnas, ancho / numFilas,
						java.awt.Image.SCALE_AREA_AVERAGING);
				iconoProhibido = new ImageIcon(imProhibido2);
				this.setIcon(iconoProhibido);
				this.prohibido = true;

			}
			break;
		default:
			break;
		}
	}

	public void setWaypoint(boolean waypoint) {
		this.waypoint = waypoint;
	}

	public boolean getWaypoint() {
		return waypoint;
	}

	public void setProhibido(boolean prohibido) {
		this.prohibido = prohibido;

	}

	public boolean getProhibido() {
		return waypoint;
	}

}
