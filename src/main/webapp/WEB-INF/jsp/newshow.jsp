<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zxr64
  Date: 2019/2/26
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.zangmz.hit.medicineneo4j.controller.Relation " %>
<%@ page import="javax.annotation.Resource " %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.zangmz.hit.medicineneo4j.utils.Webinfo" %>

<head>
    <title>Test</title>
    <style type="text/css">
        #cy {
            height: 1000px;
            width: 1500px;
            background-color: #f9f9f9;

        }
    </style>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <%--    <script src="../js/code.js"></script>--%>
    <script src="../js/cytoscape.umd.js"></script>
    <link href="../js/cytoscape.js-panzoom.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../js/cytoscape-panzoom.js"></script>
    <script type="text/javascript">
    </script>
</head>
<%
    String temp = (String)pageContext.findAttribute("webinfo") ;
    Webinfo webinfo = com.alibaba.fastjson.JSON.parseObject(temp, Webinfo.class);
%>
<%--<%--%>
<%--    String testd = (String)pageContext.findAttribute("rel");--%>
<%--    Relation atestrel = JSON.parseObject(testd,Relation.class);--%>
<%--    String test1 = (String)pageContext.findAttribute("test11");--%>
<%--    ArrayList<Relation> aa = (ArrayList<Relation>) com.alibaba.fastjson.JSON.parseArray(test1, Relation.class);--%>
<%--    Integer all_returncode = (Integer) pageContext.findAttribute("all_returncode");--%>
<%--    String all_returnmessage = (String)pageContext.findAttribute("all_returnmessage");--%>
<%--%>--%>
<body>
<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#relation" aria-controls="relation" role="tab" data-toggle="tab">Relation</a></li>
        <li role="presentation"><a href="#Entity" aria-controls="Entity" role="tab" data-toggle="tab">Entity</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="relation">
            <div style="padding-top:40px"></div>
            <form id="rel" class="form-horizontal"  method="post">
                <div class="form-group">
                    <label for="head_entity" class="col-sm-2 control-label">Subject</label>
                    <div class="col-sm-10">
                        <input type="text" name="head_entity" class="form-control" list="head_entity_list" id="head_entity" placeholder="<%=webinfo.getHead_entity()%>" value="<%=webinfo.getHead_entity()%>">
                        <datalist id="head_entity_list">
                            <option value="Breast">Breast</option>
                            <option value="Tumor Progression">Tumor Progression</option>
                            <option value="Head">Head</option>
                            <option value="Carcinoma">Carcinoma</option>
                            <option value="Hereditary Malignant Neoplasm">Hereditary Malignant Neoplasm</option>
                        </datalist>
                    </div>
                </div>
                <div class="form-group">
                    <label for="tail_entity" class="col-sm-2 control-label">Object</label>
                    <div class="col-sm-10">
                        <input type="text" name="tail_entity" class="form-control" list="tail_entity_list" id="tail_entity" placeholder="<%=webinfo.getTail_entity()%>" value="<%=webinfo.getTail_entity()%>">
                        <datalist id="tail_entity_list">
                            <option value="Colonic Diseases">Colonic Diseases</option>
                            <option value="Body Regions">Body Regions</option>
                            <option value="Breast">Breast</option>
                            <option value="Carcinoma">Carcinoma</option>
                            <option value="Neoplasms">Neoplasms</option>
                        </datalist>
                    </div>
                </div>
                <div class="col-sm-offset-2 col-sm-10">
                    <label><input id="umls_pcnn" name="umls_pcnn" type="checkbox"  value="true" checked />umls_pcnn&nbsp;&nbsp;</label>
                    <label><input id="umls_rel" name="umls_rel" type="checkbox"  value="true" checked/>umls_rel&nbsp;&nbsp;</label>
                    <label><input id="pmoz_rel" name="pmoz_rel" type="checkbox" value="true" checked/>pmoz_rel&nbsp;&nbsp;</label>
                    <label><input id="subclass_of" name="subclass_of" type="checkbox"  value="true" checked/>subclass_of&nbsp;&nbsp;</label>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" class="btn btn-default" onclick=query()>query</button>
                    </div>
                </div>

            </form>
            <form class="form-horizontal" action="input" method="post">
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">clear</button>
                    </div>
                </div>
            </form>
            <div id="relation_result"></div>
        </div>
        <div role="tabpanel" class="tab-pane" id="Entity">
            <div style="padding-top:40px"></div>
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="input_entity" class="col-sm-2 control-label">entity</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="input_entity" placeholder="head entity">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">query</button>
                    </div>
                </div>
            </form>
            <div id="entity_result"></div>
        </div>
    </div>

