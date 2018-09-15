package com.menta.restjersey;


import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;


public class DocumentFreeling {
	@JsonProperty
	Integer fragmentWindow;
	Integer fragmentJump;
	String text;
	String language;
	List<SentenceFreeling> fragments;
	
	

	
	public Integer getFragmentWindow() {
		return fragmentWindow;
	}
	public void setFragmentWindow(Integer fragmentWindow) {
		this.fragmentWindow = fragmentWindow;
	}
	public Integer getFragmentJump() {
		return fragmentJump;
	}
	public void setFragmentJump(Integer fragmentJump) {
		this.fragmentJump = fragmentJump;
	}
	public List<SentenceFreeling> getFragments() {
		return fragments;
	}
	public void setFragments(List<SentenceFreeling> fragments) {
		this.fragments = fragments;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	
	
	


	
}


