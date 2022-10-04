package com.example.lesson_22;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class IncreaseCounter extends HttpServlet {

    private AtomicInteger counter;

    @Override
    public void init() {
        counter = new AtomicInteger(0);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        counter.incrementAndGet();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("text/html");
        try(PrintWriter writer = resp.getWriter()){
            writer.write("<h2>Number of times sending GET request: " + counter.get() + "</h2>");
            writer.write("<h2>Path info: " + req.getServletContext().getContextPath() + "</h2>");
            writer.write("<h2>Headers info: " + req.getAttributeNames() + "</h2>");
            writer.write("<h2>Method info: " + req.getMethod() + "</h2>");
            writer.write("<h2>IP info: " + req.getRemoteAddr() + "</h2>");
        }
    }
}
