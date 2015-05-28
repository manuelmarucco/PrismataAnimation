package anim;

public class Adn {

	private double x = 0;
	private int y = 0;
	private double fase = 0;
	private double N = 90; //periodo de oscilacion	(N = 60 = 60 segundos)
	private double o = 57; //cantidad de oscilaciones del mas lento 
	private double A = 70; //Amplitud == cantidad de pixeles en los que se va a mover
	
	public Adn(){
	}
	
	public Adn(double oo){
		fase = oo;
	}
	
	public void update() {
		
		y = (int) Math.round( A*Math.sin(2*Math.PI*o/N*x+Math.PI/2+fase) );

		if(x == N)	x = 0;

		x += 2/N;	
		
	}

	
	public int getY() {
		return y;
	}
	
	public void setN(double NN) {
		N = NN;
	}
	
	public void setFase(double NN) {
		fase = NN;
	}
}

