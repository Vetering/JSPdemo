function searchAction() {
    const keyword = $('#fuzzy_query').val();
    // 使用AJAX调用AdminListServlet并传递关键字
    $.ajax({
        type: "GET",
        url: "AdminListServlet",
        data: { action: "search", keyword: keyword },
        success: function(response) {
            // 处理响应，更新表格等
            console.log("查询操作完成：", response);
        },
        error: function(error) {
            console.error("查询操作出错：", error);
        }
    });
}

function resetAction() {
    // 使用AJAX调用AdminListServlet并传递重置操作
    $.ajax({
        type: "GET",
        url: "AdminListServlet",
        data: { action: "reset" },
        success: function(response) {
            // 处理响应，更新表格等
            console.log("重置操作完成：", response);
        },
        error: function(error) {
            console.error("重置操作出错：", error);
        }
    });
}