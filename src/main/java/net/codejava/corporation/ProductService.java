package net.codejava.corporation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import net.codejava.ExcelHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static java.nio.file.StandardCopyOption.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> listAll() {
        return repo.findAll();
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Product get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void savefile(MultipartFile file) {
        try {
            List<Product> customers = ExcelHelper.excelCustomers(file.getInputStream());
            List<String> mstListFile = customers.stream().map(customer -> customer.getMst()).collect(Collectors.toList());
            if (!mstListFile.stream().allMatch(new HashSet<>()::add)) {
                System.out.println("file co mst bi trung");
                throw new IllegalStateException("file co mst bi trung");
            }
            ;
            mstListFile.forEach(mst -> {
                Optional<Product> customerOptional = repo.findProductByMst(mst);
                if (customerOptional.isPresent()) {
                    System.out.println("co mst ton tai" + mst);
                    throw new IllegalStateException("co mst ton tai" + mst);
                }
            });
            repo.saveAll(customers);
        } catch (IOException e) {
            System.out.println("fail to store excel data: ");
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void print(Product product) throws IOException, InvalidFormatException {
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        Path copied = Paths.get("src/main/resources/templates/khdn.docx");
        Path originalPath = Paths.get("src/main/resources/templates/template.docx");
        Files.copy(originalPath, copied, REPLACE_EXISTING);

        XWPFDocument doc = new XWPFDocument(OPCPackage.open("src/main/resources/templates/khdn.docx"));
        OutputStream out = new FileOutputStream("src/main/resources/templates/khdn1.docx");
        try {
            for (XWPFTable tbl : doc.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            List<XWPFRun> runs = p.getRuns();
                            if (runs != null) {
                                for (XWPFRun r : runs) {
                                    String text = r.getText(0);
                                    System.out.println(text);
                                    if (text != null && text.contains("yyy")) {
                                        text = text.replace("yyy", String.valueOf(year));// your content
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
                    }
                }
            }
            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        System.out.println(text);
                        if (text != null && text.contains("(MST)")) {
                            text = text.replace("(MST)", product.getMst());// your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("tencongty")) {
                            text = text.replace("tencongty", product.getTencty());// your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("diachi")) {
                            text = text.replace("diachi", product.getDiachi());// your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("(ngaythanhlap)")) {
                            text = text.replace("(ngaythanhlap)", product.getNgaythanhlap().toString());// your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("(Xếp hạng từ VNACCS)")) {
                            text = text.replace("(Xếp hạng từ VNACCS)", product.getTkvnaccs());// your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("(boss)")) {
                            text = text.replace("(boss)", product.getBoss());// your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("boss_id")) {
                            text = text.replace("boss_id", product.getBossid());// your content
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

    public List<Product> searchProducts(String mst, String tencty, String diachi, String tinhtrang, String tieuchitrongdiem, String chicucthuequanly, String congchucid, String linhvuc, String nganhnghekd, String tkvnaccs, String tkdaily, String boss, String bossid) {
        List<Product> products = repo.searchProducts(mst, tencty, diachi, tinhtrang, tieuchitrongdiem, chicucthuequanly, congchucid, linhvuc, nganhnghekd, tkvnaccs, tkdaily, boss, bossid);
        return products;
    }
}
