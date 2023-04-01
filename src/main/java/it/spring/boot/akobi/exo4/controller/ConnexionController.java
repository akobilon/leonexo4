package it.spring.boot.akobi.exo4.controller;

import it.spring.boot.akobi.exo4.model.Utilisateurs;
import it.spring.boot.akobi.exo4.service.UtilisateursService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConnexionController {
    @Autowired
    private UtilisateursService utilisateursService;

    @GetMapping("/connexion")
    public String showConnexionForm(Model model) {
        model.addAttribute("utilisateurs", new Utilisateurs());

        return "connexion";
    }

    @PostMapping("/connexion")
    public String submitConnexionForm(@ModelAttribute("connexion") Utilisateurs utilisateur, BindingResult bindingResult,
                                      Model model, HttpServletRequest request) {
//        model.addAttribute("utilisateurs", new Utilisateurs());
        String email = request.getParameter("username");
        String pwd = request.getParameter("password");
        Utilisateurs existingUser = utilisateursService.findUserByEmailAndPassword(email, pwd);
        System.out.println(existingUser);
        if (existingUser != null) {
            model.addAttribute("user", existingUser.getNom());
            return "success";

        }

        return "redirect:/connexion?error";
        // return "connexion";


    }
}
