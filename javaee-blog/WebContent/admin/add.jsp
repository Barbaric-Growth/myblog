<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新的文章 | MyBlog</title>
<!-- Bootstrap core CSS -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="signin.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="/Blog/css/add.css" />
<script src="/Blog/js/add.js"></script>

<link rel="stylesheet" type="text/css"
	href="/Blog/ueditor/themes/default/css/ueditor.css">
</head>
<body style="background-image:url(/Blog/img/xixi.jpg); background-size:cover;background-attachment:fixed;" no-repeat>
	<div class="head_line"></div>
	<div class="container" id="main">
		<div id="title">
			<h2>
				<a href="#">MyBlog</a>
			</h2>
		</div>
		<h3><a href="#">编辑新文章</a></h3>
		<form action="/Blog/NewArticleServlet" method="post">

			<div class="info">
				<!-- title -->
				<span class="help">标题</span> <input type="text" class="form-control"
					name="title">
				<!-- time -->
				<span class="help">时间</span> <input type="text" class="form-control"
					name="time" value="${time}">
				<!-- author-->
				<span class="help">作者</span> <input type="text" class="form-control"
					name="author">
				<!-- sort -->
				<span class="help">分类</span><br />
				<c:forEach var="s" items="${sort_count}">
					<input class="btn btn-default" type="button" value="${s.key}"
						onclick="sort_click(this)"> &nbsp;					
 				</c:forEach>
				<input type="text" class="form-control" id="sort" name="sort">

				<!-- tag -->
				<span class="help">标签</span><br />
				<c:forEach var="tag" items="${all_tag}">
					<input class="btn btn-default" type="button" value="${tag.tag}"
						onclick="tags_click(this)">&nbsp;
		   		</c:forEach>
				<input type="text" class="form-control" id="tags" name="tags">
			</div>
			<span class="help1">编辑文章内容</span><br />
			<!-- content -->
			<div id="editor">
				<script type="text/javascript" src="/Blog/ueditor/ueditor.config.js"></script>
				<script type="text/javascript"
					src="/Blog/ueditor/ueditor.all.min.js"></script>
				<script type="text/javascript"
					src="/Blog/ueditor/lang/zh-cn/zh-cn.js"></script>

				<script id="editor1" type="text/plain" name="content"
					style="width: 800px; height: 450px;"></script>
				<script type="text/javascript">
					var ue = UE.getEditor('editor1')({
						pasteplain: true, /* 纯文本粘贴 */          
						autoHeightEnabled:false,/* 启用右侧滚轮,默认是true自动长高模式*/          
						toolbars: [[            
							'fullscreen', 'source', '|', 'undo', 'redo', '|',            
							'bold', 'italic', 'underline',            
							'removeformat', '|',            
							'insertorderedlist', 'insertunorderedlist',            
							'indent', '|', 'justifyleft', 'justifycenter',            
							'|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', 'insertimage',            
							'|', 'link', 'unlink'        ]]  
					
					});
				</script>
			</div>

			<br /> <input class="btn btn-default" type="submit" value="提交" />
		</form>
		<!-- container -->
	</div>
	<!-- container -->




</body>
</html>