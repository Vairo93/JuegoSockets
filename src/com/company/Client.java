package com.company;

import java.io.*;
import java.net.*;

public class Client {

        private static String host = "localhost";

        private static Integer puerto = 1337;

        private static Double numVersion= 1.0;

        private static String msgTitulo = "--- Piedra, papel o tijeras  " + numVersion + " --- \n";


        private static String msgRules = "\n Las reglas son piedra gana a tijeras, tijeras gana a papel y papel gana a piedra";

        public static void main(String args[]) throws Exception {

            String input = "";
            String respuesta;

            System.out.println(Client.msgTitulo);

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
                    System.in));
            Socket clientSocket = new Socket(Client.host, Client.puerto);
            DataOutputStream outToServer = new DataOutputStream(
                    clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));

            do {

                if (input.equals("reglas")) {
                    System.out.println(Client.msgRules);
                }

                System.out.println("Selecciona lo que quieres sacar: Roca(R) o Papel(P) o Tijeras(S)");
                input = inFromUser.readLine();

            } while (!input.equals("R") && !input.equals("P") && !input.equals("S"));

            outToServer.writeBytes(input + "\n");
            System.out.println("\n Has seleccionado" + input);

            respuesta = inFromServer.readLine();

            System.out.println("Respuesta del servidor: " + respuesta);


            clientSocket.close();

        }
    }
