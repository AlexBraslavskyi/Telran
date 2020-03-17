package CinemaProject;

public class Movie {
   private String title;
   private int duration;
   private String director;
   private int year;
 
public Movie() {
	
}
public Movie(String title, int duration, String director, int year) {
	this.setTitle(title);
	this.setDuration(duration);
	this.setDirector(director);
	this.setYear(year);
}

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	if(title != null)
	  this.title = title;
}
public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	if(duration > 0)
	   this.duration = duration;
}
public String getDirector() {
	return director;
}
public void setDirector(String director) {
	if(director != null)
	   this.director = director;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	if(year > 0)
	   this.year = year;
}
@Override
public String toString() {
	
	return "Title: "+ title+" Duration: "+ duration+
			" Director: "+ director+" Year: " +year;
}
/*
@Override
public boolean equals(Object obj) {
	Movie mv = (Movie)obj;
	if(this.duration == mv.duration
			&& this.title.equals(mv.title) 
			&& this.director.equals(mv.director)
			&& this.year == mv.year) {
		return true;
	}
	
	return false;
}

  */ 
   
}






