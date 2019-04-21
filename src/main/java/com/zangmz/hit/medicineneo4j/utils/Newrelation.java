package com.zangmz.hit.medicineneo4j.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

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
                ans += "\\nSubclass of:";
                ans+=subclass_of;
            }
            if(tree_number!=null){
                ans+="\\nTree number:";
                ans+=tree_number;
            }
            if(mCID!=null){
                ans+="\\nMTID:";
                ans+=mCID;
            }
            if(mTID!=null) {
                ans += "\\nMCID:";
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

        public void setSentence(String sentence){
            String new_sentence=sentence.replaceAll("\"","\'");
            this.sentence=new_sentence;
        }
        public String getinfo(){
            String ans="";
            if(pmid!=null){
                ans+="Pmid:"+pmid+"\\n";
            }
            if(sentence!=null){
                ans+="Sentence:"+sentence;
            }
            return ans;
        }

    }
    public static class info{
        public NewNode subject = new NewNode();
        public NewNode object = new NewNode();
        private String rel_name,type_name;
        private String rels;
    }

    private Map<String, info> dic;
    public void infoin(JSONArray datas, String type_name){

        for(int i=0; i<datas.size();i++){
            com.alibaba.fastjson.JSONObject data=datas.getJSONObject(i);
            String sub_name = data.getJSONObject("subject").getString("name");
            String obj_name = data.getJSONObject("object").getString("name");
            String rel_name = data.getJSONObject("relation").getString("relation");
            String key = sub_name + "$" + obj_name + "$" + rel_name + "$" + type_name;
            if(dic.containsKey(key)){
                info a = dic.get(key);

            }
            else{

            }
            NewNode object = JSON.toJavaObject(data.getJSONObject("object"),NewNode.class);
            NewNode subject = JSON.toJavaObject(data.getJSONObject("subject"),NewNode.class);
            System.out.println("111");


        }




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