</div>
<div class="col-sm-offset-2 col-sm-10" id="cy">
    <script type="text/javascript">
        var tempnode=[]
        var tempedge=[]
        function query(){
            $.ajax({
                    url : "/pmo2/getRelation",
                    type:"post",
                    data:{'head_entity':$('#head_entity').val(),
                        'tail_entity':$('#tail_entity').val(),
                        'umls_pcnn':$('#umls_pcnn').val(),
                        'umls_rel':$('#umls_rel').val(),
                        'subclass_of':$('#subclass_of').val(),
                        'pmoz_rel':$('#pmoz_rel').val()},
                    async : false,
                    dataType : "json",        //返回数据形式为json
                    success : function(result){
                        // for(var i=0;i<5;i++){
                        //     cy.add(
                        //         {
                        //             data:
                        //                 { name: cy.nodes().length+i,
                        //                     id: cy.nodes().length+i
                        //                 }
                        //         }
                        //     );
                        // }
                    },
                    error:function (errorMsg) {

                    }


                }
            )

            var layout = cy.elements().makeLayout({
                name:"random"
            });
            layout.run();
        }
        // $.ajax({
        //     type : "post",
        //     async : false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        //     url : "/pmo2/getRelation",    //请求发送到TestServlet处
        //     data : {},
        //     dataType : "json",        //返回数据形式为json
        //     success : function(result) {
        //         //请求成功时执行该函数内容，result即为服务器返回的json对象
        //         if (result) {
        //             tempnode.push(
        //                 {
        //                     data:
        //                         { name: "a",
        //                             id:"a"
        //                         }
        //                 },
        //                 {
        //                     data:
        //                         { name: "b",
        //                             id:"b"
        //                         }
        //                 }
        //             );
        //             tempnode.push(
        //                 {
        //                     data:
        //                         { name: "d",
        //                             id:"d"
        //                         }
        //                 }
        //             );
        //             tempnode.push(
        //                 {
        //                     data:
        //                         { name: "c",
        //                             id:"c"
        //                         }
        //                 }
        //             );
        //             tempedge.push(
        //                 { // edge ab
        //                     data: {  source: 'a', target: 'b' }
        //                 }
        //             );
        //             tempedge.push(
        //                 { // edge ab
        //                     data: {  source: 'a', target: 'c' }
        //                 }
        //             );
        //             tempedge.push(
        //                 { // edge ab
        //                     data: {  source: 'a', target: 'd' }
        //                 }
        //             );
        //             tempedge.push(
        //                 { // edge ab
        //                     data: {  source: 'b', target: 'c' }
        //                 }
        //             );
        //
        //
        //         }
        //
        //     },
        //     error : function(errorMsg) {
        //         //请求失败时执行该函数
        //         alert("error!");
        //     }
        // })
        query()
        var cy = cytoscape({
            container: document.getElementById('cy'), // container to render in
            elements:
                {
                    nodes:tempnode,
                    edges:tempedge
                }
            ,
            style: [
                {
                    selector: 'node',
                    style: {
                        'background-color': '#666',
                        'label': 'data(id)'
                    }
                },
                {
                    selector: 'edge',
                    style: {
                        'width': 3,
                        'line-color': '#ccc',
                        'target-arrow-color': '#ccc',
                        'target-arrow-shape': 'triangle',
                        'line-style':'dashed',
                        'line-dash-pattern':[8,2]
                    }
                }
            ],
            layout: {
                name: 'concentric',
                rows: 1
            }

        });
        document.addEventListener('DOMContentLoaded', function (){
            var newnode=[]
            cy.on('click','node',function(evt){
                var temppnode = cy.getElementById(this.id());
                // alert(temppnode.data("name"))
                var nodelist = cy.nodes()
                for (var i=0;i<nodelist.length;i++){
                    cy.$(nodelist[i]).lock();
                }
                $.ajax({
                    type : "post",
                    async : false,
                    url : "/pmo2/getRelation",
                    data : {'head_entity':$('#head_entity').val(),
                        'tail_entity':$('#tail_entity').val(),
                        'umls_pcnn':$('#umls_pcnn').val(),
                        'umls_rel':$('#umls_rel').val(),
                        'subclass_of':$('#subclass_of').val(),
                        'pmoz_rel':$('#pmoz_rel').val()},
                    dataType : "json",
                    success : function(result) {
                        if (result) {
                            alert("success!");
                            // cy.add(
                            //     {
                            //         data:
                            //             { name: cy.nodes().length,
                            //                 id: cy.nodes().length
                            //             }
                            //     }
                            // );
                        }
                    },
                    error : function(errorMsg) {
                        alert("error!");
                    }
                })
                var layout = cy.elements().makeLayout({
                    name:"random"
                });
                layout.run();
                var nodelist = cy.nodes()
                for (var i=0;i<nodelist.length;i++){
                    cy.$(nodelist[i]).unlock();
                }
            })

        })
    </script>
