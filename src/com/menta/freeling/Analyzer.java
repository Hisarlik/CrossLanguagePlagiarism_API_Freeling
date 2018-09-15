package com.menta.freeling;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.menta.restjersey.DocumentFreeling;
import com.menta.restjersey.SentenceFreeling;
import com.menta.restjersey.TokenFreeling;

import java.io.IOException;
import edu.upc.freeling.*;

public class Analyzer {
  // Modify this line to be your FreeLing installation directory
  private static final String FREELINGDIR = "/usr/local";
  private static final String DATA = FREELINGDIR + "/share/freeling/";
  private static String LANG = "es";

  public static DocumentFreeling fragmentText( DocumentFreeling sentsText)  {
    System.loadLibrary( "freeling_javaAPI" );

    Util.initLocale( "default" );
    // Create analyzers.
    LangIdent lgid = new LangIdent(DATA + "/common/lang_ident/ident-few.dat");

    // Identify language of the text.  
    // Note that this will identify the language, but will NOT adapt
    // the analyzers to the detected language.  All the processing 
    // in the loop below is done by modules for LANG (set to "es" at
    // the beggining of this class) created above.
    LANG = lgid.identifyLanguage(sentsText.getText());
    System.out.println( "-------- LANG_IDENT results -----------" );
    System.out.println("Language detected (from first line in text): " + LANG);

    // Create options set for maco analyzer.
    // Default values are Ok, except for data files.
    MacoOptions op = new MacoOptions( LANG );

    op.setActiveModules(false, true, true, true, 
                               true, true, true, 
                               true, true, true);

    op.setDataFiles(
      "", 
      DATA+LANG+"/locucions.dat", 
      DATA + LANG + "/quantities.dat",
      DATA + LANG + "/afixos.dat",
      DATA + LANG + "/probabilitats.dat",
      DATA + LANG + "/dicc.src",
      DATA + LANG + "/np.dat",
      DATA + "common/punct.dat");



    Tokenizer tk = new Tokenizer( DATA + LANG + "/tokenizer.dat" );
    Splitter sp = new Splitter( DATA + LANG + "/splitter.dat" );
    Maco mf = new Maco( op );

    HmmTagger tg = new HmmTagger( DATA + LANG + "/tagger.dat", true, 2 );
    ChartParser parser = new ChartParser(
      DATA + LANG + "/chunker/grammar-chunk.dat" );
    DepTxala dep = new DepTxala( DATA + LANG + "/dep/dependences.dat",
      parser.getStartSymbol() );
    Nec neclass = new Nec( DATA + LANG + "/nerc/nec/nec-ab-poor1.dat" );

    Senses sen = new Senses(DATA + LANG + "/senses.dat" ); // sense dictionary
    Ukb dis = new Ukb( DATA + LANG + "/ukb.dat" ); // sense disambiguator



    // Identify language of the text.  
    // Note that this will identify the language, but will NOT adapt
    // the analyzers to the detected language.  All the processing 
    // in the loop below is done by modules for LANG (set to "es" at
    // the beggining of this class) created above.
  
    System.out.println( "-------- LANG_IDENT results -----------" );
    System.out.println("Language detected (from first line in text): " + LANG);


      // Extract the tokens from the line of text.
      ListWord l = tk.tokenize(sentsText.getText());

      // Split the tokens into distinct sentences.
      ListSentence ls = sp.split( l, false );
     
      System.out.println("Lista de frases:"+ls.size());
      // Perform morphological analysis
      mf.analyze( ls );

      // Perform part-of-speech tagging.
      tg.analyze( ls );

      // Perform named entity (NE) classificiation.
      neclass.analyze( ls );

      sen.analyze( ls );
      dis.analyze( ls );
      
	return BuildResponse(ls, sentsText, LANG);

     
    
  }



  private static DocumentFreeling BuildResponse( ListSentence ls, DocumentFreeling document, String language ) {


	
	  document.setLanguage(language);
	  List<SentenceFreeling> fragments = new ArrayList<SentenceFreeling>();	

      // get the analyzed words out of ls.
      // ListSentenceIterator sIt = new ListSentenceIterator(ls);
      

	  //SentenceFreeling sentenceFreeling = new SentenceFreeling();
  	  //List<TokenFreeling> listTokens = new ArrayList<TokenFreeling>();
  	  
  	  for(int index=0;index<ls.size();index+=document.getFragmentJump()) {
  		  
  		  System.out.println("Index: "+index);
  	      // get the analyzed words out of ls.
  	      ListSentenceIterator sIt = new ListSentenceIterator(ls);
  	     
  	      int sentenceId = 0;
  	      while (sIt.hasNext()) {
  	    	  
  	    	  if(index == sentenceId) {
  	    		  SentenceFreeling sentenceFreeling = new SentenceFreeling(language);
  	    		  List<TokenFreeling> listTokens = new ArrayList<TokenFreeling>();
  	    		  for(int indexWindow=0;indexWindow<document.getFragmentWindow();indexWindow++) {
  	    			 
  	    			  if(sIt.hasNext()) {
	  	    					    
		  	    			Sentence s = sIt.next();
		  	    			ListWordIterator wIt = new ListWordIterator(s);
		  	    	        while (wIt.hasNext()) {
		  	    	          Word w = wIt.next();
		  	    	          TokenFreeling token = new TokenFreeling(w.getForm(), w.getLemma(), w.getTag());
		  	    	          listTokens.add(token);
		  	    	          
		  	    	          System.out.print(
		  	    	            w.getForm() + " " + w.getLemma() + " " + w.getTag() );
		
		  	    	          System.out.println();
		  	    	        }
		  	    	       
		  	    			  
		  	    		  }
  	    			 sentenceFreeling.setTokens(listTokens);
	  	    		  }
  	    		 fragments.add(sentenceFreeling);
	  	    	  }else {
	  	    		sIt.next();
	  	    	  }
	  	    	  
	  	    	 
	  	    	  sentenceId++;
  	    	  
  	      }
  	   
  	  }
  		  
   
      document.setFragments(fragments);
      return document;
    }


  
}

