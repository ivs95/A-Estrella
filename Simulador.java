package practica1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Simulador {

	private static HashMap<Integer, Casilla> waypoints = new HashMap<Integer, Casilla>();
	private static List<Casilla> prohibidos = new ArrayList<Casilla>();
	private static Casilla inicio;
	private static Casilla meta;
	private static int filas;
	private static int columnas;
	private static int numWaypoints = 0;

	public static void inicia(int numFilas, int numColumnas, int filaIni, int columnaIni, int filaMeta,
			int columnaMeta) {
		int contadorWaypoints = 1;
		boolean finWaypoints = true;
		filas = numFilas;
		columnas = numColumnas;
		inicio = new Casilla(filaIni, columnaIni, columnas, filas);
		meta = new Casilla(filaMeta, columnaMeta, columnas, filas);
		if (numWaypoints != 0) {
			meta = waypoints.get(contadorWaypoints);
			waypoints.remove(contadorWaypoints);
			contadorWaypoints++;
			finWaypoints = false;
		}

		ColaDeNodos abierta = new ColaDeNodos(meta);
		List<Casilla> cerrada = new ArrayList<>();
		boolean exito = false, error = false;
		abierta.add(inicio);

		while (!exito && !error) {
			Casilla actual = abierta.poll();
			cerrada.add(actual);
			if (actual.equals(meta)) {
				abierta = new ColaDeNodos(meta);
				cerrada = new ArrayList<>();
				abierta.add(actual);
				if (!finWaypoints) {
					if (!waypoints.isEmpty()) {
						meta = waypoints.get(contadorWaypoints);
						waypoints.remove(contadorWaypoints);
						contadorWaypoints++;
					} else {
						meta = new Casilla(filaMeta, columnaMeta, columnas, filas);
						finWaypoints = true;
					}
				} else {
					VentanaSimulacion.pinta(actual.getRuta());
					exito = true;
				}
			} else {
				List<Casilla> posibles = actual.expande();
				for (Casilla c : posibles) {
					if (!cerrada.contains(c) && !prohibidos.contains(c)) {
						abierta.inserta(c);
					}
				}
				if (abierta.isEmpty()) {
					error = true;
				}
			}
		}
		if (error)
			VentanaSimulacion.metaImposible();

	}

	public static void addWaypoint(int fila, int columna, int orden) {
		waypoints.put(orden, new Casilla(fila, columna, columnas, filas));
		setNumWaypoints(orden);

	}

	public static void addProhibido(int fila, int columna) {
		prohibidos.add(new Casilla(fila, columna, columnas, filas));
	}

	public static void eliminaProhibido(int fila, int columna) {
		prohibidos.remove(new Casilla(fila, columna, columnas, filas));
	}

	public static int getNumWaypoints() {
		return numWaypoints;
	}

	public static void setNumWaypoints(int numWaypoints) {
		Simulador.numWaypoints = numWaypoints;
	}

}
