<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 
<html>
<head>
    <title>UEditor测试</title>
    <script type="text/javascript" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <link rel="stylesheet" type="text/css" href="ueditor/themes/default/css/ueditor.css">
</head>
<body>
<div>
   
    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
</div>
<script type="text/javascript">
 
    var ue = UE.getEditor('editor');
</script>
</body>
</html>