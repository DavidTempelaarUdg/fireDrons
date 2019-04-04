
/*
 * Dialeg que els drons comparteixen
 */

import java.util.ArrayList;

/**
 * @author David Tempelaar
 */
public class DialegDrons {

    private ArrayList<FocHeur> focsObj;

    public class FocHeur {
        int x;
        int y;
        int heuristica;

        public FocHeur(int a, int b, int heur){x=a;y=b;heuristica=heur;}
        public int getX(){return x;}
        public int getY(){return y;}
        public int getHeur(){return heuristica;}
    }

    public DialegDrons(int nRobots){
        focsObj = new ArrayList<>();
        for(int i=0; i<nRobots; i++){ focsObj.add(new FocHeur(-1,-1,0)); }
    }

    public void actFocHeur(int idRobot, Foc f, int heur){
        focsObj.set(idRobot,new FocHeur((int)f.x,(int)f.y,heur));
    }

    public int size(){ return focsObj.size(); }

    public int getHeur(int id) { return focsObj.get(id).getHeur(); }
    public int getX(int id) {return focsObj.get(id).getX();}
    public int getY(int id) {return focsObj.get(id).getY();}

    public void mostrar(){
        for(int i=0;i<focsObj.size();i++){
            System.out.print("("+focsObj.get(i).getX()+","+focsObj.get(i).getY()+")["+focsObj.get(i).getHeur()+"] / ");
        }
        System.out.println();
    }
}
