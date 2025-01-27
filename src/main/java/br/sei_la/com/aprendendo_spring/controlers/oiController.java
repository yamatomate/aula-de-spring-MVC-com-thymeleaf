/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.sei_la.com.aprendendo_spring.controlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gusta
 */
@Controller
public class oiController {
    
    @GetMapping("/oi")
    public String hello(HttpServletRequest request){
        request.setAttribute("nome","adriel");
        return "hello";
    }
}
