import java.io.*;
import java.net.Socket;
import java.util.Scanner;


import static java.lang.System.exit;


public class Client {

    public static void main(String[] args) {
        String targa;
        String modello;
        String proprietario;
        int anno;


        if (args.length != 2) {
            System.out.println("Per favore, inserire l'indirizzo e il numero di porta");
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
                System.out.println("4-Visualizza veicoli sul server");
                System.out.println("0-Uscita dal programma");
                System.out.println("Scegliere un'opzione: ");
                Scanner input = new Scanner(System.in);
                int scelta = Integer.parseInt(input.nextLine());
                PrintWriter pw = new PrintWriter(client.getOutputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                pw.println(scelta);
                pw.flush();
                switch (scelta) {
                    case 1: {

                        System.out.println("Numero di targa del veicolo da inserire: ");
                        targa = input.nextLine();
                        pw.println(targa);
                        pw.flush();
                        String confermaTarga = br.readLine();
                        System.out.println(confermaTarga);
                        if (confermaTarga.equalsIgnoreCase("Numero di targa non valido. " +
                                "Impossibile inserire il veicolo nel sistema")
                                || confermaTarga.equalsIgnoreCase("Veicolo precedentemente inserito nel sistema"))
                            break;
                        else {
                            System.out.println("Marca e modello del veicolo: ");
                            modello = input.nextLine();
                            pw.println(modello);
                            pw.flush();
                            System.out.println("Cognome e nome del proprietario: ");
                            proprietario = input.nextLine();
                            pw.println(proprietario);
                            pw.flush();
                            System.out.println("Anno di immatricolazione del veicolo: ");
                            anno = input.nextInt();
                            pw.println(anno);
                            pw.flush();
                            String confermaIns = br.readLine();
                            System.out.println(confermaIns);
                            try{
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }


                    case 2: {

                        System.out.println("Numero di targa del veicolo da cercare: ");
                        targa = input.nextLine();
                        pw.println(targa);
                        pw.flush();
                        br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        for (int i = 0; i < 4; i++) {
                            String conferma = br.readLine();
                            System.out.println(conferma);
                            if (conferma.equalsIgnoreCase("Veicolo non presente nel sistema")
                                    || conferma.equalsIgnoreCase("Numero di targa non valido. Impossibile effettuare " +
                                    "la ricerca"))
                                break;
                        }

                        break;
                    }

                    case 3: {
                        System.out.println("Numero di targa del veicolo da eliminare: ");
                        targa = input.nextLine();
                        pw.println(targa);
                        pw.flush();
                        String conferma = br.readLine();
                        System.out.println(conferma);
                        try{
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;

                    }

                    case 4: {
                        System.out.println("Visualizzazione sul server...");
                        break;
                    }


                    case 0: {
                        System.out.println("Chiusura del programma...");
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