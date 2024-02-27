package sv.edu.udb.model;
import java.sql.SQLException;
import java.util.ArrayList;
public class ArtistasDAO extends AppConnection{
	
	/**
	 * @param artistas
	 * @throws SQLException
	 */
	public void insertarArtista(Artistas artista) throws SQLException {
		Conectar();
		ps = con.prepareStatement("insert into artistas (nombre_artista, descripcion) values (?,?)");
		ps.setString(1, artista.getNombre_Artista());
		ps.setString(2, artista.getDescripcion());
		ps.execute();
		Desconectar();
	}
	
	/**
	 * @param artistas
	 * @throws SQLException
	 */
	public void updateArtista(Artistas artista) throws SQLException {
		Conectar();
		ps = con.prepareStatement("update artistas set nombre_artista = ?, descripcion = ? where id_artista = ?");
		ps.setString(1, artista.getNombre_Artista());
		ps.setString(2, artista.getDescripcion());
		ps.setInt(3, artista.getId_Artista());
		ps.executeUpdate();
		Desconectar();
	}
	
	/**
	 * @param id_artista
	 */
	public void deleteArtista(int id) throws SQLException {
		Conectar();
		ps = con.prepareStatement("delete from artistas where id_artista = ?");
		ps.setInt(1, id);
		ps.execute();
		Desconectar();
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Artistas> findAll() throws SQLException {
		Conectar();
		st = con.createStatement();
		rs = st.executeQuery("select id_artista, nombre_artista, descripcion from artistas");
		ArrayList<Artistas> artistas = new ArrayList();
		
		while(rs.next()) {
			Artistas art = new Artistas();
			art.setId_Artista(rs.getInt(1));
			art.setNombre_Artista(rs.getString(2));
			art.setDescripcion(rs.getString(3));
			artistas.add(art);
		}
		Desconectar();
		return artistas;
 	}
	
	/**
	 * @param id_artista
	 * @return
	 * @throws SQLException
	 */
	public Artistas findById(int id_artista) throws SQLException {
		Artistas artista = null;
		Conectar();
		ps = con.prepareStatement("select id_artista, nombre_artista, descripcion from artistas where id_artista = ?");
		ps.setInt(1, id_artista);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			artista = new Artistas();
			artista.setId_Artista(rs.getInt(1));
			artista.setNombre_Artista(rs.getString(2));
			artista.setDescripcion(rs.getString(3));
		}
		Desconectar();
		return artista;
	}
	
	public Artistas findByIdDiscos(int id_artista) throws SQLException {
		Artistas artista = null;
		Conectar();
		ps = con.prepareStatement("select id_artista, nombre_artista, descripcion, discos.nombre_disco from artistas inner join discos where artistas.id_artista = discos.id_artista");
		ps.setInt(1, id_artista);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			artista = new Artistas();
			artista.setId_Artista(rs.getInt(1));
			artista.setNombre_Artista(rs.getString(2));
			artista.setDescripcion(rs.getString(3));
		}
		Desconectar();
		return artista;
	}
}