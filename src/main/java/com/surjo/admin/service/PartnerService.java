package com.surjo.admin.service;

import com.surjo.admin.model.Order;
import com.surjo.admin.model.Partner;
import com.surjo.admin.model.Target;

import java.util.List;

public interface PartnerService {

    Partner createPartner(Partner partner) throws Exception;

    List<Partner> getAllPartnerList();

    Partner getPartnerById(Long id);

    void deletePartner(Long id);

    Partner getPartnerByMobileNumber(String mobileNumber);

    List<Partner> getPartnerByParent(String mobilNumber);

    List<Target> getTargetByPartner(String mobilNumber);

    Partner partnerActive(Partner partner);

    Boolean referenceExist(String mobileNumber);

}
