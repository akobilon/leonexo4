package it.spring.boot.akobi.exo4.controller;

import it.spring.boot.akobi.exo4.model.Utilisateurs;
import it.spring.boot.akobi.exo4.repository.UtilisateursRepository;
import it.spring.boot.akobi.exo4.service.UtilisateursService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class InscriptionController {
    @Autowired
    private UtilisateursService utilisateursService;
//    private UtilisateursRepository utilisateursRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("utilisateurs", new Utilisateurs());

        return "index";
    }
    @GetMapping("/inscription")
    public String showRegistrationForm(Model model) {
        model.addAttribute("utilisateurs", new Utilisateurs());

        return "inscription";
    }

    @PostMapping("/inscription")
    public String inscriptionUser(@Valid Utilisateurs utilisateur, BindingResult bindingResult, Model model, HttpServletRequest request) {

        Utilisateurs existingUser = utilisateursService.findUserByEmail(utilisateur.getEmail());
        String pwd = request.getParameter("passwordsecond");
        if(utilisateur.getNom().isBlank() && !utilisateur.getNom().isEmpty()){
            bindingResult.rejectValue("nom", null,
                    "Le nom ne doit pas etre vide");
        }
        if(utilisateur.getPrenom().isBlank() && !utilisateur.getPrenom().isEmpty()){
            bindingResult.rejectValue("prenom", null,
                    "Le prenom doit pas etre vide");
        }
        if(utilisateur.getPassword().isBlank() && !utilisateur.getPassword().isEmpty()){
            bindingResult.rejectValue("password", null,
                    "Le mot de passe ne doit pas etre vide");
        }
        else if (utilisateur.getPassword().length() < 8) {
            bindingResult.rejectValue("password", null,
                    "Le mot de passe doit contenir au moins 8 caractères");
        }
        if(!utilisateur.getPassword().equals(pwd)){
            bindingResult.rejectValue("password", null,
                    "Les mots de passes ne correspondonte pas");
        }
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            bindingResult.rejectValue("email", null,
                    "Il existe déjà un compte enregistré avec le même e-mail");
        }


        if (bindingResult.hasErrors()) {
            model.addAttribute("user", utilisateur);
//            return "/register";
            return "inscription";
        }
        utilisateursService.saveUtilisateur(utilisateur);
        return "redirect:/connexion";


//        if (bindingResult.hasErrors()) {
//            return "inscription";
//        }
////        utilisateur.setPassword(new BCryptPasswordEncoder().encode(utilisateur.getPassword()));
//
//        return "redirect:/login";
    }
}
