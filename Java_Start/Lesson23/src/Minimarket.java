
public class Minimarket {
    private String name;
    private String address;
    private Good[] goods;
    private Employee[] staff;

    public Minimarket(String name, String address) {
        this.name= name;
        this.address=address;     
    }
public void addAllGoods(Good[]goods){
	if(goods!=null)
		this.goods = goods;
}

public void addAllStaff(Employee[]staff){
	if(staff!=null)
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

    public Employee[] addEmployee(Employee[]staff,Employee employee) {
        Employee[] newStaff;
        newStaff = new Employee[staff.length + 1];
        for(int i = 0;i<staff.length;i++) {
            if (employee.getId() != staff[i].getId()) {
                newStaff[i] = staff[i];
            } else {
                return staff;
            }
        }
                newStaff[staff.length] = employee;
              staff = newStaff;
              this.staff = staff;

       return staff;
        }
    public Employee[] delEmployee(Employee[]staff,int id) {
        Employee[] newStaff;
       newStaff = new Employee[staff.length-1];
        int i, count;
        for (i = 0, count = 0; i < staff.length-1; i++) {
            if (staff[i].getId() != id && count == i) {
                newStaff[i] = staff[i];
                count++;
            } else if (staff[i].getId() == id || count < i) {
                newStaff[i] = staff[i + 1];

            } else if (staff[i].getId() != id && i == staff.length) {
                newStaff[i] = staff[i];

            }
        }
           staff = newStaff;
         this.staff = staff;
            return newStaff;
        }

public void displayMinimarket(){
	System.out.println("Minimarket name: "+name);
	System.out.println("Address: "+address);
	System.out.println("==================================================================================");
	System.out.println("Staff: ");
	for(int i=0;i<staff.length;i++){
		System.out.println(staff[i]);
	}
	System.out.println("=================================================================================");
	System.out.println("Goods:");
	for(int i = 0;i<goods.length;i++){
		System.out.println(goods[i]);
	}
}
    @Override
    public boolean equals(Object obj) {
        Minimarket m = (Minimarket) obj;
        if (name.equals(m.name) && address.equals(m.address))
            return true;
        return false;
    }
public void printAllSalaries(){
	for(int i = 0;i<staff.length;i++){
		System.out.print(staff[i].getName());
		System.out.print("\t"+staff[i].getPosition());
		System.out.print("\t"+staff[i].calculateSalary()+" NIS");
		System.out.println();
	}

}
   /* @Override
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
    }*/
}