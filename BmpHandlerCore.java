import java.io.*;
import java.util.*;

public class BmpHandlerCore{

//                                  AZUL

    public static void colorAzul(String Filename,int size)throws Exception{
        //Campos
        int[] rojo = new int[size];
        int[] verde = new int[size];
        int[] azul = new int[size];
        int cont= 0;
        FileInputStream archivo = new FileInputStream(Filename);
        for(int i=0;i<54;i++){
            azul[i]=archivo.read();
        }
        for (int i=54; i<size;i++){
            if(cont%3==0){
                azul[i]=archivo.read();
        

            }else{
                archivo.read();
                azul[i]=0;                
            }cont++;      
        }
        int posicionPunto = Filename.indexOf(".");
        String nombreNuevoArchivo = Filename.substring(0,posicionPunto);
        FileOutputStream FotoNueva = new FileOutputStream(nombreNuevoArchivo+"-blue.bmp");
        for(int j=0; j<size;j++){
            FotoNueva.write(azul[j]);
        }
        FotoNueva.close(); 
    }

    //                                  VERDE

    public static void colorVerde(String Filename,int size)throws Exception{
        //Campos
        int[] verde = new int[size];
        FileInputStream archivo = new FileInputStream(Filename);

        for(int i=0;i<54;i++){
            verde[i]=archivo.read();
        }

        int cont= 2;
        for (int i=54; i<size;i++){
            if(cont%3==0){
                verde[i]=archivo.read();
        

            }else{
                archivo.read();
                verde[i]=0;                
            }cont++;      
        }
        int posicionPunto = Filename.indexOf(".");
        String nombreNuevoArchivo = Filename.substring(0,posicionPunto);
        FileOutputStream FotoNueva = new FileOutputStream(nombreNuevoArchivo+"-green.bmp");
        for(int j=0; j<size;j++){
            FotoNueva.write(verde[j]);
        }
        FotoNueva.close(); 
    }

    //                                  Rojo

    public static void colorRojo(String Filename,int size)throws Exception{
        //Campos
        int[] rojo = new int[size];
        FileInputStream archivo = new FileInputStream(Filename);

        for(int i=0;i<54;i++){
            rojo[i]=archivo.read();
        }

        int cont= 1;
        for (int i=54; i<size;i++){
            if(cont%3==0){
                rojo[i]=archivo.read();
        

            }else{
                archivo.read();
                rojo[i]=0;                
            }cont++;      
        }
        int posicionPunto = Filename.indexOf(".");
        String nombreNuevoArchivo = Filename.substring(0,posicionPunto);
        FileOutputStream FotoNueva = new FileOutputStream(nombreNuevoArchivo+"-red.bmp");
        for(int j=0; j<size;j++){
            FotoNueva.write(rojo[j]);
        }
        FotoNueva.close(); 
    }


    //                                      CEPIA

    public static void colorSepia(String Filename,int size)throws Exception{

        FileInputStream archivo = new FileInputStream(Filename);

        int[] sepia= new int[size];
        int contA= 0;
        int contV= 2;
        int contR= 1;
        int cont= 54;
        int newAzul,newRojo,newVerde;
        int Azul, Verde, Rojo;
        for(int i=0;i<54;i++){
            sepia[i]=archivo.read();
        }
        for (int i=54; i<size/3;i++){
            
            Azul=archivo.read();
            Verde=archivo.read();
            Rojo=archivo.read();

            newAzul=(int)(0.272*Rojo+0.534*Verde+0.131*Azul);
            if(newAzul>255){
                sepia[cont]= 255;
            }else{
                sepia[cont]= newAzul;
                }
            cont++;

            newVerde=(int)(0.349*Rojo+0.686*Verde+0.168*Azul);
            if(newVerde>255){
                sepia[cont]= 255;
            }else{
                sepia[cont]= newVerde;
                }            
            cont++;             
            
            newRojo=(int)(0.393*Rojo+0.769*Verde+0.189*Azul);
            if(newRojo>255){
                sepia[cont]= 255;
            }else{
                sepia[cont]= newRojo;
            }
            cont ++;

        }           
        int posicionPunto = Filename.indexOf(".");
        String nombreNuevoArchivo = Filename.substring(0,posicionPunto);
        FileOutputStream FotoSepia = new FileOutputStream(nombreNuevoArchivo+"-sepia.bmp");
        for(int j=0; j<size;j++){
            FotoSepia.write(sepia[j]);
        }
        FotoSepia.close(); 
    }

}