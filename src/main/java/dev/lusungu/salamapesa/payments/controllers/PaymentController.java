package dev.lusungu.salamapesa.payments.controllers;

import dev.lusungu.salamapesa.payments.dtos.PaymentRequestDto;
import dev.lusungu.salamapesa.payments.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping
    public ResponseEntity<String> processPayment(@Valid @RequestBody PaymentRequestDto dto){
        String status = paymentService.makePayment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }



}
