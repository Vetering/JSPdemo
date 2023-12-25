<%--
  Created by IntelliJ IDEA.
  User: veter
  Date: 2023/12/4
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="src/assets/css/register.css">
    <link rel="stylesheet" type="text/css"href="src/assets/css/iconfont.css"/>
    <title>学生信息管理系统</title>
</head>
<body>
<div class="zc-container">
    <div class="login">
        <form id="zcForm" class="zc-form" action="/api/RegistersServlet" method="post">
            <div class="return"><a href="/"><h3>&lt;返回</h3></a></div>
            <h2 class="title">学生信息管理系统</h2>
            <div class="form-group" style="display: flex">
                <label for="role"><label for="password"><i class="iconfont">&#xe74d;</i></label></label>
                <select id="role" name="role">
                    <option name="1" value="1">管理员</option>
                    <option name="2" value="2">教师</option>
                    <option name="3" value="3">学生</option>
                </select>
            </div>
            <div class="form-group" style="display: flex">
                <label for="username"><i class="iconfont">&#xe609;</i></label>
                <input type="text" id="username" name="username" class="username" placeholder="设置用户" required>
            </div>
            <div class="form-group" style="display: flex">
                <label for="password"><i class="iconfont">&#xe605;</i></label>
                <input type="password" id="password" name="password" class="password" placeholder="设置密码" required>
            </div>
            <button class="ui-button" type="submit">注册</button>
        </form>
        <div id="xy" style="display: flex">
            <p style="padding-left: 60px;color:black"> 登录即表示同意平台</p>
            <a id="xya" href="" style="color: #317de7">
                <p>《隐私政策》</p>
            </a>
            <p style="color:black;">和 </p>
            <a id="xya" href="" style="color: #317de7">
                <p>《用户协议》</p>
            </a>
        </div>
        <%
            // 获取HttpServletRequest对象
            HttpServletRequest httpRequest1 = (HttpServletRequest) pageContext.getRequest();

            // 输出指定请求头的值
            String platformHeaderValue = httpRequest1.getHeader("sec-ch-ua-platform");

            // 将值存储在Session中，以备后用
            HttpSession session2 = httpRequest1.getSession();
            session2.setAttribute("platformHeaderValue", platformHeaderValue);
        %>

        <!-- 在页面中显示指定请求头的值 -->
        <div style="display: flex;position: relative;bottom: 0px;display: inline-block;margin-left: 25%;"><h3 style="display:inline-block;">注册平台:</h3> <%= platformHeaderValue %></div>

    </div>
    <div class="interval"></div>
    <div class="img">
    </div>
</div>
</body>
</html>


