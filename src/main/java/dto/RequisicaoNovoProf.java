/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import br.sei_la.com.aprendendo_spring.models.Professor;
import br.sei_la.com.aprendendo_spring.models.StatusProfessor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 *
 * @author gusta
 */
public class RequisicaoNovoProf {

    @NotBlank
    @NotNull
    private String nomeDTO;
    private StatusProfessor statusProfessorDTO;
    @NotNull
    @DecimalMin(value = "1000.00")
    private BigDecimal salarioDTO;

    public Professor toProfessor() {
        Professor professor = new Professor(this.nomeDTO, this.salarioDTO, this.statusProfessorDTO);
        return professor;
    }

    public String getNomeDTO() {
        return nomeDTO;
    }

    public void setNomeDTO(String nomeDTO) {
        this.nomeDTO = nomeDTO;
    }

    public StatusProfessor getStatusProfessorDTO() {
        return statusProfessorDTO;
    }

    public void setStatusProfessorDTO(StatusProfessor statusProfessorDTO) {
        this.statusProfessorDTO = statusProfessorDTO;
    }

    public BigDecimal getSalarioDTO() {
        return salarioDTO;
    }

    public void setSalarioDTO(BigDecimal salarioDTO) {
        this.salarioDTO = salarioDTO;
    }
    
    @Override
    public String toString() {
        return "RequisicaoNovoProf{" + "nome=" + nomeDTO + ", status=" + statusProfessorDTO + ", salario=" + salarioDTO + '}';
    }

}
