public class ThreadSalvataggio implements Runnable {

    public ThreadSalvataggio(Veicoli veicoli) {
        this.veicoli = veicoli;
    }

    Veicoli veicoli;

    public void run() {
        veicoli.salvaSuFile();

    }
}
