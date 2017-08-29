package com.wojciech.kopec.servlet;

import com.wojciech.kopec.dao.CompanyDao;
import com.wojciech.kopec.model.Company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CompanyServlet", urlPatterns = "/CompanyServlet")
public class CompanyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String NIP = request.getParameter("NIP");
        String REGON = request.getParameter("REGON");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String contactNumber = request.getParameter("contactNumber");
        String emailAddress = request.getParameter("emailAddress");

        String option = request.getParameter("option");
        CompanyDao dao = new CompanyDao();

        Company company = null;
        List<Company> companies = new ArrayList<>();
        String operation = null;
        boolean result = false;
        if ("search".equals(option)) {
            company = dao.read("name", name);
            result = company != null;
            operation = "search";
        } else if ("add".equals(option)) {
            company = new Company(name, NIP, REGON, city, address, contactNumber, emailAddress);
            result = dao.create(company);
            operation = "add";
        } else if ("update".equals(option)) {
            company = new Company(name, NIP, REGON, city, address, contactNumber, emailAddress);
            result = dao.update(company);
            operation = "update";
        } else if ("delete".equals(option)) {
            company = new Company(name, NIP, REGON, city, address, contactNumber, emailAddress);
            result = dao.delete(company);
            operation = "delete";
        } else if ("listAll".equals(option)) {
            companies = dao.listAll();
            result = companies != null;
            operation = "listAll";
        }
        if (company != null)
            companies.add(company);
        if (companies != null && result) {
            request.setAttribute("option", operation);
            request.setAttribute("companies", companies);
            request.getRequestDispatcher("companyResults.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}
