package br.senac.sp.epictask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senac.sp.epictask.data.tarefaRepository;
import br.senac.sp.epictask.model.Tarefa;

@Controller
public class tarefaController {

    @Autowired
    tarefaRepository repository;

    @GetMapping("/tarefas")
    public String  tarefas(Model model){
        var tarefas = repository.findAll();
        model.addAttribute("tarefas", tarefas);
        return "tarefas";
    }

    @GetMapping("/tarefas/cadastrar")
    public String formulario(){
        return "formulario_tarefa";
    }

    @PostMapping("/tarefas")
        public String cadastrar(Tarefa tarefa){
            repository.save(tarefa);
            return "redirect:/tarefas";
        }

        @GetMapping("/")
        public String home(){
            return "redirect:/tarefas";
        }

        @DeleteMapping("/tarefas")
        public String apagar(long id, RedirectAttributes redirect){
            repository.deleteById(id);
            redirect.addFlashAttribute("mensagem", "tarefa apagada com sucesso");
            return "redirect:/tarefas";
        }
    }

