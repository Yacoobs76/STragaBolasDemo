package ReproduceAudio;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Yacoobs
 * Esta clase se encarga de Reproducir los efectos de audio almacenando los en un ArrayList las rutas
 * de ubicacion de cada uno de ellos y luego llamando a su metodo Fx() carga dicha ruta, reproduciendo
 * el audio con la interface Clip, pasando como argumento al metodo el indice de la ruta. 
 * 
 */

public class ReproduceAudio {
    
    Clip sonido;
    
    private ArrayList<File> EFX = new ArrayList<>();
    
    public ReproduceAudio(){
        
        introducirFx("Efecto0.wav");
        introducirFx("Efecto1.wav");
        introducirFx("Efecto2.wav");
        introducirFx("Efecto3.wav");
        introducirFx("Efecto4.wav");
        introducirFx("Efecto5.wav");
        introducirFx("Efecto6.wav");
        introducirFx("Efecto7.wav");
        introducirFx("Efecto8.wav");
        introducirFx("Efecto9.wav");
        introducirFx("Efecto10.wav");
        introducirFx("Efecto11.wav");
    }
    
    
    public void introducirFx (String Ruta){
        
        try{
            
            File file = new File("").getAbsoluteFile();
            
            String rutt = file + "/src/AudiosJuego/" + Ruta;
            
            file  = new File(rutt);
            
            
            EFX.add(file);
            
            
        }catch(NullPointerException e){
            System.out.println("Error la ruta o archivo no encontrado de audio....");
        }
        
    }
    
    public void Fx (int indice){
        
        try {
            File file = EFX.get(indice);
            
            Clip sonido = AudioSystem.getClip();
            
            sonido.open(AudioSystem.getAudioInputStream(file));
            
            sonido.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(ReproduceAudio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EfectoSirena (int indice){
        
        try {
            File file = EFX.get(indice);
            
            sonido = AudioSystem.getClip();
            
            sonido.open(AudioSystem.getAudioInputStream(file));
            
            sonido.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(ReproduceAudio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void StopSonido(){
        sonido.stop();
    }
    
}