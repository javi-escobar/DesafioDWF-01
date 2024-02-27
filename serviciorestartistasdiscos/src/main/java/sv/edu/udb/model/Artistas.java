package sv.edu.udb.model;

public class Artistas {
	private int id_artista;
	private String nombre_artista;
	private String descripcion;
	
	/**
	 * @return the id_artista
	 */
	public int getId_Artista() {
		return id_artista;
	}
	/**
	 * @param id_artista the id_artista to set
	 */
	public void setId_Artista(int id_artista) {
		this.id_artista = id_artista;
	}
	/**
	 * @return the nombre_artista
	 */
	public String getNombre_Artista() {
		return nombre_artista;
	}
	/**
	 * @param nombre_artista the nombre_artista to set
	 */
	public void setNombre_Artista(String nombre_artista) {
		this.nombre_artista = nombre_artista;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
