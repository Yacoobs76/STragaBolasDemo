package ReproduceAudio;





import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;




/**
 * SUPER TRAGA BOLAS programado por Yacoobs C. M. con Java
 * 
 * @author Yacoobs
 * Esta clase se encarga de reproducir efectos de audio del programa 
 * Precisa de la libreria externa Jl1.0.jar JLayer 1.0.1 
 * Descarga: http://www.java2s.com/Code/Jar/j/Downloadjl10jar.htm
 */

public class ReproduceMusic {

    private Player sonido;   
    private File file=null;
    private boolean Reproduce=true;

    //Metodo encargado de reproducir el audio, pide la ruta y el nombre de archivo para reproducir el audio.
    public void Reproducir(String ruta){
        
        if (ruta!=""){

            Thread EmpezarAudio = new Thread(() -> {
                if (Reproduce){
                    
                    Reproduce=false;
                    
                    try {
                        file = new File("").getAbsoluteFile();
                    } catch(NullPointerException e2){
                        System.out.println("Error la ruta o archivo no encontrado de audio....");
                    }
                    
                    try {
                        InputStream inputStream = new FileInputStream(file + "/src/AudiosJuego/" + ruta);
                        
                        sonido = new Player(inputStream);
                        
                        sonido.play();
                        
                    } catch (JavaLayerException e) {
                        JOptionPane.showMessageDialog(null, "Error Reproducir Audio JavaLayerException \n" + e);
                    } catch (FileNotFoundException e) {
                        
                        JOptionPane.showMessageDialog(null, "Error Reproducir Audio FileNotFoundException \n" + e);
                    } catch (NullPointerException e){
                        
                        JOptionPane.showMessageDialog(null, "Error Reproducir Audio showMessageDialog \n" + e);
                    }
                    Reproduce=true;
                    
                }
            });
            EmpezarAudio.start();
        } 
    }

    
    //Metodo utilizado para detener el audio.
    public void detener(){
        sonido.close();
    }
    
    
}

 