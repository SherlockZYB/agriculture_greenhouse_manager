// 保存当前的dataTable信息
let type=this.location.search;
let data=type.split("sort=");
let sort=data[1];
// 获取上页传入的信息
sort=decodeURI(sort);

//删除或修改
function DeleteAndModifyTip(){
	alert("请在表格最后一栏中直接删除或修改");
}
//添加
function AddShow(){
	let addTip=document.getElementById("add_tip");
	addTip.style.display="block";
}


// 目前还为对输入的合法性进行验证
function AddRecord(){
	let account=document.getElementById("account").value;
	let mail=document.getElementById("mail").value;
	let password=document.getElementById("password").value;
	let userLevel=document.getElementById("userLevel").value;
	let isWorker=document.getElementById("isWorker").value;
	if(account==="" || mail==="" || password===""){
		alert("请填写完整信息");
	}
	if(isWorker==="是"){
		isWorker=1;
	}else{
		isWorker=0;
	}
	if(userLevel==="系统管理员"){
		userLevel=0;
	}else if(userLevel==="普通管理员"){
		userLevel=1;
	}else{
		userLevel=2;
	}
	let message={};
	message.Action="addUserInfo";
	message.account=account;
	message.mail=mail;
	message.password=password;
	message.userLevel=userLevel;
	message.isWorker=isWorker;
	let url="../ServletAction";
	$.post(url,message,function(json){
		if(json.ok===200){
			// 重新加载页面
			alert("添加成功!");
			window.location.reload();
		}else{
			alert("添加失败，请确定联网，稍后重试!");
		}
	})

}


function initDataTable(){
	// 全局设置监听
	$(".sure").click(function (){
		$(".tip").fadeOut(100);
	});
	// 2.弹窗的取消
	$(".cancel").click(function (){
		$(".tip").fadeOut(100);
	});
	$(".tiptop a").click(function (){
		$(".tip").fadeOut(100);
	});
	// 初始化DataTable！
	init();
}
//获取大棚信息
function init(){
	resultList=[];
	$('.datatable').dataTable({
		"paging": true,
		"searching": true,
		"retrieve":true,
		// "destroy":true,
		"oLanguage": {
			"aria": {
				"sortAscending": ": activate to sort column ascending",
				"sortDescending": ": activate to sort column descending"
			},
			"sProcessing": "处理中...",
			"sLengthMenu": "_MENU_ 记录/页",
			"sZeroRecords": "没有匹配的记录",
			"sInfo": "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
			"sInfoEmpty": "显示第 0 至 0 项记录，共 0 项",
			"sInfoFiltered": "(由 _MAX_ 项记录过滤)",
			"sInfoPostFix": "",
			"sSearch": "查询:",
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "上页",
				"sNext": "下页",
				"sLast": "末页"
			}
		},
		"aoColumns": [{
			"mRender": function (data, type, full) {
				sReturn = '<input type="checkbox" class="checkboxes" value="' + data + '"/>';
				return sReturn;
			}, "orderable": false
		}, {
			"mRender": function (data, type, full) {
				sReturn = "<div>"+full.greenhouse_name+"</div>";
				return sReturn;
			}, "orderable": true
		}, {
			"mRender": function (data, type, full) {
				sReturn = "<div>"+full.greenhouse_status+"</div>";
				return sReturn;
			}, "orderable": true
		},  {
			"mRender": function (data, type, full) {
				sReturn ="<div>"+full.greenhouse_remark+"</div>";
				return sReturn;
			}, "orderable": true
		},{
			"mRender": function (data, type, full) {
				sReturn = "<div>"+full.greenhouse_datetime+"</div>";
				return sReturn;
			}, "orderable": true
		},{
			"mRender": function (data, type, full) {
				sReturn = "<div>"+full.greenhouse_manager+"</div>";
				return sReturn;
			}, "orderable": true
		},{
			"mRender": function (data, type, full) {
				resultList.push(full);
				sReturn = "<a href=\"javascript:ModifyShow("+full.id+")\">【修改记录】</a><a href=\"javascript:DeleteRecord(" + full.id + ")\">【删除记录】</a>";
				return sReturn;
			}, "orderable": true
		}
		],
		"aLengthMenu": [[5, 10, 15, 20, 25, 40, 50, -1], [5, 10, 15, 20, 25, 40, 50, "所有记录"]],
		"fnDrawCallback": function () {
			$(".checkboxes").uniform();
			$(".group-checkable").uniform();
		},
		"sAjaxSource": "../greenhouseModule_file_servlet_action?Action=getGreenhouseRecord&sort="+sort

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
};