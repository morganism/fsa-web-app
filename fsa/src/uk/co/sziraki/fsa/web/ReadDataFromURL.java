package uk.co.sziraki.fsa.web;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


/**
 * @author Morgan Sziraki
 *
 */
public class ReadDataFromURL 
{
	private static String API_BASE_URL = "http://api.ratings.food.gov.uk/";
	private static String API_AUTHORITIES_URL = "Authorities";
	private static String API_ESTABLISHMENTS_URL = "Establishments?localAuthorityId=";
	
	private static String REQ_PARAM_API_VERSION_NAME = "x-api-version";
	private static String REQ_PARAM_API_VERSION_VALUE = "2";
	
	private String readUrl(String urlString)
	{
		HttpParams httpParameters;
		HttpResponse response;
		
		String result = "";

		try
		{
			httpParameters = new BasicHttpParams();
			HttpGet request = new HttpGet(urlString);
			//	Session.Request.Headers.Add("x-api-version", 2);
			request.addHeader(REQ_PARAM_API_VERSION_NAME,REQ_PARAM_API_VERSION_VALUE);
			HttpClient client = new DefaultHttpClient(httpParameters);
			response = client.execute(request);
			result = EntityUtils.toString(response.getEntity());
		}
		catch (URISyntaxException | HttpException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Get the JSON packet for all authorities
	 * @return
	 */
	public String getAllLocalAuthoritiesData()
	{
		return readUrl(API_BASE_URL + API_AUTHORITIES_URL);
	}
	
	/**
	 * Get the JSON packet for a single authority
	 * @param localAuthorityId The local authority id 
	 * @return
	 */
	public String getLocalAuthorityData(String localAuthorityId)
	{
		return readUrl(API_BASE_URL + API_AUTHORITIES_URL + "/" + localAuthorityId);
	}
	
	/**
	 * Get the JSON packet for a single authority
	 * @param localAuthorityId The local authority id 
	 * @return
	 */
	public String getEstablishmentData(String localAuthorityId)
	{
		return readUrl(API_BASE_URL + API_ESTABLISHMENTS_URL + localAuthorityId);
	}	
}
