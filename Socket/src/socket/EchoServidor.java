/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;
import java.io.*;
import java.net.*;
import java.util.Scanner;


/**
 *
 * @author licenciaturas
 */
public class EchoServidor {
      public static final int PORT = 4444;
      public static void main(String[] args) throws IOException {
        Scanner entrad = new Scanner (System.in);
        // Establece el puerto en el que escucha peticiones
        ServerSocket socketServidor = null;
        try {
          socketServidor = new ServerSocket(PORT);
        } catch (IOException e) {
          System.out.println("No puede escuchar en el puerto: " + PORT);
          System.exit(-1);
        }

        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;

        System.out.println("Escuchando: " + socketServidor);
        try {
          // Se bloquea hasta que recibe alguna petición de un cliente
          // abriendo un socket para el cliente
          socketCliente = socketServidor.accept();
          System.out.println("Connexión acceptada: "+ socketCliente);
          // Establece canal de entrada
          entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
          // Establece canal de salida
          salida = new PrintWriter(new BufferedWriter(new 
    	  OutputStreamWriter(socketCliente.getOutputStream())),true);
          
          // Hace eco de lo que le proporciona el cliente, hasta que recibe "Adios"
          while (true) {  
            String str = entrada.readLine();
            System.out.println("Cliente: " + str);
              if (!str.equals("suma") && !str.equals("resta") && !str.equals("multiplicacion") && !str.equals("nombre")) {
                  salida.println(str);
              }
              if(str.equals("suma")){
                int n1 , n2;
                salida.println("Introduce el primer número:");			
                n1 = Integer.parseInt(entrada.readLine());
                salida.println("Introduce el segundo número:");
                n2 = Integer.parseInt(entrada.readLine());
                int suma = n1 + n2;
                salida.println(suma);
                }
              
               if(str.equals("resta")){
                int n1 , n2;
                salida.println("Introduce el primer número:");			
                n1 = Integer.parseInt(entrada.readLine());
                salida.println("Introduce el segundo número:");
                n2 = Integer.parseInt(entrada.readLine());
                int resta = n1 - n2;
                salida.println(resta);
                }
               
               if(str.equals("multiplicacion")){
                int n1 , n2;
                salida.println("Introduce el primer número:");			
                n1 = Integer.parseInt(entrada.readLine());
                salida.println("Introduce el segundo número:");
                n2 = Integer.parseInt(entrada.readLine());
                int multi = n1 * n2;
                salida.println(multi);
                }
               
                if (str.equals("nombre")) {
                  salida.println(InetAddress.getLocalHost().getHostName());
                }
              if (str.equals("Adios") || str.equals("adios")) {
                  break;
              }
            }
        } catch (IOException e) {
          System.out.println("IOException: " + e.getMessage());
        }  
        salida.close();
        entrada.close();
        socketCliente.close();
        socketServidor.close();
      }
    }

