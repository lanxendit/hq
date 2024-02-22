package net.codejava.codee;


import net.codejava.individual.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeeRepo extends JpaRepository<Codee, Long> {
    public List<Codee> findAllByOrderBySttAscNameAscMeaningAsc();
    @Query("SELECT p FROM Codee p WHERE p.stt='1' order by name, meaning")
    List<Codee> getC1List();
    @Query("SELECT p FROM Codee p WHERE p.stt='2' order by name, meaning")
    List<Codee> getC2List();
    @Query("SELECT p FROM Codee p WHERE p.stt='3' order by name, meaning")
    List<Codee> getC3List();
}

