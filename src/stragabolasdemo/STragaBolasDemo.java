package stragabolasdemo;


import LogicaJuego.FantasmasJuego;
import CargarGraficos.BolasMapa;
import CargarGraficos.Imagenes_Juego;
import CargarGraficos.GraficosLetrasNumeros;
import ReproduceAudio.ReproduceMusic;
import ReproduceAudio.ReproduceAudio;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;




/**
 *
 * @author Yacoobs C. M.
 * Clase Principal del juego.
 * Juego Simulacion Pacman....
 * 16:38 07 enero del 2018
 * Ultima modificacion 22-1-2018
 * @version V.0.7  DEMO
 */

public final class STragaBolasDemo extends JFrame{

    private final String version;
    
    //Variables que establecen las dimensiones del Frame.
    private final int frame_Ancho, frame_Alto;
    
    private int puntosJuego, vidas, posicion, velocidadPacman, puntosExtra, mapaJuego, extraVida, fanConge, movi_X, movi_Y, posicion_X, posicion_Y
            ,indiceMuerte, timerSuperPacMan, timerCongelaFan;
    
    
    private boolean findelJuego, pausar, movimientoPacMan, empezarJuego, muerte, MapaComplet, superPacMan, congelacion;

    private Color colorPuntos;

    private final Font fuente;
    
    private final Font fuente1;
    
    //Variables que almacenan las imagenes graficas del juego.
    private final ArrayList<BufferedImage> Img_Enemigo, Img_PacD, Img_PacI, Img_PacA, Img_PacB, Img_Bolas, Img_Mapa, Img_FanVulnerable, 
            Img_FanCongelado, Img_OjosFantas, Img_AnimaPacMuerte;
    
    
    private final ArrayList<Rectangle> SolidosMapa, BolasMap, PosicionPuntos;

    private final ArrayList<Integer> PuntosFantasma, BolasMap_Estado;

    private final ArrayList<Boolean> FinalizaDirecPac, FinalizaMoviPac, FinalizaSuperPac, FinalizaCongelado; 
    

    
    
    //Intancias de Clases externas del juego.
    private final Imagenes_Juego Cl_Img_Ju;
    
    private final GraficosLetrasNumeros Cl_Gra_LeNu;
    
    private final ReproduceAudio Cl_Repro_Au;
    
    private final FantasmasJuego Cl_FantS;
    
    private final BolasMapa Cl_BolM;
    
    private final FondoGrafico Cl_FGra;

    private final ReproduceMusic Cl_ReproMus;
    


