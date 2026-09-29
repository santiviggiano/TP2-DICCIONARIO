
public class DiccionarioEstatico<K, V> implements Diccionario<K, V> {

    private final Object[] claves;
    private final Object[] valores;
    private int cantidad;

    public DiccionarioEstatico(int capacidad) {
        claves = new Object[capacidad];
        valores = new Object[capacidad];
        cantidad = 0;
    }

    @Override
    public void definir(K clave, V valor) {
        int idx = buscarIndice(clave);
        if (idx != -1) {
            valores[idx] = valor;
        } else {
            if (cantidad == claves.length) {
                throw new RuntimeException("El diccionario está lleno");
            }
            claves[cantidad] = clave;
            valores[cantidad] = valor;
            cantidad++;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public V obtener(K clave) {
        int idx = buscarIndice(clave);
        if (idx == -1) {
            throw new RuntimeException("La clave no existe en el diccionario: " + clave);
        }
        return (V) valores[idx];
    }

    @Override
    public void eliminar(K clave) {
        int idx = buscarIndice(clave);
        if (idx == -1) {
            throw new RuntimeException("La clave no existe en el diccionario: " + clave);
        }
        
        int ultimo = cantidad - 1;
        claves[idx] = claves[ultimo];
        valores[idx] = valores[ultimo];
        claves[ultimo] = null;
        valores[ultimo] = null;
        cantidad--;
    }

    @Override
    public boolean existeClave(K clave) {
        return buscarIndice(clave) != -1;
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
        for (int i = 0; i < cantidad; i++) {
            resultado.agregar((K) claves[i]);
        }
        return resultado;
    }

    private int buscarIndice(K clave) {
        for (int i = 0; i < cantidad; i++) {
            if (claves[i].equals(clave)) {
                return i;
            }
        }
        return -1;
    }
}
