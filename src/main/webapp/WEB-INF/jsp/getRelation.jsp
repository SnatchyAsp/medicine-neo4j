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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/cytoscape/3.4.2/cytoscape.min.js"></script>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<%
    String testd = (String)pageContext.findAttribute("rel");
    Relation atestrel = JSON.parseObject(testd,Relation.class);
    String test1 = (String)pageContext.findAttribute("test11");
    ArrayList<Relation> aa = (ArrayList<Relation>) com.alibaba.fastjson.JSON.parseArray(test1, Relation.class);
    Integer all_returncode = (Integer) pageContext.findAttribute("all_returncode");
    String all_returnmessage = (String)pageContext.findAttribute("all_returnmessage");
%>
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
            <form class="form-horizontal" action="getRelation" method="post">
                <div class="form-group">
                    <label for="head_entity" class="col-sm-2 control-label">Subject</label>
                    <div class="col-sm-10">
                        <input type="text" name="head_entity" class="form-control" id="head_entity" placeholder="<%=atestrel.getHead_entity()%>" value="<%=atestrel.getHead_entity()%>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="tail_entity" class="col-sm-2 control-label">Object</label>
                    <div class="col-sm-10">
                        <input type="text" name="tail_entity" class="form-control" id="tail_entity" placeholder="<%=atestrel.getTail_entity()%>" value="<%=atestrel.getTail_entity()%>">
                    </div>
                </div>
                <div class="col-sm-offset-2 col-sm-10">
                    <label><input name="umls_pcnn" type="checkbox" value="umls_pcnn" />umls_pcnn&nbsp;&nbsp;</label>
                    <label><input name="umls_rel" type="checkbox" value="umls_rel" />umls_rel&nbsp;&nbsp;</label>
                    <label><input name="pmoz_rel" type="checkbox" value="pmoz_rel" />pmoz_rel&nbsp;&nbsp;</label>
                    <label><input name="subclass_of" type="checkbox" value="subclass_of" />subclass_of&nbsp;&nbsp;</label>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">query</button>
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

