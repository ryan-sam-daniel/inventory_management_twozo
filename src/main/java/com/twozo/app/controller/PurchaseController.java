package com.twozo.app.controller;

import com.twozo.app.model.*;
import com.twozo.app.service.TradeService;
import com.twozo.app.validator.QuantityValidator;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/purchase")
public class PurchaseController{
    private final TradeService<Product, Purchase, PurchaseItem> purchaseService;
    private final QuantityValidator quantityValidator;

    public PurchaseController(final TradeService<Product, Purchase, PurchaseItem> purchaseService,final QuantityValidator quantityValidator){
        this.purchaseService = purchaseService;
        this.quantityValidator = quantityValidator;
    }
    
    @PostMapping("/add")
    public ResponseEntity<ReturnMsgDTO> add(@RequestBody  final TradeOperationDTO tradeOperationDTO){
        final ReturnMsgDTO returnMsgDTO = new ReturnMsgDTO();
        if(!quantityValidator.validateStockQuantity(tradeOperationDTO.getQuantity())){
            returnMsgDTO.setMsg("Quantity is zero or less than zero");
            return ResponseEntity.badRequest().body(returnMsgDTO);
        }
        returnMsgDTO.setMsg(purchaseService.add(tradeOperationDTO));
        return ResponseEntity.ok(returnMsgDTO);
    }

    @PostMapping("/remove")
    public ResponseEntity<ReturnMsgDTO> remove(@RequestBody final TradeOperationDTO tradeOperationDTO){
        final ReturnMsgDTO returnMsgDTO = new ReturnMsgDTO();
        if(!quantityValidator.validateStockQuantity(tradeOperationDTO.getQuantity())){
            returnMsgDTO.setMsg("Quantity is zero or less than zero");
            return ResponseEntity.badRequest().body(returnMsgDTO);
        }
        returnMsgDTO.setMsg(purchaseService.remove(tradeOperationDTO));
        return ResponseEntity.ok(returnMsgDTO);
    }

    @GetMapping("/create")
    public ResponseEntity<ReturnMsgDTO> createNew(){
        final ReturnMsgDTO returnMsgDTO = new ReturnMsgDTO();
        returnMsgDTO.setMsg(purchaseService.createNew());
        return ResponseEntity.ok(returnMsgDTO);
    }

    @GetMapping("/getCart")
    public Collection<PurchaseItem> getCart(){
        return purchaseService.getCart();
    }

    @GetMapping("/subTotal")
    public double caluculateSubtotal(){
        return purchaseService.getSubtotal();
    }

    @GetMapping("/tax")
    public double calculateTax(){
        return purchaseService.getTax();
    }

    @PostMapping("/finalAmount")
    public ResponseEntity<?> calculateFinalAmount(@RequestBody final FinalAmountRequest finalAmountRequest){
        final ReturnMsgDTO returnMsgDTO = new ReturnMsgDTO();
        if(!quantityValidator.validateAmount(finalAmountRequest.getAmount())){
            if(!quantityValidator.validateRate(finalAmountRequest.getRate())){
                returnMsgDTO.setMsg("Validation error ! Quantity has to be changed");
                return ResponseEntity.badRequest().body(returnMsgDTO);
            }
        }
        return ResponseEntity.ok(purchaseService.getFinalAmount(finalAmountRequest));
    }

}
