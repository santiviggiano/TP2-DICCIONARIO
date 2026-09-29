import java.util.Iterator;
import java.util.NoSuchElementException;


public class ListaEnlazada<T> implements Iterable<T> {

    private static class NodoLista<T> {
        T dato;
        NodoLista<T> siguiente;
        NodoLista(T dato) {
            this.dato = dato;
        }
    }

    private NodoLista<T> cabeza;
    private NodoLista<T> cola;
    private int tamanio;

    public void agregar(T elemento) {
        NodoLista<T> nuevo = new NodoLista<>(elemento);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
        tamanio++;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        NodoLista<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoLista<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }
}
