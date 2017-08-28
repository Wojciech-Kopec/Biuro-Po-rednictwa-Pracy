package com.wojciech.kopec.servlet;

import com.wojciech.kopec.dao.CandidateDao;
import com.wojciech.kopec.dao.JobOfferDao;
import com.wojciech.kopec.model.Candidate;
import com.wojciech.kopec.model.JobOffer;

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

@WebServlet(name = "CandidateServlet", urlPatterns = "/CandidateServlet")
public class CandidateServlet extends HttpServlet {

    /* Variables used across methods regarding Candidate object */
    private CandidateDao dao = new CandidateDao();
    private String firstName;
    private String lastName;
    private String PESEL;
    private String sex;
    private String city;
    private String address;
    private String contactNumber;
    private String emailAddress;
    private String experiences;
    private String option;

    /* Variables regarding JobOffer object used for Candidate & JobOffer models crossover operations */
    private String jobOfferId;
    private JobOffer jobOffer;

    /* Variables regarding Forward-related processes  */
    private Candidate candidate;
    private List<Candidate> candidates;
    private boolean result = false;
    private String operation;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getRequestParameters(request);
        candidates = new ArrayList<>();
        try {
            executeDaoOperation();
            setRequestAttributes(request, response);
        } catch (RuntimeException e) {
            dao.handleException(e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            dao.closeSession();
        }
    }

    /** Method getRequestParameters(HttpServletRequest request) is intercepting all possible request parameters
     * forwarded to CandidateServlet. Therefore, some of them might be Null. */
    private void getRequestParameters(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        PESEL = request.getParameter("PESEL");
        sex = request.getParameter("sex");
        city = request.getParameter("city");
        address = request.getParameter("address");
        contactNumber = request.getParameter("contactNumber");
        emailAddress = request.getParameter("emailAddress");
        experiences = request.getParameter("experiences");
        option = request.getParameter("option");

        jobOfferId = request.getParameter("jobOfferId");
    }

    private void executeDaoOperation() {
        if ("search".equals(option)) {
            searchBy(lastName);
        } else if ("add".equals(option)) {
            addNewEntity();
        } else if ("update".equals(option)) {
            getEntityToUpdate();
        } else if ("modify".equals(option)) {
            updateEntity();
        } else if ("delete".equals(option)) {
            deleteEntity();
        } else if ("listAll".equals(option)) {
            getAllEntities();
        } else if ("listMatching".equals(option)) {
            JobOfferDao jobOfferDao = new JobOfferDao();
            jobOffer = jobOfferDao.read("id", jobOfferId);
            getAllEntitiesMatching(jobOffer);
        }
    }

    /** Method setRequestAttributes(HttpServletRequest request, HttpServletResponse response) is respondible for
     * */
    private void setRequestAttributes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("update".equals(option)) {
            request.setAttribute("candidate", candidate);
            request.getRequestDispatcher("candidateModify.jsp").forward(request, response);
        } else if ("listMatching".equals(option)) {
            request.setAttribute("jobOffer", jobOffer);
        } else {
            if (candidate != null)
                candidates.add(candidate);
            if (candidates != null && result) {
                request.setAttribute("option", operation);
                request.setAttribute("candidates", candidates);
                request.getRequestDispatcher("candidateResults.jsp").forward(request, response);
            }
        }
    }

    private void searchBy(String lastName) {
        candidate = dao.read("lastName", lastName);
        result = candidate != null;
        operation = "Wyszukaj";
    }

    private void addNewEntity() {
        candidate = new Candidate(firstName, lastName, new Long(PESEL), sex.charAt(0), city, address,
                new Long(contactNumber), emailAddress, new ArrayList<>(Arrays.asList((experiences).split("\\n"))));
        result = dao.create(candidate);
        operation = "Dodaj Osobę";
    }

    private void getEntityToUpdate() {
        candidate = dao.read("lastName", lastName);

    }

    private void updateEntity() {
        candidate = new Candidate(firstName, lastName, new Long(PESEL), sex.charAt(0), city, address,
                new Long(contactNumber), emailAddress, new ArrayList<>(Arrays.asList((experiences).split("\\n"))));
        result = dao.update(candidate);
        operation = "Modyfikuj Osobę";
    }

    private void deleteEntity() {
        candidate = new Candidate(firstName, lastName, new Long(PESEL), sex.charAt(0), city, address,
                new Long(contactNumber), emailAddress, new ArrayList<>(Arrays.asList((experiences).split("\\n"))));
        result = dao.delete(candidate);
        operation = "Usuń Osobę";
    }

    private void getAllEntities() {
        candidates = dao.listAll();
        result = candidates != null;
        operation = "Pokaż Wszystkie Osoby";
    }

    private void getAllEntitiesMatching(JobOffer jobOffer) {
        candidates = dao.findCandidatesMatchingJobOffer(jobOffer);
        result = candidates != null;
        operation = "Znajdź Potencjalnych Pracowników";
    }
}