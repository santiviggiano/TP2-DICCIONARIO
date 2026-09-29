
public class UtilizacionDiccionario {

    
    public static <K, V> Diccionario<K, V> combinarDiccionarios(Diccionario<K, V> d1, Diccionario<K, V> d2) {
        Diccionario<K, V> resultado = new DiccionarioDinamico<>();
        for (K clave : d1.claves()) {
            resultado.definir(clave, d1.obtener(clave));
        }
        for (K clave : d2.claves()) {
            resultado.definir(clave, d2.obtener(clave)); 
        }
        return resultado;
    }

    public static <K, V> Diccionario<V, K> invertir(Diccionario<K, V> d) {
        Diccionario<V, K> resultado = new DiccionarioDinamico<>();
        for (K clave : d.claves()) {
            V valor = d.obtener(clave);
            resultado.definir(valor, clave);
        }
        return resultado;
    }

    
    public static <K> int contarValoresMayoresA(Diccionario<K, Integer> d, int umbral) {
        int contador = 0;
        for (K clave : d.claves()) {
            int valor = d.obtener(clave);
            if (valor > umbral) {
                contador++;
            }
        }
        return contador;
    }

    
    public static ListaEnlazada<String> clavesOrdenadas(Diccionario<String, ?> d) {
        ListaEnlazada<String> sinOrdenar = d.claves();

        
        String[] arreglo = new String[sinOrdenar.tamanio()];
        int i = 0;
        for (String clave : sinOrdenar) {
            arreglo[i] = clave;
            i++;
        }

      
        for (int j = 1; j < arreglo.length; j++) {
            String actual = arreglo[j];
            int k = j - 1;
            while (k >= 0 && arreglo[k].compareTo(actual) > 0) {
                arreglo[k + 1] = arreglo[k];
                k--;
            }
            arreglo[k + 1] = actual;
        }

        ListaEnlazada<String> resultado = new ListaEnlazada<>();
        for (String clave : arreglo) {
            resultado.agregar(clave);
        }
        return resultado;
    }
}
