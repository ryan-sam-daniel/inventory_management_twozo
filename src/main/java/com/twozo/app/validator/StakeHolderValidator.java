package com.twozo.app.validator;

import com.twozo.app.model.Customer;
import com.twozo.app.model.Vendor;
import org.springframework.stereotype.Component;

@Component
public class StakeHolderValidator {
    public StringBuilder validateCustomer(final Customer customer){

        final StringBuilder errorMessages = new StringBuilder();

        if(customer.getPhoneNo().length() != 10){
            errorMessages.append("Length of phone should be exactly ten. ");
        }

        if(customer.getName().isEmpty()){
            errorMessages.append("Customer name is empty. ");
        }

        if(customer.getCity().isEmpty()){
            errorMessages.append("City name is empty. ");
        }

        if(customer.getPincode() == 0 || String.valueOf(customer.getPincode()).length() != 6){
            errorMessages.append("Pincode is 0 or not satisfied due to count. ");
        }

        return errorMessages;
    }

    public StringBuilder validateVendor(final Vendor vendor){
        final StringBuilder errorMessages = new StringBuilder();

        if(vendor.getPhoneNo().length() != 10){
            errorMessages.append("Length of phone should be exactly ten. ");
        }

        if(vendor.getName().isEmpty()){
            errorMessages.append("Customer name is empty. ");
        }

        if(vendor.getCity().isEmpty()){
            errorMessages.append("City name is empty. ");
        }

        if(vendor.getPincode() == 0 || String.valueOf(vendor.getPincode()).length() != 6){
            errorMessages.append("Pincode is 0 or not satisfied due to count. ");
        }

        return errorMessages;
    }
}
