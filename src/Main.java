import java.util.Scanner;


public class Main {
    public static void llenarSala(String matriz[][], int filas, int columnas){
        String butacas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for(int fila = 0; fila < filas; fila ++){
            for(int columna = 0; columna<columnas; columna++){
                matriz[fila][columna] = butacas.charAt(fila) + String.valueOf(columna+1) + "\t";
            }
        }
    }

    public static void verSala(String matriz[][], int filas, int columnas){
        String salida = "";
        salida += "------ PANTALLA --------\n";
        for(int fila = 0; fila < filas; fila ++){
            for(int columna = 0; columna<columnas; columna++){
                salida += matriz[fila][columna];
            }
            salida += "\n";
        }
        System.out.println(salida);
    }

    public static void comprarBoleto(String matriz[][], int filas, int columnas, String boleto){
        boleto = boleto.toUpperCase();
        boolean encontrado = false;
        int cualFila=-1, cualColumna=-1;

        for(int fila = 0; fila < filas; fila++){
            for(int columna =0 ; columna < columnas; columna++){

                if(boleto.equals(matriz[fila][columna].toUpperCase().trim())){
                    //encontrado
                    cualFila = fila;
                    cualColumna = columna;
                    encontrado = true;
                    break;
                }
            }
            if(encontrado){
                break;
            }
        }
        if (encontrado) {
            System.out.println("Boleto " + matriz[cualFila][cualColumna] + " comprado con exito");
            matriz[cualFila][cualColumna] = "X\t";
        } else {
            System.out.println("Boleto no encontrado");
        }

    }

    public static void devolverBoleto(String matriz[][], int filas, int columnas, String boleto){
        String butacas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean encontrado = false;
        String letraFila = String.valueOf(boleto.charAt(0)).toUpperCase();
        int cualFila = -1, cualColumna = Integer.parseInt(String.valueOf(boleto.charAt(1)))-1;

        if (cualColumna < 0 || cualColumna >= columnas) {
            System.out.println("Número de columna fuera de rango");
            return;
        }

        // Encontrar la fila correspondiente a la letra
        for (int fila = 0; fila < butacas.length(); fila++) {
            if (letraFila.equals(String.valueOf(butacas.charAt(fila)))) {
                cualFila = fila;
                encontrado = true;
                break;
            }
        }

        // Verificar que la fila está dentro del rango permitido
        if (!encontrado || cualFila < 0 || cualFila >= filas) {
            System.out.println("Letra de fila fuera de rango o no encontrada");
            return;
        }

        // Verificar que el asiento está marcado como "X"
        System.out.println("Revisando asiento en fila " + cualFila + " y columna " + cualColumna + ": '" + matriz[cualFila][cualColumna] + "'"); // Depuración
        if ("X".equals(matriz[cualFila][cualColumna].trim())) {
            System.out.println("Asiento devuelto con éxito");
            matriz[cualFila][cualColumna] = letraFila + String.valueOf(cualColumna + 1)+"\t";
        } else {
            System.out.println("Asiento no está marcado como ocupado");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion, filas,columnas;
        String boleto;
        boolean salir = false;

        do {
            System.out.println("Cuantas filas possee la matriz");
            filas = sc.nextInt();
        }while(filas<0 || filas>10);

        do {
            System.out.println("Cuantas Columnas possee la matriz");
            columnas = sc.nextInt();
        }while(columnas<0 || columnas>10);

        String cine[][] = new String[filas][columnas];
        llenarSala(cine, filas, columnas);

        while(!salir){
            System.out.println("Ingrese la opcion que desea\n1. Ver sala\n2. Comprar boleto\n3. Devolver boleto\nCualquier otro para salir");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    verSala(cine, filas, columnas);
                    break;
                case 2:
                    verSala(cine, filas, columnas);
                    System.out.println("Ingrese el boleto que desea comprar");
                    boleto = sc.next();
                    comprarBoleto(cine, filas, columnas, boleto);
                    break;
                case 3:
                    verSala(cine, filas, columnas);
                    System.out.println("Ingrese el asiento que desea devolver");
                    boleto = sc.next();
                    devolverBoleto(cine, filas, columnas, boleto);
                    break;
                default:
                    salir = true;
                    break;
            }

        }

    }
}