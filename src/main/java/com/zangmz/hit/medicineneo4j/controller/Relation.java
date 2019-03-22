package com.zangmz.hit.medicineneo4j.controller;

import com.zangmz.hit.medicineneo4j.bo.res.BaseRes;
import com.zangmz.hit.medicineneo4j.pmo2domain.PMO2Relation;
import com.zangmz.hit.medicineneo4j.services.PMO2Service;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Getter
@Setter
public class Relation {

    @Resource
    private PMO2Service pmo2Service;

    /*some classes*/
    @Getter
    @Setter
    public static class NewNode{
        private String ID;
        private String name;
        private String subclass_of;
        private String tree_number;
        private String mtid;
        private String mcid;

        public void setTree_number(String tree_number){
            //String new_tree=tree_number.replaceAll("\\$","\\\\n");
            String new_tree=tree_number.replaceAll("\\$","  ");

            this.tree_number = new_tree.replaceAll("\"","\'");
        }
        public void setName(String name){
            this.name=name.replaceAll("\"","\'");
        }
        public String getinfo(){
            String ans = "";
           if(name!=null){
                ans += "Name:";
                ans += name;
            }
            if(subclass_of!=null){
                ans += "\\nSubclass of:";
                ans+=subclass_of;
            }
            if(tree_number!=null){
                ans+="\\nTree number:";
                ans+=tree_number;
            }
            if(mtid!=null){
                ans+="\\nMTID:";
                ans+=mtid;
            }
            if(mcid!=null) {
                ans += "\\nMCID:";
                ans += mcid;
            }
                return ans;
        }
    }
    @Getter
    @Setter
    public static class arelation{
        private String pmid;
        private String sentence;
        private String rel_name;

        public void setSentence(String sentence){
            String new_sentence=sentence.replaceAll("\"","\'");
            this.sentence=new_sentence;

        }
        /*public String getRel_name() {
            if(rel_name==null) return "";
            return rel_name;
        }*/
        /*public String getPmid(){
            return pmid;
        }

        public String getSentence() {
            return sentence;
        }*/
        public String getinfo(String type){
            String ans="";
            if(type!=null){
                ans+="Type:"+type+"\\n";
            }
            if(rel_name!=null){
                ans+="Rel_name:"+rel_name+"\\n";
            }
            if(pmid!=null){
                ans+="Pmid:"+pmid+"\\n";
            }
            if(sentence!=null){
                ans+="Sentence:"+sentence;
            }


            //ans+="Type:"+type+"\\nRel_name:" + rel_name + "\\nPmid:"+pmid+"\\nSentence:"+sentence;
            return ans;
        }
        /*public void setPmid(String pmid){
            this.pmid=pmid;
        }
        public void setSentence(String sentence){
            String new_sen=sentence.replaceAll(",","\\\\n");
            this.sentence=new_sen;
        }
        public void setRel_name(String rel_name){
            this.rel_name=rel_name;
        }*/

    }



    /*init info which is used for searching*/
    private String head_entity;
    private String tail_entity;
    private String umls_pcnn;
    private String umls_rel;
    private String pmoz_rel;
    private String subclass_of;



    /*info that will get after searching*/
    private int return_code=-1;
    public arelation[] umls_pcnn_relations;
    public arelation[] umls_rel_relations;
    public arelation[] pmoz_rel__relations;
    public arelation[] subclass_of__relations;
    private String relation="";
    private String return_message;
    public NewNode subject = new NewNode();
    public NewNode object = new NewNode();



    /*functions*/
    public void InitNode(NewNode node, JSONObject node_info){
        node.setID(String.valueOf(node_info.getInt("id")));
        if(!node_info.getString("name").equals("null")) node.setName(node_info.getString("name"));
        if(!node_info.getString("MTID").equals("null")) node.setMtid(node_info.getString("MTID"));    //This may cause failure, because the data sample in the doc is mtid, but the function returns MTID.
        if(!node_info.getString("MCID").equals("null")) node.setMcid(node_info.getString("MCID"));
        if(!node_info.getString("subclass_of").equals("null")) node.setSubclass_of(node_info.getString("subclass_of"));
        if(!node_info.getString("tree_number").equals("null")) node.setTree_number(node_info.getString("tree_number"));
    }
    public void setrelreturn(String newreturnmessage,Integer newreturncode){
        if(newreturncode!=0&&return_code!=0){
            return_code=newreturncode;
            return_message=newreturnmessage;
            return;
        }
        if(newreturncode!=0&&return_code==0){
            return;
        }
        if(newreturncode==0&&return_code!=0){
            return_code=newreturncode;
            return_message=newreturnmessage;
        }
    }



    public void setinfo(JSONObject data, String rel)
    {


        InitNode(subject,data.getJSONObject("subject"));
        InitNode(object, data.getJSONObject("object"));


        arelation[] temp_relations = new arelation[1];
        JSONObject temp_rel = data.getJSONObject("relation");
        arelation temp_relation = new arelation();
        if(!temp_rel.getString("pmid").equals("null")) temp_relation.setPmid(temp_rel.getString("pmid"));
        if(!temp_rel.getString("relation").equals("null")) temp_relation.setRel_name(temp_rel.getString("relation"));
        if(!temp_rel.getString("sentence").equals("null")) temp_relation.setSentence(temp_rel.getString("sentence"));
            temp_relations[0] = temp_relation;
        if(rel.equals("umls_pcnn")){
            umls_pcnn_relations = temp_relations;
        }
        else if(rel.equals("umls_rel")){
            umls_rel_relations = temp_relations;
        }
        else  if(rel.equals("pmoz_rel")){
            pmoz_rel__relations = temp_relations;
        }
        else{
            subclass_of__relations = temp_relations;
        }
    }



    public int getReturn_code(){
        return return_code;
    }
    public String getReturn_message(){
        return return_message;
    }


    public String getRelation()
    {
        return relation;
    }

}