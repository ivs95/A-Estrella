package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Casilla {

	private int fila;
	private int columna;
	private int numColumnas;
	private int numFilas;
	private double distCamino;
	private Casilla padre;

	public Casilla(int fila, int columna, int numColumnas, int numFilas) {
		this.fila = fila;
		this.columna = columna;
		this.numColumnas = numColumnas;
		this.numFilas = numFilas;
		this.distCamino = 0;
	}

	public Casilla(int fila2, int columna2, Casilla padre) {
		this.fila = fila2;
		this.columna = columna2;
		this.padre = padre;
		this.numColumnas = padre.getNumColumnas();
		this.numFilas = padre.getNumFilas();
		this.distCamino = padre.getDistCamino() + getDistMeta(padre.getFila(), padre.getColumna());
	}

	private int getNumFilas() {
		return this.numFilas;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	private int getNumColumnas() {
		// TODO Auto-generated method stub
		return this.numColumnas;
	}

	public List<Casilla> expande() {
		List<Casilla> casillasAExpandir = new ArrayList<Casilla>();
		if (fila > 0) {
			Casilla c = new Casilla(this.fila - 1, this.columna, this);
			casillasAExpandir.add(c);
			if (columna > 0) {
				c = new Casilla(this.fila - 1, this.columna - 1, this);
				casillasAExpandir.add(c);
			}
			if (columna < numColumnas - 1) {
				c = new Casilla(this.fila - 1, this.columna + 1, this);
				casillasAExpandir.add(c);
			}
		}
		if (columna > 0) {
			Casilla c = new Casilla(this.fila, this.columna - 1, this);
			casillasAExpandir.add(c);
			if (fila < numFilas - 1) {
				c = new Casilla(this.fila + 1, this.columna - 1, this);
				casillasAExpandir.add(c);
			}
		}

		if (fila < numFilas - 1) {
			Casilla c = new Casilla(this.fila + 1, this.columna, this);
			casillasAExpandir.add(c);
			if (columna < numColumnas - 1) {
				c = new Casilla(this.fila + 1, this.columna + 1, this);
				casillasAExpandir.add(c);
			}
		}
		if (columna < numColumnas - 1) {
			Casilla c = new Casilla(this.fila, this.columna + 1, this);
			casillasAExpandir.add(c);
		}

		return casillasAExpandir;
	}

	public double getDistMeta(int filaMeta, int columnaMeta) {

		double distMeta = Math.sqrt((Math.pow(fila - filaMeta, 2) + Math.pow(columna - columnaMeta, 2)));

		return distMeta;

	}

	public Stack<Casilla> getRuta() {
		Stack<Casilla> ruta = new Stack<Casilla>();

		if (this.padre != null)
			ruta = padre.getRuta();
		ruta.push(this);

		return ruta;
	}

	public double distPadre() {
		double distPadre = Math.sqrt((Math.pow(fila - padre.getFila(), 2) + Math.pow(columna - padre.getColumna(), 2)));

		return distPadre;
	}

	public int getPosicionEnTablero() {
		return this.fila * this.numColumnas + this.columna;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Casilla) {
			Casilla otra = (Casilla) o;
			if (otra.getFila() == this.getFila() && otra.getColumna() == this.getColumna())
				return true;
			else
				return false;
		} else
			return false;

	}

	public double getDistCamino() {
		return distCamino;
	}

	public void setDistCamino(double distCamino) {
		this.distCamino = distCamino;
	}

	public String toString() {
		return this.fila + " " + this.columna;
	}

	public double getDistMeta(Casilla meta) {
		double distMeta = Math.sqrt((Math.pow(fila - meta.getFila(), 2) + Math.pow(columna - meta.getColumna(), 2)));

		return distMeta;
	}
}