    public STragaBolasDemo(){
        
        this.FinalizaCongelado = new ArrayList<>();
        
        this.FinalizaSuperPac = new ArrayList<>();
        
        this.FinalizaMoviPac = new ArrayList<>();
        
        this.FinalizaDirecPac = new ArrayList<>();
        
        this.PuntosFantasma = new ArrayList<>();
        
        this.PosicionPuntos = new ArrayList<>();
        
        this.BolasMap_Estado = new ArrayList<>();
        
        this.BolasMap = new ArrayList<>();
        
        this.SolidosMapa = new ArrayList<>();
        
        this.Img_AnimaPacMuerte = new ArrayList<>();
        
        this.Img_OjosFantas = new ArrayList<>();
        
        this.Img_FanCongelado = new ArrayList<>();
        
        this.Img_FanVulnerable = new ArrayList<>();
        
        this.Img_Mapa = new ArrayList<>();
        
        this.Img_Bolas = new ArrayList<>();
        
        this.Img_PacB = new ArrayList<>();
        
        this.Img_PacA = new ArrayList<>();
        
        this.Img_PacI = new ArrayList<>();
        
        this.Img_PacD = new ArrayList<>();
        
        this.Img_Enemigo = new ArrayList<>();
        

        
        this.empezarJuego = false;
        
        this.findelJuego = false;
        
        this.movimientoPacMan = true;
        
        this.muerte = false;
        
        this.superPacMan = false;
        
        this.congelacion = false;

        this.pausar = false;
        
        this.posicion_X = 425;//425 Posicion inicial del Jugador
        
        this.posicion_Y = 570;//570
        
        this.extraVida = 8000;
        
        this.movi_X = 0;
        
        this.movi_Y = 0;
        
        
        this.velocidadPacman = 9;
        
        this.vidas = 3;
        
        this.puntosJuego = 0;
        
        this.mapaJuego = 5;
        
        this.colorPuntos = Color.WHITE;
        
        this.frame_Ancho = 900;
        
        this.frame_Alto = 735;
        
        this.version = "SupeR TragaBolas V.0.7 DEMO solo un Mapa";

        this.puntosExtra = 100;
         
        this.fuente = new Font("Verdana",Font.BOLD,11);
        
        this.fuente1 = new Font("Verdana",Font.BOLD,12);
        
        
        //Intancias de CLASE.
        this.Cl_ReproMus = new ReproduceMusic();
        
        this.Cl_FGra = new FondoGrafico();
        
        this.Cl_BolM = new BolasMapa(BolasMap, BolasMap_Estado, SolidosMapa);
        
        this.Cl_FantS = new FantasmasJuego(this, SolidosMapa);
        
        this.Cl_Repro_Au = new ReproduceAudio();
        
        this.Cl_Gra_LeNu = new GraficosLetrasNumeros();
        
        this.Cl_Img_Ju = new Imagenes_Juego();

        
        Cl_Img_Ju.CargarMapa(Img_Mapa);
        
        Cl_Img_Ju.CargarAnimacionMuertePacman(Img_AnimaPacMuerte);  
        
        Cl_Img_Ju.CargarEnemigo(Img_Enemigo);
        
        Cl_Img_Ju.CargarPacAbajo(Img_PacB);
        
        Cl_Img_Ju.CargarPacArriba(Img_PacA);
        
        Cl_Img_Ju.CargarPacDerecha(Img_PacD);
        
        Cl_Img_Ju.CargarPacIzquierda(Img_PacI);
        
        Cl_Img_Ju.CargarBolas(Img_Bolas);
        
        Cl_Img_Ju.CargarFanVulnerable(Img_FanVulnerable);
        
        Cl_Img_Ju.CargarFanCongelados(Img_FanCongelado);
        
        Cl_Img_Ju.CargarOjosFantas(Img_OjosFantas);

        
        
        
        
        setBackground(Color.BLACK);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setBounds(0, 0, frame_Ancho, frame_Alto);
        
        setLocationRelativeTo(null);
        
        setResizable(false);
        
        setTitle(version);
        
        
        add(Cl_FGra);
        
        setVisible(true);
 
        CrearBucle();
        
        Control_Jugador();
        
        //Cl_ReproMus.Reproducir("MusicaTema.mp3"); 
        
    }
    
 
    
    private class FondoGrafico extends JPanel{
        
        @Override
        public void paint(Graphics g){
          
             Graphics2D g2 = (Graphics2D) g;

            //Dibuja un fondo Negro total al JFrame.
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, getWidth(), getHeight());
            

