package ufpr.org.churrascometro.calc;

/**
 * Created by roberto on 18/11/17.
 */

public class BarbecueCalc {

    private double menQuantity;
    private double womenQuantity;
    private double childrenQuantity;

    public BarbecueCalc(double menQuantity, double womenQuantity, double childrenQuantity) {
        this.menQuantity = menQuantity;
        this.womenQuantity = womenQuantity;
        this.childrenQuantity = childrenQuantity;
    }

    public double getMeatQuantity() {
        return ((this.menQuantity * 500) + (this.womenQuantity * 300) + (this.childrenQuantity * 200)) / 1000;
    }

    public double getSausageQuantity() {
        return ((menQuantity * 450) + (womenQuantity * 250) + (childrenQuantity * 200)) / 1000;
    }
}
