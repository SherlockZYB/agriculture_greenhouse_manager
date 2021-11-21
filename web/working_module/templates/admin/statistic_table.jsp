<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<!-- BEGIN HEAD -->
<head>
    <title>员工工资统计</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<%--    <meta http-equiv="Content-type" content="text/html; charset=utf-8">--%>
    <%@include file="../../home/frame/frame_style.jsp"%>

    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<body class="page-header-fixed page-quick-sidebar-over-content ">

<div class="clearfix">
</div>

<div class="page-content">
    <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
    <div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">Modal title</h4>
                </div>
                <div class="modal-body">
                    Widget settings form goes here
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn blue">Save changes</button>
                    <button type="button" class="btn default" data-dismiss="modal">Close</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
    <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
    <!-- BEGIN STYLE CUSTOMIZER -->
    <div class="theme-panel hidden-xs hidden-sm">
        <div class="toggler">
        </div>
        <div class="toggler-close">
        </div>
        <div class="theme-options">
            <div class="theme-option theme-colors clearfix">
                <span>
                THEME COLOR </span>
                <ul>
                    <li class="color-default current tooltips" data-style="default" data-container="body" data-original-title="Default">
                    </li>
                    <li class="color-darkblue tooltips" data-style="darkblue" data-container="body" data-original-title="Dark Blue">
                    </li>
                    <li class="color-blue tooltips" data-style="blue" data-container="body" data-original-title="Blue">
                    </li>
                    <li class="color-grey tooltips" data-style="grey" data-container="body" data-original-title="Grey">
                    </li>
                    <li class="color-light tooltips" data-style="light" data-container="body" data-original-title="Light">
                    </li>
                    <li class="color-light2 tooltips" data-style="light2" data-container="body" data-html="true" data-original-title="Light 2">
                    </li>
                </ul>
            </div>
            <div class="theme-option">
                <span>
                Theme Style </span>
                <select class="layout-style-option form-control input-sm">
                    <option value="square" selected="selected">Square corners</option>
                    <option value="rounded">Rounded corners</option>
                </select>
            </div>
            <div class="theme-option">
                <span>
                Layout </span>
                <select class="layout-option form-control input-sm">
                    <option value="fluid" selected="selected">Fluid</option>
                    <option value="boxed">Boxed</option>
                </select>
            </div>
        </div>
    </div>
    <!-- END STYLE CUSTOMIZER -->
    <!-- BEGIN PAGE CONTENT-->
    <div class="row">
        <div class="col-md-12">
            <!-- BEGIN ROW -->
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN CHART PORTLET-->
                    <div class="portlet light bordered">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-bar-chart font-green-haze"></i>
                                <span class="caption-subject bold uppercase font-green-haze">工资统计表</span>
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse">
                                </a>
                                <a href="#portlet-config" data-toggle="modal" class="config">
                                </a>
                                <a href="javascript:;" class="reload">
                                </a>
                                <a href="javascript:;" class="fullscreen">
                                </a>
                                <a href="javascript:;" class="remove">
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div id="chart_1" class="chart" style="height: 500px;">
                            </div>
                        </div>
                    </div>
                    <!-- END CHART PORTLET-->
                </div>
            </div>
            <!-- END ROW -->
        </div>
    </div>
    <!-- END PAGE CONTENT-->
</div>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="../../assets/global/plugins/respond.min.js"></script>
<script src="../../assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="../../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="../../assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="../../assets/global/plugins/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/amcharts/amcharts/serial.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/amcharts/amcharts/themes/light.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../../assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="../../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<%--<script src="../../assets/admin/pages/scripts/charts-amcharts.js"></script>--%>
<script>
    jQuery(document).ready(function() {
        // initiate layout and plugins
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
        QuickSidebar.init(); // init quick sidebar
        Demo.init(); // init demo features
        // ChartsAmcharts.init(); // init demo charts
        initStatisticTable();
    });
</script>
<script>
    function initStatisticTable(){
        let url="../../../ServletAction";
        let message={};
        message.Action="statisticSalaryRecord";
        $.post(url,message,function (json) {
            if (json.ok === 200) {
                alert(JSON.stringify(json));
                alert("统计成功！");
            } else {
                alert("统计失败！");
            }
            AmCharts.makeChart("chart_1", {
                "type": "serial",
                "theme": "light",
                "pathToImages": Metronic.getGlobalPluginsPath() + "amcharts/amcharts/images/",
                "autoMargins": false,
                "marginLeft": 30,
                "marginRight": 8,
                "marginTop": 10,
                "marginBottom": 26,

                "fontFamily": 'Open Sans',
                "color":    '#888',

                "dataProvider": json.aaData,
                "valueAxes": [{
                    "axisAlpha": 0,
                    "position": "left"
                }],
                "startDuration": 1,
                "graphs": [{
                    "alphaField": "alpha",
                    "balloonText": "<span style='font-size:13px;'>[[title]] in [[category]]:<b>[[value]]</b> [[additional]]</span>",
                    "dashLengthField": "dashLengthColumn",
                    "fillAlphas": 1,
                    "title": "Number",
                    "type": "column",
                    "valueField": "salary_number"
                }],
                "categoryField": "employee_id",
                "categoryAxis": {
                    "gridPosition": "start",
                    "axisAlpha": 0,
                    "tickLength": 0
                }
            });

            $('#chart_1').closest('.portlet').find('.fullscreen').click(function() {
                chart.invalidateSize();
            });
            }
        )

    }
</script>
<!-- END PAGE LEVEL SCRIPTS -->
</body>
<!-- END BODY -->
</html>