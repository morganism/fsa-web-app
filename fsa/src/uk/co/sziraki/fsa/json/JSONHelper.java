package uk.co.sziraki.fsa.json;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper 
{
	String jsonString = "";
	private String localAuthorityName;
	
	/**
	 * Construct a new helper
	 * @param json
	 */
	public JSONHelper(String jsonString)
	{
		this.jsonString = jsonString;
	}
	
	public String getAuthoritiesJSON()
	{
		return getJSON("authorities");
	}
	
	public String getEstablishmentsJSON()
	{
		return getJSON("establishments");
	}
	
	public String getJSON(String key)
	{
		String json = "";
		
		try 
		{
			JSONObject jsonObject = new JSONObject(jsonString);
			json = jsonObject.get(key).toString();
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return json;
	}	
	
	
	public Map<String,String> getAuthoritiesList()
	{
		Map<String,String> map = new HashMap<String,String>();
		
		String json = getAuthoritiesJSON();
		try 
		{
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String name = jsonObject.getString("Name");
				String localAuthorityId = Integer.toString(jsonObject.getInt("LocalAuthorityId"));
				map.put(name, localAuthorityId);
			}
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	public Map<Integer,Establishment> getEstablishentsData()
	{
		Map<Integer,Establishment> map = new HashMap<Integer,Establishment>();
		
		String json = getEstablishmentsJSON();
		try 
		{
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				int fhrsid = jsonObject.getInt("FHRSID");
				String ratingValue = jsonObject.getString("RatingValue");
				setLocalAuthorityName(jsonObject.getString("LocalAuthorityName"));
				
				Establishment e = new Establishment(fhrsid, ratingValue, getLocalAuthorityName());
				map.put(fhrsid, e);
			}
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}

	/**
	 * @return the localAuthorityName
	 */
	public String getLocalAuthorityName() {
		return localAuthorityName;
	}

	/**
	 * @param localAuthorityName the localAuthorityName to set
	 */
	public void setLocalAuthorityName(String localAuthorityName) {
		this.localAuthorityName = localAuthorityName;
	}

	
	


}
