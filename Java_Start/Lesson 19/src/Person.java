
public class Person {

	private String name;
	private String surName;
	private int age;
	private int id;

	//default constructor
	public Person(){
		System.out.println("Default constructor");
	}
	//constructor
	public Person(String name,String surName, int age, int id){
	setName(name);
	setSurName(surName);
	setAge(age);
	setId(id);
	}
	//setters
	public void setName(String n){
	if(n!=null)
		name = n;
	else
		System.err.println("Error: wrong name");
	}
	public void setSurName(String n){
		if(n!=null)
			surName = n;
		else
			System.err.println("Error: wrong surname");		
		}

	public void setAge(int i){
		if(i>=0 && i< 120)
			age = i;
		else
			System.err.println("Error: wrong age");
		}
public void setId(int i) {
	if(i>0)
		id = i;
	else
		System.out.println("Error: wrong id");
	}

//getters
public String getName(){
	return name;
}
public String getSurName() {
	return surName;
}
public int getAge() {
	return age;
}
public int getId() {
	return id;
}
//custom
public void display(){
	System.out.print("Name:" + name);
	System.out.print(" Surame:" + surName);
	System.out.print(" Id:" + id);
	System.out.println(" Age:" + age);
	
}

}