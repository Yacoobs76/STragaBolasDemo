package CargarGraficos;





import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Yacoobs
 * Esta Clase externa se encarga de almacenar las imagenes en un ArrayList que previamente, despues 
 * se devuelve a la Clase principal para que ella pueda seleccionar la imagen oportuna indicando, el 
 * indice del Array que solicite.
 */

public class Imagenes_Juego {

//Metodos que establece las rutas de las imagenes. 
    
    
    
    
    public void CargarBolas(ArrayList<BufferedImage> ArrayImg){
        ObtenerArchivos(ArrayImg, "src/G_Bolas/", "png");      
    }
    
    
    public void CargarEnemigo(ArrayList<BufferedImage> ArrayImg){      
        ObtenerArchivos(ArrayImg, "src/G_Enemigo/", "png");       
    }
    
    
    public void CargarFanCongelados(ArrayList<BufferedImage> ArrayImg){      
        ObtenerArchivos(ArrayImg, "src/G_FanCongelados/", "png");       
    }
    
    
    public void CargarFanVulnerable(ArrayList<BufferedImage> ArrayImg){      
        ObtenerArchivos(ArrayImg, "src/G_FanVulnerable/", "png");       
    }
    
    
    public void CargarLetras(ArrayList<BufferedImage> ArrayImg){      
        ObtenerArchivos(ArrayImg, "src/G_Letras/", "png");       
    }
    
    
    public void CargarMapa(ArrayList<BufferedImage> ArrayImg){
         ObtenerArchivos(ArrayImg, "src/G_Map/", "png");
     } 
    
    
    public void CargarNumeros(ArrayList<BufferedImage> ArrayImg){      
        ObtenerArchivos(ArrayImg, "src/G_Numeros/", "png");       
    }
    
    
    public void CargarOjosFantas(ArrayList<BufferedImage> ArrayImg){
        ObtenerArchivos(ArrayImg, "src/G_OjosF/", "png");
    }
    

    public void CargarAnimacionMuertePacman(ArrayList<BufferedImage> ArrayImg){      
        ObtenerArchivos(ArrayImg, "src/Pacm/MuerePacman", "png");       
    }
    
    
    public void CargarPacArriba(ArrayList<BufferedImage> ArrayImg){
        ObtenerArchivos(ArrayImg, "src/Pacm/PacArriba", "png");      
    }
    
    
    public void CargarPacAbajo(ArrayList<BufferedImage> ArrayImg){
        ObtenerArchivos(ArrayImg, "src/Pacm/PacAbajo", "png");      
    }
    
    
    public void CargarPacDerecha(ArrayList<BufferedImage> ArrayImg){
        ObtenerArchivos(ArrayImg, "src/Pacm/PacDerecha", "png");      
    }
    
    
    public void CargarPacIzquierda(ArrayList<BufferedImage> ArrayImg){
        ObtenerArchivos(ArrayImg, "src/Pacm/PacIzquierda", "png");      
    }
    
    
    
    

    
    
    
    
    

    public void ObtenerArchivos(ArrayList<BufferedImage> ArrayImg, String ruta, String Extension){
        
        File archivos = new File(ruta).getAbsoluteFile();
        
        //System.out.println(archivos);
        
        String lista_Archivos[] = archivos.list();
        
        
        try{
            for (String arch:lista_Archivos){
                int longi = arch.lastIndexOf('.');
                int longi1 = arch.length();
                String ob_Ext = arch.substring(longi+1, longi1);
                if (ob_Ext.equals(Extension)){


                    String rutt = archivos.getAbsoluteFile()+ "/"+arch;

                    //Abrimos y cargamos las imagenes de dibujado del programa.....
                    try {
                        BufferedImage Img = ImageIO.read(new File(rutt));
                        ArrayImg.add(Img);
                    } 
                    catch (IllegalArgumentException ex) {
                        System.out.println("Error al CargarGraficos....");
                    } 
                    catch (IOException ex) {                    
                        System.out.println("Error Ruta incorrecta... " + rutt);
                    }      
                }           
            } 
        }catch(NullPointerException e){System.out.println("Carpeta de archivos vacia " + ruta);}
        
    }

    
    
}
