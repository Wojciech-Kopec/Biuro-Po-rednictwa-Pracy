package servlet;

import dao.CandidateDao;
import dao.JobOfferDao;
import model.Candidate;
import model.JobOffer;
import util.HibernateUtil;

import java.io.IOException;
import java.math.BigInteger;
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
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String PESEL = request.getParameter("PESEL");
        String sex = request.getParameter("sex");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String contactNumber = request.getParameter("contactNumber");
        String emailAddress = request.getParameter("emailAddress");
        String experiences = request.getParameter("experiences");

        String option = request.getParameter("option");
        CandidateDao dao = new CandidateDao();

        Candidate candidate = null;
        List<Candidate> candidates = new ArrayList<>();
        String operation = null;
        boolean result = false;
        try {
            if ("search".equals(option)) {
                candidate = dao.read("lastName", lastName);
                result = candidate != null;
                operation = "Wyszukaj";
            } else if ("add".equals(option)) {
                candidate = new Candidate(firstName, lastName, new Long(PESEL), sex.charAt(0), city, address,
                        new Long(contactNumber), emailAddress, new ArrayList<>(Arrays.asList((experiences).split("\\n"))));
                result = dao.create(candidate);
                operation = "Dodaj Osobę";
            } else if ("update".equals(option)) {
                candidate = dao.read("lastName", lastName);
                request.setAttribute("candidate", candidate);
                request.getRequestDispatcher("candidateModify.jsp").forward(request, response);
                return;
            } else if ("modify".equals(option)) {
                candidate = new Candidate(firstName, lastName, new Long(PESEL), sex.charAt(0), city, address,
                        new Long(contactNumber), emailAddress, new ArrayList<>(Arrays.asList((experiences).split("\\n"))));
                result = dao.update(candidate);
                operation = "Modyfikuj Osobę";
            } else if ("delete".equals(option)) {
                candidate = new Candidate(firstName, lastName, new Long(PESEL), sex.charAt(0), city, address,
                        new Long(contactNumber), emailAddress, new ArrayList<>(Arrays.asList((experiences).split("\\n"))));
                result = dao.delete(candidate);
                operation = "Usuń Osobę";
            } else if ("listAll".equals(option)) {
                candidates = dao.listAll();
                result = candidates != null;
                operation = "Pokaż Wszystkie Osoby";
            } else if("listMatching".equals(option)) {
                JobOfferDao jobOfferDao = new JobOfferDao();
                JobOffer jobOffer = jobOfferDao.read("id", request.getParameter("jobOfferId"));
                candidates = dao.findCandidatesMatchingJobOffer(jobOffer);
                result = candidates != null;
                request.setAttribute("jobOffer", jobOffer);
                operation = "Znajdź Potencjalnych Pracowników";
            }
            if (candidate != null)
                candidates.add(candidate);
            if (candidates != null && result) {
                request.setAttribute("option", operation);
                request.setAttribute("candidates", candidates);
                request.getRequestDispatcher("candidateResults.jsp").forward(request, response);
            }
        } catch (RuntimeException e) {
            dao.handleException(e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            dao.closeSession();
        }
    }
}
