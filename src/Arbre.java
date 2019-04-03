
import javax.swing.ImageIcon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Llorenç
 */
public class Arbre {
    double x;
    double y;
    
    public Arbre(double xin, double yin){
        x=xin;
        y=yin;
    }
    
    public ImageIcon getImatge() {
       String dir = System.getProperty("user.dir")+ "//Imatges//arbre.jpg";
       return new ImageIcon( dir);
    }
}
