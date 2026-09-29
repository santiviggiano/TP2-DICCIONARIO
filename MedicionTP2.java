
 
public class MedicionTP2 {

    private static long contadorClaveNueva = 0;

    private static String claveNuevaUnica() {
        contadorClaveNueva++;
        return "NUEVA-" + contadorClaveNueva + "-" + System.nanoTime();
    }

    public static void main(String[] args) {
        int[] tamanios = {1000, 10000, 100000};
        int warmup = 10;
        int repeticiones = 15;

        System.out.println("n\tdefinir_estatica_ns\tdefinir_dinamica_ns\tobtener_estatica_ns\tobtener_dinamica_ns");

        for (int n : tamanios) {
            
            DiccionarioEstatico<String, Integer> estDefinir =
                    new DiccionarioEstatico<>(n + warmup + repeticiones + 10);
            DiccionarioDinamico<String, Integer> dinDefinir = new DiccionarioDinamico<>();
            for (int i = 0; i < n; i++) {
                estDefinir.definir("K" + i, i);
                dinDefinir.definir("K" + i, i);
            }

            long tDefinirEst = MedirTiempos.medirNanos(
                    () -> estDefinir.definir(claveNuevaUnica(), 0),
                    repeticiones, warmup);
            long tDefinirDin = MedirTiempos.medirNanos(
                    () -> dinDefinir.definir(claveNuevaUnica(), 0),
                    repeticiones, warmup);

            DiccionarioEstatico<String, Integer> estObtener = new DiccionarioEstatico<>(n);
            DiccionarioDinamico<String, Integer> dinObtener = new DiccionarioDinamico<>();
            for (int i = 0; i < n; i++) {
                estObtener.definir("K" + i, i);
                dinObtener.definir("K" + i, i);
            }
            String claveObtenerEst = "K" + (n - 1); 
            String claveObtenerDin = "K" + 0;        

            long tObtenerEst = MedirTiempos.medirNanos(
                    () -> estObtener.obtener(claveObtenerEst),
                    repeticiones, warmup);
            long tObtenerDin = MedirTiempos.medirNanos(
                    () -> dinObtener.obtener(claveObtenerDin),
                    repeticiones, warmup);

            System.out.println(n + "\t" + tDefinirEst + "\t" + tDefinirDin + "\t" + tObtenerEst + "\t" + tObtenerDin);
        }
    }
}
