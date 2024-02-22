package net.codejava;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import net.codejava.codee.Codee;
import net.codejava.codee.CodeeService;
import net.codejava.corporation.Product;
import net.codejava.corporation.ProductExcelExporter;
import net.codejava.corporation.ProductService;
import net.codejava.individual.Individual;
import net.codejava.individual.IndividualService;
import net.codejava.role.Role;
import net.codejava.role.RoleService;
import net.codejava.user.Encode;
import net.codejava.user.User;
import net.codejava.user.UserDetailsServiceImpl;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class AppController {
    @Autowired
    private ProductService productService;
    @Autowired
    private IndividualService individualService;
    @Autowired
    private CodeeService codeeService;
    @Autowired
    private UserDetailsServiceImpl userService;
    @Autowired
    private RoleService roleService;

    private HttpHeaders headers(String name) {

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + name);
        header.add("Cache-Control", "no-cache, no-store,"
                + " must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;

    }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }
    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=khdn_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Product> listProducts = productService.listAll();

        ProductExcelExporter excelExporter = new ProductExcelExporter(listProducts);

        excelExporter.export(response);
    }

    @PostMapping(value = "/search")
    public String searchProducts(@ModelAttribute("p") Product p, Model model) {
        List<Product> listProducts = productService.searchProducts(p.getMst(), p.getTencty(), p.getDiachi(), p.getTinhtrang(), p.getTieuchitrongdiem(), p.getChicucthuequanly(),
                p.getCongchucid(), p.getLinhvuc(), p.getNganhnghekd(),
                p.getTkvnaccs(), p.getTkdaily(), p.getBoss(), p.getBossid());
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @RequestMapping("/individual")
    public String viewIndividualList(Model model) {
        List<Individual> listIndividuals = individualService.listAll();
        model.addAttribute("listIndividuals", listIndividuals);
        return "individual";
    }

    @PostMapping(value = "/search/individual")
    public String searchIndividuals(@ModelAttribute("individual") Individual individual, Model model) {
        List<Individual> listIndividuals = individualService.searchIndividuals(individual.getCn_id(), individual.getTencn(), individual.getQuoctich(), individual.getHokhau_thuongtru(),
                individual.getHokhau_tamtru(), individual.getDiachicssx(), individual.getLinhvuc(), individual.getTieuchi(), individual.getMahq(), individual.getCongchuc_id());
        model.addAttribute("listIndividuals", listIndividuals);
        return "individual";
    }

    @RequestMapping("/codee")
    public String viewCodeeList(Model model) {
        List<Codee> listCodees = codeeService.listAll();
        model.addAttribute("listCodees", listCodees);
        return "codee";
    }

    @RequestMapping("/user")
    public String viewUserList(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "user";
    }

    @RequestMapping("/new")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("c1List",codeeService.c1ListJSON());
        model.addAttribute("c2List",codeeService.c2ListJSON());
        model.addAttribute("c3List",codeeService.c3ListJSON());
        return "new_product";
    }

    @RequestMapping("/new/individual")
    public String showNewIndividualForm(Model model) {
        Individual individual = new Individual();
        model.addAttribute("individual", individual);
        return "new_individual";
    }

    @RequestMapping("/new/codee")
    public String showNewCodeForm(Model model) {
        Codee codee = new Codee();
        model.addAttribute("codee", codee);
        return "new_codee";
    }

    @RequestMapping("/new/user")
    public String showNewUserForm(Model model) {
//create "user" thymeleaf attribute (object in java) so that u can refer it with submitted form data
        User user = new User();
        model.addAttribute("user", user);
//create "role_options" thymeleaf attribute to show options in Roles select list of the form
        Set<Role> role_options = new HashSet<>(roleService.listAll());
        model.addAttribute("role_options", role_options);
        return "new_user";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_product");

        Product product = productService.get(id);
        mav.addObject("product", product);
        mav.addObject("c1List",codeeService.c1ListJSON());
        mav.addObject("c2List",codeeService.c2ListJSON());
        mav.addObject("c3List",codeeService.c3ListJSON());

        return mav;
    }

    @RequestMapping("/edit/individual/{id}")
    public ModelAndView showEditIndividualForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_individual");

        Individual individual = individualService.get(id);
        mav.addObject("individual", individual);

        return mav;
    }

    @RequestMapping("/edit/codee/{id}")
    public ModelAndView showEditCodeeForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_codee");
        Codee codee = codeeService.get(id);
        mav.addObject("codee", codee);

        List<String> stt_options = new ArrayList<String>();
        stt_options.add("1");
        stt_options.add("2");
        stt_options.add("3");
        mav.addObject("stt_options", stt_options);


        return mav;
    }

    @RequestMapping("/edit/user/{username}")
    public ModelAndView showEditUserForm(@PathVariable(name = "username") String username, Model model) {
        Set<Role> role_options = new HashSet<>(roleService.listAll());
        model.addAttribute("role_options", role_options);

        ModelAndView mav = new ModelAndView("edit_user");
        User user = userService.loadUserByUsername2(username);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/upload")
    public String showUploadProductForm() {
        return "upload_product";
    }

    @RequestMapping("/upload/individual")
    public String showUploadIndividualForm() {
        return "upload_individual";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @RequestMapping(value = "/save/individual", method = RequestMethod.POST)
    public String saveIndividual(@ModelAttribute("individual") Individual individual) {
        individualService.save(individual);
        return "redirect:/individual";
    }

    @RequestMapping(value = "/save/codee", method = RequestMethod.POST)
    public String saveCode(@ModelAttribute("codee") Codee codee) {
        System.out.println(codee.getName());
        codeeService.save(codee);
        return "redirect:/codee";
    }

    @RequestMapping(value = "/save/user", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadProduct(@RequestParam("file") MultipartFile file) {
        productService.savefile(file);
        return "redirect:/";
    }

    //		public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
//		String message = "";
//		if (ExcelHelper.hasExcelFormat(file)) {
//			try {
//				productService.savefile(file);
//				message = "Uploaded the file successfully: " + file.getOriginalFilename();
//				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//			} catch (Exception e) {
//				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//			}
//		}
//		message = "Please upload an excel file!";
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//	}
    @RequestMapping(value = "/upload/individual", method = RequestMethod.POST)
    public String uploadIndividual(@RequestParam("file") MultipartFile file) {
        individualService.savefile(file);
        return "redirect:/individual";
    }

    @GetMapping(path = "/files/{name}")
    public ResponseEntity<Resource> download
            (@PathVariable("name") String name) throws IOException {

        File file = new File("src/main/resources/" + name);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource
                (Files.readAllBytes(path));

        return ResponseEntity.ok().headers(this.headers(name))
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType
                        ("application/octet-stream")).body(resource);
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        productService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/delete/individual/{id}")
    public String deleteIndividual(@PathVariable(name = "id") Long id) {
        individualService.delete(id);
        return "redirect:/individual";
    }

    @RequestMapping("/delete/codee/{id}")
    public String deleteCode(@PathVariable(name = "id") Long id) {
        codeeService.delete(id);
        return "redirect:/codee";
    }

    @RequestMapping("/delete/user/{username}")
    public String deleteUser(@PathVariable(name = "username") String username) {
        userService.delete(username);
        return "redirect:/user";
    }

    @RequestMapping("/print/{id}")
    public ResponseEntity<Resource> printProduct(@PathVariable(name = "id") Long id) throws IOException, InvalidFormatException {
        Product product = productService.get(id);
        productService.print(product);
        //ben tren tao file, duoi day download file
        File file = new File("src/main/resources/templates/" + "khdn.docx");
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource
                (Files.readAllBytes(path));

        return ResponseEntity.ok().headers(this.headers("khdn.docx"))
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType
                        ("application/octet-stream")).body(resource);

    }
//	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
//
//		// check if file is empty
//		if (file.isEmpty()) {
//			attributes.addFlashAttribute("message", "Please select a file to upload.");
//			return "redirect:/";
//		}
//
//		// normalize the file path
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//		// save the file on the local file system
//		try {
//			Path path = Paths.get(UPLOAD_DIR + fileName);
//			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		// return success response
//		attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
//
//		return "redirect:/";
//	}

}
