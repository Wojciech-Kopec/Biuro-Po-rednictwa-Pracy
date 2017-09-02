package com.wojciech.kopec.servlet;

import com.wojciech.kopec.dao.CandidateDao;
import com.wojciech.kopec.dao.JobOfferDao;
import com.wojciech.kopec.model.Candidate;
import com.wojciech.kopec.model.JobOffer;
import jdk.nashorn.internal.scripts.JO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    /* Variables used across methods regarding JobOffer object */
    private JobOfferDao dao = new JobOfferDao();
    private String position;
    private String companyName;
    private String description;
    private String workingHours;
    private String contractType;
    private String requiredYearsOfExperience;
    private String requiredQualifications;
    private String minSalary;
    private String maxSalary;
    private String option;

    /* Variables regarding Candidate object used for Candidate & JobOffer models crossover operations */
    private String candidateId;
    private Candidate candidate;

    /* Variables regarding Forward-related processes  */
    private JobOffer jobOffer;
    private List<JobOffer> jobOffers;
    private boolean result = false;
    private String operation;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        getRequestParameters(request);
        jobOffers = new ArrayList<>();

        try {
            executeDaoOperation();
            setRequestAttributes(request,response);
        } catch (RuntimeException e) {
            dao.handleException(e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            dao.closeSession();
        }
    }

    private void executeDaoOperation() throws ServletException, IOException {
        if ("search".equals(option)) {
            searchBy();
        } else if ("add".equals(option)) {
            addNewEntity();
        } else if ("update".equals(option)) {
            updateEntity();
        } else if ("delete".equals(option)) {
            deleteEntity();
        } else if ("listAll".equals(option)) {
            getAllEntities();
        } else if ("listMatching".equals(option)) {
            getAllJobOffersMatchingCandidate();
        } else if ("listCompanyOffers".equals(option)) {
            getAllJobOffersMatchingCompany();
        }
    }

    private void getAllJobOffersMatchingCompany() {
        jobOffers = dao.listAllByAttribute("NAZWA", companyName);
        result = jobOffers != null;
        operation = "Znajdź Oferty Firmy";
    }

    private void getAllJobOffersMatchingCandidate() {
        CandidateDao candidateDao = new CandidateDao();
        Candidate candidate = candidateDao.read("ID", candidateId);
        jobOffers = dao.findJobOffersMatchingCandidate(candidate);
        result = jobOffers != null;
        operation = "Znajdź Pasujące Oferty Pracy";
    }

    private void getAllEntities() {
        jobOffers = dao.listAll();
        result = jobOffers != null;
        operation = "Pokaż Wszystkie Oferty Pracy";
    }

    private void deleteEntity() {
        jobOffer = new JobOffer(position, companyName, description, Double.valueOf(workingHours), contractType, Integer.valueOf(requiredYearsOfExperience), new ArrayList<>(Arrays.asList((requiredQualifications).split("\\n"))), Integer.valueOf(minSalary), Integer.valueOf(maxSalary));
        result = dao.delete(jobOffer);
        operation = "Usunięcia Oferty Pracy";
    }

    private void updateEntity() {
        jobOffer = new JobOffer(position, companyName, description, Double.valueOf(workingHours), contractType, Integer.valueOf(requiredYearsOfExperience), new ArrayList<>(Arrays.asList((requiredQualifications).split("\\n"))), Integer.valueOf(minSalary), Integer.valueOf(maxSalary));
        result = dao.update(jobOffer);
        operation = "Modyfikacja Oferty Pracy";
    }

    private void addNewEntity() {
        jobOffer = new JobOffer(position, companyName, description, Double.valueOf(workingHours), contractType, Integer.valueOf(requiredYearsOfExperience), new ArrayList<>(Arrays.asList((requiredQualifications).split("\\n"))), Integer.valueOf(minSalary), Integer.valueOf(maxSalary));
        result = dao.create(jobOffer);
        operation = "Dodanie Oferty Pracy";
    }

    private void setRequestAttributes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("listMatching".equals(option)) {
            request.setAttribute("candidate", candidate);
        }
        if (jobOffer != null)
            jobOffers.add(jobOffer);
        if (jobOffers != null && result) {
            request.setAttribute("option", operation);
            request.setAttribute("jobOffers", jobOffers);
            request.getRequestDispatcher("jobOfferResults.jsp").forward(request, response);
        }
    }

    private void searchBy() {
        jobOffer = dao.read("NAZWA", companyName);
        result = jobOffer != null;
        operation = "Wyszukanie Oferty Pracy";
    }

    private void getRequestParameters(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        position = request.getParameter("position");
        companyName = request.getParameter("companyName");
        description = request.getParameter("description");
        workingHours = request.getParameter("workingHours");
        contractType = request.getParameter("contractType");
        requiredYearsOfExperience = request.getParameter("requiredYearsOfExperience");
        requiredQualifications = request.getParameter("requiredQualifications");
        minSalary = request.getParameter("minSalary");
        maxSalary = request.getParameter("maxSalary");
        option = request.getParameter("option");
        candidateId = request.getParameter("candidateId");
    }

}
