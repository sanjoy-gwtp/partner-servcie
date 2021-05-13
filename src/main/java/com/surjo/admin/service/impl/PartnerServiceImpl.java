package com.surjo.admin.service.impl;

import com.surjo.admin.entity.PartnerEntity;
import com.surjo.admin.entity.PartnerStatus;
import com.surjo.admin.exception.ChildLimitException;
import com.surjo.admin.exception.ReferenceNoFundException;
import com.surjo.admin.model.*;
import com.surjo.admin.repository.PartnerRepository;
import com.surjo.admin.service.PartnerService;
import com.surjo.admin.service.ProfileService;
import com.surjo.admin.service.SmsNotificationService;
import com.surjo.admin.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final ProfileService profileService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final SmsNotificationService smsNotificationService;

    public PartnerServiceImpl(PartnerRepository partnerRepository, ProfileService profileService, ModelMapper modelMapper, UserService userService, SmsNotificationService smsNotificationService) {
        this.partnerRepository = partnerRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.smsNotificationService = smsNotificationService;
    }

    @Transactional
    public Partner createPartner(Partner partner) throws Exception {
        check(partner);
        PartnerEntity parent=partnerRepository.findPartnerByMobileNumber(partner.getReferenceNumber());
        partner.setLevel(parent.getLevel() + 1);
        partner.setParent(parent.getId());
        if (partner.getId() == null||partner.getId()<1) {
            createUser(partner);
            partner.setAccountBalance(BigDecimal.ZERO);
            partner.setPurchaseBalance(BigDecimal.ZERO);
            partner.setLeanBalance(BigDecimal.ZERO);
            partner.setPartnerStatus(PartnerStatus.INACTIVE);
            partner.setPosition((long)(partnerRepository.findPartnerByReferenceNumber(partner.getReferenceNumber()).size() + 1));
            partner.setLevelPosition(((parent.getLevelPosition()-1)*4)+partner.getPosition());

            Profile profile=new Profile();
            profile.setUserId(partner.getMobileNumber());
            profile.setPosition(partner.getPosition().intValue());
            profile.setDealerArea(partner.getDealerArea());
            profileService.createProfile(profile);
        }
        PartnerEntity partnerEntity=partnerRepository.save(modelMapper.map(partner, PartnerEntity.class));
        sentMessage(partnerEntity,partner.getPassword());
        return modelMapper.map(partnerEntity,Partner.class);
    }

    @Transactional
    public Partner partnerActive(Partner partner) {
        PartnerEntity partnerEntity=partnerRepository.getOne(partner.getId());
        if(partnerEntity.getPartnerStatus().equals(PartnerStatus.INACTIVE)){
            partnerEntity.setPartnerStatus(PartnerStatus.ACTIVE);
            //partnerCommission(partnerEntity);
        }
        return modelMapper.map(partnerEntity,Partner.class);
    }

    @Override
    public Boolean referenceExist(String mobileNumber) {
        PartnerEntity partnerEntity=partnerRepository.findPartnerByMobileNumber(mobileNumber);
        return partnerEntity != null && partnerEntity.getPartnerStatus().equals(PartnerStatus.ACTIVE);
    }

    private void partnerCommission(PartnerEntity partnerEntity) {
        if(partnerEntity.getLevelPosition()%4==0){
            PartnerEntity parent=partnerRepository.findPartnerByMobileNumber(partnerEntity.getReferenceNumber());
            int level=partnerEntity.getLevel().intValue();
            int count=0;
            while (level>0){
                if(count!=0) {
                    parent = partnerRepository.findPartnerByMobileNumber(parent.getReferenceNumber());
                }
                level=level-1;
                count=count+1;
                if(count==1){
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(10)));
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(10)));
                    if(partnerEntity.getLevelPosition().intValue()!=Math.pow(4,partnerEntity.getLevel())){
                        break;
                    }
                }else if(count==2){
                    double amount=3*Math.pow(4,partnerEntity.getLevel());
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(amount/2)));
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(amount/2)));
                }else if(count==3){
                    double amount=2*Math.pow(4,partnerEntity.getLevel());
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(amount/2)));
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(amount/2)));
                }else {
                    double amount=1*Math.pow(4,partnerEntity.getLevel());
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(amount/2)));
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(amount/2)));
                }
            }
        }
    }

    private void partnerCommissionWhenFirstPaymentApproved(PartnerEntity partnerEntity) {

//        List<PartnerEntity> partnerEntityList=partnerRepository.findPartnerByReferenceNumber(partnerEntity.getReferenceNumber());
//        if(partnerEntityList.size()==4){
//            partnerEntity.setAccountBalance(partnerEntity.getAccountBalance().add(BigDecimal.valueOf(10)));
//            partnerEntity.setPurchaseBalance(partnerEntity.getPurchaseBalance().add(BigDecimal.valueOf(10)));
//        }


        if(partnerRepository.findPartnerByReferenceNumber(partnerEntity.getReferenceNumber()).size()==4){
            PartnerEntity parent=partnerRepository.findPartnerByMobileNumber(partnerEntity.getReferenceNumber());
            int level=partnerEntity.getLevel().intValue();
            int count=0;
            while (level>0){
                if(count!=0) {
                    parent = partnerRepository.findPartnerByMobileNumber(parent.getReferenceNumber());
                }
                level=level-1;
                count=count+1;
                if(count==1){
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(10)));
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(10)));
                    if(partnerEntity.getLevelPosition().intValue()!=Math.pow(4,partnerEntity.getLevel())){
                        break;
                    }
                }else if(count==2){
                    double amount=3*Math.pow(4,partnerEntity.getLevel());
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(amount/2)));
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(amount/2)));
                }else if(count==3){
                    double amount=2*Math.pow(4,partnerEntity.getLevel());
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(amount/2)));
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(amount/2)));
                }else {
                    double amount=1*Math.pow(4,partnerEntity.getLevel());
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(amount/2)));
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(amount/2)));
                }
            }
        }
    }

    private void check(Partner partner) throws Exception{
        if(partnerRepository.findPartnerByMobileNumber(partner.getReferenceNumber())==null) {
            throw new ReferenceNoFundException();
        }
       if(partnerRepository.findPartnerByReferenceNumber(partner.getReferenceNumber()).size()>=4){
           throw new ChildLimitException();
       }
    }

    @Override
    public List<Partner> getAllPartnerList() {
        return partnerRepository.findAll().stream().map(partnerEntity ->
                modelMapper.map(partnerEntity,Partner.class)).collect(Collectors.toList());
    }

    @Override
    public Partner getPartnerById(Long id) {
        return modelMapper.map(partnerRepository.getOne(id),Partner.class);
    }

    @Override
    public void deletePartner(Long id) {
        partnerRepository.delete(partnerRepository.getOne(id));
    }

    @Override
    public Partner getPartnerByMobileNumber(String mobileNumber) {
        PartnerEntity partnerEntity= partnerRepository.findPartnerByMobileNumber(mobileNumber);
        if(partnerEntity==null){
            return new Partner();
        }
        return modelMapper.map(partnerEntity,Partner.class);
    }

    @Override
    public List<Partner> getPartnerByParent(String mobilNumber) {
        return partnerRepository.findPartnerByReferenceNumber(mobilNumber)
                .stream().map(partnerEntity -> modelMapper.map(partnerEntity,Partner.class)).collect(Collectors.toList());
    }

    @Override
    public List<Target> getTargetByPartner(String mobileNumber) {
        List<Target> targets=new ArrayList<>();
        for(int i=0;i<10;i++){
            Target target=new Target();
            target.setLevel(i+1);
            target.setTarget(targets.size()==0?4:targets.get(i-1).getTarget()*4);
            if(target.getLevel()==1){
                target.setPartnerEntityList(partnerRepository.findPartnerByReferenceNumber(mobileNumber));
                target.setAchievement((long)target.getPartnerEntityList().size());
            }else {
               Target currentTarget=getAchievementByMobile(targets.get(i-1));
               target.setPartnerEntityList(currentTarget.getPartnerEntityList());
               target.setAchievement(currentTarget.getAchievement());
            }
            float per=(target.getAchievement()*100)/target.getTarget();
            target.setAchievementInPercentage(per);
            targets.add(target);
        }
        return targets;
    }

    private Target getAchievementByMobile(Target target){
        Target currentTarget=new Target();
         List<PartnerEntity> partnerEntityList=new ArrayList<>();
         for(PartnerEntity parent:target.getPartnerEntityList()){
             partnerEntityList.addAll(partnerRepository.findPartnerByReferenceNumber(parent.getMobileNumber()));
         }
        currentTarget.setPartnerEntityList(partnerEntityList);
        currentTarget.setAchievement((long)partnerEntityList.size());
     return currentTarget;
    }

