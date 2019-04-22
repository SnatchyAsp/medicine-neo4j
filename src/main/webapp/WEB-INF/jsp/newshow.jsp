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
            height: 500px;
            width: 900px;
            position: relative;
            top: -490px;
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
    <script src="https://unpkg.com/klayjs@0.4.1/klay.js"></script>
    <script src="https://unpkg.com/webcola/WebCola/cola.min.js"></script>
    <%--<script src="../js/cytoscape-klay.js"></script>--%>
    <%--<script src="../js/cytoscape-cola.js"></script>--%>
    <script src="https://unpkg.com/popper.js@1.14.4/dist/umd/popper.js"></script>
    <script src="https://unpkg.com/tippy.js@4.0.1/umd/index.all.min.js"></script>
    <script src="https://unpkg.com/cytoscape-popper@1.0.2/cytoscape-popper.js"></script>
    <%--<script src="../js/cytoscape-popper.js"></script>--%>
    <script type="text/javascript">
    </script>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../css/ztree_css/demo.css" type="text/css">
    <link rel="stylesheet" href="../../css/ztree_css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../../js/ztree_js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="../../js/ztree_js/jquery.ztree.core.js"></script>
    <SCRIPT type="text/javascript">
        <!--
        var setting = {
            view: {
                showIcon: false,
                fontCss: getFont,
                nameIsHTML: true
            },
            async: {
                enable: true,
                url:"../pmo2/getNodes",
                autoParam:["id"],
                //otherParam:{"otherParam":"zTreeAsyncTest"},
                //dataFilter: filter
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                //beforeClick: beforeClick,
                onClick: onClick
            }

        };
        function getFont(treeId, node) {
            return node.font ? node.font : {};
        }
        function onClick(event, treeId, treeNode, clickFlag) {
            $("#head_entity").attr("value",treeNode.name);
            console.log("onlick");
        }
        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting);
        });
        //-->
    </SCRIPT>
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

    <div class="row">
        <div class="col-sm-3">
            <div class="zTreeDemoBackground left">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
        <div class="tab-content col-sm-9">
            <div role="tabpanel" class="tab-pane active" id="relation">
                <div style="padding-top:40px"></div>
                <form id="rel" class="form-inline"  method="post">
                    <div class="form-group">
                        <label for="head_entity" class="col-sm-2 control-label">Subject</label>
                        <div class="col-sm-3">
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
                        <div class="col-sm-3">
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
                    <div class="col-sm-10">
                        <label><input id="umls_pcnn" name="umls_pcnn" type="checkbox"  value="true" checked />umls_pcnn&nbsp;&nbsp;</label>
                        <label><input id="umls_rel" name="umls_rel" type="checkbox"  value="true"/>umls_rel&nbsp;&nbsp;</label>
                        <%--<label><input id="pmoz_rel" name="pmoz_rel" type="checkbox" value="true" checked/>pmoz_rel&nbsp;&nbsp;</label>--%>
                        <%--<label><input id="subclass_of" name="subclass_of" type="checkbox"  value="true" checked/>subclass_of&nbsp;&nbsp;</label>--%>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <button type="button" class="btn btn-default" onclick=query()>query</button>
                        </div>
                    </div>

                </form>
                <form class="form-horizontal col-sm-1" action="input" method="post" style="position: relative;bottom: 35px;right: 150px">
                    <div class="form-group">
                        <div>
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
                        <div class="col-sm-2">
                            <button type="submit" class="btn btn-default">query</button>
                        </div>
                    </div>
                </form>
                <div id="entity_result"></div>
            </div>
        </div>

    </div>
    <!-- Tab panes -->


