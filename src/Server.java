import java.io.*;
import java.net.*;


import static java.lang.System.exit;
import static java.lang.System.setOut;


public class Server {


    public static void main(String[] args) {
        String targa = null;
        String modello = null;
        String proprietario = null;
        int anno = 0;
        Veicolo v = null;

        Veicoli veicoli = new Veicoli();


        if (args.length != 1) {
            System.out.println("Per favore, inserisci il numero di porta ");
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

                InputStreamReader is = new InputStreamReader(client.getInputStream());
                BufferedReader br = new BufferedReader(is);
                int scelta = Integer.parseInt(br.readLine());
                System.out.println("Opzione scelta: " + scelta);

                switch (scelta) {

                    case 1: {

                        targa = br.readLine();
                        System.out.println("Numero di targa inserito: " + targa);
                        PrintWriter pw = new PrintWriter(client.getOutputStream());
                        if (targa.length() != 7) {
                            pw.println("Numero di targa non valido. Impossibile inserire il veicolo");
                            pw.flush();
                            break;
                        } else {
                            /*if (veicoli.cercaVeicolo(targa)) {
                                pw.println("Veicolo inserito precedentemente");
                                pw.flush();
                                break;
                            } else {
                                pw.println("Targa corretta. Inserire gli altri campi");
                                pw.println();
                                pw.flush();
                                // v.setTarga(targa);
                                */
                            modello = br.readLine();
                            System.out.println("Marca e modello: " + modello);
                            proprietario = br.readLine();
                            System.out.println("Cognome e nome del proprietario: " + proprietario);
                            anno = Integer.parseInt(br.readLine());
                            System.out.println("Anno di immatricolazione: " + anno);
                            v = new Veicolo(targa, modello, proprietario, anno);
                            veicoli.inserisciVeicolo(v);
                            pw.println("Inserimento del veicolo nel sistema riuscito");
                            pw.flush();
                            break;
                        }

                    }

                    case 2: {
                        targa = br.readLine();
                        System.out.println("Numero di targa inserito: " + targa);
                        PrintWriter pw = new PrintWriter(client.getOutputStream());
                        if (targa.length() != 7) {
                            pw.println("Numero di targa non valido");
                            pw.flush();
                            break;
                        } else {
                            if (veicoli.cercaVeicolo(targa)) {
                                pw.println(v);
                                pw.flush();
                            } else {
                                pw.println("Veicolo non trovato");
                                pw.flush();
                            }
                        }
                    }
                    break;
                    case 3: {
                        veicoli.visualizzaListaVeicoli();
                        PrintWriter pw = new PrintWriter(client.getOutputStream());
                        pw.println(v);
                        pw.flush();
                        break;
                    }
                       /*
                       targa = br.readLine();
                       System.out.println("Numero di targa inserito: " + targa);
                       PrintWriter pw = new PrintWriter(client.getOutputStream());
                       if (targa.length() != 7) {
                           pw.println("Numero di targa non valido");
                           pw.flush();
                       } else {
                           if (veicoli.cercaVeicolo(targa)) {
                               veicoli.eliminaVeicolo(v);
                               veicoli.visualizzaListaVeicoli();
                               pw.println("Eliminazione riuscita");
                               pw.flush();

                           } else {
                               pw.println("Numero di targa non presente, impossibile eliminare");
                               pw.flush();
                           }
                       }
                       break;

                   }*/

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
