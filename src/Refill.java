import javax.swing.ImageIcon;

/**
 *
 * @author Llorenç
 */
public class Refill {
    public double x;
    public double y;
    
    public Refill(double xin, double yin){
        x=xin;
        y=yin;
    }

    public ImageIcon getImatge() {
       String dir = System.getProperty("user.dir")+ "\\Imatges\\refill.png";
       return new ImageIcon( dir);
    }
}
