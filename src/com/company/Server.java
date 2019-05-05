package com.company;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Server {

    private static Integer puerto = 1337;

    private static boolean puertoValido(Integer x) {
        return x >= 1 && x <= 65535 ? true : false;
    }

    private static int getPort() {

        Integer input;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Ponga un numero compendido entre 1 y 65535 para el puerto \n");
            input = sc.nextInt();

        } while (input != 0 && !Server.puertoValido(input));

        sc.close();

        return input == 0 ? Server.puerto : input;
    }

    public static void main(String args[]) throws Exception {

        String resClient_1 = "";
        String resClient_2 = "";
        String inputClient_1;
        String inputClient_2;

        Server.puerto = Server.getPort();

        ServerSocket serverSocket = new ServerSocket(Server.puerto);

        while (!serverSocket.isClosed()) {

            Socket client_1 = serverSocket.accept();
            if (client_1.isConnected()) {
                System.out.println("\nJugador 1: (" + (client_1.getLocalAddress().toString()).substring(1) + ":"
                        + client_1.getLocalPort() + ") se ha conectado ... esperando al jugador 2 ...");
            }
            DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
            BufferedReader inClient_1 = new BufferedReader(new InputStreamReader(client_1.getInputStream()));

            Socket client_2 = serverSocket.accept();
            if (client_2.isConnected()) {
                System.out.println("Jugador 2: (" + (client_2.getLocalAddress().toString()).substring(1) + ":"
                        + client_1.getLocalPort() + ") se ha conectado ... empieza la partida ...");
            }
            DataOutputStream outClient_2 = new DataOutputStream(client_2.getOutputStream());
            BufferedReader inClient_2 = new BufferedReader(new InputStreamReader(client_2.getInputStream()));

            inputClient_1 = inClient_1.readLine();
            inputClient_2 = inClient_2.readLine();

            if (inputClient_1.equals(inputClient_2)) {
                resClient_1 = "Empate";
                resClient_2 = "Empate";
            }
            else if (inputClient_1.equals("R") && inputClient_2.equals("S")) {
                resClient_1 = "Has ganado";
                resClient_2 = "Has perdido";

            }
            else if (inputClient_1.equals("S") && inputClient_2.equals("R")) {
                resClient_1 = "Has perdido";
                resClient_2 = "Has ganado";
            }
            else if (inputClient_1.equals("R") && inputClient_2.equals("P")) {
                resClient_1 = "Has perdido";
                resClient_2 = "Has ganado";
            }
            else if (inputClient_1.equals("P") && inputClient_2.equals("R")) {
                resClient_1 = "Has ganado";
                resClient_2 = "Has perdido";
            }
            else if (inputClient_1.equals("S") && inputClient_2.equals("P")) {
                resClient_1 = "Has ganado";
                resClient_2 = "Has perdido";
            }
            else if (inputClient_1.equals("P") && inputClient_2.equals("S")) {
                resClient_1 = "Has perdido";
                resClient_2 = "Has ganado";
            }

            outClient_1.writeBytes(resClient_1.toUpperCase());
            outClient_2.writeBytes(resClient_2.toUpperCase());
            client_1.close();
            client_2.close();

        }
    }

}
