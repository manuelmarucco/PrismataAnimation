package anim;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AnimApplet extends Applet implements Runnable {

	private URL base;
	private Image image,orbImage;
	private Graphics background;
	private List<Orb> orbs = new ArrayList<Orb>();
	private List<Adn> adnOrbs = new ArrayList<Adn>();
	private int Norbs = 40; //Cantidad de orbes que que voy a tener
	
    @Override
    public void init() {
    
    // Background Setups
    	
        setSize(800, 480);
        setBackground(Color.BLACK);
        setFocusable(true);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Prismata Animation");
        try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

	// Image Setups
 	
        orbImage = getImage(base, "images/orb.png");
       
    // Configuracion de Lista de Orbs para RECTANGULAR Y CIRCULAR
        for (int i=0; i<Norbs; i++){
        	orbs.add(new Orb(57+i));
    	}
      
        /*
     // Configuracion de Lista de Orbs para ADN RECTANGULAR
        for (int i=0; i<Norbs; i++){
        	adnOrbs.add(new Adn(2*Math.PI*57*i*11/60)); //7 = adn triple || 11 = adn
    	}
    	*/
        /*
        //Configuracion de Lista de Orbs para ADN CIRCULAR
        for (int i=0; i<Norbs; i++){
        	adnOrbs.add(new Adn(2*Math.PI*57*i*57.3/60)); //14 = trebol || 10 = doble circulo || 13 = triple circulo rotando
    	}
    	*/												// 57 = tres circulos partidos //57.3 = flor partida
    }

    @Override
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }


    public void run() {
        while (true) {
        	
        	for (Orb orb: orbs){
        		orb.update();
        	}
        	
        	for (Adn orb: adnOrbs){
        		orb.update();
        	}
            repaint(); // llama al metodo paint()
            try {
                Thread.sleep(10);	//retardo de refresco del programa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
	public void paint(Graphics g) {
    
    /*
    // Forma Rectangular
      	int count = 0;
      	
    	for (Orb orb: orbs){
    		g.drawImage(orbImage, 200+count, orb.getY()+250, this);
    		count += 20;
    	}
    */
    
    // Forma Circular
    	int y,ang=0,x,rad=100;
    	
    	for (Orb orb: orbs){
    		y = (int) Math.round((orb.getY()+rad)*Math.sin(ang));
    		x = (int) Math.round((orb.getY()+rad)*Math.cos(ang));
    		
    		g.drawImage(orbImage, 400+x, y+250, this);
    		ang += (int)Math.round(360/Norbs); //Espacio en grados entre cada orb
    	}
   
    /*	
    //	Forma ADN
	int count = 0;
      	
    	for (Adn orb: adnOrbs){
    		g.drawImage(orbImage, 200+count, orb.getY()+250, this);
    		count += 20;
    	}
    	*/
    	/*
    // Forma ADN Circular
    	int y,ang=0,x,rad=100;
    	
    	for (Adn orb: adnOrbs){
    		y = (int) Math.round((orb.getY()+rad)*Math.sin(ang));
    		x = (int) Math.round((orb.getY()+rad)*Math.cos(ang));
    		
    		g.drawImage(orbImage, 400+x, y+250, this);
    		ang += (int)Math.round(360/Norbs); //Espacio en grados entre cada orb
    	}
    	*/
	}
    
    @Override
   	public void update(Graphics g) {
     	
        // Creo imagen del background y lo actualizo constantemente como imagen
        	
           	if (image == null) {
       			image = createImage(this.getWidth(), this.getHeight());
       			background = image.getGraphics();
       		}

       		background.setColor(getBackground());
       		background.fillRect(0, 0, getWidth(), getHeight());
       		background.setColor(getForeground());
       		paint(background);

       		g.drawImage(image, 0, 0, this);

   		
   	}
    
} 