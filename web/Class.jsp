<%--
  Created by IntelliJ IDEA.
  User: veter
  Date: 2023/12/6
  Time: 0:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<%
    // 检查用户是否已经登录
    HttpSession session1 = request.getSession(false);
    session1.getMaxInactiveInterval();
    Object attributeValue = session.getAttribute("parameter");
    if (session1 == null || (!"1".equals(attributeValue != null ? attributeValue.toString() : null) && !"2".equals(attributeValue != null ? attributeValue.toString() : null))) {
        // 如果未登录，弹出警告消息
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>还没登录！请登录！</title>
    <script>
        alert("您还未登录，请登录！");
    </script>
    <meta http-equiv="refresh" content="0;url=/login.jsp">
</head>
<%
        return; // 终止代码执行，确保不会继续向下执行
    }
%>

<title>学生信息管理系统</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vertical Menu Example</title>

    <link rel="stylesheet" href="/src/assets/element-ui/index.css">
    <script src="/src/assets/js/vue.js"></script>
    <script src="/src/assets/element-ui/index.js"></script>
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            zoom: 90%;
            -moz-transform: scale(1); /* Firefox */
            -webkit-transform: scale(1); /* Chrome, Safari, Opera */
            transform: scale(1);
            transform-origin: 0 0
        }
        .el-header {
            background-color: #B3C0D1;
            color: #333;
            line-height: 60px;
        }

        .el-aside {
            color: #333;
        }
        .el-submenu{
            margin-top: -2px;
        }
        .top-nav-icon{
            margin-left: -5px;
            width: 80px;
            height: 50px;
            border-radius: 5px;
        }
        .el-table el-table--fit el-table--scrollable-y el-table--enable-row-hover el-table--enable-row-transition{
            height: 100%;
        }
        .el-table__body{
            height: 100%;
        }
        .top-nav-icon{
            margin-left: -5px;
            width: 80px;
            left: 16%;
            margin-top: 5px;
            position: absolute;
            border-radius: 5px;
        }
        .el-submenu__title{
            padding-left: 20px;
            height: 6.5%;
        }
        .main-nav-icon{
            font-size: 200%;
            left: 25%;
            top: -3%;
            position: absolute;
        }
        .el-table__row{
            height: 3%;
        }
        .top-nav-right{
            margin-right: 5%;
        }
        .top-nav-right>span{
            text-overflow: ellipsis;
        }
    </style>
</head>
<%
    HttpSession session2 = request.getSession(false);
    session2.getMaxInactiveInterval();