</div>

<%--<%if(all_returncode==0){--%>
<%--    if(atestrel.getReturn_code()!=0){%>--%>
<%--<div class="col-sm-offset-2 col-sm-10" >--%>
<%--    <h1><%=atestrel.getReturn_message()%></h1>--%>
<%--</div>--%>
<%--<%}%>--%>

<%--<div class="col-sm-offset-2 col-sm-10" id="cy" style="width: 1500px;height:1000px;">--%>
<%--    <script type="text/javascript">--%>
<%--        document.addEventListener('DOMContentLoaded', function () {--%>
<%--            var demoEdges = [];--%>
<%--            var demoNodes=[];--%>
<%--            <%--%>
<%--            for(int m=0;m<aa.size();m++){--%>
<%--                Relation testrel = aa.get(m);--%>
<%--                if(testrel.getReturn_code()!=0) continue;--%>
<%--            if(testrel.pmoz_rel__relations!=null){--%>
<%--                for(int i=0;i<testrel.pmoz_rel__relations.length;i++){--%>
<%--                    String temprel;--%>
<%--                    if(testrel.pmoz_rel__relations[i].getRel_name()==null){--%>
<%--                        temprel="";--%>
<%--                    }--%>
<%--                    else temprel=testrel.pmoz_rel__relations[i].getRel_name();--%>
<%--            %>--%>
<%--            demoEdges.push({--%>
<%--                data:{--%>
<%--                    id:"<%=testrel.subject.getName()+testrel.object.getName()+testrel.getPmoz_rel()+testrel.pmoz_rel__relations[i].getinfo("pmoz_rel")%>",--%>
<%--                    rel:"<%=temprel%>",--%>
<%--                    sentence: "<%=testrel.pmoz_rel__relations[i].getSentence()%>",--%>
<%--                    pmid: "<%=testrel.pmoz_rel__relations[i].getPmid()%>",--%>
<%--                    type:"pmoz_rel",--%>
<%--                    source:"<%=testrel.subject.getinfo()%>",--%>
<%--                    target:"<%=testrel.object.getinfo()%>",--%>
<%--                    ntype:"edge",--%>
<%--                    detail:"<%="Subject:"+testrel.subject.getName()+"\\n"+testrel.pmoz_rel__relations[i].getinfo("pmoz_rel")+"\\n"+"Object:"+testrel.object.getName()%>"--%>
<%--                }--%>
<%--            });--%>
<%--            <%}--%>
<%--            }%>--%>

<%--            <%--%>
<%--            if(testrel.subclass_of__relations!=null){--%>
<%--                for(int i=0;i<testrel.subclass_of__relations.length;i++){--%>
<%--                    String temprel;--%>
<%--                    if(testrel.subclass_of__relations[i].getRel_name()==null){--%>
<%--                        temprel="";--%>
<%--                    }--%>
<%--                    else temprel=testrel.subclass_of__relations[i].getRel_name();--%>
<%--            %>--%>
<%--            demoEdges.push({--%>
<%--                data:{--%>
<%--                    id:"<%=testrel.subject.getName()+testrel.object.getName()+testrel.getSubclass_of()+testrel.subclass_of__relations[i].getinfo("subclass_of")%>",--%>
<%--                    rel:"<%=temprel%>",--%>
<%--                    sentence: "<%=testrel.subclass_of__relations[i].getSentence()%>",--%>
<%--                    pmid: "<%=testrel.subclass_of__relations[i].getPmid()%>",--%>
<%--                    type:"subclass_of",--%>
<%--                    source:"<%=testrel.subject.getinfo()%>",--%>
<%--                    target:"<%=testrel.object.getinfo()%>",--%>
<%--                    ntype:"edge",--%>
<%--                    detail:"<%="Subject:"+testrel.subject.getName()+"\\n"+testrel.subclass_of__relations[i].getinfo("subclass_of")+"\\n"+"Object:"+testrel.object.getName()%>"--%>

<%--                }--%>
<%--            });--%>
<%--            <%}--%>
<%--            }%>--%>

<%--            <%--%>
<%--            if(testrel.umls_pcnn_relations!=null){--%>
<%--                for(int i=0;i<testrel.umls_pcnn_relations.length;i++){--%>
<%--                    String temprel;--%>
<%--                    if(testrel.umls_pcnn_relations[i].getRel_name()==null){--%>
<%--                        temprel="";--%>
<%--                    }--%>
<%--                    else temprel=testrel.umls_pcnn_relations[i].getRel_name();--%>
<%--            %>--%>
<%--            demoEdges.push({--%>
<%--                data:{--%>
<%--                    id:"<%=testrel.subject.getName()+testrel.object.getName()+testrel.getUmls_pcnn()+testrel.umls_pcnn_relations[i].getinfo("umls_pcnn")%>",--%>
<%--                    rel:"<%=temprel%>",--%>
<%--                    sentence: "<%=testrel.umls_pcnn_relations[i].getSentence()%>",--%>
<%--                    pmid: "<%=testrel.umls_pcnn_relations[i].getPmid()%>",--%>
<%--                    type:"umls_pcnn",--%>
<%--                    source:"<%=testrel.subject.getinfo()%>",--%>
<%--                    target:"<%=testrel.object.getinfo()%>",--%>
<%--                    ntype:"edge",--%>
<%--                    detail:"<%="Subject:"+testrel.subject.getName()+"\\n"+testrel.umls_pcnn_relations[i].getinfo("umls_pcnn")+"\\n"+"Object:"+testrel.object.getName()%>"--%>

<%--                }--%>
<%--            });--%>
<%--            <%}--%>
<%--            }%>--%>

<%--            <%--%>
<%--            if(testrel.umls_rel_relations!=null){--%>
<%--                for(int i=0;i<testrel.umls_rel_relations.length;i++){--%>
<%--                    String temprel;--%>
<%--                    if(testrel.umls_rel_relations[i].getRel_name()==null){--%>
<%--                        temprel="";--%>
<%--                    }--%>
<%--                    else temprel=testrel.umls_rel_relations[i].getRel_name();--%>
<%--            %>--%>
<%--            demoEdges.push({--%>
<%--                data:{--%>
<%--                    id:"<%=testrel.subject.getName()+testrel.object.getName()+testrel.getUmls_rel()+testrel.umls_rel_relations[i].getinfo("umls_rel")%>",--%>
<%--                    rel:"<%=temprel%>",--%>
<%--                    sentence: "<%=testrel.umls_rel_relations[i].getSentence()%>",--%>
<%--                    pmid: "<%=testrel.umls_rel_relations[i].getPmid()%>",--%>
<%--                    type:"umls_rel",--%>
<%--                    source:"<%=testrel.subject.getinfo()%>",--%>
<%--                    target:"<%=testrel.object.getinfo()%>",--%>
<%--                    ntype:"edge",--%>
<%--                    detail:"<%="Subject:"+testrel.subject.getName()+"\\n"+testrel.umls_rel_relations[i].getinfo("umls_rel")+"\\n"+"Object:"+testrel.object.getName()%>"--%>

<%--                }--%>
<%--            });--%>
<%--            <%}--%>
<%--            }%>--%>

<%--            demoNodes.push({--%>
<%--                data: { name: "<%=testrel.object.getName()%>",--%>
<%--                    id:"<%=testrel.object.getinfo()%>",--%>
<%--                    info: "<%=testrel.object.getinfo()%>",--%>
<%--                    ntype:"real"--%>
<%--                }--%>
<%--            })--%>
<%--            demoNodes.push({--%>
<%--                data: { id: "<%=testrel.subject.getinfo()%>",--%>
<%--                    name: "<%=testrel.subject.getName()%>",--%>
<%--                    info:"<%=testrel.subject.getinfo()%>"},--%>
<%--                ntype:"real"--%>
<%--            })--%>
<%--            <%}%>--%>
<%--            var cy = window.cy =cytoscape({--%>

<%--                container: document.getElementById('cy'),--%>
<%--                //zoomingEnabled: false,--%>
<%--                //panningEnabled: false,--%>
<%--                //userZoomingEnabled:false,--%>

<%--                elements: {--%>
<%--                    nodes: demoNodes,--%>
<%--                    edges: demoEdges--%>
<%--                },--%>

<%--                style: [--%>
<%--                    {--%>
<%--                        selector: 'node',--%>
<%--                        style: {--%>
<%--                            'background-color': '#666',--%>
<%--                            'label': 'data(name)',--%>
<%--                            'font-size':40--%>
<%--                        }--%>
<%--                    },--%>
<%--                    {--%>
<%--                        selector: 'node[id="info"]',--%>
<%--                        style: {--%>
<%--                            'height': 10,--%>
<%--                            'width': 10,--%>
<%--                            'font-size':25,--%>
<%--                            'background-color': '#666',--%>
<%--                            'label': 'data(label)',--%>
<%--                            'text-wrap': 'wrap',--%>
<%--                            'text-halign': 'right',--%>
<%--                            'text-valign': 'bottom'--%>
<%--                        }--%>
<%--                    },--%>

<%--                    {--%>
<%--                        selector: 'edge',--%>
<%--                        style: {--%>
<%--                            'width': 3,--%>
<%--                            'curve-style': 'bezier',--%>
<%--                            'line-color': '#ccc',--%>
<%--                            'target-arrow-color': '#ccc',--%>
<%--                            'target-arrow-shape': 'triangle',--%>
<%--                            'label': 'data(rel)'--%>
<%--                        }--%>
<%--                    },--%>
<%--                    {--%>
<%--                        selector: 'edge[type="pmoz_rel"]',--%>
<%--                        style: {--%>
<%--                            'line-color': '#000',--%>
<%--                            'target-arrow-color': '#000',--%>
<%--                        }--%>
<%--                    },--%>
<%--                    {--%>
<%--                        selector: 'edge[type="subclass_of"]',--%>
<%--                        style: {--%>
<%--                            'line-color': '#f00',--%>
<%--                            'target-arrow-color': '#f00',--%>
<%--                        }--%>
<%--                    },--%>
<%--                    {--%>
<%--                        selector: 'edge[type="umls_rel"]',--%>
<%--                        style: {--%>
<%--                            'line-color': '#0f0',--%>
<%--                            'target-arrow-color': '#0f0',--%>
<%--                        }--%>
<%--                    },--%>
<%--                    {--%>
<%--                        selector: 'edge[type="umls_pcnn"]',--%>
<%--                        style: {--%>
<%--                            'line-color': '#00f',--%>
<%--                            'target-arrow-color': '#00f',--%>
<%--                        }--%>
<%--                    }--%>
<%--                ],--%>

<%--                layout: {--%>
<%--                    name: 'grid',--%>
<%--                }--%>

<%--            });--%>

<%--            cy.layout({--%>
<%--                name: 'concentric',--%>
<%--                nodeDimensionsIncludeLabels: true,--%>
<%--                //padding: 5--%>
<%--                //name: 'grid'--%>
<%--            }).run();--%>
<%--            var showinfo_node = function(n,x,y){--%>
<%--                var info_node =cy.getElementById('info')--%>
<%--                var x_pos = info_node.position("x")--%>
<%--                var y_pos = info_node.position("y")--%>
<%--                cy.remove(info_node);--%>
<%--                cy.add({--%>
<%--                    data:{--%>
<%--                        id:"info",--%>
<%--                        label:n.data("info")--%>
<%--                    },--%>
<%--                    position: {--%>
<%--                        x: x,--%>
<%--                        y: y--%>
<%--                    }--%>
<%--                })--%>
<%--                var tempnode = cy.getElementById("info");--%>
<%--                tempnode.layout({--%>
<%--                    name:"preset"--%>
<%--                }).run();--%>

<%--            }--%>
<%--            var showinfo_edge = function(n,x,y){--%>
<%--                var info_node =cy.getElementById('info')--%>
<%--                var x_pos = info_node.position("x")--%>
<%--                var y_pos = info_node.position("y")--%>
<%--                cy.remove(cy.getElementById('info'));--%>
<%--                cy.add({--%>
<%--                    data:{--%>
<%--                        id:"info",--%>
<%--                        label:n.data("detail")--%>
<%--                    },--%>
<%--                    position: {--%>
<%--                        x: x,--%>
<%--                        y: y--%>
<%--                    }--%>
<%--                })--%>
<%--                var tempedge = cy.getElementById('info');--%>
<%--                tempedge.layout({--%>
<%--                    name:"preset"--%>
<%--                }).run();--%>

<%--            }--%>
<%--            cy.on('click','node[ntype="real]',function(evt){--%>
<%--                var temppnode = cy.getElementById(this.id());--%>
<%--                var x = evt.position.x--%>
<%--                var y = evt.position.y--%>
<%--                showinfo_node(temppnode,x,y);--%>
<%--            })--%>


<%--            cy.on('click','edge[ntype="edge"]',function(evt){--%>
<%--                var temppedge = cy.getElementById(this.id());--%>
<%--                var x = evt.position.x--%>
<%--                var y = evt.position.y--%>
<%--                showinfo_edge(temppedge,x,y);--%>
<%--            })--%>
<%--            cy.panzoom({--%>
<%--            });--%>



<%--        });--%>

<%--    </script>--%>
<%--</div>--%>

<%--<%}--%>
<%--else{--%>
<%--%>--%>
<%--<div class="col-sm-offset-2 col-sm-10" >--%>
<%--    <h1><%=all_returnmessage%></h1>--%>
<%--</div>--%>
<%--<%}%>--%>
</body>
