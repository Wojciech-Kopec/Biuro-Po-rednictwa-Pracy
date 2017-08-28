package com.wojciech.kopec.servlet;

import com.wojciech.kopec.dao.CandidateDao;
import com.wojciech.kopec.dao.JobOfferDao;
import com.wojciech.kopec.model.Candidate;
import com.wojciech.kopec.model.JobOffer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "JobOfferServlet", urlPatterns = "/JobOfferServlet")
public class JobOfferServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String position = request.getParameter("position");
        String companyName = request.getParameter("companyName");
        String description = request.getParameter("description");
        String workingHours = request.getParameter("workingHours");
        String contractType = request.getParameter("contractType");
        String requiredYearsOfExperience = request.getParameter("requiredYearsOfExperience");
        String requiredQualifications = request.getParameter("requiredQualifications");
        String minSalary = request.getParameter("minSalary");
        String maxSalary = request.getParameter("maxSalary");

        String option = request.getParameter("option");
        JobOfferDao dao = new JobOfferDao();

        JobOffer jobOffer = null;
        List<JobOffer> jobOffers = new ArrayList<>();
        String operation = null;
        boolean result = false;
        try {
            if ("search".equals(option)) {
                jobOffer = dao.read("companyName", companyName);
                result = jobOffer != null;
                operation = "Wyszukanie Oferty Pracy";
            } else if ("add".equals(option)) {
                jobOffer = new JobOffer(position, companyName, description, Double.valueOf(workingHours), contractType, Integer.valueOf(requiredYearsOfExperience), new ArrayList<>(Arrays.asList((requiredQualifications).split("\\n"))), Integer.valueOf(minSalary), Integer.valueOf(maxSalary));
                result = dao.create(jobOffer);
                operation = "Dodanie Oferty Pracy";
            } else if ("update".equals(option)) {
                jobOffer = new JobOffer(position, companyName, description, Double.valueOf(workingHours), contractType, Integer.valueOf(requiredYearsOfExperience), new ArrayList<>(Arrays.asList((requiredQualifications).split("\\n"))), Integer.valueOf(minSalary), Integer.valueOf(maxSalary));
                result = dao.update(jobOffer);
                operation = "Modyfikacja Oferty Pracy";
            } else if ("delete".equals(option)) {
                jobOffer = new JobOffer(position, companyName, description, Double.valueOf(workingHours), contractType, Integer.valueOf(requiredYearsOfExperience), new ArrayList<>(Arrays.asList((requiredQualifications).split("\\n"))), Integer.valueOf(minSalary), Integer.valueOf(maxSalary));
                result = dao.delete(jobOffer);
                operation = "Usunięcia Oferty Pracy";
            } else if ("listAll".equals(option)) {
                jobOffers = dao.listAll();
                result = jobOffers != null;
                operation = "Pokaż Wszystkie Oferty Pracy";
            } else if ("listMatching".equals(option)) {
                CandidateDao candidateDao = new CandidateDao();
                Candidate candidate = candidateDao.read("id", request.getParameter("candidateId"));
                jobOffers = dao.findJobOffersMatchingCandidate(candidate);
                result = jobOffers != null;
                request.setAttribute("candidate", candidate);
                operation = "Znajdź Pasujące Oferty Pracy";
            } else if ("listCompanyOffers".equals(option)) {
                jobOffers = dao.listAllByAttribute("companyName", request.getParameter("companyName"));
                result = jobOffers != null;
                operation = "Znajdź Oferty Firmy";
            }
            if (jobOffer != null)
                jobOffers.add(jobOffer);
            if (jobOffers != null && result) {
                request.setAttribute("option", operation);
                request.setAttribute("jobOffers", jobOffers);
                request.getRequestDispatcher("jobOfferResults.jsp").forward(request, response);
            }
        } catch (RuntimeException e) {
            dao.handleException(e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            dao.closeSession();
        }
    }

}
