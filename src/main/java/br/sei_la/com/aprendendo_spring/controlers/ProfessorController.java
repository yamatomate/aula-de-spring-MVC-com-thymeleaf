/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.sei_la.com.aprendendo_spring.controlers;

import br.sei_la.com.aprendendo_spring.models.Professor;
import br.sei_la.com.aprendendo_spring.models.StatusProfessor;
import br.sei_la.com.aprendendo_spring.repository.ProfessorRepository;
import dto.RequisicaoNovoProf;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gusta
 */
@Controller
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @GetMapping("/professores")
    public ModelAndView index() {
        List<Professor> professores = this.professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/professores/novo")
    public ModelAndView NovoProfessor() {
        ModelAndView mvProfessorNovo = new ModelAndView("professores/novo");
        mvProfessorNovo.addObject("statusProfessor", StatusProfessor.values());
        return mvProfessorNovo;
    }


@PostMapping("/professores")
public String SalvarNovoProfessor(RequisicaoNovoProf professorNV){//o correto seria criar mas usarei salvar
        System.out.println("**************************\nnovo professor criado: "
                +professorNV.toString()+
                "\n**************************");
        return "redirect:/professores";
    }
}
