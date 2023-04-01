package it.spring.boot.akobi.exo4.repository;

import it.spring.boot.akobi.exo4.model.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Long> {
    @Query("SELECT u FROM Utilisateurs u WHERE u.email = ?1")
    Utilisateurs findByEmail(String email);

    @Query("SELECT u FROM Utilisateurs u WHERE u.email = ?1 and u.password= ?2")
    Utilisateurs findByEmailAndPassword(String email,String pwd);
}
