package LogicaJuego;






import ReproduceAudio.ReproduceAudio;
import java.awt.Rectangle;
import java.util.ArrayList;
import stragabolasdemo.STragaBolasDemo;

/**
 *
 * @author Yacoobs Aprede Java.
 * Esta clase se encarga de Crear, generar movimiento y evaluar condiciones de movimineto de los fantasmas.
 */


public class FantasmasJuego {
    
    private final Rectangle PuertaCalcel = new Rectangle(430, 270, 20, 10);
    private final Rectangle PerimetroCalcel = new Rectangle(301, 299, 278, 49);

//Establece la velocidad del fantasma.
    private int VelocidadFantasma;
    private int Figura;
    private int FiguraOjos;
    
//Establece el tiempo que tarda los fantasmas en salir de la Carcer uno detras de otro.    
    private int TiempoSalida;
    private int TimerFan_Vul=0;
    
    
//Almacena las posiciones Ancho y Alto de los fantasmas en la pantalla grafica.    
    private ArrayList<Integer> Posi_Fantasma_X = new ArrayList<>();
    private ArrayList<Integer> Posi_Fantasma_Y= new ArrayList<>();
    
//Almacena las direcciones que toman los fantamas.    
    private ArrayList<Integer> Movi_Fant_X = new ArrayList<>();
    private ArrayList<Integer> Movi_Fant_Y= new ArrayList<>();
    
//Esta variable booleana se encarga de destruir el ultimo Thread de FantasmasVulnerables que realiza.    
    private ArrayList<Boolean> FinalizarFantasmaVulnerable = new ArrayList<>();
    
    private ArrayList<Integer> Estado_Fantasma= new ArrayList<>();
    
    private ArrayList<Integer> Tipo_Fantasma= new ArrayList<>();
    
    private ReproduceAudio Repro_Au = new ReproduceAudio();
    
//Esta vaiable almacena todas las paredes solidas del Mapa.
    private ArrayList<Rectangle> SolidosMapa = new ArrayList<>();
    
    private STragaBolasDemo JuegoPac;
    
