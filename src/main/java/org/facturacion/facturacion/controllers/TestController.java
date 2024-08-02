package org.facturacion.facturacion.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
public class TestController {


    @GetMapping()
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Backend activo en "+ LocalDate.now());
    }
}
