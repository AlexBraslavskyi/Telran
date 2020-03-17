public class Alcohol extends Drink {

    private int alcohol;

    public Alcohol() {

    }

    public Alcohol(String name, double price, String mItem, boolean isKosher, boolean isSparkling, int alcohol) {
        super(name, price, mItem, isKosher, isSparkling);
        setAlcohol(alcohol);
    }

    public int getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(int alcohol) {
        if(alcohol!=0)
            this.alcohol = alcohol;
        else
            System.out.println("Error: wrong persent alcohol");
    }

    @Override
    public String toString() {
        return super.toString()+" Persent alcohol: "+alcohol;
    }

    }


