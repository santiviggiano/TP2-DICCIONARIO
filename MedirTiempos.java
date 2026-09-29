
public class MedirTiempos {

    public static long medirNanos(Runnable accion, int repeticiones, int warmup) {
        for (int i = 0; i < warmup; i++) {
            accion.run();
        }
        long mejor = Long.MAX_VALUE;
        for (int i = 0; i < repeticiones; i++) {
            long inicio = System.nanoTime();
            accion.run();
            long fin = System.nanoTime();
            long transcurrido = fin - inicio;
            if (transcurrido < mejor) {
                mejor = transcurrido;
            }
        }
        return mejor;
    }
}
