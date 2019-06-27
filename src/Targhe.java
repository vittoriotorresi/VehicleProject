
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
//CLASSE PER TEST ARRAYLIST TARGHE

public class Targhe implements Comparable<String> {

    ArrayList<String> targhe = new ArrayList<String>();
    File file = new File("targhe.txt");


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

    public void salvaSuFile() {
        try {
            FileWriter fw = new FileWriter("targhe.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (String t : targhe) {
                bw.write(t);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio");
            e.printStackTrace();
        }


    }

    @Override
    public int compareTo(String targa) {
        return targa.compareTo(targa);
    }
}