</div>
<div class="col-sm-offset-3 col-sm-9" id="cy">
    <script type="text/javascript">
        var tempnode=[]
        var tempedge=[]
        function query(){
            cy.nodes().removeClass("highlight")
            cy.edges().removeClass("highlight")
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


                        if(result["return"]["subject_name"]!="0"){
                            alert(result["return"]["object_name"])
                        }
                        else{
                            // for (var i=0;i<cy.nodes().length;i++){
                            //     cy.$(cy.nodes()[i]).lock();
                            // }

                            for(var key in result){
                                if(key == "return"){
                                    continue
                                }
                                tempnode.push({
                                    data: {
                                        name:result[key]["object"]["name"],
                                        info: result[key]["object"]["info"],
                                        id: result[key]["object"]["info"],
                                        ntype:"real"
                                    }
                                })
                                tempnode.push({
                                    data: {
                                        name:result[key]["subject"]["name"],
                                        id: result[key]["subject"]["info"],
                                        info: result[key]["subject"]["info"],
                                        ntype:"real"
                                    }
                                })
                                tempedge.push({
                                    data:{
                                        source:result[key]["subject"]["info"],
                                        target:result[key]["object"]["info"],
                                        id:result[key]["subject"]["info"]+result[key]["object"]["info"]+result[key]["rel_name"]+result[key]["type_name"],
                                        info:result[key]["info"],
                                        type_name:result[key]["type_name"],
                                        rel_name:result[key]["rel_name"]
                                    }
                                })
                                // if(cy.nodes('[id=\"'+result[key]["object"]["name"]+"\"]").length==0) {
                                //     cy.add({
                                //         data:{
                                //             id:result[key]["object"]["name"],
                                //             info:result[key]["object"]["info"]
                                //         }
                                //     })
                                // }
                                // if(cy.nodes('[id=\"'+result[key]["subject"]["name"]+"\"]").length==0) {
                                //     cy.add({
                                //         data:{
                                //             id:result[key]["subject"]["name"],
                                //             info:result[key]["subject"]["info"]
                                //         }
                                //     })
                                // }
                                // if(cy.edges('[id=\"'+key+"\"]").length==0){
                                //     cy.add({
                                //         data:{
                                //             id:key,
                                //             source:"a"
                                //         }
                                //     })
                                //
                                // }
                            }
                            console.log(tempedge)
                        }



                    },
                    error:function (errorMsg) {

                    }


                }
            )
            cy.add(tempnode)
            cy.add(tempedge)
            cy.layout({
                name:"concentric",
                nodeDimensionsIncludeLabels: true
            }).run()
            // for (var i=0;i<cy.nodes().length;i++){
            //     cy.$(cy.nodes()[i]).unlock();
            // }
            // var layout = cy.elements().makeLayout({
            //     name:"random"
            // });
            // layout.run();
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
                        'label': 'data(name)',
                        'size':36
                    }
                },
                {
                    selector: 'node.highlight',
                    style: {
                        'background-color': '#000',
                        'border-width': '2px'
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
                        'optical':0.4,
                        'label':'data(rel_name)'
                    }
                },
                {
                    selector: 'edge[type_name="subclass_of"]',
                    style: {
                        'line-style':'dashed',
                        'line-dash-pattern':[8,20]
                    }
                },
                {
                    selector: 'edge[type_name="umls_rel"]',
                    style: {
                        'line-style':'dashed',
                        'line-dash-pattern':[5,5],
                        'line-cap':'round'
                    }
                },
                {
                    selector: 'edge[type_name="umls_pcnn"]',
                    style: {
                        'line-style':'dashed',
                        'line-dash-pattern':[2,2]
                    }
                },
                {
                    selector: 'edge[type_name="pmoz_rel"]',
                    style: {
                        'line-style':'solid',
                    }
                },
                {
                    selector: 'edge.highlight',
                    style: {
                        'width': 5,
                        'line-color': '#444',
                        'target-arrow-color': '#444',
                        'optical':0.6
                    }
                }
            ],
            layout: {
                name: 'concentric',
                rows: 1
            }

        });
        query()
        document.addEventListener('DOMContentLoaded', function (){
            // var showinfo_node = function(n,x,y){
            //     var info_node =cy.getElementById('info')
            //     var x_pos = info_node.position("x")
            //     var y_pos = info_node.position("y")
            //     cy.remove(info_node);
            //     cy.add({
            //         data:{
            //             id:"info",
            //             label:n.data("info")
            //         },
            //         position: {
            //             x: x,
            //             y: y
            //         }
            //     })
            //     var tempnode = cy.getElementById("info");
            //     tempnode.layout({
            //         name:"preset"
            //     }).run();
            //
            // }
            // var showinfo_edge = function(n,x,y){
            //     var info_node =cy.getElementById('info')
            //     var x_pos = info_node.position("x")
            //     var y_pos = info_node.position("y")
            //     cy.remove(cy.getElementById('info'));
            //     cy.add({
            //         data:{
            //             id:"info",
            //             label:n.data("detail")
            //         },
            //         position: {
            //             x: x,
            //             y: y
            //         }
            //     })
            //     var tempedge = cy.getElementById('info');
            //     tempedge.layout({
            //         name:"preset"
            //     }).run();
            //
            // }
            // cy.on('click','node[ntype="real]',function(evt){
            //     var temppnode = cy.getElementById(this.id());
            //     var x = evt.position.x
            //     var y = evt.position.y
            //     showinfo_node(temppnode,x,y);
            // })
            //
            //
            // cy.on('click','edge[ntype="edge"]',function(evt){
            //     var temppedge = cy.getElementById(this.id());
            //     var x = evt.position.x
            //     var y = evt.position.y
            //     showinfo_edge(temppedge,x,y);
            // })
            var makeTippy = function(node,evt){
                return tippy( node.popperRef(
                ), {
                    content: function(){
                        var div = document.createElement('div');
                        div.innerHTML = node.data("info");
                        return div;
                    },
                    trigger: 'manual',
                    arrow: false,
                    placement: 'bottom',
                    hideOnClick: false,
                    multiple: false,
                    sticky: true,
                    size:'big'
                } );
            };
            var hideTippy = function(node){
                var tippy = node.data('tippy');

                if(tippy != null){
                    tippy.hide();
                }
            };

            var hideAllTippies = function(){
                cy.nodes().forEach(hideTippy);
                cy.edges().forEach(hideTippy);
            };

            cy.on('tap', function(e){
                if(e.target === cy){
                    hideAllTippies();
                }
            });

            cy.on('zoom pan', function(e){
                hideAllTippies();
            });
            cy.on("mouseover",'ele',function (evt) {
                hideAllTippies()
                console.log(cy.extent())
                var anode = cy.getElementById(this.id())
                var temptippy = makeTippy(anode,evt)
                anode.data('tippy', temptippy);
                temptippy.show()
            })

            cy.on('click','node',function(evt){
                cy.nodes().removeClass("highlight")
                cy.edges().removeClass("highlight")
                var temppnode = cy.getElementById(this.id());

                // var nodelist = cy.nodes()
                // for (var i=0;i<nodelist.length;i++){
                //     cy.$(nodelist[i]).lock();
                // }
                $.ajax({
                    type : "post",
                    async : false,
                    url : "/pmo2/getRelation",
                    data : {'head_entity':temppnode.data("name"),
                        'tail_entity':"",
                        'umls_pcnn':$('#umls_pcnn').val(),
                        'umls_rel':$('#umls_rel').val(),
                        'subclass_of':$('#subclass_of').val(),
                        'pmoz_rel':$('#pmoz_rel').val()},
                    dataType : "json",
                    success : function(result) {

                        console.log(result)
                        if(result["return"]["subject_name"]!="0"){
                            alert(result["return"]["object_name"])
                        }
                        else{
                            // for (var i=0;i<cy.nodes().length;i++){
                            //     cy.$(cy.nodes()[i]).lock();
                            // }
                            console.log(result)
                            for(var key in result){
                                if(key == "return"){
                                    continue
                                }
                                tempnode.push({
                                    data: {
                                        name:result[key]["object"]["name"],
                                        info: result[key]["object"]["info"],
                                        id: result[key]["object"]["info"],
                                        ntype:"real"
                                    }
                                })
                                tempnode.push({
                                    data: {
                                        name:result[key]["subject"]["name"],
                                        id: result[key]["subject"]["info"],
                                        info: result[key]["subject"]["info"],
                                        ntype:"real"
                                    }
                                })
                                tempedge.push({
                                    data:{
                                        source:result[key]["subject"]["info"],
                                        target:result[key]["object"]["info"],
                                        id:result[key]["subject"]["info"]+result[key]["object"]["info"]+result[key]["rel_name"]+result[key]["type_name"],
                                        info:result[key]["info"],
                                        type_name:result[key]["type_name"],
                                        rel_name:result[key]["rel_name"]
                                    }
                                })
                                // if(cy.nodes('[id=\"'+result[key]["object"]["name"]+"\"]").length==0) {
                                //     cy.add({
                                //         data:{
                                //             id:result[key]["object"]["name"],
                                //             info:result[key]["object"]["info"]
                                //         }
                                //     })
                                // }
                                // if(cy.nodes('[id=\"'+result[key]["subject"]["name"]+"\"]").length==0) {
                                //     cy.add({
                                //         data:{
                                //             id:result[key]["subject"]["name"],
                                //             info:result[key]["subject"]["info"]
                                //         }
                                //     })
                                // }
                                // if(cy.edges('[id=\"'+key+"\"]").length==0){
                                //     cy.add({
                                //         data:{
                                //             id:key,
                                //             source:"a"
                                //         }
                                //     })
                                //
                                // }
                            }
                            console.log(temppnode)
                        }
                    },
                    error : function(errorMsg) {
                        alert("error!");
                    }
                })
                // var layout = cy.elements().makeLayout({
                //     name:"random"
                // });
                // layout.run();

                cy.add(tempnode)
                cy.add(tempedge)
                temppnode.neighborhood().nodes().addClass("highlight");
                temppnode.addClass("highlight")
                var temppedge = temppnode.connectedEdges()
                temppedge.addClass("highlight")


                cy.layout({
                    name:"concentric",
                    nodeDimensionsIncludeLabels: true,
                    nodeOverlap: 1,
                }).run()

                // var nodelist = cy.nodes()
                // for (var i=0;i<nodelist.length;i++){
                //     cy.$(nodelist[i]).unlock();
                // }
                cy.center(temppnode.neighborhood().nodes().union(temppnode))
                cy.fit(temppnode.neighborhood().nodes().union(temppnode))
            })



        })
    </script>
</div>
</body>
