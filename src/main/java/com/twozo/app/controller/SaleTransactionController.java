package com.twozo.app.controller;

import com.twozo.app.model.ReturnMsgDTO;
import com.twozo.app.model.SaleTransactionRequest;
import com.twozo.app.service.TradeTransactionService;
import com.twozo.app.validator.QuantityValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleTransactionController  {
    private final TradeTransactionService<SaleTransactionRequest> saleTransactionService;
    private final QuantityValidator quantityValidator;

    public SaleTransactionController (final TradeTransactionService<SaleTransactionRequest> saleTransactionService,final QuantityValidator quantityValidator){
        this.saleTransactionService = saleTransactionService;
        this.quantityValidator = quantityValidator;
    }

    @PostMapping("/transaction")
    public ResponseEntity<ReturnMsgDTO> processSaleTransaction(@RequestBody final SaleTransactionRequest saleTransactionRequest) {
        final ReturnMsgDTO returnMsgDTO = new ReturnMsgDTO();
        if(!quantityValidator.validateAmount(saleTransactionRequest.getAmount())){
            returnMsgDTO.setMsg("Amount is not a correct value. ");
        }
        if(saleTransactionRequest.getCart() == null){
            returnMsgDTO.setMsg("Cart is null. ");
        }
        if (saleTransactionRequest.getSale() == null){
            returnMsgDTO.setMsg("Purchase details are null. ");
        }
        if (saleTransactionRequest.getMethod().isEmpty()){
            returnMsgDTO.setMsg("Method is not specified. ");
        }
        if (!quantityValidator.validatePhoneNo(saleTransactionRequest.getPhoneNo())) {
            returnMsgDTO.setMsg("Phone number is wrong. ");
        }
        if(returnMsgDTO.hasMsg()){
            return ResponseEntity.badRequest().body(returnMsgDTO);
        }
        if(saleTransactionService.completeTrade(saleTransactionRequest)){
            returnMsgDTO.setMsg("Sale transaction success");
            return ResponseEntity.ok(returnMsgDTO);
        }
        else{
            returnMsgDTO.setMsg("Sale transaction Failed");
            return ResponseEntity.badRequest().body(returnMsgDTO);
        }
    }


}
