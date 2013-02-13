package JerseyTutorial;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.research.ws.wadl.Response;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

/**
 * Created with IntelliJ IDEA.
 * User: RMaharaj
 * Date: 2/12/13
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/helloworld")
public class HelloWorldResource {
	
	private static final DateFormat DATE_PARSER = new SimpleDateFormat("yyyy-MM-dd");

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getClichedMessaged(){
        return "Hello World";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getClichedMessageAsRSS() throws IOException, FeedException, ParseException{
    	String feedType = "atom_1.0";
    	
    	SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType(feedType);
        feed.getSupportedFeedTypes();

        feed.setTitle("Sample Feed (created with ROME)");
        feed.setLink("http://rome.dev.java.net");
        feed.setDescription("This feed has been created using ROME (Java syndication utilities");

        List entries = new ArrayList();
        SyndEntry entry;
        SyndContent description;

        entry = new SyndEntryImpl();
        entry.setTitle("ROME v1.0");
        entry.setLink("http://wiki.java.net/bin/view/Javawsxml/Rome01");
        entry.setPublishedDate(DATE_PARSER.parse("2004-06-08"));
        description = new SyndContentImpl();
        description.setType("text/plain");
        description.setValue("Initial release of ROME");
        entry.setDescription(description);
        entries.add(entry);

        entry = new SyndEntryImpl();
        entry.setTitle("ROME v2.0");
        entry.setLink("http://wiki.java.net/bin/view/Javawsxml/Rome02");
        entry.setPublishedDate(DATE_PARSER.parse("2004-06-16"));
        description = new SyndContentImpl();
        description.setType("text/plain");
        description.setValue("Bug fixes, minor API changes and some new features");
        entry.setDescription(description);
        entries.add(entry);

        entry = new SyndEntryImpl();
        entry.setTitle("ROME v3.0");
        entry.setLink("http://wiki.java.net/bin/view/Javawsxml/Rome03");
        entry.setPublishedDate(DATE_PARSER.parse("2004-07-27"));
        description = new SyndContentImpl();
        description.setType("text/html");
        description.setValue("<p>More Bug fixes, mor API changes, some new features and some Unit testing</p>"+
                             "<p>For details check the <a href=\"https://rometools.jira.com/wiki/display/ROME/Change+Log#ChangeLog-Changesmadefromv0.3tov0.4\">Changes Log</a></p>");
        entry.setDescription(description);
        entries.add(entry);

        feed.setEntries(entries);
        
        

//        String fileName = "test.rss";
//		Writer writer = new FileWriter(fileName);
        StringWriter stringWriter = new StringWriter();
        SyndFeedOutput output = new SyndFeedOutput();
        
        output.output(feed,stringWriter);
        

//        System.out.println("The feed has been written to the file ["+fileName+"]");
		return stringWriter.toString();
    }


}
