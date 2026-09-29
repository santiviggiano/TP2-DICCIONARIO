
public class DiccionarioDinamico<K, V> implements Diccionario<K, V> {

    private NodoDiccionario cabeza;
    private int cantidad;

    @Override
    public void definir(K clave, V valor) {
        NodoDiccionario nodo = buscarNodo(clave);
        if (nodo != null) {
            nodo.valor = valor;
        } else {
            NodoDiccionario nuevo = new NodoDiccionario();
            nuevo.clave = clave;
            nuevo.valor = valor;
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            cantidad++;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public V obtener(K clave) {
        NodoDiccionario nodo = buscarNodo(clave);
        if (nodo == null) {
            throw new RuntimeException("La clave no existe en el diccionario: " + clave);
        }
        return (V) nodo.valor;
    }

    @Override
    public void eliminar(K clave) {
        NodoDiccionario anterior = null;
        NodoDiccionario actual = cabeza;
        while (actual != null && !actual.clave.equals(clave)) {
            anterior = actual;
            actual = actual.siguiente;
        }
        if (actual == null) {
            throw new RuntimeException("La clave no existe en el diccionario: " + clave);
        }
        if (anterior == null) {
            cabeza = actual.siguiente;
        } else {
            anterior.siguiente = actual.siguiente;
        }
        cantidad--;
    }

    @Override
    public boolean existeClave(K clave) {
        return buscarNodo(clave) != null;
    }

    @Override
    public boolean esVacio() {
        return cantidad == 0;
    }

    @Override
    public int cantidadClaves() {
        return cantidad;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ListaEnlazada<K> claves() {
        ListaEnlazada<K> resultado = new ListaEnlazada<>();
        NodoDiccionario actual = cabeza;
        while (actual != null) {
            resultado.agregar((K) actual.clave);
            actual = actual.siguiente;
        }
        return resultado;
    }

    private NodoDiccionario buscarNodo(K clave) {
        NodoDiccionario actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }
}