%>
<body>
<div style="height: 100%">
    <div id="app" style="">
        <el-container style="height: 100%; border: 1px solid #eee;">
            <el-aside width="15%" style="background-color: rgb(238, 241, 246);height: 100%;">
                <el-menu  :default-openeds="['1', '3']">
                    <el-submenu index="1">
                        <template slot="title"><i class="el-icon-message"></i>学院信息</template>
                            <el-menu-item  index="2-1" @click="handleClassAll">${sessionScope._class}</el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-container>
                <el-header style="text-align: right; font-size: 12px;border-radius: 5px; background-color: white;	border-bottom-style:solid;border-width:1px;">
                    <img class="top-nav-icon" src="src/assets/img/score-icon.png">
                    <h1 class="main-nav-icon" >学生信息管理系统</h1>
                    <div class="top-nav-right"><el-dropdown>
                        <i class="el-icon-setting"  style="margin-right: 15px"></i>
                        <el-dropdown-menu slot="dropdown" style="text-align: center">
                            <el-dropdown-item ><el-button style="border: none" @click="Index">首页</el-button></el-dropdown-item>
                            <el-dropdown-item>
                                <el-button style="border: none" @click="Quit">退出登录</el-button></el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                        <span>${sessionScope.username}</span>
                    </div>
                </el-header>
                <div>
                    <el-input style="width: 200px;margin-top: 5px" placeholder="查询用户名" v-model="name"></el-input>
                    <el-button type="success" @click="handleSelect" >查询</el-button>
                    <el-button type="primary"  @click="reset">重置</el-button>

                </div>
                <div style="margin: 10px 0 10px 10px;">
                    <el-button type="primary" plain @click="handleAdd">新增</el-button>
                    <el-button type="danger" plain @click="handleDeletes">批量删除</el-button>
                    <el-button type="info" plain @click="exportData">批量导出Excel</el-button>
                    <el-upload
                            action="/api/import"
                            :show-file-list="false"
                            :on-success="handleImport"
                            :on-error="handleImportError"
                            accept=".xlsx, .xls"
                            style="display: inline-block; margin-left: 10px"
                    >
                        <el-button type="success" plain>批量导入Excel</el-button>
                    </el-upload>
                </div>
                <el-main>
                    <el-table   ref="multipleTable"
                                :data="tableData"
                                tooltip-effect="dark" @selection-change="handleSelectionChange" style="width: 100%;"height="100%">
                        <el-table-column type="selection" width="55" height="3%" align="center"></el-table-column>
                        <el-table-column prop="id" label="id"  width="140"height="3%" align="center" style="padding-bottom: 10px"></el-table-column>
                        <el-table-column prop="StudentID" label="学号" width="140"height="3%" align="center"></el-table-column>
                        <el-table-column prop="name" label="姓名" width="160"height="3%" align="center"></el-table-column>
                        <el-table-column prop="sex" label="性别" width="90" height="3%" align="center">
                            <template slot-scope="scope">
                                {{ scope.row.sex === 'M' ? '男' : (scope.row.sex === 'W' ? '女' : '') }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="Age" label="年龄" width="60"height="3%" align="center"></el-table-column>
                        <el-table-column prop="_faculty" label="院系" width="130" height="3%"align="center"></el-table-column>
                        <el-table-column prop="_class" label="班级" width="180" height="3%"align="center"></el-table-column>
                        <el-table-column prop="AdmissionTime" label="入学时间" width="140"height="3%" align="center"></el-table-column>
                        <el-table-column  label="操作" alogn="center" width="140" height="3%"align="right"></el-table-column>
                        <el-table-column>
                            <template slot-scope="scope">
                                <el-button type="primary" icon="el-icon-edit" circle size="mini"
                                           @click="handleEdit(scope.row)"></el-button>
                                <el-button type="danger" icon="el-icon-delete" circle size="mini"
                                           type="danger"
                                           @click="handleDelete(scope.$index, scope.row)"></el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-main>
                <div >
                    <el-pagination
                            @current-change="handleCurrentChange"
                            :current-page="pageNum"
                            :page-sizes="[100,200,300,400]"
                            :page-size="pageSize"
                            layout="total, prev, pager, next"
                            :total="total">
                    </el-pagination>
                </div>
                <el-dialog title="学生信息管理" align="center" :visible.sync="fromVisible" width="30%">
                    <el-form :model="form" label-width="80px" style="padding-right: 20px" :rules="rules" ref="formRef">
                        <el-form-item label="学号" prop="StudentID">
                            <el-input v-model="form.StudentID" placeholder="学号"></el-input>
                        </el-form-item>
                        <el-form-item label="姓名" prop="name">
                            <el-input v-model="form.name" placeholder="name"></el-input>
                        </el-form-item>
                        <el-form-item label="性别" prop="sex">
                            <el-radio-group v-model="form.sex" style="margin-top: 2.5%;margin-left: -76%;">
                                <el-radio :label="'M'">男</el-radio>
                                <el-radio :label="'W'">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="院系" prop="_faculty">
                            <el-input v-model="form._faculty" placeholder="院系"></el-input>
                        </el-form-item><el-form-item label="年龄" prop="Age">
                        <el-input v-model="form.Age" placeholder="年龄"></el-input>
                    </el-form-item>
                        <el-form-item label="入学时间" prop="AdmissionTime">
                            <el-date-picker style="width: 100%"
                                            v-model="form.AdmissionTime"
                                            type="date"
                                            placeholder="选择日期" @change="handleDateChange">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="班级" prop="_class">
                            <el-input v-model="form._class" placeholder="班级"></el-input>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="fromVisible=false">取 消</el-button>
                        <el-button type="primary" @click="save">确 定</el-button>
                    </div>
                </el-dialog>
            </el-container>
        </el-container>
    </div>
</div>
<%
    Object _class = session.getAttribute("_class");
%>
<script src="src/assets/js/axios.min.js"></script>
<script src="src/assets/js/main.js"></script>
<script src="src/assets/js/vue-router.js"></script>
<script>
    new Vue({
        el: '#app',
        data() {
            return {
                sessionScope: {
                    _class: '' // 初始化为一个空字符串
                },
                pageNum: 0,
                pageSize: 10,
                tableData: [],
                total: 200,
                search: '',
                name: '',
                fromVisible: false,
                form: {sex: ''},
                rules: {
                    StudentID: [
                        { required: true, message: '请输入学号', trigger: 'blur' },
                    ],
                    // 添加其他字段的验证规则
                },
                multipleSelection: []
            };
        },
        created() {
            // 先确保 this.sessionScope._class 已经被设置
            this.sessionScope._class = '<%= session.getAttribute("_class") %>';
            console.log("Value of _class in created:", this.sessionScope._class);

            // 直接调用 load() 方法
            this.load();

            // 添加事件监听器
            window.addEventListener('handleClassSuccess', this.handleClassAll);
        },
        methods: {
            Index(){
              window.location.href='/admin.jsp';
            },
            handleClassAll() {
                const _class = this.sessionScope._class;
                console.log(this.sessionScope._class)
                // 使用axios发送GET请求
                axios.get('/api/ClassServlet', {
                    params: {
                        _class: _class
                    }
                })
                    .then(response => {
                        // 处理请求成功的逻辑
                        console.log(response.data);
                    })
                    .catch(error => {
                        // 处理请求失败的逻辑
                        console.error('请求错误:', error);
                    });
            },
            Quit(){
                window.open('/api/LogoutServlet')
            },
            handleImport(response) {
                console.log('Message:', response);
                this.$message.success('导入成功！');

                if (this.total % 10 !== 0) {
                    this.pageNum = Math.ceil(this.total / 10);
                    console.log("else.pageNum1:", this.total / 10);
                    console.log("else.pageNum:", this.pageNum);
                    this.load()

                }else if (this.total % 10 === 0) {
                    this.pageNum = Math.ceil(this.total / 10)+1
                    console.log(Math.ceil(this.total / 10))
                    console.log(this.total / 10)
                    console.log("if.pageNum:", this.pageNum);
                    this.load()
                }

            },

            handleImportError(error, file, fileList) {
                console.error('Import error:', error);
                // 处理导入失败的逻辑，如果有需要的话
                this.$message.error('导入失败');
            },
            exportData(){
                const _class = this.sessionScope._class;
                console.log(_class);
                window.open(`/api/ExportClassServlet?_class=`+_class);
            },
            handleSelectionChange(val) {
                this.multipleSelection = val.map(item => ({ id: item.id }));
                console.log(this.multipleSelection);
            },handleDeletes() {
                this.$confirm('您确认删除吗？','确认删除',{type: "warning"}).then(response =>
                    axios.post('/api/DeleteServlet', this.multipleSelection)
                        .then(res => {
                            this.$message.success('删除成功！');
                            this.load();
                            console.log(res.data);
                            // 如果需要，处理从服务器返回的响应
                        })
                        .catch(error => {
                                this.$message.error('删除失败');
                            }
                        ));
            },toggleSelection(rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row);
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },
            handleDateChange() {
                if (this.form.AdmissionTime) {
                    // 将日期转换为 ISO 格式的字符串，然后提取日期部分
                    const isoDateString = new Date(this.form.AdmissionTime).toISOString();
                    const formattedDate = isoDateString.split('T')[0];
                    // 更新 AdmissionTime 为提取的日期部分
                    this.form.AdmissionTime = formattedDate;
                }
            },
            handleSelect() {
                axios.get('/StudentServlet', {
                    params: {
                        pageNum: 0,
                        pageSize: this.total,
                        name: this.name
                    }
                }).then(response => {
                    const dataArray = response.data.data;
                    const lastItem = dataArray[dataArray.length - 1];
                    this.total = lastItem && lastItem.data && lastItem.data.total;
                    this.tableData = response.data.data.slice(0, -1).map(item => item.data);
                }).catch(error => {
                    console.error('Error fetching data:', error);
                });
            },
            menu(){
                axios.get('/MenuServlet').then(response => {
                    this.MenuData = response.data.data.slice(0, -1).map(item => item.data);
                }).catch(error => {
                    console.error('Error fetching data:', error);
                });
            },
            load() {
                const _class = this.sessionScope._class;
                console.log(_class);
                axios.get('/api/ClassServlet', {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        _class:this.sessionScope._class
                    }
                }).then(response => {
                    const dataArray = response.data.data;
                    const lastItem = dataArray[dataArray.length - 1];
                    this.total = lastItem && lastItem.data && lastItem.data.total;
                    this.tableData = response.data.data.slice(0, -1).map(item => item.data);
                    console.log(this.tableData)
                    console.log(this.total)
                }).catch(error => {
                    console.error('数据错误！:', error);
                });
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(pageNum) {
                this.pageNum = pageNum;
                this.load();
            },
            reset() {
                this.name = '';
                this.pageNum=1
                this.load();
            },
            handleEdit(row) {   // 编辑数据
                this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  深拷贝数据
                this.fromVisible = true   // 打开弹窗
            },
            handleAdd() {   // 新增数据
                this.form = {  }  // 新增数据的时候清空数据
                this.fromVisible = true   // 打开弹窗
            },
            save() {
                axios.get('/api/updateServlet', {
                    params: this.form
                }).then(response => {
                    console.log(response.data.data);
                    if (response.data.data) {
                        const resultData = response.data.data[0];
                        if (resultData.code === 200) {
                            this.$message.success('保存成功');
                            this.pageNum = 1;
                            this.load();
                            this.fromVisible = false;
                        } else if (resultData.code === 500) {
                            // Handle error
                            this.$message.error('保存失败');
                        } else {
                            this.$message.warning('保存状态未知');
                        }
                    } else {
                        this.$message.error('未收到有效数据');
                    }
                }).catch(error => {
                    console.error('Error in save request:', error);
                    this.$message.error('保存请求失败');
                });
            },
            handleDelete($index, row) {
                console.log($index, row.id);
                this.$confirm('您确认删除吗？','确认删除',{type: "warning"}).then(response =>
                    axios.get('/api/DeleteServlet', {
                        params: { id: row.id }
                    }).then(response => {
                        const resultData = response.data && response.data.data && response.data.data[0];
                        if (resultData && resultData.code === 200) {
                            this.$message.success('删除成功');
                            this.pageNum = 1;
                            this.load();
                        } else if (resultData && resultData.code === 500) {
                            this.$message.error('删除失败');
                        } else {
                            this.$message.warning('删除状态未知');
                        }
                    }).catch(error => {
                            console.error('Error in delete request:', error);
                            this.$message.error('删除请求失败');
                        }
                    ))
            }
        },
        mounted() {
        },
    });
</script>
</body>
</html>
