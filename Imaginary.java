import java.util.Objects;

/**
 * Created by hIM on 3/30/2017.
 */
public class Imaginary {
    double r;
    double i;


    public Imaginary (double r, double i){
        this.r = r;
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        Imaginary toCompare = (Imaginary)o;
        // If the object is compared with itself then return truee
        if ((toCompare.r == this.r) && (toCompare.i == this.i)) return true;
        else return false;
        }

    @Override
    public int hashCode() {
        long real = Double.doubleToLongBits(this.r); // harmonize NaN bit patterns
        long imag = Double.doubleToLongBits(this.i);
        if (real == 1L << 63) real = 0; // convert -0.0 to +0.0
        if (imag == 1L << 63) imag = 0;
        long h = real ^ imag;
        return (int)h ^ (int)(h >>> 32);
    }


    void add (Imaginary number){
        this.r += number.r;
        this.i += number.i;
    }

    void square (){
        double r  = (this.r*this.r) - (this.i *this.i);
        double i = 2*(this.r*this.i);
        this.i = i;
        this.r = r;
    }

    double magnitudeSqr (){ return ((this.r*this.r) + (this.i * this.i));}

    void print (){
        System.out.println(this.r + " " + this.i);
    }

    void printMagnitude (){
        System.out.println("Magnitude " +this.magnitudeSqr());
    }


    }

