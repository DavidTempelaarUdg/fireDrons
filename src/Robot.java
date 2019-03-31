import javax.swing.ImageIcon;

/**
 * @author Llorenç
 */
abstract class Robot {
    
    private double energia;
    private double punts;
    
    public Escenari escena;
    
    //la posició actual del robot
    private double x;
    private double y;
    
    double ple;
    
    //indica si ja ens hem mogut
    private boolean mogut;

    
    public Robot(double xin,double yin, Escenari esc){
        this.x = xin;
        this.y = yin;
        this.ple =5;
        this.escena = esc;
        this.energia = 0;
        this.punts = 0;
    }
    
    public void setPos(double xin,double yin){
        //Fa que el robot es mogui, la posicio no pot estar ocupada perres exepuant la paperera
        //Els robots no poden avançar més de una posicio
        //si la posició que es demana esta ocupada el robot no es moura
        
        //Comprovem si la pos esta ocupada
        if( !ocupada(xin,yin) && !mogut){
            //si intenten sortir de la taula corretjim la posiciò
            if(xin<0){
                xin =0;
            }
            if(yin<0){
                yin =0;
            }
            
            if(xin>escena.dimx-1){
                xin =escena.dimx-1;
            }
            if(yin>escena.dimy-1){
                yin =escena.dimy-1;
            }
            
            //nomes permetem avançar de 1 en 1
            if(x+1==xin || x-1==xin){
              x=xin;  
              mogut = true;
            }
            
            if(y+1==yin || y-1==yin){
              y=yin; 
              mogut = true;
            }
            
            if(mogut){
                //hem gastat energia
                energia++;
            }
            
        }
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public boolean ocupada(double xin, double yin){
        //comprova si la posició esta ocupada
        boolean  trobat = false;
        int i = 0;
        while( i!= escena.Robots.size() & ! trobat){

          Robot r = escena.Robots.get(i);
          trobat = (int)r.getX() == xin & (int)r.getY() == yin ;//&  xin >= 0 & yin >= 0 & xin < (int)escena.dimx & yin  < (int)escena.dimy ;
          i++;
        }
        
        return trobat;
    }
    
    public boolean esfoc(double xin, double yin){
        boolean  trobat = false;
        int i = 0;
        while( i!= escena.Focs.size() & ! trobat){
            Foc r = escena.Focs.get(i);
            trobat = (int)r.x == xin & (int)r.y == yin & r.estat==1 ;
            i++;
        }
        return trobat;
    }
    
    public void moure(){
        mogut = false;
        mourerobot();
    }
    
    public void apaga(){
        //Per apagar el foc hi ha d'estar a sobre
        if(ple>0 && esfoc(this.x,this.y)){
            //podem recollir
            boolean  trobat = false;
            int i = 0;
            while( i!= escena.Focs.size() & ! trobat){
                Foc r = escena.Focs.get(i);
                trobat = (int)r.x == this.x & (int)r.y == this.y;
                i++;
            }
            
            //Apaguem el foc
            escena.Focs.get(i-1).apaga();//
            
            //Augmentem en 1 la puntuació
            punts++;
            
            //buidem diposit 1
            ple--;
            
        }
    }
    
    public void emplena(){
        //per emplenar hem de essrat sobre el diposit
        if(x==escena.Diposit.x && y==escena.Diposit.y){
            //estem a sobra el diposit -> emplenem
            ple = 5;
        }
    }
    
    public double getEnergia(){
        //l'energia gastada
        return energia;
    }
    
    public double getPunts(){
        //les basures recollides
        return punts;
    }
    
    public ImageIcon getImatge(){
       String dir = System.getProperty("user.dir")+ "\\Imatges\\robot" + Integer.toString((int)ple) + ".jpg";
       return new ImageIcon( dir);
    }
    
    public double anarX(double xin){
        double xanar =0;
        if(x < xin){
            xanar = 1;
        }
        if(x > xin){
            xanar = -1;
        }
        return xanar;
    }
    
    public double anarY(double yin){
        double yanar =0;
        if(y < yin){
            yanar = 1;
        }
        if(y > yin){
            yanar = -1;
        }
        return yanar;
    }
    
    abstract public void mourerobot();
}
