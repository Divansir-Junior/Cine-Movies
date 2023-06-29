package data;


import java.io.Serializable;

public class Movie implements Serializable {
	private String name;
	private String nameDirector;
	private String filmGegnre;
	
	public Movie() {
	}

	public Movie(String name, String nameDirector, String filmGegnre) {
		super();
		this.name = name;
		this.nameDirector = nameDirector;
		this.filmGegnre = filmGegnre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameDirector() {
		return nameDirector;
	}

	public void setNameDirector(String nameDirector) {
		this.nameDirector = nameDirector;
	}

	public String getFilmGegnre() {
		return filmGegnre;
	}

	public void setFilmGegnre(String filmGegnre) {
		this.filmGegnre = filmGegnre;
	}
	
	public String toString() {
		return "\nNome do filme:"+ " " + name +"\n" +"Diretor do filme:" +  nameDirector +  " " +  "\nGenero do filme:" + filmGegnre + "\n---------------------";
	}
}
