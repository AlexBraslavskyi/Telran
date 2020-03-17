public class MeatFood extends Food {

    private String meatFoodType;

    public MeatFood(String meatFoodType) {
    }

    public MeatFood(String name, double price, String mItem, boolean isKosher, String meatFoodType) {
        super(name, price, mItem, isKosher);
        setMeatFoodType(meatFoodType);
    }

    public String getMeatFoodType() {
        return meatFoodType;
    }

    public void setMeatFoodType(String meatFoodType) {
       if(meatFoodType!=null)
        this.meatFoodType = meatFoodType;
       else
           System.out.println("Error: Wronge type");
    }

    @Override
    public String toString() {
        return super.toString()+" Meat food type: "+ meatFoodType;
    }
}
