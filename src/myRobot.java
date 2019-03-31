/*
 * Exemple de robot simple
 */

/**
 * @author Llorenç
 */
class myRobot extends Robot {
    
    //No modificar el constructor
    myRobot(double x,double y, Escenari e){
        //Inicialitzem la superclasse
        super(x, y, e);
    }
    
    //Wasa
    
    @Override
    public void mourerobot(){
        double xi,yi;
        xi=0;
        yi=0;
        //System.out.println("Posicio Actual- " + Integer.toString((int) x) + "," + Integer.toString((int) y));
        if(!escena.Focs.isEmpty()){
            //si hi ha Focs i tenim aigua nem a apagar
            xi = escena.Focs.get(0).x;
            yi = escena.Focs.get(0).y;
            //System.out.println("basura - " + Integer.toString((int) xi) + "," + Integer.toString((int) yi));
        }
        if(ple == 0){
             //si no aigua anem al diposit a carregar   
                 xi = escena.Diposit.x;
                 yi = escena.Diposit.y;
              //System.out.println("paperera - " + Integer.toString((int) xi) + "," + Integer.toString((int) yi));
        }
        setPos(getX()+anarX(xi),getY()+anarY(yi));
        
        //System.out.println("Numero Focs - " + Integer.toString((int) escena.Focs.size()));
        
        apaga();
        emplena();
    }    
}
