import java.util.List;

import gov.nih.nlm.nls.metamap.Position;

public class Entity {
	public int Score;
    public String ConceptId;
    public String ConceptName;
    public String PreferredName;
    public List<String> MatchedWords;
    public List<String> SemanticTypes;
    public List<Object> MatchMap;
    //public List<MatchMap> MatchMap_alt_repr;
    public boolean isHead;
    public boolean isOvermatch;
    public List<String> Sources;
    public List<String> PositionalInfo;
    
    public Entity(int Score,String ConceptId,String ConceptName,String PreferredName,List<String> MatchedWords,
    		List<String> SemanticTypes,List<Object> MatchMap,boolean isHead,
    		boolean isOvermatch,List<String> Sources,List<String> PositionalInfo) {
    	this.Score = Score;
    	this.ConceptId = ConceptId;
    	this.ConceptName = ConceptName;
    	this.PreferredName = PreferredName;
    	this.MatchedWords = MatchedWords;
    	this.SemanticTypes = SemanticTypes;
    	this.MatchMap = MatchMap;
    	//this.MatchMap_alt_repr = MatchMap_alt_repr;
    	this.isHead = isHead;
    	this.isOvermatch = isOvermatch;
    	this.Sources = Sources;
    	this.PositionalInfo = PositionalInfo;
    }
}
