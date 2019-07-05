public class ThreadSalvataggio implements Runnable {

    public ThreadSalvataggio(Veicoli veicoli) {
        this.veicoli = veicoli;
    }

    Veicoli veicoli;

    public void run() {

        try {
            Thread.sleep(10000);
            veicoli.salvaSuFile();
        } catch (InterruptedException e) {
            System.out.println("Thread interrotto");
            e.printStackTrace();
        }

    }
}
