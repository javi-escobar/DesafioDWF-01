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
import sv.edu.udb.model.Artistas;
import sv.edu.udb.model.Discos;
import sv.edu.udb.model.ArtistasDAO;
import sv.edu.udb.model.DiscosDAO;

@Path("artistas")
public class ArtistasRest {
	ArtistasDAO artistaDAO = new ArtistasDAO();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArtistas() throws SQLException{
		ArtistasDAO artistasDAO = new ArtistasDAO();
		List<Artistas> artistas = artistasDAO.findAll();
		return Response.status(200).entity(artistas).build();
	}
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByArtistasId(@PathParam("id") int id) throws SQLException {
		ArtistasDAO artistasDAO = new ArtistasDAO();
		Artistas artista = artistasDAO.findById(id);
		if(artista == null) {
			return Response.status(404).build();
		}
		return Response.status(200).entity(artista).build();
	}
    @GET
    @Path("{id}/discos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscosPorArtista(@PathParam("id") int id) {
        try {
            Artistas artista = artistaDAO.findById(id);
            if (artista == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Artista no encontrado").build();
            }

            DiscosDAO discosDAO = new DiscosDAO();
            List<Discos> discos = discosDAO.obtenerDiscosPorIdArtista(id);

            return Response.ok(discos).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener los discos").build();
        }
    }
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertArtista(@FormParam("id_artista") int id, @FormParam("nombre_artista") String art, @FormParam("descripcion") String des) throws SQLException {
		Artistas artista = new Artistas();
		try {
			artista.setId_Artista(id);
			artista.setNombre_Artista(art);
			artista.setDescripcion(des);
			artistaDAO.insertarArtista(artista);
			artista = artistaDAO.findById(artista.getId_Artista());
			return Response.status(201).entity(artista).build();	
		}
		catch (SQLException e) {
			return Response.status(400).header("Bad Request", "*").build();
		}
		
	}
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateArtista(@PathParam("id") int id, @FormParam("nombre_artista") String art, @FormParam("descripcion") String des) throws SQLException {
		Artistas artista = artistaDAO.findById(id);
		if(artista == null) {
			return Response.status(404).header("NOT FOUND: Artista No Encontrado", "*").build();
		}
		artista.setNombre_Artista(art);
		artista.setDescripcion(des);
		artistaDAO.updateArtista(artista);
		return Response.status(204).header("No Content", "*").entity(artista).build();
		
	}
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response eliminarArtista(@PathParam("id") int id) throws SQLException{
		Artistas artista = artistaDAO.findById(id);
		if(artista == null) {
			return Response.status(404).header("NOT FOUND: Artista No Encontrado", "*").build();
		}
		artistaDAO.deleteArtista(id);
		return Response.status(204).header("No Content", "*").entity(artista).build();
	}
}