    public FantasmasJuego(STragaBolasDemo JuegoPac, ArrayList<Rectangle> SolidosMapa) {
        this.JuegoPac = JuegoPac;
        this.SolidosMapa = SolidosMapa;   
    }
 
//Este metodo Predefine la cantidad de fantasmas por Level y Crea el tipo y cantidad de Fantasmas que tendran Juego.   
    public void ActivaFantasmas(){
        
        VelocidadFantasma=10;
        TiempoSalida=3;
        Posi_Fantasma_X.clear();
        Posi_Fantasma_Y.clear();
        Movi_Fant_X.clear();
        Movi_Fant_Y.clear();
        Estado_Fantasma.clear();
        FinalizarFantasmaVulnerable.clear();
         
        int tipo=0;
        int posicion=0;
        int cantidadFantasmas=0;
        if (JuegoPac.getNivel()<=4){
            cantidadFantasmas=5;
        }
        if (JuegoPac.getNivel()>=5){
            cantidadFantasmas=8;
        }
        if (JuegoPac.getNivel()>=10){
            cantidadFantasmas=11;
        }
        if (JuegoPac.getNivel()>=15){
            cantidadFantasmas=14;
        }
        if (JuegoPac.getNivel()>=20){
            cantidadFantasmas=17;
        }
        if (JuegoPac.getNivel()>=25){
            cantidadFantasmas=20;//20
        }
        
        for (int numero=0;numero<cantidadFantasmas;numero++){
            CrearFantasma(numero, 330+posicion, tipo);        
            if (tipo>=10){
                tipo=0;
            }
            if (posicion>=210){
                posicion=0;
            }
            posicion+=10;
            tipo++;
        }
        SalirFantasma();
        MovimientoOjos();
    }

//Este metdo Crea un hilo de ejecuion que genera la salida automatica de fantasmas de la carcel por defecto son 3 segundos.    
    public void SalirFantasma(){
        System.out.println("Llamada al metodo salir Fantasmas.......");
        Thread GeneraFan = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!JuegoPac.isFindelJuego() && !JuegoPac.isMuerte() && !JuegoPac.isMapaComplet()){
                    if (!JuegoPac.isPausar()){
                        boolean entrar=true;
                        int indx=0;
                        for (int Estado:Estado_Fantasma){

                            if (Estado==0 && entrar){
                                entrar=false;
                                Estado_Fantasma.set(indx, 1);
                            }
                            indx++;
                        }
                    }
                    double Tiempo = GetTime();
                    while(Tiempo+TiempoSalida>GetTime() && !JuegoPac.isMuerte() && !JuegoPac.isMapaComplet()){
                        try {Thread.sleep(500);} catch (InterruptedException ex) {}    
                    }
                }
            }
        }); 
        GeneraFan.start();
    }

    public void CrearFantasma(int numero, int posicion, int tipo){  
        
        Posi_Fantasma_X.add(posicion);
        Posi_Fantasma_Y.add(302);
        Movi_Fant_X.add(1);
        Movi_Fant_Y.add(0);
        Estado_Fantasma.add(0);
        Tipo_Fantasma.add(tipo);
        MovimientoFantasma(numero);

    }

    public int Direccion(int N){
        int elec=0;
        if (getFan_X(N)==1 || getFan_X(N)==-1){
            elec = (int) (Math.random()*2+2);
        }
        if (getFan_Y(N)==1 || getFan_Y(N)==-1){
            elec = (int) (Math.random()*2);
        }
        return elec;
    }
    
    public void MovimientoOjos(){
        Thread ojos = new Thread(() -> {
            FiguraOjos=0;
            while (!JuegoPac.isMuerte() && !JuegoPac.isMapaComplet()){
                
                if (FiguraOjos>=7){
                    FiguraOjos=0;
                }
                
                try {Thread.sleep(100);} catch (InterruptedException ex) {}
                FiguraOjos++; 
                
            }
            System.out.println("Fin Movimiento Ojos");
            
        });
        ojos.start();
    }
    
    public void MovimientoFantasma(int N){

        Thread fantasma = new Thread(new Runnable() {
            @Override
            public void run() {
                int elec=Direccion(N); 
                do{
                    try {Thread.sleep(VelocidadFantasma);} catch (InterruptedException ex) {}
                    if (EvaluaSolidosFantasma(1,0, N) && elec==0){   //Derecha Fantasma                      
                        Movi_Fant_X.set(N, 1);
                        Movi_Fant_Y.set(N, 0);
                        elec=Direccion(N);
                    }else
                    if (EvaluaSolidosFantasma(-1,0, N) && elec==1){   //Izquierda Fantasma                       
                        Movi_Fant_X.set(N, -1);
                        Movi_Fant_Y.set(N, 0);
                        elec=Direccion(N);
                    }else
                    if (EvaluaSolidosFantasma(0,1, N) && elec==2){   //Baja Fantasma                       
                        Movi_Fant_X.set(N, 0);
                        Movi_Fant_Y.set(N, 1);
                        elec=Direccion(N);
                    }else
                    if (EvaluaSolidosFantasma(0,-1, N) && elec==3){   //Sube el Fantasma                       
                        Movi_Fant_X.set(N, 0);
                        Movi_Fant_Y.set(N, -1);
                        elec=Direccion(N);
                    } 

                }while(EvaluaMovimientoFantasma(N) && !JuegoPac.isMuerte() && !JuegoPac.isMapaComplet());//Fin del bucle While....
                
                if (!JuegoPac.isMuerte() && !JuegoPac.isMapaComplet()){                 

                    if (getFan_X(N)==-1 && !EvaluaSolidosFantasma(0, 1, N) && !EvaluaSolidosFantasma(0, -1, N)){
                        
                        Movi_Fant_X.set(N, 1);
                        
                    }else
                        //Impide que el Fantasma quede bloqueado a la izquierda.
                        if (getFan_X(N)==1 && !EvaluaSolidosFantasma(0, 1, N) && !EvaluaSolidosFantasma(0, -1, N)){
                            
                            Movi_Fant_X.set(N, -1);
                        }
                    //Impide el Fantasma quede bloqueado Abajo.
                    if (getFan_Y(N)==-1 && !EvaluaSolidosFantasma(1, 0, N) && !EvaluaSolidosFantasma(-1, 0, N)){
                        
                        Movi_Fant_Y.set(N, 1);
                    }else
                        if (getFan_Y(N)==1 && !EvaluaSolidosFantasma(1, 0, N) && !EvaluaSolidosFantasma(-1, 0, N)){
                            //Impide que el fantasma quede bloqueado en la puerda de salida de la carcel.
                            if (getP_Fy(N)==280 && getFan_Y(N)==1){
                                Movi_Fant_Y.set(N, 1);
                            }else{
                                Movi_Fant_Y.set(N, -1);
                            }
                        }
                    
                    //System.out.println("Fin "+N);
                    MovimientoFantasma(N);
                }      
            }}); 
        fantasma.start();   
    }
    
    public boolean EvaluaMovimientoFantasma(int N){
        
        int incrementoX = getP_Fx(N)+getFan_X(N);
        int incrementoY = getP_Fy(N)+getFan_Y(N);
        int PacManPoX = JuegoPac.getPosicion_X()+3;
        int PacManPoY = JuegoPac.getPosicion_Y()+3;
        
        Rectangle Posicion_Fantasma = new Rectangle(incrementoX, incrementoY, 20, 20);
        Rectangle Peri_Pacman = new Rectangle(PacManPoX, PacManPoY, 14, 14);
        
        //Evalua si el fantasma y PacMan se interceptan entre ellos 
        if (Peri_Pacman.intersects(Posicion_Fantasma)){
            
            //PacMan come un Fantasma......
            if (Estado_Fantasma.get(N)==3){
                Posi_Fantasma_X.set(N, 335);
                Posi_Fantasma_Y.set(N, 302);
                Movi_Fant_X.set(N, 1);
                Movi_Fant_Y.set(N, 0);
                Estado_Fantasma.set(N, 0);
                Repro_Au.Fx(4);
                JuegoPac.AnimacionPuntos();
                return false;
            }
            //PacMan muere y le resta una vida.
            JuegoPac.setMuerte(true);
            JuegoPac.RestarVida();
            return false;    
        }
        //Evita que los fantamas Estado 0 salgan de la Carcel.
        if (Posicion_Fantasma.intersects(PuertaCalcel) && Estado_Fantasma.get(N)==0){
            Movi_Fant_Y.set(N, 1);
            return false;                  
        }     

        for (Rectangle solido:SolidosMapa){
            //Compara la posicion del Fantasma con todos los solidos menos con la puerta de la Carcel Fantasma.
            if (Posicion_Fantasma.intersects(solido) && !solido.intersects(PuertaCalcel)){ 
                return false;
            }  
        }
        //Incrementamos la posicion del fantasma siempre y cuando no intercepte una pared solida.
        if (!JuegoPac.isPausar()){
            Posi_Fantasma_X.set(N, incrementoX);
            Posi_Fantasma_Y.set(N, incrementoY);
        }
        return true;
    }

    public boolean EvaluaSolidosFantasma(int x, int y, int N){
        
        Rectangle Posicion_Fantasma = new Rectangle(getP_Fx(N)+x, getP_Fy(N)+y, 20, 20);
        for (Rectangle solido:SolidosMapa){

            if (Posicion_Fantasma.intersects(solido)){   
                return false;
            }
        }
        return true;
    }
    
    public void FantasmasVulnerables(){

        Thread Fantas = new Thread(new Runnable() {
            @Override
            public void run() {
                FinalizarFantasmaVulnerable.add(false);
                int Vulnerables = FinalizarFantasmaVulnerable.size()-1;
                if (Vulnerables!=0){
                    FinalizarFantasmaVulnerable.set(Vulnerables-1, true);
                }

                for (int indx=0;indx<Estado_Fantasma.size();indx++){
                    Estado_Fantasma.set(indx, 3);
                }
                double Tiempo = GetTime()+10;
                Figura=0;
                int velocidadM = 10;
                JuegoPac.setVelocidadPacman(7);
                if (VelocidadFantasma!=30 && !JuegoPac.isCongelacion()){
                    velocidadM = VelocidadFantasma;
                }
                while(Tiempo>GetTime() && !JuegoPac.isMuerte() && !JuegoPac.isMapaComplet() 
                        && !FinalizarFantasmaVulnerable.get(Vulnerables)){
                    VelocidadFantasma=30;
                    TimerFan_Vul=(int) (Tiempo-GetTime());
                    if (!JuegoPac.isSuperPacMan()){
                        JuegoPac.setVelocidadPacman(7);
                    }
                    try {Thread.sleep(200);} catch (InterruptedException ex) {}
                    if (Figura>2){
                        Figura=0;
                    }
                    Figura++;   
                }                
                if (!FinalizarFantasmaVulnerable.get(Vulnerables)){
                    for (int indx=0;indx<Estado_Fantasma.size();indx++){
                        //Estado 0 no pueden salir de la carcel estado 1 Salen de la carcel....
                        Rectangle Posicion_Fantasma = new Rectangle(getP_Fx(indx), getP_Fy(indx), 20, 20);
                        if (!Posicion_Fantasma.intersects(PerimetroCalcel)){
                            Estado_Fantasma.set(indx, 1);
                        }else{
                            Estado_Fantasma.set(indx, 0);
                        }                        
                        VelocidadFantasma=velocidadM;
                        TimerFan_Vul=0;
                        if (!JuegoPac.isSuperPacMan()){
                            JuegoPac.setVelocidadPacman(9);
                        }
                    }
                }
                //System.out.println("Fin de Fantasmas Vulnerables...............");
            }});
        Fantas.start();
        
    }
    
    //Este metodo Activa la EXTERMINACION total de todos los fantasmas Volviendo todos a la Carcel.    
    public void SuperBomba(){
        Repro_Au.Fx(3);
        for (int indx=0;indx<Estado_Fantasma.size();indx++){
            
            Estado_Fantasma.set(indx, 0);
            Posi_Fantasma_X.set(indx,330);
            Posi_Fantasma_Y.set(indx,302);
            Movi_Fant_X.set(indx,1);
            Movi_Fant_Y.set(indx,0);
            
        }
   
    }
    
    
