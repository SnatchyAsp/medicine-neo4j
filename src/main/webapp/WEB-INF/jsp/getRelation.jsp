<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zxr64
  Date: 2019/2/26
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.zangmz.hit.medicineneo4j.controller.Relation " %>
<%@ page import="javax.annotation.Resource " %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="com.alibaba.fastjson.JSON" %>

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
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<c:set value="${head_entity}" var="head_entity" />
<c:set value="${tail_entity}" var="tail_entity" />
<c:set value="${umls_pcnn}" var="umls_pcnn" />
<c:set value="${umls_rel}" var="umls_rel" />
<c:set value="${pmoz_rel}" var="pmoz_rel" />
<c:set value="${subclass_of}" var="subclass_of" />
<c:set value="${rel}" var="rel" />


<%

    /*Relation testrel = new Relation();
    testrel.sethead_entity((String)pageContext.findAttribute("head_entity"));
    testrel.settail_entity((String)pageContext.findAttribute("tail_entity"));
    testrel.setpmoz_rel((String)pageContext.findAttribute("pmoz_rel"));
    testrel.setsubclass_of((String)pageContext.findAttribute("subclass_of"));
    testrel.setumls_pcnn((String)pageContext.findAttribute("umls_pcnn"));
    testrel.setumls_rel((String)pageContext.findAttribute("umls_rel"));*/
    String testd = (String)pageContext.findAttribute("rel");
    Relation testrel = JSON.parseObject(testd,Relation.class);

    System.out.println(testd);

    /*if(testrel.getSubclass_of()!=null){%>
<c:set value="${subclass_of_data}" var="subclass_of_data" scope="session"/>
    <%
            JSONObject data_info = new JSONObject((String)pageContext.findAttribute("subclass_of_data"));
            testrel.setinfo(data_info, "subclass_of");
    }

    if(testrel.getPmoz_rel()!=null){%>
<c:set value="${pmoz_rel_data}" var="pmoz_rel_data" scope="session"/>
<%
        JSONObject data_info = new JSONObject((String)pageContext.findAttribute("pmoz_rel_data"));
        testrel.setinfo(data_info, "pmoz_rel");
    }
    if(testrel.getUmls_pcnn()!=null){%>
<c:set value="${umls_pcnn_data}" var="umls_pcnn_data" scope="session"/>
<%
        JSONObject data_info = new JSONObject((String)pageContext.findAttribute("umls_pcnn_data"));
        testrel.setinfo(data_info, "umls_pcnn");
    }
    if(testrel.getUmls_rel()!=null){%>
<c:set value="${umls_rel_data}" var="umls_rel_data" scope="session"/>
<%
        JSONObject data_info = new JSONObject((String)pageContext.findAttribute("umls_rel_data"));
        testrel.setinfo(data_info, "umls_rel");
    }*/
    //testrel.InitInfo();

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
                        <input type="text" name="head_entity" class="form-control" id="head_entity" placeholder="<%=testrel.getHead_entity()%>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="tail_entity" class="col-sm-2 control-label">Object</label>
                    <div class="col-sm-10">
                        <input type="text" name="tail_entity" class="form-control" id="tail_entity" placeholder="<%=testrel.getTail_entity()%>">
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

<%if(testrel.getReturn_code()==0){%>
<div class="col-sm-offset-2 col-sm-10" id="cy" style="width: 1500px;height:1000px;">
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', function () {
            var cy = window.cy =cytoscape({

                container: document.getElementById('cy'),
                zoomingEnabled: false,
                //panningEnabled: false,
                elements: [
                    {
                        data: { id: '<%=testrel.object.getName()%>',
                            info: '<%=testrel.object.getinfo()%>',
                            ntype:'real'
                        }
                    },
                    {
                        data: { id: '<%=testrel.subject.getName()%>',
                        info:'<%=testrel.subject.getinfo()%>'},
                        ntype:'real'
                    }

                ],

                style: [
                    {
                        selector: 'node',
                        style: {
                            'background-color': '#666',
                            'label': 'data(id)'
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
            <%
            if(testrel.pmoz_rel__relations!=null){
                for(int i=0;i<testrel.pmoz_rel__relations.length;i++){
            %>
            cy.add({
                data:{
                    rel:"<%=testrel.pmoz_rel__relations[i].getRel_name()%>",
                    sentence: "<%=testrel.pmoz_rel__relations[i].getSentence()%>",
                    pmid: "<%=testrel.pmoz_rel__relations[i].getPmid()%>",
                    type:"pmoz_rel",
                    source:'<%=testrel.object.getName()%>',
                    target:'<%=testrel.subject.getName()%>',
                    ntype:'edge',
                    detail:'<%=testrel.pmoz_rel__relations[i].getinfo("pmoz_rel")%>'

                }
            });
            <%}
            }%>

            <%
            if(testrel.subclass_of__relations!=null){
                for(int i=0;i<testrel.subclass_of__relations.length;i++){
            %>
            cy.add({
                data:{
                    rel:"<%=testrel.subclass_of__relations[i].getRel_name()%>",
                    sentence: "<%=testrel.subclass_of__relations[i].getSentence()%>",
                    pmid: "<%=testrel.subclass_of__relations[i].getPmid()%>",
                    type:"subclass_of",
                    source:'<%=testrel.object.getName()%>',
                    target:'<%=testrel.subject.getName()%>',
                    ntype:'edge',
                    detail:'<%=testrel.subclass_of__relations[i].getinfo("subclass_of")%>'

                }
            });
            <%}
            }%>

            <%
            if(testrel.umls_pcnn_relations!=null){
                for(int i=0;i<testrel.umls_pcnn_relations.length;i++){
            %>
            cy.add({
                data:{
                    rel:"<%=testrel.umls_pcnn_relations[i].getRel_name()%>",
                    sentence: "<%=testrel.umls_pcnn_relations[i].getSentence()%>",
                    pmid: "<%=testrel.umls_pcnn_relations[i].getPmid()%>",
                    type:"umls_pcnn",
                    source:'<%=testrel.object.getName()%>',
                    target:'<%=testrel.subject.getName()%>',
                    ntype:'edge',
                    detail:'<%=testrel.umls_pcnn_relations[i].getinfo("umls_pcnn")%>'

                }
            });
            <%}
            }%>

            <%
            if(testrel.umls_rel_relations!=null){
                for(int i=0;i<testrel.umls_rel_relations.length;i++){
            %>
            cy.add({
                data:{
                    rel:"<%=testrel.umls_rel_relations[i].getRel_name()%>",
                    sentence: "<%=testrel.umls_rel_relations[i].getSentence()%>",
                    pmid: "<%=testrel.umls_rel_relations[i].getPmid()%>",
                    type:"umls_rel",
                    source:'<%=testrel.object.getName()%>',
                    target:'<%=testrel.subject.getName()%>',
                    ntype:'edge',
                    detail:'<%=testrel.umls_rel_relations[i].getinfo("umls_rel")%>'

                }
            });
            <%}
            }%>
            cy.layout({
                name: 'random'
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
            cy.on('click','edge',function(evt){
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
    <h1><%=testrel.getReturn_message()%></h1>
</div>
<%}%>
</body>