//    private int getAchievementByMobile(String mobileNumber,int level){
//        int count=0;
//        switch (level){
//            case 1:
//                count=partnerRepository.findPartnerByReferenceNumber(mobileNumber).size();
//                break;
//            case 2:
//                for(PartnerEntity parent:partnerRepository.findPartnerByReferenceNumber(mobileNumber)){
//                    count=count+ partnerRepository.findPartnerByReferenceNumber(parent.getMobileNumber()).size();
//                }
//               break;
//
//            case 3:
//                for(PartnerEntity parent:partnerRepository.findPartnerByReferenceNumber(mobileNumber)) {
//                    for (PartnerEntity child : partnerRepository.findPartnerByReferenceNumber(parent.getMobileNumber())) {
//                        count = count + partnerRepository.findPartnerByReferenceNumber(child.getMobileNumber()).size();
//                    }
//                }
//                break;
//
//            case 4:
//                for(PartnerEntity parent:partnerRepository.findPartnerByReferenceNumber(mobileNumber)) {
//                    for (PartnerEntity child : partnerRepository.findPartnerByReferenceNumber(parent.getMobileNumber())) {
//                        for (PartnerEntity child1 : partnerRepository.findPartnerByReferenceNumber(child.getMobileNumber())) {
//                            count = count + partnerRepository.findPartnerByReferenceNumber(child1.getMobileNumber()).size();
//                        }
//                    }
//                }
//                break;
//        }
//       return count;
//    }

    private void createUser(Partner partner){
        UserModel userModel=new UserModel();
        userModel.setUserName(partner.getMobileNumber());
        userModel.setEmail(partner.getEmail());
        userModel.setFirstName(partner.getCustomerName());
        userModel.setLastName(partner.getCustomerName());
        userModel.setEnabled(Boolean.TRUE);
        userModel.setMobileNumber(partner.getMobileNumber());
        userModel.setRoles(Arrays.asList(new RoleModel(1L)));
        userModel.setCredential(new Credential(CredentialType.PASSWORD,"APP",partner.getPassword()));
        userModel.setClientId("partnerClient");
        userService.createUser(userModel);
    }

    private void sentMessage(PartnerEntity partnerEntity,String password){
        try {
            smsNotificationService.sendSms(partnerEntity.getMobileNumber(),
                    "Your account has been successfully."+"\n"
                            +"Name: "+partnerEntity.getCustomerName()+"\n"
                            +"ID:"+partnerEntity.getMobileNumber()+"\n"
                            +"PW:"+password+"\n"
                            +"Reference ID:"+partnerEntity.getReferenceNumber()+"\n"
                            +"www.dsell3.com");
        }catch (Exception e){
            System.out.println("Message Sent Failed!"+partnerEntity.getMobileNumber());
            e.printStackTrace();
        }
    }
}
