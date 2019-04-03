import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gov.nih.nlm.nls.metamap.Ev;
import gov.nih.nlm.nls.metamap.Mapping;
import gov.nih.nlm.nls.metamap.MetaMapApi;
import gov.nih.nlm.nls.metamap.MetaMapApiImpl;
import gov.nih.nlm.nls.metamap.PCM;
import gov.nih.nlm.nls.metamap.Position;
import gov.nih.nlm.nls.metamap.Result;
import gov.nih.nlm.nls.metamap.Utterance;

public class ExtractEntity {

	String[] stype = { "aapp", "acab", "amas", "anab", "anst", "antb", "bacs", "bact", "biof",
			"bodm", "boc", "celc", "celf", "cell", "cgab", "clna", "clnd", "dsyn", "fngs", "genf",
			"gngm", "horm", "inpo", "imft", "irda" };

	public List<Entity> Extact(String text) {
		List<Entity> entityList = new ArrayList<Entity>();
		List<String> position = new ArrayList<String>();
		MetaMapApi api = new MetaMapApiImpl();
		List<Result> resultList = api.processCitationsFromString(text);
		Result result = resultList.get(0);

		try {
			List<Utterance> utterances = result.getUtteranceList();
			for (Utterance u : utterances) {
				for (PCM pcm : u.getPCMList()) {
					for (Mapping map : pcm.getMappingList()) {
						for (Ev mapEv : map.getEvList()) {
							List<String> st = mapEv.getSemanticTypes();
							for (String t : st) {
								if (Arrays.asList(stype).contains(t)&!position.contains(mapEv.getPositionalInfo().toString())) {
									
									//System.out.println(mapEv.getPositionalInfo().toString());
									List<String> orig = new ArrayList<String>();
									for(Position p :mapEv.getPositionalInfo()) {
										orig.add(text.substring(p.getX(), p.getX()+p.getY()));
									}

									Entity entity = new Entity(mapEv.getScore(), mapEv.getConceptId(),
											mapEv.getConceptName(), mapEv.getPreferredName(), mapEv.getMatchedWords(),
											mapEv.getSemanticTypes(), mapEv.getMatchMap(), mapEv.isHead(),
											mapEv.isOvermatch(), mapEv.getSources(), orig);
									entityList.add(entity);
									position.add(mapEv.getPositionalInfo().toString());
//									System.out.println(position);
//									System.out.println("   Score: " + mapEv.getScore());
//									System.out.println("   Concept Id: " + mapEv.getConceptId());
//									System.out.println("   Concept Name: " + mapEv.getConceptName());
//									System.out.println("   Preferred Name: " + mapEv.getPreferredName());
//									System.out.println("   Matched Words: " + mapEv.getMatchedWords());
//									System.out.println("   Semantic Types: " + mapEv.getSemanticTypes());
//									System.out.println("   MatchMap: " + mapEv.getMatchMap());
//									System.out.println("   MatchMap alt. repr.: " + mapEv.getMatchMapList());
//									System.out.println("   is Head?: " + mapEv.isHead());
//									System.out.println("   is Overmatch?: " + mapEv.isOvermatch());
//									System.out.println("   Sources: " + mapEv.getSources());
//									System.out.println("   Positional Info: " + orig);
									break;
								}

							}
							//

						}
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entityList;
	}

//	public static void main(String[] args) {
//		ExtractEntity ee = new ExtractEntity();
//		ee.Extact("Unlinked control of multiple glucocorticoid-induced processes in HTC cells.");
//	}
}
