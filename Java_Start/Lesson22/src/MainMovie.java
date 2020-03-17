
public class MainMovie {

	public static void main(String[] args) {
		Actor a1 = new Actor("Di Niro",1943,"All geners");
		Actor a4 = new Actor("Di Niro",1943,"All geners");
		Actor a5 = a1;
		
		if(a1.equals(a4) == true)
		{
			System.out.println("Equals");
		}
		else
		{
			System.out.println("NOT Equals");
		}
		
		
		Actor a2 = new Actor("J.Roberts",1967,"All geners");
		Actor a3 = new Actor("Di Caprio",1974,"All geners");
		
		Actor[] cast = {a1,a2,a3};
		FeatureMovie fm = 
				new FeatureMovie("Titac", 220, "Cameron",
						        1999, "Comedy", cast);
		System.out.println(fm);
		Object obj = fm;
		Movie mv = fm;
		fm = (FeatureMovie)obj;
		System.out.println(fm);
		
		
		String str = "Hello";
		Object obj1 = str;
		//fm = (FeatureMovie)obj1;
		//System.out.println(fm);
		
		Movie mv1 = 
		    new Movie("Operation Y", 90, "Gaidai", 1968);
		Movie mv2 = 
			new Movie("Operation Y", 90, "Gaidai", 1968);
        if(mv1.equals(mv2))
        {
        	System.out.println("Movies equals");
        }
        else {
        	System.out.println("Movies not equals");
        }
	}

}
