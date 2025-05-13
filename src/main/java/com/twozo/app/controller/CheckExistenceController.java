package com.twozo.app.controller;

import com.twozo.app.model.Customer;
import com.twozo.app.model.Payment;
import com.twozo.app.model.Vendor;
import com.twozo.app.service.CheckExistenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/find")
public class CheckExistenceController {
    private final CheckExistenceService checkExistenceService;

    public CheckExistenceController(final CheckExistenceService checkExistenceService){
        this.checkExistenceService = checkExistenceService;
    }

    @PostMapping("/customer")
 // ResponseEntity -  status code, headers, and body
     public ResponseEntity<Customer> findCustomerById(@RequestParam final String phoneNo){
        try{
            final Customer data = checkExistenceService.checkCustomer(phoneNo);
            if (data != null) {
                return ResponseEntity.ok(data);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/vendor")
    public ResponseEntity<Vendor> findVendorById(@RequestParam final String phoneNo){
        try {
            final Vendor data = checkExistenceService.checkVendor(phoneNo);
            if (data != null) {
                return ResponseEntity.ok(data);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<Payment> findPaymentById(@RequestParam final int id){
        try {
            final Payment data = checkExistenceService.checkExistence(id);
            if (data != null) {
                return ResponseEntity.ok(data);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
