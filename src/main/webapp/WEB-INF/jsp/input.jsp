<%--
  Created by IntelliJ IDEA.
  User: nclab
  Date: 2019/1/14
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
            data: {
                simpleData: {
                    enable: true
                }
            },
            async: {
                enable: true,
                url:"getNodes",
                autoParam:["id"],
                //otherParam:{"otherParam":"zTreeAsyncTest"},
                //dataFilter: filter
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
<body>
<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#relation" aria-controls="relation" role="tab" data-toggle="tab">Relation</a></li>
        <li role="presentation"><a href="#Entity" aria-controls="Entity" role="tab" data-toggle="tab">Entity</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content  col-sm-9" >
        <div role="tabpanel" class="tab-pane active" id="relation">
            <div style="padding-top:40px"></div>
            <form class="form-horizontal" action="getRelation" method="post">
                <div class="form-group">
                    <label for="head_entity" class="col-sm-2 control-label">Subject</label>
                    <div class="col-sm-10">
                        <input type="text" name="head_entity" class="form-control" list="head_entity_list" id="head_entity"  placeholder="Subject">
                        <datalist id="head_entity_list">
                            <%--<%for(int i=0;i<1000;i++){--%>
                                <%--%>--%>
                            <%--<option value="<%=i%>"<%=i%></option>--%>
                            <%--<%--%>

                            <%--}%>--%>
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
                        <input type="text" name="tail_entity" class="form-control" list="tail_entity_list" id="tail_entity"  placeholder="Object">
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
                    <label><input name="umls_pcnn" type="checkbox" value="umls_pcnn" checked/>umls_pcnn&nbsp;&nbsp;</label>
                    <label><input name="umls_rel" type="checkbox" value="umls_rel" checked/>umls_rel&nbsp;&nbsp;</label>
                    <label><input name="pmoz_rel" type="checkbox" value="pmoz_rel" checked/>pmoz_rel&nbsp;&nbsp;</label>
                    <label><input name="subclass_of" type="checkbox" value="subclass_of" checked/>subclass_of&nbsp;&nbsp;</label>
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

    <div class="zTreeDemoBackground left" style="position: relative;bottom: 180px;left: 50px">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
</div>
</body>
</html>