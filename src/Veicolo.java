public class Veicolo implements Comparable<Veicolo> {
    private String targa;
    private String modello;
    private String proprietario;
    private int anno;



    public Veicolo(String targa, String modello, String proprietario, int anno) {
        this.setTarga(targa);
        this.setModello(modello);
        this.setProprietario(proprietario);
        this.setAnno(anno);
    }

    public String toString() {
        return
                "Targa: " + getTarga() +
                        "\nMarca e modello: " + getModello() +
                        "\nProprietario: " + getProprietario() +
                        "\nAnno: " + getAnno();
    }

    @Override
    public int compareTo(Veicolo veicolo) {
        return getTarga().compareTo(veicolo.getTarga());
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }
}