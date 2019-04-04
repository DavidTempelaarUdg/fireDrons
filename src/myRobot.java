
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Exemple de robot simple
 */

/**
 * @author Llorenç
 */
class myRobot extends Robot {

    Map<String, Integer> ponderacio;
    Posicio focObjectiu;
    int heurObjectiu;

    public class Posicio {
        double x;
        double y;

        public Posicio(double a, double b){x = a; y = b;}
        public double getX(){return x;}
        public double getY(){return y;}
        public boolean sinPos(){return (x==-1 && y==-1);}
    }

    myRobot(double x,double y, Escenari e){
        //Inicialitzem la superclasse
        super(x, y, e);

        focObjectiu = new Posicio(-1,-1);
        heurObjectiu = 0;

        ponderacio = new HashMap<>();
        ponderacio.put("00", 400);
        ponderacio.put("10", 300);
        ponderacio.put("11", 310);
        ponderacio.put("12", 320);
        ponderacio.put("13", 340);
        ponderacio.put("14", 390);
        ponderacio.put("20", 230);
        ponderacio.put("21", 240);
        ponderacio.put("22", 260);
        ponderacio.put("23", 280);
        ponderacio.put("24", 330);
        ponderacio.put("30", 200);
        ponderacio.put("31", 210);
        ponderacio.put("32", 220);
        ponderacio.put("33", 250);
        ponderacio.put("34", 270);
        ponderacio.put("40", 5);
        ponderacio.put("41", 10);
        ponderacio.put("42", 15);
        ponderacio.put("43", 20);
        ponderacio.put("44", 25);
    }
    
    @Override
    public void mourerobot(){


        double xi,yi;
        xi=0;
        yi=0;
        
        if(ple == 0){
             //si no aigua anem al diposit a carregar   
                 xi = escena.Diposit.x;
                 yi = escena.Diposit.y;
              //System.out.println("paperera - " + Integer.toString((int) xi) + "," + Integer.toString((int) yi));
        }
        //System.out.println("Posicio Actual- " + Integer.toString((int) x) + "," + Integer.toString((int) y));
        else if(!escena.Focs.isEmpty()){
            //si hi ha Focs i tenim aigua nem a apagar
            
            Foc f = this.buscarFocObjectiu();
            if(f!=null){
                xi = f.x;
                yi = f.y;

                escena.dialeg.actFocHeur(iden(),f,heurObjectiu);
            }
            else{
                xi = escena.Diposit.x;
                yi = escena.Diposit.y;
            }
            //System.out.println("basura - " + Integer.toString((int) xi) + "," + Integer.toString((int) yi));
        }
        setPos(getX()+anarX(xi),getY()+anarY(yi));
        
        //System.out.println("Numero Focs - " + Integer.toString((int) escena.Focs.size()));
        
        apaga();
        emplena();
    }

    public int iden(){
        for(int i = 0; i<escena.Robots.size();i++){
            if(esActual(escena.Robots.get(i).getX(),escena.Robots.get(i).getY())){return i;}
        }
        return -1;
    }

//    public Posicio getPosObj(){
//        return focObjectiu;
//    }

    public int arbresVoltant(double xin, double yin){
        int i = 0;
        int compt = 0;
        while( i!= escena.Arbres.size()){
            Arbre r = escena.Arbres.get(i);
            
            if(((int)r.x == xin & (int)r.y == (yin-1)) 
                    || ((int)r.x == xin & (int)r.y == (yin+1))
                    || ((int)r.x == (xin-1) & (int)r.y == yin)
                    || ((int)r.x == (xin+1) & (int)r.y == yin))
                {compt++;}
            
            i++;
        }
        return compt;
    }
    
    public Foc buscarFocObjectiu(){
        System.out.println("AL INICI");
        escena.dialeg.mostrar();
        Foc millor = null;
        int heurMillor = 0;
        
        for(int i=0;i<escena.Focs.size();i++){
            
            if(!this.ocupada(escena.Focs.get(i).x, escena.Focs.get(i).y)){
                
                int dist = this.distancia(this.getX(),this.getY(),escena.Focs.get(i).x, escena.Focs.get(i).y);
                int arb = this.arbresVoltant(escena.Focs.get(i).x, escena.Focs.get(i).y);
                int heur = this.heuristica(dist, arb);

                if(!this.existeixMillor(escena.Focs.get(i), heur) && (heurMillor==0 || heur>heurMillor)){
                    heurMillor = heur;
                    millor = escena.Focs.get(i);
                }
            }
        }

        if(heurMillor==0) return null;

        System.out.println("AL FINAL");
        escena.dialeg.mostrar();

        heurObjectiu = heurMillor;
        return millor;
    }

    public boolean existeixMillor(Foc f, int heurThis){

        for(int i=0; i<escena.dialeg.size();i++){
            if(i!=iden()){
                if((escena.dialeg.getHeur(i)>=heurThis)
                  && (escena.dialeg.getX(i)==f.x && escena.dialeg.getY(i)==f.y))
                    { return true; }
            }
        }
        return false;
    }
    
    public int distancia(double ax, double ay, double bx, double by){
        return Math.abs((int)ax-(int)bx) + Math.abs((int)ay-(int)by);
    }
    
    public int heuristica(int dist, int arbres){
        int d = dist, a = arbres;
        if(dist==0) a = 0; 
        else if(dist>4) d = 4;
        
        String mp = String.valueOf(d) + String.valueOf(a);

        return ponderacio.get(mp);
    }
}
