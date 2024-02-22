package net.codejava;

import net.codejava.corporation.Product;
import net.codejava.individual.Individual;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] ProductHEADERs = {
            "istrongdiem"
            , "sttcap1"
            , "sttcap2"
            , "sttcap3"
            , "mst"
            , "tencty"
            , "diachi"
            , "tieuchitrongdiem"
            , "mahq"
            , "ngaythanhlap"
            , "ngaythaydoi"
            , "tinhtrang"
            , "linhvuc"
            , "nganhnghekd"
            , "tkvnaccs"
            , "tkdaily"
            , "boss"
            , "bossid"
            , "bossaddr"
            , "chicucthuequanly"
            , "doitacnuocngoai"
            , "chuhangthuctetaivietnam"
            , "chuhangthuctetainuocngoai"
            , "dailylogisticstainuocngoai"
            , "dailylogisticstaivietnam"
            , "thongtinthunhapvaphantich"
            , "congchucid"
            , "congchucname"
            , "mstdnsatnhap"
            , "tendnsatnhap"
            , "diachidnsatnhap"
            , "mstdnchiatach"
            , "tendnchiatach"
            , "diachidnchiatach"};
    static String[] IndividualHEADERs = {
            "is_trongdiem"
            , "cn_id"
            , "tencn"
            , "noicap"
            , "ngaycap"
            , "ngayhethan"
            , "gioitinh"
            , "dantoc"
            , "quoctich"
            , "hokhau_thuongtru"
            , "hokhau_tamtru"
            , "tieuchi"
            , "mahq"
            , "linhvuc"
            , "diachicssx"
            , "coquan_congtac"
            , "dang_congtac"
            , "quanhe"
            , "congchuc_id"
            , "congchuc_name"};
    static String SHEET = "Sheet1";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Product> excelCustomers(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Product> customers = new ArrayList<Product>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Product customer = new Product();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            customer.setIstrongdiem(currentCell.getStringCellValue());
                            break;

                        case 1:
                            customer.setSttcap1(currentCell.getStringCellValue());
                            break;

                        case 2:
                            customer.setSttcap2(currentCell.getStringCellValue());
                            break;

                        case 3:
                            customer.setSttcap3(currentCell.getStringCellValue());
                            break;
                        case 4:
                            customer.setMst(currentCell.getStringCellValue());
                            break;
                        case 5:
                            customer.setTencty(currentCell.getStringCellValue());
                            break;
                        case 6:
                            customer.setDiachi(currentCell.getStringCellValue());
                            break;
                        case 7:
                            customer.setTieuchitrongdiem(currentCell.getStringCellValue());
                            break;
                        case 8:
                            customer.setMahq(currentCell.getStringCellValue());
                            break;
                        case 9:
                            if(currentCell.getStringCellValue() != null&&currentCell.getStringCellValue() != ""){customer.setNgaythanhlap(LocalDate.parse(currentCell.getStringCellValue()));}
                            else customer.setNgaythanhlap(null);
                            break;
                        case 10:
                            if(currentCell.getStringCellValue() != null&&currentCell.getStringCellValue() != ""){customer.setNgaythaydoi(LocalDate.parse(currentCell.getStringCellValue()));}
                            else customer.setNgaythaydoi(null);
                            break;
                        case 11:
                            customer.setTinhtrang(currentCell.getStringCellValue().trim());
                            break;
                        case 12:
                            customer.setLinhvuc(currentCell.getStringCellValue());
                            break;
                        case 13:
                            customer.setNganhnghekd(currentCell.getStringCellValue());
                            break;
                        case 14:
                            customer.setTkvnaccs(currentCell.getStringCellValue());
                            break;
                        case 15:
                            customer.setTkdaily(currentCell.getStringCellValue());
                            break;
                        case 16:
                            customer.setBoss(currentCell.getStringCellValue());
                            break;
                        case 17:
                            customer.setBossid(currentCell.getStringCellValue());
                            break;
                        case 18:
                            customer.setBossaddr(currentCell.getStringCellValue());
                            break;
                        case 19:
                            customer.setChicucthuequanly(currentCell.getStringCellValue());
                            break;
                        case 20:
                            customer.setDoitacnuocngoai(currentCell.getStringCellValue());
                            break;
                        case 21:
                            customer.setChuhangthuctetaivietnam(currentCell.getStringCellValue());
                            break;
                        case 22:
                            customer.setChuhangthuctetainuocngoai(currentCell.getStringCellValue());
                            break;
                        case 23:
                            customer.setDailylogisticstainuocngoai(currentCell.getStringCellValue());
                            break;
                        case 24:
                            customer.setDailylogisticstaivietnam(currentCell.getStringCellValue());
                            break;
                        case 25:
                            customer.setThongtinthunhapvaphantich(currentCell.getStringCellValue());
                            break;
                        case 26:
                            customer.setCongchucid(currentCell.getStringCellValue());
                            break;
                        case 27:
                            customer.setCongchucname(currentCell.getStringCellValue());
                            break;
                        case 28:
                            customer.setMstdnsatnhap(currentCell.getStringCellValue());
                            break;
                        case 29:
                            customer.setTendnsatnhap(currentCell.getStringCellValue());
                            break;
                        case 30:
                            customer.setDiachidnsatnhap(currentCell.getStringCellValue());
                            break;
                        case 31:
                            customer.setMstdnchiatach(currentCell.getStringCellValue());
                            break;
                        case 32:
                            customer.setTendnchiatach(currentCell.getStringCellValue());
                            break;
                        case 33:
                            customer.setDiachidnchiatach(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                customers.add(customer);
            }
            workbook.close();
            return customers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    public static List<Individual> excelIndividuals(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Individual> individuals = new ArrayList<Individual>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Individual individual = new Individual();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            individual.setIs_trongdiem(currentCell.getStringCellValue());
                            break;
                        case 1:
                            individual.setCn_id(currentCell.getStringCellValue());
                            break;
                        case 2:
                            individual.setTencn(currentCell.getStringCellValue());
                            break;
                        case 3:
                            individual.setNoicap(currentCell.getStringCellValue());
                            break;
                        case 4:
                            if(currentCell.getStringCellValue() != null&&currentCell.getStringCellValue() != ""){individual.setNgaycap(LocalDate.parse(currentCell.getStringCellValue()));}
                            else individual.setNgaycap(null);
                            break;
                        case 5:
                            if(currentCell.getStringCellValue() != null&&currentCell.getStringCellValue() != ""){individual.setNgayhethan(LocalDate.parse(currentCell.getStringCellValue()));}
                            else individual.setNgayhethan(null);
                            break;
                        case 6:
                            individual.setGioitinh(currentCell.getStringCellValue());
                            break;
                        case 7:
                            individual.setDantoc(currentCell.getStringCellValue());
                            break;
                        case 8:
                            individual.setQuoctich(currentCell.getStringCellValue());
                            break;
                        case 9:
                            individual.setHokhau_thuongtru(currentCell.getStringCellValue());
                            break;
                        case 10:
                            individual.setHokhau_tamtru(currentCell.getStringCellValue());
                            break;
                        case 11:
                            individual.setTieuchi(currentCell.getStringCellValue().trim());
                            break;
                        case 12:
                            individual.setMahq(currentCell.getStringCellValue());
                            break;
                        case 13:
                            individual.setLinhvuc(currentCell.getStringCellValue());
                            break;
                        case 14:
                            individual.setDiachicssx(currentCell.getStringCellValue());
                            break;
                        case 15:
                            individual.setCoquan_congtac(currentCell.getStringCellValue());
                            break;
                        case 16:
                            individual.setDang_congtac(currentCell.getStringCellValue());
                            break;
                        case 17:
                            individual.setQuanhe(currentCell.getStringCellValue());
                            break;
                        case 18:
                            individual.setCongchuc_id(currentCell.getStringCellValue());
                            break;
                        case 19:
                            individual.setCongchuc_name(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                individuals.add(individual);
            }
            workbook.close();
            return individuals;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}
