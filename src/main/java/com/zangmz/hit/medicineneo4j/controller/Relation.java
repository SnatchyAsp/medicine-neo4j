package com.zangmz.hit.medicineneo4j.controller;

import com.zangmz.hit.medicineneo4j.bo.res.BaseRes;
import com.zangmz.hit.medicineneo4j.pmo2domain.PMO2Relation;
import com.zangmz.hit.medicineneo4j.services.PMO2Service;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Relation {

    @Resource
    private PMO2Service pmo2Service;

    /*some classes*/
    public class NewNode{
        private String ID;
        private String name;
        private String subclass_of;
        private String tree_number;
        private String mtid;
        private String mcid;
        public void setID(String ID){
            this.ID = ID;
        }
        /*public String getID(){
            return ID;
        }*/
        public void setName(String name){
            this.name = name;
        }
        /*public String getName(){
            return name
        }*/
        public void setSubclass_of(String subclass_of){
            String new_sub=subclass_of.replaceAll("\\,","\\\\n");
            this.subclass_of = new_sub;
        }
        public void setTree_number(String tree_number){
            String new_tree=tree_number.replaceAll("\\$","\\\\n");
            this.tree_number = new_tree;
        }
        public void setMtid(String mtid){
            this.mtid = mtid;
        }
        public void setMcid(String mcid){
            this.mcid = mcid;
        }
        public String getName(){
            return name;
        }
        public String getinfo(){
            String ans = "";
            /*if(!ID.equals("null")) {
                ans+="ID:";
                ans +=ID;
            }*/
            if(!name.equals("null")){
                /*ans += "\\nName:";*/
                ans += "Name:";
                ans += name;
            }
            if(!subclass_of.equals("null")){
                ans += "\\nSubclass of:";
                ans+=subclass_of;
            }
            if(!tree_number.equals("null")){
                ans+="\\nTree number:";
                ans+=tree_number;
            }
            if(!mtid.equals("null")){
                ans+="\\nMTID:";
                ans+=mtid;
            }
            if(!mcid.equals("null")){
                ans+="\\nMCID:";
                ans+=mcid;
            }
            return ans;
        }
    }
    public class arelation{
        private String pmid;
        private String sentence;
        private String rel_name;
        public String getPmid(){
            return pmid;
        }
        public String getRel_name() {
            return rel_name;
        }
        public String getSentence() {
            return sentence;
        }
        public String getinfo(String type){
            String ans="";
            if(!type.equals("null")){
                ans+="Type:"+type+"\\n";
            }
            if(!rel_name.equals("null")){
                ans+="Rel_name:"+rel_name+"\\n";
            }
            if(!pmid.equals("null")){
                ans+="Pmid:"+pmid+"\\n";
            }
            if(!sentence.equals("null")){
                ans+="Sentence:"+sentence;
            }


            //ans+="Type:"+type+"\\nRel_name:" + rel_name + "\\nPmid:"+pmid+"\\nSentence:"+sentence;
            return ans;
        }
        public void setPmid(String pmid){
            this.pmid=pmid;
        }
        public void setSentence(String sentence){
            String new_sen=sentence.replaceAll(",","\\\\n");
            this.sentence=new_sen;
        }
        public void setRel_name(String rel_name){
            this.rel_name=rel_name;
        }

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

    private static String loadJSON(String url) {
        //处理字符串
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "utf-8"));// 防止乱码
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            System.out.println("!!!!!! MalformedURLException");
        } catch (IOException e) {
            System.out.println("!!!!!! IOEXCEPTION");
        }
        System.out.println(json.toString());
        return json.toString();
    }

    /*function used for test(get data))*/
    public JSONObject search(String head_entity, String tail_entity, String rel){
        JSONObject test_data = new JSONObject("{\n" +
                "\t\"returnCode\": \"0\",\n" +
                "\t\"data\": [{\n" +
                "\t\t\"subject\": {\n" +
                "\t\t\t\"id\": 531787,\n" +
                "\t\t\t\"name\": \"Tumor Progression\",\n" +
                "\t\t\t\"subclass_of\": \"MT00001613\",\n" +
                "\t\t\t\"tree_number\": \"null\",\n" +
                "\t\t\t\"mtid\": \"null\",\n" +
                "\t\t\t\"mcid\": \"MC00066784\"\n" +
                "\t\t},\n" +
                "\t\t\"object\": {\n" +
                "\t\t\t\"id\": 3298,\n" +
                "\t\t\t\"name\": \"Colonic Diseases\",\n" +
                "\t\t\t\"subclass_of\": \"MT00003297\",\n" +
                "\t\t\t\"tree_number\": \"T6.6.4.5.2$\",\n" +
                "\t\t\t\"mtid\": \"MT00003299\",\n" +
                "\t\t\t\"mcid\": \"MC00003102\"\n" +
                "\t\t},\n" +
                "\t\t\"relation\": {\n" +
                "\t\t\t\"id\": \"null\",\n" +
                "\t\t\t\"relation\": \"PART_OF\",\n" +
                "\t\t\t\"sentence\": \"Identification of these alterations provides valuable insight into the process of experimental liver metastasis and is a first step towards mapping genes linked to the terminal phases of human colon cancer progression.\",\n" +
                "\t\t\t\"pmid\": \"8674279\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"subject\": {\n" +
                "\t\t\t\"id\": 531787,\n" +
                "\t\t\t\"name\": \"Tumor Progression\",\n" +
                "\t\t\t\"subclass_of\": \"MT00001613\",\n" +
                "\t\t\t\"tree_number\": \"null\",\n" +
                "\t\t\t\"mtid\": \"null\",\n" +
                "\t\t\t\"mcid\": \"MC00066784\"\n" +
                "\t\t},\n" +
                "\t\t\"object\": {\n" +
                "\t\t\t\"id\": 3298,\n" +
                "\t\t\t\"name\": \"Colonic Diseases\",\n" +
                "\t\t\t\"subclass_of\": \"MT00003297\",\n" +
                "\t\t\t\"tree_number\": \"T6.6.4.5.2$\",\n" +
                "\t\t\t\"mtid\": \"MT00003299\",\n" +
                "\t\t\t\"mcid\": \"MC00003102\"\n" +
                "\t\t},\n" +
                "\t\t\"relation\": {\n" +
                "\t\t\t\"id\": \"null\",\n" +
                "\t\t\t\"relation\": \"PART_OF\",\n" +
                "\t\t\t\"sentence\": \"Extension of this analysis to the use of ICC on 104 colonic lesions, representative of different stages of colonic neoplastic progression, showed an absence of detectable p53 nuclear staining in preneoplastic polyp lesions (20 cases) with staining of 52% (25/48) of primary colon carcinomas and 81% (29/36) of hepatic metastases, suggestive of an increased incidence of p53 mutations in late stage lesions of colonic cancer.\",\n" +
                "\t\t\t\"pmid\": \"7651727\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"subject\": {\n" +
                "\t\t\t\"id\": 531787,\n" +
                "\t\t\t\"name\": \"Tumor Progression\",\n" +
                "\t\t\t\"subclass_of\": \"MT00001613\",\n" +
                "\t\t\t\"tree_number\": \"null\",\n" +
                "\t\t\t\"mtid\": \"null\",\n" +
                "\t\t\t\"mcid\": \"MC00066784\"\n" +
                "\t\t},\n" +
                "\t\t\"object\": {\n" +
                "\t\t\t\"id\": 3298,\n" +
                "\t\t\t\"name\": \"Colonic Diseases\",\n" +
                "\t\t\t\"subclass_of\": \"MT00003297\",\n" +
                "\t\t\t\"tree_number\": \"T6.6.4.5.2$\",\n" +
                "\t\t\t\"mtid\": \"MT00003299\",\n" +
                "\t\t\t\"mcid\": \"MC00003102\"\n" +
                "\t\t},\n" +
                "\t\t\"relation\": {\n" +
                "\t\t\t\"id\": \"null\",\n" +
                "\t\t\t\"relation\": \"PART_OF\",\n" +
                "\t\t\t\"sentence\": \"Here we have analysed the pattern of Cdx1 in human colon cancer progression.\",\n" +
                "\t\t\t\"pmid\": \"12970739\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"subject\": {\n" +
                "\t\t\t\"id\": 531787,\n" +
                "\t\t\t\"name\": \"Tumor Progression\",\n" +
                "\t\t\t\"subclass_of\": \"MT00001613\",\n" +
                "\t\t\t\"tree_number\": \"null\",\n" +
                "\t\t\t\"mtid\": \"null\",\n" +
                "\t\t\t\"mcid\": \"MC00066784\"\n" +
                "\t\t},\n" +
                "\t\t\"object\": {\n" +
                "\t\t\t\"id\": 3298,\n" +
                "\t\t\t\"name\": \"Colonic Diseases\",\n" +
                "\t\t\t\"subclass_of\": \"MT00003297\",\n" +
                "\t\t\t\"tree_number\": \"T6.6.4.5.2$\",\n" +
                "\t\t\t\"mtid\": \"MT00003299\",\n" +
                "\t\t\t\"mcid\": \"MC00003102\"\n" +
                "\t\t},\n" +
                "\t\t\"relation\": {\n" +
                "\t\t\t\"id\": \"null\",\n" +
                "\t\t\t\"relation\": \"PART_OF\",\n" +
                "\t\t\t\"sentence\": \"Cdx1 homeobox gene during human colon cancer progression.\",\n" +
                "\t\t\t\"pmid\": \"12970739\"\n" +
                "\t\t}\n" +
                "\t}],\n" +
                "\t\"returnMessage\": \"search success\"\n" +
                "}\n");
        /*JSONObject test_data = new JSONObject("{\"returnCode\":\"1\",\"data\":null,\"returnMessage\":\"no subject\"}");*/
        //
        //String url = "http://localhost:8080/pmo2/getRelation?subject="+head_entity+"&object="+tail_entity+"&relationType="+rel;
        //String url = "http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo";
        //String url="https://api.androidhive.info/volley/person_object.json";
        //String param = "";
        //String a = loadJSON(url);
        //JSONObject jo = new JSONObject(a);
        /*try {
            //1.连接部分
            URL url = new URL("https://a.abc.com");
            // http协议传输
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            //2.传入参数部分
            // 得到请求的输出流对象
            out = new OutputStreamWriter(httpUrlConn.getOutputStream(),"UTF-8");
            // 把数据写入请求的Body
            out.write("appId=" + appId + "&name=" + name); //参数形式跟在地址栏的一样
            out.flush();
            out.close();

            //3.获取数据
            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();*/

        BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(head_entity, tail_entity, rel);
        JSONObject test = new JSONObject(result);
        return test;
    }




    /*functions*/
    public void InitNode(NewNode node, JSONObject node_info){
        node.setID(String.valueOf(node_info.getInt("id")));
        node.setName(node_info.getString("name"));
        node.setMtid(node_info.getString("MTID"));    //This may cause failure, because the data sample in the doc is mtid, but the function returns MTID.
        node.setMcid(node_info.getString("MCID"));
        node.setSubclass_of(node_info.getString("subclass_of"));
        node.setTree_number(node_info.getString("tree_number"));
    }



    public void setinfo(JSONObject data, String rel)
    {

        /*if(return_code!=0&&return_code!=-1)
        {
            return_code = data.getInt("returnCode");
            return_message = data.getString("returnMessage");
            return;
        }*/
        int temp_return_code=data.getInt("returnCode");
        String temp_return_message=data.getString("returnMessage");
        if(temp_return_code!=0&&return_code!=0){
            return_message=temp_return_message;
            return;
        }
        if(temp_return_code!=0&&return_code==0){
            return;
        }
        if(temp_return_code==0&&return_code!=0){
            return_code=temp_return_code;
            return_message=temp_return_message;
        }
        JSONArray data_array = data.getJSONArray("data");
        JSONObject temp = data_array.getJSONObject(0);
        InitNode(subject,temp.getJSONObject("subject"));
        InitNode(object, temp.getJSONObject("object"));


        arelation[] temp_relations = new arelation[data_array.length()];
        for(int i=0;i<data_array.length();i++){
            temp = data_array.getJSONObject(i);
            JSONObject temp_rel = temp.getJSONObject("relation");
            arelation temp_relation = new arelation();
            temp_relation.setPmid(temp_rel.getString("pmid"));
            temp_relation.setRel_name(temp_rel.getString("relation"));
            temp_relation.setSentence(temp_rel.getString("sentence"));
            temp_relations[i] = temp_relation;
        }
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



    public void InitInfo(){

        if(umls_pcnn!= null){
            JSONObject data_info = search(head_entity, tail_entity, umls_pcnn);
            setinfo(data_info, umls_pcnn);
        }
        if(umls_rel!=null){
            JSONObject data_info = search(head_entity, tail_entity, umls_rel);
            setinfo(data_info, umls_rel);
        }
        if(pmoz_rel!=null){
            JSONObject data_info = search(head_entity, tail_entity, pmoz_rel);
            setinfo(data_info, pmoz_rel);
        }
        if(subclass_of!=null){
            JSONObject data_info = search(head_entity, tail_entity, subclass_of);
            setinfo(data_info, subclass_of);
        }


        /*
        JSONObject test_data = new JSONObject("{\n" +
                "\t\"returnCode\": \"0\",\n" +
                "\t\"data\": [{\n" +
                "\t\t\"subject\": {\n" +
                "\t\t\t\"id\": 531787,\n" +
                "\t\t\t\"name\": \"Tumor Progression\",\n" +
                "\t\t\t\"subclass_of\": \"MT00001613\",\n" +
                "\t\t\t\"tree_number\": \"null\",\n" +
                "\t\t\t\"mtid\": \"null\",\n" +
                "\t\t\t\"mcid\": \"MC00066784\"\n" +
                "\t\t},\n" +
                "\t\t\"object\": {\n" +
                "\t\t\t\"id\": 3298,\n" +
                "\t\t\t\"name\": \"Colonic Diseases\",\n" +
                "\t\t\t\"subclass_of\": \"MT00003297\",\n" +
                "\t\t\t\"tree_number\": \"T6.6.4.5.2$\",\n" +
                "\t\t\t\"mtid\": \"MT00003299\",\n" +
                "\t\t\t\"mcid\": \"MC00003102\"\n" +
                "\t\t},\n" +
                "\t\t\"relation\": {\n" +
                "\t\t\t\"id\": \"null\",\n" +
                "\t\t\t\"relation\": \"PART_OF\",\n" +
                "\t\t\t\"sentence\": \"Identification of these alterations provides valuable insight into the process of experimental liver metastasis and is a first step towards mapping genes linked to the terminal phases of human colon cancer progression.\",\n" +
                "\t\t\t\"pmid\": \"8674279\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"subject\": {\n" +
                "\t\t\t\"id\": 531787,\n" +
                "\t\t\t\"name\": \"Tumor Progression\",\n" +
                "\t\t\t\"subclass_of\": \"MT00001613\",\n" +
                "\t\t\t\"tree_number\": \"null\",\n" +
                "\t\t\t\"mtid\": \"null\",\n" +
                "\t\t\t\"mcid\": \"MC00066784\"\n" +
                "\t\t},\n" +
                "\t\t\"object\": {\n" +
                "\t\t\t\"id\": 3298,\n" +
                "\t\t\t\"name\": \"Colonic Diseases\",\n" +
                "\t\t\t\"subclass_of\": \"MT00003297\",\n" +
                "\t\t\t\"tree_number\": \"T6.6.4.5.2$\",\n" +
                "\t\t\t\"mtid\": \"MT00003299\",\n" +
                "\t\t\t\"mcid\": \"MC00003102\"\n" +
                "\t\t},\n" +
                "\t\t\"relation\": {\n" +
                "\t\t\t\"id\": \"null\",\n" +
                "\t\t\t\"relation\": \"PART_OF\",\n" +
                "\t\t\t\"sentence\": \"Extension of this analysis to the use of ICC on 104 colonic lesions, representative of different stages of colonic neoplastic progression, showed an absence of detectable p53 nuclear staining in preneoplastic polyp lesions (20 cases) with staining of 52% (25/48) of primary colon carcinomas and 81% (29/36) of hepatic metastases, suggestive of an increased incidence of p53 mutations in late stage lesions of colonic cancer.\",\n" +
                "\t\t\t\"pmid\": \"7651727\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"subject\": {\n" +
                "\t\t\t\"id\": 531787,\n" +
                "\t\t\t\"name\": \"Tumor Progression\",\n" +
                "\t\t\t\"subclass_of\": \"MT00001613\",\n" +
                "\t\t\t\"tree_number\": \"null\",\n" +
                "\t\t\t\"mtid\": \"null\",\n" +
                "\t\t\t\"mcid\": \"MC00066784\"\n" +
                "\t\t},\n" +
                "\t\t\"object\": {\n" +
                "\t\t\t\"id\": 3298,\n" +
                "\t\t\t\"name\": \"Colonic Diseases\",\n" +
                "\t\t\t\"subclass_of\": \"MT00003297\",\n" +
                "\t\t\t\"tree_number\": \"T6.6.4.5.2$\",\n" +
                "\t\t\t\"mtid\": \"MT00003299\",\n" +
                "\t\t\t\"mcid\": \"MC00003102\"\n" +
                "\t\t},\n" +
                "\t\t\"relation\": {\n" +
                "\t\t\t\"id\": \"null\",\n" +
                "\t\t\t\"relation\": \"PART_OF\",\n" +
                "\t\t\t\"sentence\": \"Here we have analysed the pattern of Cdx1 in human colon cancer progression.\",\n" +
                "\t\t\t\"pmid\": \"12970739\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"subject\": {\n" +
                "\t\t\t\"id\": 531787,\n" +
                "\t\t\t\"name\": \"Tumor Progression\",\n" +
                "\t\t\t\"subclass_of\": \"MT00001613\",\n" +
                "\t\t\t\"tree_number\": \"null\",\n" +
                "\t\t\t\"mtid\": \"null\",\n" +
                "\t\t\t\"mcid\": \"MC00066784\"\n" +
                "\t\t},\n" +
                "\t\t\"object\": {\n" +
                "\t\t\t\"id\": 3298,\n" +
                "\t\t\t\"name\": \"Colonic Diseases\",\n" +
                "\t\t\t\"subclass_of\": \"MT00003297\",\n" +
                "\t\t\t\"tree_number\": \"T6.6.4.5.2$\",\n" +
                "\t\t\t\"mtid\": \"MT00003299\",\n" +
                "\t\t\t\"mcid\": \"MC00003102\"\n" +
                "\t\t},\n" +
                "\t\t\"relation\": {\n" +
                "\t\t\t\"id\": \"null\",\n" +
                "\t\t\t\"relation\": \"PART_OF\",\n" +
                "\t\t\t\"sentence\": \"Cdx1 homeobox gene during human colon cancer progression.\",\n" +
                "\t\t\t\"pmid\": \"12970739\"\n" +
                "\t\t}\n" +
                "\t}],\n" +
                "\t\"returnMessage\": \"search success\"\n" +
                "}\n");
        JSONObject test_data = new JSONObject("{\"returnCode\":\"1\",\"data\":null,\"returnMessage\":\"no subject\"}");
        return_code = test_data.getInt("returnCode");
        return_message = test_data.getString("returnMessage");
        if(return_code != 0) return;
        JSONArray data = test_data.getJSONArray("data");
        JSONObject temp = data.getJSONObject(0);
        InitNode(subject,temp.getJSONObject("subject"));
        InitNode(object, temp.getJSONObject("object"));
        for(int i=0;i<data.length();i++){
            temp = data.getJSONObject(i);
            JSONObject temp_rel = temp.getJSONObject("relation");
            if(relation.indexOf(temp_rel.getString("relation")) == -1){
                relation += temp_rel.getString("relation");
                relation += "\\n";
            }

        }
        */




    }
    public int getReturn_code(){
        return return_code;
    }
    public String getReturn_message(){
        return return_message;
    }

    public String gethead_entity() {
        return head_entity;
    }
    public void sethead_entity(String head_entity) {
        this.head_entity = head_entity;
    }

    public String gettail_entity() {
        return tail_entity;
    }
    public void settail_entity(String tail_entity) {
        this.tail_entity = tail_entity;
    }

    public String getumls_pcnn() {
        return umls_pcnn;
    }
    public void setumls_pcnn(String umls_pcnn) {
        this.umls_pcnn = umls_pcnn;
    }

    public String getumls_rel() {
        return umls_rel;
    }
    public void setumls_rel(String umls_rel) {
        this.umls_rel = umls_rel;
    }

    public String getpmoz_rel() {
        return pmoz_rel;
    }
    public void setpmoz_rel(String pmoz_rel) {
        this.pmoz_rel = pmoz_rel;
    }

    public String getsubclass_of() {
        return subclass_of;
    }
    public void setsubclass_of(String subclass_of) {
        this.subclass_of = subclass_of;
    }

    public String getRelation()
    {
        return relation;
    }

}