package uk.co.sziraki.fsa.web;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uk.co.sziraki.fsa.json.Establishment;

public class RatingTable 
{
	private Map<Integer, Establishment> map;

	
	public RatingTable(Map<Integer, Establishment> map)
	{
		this.map = map;
	}
	
	/**
	 * Get an HTML table of ratings
	 * @return the html table
	 */
	public String getRatingTable()
	{
		int establishmentCount = 0;
		
		Map<String, Integer> ratings = new HashMap<String, Integer>();
		for (Map.Entry<Integer, Establishment> entry : map.entrySet())
		{
			establishmentCount++;
			Establishment e = entry.getValue();
			
			
			String ratingValue = e.getRatingValue();
			
			int count = ratings.containsKey(ratingValue) ? ratings.get(ratingValue) : 0;
			ratings.put(ratingValue, count + 1);
		}
	
		StringBuilder sb = new StringBuilder("<table border=1>");
		
		sb.append("<tr bgcolor=\"#eeeeee\"><th>Rating Value</th><th>Percent</th></tr>");
		int l = 0; // line number
		for (Map.Entry<String,Integer> entry : sortByComparator(ratings).entrySet())
		{
			int itemCount = entry.getValue();
			String bgcolor = (l++ % 2 == 0) ? "#ffffff" : "#f2f2f2"; // alternate colors
			sb.append("<tr bgcolor=\"" + bgcolor +"\"><td>").
				append(entry.getKey()).
				append("</td><td>").
				append(getPercent(establishmentCount, itemCount)).
				append("</td></tr>");
		}
		sb.append("</table>");
				
		return sb.toString();
	}
	
	private float getPercent(int totalCount, int itemCount)
	{
		return 100f * itemCount / totalCount;
	}

	/**
	 * Sort the ratings map by value
	 * @param unsortMap
	 * @return the sorted by value map
	 */
	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) 
	{
		 
		// Convert Map to List
		List<Map.Entry<String, Integer>> list = 
				new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
 
		// Sort list with comparator, to compare the Map values
		Collections.sort
		(
			list, 
			new Comparator<Map.Entry<String, Integer>>() 
			{
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) 
				{
					// reverse sort o2 comp to 01 so highest is first
					return (o2.getValue()).compareTo(o1.getValue());
				}
			}
		);
 
		// Convert sorted map back to a Map
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) 
		{
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
