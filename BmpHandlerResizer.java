import java.io.*;
import java.util.*;
public class BmpHandlerResizer{
    public static void resizerThin(String nombreDelArchivo, int size, int width, int height){
        try{
            int ancho = width*3;
            int largo = height;
            FileInputStream nombreDeImagen = new FileInputStream(nombreDelArchivo); 
            byte[] fila = new byte[size];
            int body;
            byte[][] matrizOriginal = new byte[largo][ancho];
            
        //                          CREACIÓN MATRIZ ORIGINAL
            for(int y=0;y<54;y++){
                    body = nombreDeImagen.read();
                }
            body = nombreDeImagen.read(fila);
            nombreDeImagen.close();

            for(int i=0;i<largo;i++){;
                for(int j=0;j<ancho;j++){
                    matrizOriginal[i][j] = fila[j+ancho*i];
                }
            }
        //                             CREACIÓN MATRIZ NUEVA
            byte[][] matrizNueva = new byte[largo][ancho/2];

        //                          COPIA DE LA MATRIZ ORIGINAL
        int contj = 0;
            for(int i=0;i<largo;i++){
                for(int j=0;j<ancho/2;j+=3){
                    //azul
                    matrizNueva[i][j] = matrizOriginal[i][contj];
                    //verde
                    contj++;
                    matrizNueva[i][j+1] = matrizOriginal[i][contj];
                    contj++;
                    //rojo
                    matrizNueva[i][j+2] = matrizOriginal[i][contj];
                    contj+=4;
                }
                contj = 0;
            }

        //                           CREACIÓN NUEVO ARCHIVO BMP
            int posicionPunto = nombreDelArchivo.indexOf(".");
            String nombreNuevoArchivo = nombreDelArchivo.substring(0,posicionPunto);
            FileOutputStream imagenRotadaH = new FileOutputStream(nombreNuevoArchivo+"-thin.bmp");
            FileInputStream nombreDeImagenCopia = new FileInputStream(nombreDelArchivo);

            byte[] byteHeader = new byte[54];
            int intHeader = nombreDeImagenCopia.read(byteHeader);

            //Edición del encabezado
            //Size
            int posS1 = byteHeader[2]/2;
            int posS2 = byteHeader[3]/2;
            int posS3 = byteHeader[4]/2;
            int posS4 = byteHeader[5]/2;
            //Ancho
            int posA1 = byteHeader[18]/-2;
            int posA2 = byteHeader[19]/2;
            int posA3 = byteHeader[20]/2;
            int posA4 = byteHeader[21]/2;
            
                //Intercambio dentro del encabezado

            byteHeader[2]=(byte)posS1;
            byteHeader[3]=(byte)posS2;
            byteHeader[4]=(byte)posS3;
            byteHeader[5]=(byte)posS4;

            byteHeader[18]=(byte)posA1;
            byteHeader[19]=(byte)posA2;
            byteHeader[20]=(byte)posA3;
            byteHeader[21]=(byte)posA4;


            imagenRotadaH.write(byteHeader);
            nombreDeImagenCopia.close();
            
            for(int i=0;i<largo;i++){
                imagenRotadaH.write(matrizNueva[i]);
            }
            imagenRotadaH.close();

        }
        catch(Exception e){

        }
    }

    public static void resizerFlat(String nombreDelArchivo, int size, int width, int height){
        try{
            int ancho = width*3;
            int largo = height;
            FileInputStream nombreDeImagen = new FileInputStream(nombreDelArchivo); 
            byte[] fila = new byte[size];
            int body;
            byte[][] matrizOriginal = new byte[largo][ancho];
            
        //                          CREACIÓN MATRIZ ORIGINAL
            for(int y=0;y<54;y++){
                    body = nombreDeImagen.read();
                }
            body = nombreDeImagen.read(fila);
            nombreDeImagen.close();

            for(int i=0;i<largo;i++){;
                for(int j=0;j<ancho;j++){
                    matrizOriginal[i][j] = fila[j+ancho*i];
                }
            }
        //                             CREACIÓN MATRIZ NUEVA
            byte[][] matrizNueva = new byte[largo/2][ancho];

        //                          COPIA DE LA MATRIZ ORIGINAL
        int conti =0;
            for(int i=0;i<largo/2;i++){
                matrizNueva[i] = matrizOriginal[conti];
                conti += 2;
            }

        //                           CREACIÓN NUEVO ARCHIVO BMP
            int posicionPunto = nombreDelArchivo.indexOf(".");
            String nombreNuevoArchivo = nombreDelArchivo.substring(0,posicionPunto);
            FileOutputStream imagenRotadaH = new FileOutputStream(nombreNuevoArchivo+"-flat.bmp");
            FileInputStream nombreDeImagenCopia = new FileInputStream(nombreDelArchivo);

            byte[] byteHeader = new byte[54];
            int intHeader = nombreDeImagenCopia.read(byteHeader);

            //Edición del encabezado
            //Size
            int posS1 = byteHeader[2]/2;
            int posS2 = byteHeader[3]/2;
            int posS3 = byteHeader[4]/2;
            int posS4 = byteHeader[5]/2;
            //Largo
            int posA1 = byteHeader[22]/2;
            int posA2 = byteHeader[23]/2;
            int posA3 = byteHeader[24]/2;
            int posA4 = byteHeader[25]/2;
            
                //Intercambio dentro del encabezado

            byteHeader[2]=(byte)posS1;
            byteHeader[3]=(byte)posS2;
            byteHeader[4]=(byte)posS3;
            byteHeader[5]=(byte)posS4;

            byteHeader[22]=(byte)posA1;
            byteHeader[23]=(byte)posA2;
            byteHeader[24]=(byte)posA3;
            byteHeader[25]=(byte)posA4;


            imagenRotadaH.write(byteHeader);
            nombreDeImagenCopia.close();
            
            for(int i=0;i<largo/2;i++){
                imagenRotadaH.write(matrizNueva[i]);
            }
            imagenRotadaH.close();

        }
        catch(Exception e){

        }
    }
}