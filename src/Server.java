import java.io.*;
import java.net.*;


import static java.lang.System.exit;


public class Server {


    public static void main(String[] args) {
        String targa;
        String modello;
        String proprietario;
        int anno;
        Veicolo v = null;
        Veicoli veicoli = new Veicoli();


        if (args.length != 1) {
            System.out.println("Per favore, inserire il numero di porta ");
            exit(-1);
        }
        int port = Integer.parseInt(args[0]);
        ServerSocket server;
        Socket client;
        try {
            server = new ServerSocket(port);
            System.out.println("Avvio del server sulla porta numero " + port);
            client = server.accept();
            System.out.println("Connessione stabilita all'indirizzo " + client.getRemoteSocketAddress() +
                    " sulla porta numero " + port);
        } catch (IOException e) {
            System.out.println("Errore nella creazione del ServerSocket sulla porta numero " + port);
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                ThreadSalvataggio ts = new ThreadSalvataggio(veicoli);
                Thread t = new Thread(ts);
                t.start();

                InputStreamReader is = new InputStreamReader(client.getInputStream());
                BufferedReader br = new BufferedReader(is);
                int scelta = Integer.parseInt(br.readLine());
                System.out.println("Operazione da eseguire: " + scelta);


                switch (scelta) {

                    case 1: {

                        targa = br.readLine();
                        System.out.println("Numero di targa inserito: " + targa);
                        PrintWriter pw = new PrintWriter(client.getOutputStream());
                        if ((targa.length() != 7)) {
                            pw.println("Numero di targa non valido. Impossibile inserire " +
                                    "il veicolo nel sistema");
                            pw.flush();
                            break;
                        } else {
                            if (veicoli.cercaVeicolo(targa)) {
                                pw.println("Veicolo precedentemente inserito nel sistema");
                                pw.flush();
                                break;
                            } else {
                                pw.println("Numero di targa valido. E' possibile inserire gli altri dati");
                                pw.flush();
                                modello = br.readLine();
                                System.out.println("Marca e modello: " + modello);
                                proprietario = br.readLine();
                                System.out.println("Cognome e nome del proprietario: " + proprietario);
                                anno = Integer.parseInt(br.readLine());
                                System.out.println("Anno di immatricolazione: " + anno);
                                v = new Veicolo(targa, modello, proprietario, anno);
                                veicoli.inserisciVeicolo(v);
                                pw.println("Veicolo inserito nel sistema");
                                pw.flush();
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }

                        }
                    }

                    case 2: {
                        targa = br.readLine();
                        System.out.println("Numero di targa inserito: " + targa);
                        PrintWriter pw = new PrintWriter(client.getOutputStream());
                        if (targa.length() != 7) {
                            pw.println("Numero di targa non valido. Impossibile " +
                                    "effettuare la ricerca");
                            pw.flush();
                            break;
                        } else {

                            if (veicoli.cercaVeicolo(targa)) {
                                System.out.println(veicoli.veicolo_trovato);
                                pw.println(veicoli.veicolo_trovato);
                                pw.flush();
                                break;
                            } else {
                                pw.println("Veicolo non presente nel sistema");
                                pw.flush();
                                break;

                            }
                        }
                    }

                    case 3: {
                        targa = br.readLine();
                        System.out.println("Numero di targa inserito: " + targa);
                        PrintWriter pw = new PrintWriter(client.getOutputStream());
                        if (targa.length() != 7) {
                            pw.println("Numero di targa non valido. Impossibile eliminare" +
                                    " il veicolo dal sistema");
                            pw.flush();
                            break;
                        } else {
                            if (veicoli.cercaVeicolo(targa)) {
                                veicoli.eliminaVeicolo(veicoli.veicolo_trovato);
                                pw.println("Veicolo eliminato dal sistema");
                                pw.flush();
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;

                            } else {
                                pw.println("Veicolo non presente nel sistema. Impossibile eliminare");
                                pw.flush();
                                break;

                            }
                        }

                    }
                    case 4: {
                        veicoli.visualizzaListaVeicoli();
                        break;
                    }

                    case 0: {
                        System.out.println("Chiusura del programma...");
                        server.close();
                        exit(-1);
                        break;
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
