package com.wojciech.kopec.dao;

import com.wojciech.kopec.model.Candidate;
import com.wojciech.kopec.model.JobOffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JobOfferDao extends GenericDaoImpl<JobOffer> {

    public List<JobOffer> findJobOffersMatchingCandidate(Candidate candidate) {
        List<JobOffer> matchedJobOffers = new ArrayList<>();

        for (JobOffer jobOffer : listAll()) {
            // Create a list which will contain both list - element will occur twice
            List<String> bothListsElements = new ArrayList<>(candidate.getExperiences());
            bothListsElements.addAll(jobOffer.getRequiredQualifications());

            // Create a set which will contain both list - common element will occur once
            Set<String> matchedQualifications = new HashSet<>(candidate.getExperiences());
            matchedQualifications.addAll(jobOffer.getRequiredQualifications());

            for(String qualification : matchedQualifications) {
                bothListsElements.remove(qualification);
            }
            if (bothListsElements.size() > 2)
                matchedJobOffers.add(jobOffer);
        }
        return matchedJobOffers;
    }
}
