import java.io.*;
import java.net.*;

import static java.lang.System.exit;


public class Server {


    public static void main(String[] args) {
        String targa;


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
                Targhe targhe = new Targhe();
                switch (scelta) {

                    case 1: {
                        do {

                            targa = br.readLine();
                            System.out.println("Numero di targa inserito: " + targa);
                            PrintWriter pw = new PrintWriter(client.getOutputStream());
                            if (!targa.equalsIgnoreCase("0000000")) {
                                if (targa.length() != 7) {
                                    pw.println("Numero di targa non valido, inserimento non riuscito");
                                    pw.flush();
                                } else {
                                    if (targhe.verificaTarga(targa)) {
                                        pw.println("Numero di targa presente nel sistema, inserimento non riuscito");
                                        pw.flush();
                                    } else {
                                        targhe.inserisciTarga(targa);
                                        pw.println("Inserimento del numero di targa nel sistema riuscito");
                                        pw.flush();
                                    }
                                }

                            }
                        } while (!targa.equalsIgnoreCase("0000000"));

                        if (targa.equalsIgnoreCase("0000000")) {
                            targhe.visualizzaListaTarghe();
                            targhe.salvaSuFile();
                            break;
                        }

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