            if (empezarJuego){
                
                //Dibuja el perimetro del mapa.........
                if (mapaJuego<=1){
                    g2.drawImage(Img_Mapa.get(1), 0, 0, null);
                }
//                if (mapaJuego>=2){
//                    g2.drawImage(Img_Mapa.get(2), 0, 0, null);
//                }
//                if (mapaJuego>=3){
//                    g2.drawImage(Img_Mapa.get(3), 0, 0, null);
//                }
//                if (mapaJuego>=4){
//                    g2.drawImage(Img_Mapa.get(4), 0, 0, null);
//                }
//                if (mapaJuego>=5){
//                    g2.drawImage(Img_Mapa.get(5), 0, 0, null);
//                }
//                if (mapaJuego>=6){
//                    g2.drawImage(Img_Mapa.get(6), 0, 0, null);
//                }
            
                //Dibuja las bolas graficas del juego en sus dos estados.
                try{
                    int indx=0;
                    for (Rectangle rec:BolasMap){
                        int Estado = BolasMap_Estado.get(indx);
                        int x= (int) rec.getX();
                        int y= (int) rec.getY();             
                        if (Estado==1){
                            g2.drawImage(Img_Bolas.get(0), x, y,null);
                        }
                        if (Estado==2){
                            g2.drawImage(Img_Bolas.get(1), x, y,null);
                        }
                        if (Estado==3){
                            g2.drawImage(Img_Bolas.get(2), x, y,null);
                        }
                        if (Estado==4){
                            g2.drawImage(Img_Bolas.get(3), x, y,null);
                        }
                        if (Estado==5){
                            g2.drawImage(Img_Bolas.get(4), x, y,null);
                        }
                        indx++;
                    } 
                }catch(ConcurrentModificationException e){
                    System.out.println("Error Dibuja las bolas Graficas del juego");
                }

                //Dibuja informacion del juego......... 
                Cl_Gra_LeNu.Dibuja_Texto_Grafico(g2, 50, 20, 20, 20, 20, "Puntos " + puntosJuego);

                Cl_Gra_LeNu.Dibuja_Texto_Grafico(g2, 350, 20, 20, 20, 20, "Level " + mapaJuego);

                //Dibuja las vidas de jugador PAcMan.
                int incre=0;
                for (int V=0;V<vidas;V++){
                    
                    g2.drawImage(Img_PacD.get(0), 650 + incre, 25,null);
                    
                    if (incre<120){
                        
                        incre+=30;
                    }if (vidas>5){
                        
                        g2.setColor(Color.WHITE);
                        
                        g2.setFont(fuente1);
                        
                        g2.drawString("" + vidas, 800, 40);
                    }  
                }

                //Dibuja la posicion inicial de Pacman......
                if (movi_X==0 && movi_Y==0 && !muerte){
                    g2.drawImage(Img_PacD.get(0), 425, 570, null);
                }

                if (!muerte){

                    //Dibuja el muñeco grafico de TragaBolas.........................
                    try{
                        int selec=0;
                        if (superPacMan){
                            selec=24;
                        }

                        if (movi_X==-1){
                            g2.drawImage(Img_PacI.get(posicion+selec), posicion_X, posicion_Y, null);
                        }
                        if (movi_X==1){
                            g2.drawImage(Img_PacD.get(posicion+selec), posicion_X, posicion_Y, null);
                        }
                        if (movi_Y==-1){
                            g2.drawImage(Img_PacA.get(posicion+selec), posicion_X, posicion_Y, null);
                        }
                        if (movi_Y==1){
                            g2.drawImage(Img_PacB.get(posicion+selec), posicion_X, posicion_Y, null);
                        }
                    }catch(
                            IndexOutOfBoundsException e){System.out.println("Error de indices...");
                    }


                    //Dibuja el Fantasma grafico en el juego.....................
                    for (int Indx=0;Indx<Cl_FantS.getFantasmas();Indx++){

                        int PosicionFantasmaX = Cl_FantS.getPosi_Fantasma_X().get(Indx);
                        int PosicionFantasmaY = Cl_FantS.getPosi_Fantasma_Y().get(Indx);
                        int TipoFantasma = Cl_FantS.getTipo_Fantasma().get(Indx);
                        int EstadoFantasma = Cl_FantS.getEstado_Fantasma().get(Indx);

                        if (!congelacion){
                            if (EstadoFantasma==3){
                                g2.drawImage(Img_FanVulnerable.get(Cl_FantS.getFigura()),PosicionFantasmaX ,PosicionFantasmaY , null);
                            }
                            if (EstadoFantasma==0){
                                g2.drawImage(Img_OjosFantas.get(Cl_FantS.getFiguraOjos()),PosicionFantasmaX ,PosicionFantasmaY , null);
                            }
                            
                            if (EstadoFantasma==1){
                                g2.drawImage(Img_Enemigo.get(TipoFantasma),PosicionFantasmaX ,PosicionFantasmaY , null);  
                            }
                        }else{
                            g2.drawImage(Img_FanCongelado.get(fanConge), PosicionFantasmaX, PosicionFantasmaY,this);
                        }
                    }

                    //Dibuja los puntos al eliminar un Fantasma en el juego.
                    int idx=0;
                    //Aqui hay un Error ConcurrentModificationException
                    try{
                        for (Rectangle rec:PosicionPuntos){
                            int X=(int) rec.getX();
                            int Y=(int) rec.getY();
                            g2.setColor(colorPuntos);
                            g2.setFont(new Font("Verdana",Font.BOLD,9));
                            g2.drawString(""+PuntosFantasma.get(idx), X, Y);
                            idx++;
                        }  
                    }catch(ConcurrentModificationException e){
                        System.out.println("Error puntos");
                    }
                }

                if (findelJuego){ 
                    Cl_Gra_LeNu.Dibuja_Texto_Grafico(g2, 150, 300, 40, 40, 40, "Fin del Juego");
                }
                try{
                    if (muerte){  
                            g2.drawImage(Img_AnimaPacMuerte.get(indiceMuerte), posicion_X, posicion_Y, null);    
                        }
                }catch(IndexOutOfBoundsException e){
                    System.out.println("Error sobre cargamiento indice AnimacionPacMuerte");
                }
                
                if (MapaComplet){
                    Cl_Gra_LeNu.Dibuja_Texto_Grafico(g2, 70, 300, 50, 50, 50, "Mapa Completado");
                } 

                //Muestra la cuenta atras cuando los Fantasmas son Vulnerables.
                if (Cl_FantS.getTimerFan_Vul()!=0){
                    Cl_Gra_LeNu.Dibuja_Texto_Grafico(g2, 420, 290, 30, 30, 20, "" + Cl_FantS.getTimerFan_Vul());
                }
                
                //Muestra la cuenta atras cuando es Super TragaBolas.
                if (superPacMan){
                    g2.drawImage(Img_PacD.get(posicion+24), 790, 615, null);
                    Cl_Gra_LeNu.Dibuja_Texto_Grafico(g2, 810, 615, 30, 30, 20, "" + timerSuperPacMan);
                }
                
                //Muestra la cuenta atras cuando los Fantasmas estan Congelados.
                if (congelacion){
                    g2.drawImage(Img_FanCongelado.get(fanConge), 790, 650, null);
                    Cl_Gra_LeNu.Dibuja_Texto_Grafico(g2, 810, 650, 30, 30, 20, "" + timerCongelaFan);
                }

 
            }
            
