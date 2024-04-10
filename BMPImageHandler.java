import java.io.*;
import java.util.*;

public class BMPImageHandler{
    public static void main (String args[]){

        try{
//                                             OPCIÓN V HELP

    // -help
            if(args[0].equals("-help")){
                System.out.println("Opciones\t\tForma de ejecutar\n");
                System.out.println("Basics:\t\tjava BMPImageHandler -basics imagen.bmp");
                System.out.println("Rotate:\t\tjava BMPImageHandler -rotate imagen.bmp");
                System.out.println("Resize:\t\tjava BMPImageHandler -resize imagen.bmp");
                System.out.println("All:\t\tjava BMPImageHandler -all imagen.bmp\n");
                System.exit(0);
            }

        //                      Análisis Encabezado
            
            FileInputStream nombreArchivo = new FileInputStream(args[1]);            
            
            byte[] arreglo = new byte[54];
        
            int header = nombreArchivo.read(arreglo);

    //File size
            int pos1 = arreglo[2];
            int pos2 = arreglo[3];
            int pos3 = arreglo[4];

            Double operacion2 = pos2 * Math.pow(2,8);
            Double operacion3 = pos3 * Math.pow(2,16);
            int operacion2I = operacion2.intValue();
            int operacion3I = operacion3.intValue();
            int size = pos1 + operacion2I + operacion3I;

    // Ancho
            int posA1 = arreglo[18] * -1;
            int posA2 = arreglo[19];
            int posA3 = arreglo[20];
            int posA4 = arreglo[21];
            int operacion2A = posA2 << 8;
            int operacion3A = posA3 << 16;
            int operacion4A = posA4 << 24;
        
            int width = posA1 + operacion2A + operacion3A + operacion4A;

    //Largo
            int H1 = arreglo[22];
            int H2 = arreglo[23]+1;
            int H3 = arreglo[24];
            int H4 = arreglo[25];

            int operacionH2 = H2 << 8;
            int operacionH3 = H3 << 16;
            int operacionH4 = H4 << 24;

            int height = H1 + operacionH2 + operacionH3 + operacionH4;


//                                             OPCIONES I-IV

    // I -basics
            if(args[0].equals("-basics")){
                //                          LLamada colores PARTE I
                BmpHandlerCore.colorRojo(args[1],size);
                BmpHandlerCore.colorVerde(args[1],size);
                BmpHandlerCore.colorAzul(args[1],size);
                BmpHandlerCore.colorSepia(args[1],size);
            }

    // II -rotate
            else if(args[0].equals("-rotate")){
                //                        Llamada Rotaciones PARTE II.I
                BmpHandlerRotator.HorizontalRotator(args[1],size,width,height);
                BmpHandlerRotator.VerticalRotator(args[1],size,width,height);
            }
    // III -resize
            else if(args[0].equals("-resize")){
                //                        Llamada resizers PARTE II.II
                BmpHandlerResizer.resizerThin(args[1],size,width,height);
                BmpHandlerResizer.resizerFlat(args[1],size,width,height);
            }
    
    // IV -all
            else if(args[0].equals("-all")){
                //                          LLamada colores PARTE I
                BmpHandlerCore.colorRojo(args[1],size);
                BmpHandlerCore.colorVerde(args[1],size);
                BmpHandlerCore.colorAzul(args[1],size);
                BmpHandlerCore.colorSepia(args[1],size);

            //                        LLamada Rotaciones PARTE II.I
            BmpHandlerRotator.HorizontalRotator(args[1],size,width,height);
            BmpHandlerRotator.VerticalRotator(args[1],size,width,height);

            //                        Llamada Resizers Parte II.II
            BmpHandlerResizer.resizerThin(args[1],size,width,height);
            BmpHandlerResizer.resizerFlat(args[1],size,width,height);
            }

    // BANDERA INVÁLIDA
            else{
                System.out.println("Bandera inválida");
            }
        }
        catch (Exception e){
            System.out.println("Archivo no existe o es inválido");
        }
    }
}