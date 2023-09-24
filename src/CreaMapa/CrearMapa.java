package CreaMapa;





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

public class CrearMapa {

    private static ArrayList<Rectangle> SolidosMapa =new ArrayList<>();
    
    public CrearMapa(ArrayList<Rectangle> SolidosMapa) {
    
        this.SolidosMapa=SolidosMapa;
    
    }
    
    public void CrearMapa1(){
        
        SolidosMapa.clear();
        
        SolidosMapa.add(new Rectangle(30, 60, 820, 10));//Perimetro exteriror Izquierda
        SolidosMapa.add(new Rectangle(30, 590, 820, 10));//Perimetro exteriror Arriba
        SolidosMapa.add(new Rectangle(840, 60, 10, 540));//Perimetro exteriror Abajo
        SolidosMapa.add(new Rectangle(30, 60, 10, 540));//Perimetro exteriror Derecha
        
        //Solidos de la Carcel de los Fantasmas.
        SolidosMapa.add(new Rectangle(430, 270, 20, 10));//Figura 20.....
        SolidosMapa.add(new Rectangle(300, 270, 130, 30));
        SolidosMapa.add(new Rectangle(450, 270, 130, 30));
        SolidosMapa.add(new Rectangle(300, 300, 10, 50));
        SolidosMapa.add(new Rectangle(570, 300, 10, 50));
        SolidosMapa.add(new Rectangle(310, 325, 260, 25));

        SolidosMapa.add(new Rectangle(60, 90, 140, 40));
        SolidosMapa.add(new Rectangle(220, 90, 140, 40));
        SolidosMapa.add(new Rectangle(520, 90, 140, 40));
        SolidosMapa.add(new Rectangle(680, 90, 140, 40));
        SolidosMapa.add(new Rectangle(680, 150, 140, 40));
        SolidosMapa.add(new Rectangle(60, 150, 140, 40));
        SolidosMapa.add(new Rectangle(380, 70, 120, 60));
        SolidosMapa.add(new Rectangle(300, 150, 280, 60));
        SolidosMapa.add(new Rectangle(600, 150, 60, 160));
        SolidosMapa.add(new Rectangle(220, 150, 60, 160));
        SolidosMapa.add(new Rectangle(420, 210, 40, 40));
        SolidosMapa.add(new Rectangle(280, 230, 120, 20));
        SolidosMapa.add(new Rectangle(480, 230, 120, 20));
        SolidosMapa.add(new Rectangle(60, 210, 140, 80));
        SolidosMapa.add(new Rectangle(680, 210, 140, 80));
        SolidosMapa.add(new Rectangle(60, 310, 140, 100));
        SolidosMapa.add(new Rectangle(680, 310, 140, 100));
        SolidosMapa.add(new Rectangle(220, 230, 60, 80));
        SolidosMapa.add(new Rectangle(600, 230, 60, 80));
        SolidosMapa.add(new Rectangle(300, 370, 280, 40));
        SolidosMapa.add(new Rectangle(400, 410, 80, 60));
        SolidosMapa.add(new Rectangle(60, 430, 140, 40));
        SolidosMapa.add(new Rectangle(220, 330, 60, 80));
        SolidosMapa.add(new Rectangle(600, 330, 60, 80));
        SolidosMapa.add(new Rectangle(680, 430, 140, 40));
        SolidosMapa.add(new Rectangle(500, 430, 160, 40));
        SolidosMapa.add(new Rectangle(220, 430, 160, 40));
        SolidosMapa.add(new Rectangle(40, 490, 80, 20));
        SolidosMapa.add(new Rectangle(760, 490, 160, 20));
        SolidosMapa.add(new Rectangle(680, 470, 60, 40));
        SolidosMapa.add(new Rectangle(140, 470, 60, 40));
        SolidosMapa.add(new Rectangle(220, 490, 60, 40));
        SolidosMapa.add(new Rectangle(600, 490, 60, 40));
        SolidosMapa.add(new Rectangle(300, 490, 280, 20));
        SolidosMapa.add(new Rectangle(400, 510, 80, 60));
        SolidosMapa.add(new Rectangle(500, 530, 320, 40));
        SolidosMapa.add(new Rectangle(60, 530, 320, 40));
        
        
    }
    
  
    
    
    public static ArrayList<Rectangle> getSolidosMapa() {
        return SolidosMapa;
    }
}