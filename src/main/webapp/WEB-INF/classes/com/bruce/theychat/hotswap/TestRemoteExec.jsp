<%@ page import="java.lang.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.bruce.theychat.hotswap.*"%>
<%
    InputStream is=new FileInputStream("c:/Demo.class");
    byte[] b=new byte[is.available()];
    is.read(b);
    is.close();
    out.println("<textarea style='width:1000;height=800'>");
    out.println(JavaClassExecutor.execute(b));
    out.println("</textarea>");
%>