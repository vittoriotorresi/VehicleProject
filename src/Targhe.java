import java.util.ArrayList;
import java.util.Collections;
//CLASSE PER TEST ARRAYLIST TARGHE

public class Targhe implements Comparable<String> {

    ArrayList<String> targhe = new ArrayList<String>();


    public void inserisciTarga(String targa) {
        targhe.add(targa);
    }

    public boolean verificaTarga(String targa) {
        for (String t : targhe) {
            if (t.equalsIgnoreCase(targa))
                return true;

        }
        return false;
    }

    public void visualizzaListaTarghe() {
        System.out.println("Sono state inserite " + targhe.size() + " targhe");
        Collections.sort(targhe);
        for (String t : targhe) {
            System.out.println(t);
        }
    }

    @Override
    public int compareTo(String targa) {
        return targa.compareTo(targa);
    }
}