            //Dibuja el titulo del Juego Traga.
            g2.drawImage(Img_Mapa.get(0), 100, 600, null);
            
            //Mensaje de Reinicio del Juego.
            g2.setFont(fuente);
            g2.setColor(Color.WHITE);
            g2.drawString("Pulse Ctrl+R para Reiniciar.", 340, 10);

            if (!empezarJuego){

               g2.setColor(Color.WHITE);
               g2.setFont(new Font("Comic Sans Ms", Font.BOLD, 40));
               g2.drawString("Pulsa F1 para empezar el juego", 100, 100);
               g2.setColor(Color.RED);
               g2.drawString("Pulsa F1 para empezar el juego", 102, 102);
               
               g2.setColor(Color.WHITE);
               g2.setFont(new Font("Comic Sans Ms", Font.BOLD, 40));
               g2.drawString("CTRL + Tecla Up Cerrar Juego", 100, 200);
               g2.setColor(Color.GRAY);
               g2.drawString("CTRL + Tecla Up Cerrar Juego", 102, 202);
               
               g2.setColor(Color.WHITE);
               g2.setFont(new Font("Comic Sans Ms", Font.BOLD, 40));
               g2.drawString("CTRL + R Reiniciar Juego", 100, 300);
               g2.setColor(Color.GRAY);
               g2.drawString("CTRL + R Reiniciar Juego", 102, 302);
               
               g2.setColor(Color.WHITE);
               g2.setFont(new Font("Comic Sans Ms", Font.BOLD, 40));
               g2.drawString("Teclas Cursor control Juego", 100, 400);
               g2.setColor(Color.GRAY);
               g2.drawString("Teclas Cursor control Juego", 102, 402);
               
               
               
            }

