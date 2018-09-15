package com.menta.restjersey;

 


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.menta.freeling.Analyzer;


 
@Path("/analysis_service")
public class AnalysisService {
 

 
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces({MediaType.APPLICATION_JSON})
	  public Response getAnalysisFromFreeling(DocumentFreeling document)  {
		 
		// Text of the document and Analyze data
		System.out.println("Document"+document.getText());
		DocumentFreeling documentAnalyzed = (DocumentFreeling) Analyzer.fragmentText(document);
		 
		return Response.status(200).entity(documentAnalyzed).build();
		
	  }
}
