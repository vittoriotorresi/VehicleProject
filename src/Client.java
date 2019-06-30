import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.exit;


public class Client {

    public static void main(String[] args) {
        String targa;
        if (args.length != 2) {
            System.out.println("Per favore, inserisci l'indirizzo e il numero di porta");
            exit(-1);
        }
        String address = args[0];
        int port = Integer.parseInt(args[1]);
        Socket client;


        try {
            client = new Socket(address, port);
            System.out.println("Connessione stabilita all'indirizzo " + client.getRemoteSocketAddress() +
                    " sulla porta numero " + port);
        } catch (IOException e) {
            System.out.println("Errore nella creazione del socket client all'indirizzo " +
                    address + " sulla porta numero " + port);
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                System.out.println("-BENVENUTO NEL SISTEMA PER LA GESTIONE ANAGRAFICA DEI VEICOLI-");
                System.out.println("1-Inserisci nuovo veicolo");
                System.out.println("2-Ricerca veicolo");
                System.out.println("3-Elimina veicolo");
                ;
                System.out.println("0-Uscita dal programma");
                System.out.println("Scegliere un'opzione: ");
                Scanner input = new Scanner(System.in);
                int scelta = Integer.parseInt(input.nextLine());
                PrintWriter pw = new PrintWriter(client.getOutputStream());
                pw.println(scelta);
                pw.flush();
                switch (scelta) {
                    case 1: {
                        System.out.println("Inserire il numero di targa del veicolo");
                        System.out.println("Digitare 0000000 per tornare al menu principale");
                        do {
                            System.out.println("Numero di targa: ");

                            targa = input.nextLine();
                            pw.println(targa);
                            pw.flush();
                            if (!targa.equalsIgnoreCase("0000000")) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                                String conferma = br.readLine();
                                System.out.println(conferma);
                            }
                        } while (!targa.equalsIgnoreCase("0000000"));
                        if (targa.equalsIgnoreCase("0000000"))
                            break;
                    }
                    case 2: {
                        System.out.println("Numero di targa: ");
                        targa = input.nextLine();
                        pw.println(targa);
                        pw.flush();
                        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        String conferma = br.readLine();
                        System.out.println(conferma);
                        break;
                    }
                    case 3: {
                        System.out.println("Numero di targa: ");
                        targa = input.nextLine();
                        pw.println(targa);
                        pw.flush();
                        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        String conferma = br.readLine();
                        System.out.println(conferma);
                        break;

                    }

                    case 0: {
                        System.out.println("Chiusura del programma");
                        client.close();
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
