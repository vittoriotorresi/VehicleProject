import java.util.*;
import java.util.Collections;

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
                System.out.println(v);
                return true;
            }
        }
        return false;
    }

    public void visualizzaListaVeicoli() {
        System.out.println("Nel sistema sono presenti " + veicoli.size() + " veicoli");
        Collections.sort(veicoli);
        for (Veicolo v : veicoli) {
            System.out.println(v);
        }
    }


    public void eliminaVeicolo(Veicolo v) {
        veicoli.remove(v);
    }
}

