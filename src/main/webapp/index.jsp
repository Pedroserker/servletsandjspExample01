<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Map"%>
<%
    Map<String,String> errores = (Map<String, String>) request.getAttribute("errores");
%>
<html>
<head>
    <title>Registro de productos</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<h1 class="title-h1">Registro de productos</h1>
<%if(errores != null && errores.size()>0){%>
<ol>
    <%for(String error:errores.values()){%>
    <li class="errors"><%=error%></li>
    <%}%>
</ol>
<%}%>
<form action="<%=request.getContextPath()%>/crear" method="post">
    <fieldset class="fieldset">
        <legend>Datos del producto</legend>

        <label for="producto">Nombre:</label>
        <input type="text" name="producto" id="producto" value="${param.producto}">
        <%if(errores != null && errores.containsKey("producto")) {
            out.println("<small class='small-error'>" + errores.get("producto") + "</small>");
        }
        %>
        <label for="precio">Precio:</label>
        <input type="number" name="precio" id="precio" value="${param.precio}">
        <%if(errores != null && errores.containsKey("precio")) {
            out.println("<small class='small-error'>" + errores.get("precio") + "</small>");
        }
        %>
        <label for="fabricante">Fabricante:</label>
        <input type="text" name="fabricante" id="fabricante" value="${param.fabricante}">
        <%if(errores != null && errores.containsKey("fabricante")) {
            out.println("<small class='small-error'>" + errores.get("fabricante") + "</small>");
        }
        %>
        <label for="categorias">Categorías:</label>
        <select name="categorias" id="categorias">
            <option selected disabled>--Selecione una catagoría--</option>
            <option value="electrdom" ${param.equals("electrdom")?"selected":""}>Electródomestico</option>
            <option value="muebl" ${param.equals("muebl")?"selected":""}>Muebles</option>
            <option value="other" ${param.equals("other")?"selected":""}>Otros</option>
        </select>
        <%if(errores != null && errores.containsKey("categorias")) {
            out.println("<small class='small-error'>" + errores.get("categorias") + "</small>");
        }
        %>
        <input type="submit" name="Enviar" class="btn">
    </fieldset>
</form>
</body>
</html>
