jQuery(document).ready(function() {
    console.log("进入warning_table.js");
    initWarningTable();
});

let url="../ServletAction";

function initWarningTable(){
    let message={};
    message.Action="showWarningTable";
    $.post(url,message,function (json){
        var list=json.warning_list;
        console.log(list);
        var html="";
        if(list!=undefined && list.length>0){
            for(var i=0;i<list.length;i++){
                var record=list[i];
                html=html+"<tr>\n" +
                    "                                        <td>"+record.warning_id+"</td>\n" +
                    "                                        <td>"+record.warning_record+"</td>\n" +
                    "                                        <td>"+record.greenhouse_id+"</td>\n" +
                    "                                        <td>"+record.greenhouse_name+"</td>\n" +
                    "                                        <td>"+record.warning_datetime+"</td>\n" +
                    // "                                        <td><a href=\"javascript:Page.onModifyRecord("+record.id+")\">【修改】</a><a href=\"javascript:Page.onDeleteRecord("+record.id+")\">【删除】</a></td>\n" +
                    "                                    </tr>";
            }
        }
        $("#warning_list").html(html);
    });
}

function onAddRecord(){
    $(".tip").show();
}

function onModifyRecord(){
    $(".tip").show();
}

function onDeleteRecord(){
    $(".tip").show();
}

function onQueryRecord(){
    $(".tip").show();
}
