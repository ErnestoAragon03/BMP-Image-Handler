import java.io.*;
import java.util.*;
public class BmpHandlerRotator{
    public static void HorizontalRotator(String nombreDelArchivo, int size, int width, int height){
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
            byte[][] matrizNueva = new byte[largo][ancho];

        //                          EDICIÓN DE LA MATRIZ ORIGINAL
            for(int i=0;i<largo;i++){
                for(int j=0;j<ancho;j++){
                    matrizNueva[i][j] = matrizOriginal[largo-1-i][j];
                }
            }

        //                           CREACIÓN NUEVO ARCHIVO BMP
            int posicionPunto = nombreDelArchivo.indexOf(".");
            String nombreNuevoArchivo = nombreDelArchivo.substring(0,posicionPunto);
            FileOutputStream imagenRotadaH = new FileOutputStream(nombreNuevoArchivo+"-hrotation.bmp");
            FileInputStream nombreDeImagenCopia = new FileInputStream(nombreDelArchivo); 
            byte[] byteHeader = new byte[54];
            int intHeader = nombreDeImagenCopia.read(byteHeader);
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




    //                                  ROTACIÓN VERTICAL

public static void VerticalRotator(String nombreDelArchivo, int size, int width, int height){
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

            for(int i=0;i<largo;i++){
                for(int j=0;j<ancho;j++){
                    matrizOriginal[i][j] = fila[j+ancho*i];
                }
            }
        //                             CREACIÓN MATRIZ NUEVA
            byte[][] matrizNueva = new byte[largo][ancho];

        //                          EDICIÓN DE LA MATRIZ ORIGINAL
            for(int i=0;i<largo;i++){
                int contA=2;
                int contV=1;
                int contR=0;
                //Azules
                for(int j=0;j<ancho;j+=3){
                    matrizNueva[i][j] = matrizOriginal[i][ancho-1-contA];
                    
                contA+=3;
                }
                //Verdes
                for(int j=1;j<ancho;j+=3){
                    matrizNueva[i][j] = matrizOriginal[i][ancho-1-contV];
                    contV+=3;
                }
                //Rojos
                for(int j=2;j<ancho;j+=3){
                    matrizNueva[i][j] = matrizOriginal[i][ancho-1-contR];
                    contR+=3;
                }
            }
            
        //                           CREACIÓN NUEVO ARCHIVO BMP
            int posicionPunto = nombreDelArchivo.indexOf(".");
            String nombreNuevoArchivo = nombreDelArchivo.substring(0,posicionPunto);
            FileOutputStream imagenRotadaV = new FileOutputStream(nombreNuevoArchivo+"-vrotation.bmp");
            FileInputStream nombreDeImagenCopia = new FileInputStream(nombreDelArchivo); 
            byte[] byteHeader = new byte[54];
            int intHeader = nombreDeImagenCopia.read(byteHeader);
            imagenRotadaV.write(byteHeader);
            
            nombreDeImagenCopia.close();

            for(int i=0;i<largo;i++){
                imagenRotadaV.write(matrizNueva[i]);
            }
            imagenRotadaV.close();
        }
        catch(Exception e){

        }
    }




/*
    public static void VerticalRotator(String nombreDelArchivo, int size, int ancho, int largo){
        try{
            FileInputStream nombreDeImagen = new FileInputStream(nombreDelArchivo); 
            byte[] fila = new byte[size-54];
            int body;
            byte[][] matrizOriginal = new byte[largo][ancho];
            /*ByteArrayInputStream matrizOriginal = new ByteArrayInputStream(bytes,54,size);     
            matrizOriginal.reset();
            System.out.println("\n"+matrizOriginal.read());

        //                          CREACIÓN MATRIZ ORIGINAL
            for(int y=0;y<54;y++){
                    body = nombreDeImagen.read();
                }
            body = nombreDeImagen.read(fila);
            nombreDeImagen.close();

            System.out.println("\nFIla "+fila[1]);

            for(int i=0;i<largo;i++){
                for(int j=0;j<ancho;j++){
                    matrizOriginal[i][j] = fila[j+10*i];
                }
            }
        //                             CREACIÓN MATRIZ NUEVA
            byte[][] matrizNueva = new byte[largo][ancho];

        //                          EDICIÓN DE LA MATRIZ ORIGINAL
            for(int i=0;i<largo;i++){
                for(int j=0;j<ancho;j++){
                    matrizNueva[i][j] = matrizOriginal[i][ancho-1-j];
                }
            }
            System.out.println("\nMatriz original última fila primera columna:"+matrizOriginal[0][ancho-1]+" "+matrizOriginal[0][ancho-2]+" "+matrizOriginal[0][ancho-3]);
            System.out.println("\nMatriz nueva primera fila primera columna:"+ matrizNueva[0][0]+" "+matrizNueva[0][1]+" "+matrizNueva[0][2]);
        //                           CREACIÓN NUEVO ARCHIVO BMP
            FileOutputStream imagenRotadaH = new FileOutputStream("Image-vrotation.bmp");
            FileInputStream nombreDeImagenCopia = new FileInputStream(nombreDelArchivo); 
            byte[] byteHeader = new byte[54];
            //nombreDeImagen.reset();
            int intHeader = nombreDeImagenCopia.read(byteHeader);
            imagenRotadaH.write(byteHeader);
            
            nombreDeImagenCopia.close();
            /*int convertor;
            for(int i=0;i<largo;i++){
                for(int j=0;j<ancho;j++){
                    convertor = matrizNueva[i][j];
                    imagenRotadaH.write(convertor);
                }
            }
            for(int i=0;i<largo;i++){
                imagenRotadaH.write(matrizNueva[i]);
            }
            imagenRotadaH.close();

            //while(data != -1) {
                
            //}

        }
        catch(Exception e){

        }
    }*/
}