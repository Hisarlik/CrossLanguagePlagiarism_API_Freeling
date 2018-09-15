package com.menta.restjersey;

public class TokenFreeling {
	
	String form;
	String lemma;
	String tagFreeling;
	
	
	public TokenFreeling(String form2, String lemma2, String tag2) {
		this.form = form2;
		this.lemma = lemma2;
		this.tagFreeling = tag2;
		
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getLemma() {
		return lemma;
	}
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}
	public String getTagFreeling() {
		return tagFreeling;
	}
	public void setTag(String tag) {
		this.tagFreeling = tag;
	}
	
	

}
