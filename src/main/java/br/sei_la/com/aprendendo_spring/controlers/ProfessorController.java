/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.sei_la.com.aprendendo_spring.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.sei_la.com.aprendendo_spring.models.Professor;
import br.sei_la.com.aprendendo_spring.models.StatusProfessor;
import br.sei_la.com.aprendendo_spring.repository.ProfessorRepository;
import dto.RequisicaoNovoProf;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author gusta
 */
@RequestMapping("/professores")
@Controller
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @GetMapping("")
    public ModelAndView index(HttpServletRequest request) {
        List<Professor> professores = this.professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("caminho", request.getRequestURI());
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView NovoProfessor(HttpServletRequest request, RequisicaoNovoProf requisicao) {
        ModelAndView mvProfessorNovo = new ModelAndView("professores/novo");
        mvProfessorNovo.addObject("caminho", request.getRequestURI());
        mvProfessorNovo.addObject("statusProfessor", StatusProfessor.values());
        return mvProfessorNovo;
    }

    @GetMapping("{id}/editar")
    public ModelAndView EditarProfessor(@PathVariable Long id, HttpServletRequest request, RequisicaoNovoProf requisicao) {
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent()) {
            Professor professor = optional.get();
            requisicao.fromProfessor(professor);
            System.out.println(professor.toString());
            System.out.println(requisicao.toString());

            ModelAndView mvProfessorEditar = new ModelAndView("professores/editar");
            mvProfessorEditar.addObject("caminho", request.getRequestURI());
            mvProfessorEditar.addObject("statusProfessor", StatusProfessor.values());
            mvProfessorEditar.addObject("IdProf", professor.getId());

            return mvProfessorEditar;
        } else {
            return new ModelAndView("redirect:/professores");
        }
    }

    @PostMapping("")
    public ModelAndView SalvarNovoProfessor(@Valid RequisicaoNovoProf professorNV, BindingResult bindingResult) {//o correto seria criar mas usarei salvar
        System.out.println("**************************\nnovo professor criado: "
                + professorNV.toString()
                + "\n**************************");
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println("deu erro!!");
            ModelAndView mv = new ModelAndView("/professores/novo");
            mv.addObject("statusProfessor", StatusProfessor.values());
            return mv;
        } else {
            Professor professor = professorNV.toProfessor();
            this.professorRepository.save(professor);
            return new ModelAndView("redirect:/professores/" + professor.getId());
        }

    }

    @PostMapping("/{id}")
    public ModelAndView SalvarProfEditado(@PathVariable Long id,
            @Valid RequisicaoNovoProf professorDTO, BindingResult bindingResult) {
       
        if (bindingResult.hasErrors()) {
            System.out.println("deu erro!!");
            ModelAndView mv = new ModelAndView("/professores/editar");
            mv.addObject("statusProfessor", StatusProfessor.values());
            return mv;
            
        } else {
            
            Optional<Professor> optional = this.professorRepository.findById(id);

            if (optional.isPresent()) {
                Professor professor = professorDTO.toProfessor(optional.get());
                this.professorRepository.save(professor);
                return new ModelAndView("redirect:/professores/"+professor.getId());
            } else {
                System.out.println("nn foi achado nenhum professor com id" + id);
                return new ModelAndView("redirect:/professores");
            }
        }
    }

    @GetMapping("/{id}")
    public ModelAndView mostrar(@PathVariable Long id, HttpServletRequest request) {
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent()) {
            ModelAndView mvMostrar = new ModelAndView("/professores/mostrar");
            mvMostrar.addObject("caminho", request.getRequestURI());
            Professor professor = optional.get();
            mvMostrar.addObject("professor", professor);
            return mvMostrar;
        } else // nn achou o id
        {
            System.out.println("#################\nnn foi encontrado o " + id);
            return new ModelAndView("redirect:/professores");
        }
    }
    
    @GetMapping("/{id}/deletar")
    public String deletando(@PathVariable Long id){
        System.out.println("deletando");
        try{
            this.professorRepository.deleteById(id);
            return "redirect:/professores";
        } catch (EmptyResultDataAccessException e){
            System.out.println(e);
            return "redirect:/professores";
        }
    }
}
