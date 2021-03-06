package gramatica; 
import java.util.Stack;

@SuppressWarnings("rawtypes")
public class Atributo<Valor> {

	private Valor valor;
	private Stack<Atributo> dependencias;
	private boolean calculado;
	private ExpSem<Valor> exp;
	private String descripcion;
	
	public Atributo() {
		dependencias = new Stack<Atributo>();
		calculado = true;
		descripcion = "<descripcion de atributo no disponible>";
	}

	public void fijaExpresion(ExpSem<Valor> exp) {
		this.exp = exp;
	}

	public void fijaDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String descripcion() {
		return descripcion;
	}

	public void calcula() {
		this.valor = exp.val();
		calculado = true;
	}

	public Valor val() {
		if (!calculado()) {
			throw new RuntimeException("Atributo no calculado:" + descripcion);
		}
		return valor;
	}

	public Atributo siguienteDependencia() {
		while (!dependencias.isEmpty()) {
			Atributo siguiente = dependencias.pop();
			if (siguiente.calculado())
				return siguiente;
		}
		return null;
	}

	public void ponDependencias(Atributo... as) {
		for (Atributo a : as) {
			dependencias.push(a);
		}
	}

	public boolean calculado() {
		return calculado;
	}
}
