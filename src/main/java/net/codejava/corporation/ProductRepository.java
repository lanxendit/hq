package net.codejava.corporation;

import net.codejava.corporation.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByMst(String mst);

    @Query("SELECT p FROM Product p WHERE " +
            "1=1" +
            "And p.mst LIKE CONCAT('%',:mst, '%')" +
            "And p.tencty LIKE CONCAT('%', :tencty, '%')" +
            "And p.diachi LIKE CONCAT('%', :diachi, '%')" +
            "And p.tinhtrang LIKE CONCAT('%', :tinhtrang, '%')" +
            "And p.tieuchitrongdiem LIKE CONCAT('%', :tieuchitrongdiem, '%')" +
            "And p.chicucthuequanly LIKE CONCAT('%', :chicucthuequanly, '%')" +
            "And p.congchucid LIKE CONCAT('%', :congchucid, '%')" +
            "And p.linhvuc LIKE CONCAT('%', :linhvuc, '%')" +
            "And p.nganhnghekd LIKE CONCAT('%', :nganhnghekd, '%')" +
            "And p.tkvnaccs LIKE CONCAT('%', :tkvnaccs, '%')" +
            "And p.tkdaily LIKE CONCAT('%', :tkdaily, '%')" +
            "And p.boss LIKE CONCAT('%', :boss, '%')" +
            "And p.bossid LIKE CONCAT('%', :bossid, '%')")
    List<Product> searchProducts(String mst,String tencty, String diachi, String tinhtrang, String tieuchitrongdiem, String chicucthuequanly, String congchucid, String linhvuc, String nganhnghekd, String tkvnaccs, String tkdaily, String boss, String bossid);
}
