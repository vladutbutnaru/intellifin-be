package ro.happydevs.intellifin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.Household;
import ro.happydevs.intellifin.models.business.HouseholdMember;
import ro.happydevs.intellifin.models.business.User;
import ro.happydevs.intellifin.models.dto.GenericMessageDTO;
import ro.happydevs.intellifin.repositories.HouseholdMemberRepository;
import ro.happydevs.intellifin.repositories.HouseholdRepository;
import ro.happydevs.intellifin.utils.reporting.IntelliLogger;

import java.util.Arrays;
import java.util.List;

@Service
public class HouseholdService {


    @Autowired
    HouseholdRepository householdRepository;

    @Autowired
    HouseholdMemberRepository householdMemberRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    NotificationService notificationService;

    private static Logger logger = LoggerFactory.getLogger(HouseholdService.class);

    @Autowired
    IntelliLogger intelliLogger;

    public GenericMessageDTO inviteMemberToHousehold(String token, Long userToInviteId){
        //if the invited user is not already in the household

        if(isUserAlreadyInHousehold(token,userToInviteId)){
            return new GenericMessageDTO(-1,"Utilizatorul este deja in household",false);
        }

        if(notificationService.isUserAlreadyInvitedToHousehold(token,tokenService.getUserByToken(token).getId()))
            return new GenericMessageDTO(-2,"Utilizatorul este deja invitat in household",false);

        //create invitation notification

        //get user by token
        User loggedInUser =tokenService.getUserByToken(token);
        //find user as household member
        HouseholdMember loggedInMember = householdMemberRepository.findHouseholdMemberByUserId(loggedInUser.getId());

        //find household
        Household userHousehold = householdRepository.findById(loggedInMember.getHouseholdId()).get();

        //create notification
        notificationService.createInvitationToHouseholdNotification(userToInviteId,loggedInUser,userHousehold.getId());

        return new GenericMessageDTO(1,"Invitatia a fost trimisa cu succes!",true);

    }

    public boolean isUserAlreadyInHousehold(String token, Long userToInviteId){
        //get user by token
        User loggedInUser =tokenService.getUserByToken(token);
        //find user as household member
        HouseholdMember loggedInMember = householdMemberRepository.findHouseholdMemberByUserId(loggedInUser.getId());

        //find household
        Household userHousehold = householdRepository.findById(loggedInMember.getHouseholdId()).get();

        for(HouseholdMember member : householdMemberRepository.findHouseholdMembersForHouseholdId(userHousehold.getId())){
            if(member.getUserId() == userToInviteId){
            return true;
            }

        }
        return false;
    }

    public GenericMessageDTO acceptInviteToHousehold(String token, Long houseHoldId){
        //find the user
        User loggedInUser = tokenService.getUserByToken(token);

        //associate the user to the household
        HouseholdMember houseHoldMember = new HouseholdMember();
        houseHoldMember.setDeleted(false);
        houseHoldMember.setHouseholdId(houseHoldId);
        houseHoldMember.setUserId(loggedInUser.getId());
        householdMemberRepository.save(houseHoldMember);

        //delete the invite notification

        notificationService.deleteHouseholdInviteNotification(token,houseHoldId);

        return new GenericMessageDTO(1,"Accepted",true);

    }

    public GenericMessageDTO createHousehold(String token, Household household){
        User loggedInUser = tokenService.getUserByToken(token);
        household.setDeleted(false);
        household = householdRepository.save(household);

        HouseholdMember householdMember = new HouseholdMember();
        householdMember.setUserId(loggedInUser.getId());
        householdMember.setDeleted(false);
        householdMember.setHouseholdId(household.getId());

        householdMemberRepository.save(householdMember);

        return new GenericMessageDTO(1,"Household created",true);


    }

    public List<Household> listHouseholds(String token){

        //get user by token
        User loggedInUser =tokenService.getUserByToken(token);
        //find user as household member
        HouseholdMember loggedInMember = householdMemberRepository.findHouseholdMemberByUserId(loggedInUser.getId());

        //find household
        try {
            Household userHousehold = householdRepository.findById(loggedInMember.getHouseholdId()).get();
            return Arrays.asList(userHousehold);
        }
        catch(Exception e){
            return null;
        }


    }
}
