package it.spring.boot.akobi.exo4.service;

import it.spring.boot.akobi.exo4.model.Utilisateurs;
import it.spring.boot.akobi.exo4.repository.UtilisateursRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class UtilisateursService {

    @Autowired
    private UtilisateursRepository utilisateursRepository;

    public Utilisateurs saveUtilisateur(Utilisateurs employee) {
        return utilisateursRepository.save(employee);
    }


    public Utilisateurs findUserByEmail(String email) {
        return utilisateursRepository.findByEmail(email);
    }

    public Utilisateurs findUserByEmailAndPassword(String email, String pwd) {
        return utilisateursRepository.findByEmailAndPassword(email,pwd);
    }
}
