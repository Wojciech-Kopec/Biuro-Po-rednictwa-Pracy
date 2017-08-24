package dao;

import model.Candidate;
import model.JobOffer;
import org.hibernate.HibernateException;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CandidateDao extends GenericDaoImpl<Candidate> {

    public List<Candidate> findCandidatesMatchingJobOffer(JobOffer jobOffer) {
        List<Candidate> matchedCandidates = new ArrayList<>();

        for (Candidate candidate : listAll()) {
            // Create a list which will contain both list - element will occur twice
            List<String> bothListsElements = new ArrayList<>(jobOffer.getRequiredQualifications());
            bothListsElements.addAll(candidate.getExperiences());

            // Create a set which will contain both list - common element will occur once
            Set<String> matchedQualifications = new HashSet<>(jobOffer.getRequiredQualifications());
            matchedQualifications.addAll(candidate.getExperiences());

            for (String qualification : matchedQualifications) {
                bothListsElements.remove(qualification);
            }
            if (bothListsElements.size() > 2)
                matchedCandidates.add(candidate);
        }
        return matchedCandidates;
    }

    public boolean update(Candidate candidate) {
        boolean result = false;
        try {
            startOperation();
            Candidate candidateToUpdate = read("lastName", candidate.getLastName());
            candidateToUpdate.setFirstName(candidate.getFirstName());
            candidateToUpdate.setPESEL(candidate.getPESEL());
            candidateToUpdate.setCity(candidate.getCity());
            candidateToUpdate.setAddress(candidate.getAddress());
            candidateToUpdate.setContactNumber(candidate.getContactNumber());
            candidateToUpdate.setEmailAddress(candidate.getEmailAddress());
            candidateToUpdate.setExperiences((ArrayList<String>) candidate.getExperiences());
            session.update(candidateToUpdate);
            tx.commit();
            result = true;
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateUtil.closeSession(session);
        }
        return result;
    }
}


