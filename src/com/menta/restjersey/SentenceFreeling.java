package com.menta.restjersey;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

//@JsonInclude(Include.NON_EMPTY)
public class SentenceFreeling {
	@JsonProperty
	String language;
	List<TokenFreeling> tokens;
	
	public SentenceFreeling(String language) {
		this.setLanguage(language);
	}
	public List<TokenFreeling> getTokens() {
		return tokens;
	}


	public void setTokens(List<TokenFreeling> tokens) {
		this.tokens = tokens;
	}
	

	public SentenceFreeling() {
		
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}
	
	

	
}


