package sv.edu.udb.serviciorestartistasdiscos;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hello")
public class Test {
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public String getMessage(){
		return "Hola Mundo";
		}
}
