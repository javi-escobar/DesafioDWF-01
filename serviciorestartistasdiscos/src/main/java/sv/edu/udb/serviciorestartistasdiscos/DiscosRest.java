package sv.edu.udb.serviciorestartistasdiscos;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.edu.udb.model.Discos;
import sv.edu.udb.model.ArtistasDAO;
import sv.edu.udb.model.DiscosDAO;
@Path("discos")
public class DiscosRest {
    DiscosDAO discosDAO = new DiscosDAO();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscos() throws SQLException {
        List<Discos> discos = discosDAO.findAll();
        return Response.status(200).entity(discos).build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDiscoId(@PathParam("id") int id) throws SQLException {
        Discos disco = discosDAO.findById(id);
        if (disco == null) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(disco).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarDisco(@FormParam("nombre_disco") String nombre_disco,
                                   @FormParam("id_artista") int id_artista,
                                   @FormParam("numero_canciones") int numero_canciones,
                                   @FormParam("precio") double precio) throws SQLException {
        Discos disco = new Discos();
        
        try {
        	disco.setNombre_disco(nombre_disco);
            disco.setId_artista(id_artista);
            disco.setNumero_canciones(numero_canciones);
            disco.setPrecio(precio);
            
            discosDAO.insertarDisco(disco);
            disco = discosDAO.obtenerDiscoPorId(disco.getId_disco());
            
           
            return Response.status(201).entity(disco).build();
		}
		catch (SQLException e) {
			return Response.status(400).header("Bad Request", "*").build();
		}
    }


    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateDisco(@PathParam("id") int id, @FormParam("nombre_disco") String nombre_disco,
                                @FormParam("id_artista") int id_artista,
                                @FormParam("numero_canciones") int numero_canciones,
                                @FormParam("precio") double precio) throws SQLException {
        Discos disco = discosDAO.findById(id);
        if(disco == null) {
            return Response.status(404).header("NOT FOUND: Disco No Encontrado", "*").build();
        }
        disco.setNombre_disco(nombre_disco);
        disco.setId_artista(id_artista);
        disco.setNumero_canciones(numero_canciones);
        disco.setPrecio(precio);
        discosDAO.actualizarDisco(disco);
        return Response.status(204).header("No Content", "*").entity(disco).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response eliminarDisco(@PathParam("id") int id) throws SQLException {
        Discos disco = discosDAO.findById(id);
        if(disco == null) {
            return Response.status(404).header("NOT FOUND: Disco No Encontrado", "*").build();
        }
        discosDAO.eliminarDisco(id);
        return Response.status(204).header("No Content", "*").build();
    }

}