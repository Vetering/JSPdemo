<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: veter
  Date: 2023/12/4
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.net.InetAddress" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.net.UnknownHostException" %>
<%@ page import="java.util.Enumeration" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="src/assets/css/login.css">
  <link rel="stylesheet" type="text/css"href="src/assets/css/iconfont.css"/>
  <title>学生信息管理系统</title>
</head>
<body>
<div class="login-container">
  <div class="login">
    <form id="loginForm" class="login-form" action="/api/LoginServlet" method="post">
      <h2 class="title">学生信息管理系统</h2>
      <div class="form-group" style="display: flex">
        <label for="role"><label for="password"><i class="iconfont">&#xe74d;</i></label></label>
        <select id="role" name="role">
          <option name="1" value="1">管理员</option>
          <option name="2" value="2">教师</option>
        </select>
      </div>
      <div class="form-group" style="display: flex">
        <label for="username"><i class="iconfont">&#xe609;</i></label>
        <input type="text" id="username" name="username" class="username" placeholder="登录用户" value="" required>
      </div>
      <div class="form-group" style="display: flex">
        <label for="password"><i class="iconfont">&#xe605;</i></label>
        <input type="password" id="password" name="password" class="password" placeholder="登录密码" value="" required>
      </div>
      <button class="ui-button" style="" type="submit">登录</button>
    </form>
    <div id="dl">
      <a id="zc" href="./register.jsp">新用户注册</a>
      <a id="sms" href="./update.jsp">修改密码</a>
    </div>
    <div> <a id="qt" href="">其它方式登录&gt;</a></div>
    <div id="xy" style="display: flex">
      <p style="padding-left: 11%;color:black"> 登录即表示同意平台</p>
      <a id="xya" href="" style="color: #317de7">
        <p>《隐私政策》</p>
      </a>
      <p style="color:black;">和 </p>
      <a id="xya" href=""  style="color: #317de7">
        <p>《用户协议》</p>
      </a>
    </div>
    <%

      // 获取HttpServletRequest对象
      HttpServletRequest httpRequest1 = (HttpServletRequest) pageContext.getRequest();
      // 输出指定请求头的值
      String platformHeaderValue = httpRequest1.getHeader("sec-ch-ua-platform");

      // 将值存储在Session中，以备后用
      if (platformHeaderValue == null) {
        platformHeaderValue = "未知平台";
      }

      HttpSession session2 = httpRequest1.getSession();
      session2.setAttribute("platformHeaderValue", platformHeaderValue);
    %>

    <!-- 在页面中显示指定请求头的值 -->
    <div style="display: flex;position: relative;bottom: 0px;display: inline-block;margin-left: 25%;"><h3 style="display:inline-block;">登录平台:</h3> <%= platformHeaderValue %></div>

  </div>
  <div class="interval"></div>
  <div class="img">
  </div>

</div>
</body>
</html>


