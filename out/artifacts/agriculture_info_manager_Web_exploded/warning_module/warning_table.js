jQuery(document).ready(function() {
    console.log("进入warning_table.js");
    // initWarningTable();
    initDatatable();
});

let url="../ServletAction";

let modifyWarningId;

function initDatatable(){
    $('.datatable').dataTable( {
        "paging":true,
        "searching":false,
        "oLanguage": {
            "aria": {
                "sortAscending": ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            },
            "sProcessing":   "处理中...",
            "sLengthMenu":   "_MENU_ 记录/页",
            "sZeroRecords":  "没有匹配的记录",
            "sInfo":         "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
            "sInfoEmpty":    "显示第 0 至 0 项记录，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项记录过滤)",
            "sInfoPostFix":  "",
            "sSearch":       "过滤:",
            "oPaginate": {
                "sFirst":    "首页",
                "sPrevious": "上页",
                "sNext":     "下页",
                "sLast":     "末页"
            }
        },
        "aoColumns": [{"mRender": function(data, type, full) {
                sReturn = '<input type="checkbox" class="checkboxes" value="'+full.id+'"/>';
                return sReturn;
            },"orderable": false
        },{"mRender": function(data, type, full) {
                sReturn = '<div>'+full.warning_id+'</div>';
                return sReturn;
            },"orderable": false
        },{"mRender": function(data, type, full) {
                sReturn = '<div>'+full.warning_record+'</div>';
                return sReturn;
            },"orderable": false
        },{"mRender": function(data, type, full) {
                sReturn = '<div>'+full.greenhouse_id+'</div>';
                return sReturn;
            },"orderable": false
        },{"mRender": function(data, type, full) {
                sReturn = '<div>'+full.greenhouse_name+'</div>';
                return sReturn;
            },"orderable": false
        },{"mRender": function(data, type, full) {
                sReturn = '<div>'+full.warning_datetime+'</div>';
                return sReturn;
            },"orderable": false
        },{"mRender": function(data, type, full) {
                sReturn = '<div>'+"<a onclick=\"submitDeleteRecord("+full.warning_id+")\">删除</a>"+'</div>';
                sReturn += '<div>'+"<a onclick=\"onModifyRecord("+full.warning_id+")\">修改</a>"+'</div>';
                return sReturn;
            },"orderable": false
        }],


        "aLengthMenu": [[5,10,15,20,25,40,50,-1],[5,10,15,20,25,40,50,"所有记录"]],
        "fnDrawCallback": function(){$(".checkboxes").uniform();$(".group-checkable").uniform();},
        //"sAjaxSource": "get_record.jsp"
        "sAjaxSource": "../ServletAction?Action=showWarningTable"
    });
    $('.datatable').find('.group-checkable').change(function () {
        var set = jQuery(this).attr("data-set");
        var checked = jQuery(this).is(":checked");
        jQuery(set).each(function () {
            if (checked) {
                $(this).attr("checked", true);
                $(this).parents('tr').addClass("active");
            } else {
                $(this).attr("checked", false);
                $(this).parents('tr').removeClass("active");
            }
        });
        jQuery.uniform.update(set);
    });
    $('.datatable').on('change', 'tbody tr .checkboxes', function () {
        $(this).parents('tr').toggleClass("active");
    });
}

function onAddRecord(){
    $("#AddWarningRecord").show();
}

function submitAddRecord(){
    let url="../ServletAction";
    let message={};
    message.Action="addWarningRecord";
    message.warning_record=$("#AddWarningRecord #warning_record").val();
    message.greenhouse_id=$("#AddWarningRecord #greenhouse_id").val();
    $.post(url,message,function (json) {
            if (json.ok === 200) {
                window.location.href="warning_table.html";
                alert("添加成功！");
            } else {
                // 否则登录失败
                alert("添加失败！");
            }
        }
    )
    $("#AddWarningRecord").hide();
}

function submitDeleteRecord(warningId){
    let url="../ServletAction";
    let message={};
    message.id=warningId;
    message.Action="deleteWarningRecord";
    $.post(url,message,function (json) {
            if (json.ok === 200) {
                window.location.href="warning_table.html";
                alert("删除成功！");
            } else {
                // 否则登录失败
                alert("删除失败！");
            }
        }
    )
}

function onModifyRecord(warningId){
    modifyWarningId=warningId;
    $("#ModifyWarningRecord").show();
}

function submitModifyRecord(){
    let url="../ServletAction";
    let message={};
    message.Action="modifyWarningRecord";
    message.warning_id=modifyWarningId;
    message.warning_record=$("#ModifyWarningRecord #modify_warning_record").val();
    message.greenhouse_id=$("#ModifyWarningRecord #modify_greenhouse_id").val();
    $.post(url,message,function (json) {
            if (json.ok === 200) {
                window.location.href="warning_table.html";
                alert("修改成功！");
            } else {
                // 否则登录失败
                alert("修改失败！");
            }
        }
    )
    $("#ModifyWarningRecord").hide();
}

function onDeleteRecord(){
    $(".tip").show();
}

function onQueryRecord(){
    $(".tip").show();
}
