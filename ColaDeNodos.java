package practica1;

import java.util.Comparator;
import java.util.PriorityQueue;

@SuppressWarnings("serial")
public class ColaDeNodos extends PriorityQueue<Casilla> {

	private Casilla meta;

	public ColaDeNodos(Casilla meta) {
		super(new Comparator<>() {

			@Override
			public int compare(Casilla arg0, Casilla arg1) {
				return Double.compare(arg0.getDistMeta(meta), arg1.getDistMeta(meta));
			}

		});
		this.meta = meta;
	}

	public void inserta(Casilla c) {

		if (this.contains(c)) {
			ColaDeNodos aux = new ColaDeNodos(meta);
			for (Casilla d : this) {
				aux.add(d);
			}
			while (!aux.peek().equals(c)) {
				aux.remove();
			}
			Casilla auxiliar = aux.remove();
			if (c.getDistCamino() < auxiliar.getDistCamino()) {
				this.remove(auxiliar);
				this.add(c);
			}
		} else {
			this.add(c);
		}
	}

}
