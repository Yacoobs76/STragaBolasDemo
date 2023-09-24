package CargarGraficos;





import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Yacoobs C. M.
 * Clase Principal del juego.
 * Juego Simulacion Pacman....
 * 16:38 07 enero del 2018
 * Ultima modificacion 22-1-2018
 * @version V.0.7 
 */

public class GraficosLetrasNumeros {
    
//Almacena las imagenes de las letras graficas del menu.    
    private ArrayList<BufferedImage> Letras = new ArrayList<>();
    
//Almacenan las imagenes de los numeros de puntos del juego.     
    private ArrayList<BufferedImage> Numeros = new ArrayList<>();
    
//Esta clase externa se encarga de almacenar en ArrayList las ImageBuffered del juego.
    private Imagenes_Juego Img_Ju = new Imagenes_Juego();    

    public GraficosLetrasNumeros() {
        
        Img_Ju.CargarLetras(Letras);
        Img_Ju.CargarNumeros(Numeros);
    }
 
    
//Este metodo se encarga de dibujar graficos de textos numericos y letras en la pantalla grafica del Frame.    
    public void Dibuja_Texto_Grafico(Graphics2D g2, int X, int Y,int Ancho ,int Alto, int separacion, String texto){
        
        int longitud = texto.length();

        int vueltas=0;
     
        String LE[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","."};
        String NU[]={"0","1","2","3","4","5","6","7","8","9"};
        for (int p=0;p<longitud;p++){
            
            String letra= ""+texto.charAt(p);
            BufferedImage img = null;
            for (int indx=0;indx<LE.length;indx++){
                if (img==null){
                    img = Selec(Letras, letra, LE[indx], indx);
                }    
            }
            
            for (int indx=0;indx<NU.length;indx++){
                if (img==null){
                    img = Selec(Numeros, letra, NU[indx], indx);
                }        
            }
           
            if (Ancho!=0){
                g2.drawImage(img, X+vueltas, Y, Ancho, Alto, null);
            }      
            vueltas=vueltas+separacion;        
        }   
    }
    
    //Este metodo devuelve la imagen correspondiente dependiendo del caracter e indice que sea igual.    
    public BufferedImage Selec(ArrayList<BufferedImage> Buff,String letra, String text, int indx){
        BufferedImage img = null;
        if (letra.equalsIgnoreCase(text)){          
            img = Buff.get(indx);    
        }
        return img;
    }  
    
    
}
