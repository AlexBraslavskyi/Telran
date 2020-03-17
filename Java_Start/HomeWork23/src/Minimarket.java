public class Minimarket {
    private String name;
    private String address;
    private Good[] goods;
    private Employee[] staff;

    public Minimarket() {
    }

    public Minimarket(String name, String address, Good[] goods, Employee[] staff) {
        setName(name);
        setAddress(address);
        this.goods = goods;
        this.staff = staff;
    }

    public Good[] getGoods() {
        return goods;
    }

    public void setGoods(Good[] goods) {
        this.goods = goods;
    }

    public Employee[] getStaff() {
        return staff;
    }

    public void setStaff(Employee[] staff) {
        this.staff = staff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null)
            this.name = name;
        else
            System.out.println("Error: wronge name");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null)
            this.address = address;
        else
            System.out.println("Error: wronge address");
    }

    @Override
    public boolean equals(Object obj) {
        Minimarket m = (Minimarket) obj;
        if (name.equals(m.name) && address.equals(m.address))
            return true;
        return false;
    }

    @Override
    public String toString() {
        String str = "Minimarket name: " + name +
                " Address: " + address+" \n Product list:\n";
        for(int i = 0; i<goods.length;i++){
            str+=goods[i]+"\n";
        }
        str+=" \nStaff list:\n";
        for(int j =0;j<staff.length;j++){
            str+=staff[j]+"\n";
        }
        return str;
    }
}