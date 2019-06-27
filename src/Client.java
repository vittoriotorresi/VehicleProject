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
                System.out.println("Inserire la targa del veicolo: ");
                Scanner input = new Scanner(System.in);
                do {
                    targa = input.nextLine();
                    PrintWriter pw = new PrintWriter(client.getOutputStream());
                    pw.println(targa);
                    pw.flush();
                    if (!targa.equalsIgnoreCase("0000000")) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        String conferma = br.readLine();
                        System.out.println(conferma);
                    }
                } while (!targa.equalsIgnoreCase("0000000"));
                if (targa.equalsIgnoreCase("0000000")) {
                    System.out.println("Chiusura del programma...");
                    client.close();
                    exit(-1);
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}