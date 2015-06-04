package uk.co.sziraki.fsa.json;

public class Establishment 
{
	int fhrsid;
	String ratingValue;
	String localAuthorityName;
	public Establishment(int fhrsid2, String ratingValue, String localAuthorityName)
	{
		this.fhrsid = fhrsid2;
		this.ratingValue = ratingValue;
		this.localAuthorityName = localAuthorityName;
	}

	/**
	 * @return the fhrsid
	 */
	public int getFhrsid() {
		return fhrsid;
	}
	/**
	 * @param fhrsid the fhrsid to set
	 */
	public void setFhrsid(int fhrsid) {
		this.fhrsid = fhrsid;
	}
	/**
	 * @return the ratingValue
	 */
	public String getRatingValue() {
		return ratingValue;
	}
	/**
	 * @param ratingValue the ratingValue to set
	 */
	public void setRatingValue(String ratingValue) {
		this.ratingValue = ratingValue;
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
