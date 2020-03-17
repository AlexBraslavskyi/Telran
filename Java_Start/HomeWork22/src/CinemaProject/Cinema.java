package CinemaProject;

public class Cinema {

	private String cinemaName;
	private String address;
	private Movie [] movies;
	
	public Cinema() {
	
	}
	public Cinema(String cinemaName, String address, Movie[] movies) {
		setCinemaName(cinemaName);
		setAddress(address);
		setMovies(movies);
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		if(cinemaName!=null)
		this.cinemaName = cinemaName;
		else
			System.out.println("Error");
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		if(address!=null)
		this.address = address;
		else
			System.out.println("Error");
	}
	public Movie[] getMovies() {
		return movies;
	}
	public void setMovies(Movie[] movies) {
		if(movies!=null)
		this.movies = movies;
		else
			System.out.println("Error");
	}
	
	@Override
	public boolean equals(Object obj) {
		Cinema c = (Cinema)obj;
		if(this.cinemaName.equals(c.cinemaName)	&& this.address.equals(c.address))
			return true;
		
		
		return false;
	}
	
	@Override
	public String toString() {
	
		String str = "Cinema name: "+cinemaName+ " Address: "+address +"\n Movies \n";
		for(int i = 0; i < movies.length;i++)
			
		str += movies[i].toString()+"\n";
		
		
		return str;
	}
	
	
}
