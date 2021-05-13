package com.surjo.admin.controller;

import com.surjo.admin.model.Partner;
import com.surjo.admin.service.PartnerService;
import com.surjo.security.core.CustomSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/partner",produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnerController {

    private final PartnerService partnerService;
    private final CustomSession customSession;

    public PartnerController(PartnerService partnerService, CustomSession customSession) {
        this.partnerService = partnerService;
        this.customSession = customSession;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePartner(@Valid @RequestBody Partner partner) throws Exception {
        return ResponseEntity.ok(partnerService.createPartner(partner));
    }

    @RequestMapping(path ="/active" ,method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> partnerActive(@Valid @RequestBody Partner partner) throws Exception {
        return ResponseEntity.ok(partnerService.partnerActive(partner));
    }

    @GetMapping
    public ResponseEntity getAllPartner() {
        List<Partner> partnerList = partnerService.getAllPartnerList();
        return ResponseEntity.ok(partnerList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getPartnerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(partnerService.getPartnerById(id));
    }
    @GetMapping(path = "/mobile")
    public ResponseEntity getPartnerByMobileNumber(@RequestParam String mobileNumber) {
        return ResponseEntity.ok(partnerService.getPartnerByMobileNumber(mobileNumber));
    }

    @GetMapping(path = "/reference-exist")
    public ResponseEntity referenceExist(@RequestParam String mobileNumber) {
        return ResponseEntity.ok(partnerService.referenceExist(mobileNumber));
    }

    @GetMapping(path = "/detail")
    public ResponseEntity getPartner() {
        return ResponseEntity.ok(partnerService.getPartnerByMobileNumber(customSession.getUsername()));
    }

    @GetMapping(path = "/child/{mobilNumber}")
    public ResponseEntity getChild(@PathVariable("mobilNumber") String mobilNumber) {
        return ResponseEntity.ok(partnerService.getPartnerByParent(mobilNumber));
    }

    @GetMapping(path = "/target/{mobilNumber}")
    public ResponseEntity getTargetByPartner(@PathVariable("mobilNumber") String mobilNumber) {
        return ResponseEntity.ok(partnerService.getTargetByPartner(mobilNumber));
    }

    @DeleteMapping
    public ResponseEntity deletePartner(@RequestParam(name="id",required = true)Long id) {
        partnerService.deletePartner(id);
        return ResponseEntity.accepted().build();
    }
}
