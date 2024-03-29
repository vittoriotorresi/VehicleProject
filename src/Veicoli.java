import java.io.*;
import java.util.*;


public class Veicoli {

    ArrayList<Veicolo> veicoli = new ArrayList<Veicolo>();
    Veicolo veicolo_trovato = null;


    public void inserisciVeicolo(Veicolo v) {

        veicoli.add(v);
    }

    public boolean cercaVeicolo(String targa) {
        for (Veicolo v : veicoli) {
            if (v.getTarga().equalsIgnoreCase(targa)) {
                veicolo_trovato = v;
                return true;
            }
        }
        return false;
    }

    public int numeroVeicoli() {
        return veicoli.size();

    }

    public void ordinaListaVeicoli() {
        Collections.sort(veicoli);
    }

    public void visualizzaListaVeicoli() {
        System.out.println("Nel sistema sono presenti " + numeroVeicoli() + " veicoli");
        ordinaListaVeicoli();
        for (Veicolo v : veicoli) {
            System.out.println(v);

        }
    }


    public void eliminaVeicolo(Veicolo v) {
        veicoli.remove(v);
    }

    public synchronized void salvaSuFile() {
        try {
            ordinaListaVeicoli();
            FileWriter fw = new FileWriter("ListaVeicoli.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Veicolo v : veicoli) {
                bw.write(v.toString());
                bw.newLine();

            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio");
            e.printStackTrace();
        }
    }
}



