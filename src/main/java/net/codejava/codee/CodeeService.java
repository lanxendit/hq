package net.codejava.codee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeeService {
    @Autowired
    private CodeeRepo repo;

    public List<Codee> listAll() {
        return repo.findAllByOrderBySttAscNameAscMeaningAsc();
    }

    public void save(Codee codee) {
        repo.save(codee);
    }
    public Codee get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    public List<String> c1ListJSON(){
        return repo.getC1List().stream().map(codee -> codee.getName()+"-"+codee.getMeaning()).collect(Collectors.toList());
    }
    public List<String> c2ListJSON(){
        return repo.getC2List().stream().map(codee -> codee.getName()+"-"+codee.getMeaning()).collect(Collectors.toList());
    }
    public List<String> c3ListJSON(){
        return repo.getC3List().stream().map(codee -> codee.getName()+"-"+codee.getMeaning()).collect(Collectors.toList());
    }
}