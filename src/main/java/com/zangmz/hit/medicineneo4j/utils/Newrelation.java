package com.zangmz.hit.medicineneo4j.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Newrelation {


    @Getter
    @Setter
    public static class NewNode{
        private String ID;
        private String name;
        private String subclass_of;
        private String tree_number;
        private String mTID;
        private String mCID;

        public void setmTID(String mTID){
            if(mTID.equals("null")){
                this.mTID=null;
            }
            else{
                this.mTID = mTID;
            }
        }
        public void setmCID(String mCID){
            if(mCID.equals("null")){
                this.mCID=null;
            }
            else{
                this.mCID = mCID;
            }
        }
        public void setTree_number(String tree_number){
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
                ans += "<br>Subclass of:";
                ans+=subclass_of;
            }
            if(tree_number!=null){
                ans+="<br>Tree number:";
                ans+=tree_number;
            }
            if(mCID!=null){
                ans+="<br>MTID:";
                ans+=mCID;
            }
            if(mTID!=null) {
                ans += "<br>MCID:";
                ans += mTID;
            }
            return ans;
        }
    }
    @Getter
    @Setter
    public static class arelation{
        private String pmid;
        private String sentence;

        public void setPmid(String pmid){
            if(pmid.equals("null")){
                this.pmid =null;
            }
            else{
                this.pmid = pmid;
            }
        }

        public void setSentence(String sentence){
            String new_sentence=sentence.replaceAll("\"","\'");
            if(new_sentence.equals("null")){
                this.sentence =null;
            }
            else{
                this.sentence=new_sentence;
            }
        }
        public String getinfo(){
            String ans="";
            if(pmid!=null){
                ans+="Pmid:"+pmid+"<br>";
            }
            if(sentence!=null){
                ans+="Sentence:"+sentence;
            }
            return ans;
        }

    }
    @Getter
    @Setter
    public static class info{
        public NewNode subject = new NewNode();
        public NewNode object = new NewNode();
        private String rel_name,type_name,subject_name,object_name,info,allinfo;
        private String rels,all_rels;
        private int cnt,all_cnt;
        public String getInfo(){
            String ans="";
            ans+="Subject:"+this.subject_name+"<br>"+"Object:"+this.object_name+"<br>"+"Rel_name:"+this.rel_name+"<br>"+"Type:"+this.type_name+"<br>"+rels;
            return ans;
        }
        public String getAllinfo(){
            String ans="";
            ans+="Subject:"+this.subject_name+"<br>"+"Object:"+this.object_name+"<br>"+"Rel_name:"+this.rel_name+"<br>"+"Type:"+this.type_name+"<br>"+all_rels;
            return ans;
        }
        public void setRel_name(String rel_name){
            if(rel_name.equals("null")){
                this.rel_name = null;
            }
            else{
                this.rel_name = rel_name;
            }
        }
    }

    private Map<String, info> dic = new HashMap<>();
    public Map<String, info> infoin(JSONArray datas, String type_name){

        for(int i=0; i<datas.size();i++){
            com.alibaba.fastjson.JSONObject data=datas.getJSONObject(i);
            String sub_name = data.getJSONObject("subject").getString("name");
            String obj_name = data.getJSONObject("object").getString("name");
            String rel_name = data.getJSONObject("relation").getString("relation");
            String key = sub_name + "$" + obj_name + "$" + rel_name + "$" + type_name;
            if(dic.containsKey(key)){
                info a = dic.get(key);
                String old_rels = a.getRels();
                arelation rel = JSON.toJavaObject(data.getJSONObject("relation"),arelation.class);
                if(a.getCnt()<2){
                    a.setRels(old_rels+"<br><br>"+rel.getinfo());
                    a.setCnt(a.getCnt()+1);
                    a.setAll_cnt(a.getAll_cnt()+1);
                    a.setAll_rels(old_rels+"<br><br>"+rel.getinfo());
                }
                else{
                    a.setAll_rels(old_rels+"<br><br>"+rel.getinfo());
                    a.setAll_cnt(a.getAll_cnt()+1);
                }

            }
            else{
                info a = new info();
                a.setSubject(JSON.toJavaObject(data.getJSONObject("subject"),NewNode.class));
                a.setObject(JSON.toJavaObject(data.getJSONObject("object"),NewNode.class));
                a.setRel_name(rel_name);
                a.setType_name(type_name);
                arelation rel = JSON.toJavaObject(data.getJSONObject("relation"),arelation.class);
                a.setRels(rel.getinfo());
                a.setCnt(1);
                a.setAll_cnt(1);
                a.setAll_rels(rel.getinfo());
                a.setObject_name(obj_name);
                a.setSubject_name(sub_name);
                dic.put(key, a);
            }


        }
        return dic;

    }



    private String rel_name,type_name;
    /*info that will get after searching*/
    private int return_code=-1;
    public arelation[] relations;
    private String relation="";
    private String return_message;
    public NewNode subject = new NewNode();
    public NewNode object = new NewNode();

    public String getRelation(){
        for(int i=1;i<relations.length;i++){
            this.relation+=relations[i].getinfo()+"\n";
        }
        return this.relation;
    }




}