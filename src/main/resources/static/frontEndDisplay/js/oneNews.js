$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    // window.onload = function () {
    //     var editrole = $("#editrole").val();
    //     var $list = $('#tbodyContent').empty();
    //     var html = "";
    //     if (editrole == 'true') {
    //         html += '<li><a th:href="@{/users/index}">后台管理</a></li>';
    //         $list.append($(html));
    //     }
    // }
    // 查询处理

    $('#addComments').click(function () {
        var usersName = $("#usersName").val()
        var textarea = $("#textarea").val();
        var newstitle = $("#newstitle").val();
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
    });
});

