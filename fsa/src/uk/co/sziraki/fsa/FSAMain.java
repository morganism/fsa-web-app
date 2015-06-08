package uk.co.sziraki.fsa;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import uk.co.sziraki.fsa.json.Establishment;
import uk.co.sziraki.fsa.json.JSONHelper;
import uk.co.sziraki.fsa.web.AuthoritiesDD;
import uk.co.sziraki.fsa.web.RatingTable;
import uk.co.sziraki.fsa.web.ReadDataFromURL;
 
public class FSAMain extends AbstractHandler
{
	private static String HTTP_PORT = "8080";
	private static String METHOD_GET = "GET";
	private static String METHOD_POST = "POST";
	
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        
        String selectedLocalAuthortyId = (request.getParameter("authority") != null) ? request.getParameter("authority") : "";
        
        /**if (request.getMethod() != null && request.getMethod().equals(METHOD_GET))
        {**/
        	ReadDataFromURL reader = new ReadDataFromURL();
    		String json = reader.getAllLocalAuthoritiesData();
    		JSONHelper helper = new JSONHelper(json);
    		Map<String,String> map = helper.getAuthoritiesList();
    		AuthoritiesDD dd = new AuthoritiesDD(map);

    		response.getWriter().println("<h2>Food Standards Agency : Hygiene Ratings</h2>");
    		response.getWriter().println("<h4>Select a Local Authority</h4>");
    		//response.getWriter().println("<form method=\"POST\" action=\"http://localhost:" + HTTP_PORT + "\">");
    		response.getWriter().println("<form method=\"POST\" action=\"/\">");

        	response.getWriter().println(dd.getDropDown(selectedLocalAuthortyId));
        	response.getWriter().println("<input type=submit value=\"Get Rating Details\"></form>");
       /** }
        else **/
        	if (request.getMethod() != null && request.getMethod().equals(METHOD_POST))
        {
        	String localAuthorityId = request.getParameter("authority");
        	ReadDataFromURL readerP = new ReadDataFromURL();
    		String jsonP = readerP.getEstablishmentData(localAuthorityId);
    		JSONHelper helperP = new JSONHelper(jsonP);
    		Map<Integer,Establishment> mapP = helperP.getEstablishentsData();
    		RatingTable ratingTable = new RatingTable(mapP);
 

        	response.getWriter().println("<h2>Food Hygiene Ratings for " + 
        			helperP.getLocalAuthorityName() + "</h2>");
       		response.getWriter().println(ratingTable.getRatingTable());
    		
        }
        response.getWriter().println("<p><a href='https://github.com/morganism/fsa-web-app'>Source Code</a>");
    }
 
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new FSAMain());
 
        server.start();
        server.join();
    }
}
