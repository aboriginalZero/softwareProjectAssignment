$(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    //退出系统
    $("#logout").click(function () {
        if (confirm('您确定退出吗？')) {
            $("#logoutform").submit();
        }
    });
    //查看个人历史评论
    $("#myComments").click(function () {
        var username = $("#username").val();
        var $list = $('#newsList').empty();
        var html = "";
        $.ajax({
            type: "POST",
            url: "/comments/newComments",
            data: "content="+textarea +"&title=" + newstitle + "&usersName="+ usersName,
            headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
            success: function (data) {
                if (data == 1) {
                    alert("保存成功");
                    location.reload();
                } else {
                    alert("评论提交失败");
                }
            }
        });
        $.get('./list?t=' + new Date().getTime(), {

            content: $("#commentscontent").val()
        }, function (data) {
            currentPageData = data.content;
            $(".pagination").pagination(data.totalElements, getOpt());
        });
    });
    //修改个人信息
    $('#saveForm').validate({
        rules: {
            name       :{required:true}
        },messages:{
            name :{required:"必填"}
        }
    });
    $('.saveBtn').click(function(){
        if($('#saveForm').valid()){
            $.ajax({
                type: "POST",
                url: "./update",
                data: $("#saveForm").serialize(),
                headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                success: function (data) {
                    if (data == 1) {
                        alert("编辑成功");
                        closeDialog();
                    } else {
                        alert(data);
                    }
                }
            });
        }else{
            alert('数据验证失败，请检查！');
        }
    });
});

var artdialog;
function updateInfo() {
    var username = $("#username").val();
    $.get("./updateInfo/" + username, {ts: new Date().getTime()}, function (data) {
        art.dialog({
            lock: true,
            opacity: 0.3,
            title: "修改",
            width: '750px',
            height: 'auto',
            left: '50%',
            top: '50%',
            content: data,
            esc: true,
            init: function () {
                artdialog = this;
            },
            close: function () {
                artdialog = null;
            }
        });
    });
}
function closeDialog() {
    artdialog.close();
}

