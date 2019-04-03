
import java.util.HashMap;
import java.util.Map;

/*
 * Exemple de robot simple
 */

/**
 * @author Llorenç
 */
class myRobot extends Robot{
    
    Map<String, Integer> ponderacio;
    
    //No modificar el constructor
    myRobot(double x,double y, Escenari e){
        //Inicialitzem la superclasse
        super(x, y, e);
    }
    
    @Override
    public void mourerobot(){
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
        Foc millor = null;
        int heurMillor = 0;
        
        for(int i=0;i<escena.Focs.size();i++){
            
            if(!this.ocupada(escena.Focs.get(i).x, escena.Focs.get(i).y)){
                
                int dist = this.distancia(this.getX(),this.getY(),escena.Focs.get(i).x, escena.Focs.get(i).y);
                int arb = this.arbresVoltant(escena.Focs.get(i).x, escena.Focs.get(i).y);
                int heur = this.heuristica(dist, arb);

                if(heurMillor==0 || heur>heurMillor){
                    heurMillor = heur;
                    millor = escena.Focs.get(i);
                }
            }
        }
        if(heurMillor==0) return null;
        return millor;
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