//Metodos Getter del Juego.
/** 
     * @return Este metodo devuelve la Cantidad de Fantasmas que hay en movimiento en el mapa.
*/
    public int getFantasmas(){
        return Estado_Fantasma.size();
    }

    public ArrayList<Integer> getEstado_Fantasma() {
        return Estado_Fantasma;
    }
    
    public int getFan_X(int indice){
        return Movi_Fant_X.get(indice);
    }
    
    public int getFan_Y(int indice){
        return Movi_Fant_Y.get(indice);
    }
    
    public int getP_Fx(int indice){
        return Posi_Fantasma_X.get(indice);
    }
    
    public int getP_Fy(int indice){
        return Posi_Fantasma_Y.get(indice);
    }

    public ArrayList<Integer> getPosi_Fantasma_X() {
        return Posi_Fantasma_X;
    }

    public ArrayList<Integer> getPosi_Fantasma_Y() {
        return Posi_Fantasma_Y;
    }

    public ArrayList<Integer> getTipo_Fantasma() {
        return Tipo_Fantasma;
    }

    public int getVelocidadFantasma() {
        return VelocidadFantasma;
    }

    public int getFigura() {
        return Figura;
    }
    
    public double GetTime(){
       return System.nanoTime()/1000000000;
    }

    public int getTimerFan_Vul() {
        return TimerFan_Vul;
    }

    public int getFiguraOjos() {
        return FiguraOjos;
    }
    
    

    
//Metodos Setter del Juego.    
    public void setVelocidadFantasma(int VelocidadFantasma) {
        this.VelocidadFantasma = VelocidadFantasma;
    }

    
    
}

