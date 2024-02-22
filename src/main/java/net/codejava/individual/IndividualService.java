package net.codejava.individual;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import net.codejava.ExcelHelper;
import net.codejava.corporation.Product;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static java.nio.file.StandardCopyOption.*;

@Service
public class IndividualService {
    @Autowired
    private IndividualRepository repo;
    public List<Individual> listAll() {
        return repo.findAll();
    }
    public void save(Individual individual) {
        repo.save(individual);
    }

    public Individual get(Long id) {
        return repo.findById(id).get();
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public void savefile(MultipartFile file) {
        try {
            List<Individual> individuals = ExcelHelper.excelIndividuals(file.getInputStream());
//            List<String> mstListFile = individuals.stream().map(individual -> individual.getCn_id()).collect(Collectors.toList());
//            if (!mstListFile.stream().allMatch(new HashSet<>()::add)) {
//                System.out.println("file co cn_id bi trung");
//                throw new IllegalStateException("file co cn_id bi trung");
//            }
//            mstListFile.forEach(cn_id -> {
//                Optional<Individual> individualOptional = repo.findProductByCnId(cn_id);
//                if (individualOptional.isPresent()) {
//                    System.out.println("co mst ton tai" + cn_id);
//                    throw new IllegalStateException("co mst ton tai" + cn_id);
//                }
//            });
            repo.saveAll(individuals);
        } catch (IOException e) {
            System.out.println("fail to store excel data: ");
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void print(Product product) throws IOException, InvalidFormatException {
        Date d=new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int year=cal.get(Calendar.YEAR);
        Path copied = Paths.get("src/main/resources/templates/khdn.docx");
        Path originalPath = Paths.get("src/main/resources/templates/template.docx");
        Files.copy(originalPath, copied, REPLACE_EXISTING);

//        XWPFDocument doc = new XWPFDocument(OPCPackage.open("src/main/resources/templates/khdn.docx"));
//        List<XWPFRun> runs = doc.getRuns();
//        for (XWPFRun run : runs) {
//            String text = run.getText(0);
//            if (text != null && text.contains("MST")) {
//                String updatedRunText = text.replace("MST", "hehehe");
//                run.setText(updatedRunText, 0);
//            }
//        }
// Opens the input Word document.

        XWPFDocument doc = new XWPFDocument(OPCPackage.open("src/main/resources/templates/khdn.docx"));
        OutputStream out = new FileOutputStream("src/main/resources/templates/khdn1.docx");
        try {
            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains("(MST)")) {
                            text = text.replace("(MST)", product.getMst());// your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("(tencongty)")) {
                            System.out.println("chay vao day");
                            text = text.replace("(tencongty)", product.getTencty());// your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("(Xếp hạng từ VNACCS)")) {
                            text = text.replace("(Xếp hạng từ VNACCS)", product.getTkvnaccs());// your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("yyyy")) {
                            text = text.replace("yyyy", String.valueOf(year));// your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("dd")) {
                            text = text.replace("dd", String.valueOf(date));// your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("mm")) {
                            text = text.replace("mm", String.valueOf(month));// your content
                            r.setText(text, 0);
                        }

                    }
                }
            }
            doc.write(out);
        } finally {
            out.close();
            doc.close();
        }

    }
    public List<Individual> searchIndividuals(String cn_id,String tencn, String quoctich, String hokhau_thuongtru, String hokhau_tamtru, String diachicssx, String linhvuc, String tieuchi, String mahq, String congchuc_id) {
        List<Individual> individuals = repo.searchIndividuals(cn_id,tencn,quoctich,hokhau_thuongtru,hokhau_tamtru,diachicssx,linhvuc,tieuchi,mahq,congchuc_id);
        return individuals;
    }
}
