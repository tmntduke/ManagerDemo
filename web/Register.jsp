<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<%
    String path = request.getContextPath();
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册</title>
    <!-- Include the FontAwesome CSS if you want to use feedback icons provided by FontAwesome -->
    <link rel="stylesheet" href="http://libs.useso.com/js/font-awesome/4.2.0/css/font-awesome.min.css" />
    <%--<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.css"/>--%>
    <%--<link rel="stylesheet" href="dist/css/formValidation.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="css/default.css">--%>
    <%--<script type="text/javascript" src="src/js/jquery-1.7.2.min.js"></script>--%>
	<%--<script type="text/javascript" src="src/js/jquery.unobtrusive-ajax.min.js" ></script>--%>
    <link href="<%=path %>/Script/boopstat/vendor/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="<%=path %>/Script/boopstat/dist/css/formValidation.css" rel="stylesheet"/>
    <link href="<%=path %>/Script/boopstat/css/default.css" rel="stylesheet"/>
     <script>
         var xmlHttp;
         var flag=false;
         function createXNLHttp(){
             if (window.XMLHttpRequest){
                 xmlHttp=new XMLHttpRequest();
             }
             else {
               xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
             }
         }
         function checkUsername(username){
             createXNLHttp();
             var url="/Manager/CheckServlet?username="+username;
             url=encodeURI(url);
             xmlHttp.open("POST",url,true);
             xmlHttp.onreadystatechange=checkUsernameCallback;
             xmlHttp.send(null);
         }
         function checkUsernameCallback(){
             var str="error";
             if (xmlHttp.readyState==4){
                 if (xmlHttp.status==200){
                     var text=xmlHttp.responseText;
                     if (str==text){
                         alert("用户名已存在");
                     }
                     else{
                         alert("ok");
                         flag=true;
                     }
                 }
             }
         }
         <%--<%--%>
          <%--String fail=(String) request.getAttribute("fail");--%>
        <%--%>--%>
         <%--function submit(){--%>
             <%--alert(<%=fail%>>);--%>
             <%--return flag;--%>
         <%--}--%>

     </script>
</head>
<body>
<% request.setCharacterEncoding("utf-8");
 response.setCharacterEncoding("utf-8");
%>

<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
            <div class="page-header">
                <h2>Bootstrap Form</h2>
            </div>
            <form id="defaultForm" method="post" class="form-horizontal" action="/Manager/RegisterServlet ">
                <div class="form-group">
                    <label class="col-sm-3 control-label">Full name</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="firstName" placeholder="First name" />
                    </div>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="lastName" placeholder="Last name" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">Username</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="username" onblur="checkUsername(this.value)"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">Email address</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="email" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">Password</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" name="password" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">Gender</label>
                    <div class="col-sm-6">
                        <div class="radio">
                            <label>
                                <input type="radio" name="gender" value="male" /> Male
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" name="gender" value="female" /> Female
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" name="gender" value="other" /> Other
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label" id="captchaOperation"></label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="captcha" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-6 col-sm-offset-3">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="agree" value="agree" /> Agree with the terms and conditions
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <button type="submit" class="btn btn-primary" name="signup" value="Sign up">Submit</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>

</div>

<%--<script type="text/javascript" src="vendor/jquery/jquery.min.js"></script>--%>
<%--<script type="text/javascript" src="vendor/bootstrap/js/bootstrap.min.js"></script>--%>
<%--<script type="text/javascript" src="dist/js/formValidation.js"></script>--%>
<%--<script type="text/javascript" src="dist/js/framework/bootstrap.js"></script>--%>
<%--<script type="text/javascript" src="dist/js/language/zh_CN.js"></script>--%>
<script src="<%=path%>/Script/boopstat/vendor/jquery/jquery.min.js" type="text/javascript"></script>
<script src="<%=path%>/Script/boopstat/vendor/bootstrap/js/bootstrap.min.js"  type="text/javascript"></script>
<script src="<%=path%>/Script/boopstat/dist/js/formValidation.js"  type="text/javascript"></script>
<script src="<%=path%>/Script/boopstat/dist/js/framework/bootstrap.js"  type="text/javascript"></script>
<script src="<%=path%>/Script/boopstat/dist/js/language/zh_CN.js"  type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        // Generate a simple captcha
        function randomNumber(min, max) {
            return Math.floor(Math.random() * (max - min + 1) + min);
        };
        $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));

        $('#defaultForm').formValidation({
            message: 'This value is not valid',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                firstName: {
                    row: '.col-sm-4',
                    validators: {
                        notEmpty: {
                            message: 'The first name is required'
                        }
                    }
                },
                lastName: {
                    row: '.col-sm-4',
                    validators: {
                        notEmpty: {
                            message: 'The last name is required'
                        }
                    }
                },
                username: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '用户名必须设置'
                        },
                        check:{
                            message: '用户名已存在'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '用户名必须在6-30个字符之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: 'The username can only consist of alphabetical, number, dot and underscore'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '电子邮件不能为空'
                        },
                        emailAddress: {
                            message: '请输入有效的电子邮件地址'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        different: {
                            field: 'username',
                            message: '密码不能和用户名相同'
                        }
                    }
                },
                gender: {
                    validators: {
                        notEmpty: {
                            message: '必须选择一个性别'
                        }
                    }
                },
                captcha: {
                    validators: {
                        callback: {
                            message: 'Wrong answer',
                            callback: function(value, validator, $field) {
                                var items = $('#captchaOperation').html().split(' '), sum = parseInt(items[0]) + parseInt(items[2]);
                                return value == sum;
                            }
                        }
                    }
                },
                agree: {
                    validators: {
                        notEmpty: {
                            message: 'You must agree with the terms and conditions'
                        }
                    }
                }
            }
        });
    });
</script>
</body>
</html>