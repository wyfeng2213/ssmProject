<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	Cookie [] cs = request.getCookies();
	if(cs!=null){
		for(Cookie c :cs){
			out.print("key:"+c.getName()+",value:"+c.getValue()
					+"<br/>");
		}
	}else{
		out.println("没有 cookie");
	}
%>

<form action="upload" method="post" enctype="multipart/form-data">
	<input type="file" name="file"/><input type="submit" value="上传"/>
</form>
</body>
</html>