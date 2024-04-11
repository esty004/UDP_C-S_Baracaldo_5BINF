/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Esty
 */
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServer {

    public static void main(String[] args) throws IOException {

        DatagramSocket serverSocket = new DatagramSocket(6789);

        boolean attivo = true;
        byte[] bufferIN = new byte[1024];
        byte[] bufferOUT = new byte[1024];

        System.out.println("Server avviato!");
        while (attivo) {
            DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
            serverSocket.receive(receivePacket);
            String ricevuto = new String(receivePacket.getData());
            int numCaratteri = receivePacket.getLength();
            ricevuto = ricevuto.substring(0, numCaratteri);
            System.out.println("Ricevuto:" + ricevuto);
            InetAddress IPClient = receivePacket.getAddress();
            int portaClient = receivePacket.getPort();
            String daSpedire = ricevuto.toUpperCase();
            bufferOUT = daSpedire.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPClient, portaClient);
            serverSocket.send(sendPacket);
            if (ricevuto.equals("fine")) {
                System.out.println("Server in chiusura");
                attivo = false;
            }
        }
        serverSocket.close();
    }
}