<%if(all_returncode==0){
    if(atestrel.getReturn_code()!=0){%>
<div class="col-sm-offset-2 col-sm-10" >
    <h1><%=atestrel.getReturn_message()%></h1>
</div>
    <%}%>

<div class="col-sm-offset-2 col-sm-10" id="cy" style="width: 1500px;height:1000px;">
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', function () {
            var demoEdges = [];
            var demoNodes=[];
            <%
            for(int m=0;m<aa.size();m++){
                Relation testrel = aa.get(m);
                if(testrel.getReturn_code()!=0) continue;
            if(testrel.pmoz_rel__relations!=null){
                for(int i=0;i<testrel.pmoz_rel__relations.length;i++){
                    String temprel;
                    if(testrel.pmoz_rel__relations[i].getRel_name()==null){
                        temprel="";
                    }
                    else temprel=testrel.pmoz_rel__relations[i].getRel_name();
            %>
            demoEdges.push({
                data:{
                    id:"<%=testrel.subject.getName()+testrel.object.getName()+testrel.getPmoz_rel()+testrel.pmoz_rel__relations[i].getinfo("pmoz_rel")%>",
                    rel:"<%=temprel%>",
                    sentence: "<%=testrel.pmoz_rel__relations[i].getSentence()%>",
                    pmid: "<%=testrel.pmoz_rel__relations[i].getPmid()%>",
                    type:"pmoz_rel",
                    source:'<%=testrel.object.getinfo()%>',
                    target:'<%=testrel.subject.getinfo()%>',
                    ntype:'edge',
                    detail:"<%=testrel.pmoz_rel__relations[i].getinfo("pmoz_rel")%>"
                }
            });
            <%}
            }%>

            <%
            if(testrel.subclass_of__relations!=null){
                for(int i=0;i<testrel.subclass_of__relations.length;i++){
                    String temprel;
                    if(testrel.subclass_of__relations[i].getRel_name()==null){
                        temprel="";
                    }
                    else temprel=testrel.subclass_of__relations[i].getRel_name();
            %>
            demoEdges.push({
                data:{
                    id:"<%=testrel.subject.getName()+testrel.object.getName()+testrel.getSubclass_of()+testrel.subclass_of__relations[i].getinfo("subclass_of")%>",
                    rel:"<%=temprel%>",
                    sentence: "<%=testrel.subclass_of__relations[i].getSentence()%>",
                    pmid: "<%=testrel.subclass_of__relations[i].getPmid()%>",
                    type:"subclass_of",
                    source:'<%=testrel.object.getinfo()%>',
                    target:'<%=testrel.subject.getinfo()%>',
                    ntype:'edge',
                    detail:"<%=testrel.subclass_of__relations[i].getinfo("subclass_of")%>"

                }
            });
            <%}
            }%>

            <%
            if(testrel.umls_pcnn_relations!=null){
                for(int i=0;i<testrel.umls_pcnn_relations.length;i++){
                    String temprel;
                    if(testrel.umls_pcnn_relations[i].getRel_name()==null){
                        temprel="";
                    }
                    else temprel=testrel.umls_pcnn_relations[i].getRel_name();
            %>
            demoEdges.push({
                data:{
                    id:"<%=testrel.subject.getName()+testrel.object.getName()+testrel.getUmls_pcnn()+testrel.umls_pcnn_relations[i].getinfo("umls_pcnn")%>",
                    rel:"<%=temprel%>",
                    sentence: "<%=testrel.umls_pcnn_relations[i].getSentence()%>",
                    pmid: "<%=testrel.umls_pcnn_relations[i].getPmid()%>",
                    type:"umls_pcnn",
                    source:'<%=testrel.object.getinfo()%>',
                    target:'<%=testrel.subject.getinfo()%>',
                    ntype:'edge',
                    detail:"<%=testrel.umls_pcnn_relations[i].getinfo("umls_pcnn")%>"

                }
            });
            <%}
            }%>

            <%
            if(testrel.umls_rel_relations!=null){
                for(int i=0;i<testrel.umls_rel_relations.length;i++){
                    String temprel;
                    if(testrel.umls_rel_relations[i].getRel_name()==null){
                        temprel="";
                    }
                    else temprel=testrel.umls_rel_relations[i].getRel_name();
            %>
            demoEdges.push({
                data:{
                    id:"<%=testrel.subject.getName()+testrel.object.getName()+testrel.getUmls_rel()+testrel.umls_rel_relations[i].getinfo("umls_rel")%>",
                    rel:"<%=temprel%>",
                    sentence: "<%=testrel.umls_rel_relations[i].getSentence()%>",
                    pmid: "<%=testrel.umls_rel_relations[i].getPmid()%>",
                    type:"umls_rel",
                    source:'<%=testrel.object.getinfo()%>',
                    target:'<%=testrel.subject.getinfo()%>',
                    ntype:'edge',
                    detail:"<%=testrel.umls_rel_relations[i].getinfo("umls_rel")%>"

                }
            });
            <%}
            }%>

            demoNodes.push({
                data: { name: '<%=testrel.object.getName()%>',
                    id:'<%=testrel.object.getinfo()%>',
                    info: '<%=testrel.object.getinfo()%>',
                    ntype:'real'
                }
            })
            demoNodes.push({
                data: { id: '<%=testrel.subject.getinfo()%>',
                    name: '<%=testrel.subject.getName()%>',
                    info:'<%=testrel.subject.getinfo()%>'},
                ntype:'real'
            })
            <%}%>
            var cy = window.cy =cytoscape({

                container: document.getElementById('cy'),
                zoomingEnabled: false,
                //panningEnabled: false,

                elements: {
                    nodes: demoNodes,
                    edges: demoEdges
                },

                style: [
                    {
                        selector: 'node',
                        style: {
                            'background-color': '#666',
                            'label': 'data(name)'
                        }
                    },
                    {
                        selector: 'node[id="info"]',
                        style: {
                            'height': 10,
                            'width': 10,
                            'font-size':25,
                            'background-color': '#666',
                            'label': 'data(label)',
                            'text-wrap': 'wrap',
                            'text-halign': 'right',
                            'text-valign': 'bottom'
                        }
                    },

                    {
                        selector: 'edge',
                        style: {
                            'width': 3,
                            'curve-style': 'bezier',
                            'line-color': '#ccc',
                            'target-arrow-color': '#ccc',
                            'target-arrow-shape': 'triangle',
                            'label': 'data(rel)'
                        }
                    },
                    {
                        selector: 'edge[type="pmoz_rel"]',
                        style: {
                            'line-color': '#000',
                            'target-arrow-color': '#000',
                        }
                    },
                    {
                        selector: 'edge[type="subclass_of"]',
                        style: {
                            'line-color': '#f00',
                            'target-arrow-color': '#f00',
                        }
                    },
                    {
                        selector: 'edge[type="umls_rel"]',
                        style: {
                            'line-color': '#0f0',
                            'target-arrow-color': '#0f0',
                        }
                    },
                    {
                        selector: 'edge[type="umls_pcnn"]',
                        style: {
                            'line-color': '#00f',
                            'target-arrow-color': '#00f',
                        }
                    }
                ],

                layout: {
                    name: 'grid',
                }

            });

            cy.layout({
                name: 'random'
                //name: 'grid'
            }).run();
            var showinfo_node = function(n){
                cy.remove(cy.getElementById('info'));
                cy.add({
                    data:{
                        id:'info',
                        label:n.data('info')
                    },
                    position: {
                        x: 10,
                        y: 10
                    }
                })
                var tempnode = cy.getElementById('info');
                tempnode.layout({
                    name:'preset'
                }).run();

            }
            var showinfo_edge = function(n){
                cy.remove(cy.getElementById('info'));
                cy.add({
                    data:{
                        id:'info',
                        label:n.data('detail')
                    },
                    position: {
                        x: 10,
                        y: 10
                    }
                })
                var tempedge = cy.getElementById('info');
                tempedge.layout({
                    name:'preset'
                }).run();

            }
            cy.on('click','node[ntype="real]',function(evt){
                var temppnode = cy.getElementById(this.id());
                showinfo_node(temppnode);
            })
            cy.on('click','edge[ntype="edge"]',function(evt){
                var temppedge = cy.getElementById(this.id());
                showinfo_edge(temppedge);
            })



        });

    </script>
</div>

<%}
else{
%>
<div class="col-sm-offset-2 col-sm-10" >
    <h1><%=all_returnmessage%></h1>
</div>
<%}%>
</body>