            if (pausar){
                Cl_Gra_LeNu.Dibuja_Texto_Grafico(g2, 120, 300, 50, 50, 50, "Juego Pausado");
            }
            
            
        }
    }

    
    /** Pone a la escucha las teclas que intervienen en el juego.*/
    public void Control_Jugador(){
        
        Cl_FGra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println(e.getX());
                
                //System.out.println(e.getY());
            }
        });

        addKeyListener(new KeyAdapter() {
            
            

            @Override
            public void keyPressed(KeyEvent e) {

                int evt = e.getKeyCode();
                
                if (e.isControlDown() && evt == KeyEvent.VK_R){
                    Cl_Repro_Au.Fx(6);
                    
                    empezarJuego = false;
                    
                    pausar = false;
                    
                    MapaComplet = false;
                    
                    findelJuego = true;
                    
                    muerte = true;
                    
                    
                    try {Thread.sleep(500);} catch (InterruptedException ex) {}                                  

                    BolasMap.clear();
                    
                    BolasMap_Estado.clear();
                    
                    FinalizaDirecPac.clear();
                    
                    FinalizaMoviPac.clear();
                    
                    PosicionPuntos.clear();
                    
                    PuntosFantasma.clear(); 
                    
                } 
                //System.out.println(e.getKeyCode());
                
                //Tecla Skape pausa el juego........
                if (evt == 27 && empezarJuego){
                    pausar = !pausar;
                }
                //Tecla 1.....
                if (evt == 112 && !empezarJuego){   
                    EmpezarJuego();
                }
                //Tecla 2.....
                if (e.isControlDown() && evt == KeyEvent.VK_UP){   
                    System.exit(0);
                }
                
                if (empezarJuego && !muerte && !findelJuego && !MapaComplet){

                    //Tecla cursor arriba.........
                    if (evt==38 && movi_Y !=-1){                       
                        AccionTeclado(0, -1, 0, -1);                 
                    }

                    //Tecla cursor abajo..........
                    if (evt==40 && movi_Y !=1){                        
                        AccionTeclado(0, 1, 0, 1);                 
                    }

                    //Tecla cursor izquierda......
                    if (evt==37 && movi_X !=-1){                        
                        AccionTeclado(-1, 0, -1, 0);    
                    }

                    //Tecla cursor Derecha........
                    if (evt==39 && movi_X !=1){                        
                        AccionTeclado(1, 0, 1, 0);  
                    }    
                }
            }});
    }
    
    
    /**Este metodo controla  el comienzo del juego Genera las bolas, Astiva los fantasmas y llama al metodo movimiento Pacman*/    
    public void EmpezarJuego() {
        
        velocidadPacman = 9;
        
        vidas = 3;
        
        puntosJuego = 0;
        
        mapaJuego = 1;

        empezarJuego = true;
        
        findelJuego = false;
        
        movimientoPacMan = true;
        
        muerte = false;
        
        superPacMan = false;
        
        congelacion = false;
        
        
        
        BolasMap.clear();
        
        BolasMap_Estado.clear();
        
        FinalizaDirecPac.clear();
        
        FinalizaMoviPac.clear();
        
        FinalizaSuperPac.clear();
        
        PosicionPuntos.clear();
        
        PuntosFantasma.clear();
        
        posicion_X=425;//425
        
        posicion_Y=570;//570
        
        extraVida=8000;
        
        movi_X=0;
        
        movi_Y=0; 
        
        Cl_FantS.ActivaFantasmas();
        
        ControlMap();
        
    }
    

    /* Este metodo genera un bucle evaluando los solidos del mapa cuando este no encuentra salido realiza un nuevo movimiento de pacman. */   
    public void AccionTeclado(int x, int y, int M_x, int M_y){
        
        Thread accion = new Thread(() -> {

            FinalizaDirecPac.add(false);
            
            int tamanoThread = FinalizaDirecPac.size()-1;
            
            if (tamanoThread >= 1){

                FinalizaDirecPac.set(tamanoThread-1, true); 
            }           
            
            while(!MovimientoTecladoEvaluaSolidos(x, y) && !FinalizaDirecPac.get(tamanoThread) && !MapaComplet){

                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                    System.out.println("Time Error sin importancia");
                }
            }
            
            //System.out.println("Fin del Thread:  " + FinalizaDirecPac.size());
            
            //Siempre cuando MovimientoPacman sea true este no genera otro nuevo movimiento.
            if (movimientoPacMan && !MapaComplet){
                
                MovimientoPacMan();
            }
            
            
            if (!FinalizaDirecPac.get(tamanoThread)){
                
                movi_X = M_x;
                
                movi_Y = M_y;
            }
            
            
        });
        
        accion.start();
    }
    
    

    /** Genera un Thread dentro de un bucle while() esto crea el movimiento de TragaBolas.*/
    public void MovimientoPacMan(){  
        
        Thread pac = new Thread(() -> {
            
            FinalizaMoviPac.add(false);
            
            int tamanoMovi = FinalizaMoviPac.size()-1;
            
            if (tamanoMovi!=0){
                
                FinalizaMoviPac.set(tamanoMovi-1, true);
            }
            posicion = 0;
            
            while(Evalua_MovimientoSolido() && !findelJuego && !muerte && !MapaComplet && !FinalizaMoviPac.get(tamanoMovi)){
                
                try {
                    Thread.sleep(velocidadPacman);
                } catch (InterruptedException ex) {
                    System.out.println("Error Movimiento Pacman");
                }
                
                movimientoPacMan = false;
                
                if (!pausar){
                    
                    BolasJuego();
                    
                    if (posicion >= 23){
                        
                        posicion = 0;
                    }
                    posicion++;
                }
            }
            posicion=0;
            movimientoPacMan=true;
        });
        pac.start();
    }
    
    

    //Evalua el proximo movimiento echo por la tecla evaluando cuando podra realizar dicho movimiento.    
    public boolean MovimientoTecladoEvaluaSolidos(int x, int y){
        Rectangle Peri_Pacman = new Rectangle(posicion_X + x, posicion_Y + y, 20, 20);
        for (Rectangle solido:SolidosMapa){
            
            if (Peri_Pacman.intersects(solido)){            
                return false;
            }  
        }
        return true;
    } 

    //Evalua los movimientos de pacman sobre el mapa y interrumpe el movimiento cuando este intercepta un solido.    
    public boolean Evalua_MovimientoSolido(){
        Rectangle Peri_Pacman = new Rectangle(posicion_X+movi_X, posicion_Y+movi_Y, 20, 20);

        for (Rectangle solido:SolidosMapa){
            
            if (Peri_Pacman.intersects(solido)){            
                return false;
            }     
        }
        if (!pausar){
            posicion_X+=movi_X;
            posicion_Y+=movi_Y; 
        }
        return true;
    }   
    

    //Este metodo Evalua la posicion de las bolas respecto a la de pacMan para elimirnar dicha bola al pasar por encima.    
    public void BolasJuego(){
        Rectangle Peri_Pacman = new Rectangle(posicion_X, posicion_Y, 10, 10);
        int indx=0;
        int BolasEliminadas=0;
        for (int Estado:BolasMap_Estado){
            //Las bolas estado 1 son las de color amarilla.
            Rectangle Peri_Bolas = new Rectangle(BolasMap.get(indx));
            if (Estado==1){
                if (Peri_Pacman.intersects(Peri_Bolas)){
                    //Repro_Au.Fx(2);
                    Cl_ReproMus.Reproducir("Efecto2.mp3");
                    BolasMap_Estado.set(indx, 0);
                    if (superPacMan){
                        puntosJuego+=15;
                    }else{
                        puntosJuego+=10;
                    }   
                }  
            }
            //Las bolas estado 2 son las de color Rojo.
            if (Estado==2){
                if (Peri_Pacman.intersects(Peri_Bolas)){
                    Cl_Repro_Au.Fx(0);
                    BolasMap_Estado.set(indx, 0);
                    puntosJuego+=50;
                    Cl_FantS.FantasmasVulnerables();
                }
            }
            //La bola estado 3 son de color Verde.
            if (Estado==3){
                if (Peri_Pacman.intersects(Peri_Bolas)){
                    Cl_Repro_Au.Fx(7);
                    BolasMap_Estado.set(indx, 0);
                    puntosJuego+=20;
                    SuperPacMan();
                }
            }
            //La bola estado 4 son de color Azul.
            if (Estado==4){
                if (Peri_Pacman.intersects(Peri_Bolas)){
                    Cl_Repro_Au.Fx(11);
                    BolasMap_Estado.set(indx, 0);
                    puntosJuego+=25;
                    FantCongelado();
                }
            }
            
            if (Estado==5){
                if (Peri_Pacman.intersects(Peri_Bolas)){    
                    BolasMap_Estado.set(indx, 0);
                    puntosJuego+=115;
                    Cl_FantS.SuperBomba();
                }
            }

            //Las bolas estado 0 son las que no se muestran en el mapa ya que fueron comidas por TragaBolas. 
            if (Estado==0){
                BolasEliminadas++;
                //Esta condicion se cumple cuando las bolas eliminadas y el Array de bolas estado son iguales. 
                if (BolasEliminadas==BolasMap_Estado.size()){
                    MapCompletado();
                }
            }      
            indx++;
        }
        //Evalua la condicion para llamar al metodo de la ExtraVida.
        if (puntosJuego>=extraVida){
            ExtraVida();
        }
    }
    

    //Este metodo Activa el estado Congelado de los Fantasmas, pero siempre y cuando no esten en estado Vulnerables.    
    public void FantCongelado(){
        
        Thread Ralentizados = new Thread(() -> {
            int Estado=Cl_FantS.getEstado_Fantasma().get(0);
            //Si los fantasmas estan en estado Vulnerable la congelacion no hace ningun efecto.
            if (Estado!=3){
                
                FinalizaCongelado.add(false);
                int tamanoMovi = FinalizaCongelado.size()-1;
                if (tamanoMovi!=0){
                    FinalizaCongelado.set(tamanoMovi-1, true);
                }
                
                congelacion=true;
                fanConge=0;
                Cl_FantS.setVelocidadFantasma(40);
                double Tiempo = GetTime()+15;
                while(Tiempo>GetTime() && !MapaComplet && !muerte && Estado!=3 && !FinalizaCongelado.get(tamanoMovi)){
                    Estado = Cl_FantS.getEstado_Fantasma().get(0);
                    timerCongelaFan = (int) (Tiempo-GetTime());
                    try {Thread.sleep(200);} catch (InterruptedException ex) {}
                    if (fanConge>=8){
                        fanConge=0;
                    }
                    fanConge++;
                }
                if (Estado!=3 && !FinalizaCongelado.get(tamanoMovi)){
                    Cl_FantS.setVelocidadFantasma(10);
                    congelacion=false;
                }
                if (Estado==3){
                    congelacion=false;
                }
                fanConge=0;
            }
            System.out.println("Fin congelacion");
        });
        Ralentizados.start(); 
        
        
    }

 
    //Este metodo cambia el estado de PAcman normal a SuperPacman lo que hace que se vuelva Verde y pueda mover se mas rapido por el mapa.    
    public void SuperPacMan(){
        
        Thread Fantas = new Thread(() -> {
            double Tiempo = GetTime()+15;
            FinalizaSuperPac.add(false);
            int tamanoMovi = FinalizaSuperPac.size()-1;
            if (tamanoMovi!=0){
                FinalizaSuperPac.set(tamanoMovi-1, true);
            }
            
            while(Tiempo>GetTime() && !MapaComplet && !muerte && !FinalizaSuperPac.get(tamanoMovi)){
                superPacMan=true;
                timerSuperPacMan = (int) (Tiempo-GetTime());
                velocidadPacman = 5;
                
                try {Thread.sleep(100);} catch (InterruptedException ex) {}
            }
            velocidadPacman=9;
            superPacMan=false;
        });
        Fantas.start();   
    }
    
    //Este metodo reinicia el mapa cuando pacman come todas las bolas del mapa.
    public void MapCompletado(){
        
        MapaComplet=true;
        
        Thread pac = new Thread(() -> {
            Cl_Repro_Au.Fx(8);
            
            try {Thread.sleep(5500);} catch (InterruptedException ex) {}
            
            posicion_X=425;
            
            posicion_Y=570;
            
            movi_X=0;
            
            movi_Y=0;
            
            velocidadPacman=9;
            
            //mapaJuego +=1; //Solo para mas mapas...
            
            mapaJuego = 1;
            
            BolasMap.clear();
            
            BolasMap_Estado.clear();
            
            FinalizaDirecPac.clear();
            
            FinalizaMoviPac.clear();
            
            FinalizaSuperPac.clear();
            
            PosicionPuntos.clear();
            
            PuntosFantasma.clear();
            
            MapaComplet=false;
            
            Cl_FantS.ActivaFantasmas();
            
            ControlMap();
        });
        pac.start();      
    }
 
    //Este metodo de vuelve una cadena de numeros almacenados en un Array Ordenadamente.    
    public ArrayList<Integer> OrdenarPuntos(ArrayList<Integer> Lista){
        int Copi, Minimo_N;
        int Tamano=Lista.size();
        for (int i=0;i<Tamano;i++){
            Minimo_N=i;
            for (int j=i+1;j<Tamano;j++){
                if (Lista.get(j)< Lista.get(Minimo_N)){
                    Minimo_N=j;
                }
            }
            Copi=Lista.get(i);
            Lista.set(i, Lista.get(Minimo_N));
            Lista.set(Minimo_N, Copi);
        }
        return Lista;
    }


    //Estemetodo se encarga de restar una vida al juego.    
    public void RestarVida(){
        AnimacionMuertePacman();     
        Cl_Repro_Au.Fx(1);
        vidas-=1;
        if (vidas==0){
            findelJuego=true;
            Cl_Repro_Au.Fx(10);
            try {Thread.sleep(4000);} catch (InterruptedException ex) {}
            empezarJuego=false;
            muerte=true;
        }
                  
    }
    
    //Genera una animacion cuando Pacman es interceptado por un Fantasma.    
    public void AnimacionMuertePacman(){
        indiceMuerte=0;
        Thread MuertePac = new Thread(() -> {
            while(indiceMuerte<25){
                try{Thread.sleep(100);} catch (InterruptedException ex) {}
                indiceMuerte++;
            }
            if (!findelJuego){
                muerte=false;
                Cl_FantS.ActivaFantasmas();
            }
            movi_X=0;
            movi_Y=0;
            posicion_X=425;
            posicion_Y=570;
            velocidadPacman=9;
            indiceMuerte=0;
        });
        MuertePac.start();
    }
    
    //Genera una animacion de Puntos cuando PacMan come un Fantasma.
    public void AnimacionPuntos(){
        Thread EfecPuntos = new Thread(() -> {
            PuntosFantasma.add(puntosExtra);
            puntosJuego+=puntosExtra;
            
            puntosExtra+=100;
            
            int indx = PosicionPuntos.size();
            PosicionPuntos.add(new Rectangle(getPosicion_X()+10, getPosicion_Y()+10,20,20));
            double Tiempo = GetTime();
            int c=0;
            while(Tiempo+3>GetTime()){
                if (c==0){colorPuntos=Color.RED;}
                if (c==1){colorPuntos=Color.BLUE;}
                if (c==2){colorPuntos=Color.GREEN;}
                if (c==3){colorPuntos=Color.ORANGE;}
                if (c==4){colorPuntos=Color.WHITE;c=0;}
                try {Thread.sleep(200);} catch (InterruptedException ex) {}
                c++;
            }
            puntosExtra=100;
            PosicionPuntos.set(indx, new Rectangle(-50,-50));
        });
        EfecPuntos.start();
    }
    
    //Este metodo se encarga de sumar una vida extra a PacMan cuando este alcanza un determinado numero de puntos.   
    public void ExtraVida(){
        extraVida+=8000;
        vidas+=1;
        Cl_Repro_Au.Fx(9);
    }
    
    
    public void ControlMap(){
        
//        if (mapaJuego>=6){
//            Cl_BolM.GenerarBolasMapa6();
//        }else
//        if (mapaJuego>=5){
//            Cl_BolM.GenerarBolasMapa5();
//        }else
//        if (mapaJuego>=4){
//            Cl_BolM.GenerarBolasMapa4();
//        }else
//        if (mapaJuego>=3){
//            Cl_BolM.GenerarBolasMapa3();
//        }else
//        if (mapaJuego>=2){
//            Cl_BolM.GenerarBolasMapa2();
//        }else  
        if (mapaJuego<=1){
            Cl_BolM.GenerarBolasMapa1();
        }
    }
    
    
    
    //Metodos Getter de Juego.    
    public boolean isFindelJuego() {
        return findelJuego;
    }

    public boolean isEmpezarJuego() {
        return empezarJuego;
    }

    public boolean isPausar() {
        return pausar;
    }

    public int getPosicion_X() {
        return posicion_X;
    }

    public int getPosicion_Y() {
        return posicion_Y;
    }

    public int getMovi_X() {
        return movi_X;
    }

    public int getMovi_Y() {
        return movi_Y;
    }

    public boolean isMuerte() {
        return muerte;
    }

    public int getVidas() {
        return vidas;
    }
    
    public double GetTime(){
       return System.nanoTime()/1000000000;
    }

    public boolean isMapaComplet() {
        return MapaComplet;
    }

    public int getNivel() {
        return mapaJuego;
    }

    public boolean isSuperPacMan() {
        return superPacMan;
    }

    public boolean isCongelacion() {
        return congelacion;
    }
    
    
    
    

    //Metodos Setter del juego.

    public void setFindelJuego(boolean FindelJuego) {
        this.findelJuego = FindelJuego;
    }

    public void setMuerte(boolean Muerte) {
        this.muerte = Muerte;
    }
    
    public void setVelocidadPacman(int VelocidadPacman) {
        this.velocidadPacman = VelocidadPacman;
    }

    //Metodo encargado de crear el Bucle infinito con refresco.
    public void CrearBucle(){
        
        Thread hilo =  new Thread(() -> {
            while (true){    //Bucle cerrado mientras la condicion se cumpla.
             
                try {
                    //Crea una pausa de 33 ms a mayor numero menor cantidad de refrescos menos fps a menor cantidad mas fps mas refresco.
                    Thread.sleep(40);
                } catch (InterruptedException ex) {}
                repaint();  //Redibujado, refresco, repintado del Frame.
            }
        } //Ese hilo de ejecucion se crea para no bloquear el programa principal.
        ); 
        hilo.start();   //Inicio del metodo run() Thread.           
    }
    
    
    
    //Metodo statico de comienzo de ejecucion de juego.    
    public static void main(String[] args) {
        
       new STragaBolasDemo();
    }

}
