package uk.co.sziraki.fsa.web;

import java.util.Map;
import java.util.TreeMap;

public class AuthoritiesDD 
{
	private Map<String,String> map;
	public AuthoritiesDD (Map<String,String> map)
	{
		this.map = map;
	}
	
	public String getDropDown(String selectedLocalAuthorityId)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<select name=\"authority\">");
		
		Map<String,String> treeMap = new TreeMap<String,String>(map);
		for (Map.Entry<String,String> entry : treeMap.entrySet())
		{
			String selected = (entry.getValue().equals(selectedLocalAuthorityId)) ? "selected" : "";
			sb.append("<option value=\"" + entry.getValue() + "\"" + selected + ">" + entry.getKey() + "</option>");
		}
		
		sb.append("</select>");
		
		return sb.toString();
	}


}
