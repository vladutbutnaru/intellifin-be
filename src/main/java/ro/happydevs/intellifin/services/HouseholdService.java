package ro.happydevs.intellifin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.Household;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.repositories.HouseholdRepository;
import ro.happydevs.intellifin.repositories.TokenRepository;

import java.util.ArrayList;

public class HouseholdService {

    private static Logger logger = LoggerFactory.getLogger(HouseholdService.class);
    private HouseholdRepository householdRepository = new HouseholdRepository();
    private TokenRepository tokenRepository = new TokenRepository();


    //creates Household
    public boolean createHousehold(Household household){

        logger.info("[HouseholdService Create] - Called");
            return householdRepository.create(household);

    }

    //adds individual member to household(without being the household's admin)
    public boolean addMembertoHousehold(Household household){

        logger.info("[HouseholdService addMembertoHousehold] - Called");
        return householdRepository.addMemberToHousehold(household);

    }

    //deletes individual member to household(without being the household's admin)
    public boolean deleteMemberFromHousehold(Household household){

        logger.info("[HouseholdService addMembertoHousehold] - Called");
        return householdRepository.deleteMemberFromHousehold(household);

    }


    //deletes Household
    public boolean deleteHousehold(String token , int idHousehold){
        User u = tokenRepository.getUserByToken(token);
        logger.info("[Household Service Delete] - Called");
        return householdRepository.delete(idHousehold);

    }

    public ArrayList<Household> getHouseholdMembers(int idHousehold) {
        logger.info("[Householdservice getHouseholdMembers] - Called");
        return (ArrayList<Household>) householdRepository.getHouseholdMembers(idHousehold);
    }

    //list of all household members in the system
    public ArrayList<Household> getAll(){
        logger.info("[Householdservice getALL] - Called");
        return (ArrayList<Household>) householdRepository.getAll();
    }





}
