package com.twozo.app.controller;

import com.twozo.app.model.PurchaseTransactionRequest;
import com.twozo.app.model.ReturnMsgDTO;
import com.twozo.app.service.TradeTransactionService;
import com.twozo.app.validator.QuantityValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseTransactionController  {
    private final TradeTransactionService<PurchaseTransactionRequest> purchaseTransactionService;
    private final QuantityValidator quantityValidator;

    public PurchaseTransactionController (final TradeTransactionService<PurchaseTransactionRequest> purchaseTransactionService, final QuantityValidator quantityValidator){
        this.purchaseTransactionService = purchaseTransactionService;
        this.quantityValidator = quantityValidator;
    }

    @PostMapping("/transaction")
    public ResponseEntity<ReturnMsgDTO> processPurchaseTransaction(@RequestBody final PurchaseTransactionRequest purchaseTransactionRequest) {
        final ReturnMsgDTO returnMsgDTO = new ReturnMsgDTO();
        if(!quantityValidator.validateAmount(purchaseTransactionRequest.getAmount())){
            returnMsgDTO.setMsg("Amount is not a correct value. ");
        }
        if (purchaseTransactionRequest.getCart()==null){
            returnMsgDTO.setMsg("Cart is null. ");
        }
        if (purchaseTransactionRequest.getPurchase() == null){
            returnMsgDTO.setMsg("Purchase details are null. ");
        }
        if (purchaseTransactionRequest.getMethod().isEmpty()){
            returnMsgDTO.setMsg("Method is not specified. ");
        }
        if (!quantityValidator.validatePhoneNo(purchaseTransactionRequest.getPhoneNo())) {
            returnMsgDTO.setMsg("Phone number is wrong. ");
        }
        if(returnMsgDTO.hasMsg()){
            return ResponseEntity.badRequest().body(returnMsgDTO);
        }

        if(purchaseTransactionService.completeTrade(purchaseTransactionRequest)){
            returnMsgDTO.setMsg("Purchase transaction success. ");
            return ResponseEntity.ok(returnMsgDTO);
        }
        else{
            returnMsgDTO.setMsg("Purchase transaction Failed");
            return ResponseEntity.badRequest().body(returnMsgDTO);
        }
    }
}
