public class Main {

    public static void main(String[] args) {
        System.out.println("=== Prueba con DiccionarioDinamico ===");
        probar(new DiccionarioDinamico<>(), new DiccionarioDinamico<>());

        System.out.println("\n=== Prueba con DiccionarioEstatico ===");
        probar(new DiccionarioEstatico<>(20), new DiccionarioEstatico<>(20));
    }

    private static void probar(Diccionario<String, Integer> d1, Diccionario<String, Integer> d2) {
        d1.definir("a", 1);
        d1.definir("b", 2);
        d1.definir("c", 8);
        d1.definir("b", 20); 

        System.out.println("cantidadClaves d1 (se espera 3): " + d1.cantidadClaves());
        System.out.println("obtener(d1,b) (se espera 20): " + d1.obtener("b"));
        System.out.println("existeClave(d1,z) (se espera false): " + d1.existeClave("z"));

        d1.eliminar("a");
        System.out.println("existeClave(d1,a) tras eliminar (se espera false): " + d1.existeClave("a"));
        System.out.println("cantidadClaves d1 tras eliminar (se espera 2): " + d1.cantidadClaves());

        
        d2.definir("c", 100); 
        d2.definir("d", 4);
        d2.definir("e", 8);

        Diccionario<String, Integer> combinado = UtilizacionDiccionario.combinarDiccionarios(d1, d2);
        System.out.println("\ncombinarDiccionarios -> cantidad (se espera 4: b,c,d,e): " + combinado.cantidadClaves());
        System.out.println("  c (se espera 100, el de d2): " + combinado.obtener("c"));
        System.out.println("  b (se espera 20, sólo en d1): " + combinado.obtener("b"));
        System.out.println("d1 intacto tras combinar (cantidad, se espera 2): " + d1.cantidadClaves());
        System.out.println("d2 intacto tras combinar (cantidad, se espera 3): " + d2.cantidadClaves());

        Diccionario<Integer, String> invertido = UtilizacionDiccionario.invertir(d1);
        System.out.println("\ninvertir(d1) -> obtener(20) (se espera 'b'): " + invertido.obtener(20));
        System.out.println("invertir(d1) -> obtener(8) (se espera 'c'): " + invertido.obtener(8));

        int mayoresA5 = UtilizacionDiccionario.contarValoresMayoresA(d2, 5);
        System.out.println("\ncontarValoresMayoresA(d2, 5) [c=100,d=4,e=8 -> 2 mayores a 5]: " + mayoresA5);

        Diccionario<String, Integer> paraOrdenar = new DiccionarioDinamico<>();
        paraOrdenar.definir("zorro", 1);
        paraOrdenar.definir("armadillo", 2);
        paraOrdenar.definir("mono", 3);
        paraOrdenar.definir("burro", 4);
        ListaEnlazada<String> ordenadas = UtilizacionDiccionario.clavesOrdenadas(paraOrdenar);
        System.out.print("clavesOrdenadas (se espera armadillo, burro, mono, zorro): ");
        for (String s : ordenadas) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
