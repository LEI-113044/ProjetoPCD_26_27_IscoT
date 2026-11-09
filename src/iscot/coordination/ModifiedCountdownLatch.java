package iscot.coordination;

/**
 * Modified CountDownLatch API
 */
public class ModifiedCountdownLatch {
    private int count;

    /**
     * Inicializa o CountDownLatch com o número de clientes esperados
     * @param count Número de clientes esperados
     */
    public ModifiedCountdownLatch(int count) {this.count = count;}

    /**
     * Decrementa o CountDownLatch e desperta a thread do servidor bloqueada em await quando chegar a 0,
     * ou seja, quando todos os clientes tiverem consultado o evento
     */
    public synchronized void countdown() {
        if (count > 0) {
            count--;
            if (count == 0) {
                notifyAll();
            }
        }
    }

    /**
     * Suspende a execução da thread atual até que o contador atinja 0
     * @param waitPeriod Período máximo de espera em ms.
     * @throws InterruptedException Caso a thread seja interrompida.
     */
    public synchronized void await(int waitPeriod) throws InterruptedException {
        long remainingTime = waitPeriod;
        long startTime = System.currentTimeMillis();

        while (count > 0 && remainingTime > 0) {
            wait(remainingTime);
            long elapsed = System.currentTimeMillis() - startTime;
            remainingTime = waitPeriod - elapsed;
        }
    }
}