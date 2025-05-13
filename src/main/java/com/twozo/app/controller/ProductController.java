package com.twozo.app.controller;

import com.twozo.app.model.Product;
import com.twozo.app.model.ProductSearchDTO;
import com.twozo.app.model.ReturnMsgDTO;
import com.twozo.app.service.ProductServiceManager;
import com.twozo.app.validator.ProductValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductServiceManager productService;
    private final ProductValidator productValidator;

    public ProductController(final ProductServiceManager productService,final ProductValidator productValidator){
        this.productService = productService;
        this.productValidator = productValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<ReturnMsgDTO> addProduct(@RequestBody final Product product){
        final ReturnMsgDTO returnMsgDTO = new ReturnMsgDTO();
        final StringBuilder validationErrors = productValidator.validateAdding(product);

        if (!validationErrors.isEmpty()) {
            returnMsgDTO.setMsg("{\"error\":\"\n" + validationErrors.toString().trim() + "\"\n}");
            return ResponseEntity.badRequest().body(returnMsgDTO);
        }

        productService.add(product);
        returnMsgDTO.setMsg("Product added successfully");
        return ResponseEntity.ok(returnMsgDTO);
    }

    @GetMapping("/inventory")
    public Collection<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/remove")
    public ResponseEntity<ReturnMsgDTO> removeProduct(@RequestBody final ProductSearchDTO product){
        final boolean removed = productService.remove(product);
        final ReturnMsgDTO returnMsgDTO = new ReturnMsgDTO();
        if (removed) {
            returnMsgDTO.setMsg("Product Removed");
            return ResponseEntity.ok(returnMsgDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
