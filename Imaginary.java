import java.util.Objects;

/**
 * Himanshu Chaudhary
 * Is the class to represent imaginary numbers and perform computations on it
 *
 */
public class Imaginary {
    double r;
    double i;


    public Imaginary (double r, double i){
        this.r = r;
        this.i = i;
    }


    /**
     * @param number
     *        is the number to be added
     * This method adds imaginary number passed to itself
     */
    void add (Imaginary number){
        this.r += number.r;
        this.i += number.i;
    }

    /**
     * This function squares itself and updates its Real and imaginary value
     */
    void square (){
        double r  = (this.r*this.r) - (this.i *this.i);
        double i = 2*(this.r*this.i);
        this.i = i;
        this.r = r;
    }

    /**
     * This function returns the square od maginitude of the imaginary number
     * @return maginute sqaure of the imaginary number
     */
    double magnitudeSqr (){ return ((this.r*this.r) + (this.i * this.i));}

    /**
     * Prints the real and imaginary part of the Imaginary Number
     */
    void print (){
        System.out.println(this.r + " " + this.i);
    }



    }

