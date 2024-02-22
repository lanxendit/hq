package net.codejava.corporation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Product> listProducts;
    private DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");

    public ProductExcelExporter(List<Product> listProducts) {
        this.listProducts = listProducts;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("KHDN");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Trọng điểm ?", style);
        createCell(row, 1, "STT cấp 1", style);
        createCell(row, 2, "STT cấp 2", style);
        createCell(row, 3, "STT cấp 3", style);
        createCell(row, 4, "Mã số thuế", style);
        createCell(row, 5, "Tên công ty", style);
        createCell(row, 6, "Địa chỉ", style);
        createCell(row, 7, "Tiêu chí trọng điểm", style);
        createCell(row, 8, "Mã hải quan", style);
        createCell(row, 9, "Ngày thành lập", style);
        createCell(row, 10, "Ngày thay đổi", style);
        createCell(row, 11, "Tình Trạng", style);
        createCell(row, 12, "Lĩnh vực", style);
        createCell(row, 13, "Ngành nghề kinh doanh", style);
        createCell(row, 14, "Tài khoản Vnaccs", style);
        createCell(row, 15, "Tài khoản đại lý", style);
        createCell(row, 16, "Chủ doanh nghiệp", style);
        createCell(row, 17, "CMT/CCCD", style);
        createCell(row, 18, "Địa chỉ chủ doanh nghiệp", style);
        createCell(row, 19, "Chi cục thuế quản lý", style);
        createCell(row, 20, "Đối tác nước ngoài", style);
        createCell(row, 21, "Chủ hàng thực tế tại VN", style);
        createCell(row, 22, "Chủ hàng thực tế tại nước ngoài", style);
        createCell(row, 23, "Đại lý logistics tại nước ngoài", style);
        createCell(row, 24, "Đại lý logistics tại VN", style);
        createCell(row, 25, "Thông tin thu thập và phân tích", style);
        createCell(row, 26, "Mã công chức", style);
        createCell(row, 27, "Tên công chức", style);
        createCell(row, 28, "MST doanh nghiệp sát nhập", style);
        createCell(row, 29, "Tên doanh nghiệp sát nhập", style);
        createCell(row, 30, "Địa chỉ doanh nghiệp sát nhập", style);
        createCell(row, 31, "MST doanh nghiệp chia tách", style);
        createCell(row, 32, "Tên doanh nghiệp chia tách", style);
        createCell(row, 33, "Địa chỉ doanh nghiệp chia tách", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Product p : listProducts) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, p.getIstrongdiem(), style);
            createCell(row, columnCount++, p.getSttcap1(), style);
            createCell(row, columnCount++, p.getSttcap2(), style);
            createCell(row, columnCount++, p.getSttcap3(), style);
            createCell(row, columnCount++, p.getMst(), style);
            createCell(row, columnCount++, p.getTencty(), style);
            createCell(row, columnCount++, p.getDiachi(), style);
            createCell(row, columnCount++, p.getTieuchitrongdiem(), style);
            createCell(row, columnCount++, p.getMahq(), style);
            if(p.getNgaythanhlap()==null) {createCell(row, columnCount++, "", style);}else{createCell(row, columnCount++, p.getNgaythanhlap().format(formatters), style);}
            if(p.getNgaythaydoi()==null) {createCell(row, columnCount++,"", style);}else{createCell(row, columnCount++, p.getNgaythaydoi().format(formatters), style);}
            createCell(row, columnCount++, p.getTinhtrang(), style);
            createCell(row, columnCount++, p.getLinhvuc(), style);
            createCell(row, columnCount++, p.getNganhnghekd(), style);
            createCell(row, columnCount++, p.getTkvnaccs(), style);
            createCell(row, columnCount++, p.getTkdaily(), style);
            createCell(row, columnCount++, p.getBoss(), style);
            createCell(row, columnCount++, p.getBossid(), style);
            createCell(row, columnCount++, p.getBossaddr(), style);
            createCell(row, columnCount++, p.getChicucthuequanly(), style);
            createCell(row, columnCount++, p.getDoitacnuocngoai(), style);
            createCell(row, columnCount++, p.getChuhangthuctetaivietnam(), style);
            createCell(row, columnCount++, p.getChuhangthuctetainuocngoai(), style);
            createCell(row, columnCount++, p.getDailylogisticstainuocngoai(), style);
            createCell(row, columnCount++, p.getDailylogisticstaivietnam(), style);
            createCell(row, columnCount++, p.getThongtinthunhapvaphantich(), style);
            createCell(row, columnCount++, p.getCongchucid(), style);
            createCell(row, columnCount++, p.getCongchucname(), style);
            createCell(row, columnCount++, p.getMstdnsatnhap(), style);
            createCell(row, columnCount++, p.getTendnsatnhap(), style);
            createCell(row, columnCount++, p.getDiachidnsatnhap(), style);
            createCell(row, columnCount++, p.getMstdnchiatach(), style);
            createCell(row, columnCount++, p.getTendnchiatach(), style);
            createCell(row, columnCount++, p.getDiachidnchiatach(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
