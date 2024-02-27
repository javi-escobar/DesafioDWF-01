package sv.edu.udb.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscosDAO extends AppConnection {

    public void insertarDisco(Discos disco) throws SQLException {
        Conectar();
        ps = con.prepareStatement("INSERT INTO discos (nombre_disco, id_artista, numero_canciones, precio) VALUES (?, ?, ?, ?)");
        ps.setString(1, disco.getNombre_disco());
        ps.setInt(2, disco.getId_artista());
        ps.setInt(3, disco.getNumero_canciones());
        ps.setDouble(4, disco.getPrecio());
        ps.execute();
        Desconectar();
    }
    
    
   
    public List<Discos> findAll() throws SQLException {
        Conectar();
        st = con.createStatement();
        rs = st.executeQuery("SELECT discos.id_disco, discos.nombre_disco, discos.id_artista, discos.numero_canciones, discos.precio, artistas.nombre_artista FROM discos INNER JOIN artistas ON discos.id_artista = artistas.id_artista");
        List<Discos> discos = new ArrayList();

        while (rs.next()) {
            Discos disco = new Discos();
            disco.setId_disco(rs.getInt("id_disco"));
            disco.setNombre_disco(rs.getString("nombre_disco"));
            disco.setId_artista(rs.getInt("id_artista"));
            disco.setNumero_canciones(rs.getInt("numero_canciones"));
            disco.setPrecio(rs.getDouble("precio")); 
            disco.setNombre_artista(rs.getString("nombre_artista"));
            discos.add(disco);
        }

        Desconectar();
        return discos;
    }

    
    public Discos findById(int id) throws SQLException {
        Discos disco = null;
        Conectar();
        ps = con.prepareStatement("SELECT discos.id_disco, discos.nombre_disco, discos.id_artista, discos.numero_canciones, discos.precio, artistas.nombre_artista FROM discos INNER JOIN artistas ON discos.id_artista = artistas.id_artista WHERE discos.id_disco = ?");
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            disco = new Discos();
            disco.setId_disco(rs.getInt("id_disco"));
            disco.setNombre_disco(rs.getString("nombre_disco"));
            disco.setId_artista(rs.getInt("id_artista"));
            disco.setNumero_canciones(rs.getInt("numero_canciones"));
            disco.setPrecio(rs.getDouble("precio")); 
            disco.setNombre_artista(rs.getString("nombre_artista"));
        }

        Desconectar();
        return disco;
    }



    public void actualizarDisco(Discos disco) throws SQLException {
        Conectar();
        ps = con.prepareStatement("UPDATE discos SET nombre_disco = ?, id_artista = ?, numero_canciones = ?, precio = ? WHERE id_disco = ?");
        ps.setString(1, disco.getNombre_disco());
        ps.setInt(2, disco.getId_artista());
        ps.setInt(3, disco.getNumero_canciones());
        ps.setDouble(4, disco.getPrecio());
        ps.setInt(5, disco.getId_disco());
        ps.executeUpdate();
        Desconectar();
    }

    public void eliminarDisco(int id) throws SQLException {
        Conectar();
        ps = con.prepareStatement("DELETE FROM discos WHERE id_disco = ?");
        ps.setInt(1, id);
        ps.execute();
        Desconectar();
    }
    
    
    public List<Discos> obtenerDiscosPorIdArtista(int idArtista) throws SQLException {
        Conectar();
        ps = con.prepareStatement("SELECT discos.*, artistas.nombre_artista FROM discos INNER JOIN artistas ON discos.id_artista = artistas.id_artista WHERE discos.id_artista = ?");
        ps.setInt(1, idArtista);
        rs = ps.executeQuery();
        List<Discos> discos = new ArrayList();

        while (rs.next()) {
            Discos disco = new Discos();
            disco.setId_disco(rs.getInt("id_disco"));
            disco.setNombre_disco(rs.getString("nombre_disco"));
            disco.setId_artista(rs.getInt("id_artista"));
            disco.setNumero_canciones(rs.getInt("numero_canciones"));
            disco.setPrecio(rs.getDouble("precio")); 
            disco.setNombre_artista(rs.getString("nombre_artista"));
            discos.add(disco);
        }

        Desconectar();
        return discos;
    }


    public ArrayList<Discos> obtenerTodosLosDiscos() throws SQLException {
        Conectar();
        st = con.createStatement();
        rs = st.executeQuery("SELECT discos.id_disco, discos.nombre_disco, discos.id_artista, discos.numero_canciones, discos.precio, artistas.nombre_artista FROM discos INNER JOIN artistas ON discos.id_artista = artistas.id_artista");
        ArrayList<Discos> discos = new ArrayList();

        while(rs.next()) {
            Discos disco = new Discos();
            disco.setId_disco(rs.getInt(1));
            disco.setNombre_disco(rs.getString(2));
            disco.setId_artista(rs.getInt(3));
            disco.setNumero_canciones(rs.getInt(4));
            disco.setPrecio(rs.getDouble(5)); 
            disco.setNombre_artista(rs.getString(6)); 
            discos.add(disco);
        }
        Desconectar();
        return discos;
    }

    public Discos obtenerDiscoPorId(int id_disco) throws SQLException {
        Discos disco = null;
        Conectar();
        ps = con.prepareStatement("SELECT discos.id_disco, discos.nombre_disco, discos.id_artista, discos.numero_canciones, discos.precio, artistas.nombre_artista FROM discos INNER JOIN artistas ON discos.id_artista = artistas.id_artista WHERE discos.id_disco = ?");
        ps.setInt(1, id_disco);
        rs = ps.executeQuery();

        if(rs.next()) {
            disco = new Discos();
            disco.setId_disco(rs.getInt(1));
            disco.setNombre_disco(rs.getString(2));
            disco.setId_artista(rs.getInt(3));
            disco.setNumero_canciones(rs.getInt(4));
            disco.setPrecio(rs.getDouble(5)); 
            disco.setNombre_artista(rs.getString(6));
        }
        Desconectar();
        return disco;
    }
}
