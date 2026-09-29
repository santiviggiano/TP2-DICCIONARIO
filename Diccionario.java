
public interface Diccionario<K, V> {

    void definir(K clave, V valor);
  
    V obtener(K clave);
  
    void eliminar(K clave);
  
    boolean existeClave(K clave);
    boolean esVacio();
    int cantidadClaves();
    ListaEnlazada<K> claves();
}
