package com.pedro.ejercicios.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/crear")
public class RegistroProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Map<String,String> errores = new HashMap<>();
        String producto = req.getParameter("producto");
        String fabricante = req.getParameter("fabricante");
        String categorias = req.getParameter("categorias");
        Integer precio = null;
        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        }catch (NumberFormatException e){}


        if(producto == null || producto.isBlank()) {

            errores.put("producto","El producto no puede estar vacío!");

        }
        if(precio == null || precio<1){

            errores.put("precio","El precio no puede ser negativo o ir vacio");

        }
        if(fabricante.isBlank() || fabricante == null){

            errores.put("fabricante","El fabricante no puede ser vacio!");

        }else if(!(fabricante.length()>=4) || !(fabricante.length()<=10)){
            errores.put("fabricante","El fabricante debe poseer entre 4 y 10 caracteres!");

        }
        if( categorias == null || categorias.isBlank()){
            errores.put("categorias","Debe seleccionar una categoría!");
        }

        if(errores.isEmpty()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("   <meta charset='UTF-8'>");
            out.println("   <title>Registro de producto</title>");
            out.println("<body>");
            out.println("<h1>Producto registrado!</h1>");
            out.println("<ul>");
            out.println("<li>");
            out.println(producto);
            out.println("</li>");
            out.println("<li>");
            out.println(precio);
            out.println("</li>");
            out.println("<li>");
            out.println(categorias);
            out.println("</li>");
            out.println("<li>");
            out.println(fabricante);
            out.println("</li>");
            out.println("</ul>");
            out.println("</body>");
            out.println("</head>");
            out.println("</html>");
            out.println("<a href='atras/'>Volver atrás</a>".replace("atras",req.getContextPath()));
            out.close();
        }else{
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
