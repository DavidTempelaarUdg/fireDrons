
import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Llorenç
 */
public class Escenari {
    java.util.List<Robot> Robots;
    java.util.List<Arbre> Arbres;
    int numArbrescremats;
    java.util.List<Foc> Focs;
    Refill Diposit;
    double dimx;
    double dimy;
    DialegDrons dialeg;
   
    boolean fi = false;
    
    
    public Escenari(){
        Robots = new ArrayList<Robot>();
        Arbres = new ArrayList<Arbre>();
        Focs = new ArrayList<Foc>();
        Diposit = new Refill(0,0);
    }
    
    public void Crea(File fitxer) throws FileNotFoundException, IOException{
        //LLegir fitxer d'inicialització i crear l'escenari
        CSVReader reader = new CSVReader(new FileReader(fitxer), ';', '\'');
        List<String []> nextLine;
        nextLine = reader.readAll();
        this.dimx = nextLine.size();
        numArbrescremats=0;
        
        for(int i = 0;nextLine.size()!=i;i++){//x
            for(int j =0;nextLine.get(i).length != j;j++){//y
                this.dimy = nextLine.get(i).length;
                for(int k =0;nextLine.get(i)[j].length() != k;k++){
                   //System.out.println(nextLine.get(i)[j].charAt(k));
                   if(nextLine.get(i)[j].charAt(k) == 'A' || nextLine.get(i)[j].charAt(k) == 'a'){
                       Arbres.add(new Arbre(i,j));
                       
                   }
                   if(nextLine.get(i)[j].charAt(k) == 'F' || nextLine.get(i)[j].charAt(k) == 'f'){
                       Focs.add(new Foc(i,j));
                   }
                   if(nextLine.get(i)[j].charAt(k) == 'R' || nextLine.get(i)[j].charAt(k) == 'r'){
                       Robots.add(new myRobot(i,j,this));
                   }
                   if(nextLine.get(i)[j].charAt(k) == 'O' || nextLine.get(i)[j].charAt(k) == 'o'){
                       Diposit = new Refill(i,j);
                   }
                }
            }
        }
        dialeg = new DialegDrons(Robots.size());
    }
    
    public boolean Step(){
        //Passar l'step a cada 1 dels robots
        
        for( int i =0; i!=  Robots.size(); i++){
                Robots.get(i).moure();
                 /*if(Robots.get(i).ple != 0){
                     //ni ha un de ple
                     plens = true;
                 }*/
            }
        
        for( int i =Focs.size()-1; i!= -1; i--){
                
                 if(Focs.get(i).estat == 0){
                     //ni ha un foc apafat
                     Random rand = new Random();
                     int n = rand.nextInt(100);
                     
                     if (n<10){//10% de tornar a encendre
                        Focs.get(i).encen();
                     }
                     if (n>80){//20% de apagar tornar a encendre
                        Focs.remove(i);
                     }
                 }else{
                     Random rand = new Random();
                     int n = rand.nextInt(100);
                     if (n<10){//10% de propagar en arbres
                        double x=Focs.get(i).x;
                        double y=Focs.get(i).y;
                        int j=0;
                        boolean trobat=false;
                        
                        while(j<Arbres.size() & !trobat){
                         trobat= -1<=x-Arbres.get(j).x & x-Arbres.get(j).x<=1 & -1<=y-Arbres.get(j).y & y-Arbres.get(j).y<=1;
                         j=j+1;
                         }
                        
                        if(trobat){
                            x=Arbres.get(j-1).x;
                            y=Arbres.get(j-1).y;
                            Focs.add(new Foc(x,y));
                            Arbres.remove(j-1);
                            numArbrescremats++;
                        }
                     }
                 }
            }
        return Focs.isEmpty();
    }
    
    public double getX(){
        return dimx;
    }
    public double getY(){
        return dimy;
    }
}
