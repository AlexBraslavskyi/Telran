
public class FeatureMovie extends Movie{
     private String genre;
     private Actor[] cast = null;
   
	public FeatureMovie() {
		super();
		
	}
	public FeatureMovie(String title, int duration,
			String director, int year, String genre,
			Actor[] cast) {
		super(title, duration, director, year);
		this.setGenre(genre);
		this.setCast(cast);
	}

	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		if(genre != null)
		  this.genre = genre;
	}
	public Actor[] getCast() {
		return cast;
	}
	public void setCast(Actor[] cast) {
		if(cast != null)
		    this.cast = cast;
	}
	@Override
	public String toString() {
		String str = super.toString()
				+ "  Genre : " + genre 
				+ "\nCast : \n";
		for(int i = 0 ; i < cast.length; i++)
		{
			str +=  cast[i].toString() + "\n";
		}
		
		return str;
	}
     
     
	
}





