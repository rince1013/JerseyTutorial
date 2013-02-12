package JerseyTutorial;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created with IntelliJ IDEA.
 * User: RMaharaj
 * Date: 2/12/13
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/helloworld")
public class HelloWorldResource {

    @GET
    @Produces("text/plain")
    public String getClichedMessaged(){
        return "Hello World";
    }


}
