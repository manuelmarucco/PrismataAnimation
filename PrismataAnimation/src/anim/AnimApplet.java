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
	private int Norbs = 18; //Cantidad de orbes que que voy a tener
	
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
        
    // List of Orbs Setups
        for (int i=0; i<Norbs; i++){
        	orbs.add(new Orb(57+i));
    	}
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
            repaint(); // calls paint method
            try {
                Thread.sleep(17);	//retardo de refresco del programa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
	public void paint(Graphics g) {
    
    /*	
    // Forma Rectangular
      	int count = 0
      	
    	for (Orb orb: orbs){
    		g.drawImage(orbImage, 200+count, orb.getY()+250, this);
    		count += 20;
    	}
    */
    // Forma Circular
    	int y,ang=0,x,rad=5;
    	
    	for (Orb orb: orbs){
    		y = (int) Math.round((orb.getY()+rad)*Math.sin(ang));
    		x = (int) Math.round((orb.getY()+rad)*Math.cos(ang));
    		
    		g.drawImage(orbImage, 400+x, y+250, this);
    		ang += (int)Math.round(360/Norbs); //Espacio en grados entre cada orb
    	}
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