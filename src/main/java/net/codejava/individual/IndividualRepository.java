package net.codejava.individual;

import net.codejava.corporation.Product;
import net.codejava.individual.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface IndividualRepository extends JpaRepository<Individual, Long> {
    //Optional<Individual> findProductByCnId(String cn_id);
    @Query("SELECT p FROM Individual p WHERE " +
            "1=1" +
            "And p.cn_id LIKE CONCAT('%',:cn_id, '%')" +
            "And p.tencn LIKE CONCAT('%', :tencn, '%')" +
            "And p.quoctich LIKE CONCAT('%', :quoctich, '%')" +
            "And p.hokhau_thuongtru LIKE CONCAT('%', :hokhau_thuongtru, '%')" +
            "And p.hokhau_tamtru LIKE CONCAT('%', :hokhau_tamtru, '%')" +
            "And p.diachicssx LIKE CONCAT('%', :diachicssx, '%')" +
            "And p.linhvuc LIKE CONCAT('%', :linhvuc, '%')" +
            "And p.tieuchi LIKE CONCAT('%', :tieuchi, '%')" +
            "And p.mahq LIKE CONCAT('%', :mahq, '%')" +
            "And p.congchuc_id LIKE CONCAT('%', :congchuc_id, '%')")
    List<Individual> searchIndividuals(String cn_id, String tencn, String quoctich, String hokhau_thuongtru, String hokhau_tamtru, String diachicssx,
                                               String linhvuc, String tieuchi, String mahq, String congchuc_id);
}
