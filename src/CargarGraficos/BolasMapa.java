package CargarGraficos;





import CreaMapa.CrearMapa;
import java.awt.Rectangle;
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

public class BolasMapa {

    private ArrayList<Rectangle> BolasMap = new ArrayList<>();
    private ArrayList<Integer> BolasMapEstado = new ArrayList<>();
    private ArrayList<Rectangle> SolidosMapa = new ArrayList<>();
    
    public BolasMapa(ArrayList<Rectangle> BolasMap, ArrayList<Integer> BolasMapEstado, ArrayList<Rectangle> SolidosMapa) {
    
        this.BolasMap=BolasMap;
        this.BolasMapEstado=BolasMapEstado;
        this.SolidosMapa=SolidosMapa;
    
    }
    
   
    
    public void GenerarBolasMapa1(){
       
        CrearMapa CrearM = new CrearMapa(SolidosMapa);
        
        CrearM.CrearMapa1();
        
        CrearBolaAzul(445, 475);
        
        CrearBolaVerde(445, 135);
        CrearBolaVerde(545, 572);
        
        CrearBolaRoja(205, 110);
        CrearBolaRoja(445, 255);
        CrearBolaRoja(665, 110);
        CrearBolaRoja(205, 435);
        CrearBolaRoja(665, 435);
        
        for (int x=75;x<580;x+=20){
            G_BolasAuto(x);
        }
        
        
       
    }
   

    
    public void G_BolasAltura(int Altura){
        
        for (int x=45;x<840;x+=60){
            CrearBolaAmarilla(x, Altura);

        }  
    }
    
    
    public void G_BolasAuto(int Altura){
        
        for (int x=45;x<840;x+=20){
            Rectangle SolidoBola= new Rectangle(x, Altura, 10, 10);
            Rectangle SolidoCarce=new Rectangle(310, 300,260,25);
            boolean Solido=false;
            for (Rectangle rec:SolidosMapa){
                if (SolidoBola.intersects(rec) || SolidoBola.intersects(SolidoCarce)){
                     Solido=true;
                }                
            }           
            for (Rectangle rec:BolasMap){
                if (SolidoBola.intersects(rec)){
                    Solido=true;
                }
            }
            if (!Solido){
                CrearBolaAmarilla(x, Altura);  
            }

        }
    }
    

    
    public void CrearBolaAmarilla(int X, int Y){
        BolasMap.add(new Rectangle(X, Y, 10, 10));
        BolasMapEstado.add(1);
    }
    
    public void CrearBolaRoja(int X, int Y){   
        BolasMap.add(new Rectangle(X, Y, 10, 10));
        BolasMapEstado.add(2);
    }
    
    public void CrearBolaVerde(int X, int Y){
        BolasMap.add(new Rectangle(X, Y, 10, 10));
        BolasMapEstado.add(3);
    }
    
    public void CrearBolaAzul(int X, int Y){   
        BolasMap.add(new Rectangle(X, Y, 10, 10));
        BolasMapEstado.add(4);
    }
    
        public void CrearBolaMorada(int X, int Y){   
        BolasMap.add(new Rectangle(X, Y, 10, 10));
        BolasMapEstado.add(5);
    }
    
}
