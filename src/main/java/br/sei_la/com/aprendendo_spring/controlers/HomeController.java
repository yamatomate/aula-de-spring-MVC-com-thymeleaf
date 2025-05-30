/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.sei_la.com.aprendendo_spring.controlers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gusta
 */
@Controller
public class HomeController {
    
    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request){
        ModelAndView mvHome = new ModelAndView("/home");
        mvHome.addObject("caminho", request.getRequestURI());
        return mvHome;
    }
}
