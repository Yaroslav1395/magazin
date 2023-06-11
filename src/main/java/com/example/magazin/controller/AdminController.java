package com.example.magazin.controller;

import com.example.magazin.dto.category.CategoryDto;
import com.example.magazin.dto.order.OrderDto;
import com.example.magazin.dto.product.ProductForSaveDto;
import com.example.magazin.dto.product.ProductNewDto;
import com.example.magazin.dto.productImageDto.ProductImageDto;
import com.example.magazin.dto.subscribe.SubscribeDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.entity.user.Role;
import com.example.magazin.service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("quantum/admin")
@AllArgsConstructor
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private SubscribeService subscribeService;
    private CategoryService categoryService;
    private ProductService productService;
    private OrderService orderService;

    @GetMapping
    public String getAdmin(Model model, ProductNewDto productNewDto){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }
        return "admin";
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model, ProductNewDto productNewDto){
        List<UserDto> userDtoList = userService.getAllUsers();
        if(userDtoList != null){
            model.addAttribute("users", userDtoList);
        }
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }
        return "admin";
    }

    @GetMapping("/user")
    public String getUserByEmail(@RequestParam(name="email") String email, Model model, ProductNewDto productNewDto){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        UserDto userDto = userService.getUserByEmail(email);
        if(userDto != null){
            model.addAttribute("user", userDto);
        }else {
            model.addAttribute("message", "Такого пользователя нет");
        }
        return "admin";
    }

    @GetMapping("/subscribes")
    public String getAllSubscribes(Model model, ProductNewDto productNewDto){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        List<SubscribeDto> subscribeDtoList = subscribeService.getAllSubscribes();
        if(subscribeDtoList != null){
            model.addAttribute("subscribes", subscribeDtoList);
        }

        return "admin";
    }

    @GetMapping("/categories")
    public String getAllCategories(Model model, ProductNewDto productNewDto){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        if(categoryDtoList != null){
            model.addAttribute("categories", categoryDtoList);
        }

        return "admin";
    }

    @PostMapping("/newCategory")
    public String saveNewCategory(
            @RequestParam MultipartFile image,
            @RequestParam String categoryName,
            ProductNewDto productNewDto,
            Model model) throws IOException {
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        String filePath = "C:\\Atractor\\WorkTable\\magazin\\src\\main\\resources\\static\\images\\categories\\" + image.getOriginalFilename();
        String srcPath = "images/categories/" + image.getOriginalFilename();
        CategoryDto categoryDto = categoryService.saveCategory(CategoryDto.builder()
                        .name(categoryName)
                        .filePath(filePath)
                        .src(srcPath)
                .build());

        if(categoryDto != null){
            model.addAttribute("newCategory", categoryDto);
        }

        image.transferTo(new File(filePath));
        return "admin";
    }

    @GetMapping("/categoryId")
    public String getCategoryById(@RequestParam Integer categoryId, Model model, ProductNewDto productNewDto){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);

        if(categoryDto !=null){
            model.addAttribute("categoryById", categoryDto);
        }else {
            model.addAttribute("message", "Нет категории с таким ID");
        }

        return "admin";
    }

    @GetMapping("/allProducts")
    public String getAllProducts(Model model, ProductNewDto productNewDto){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        List<ProductForSaveDto> productForSaveDtos = productService.getAllProductsAsProductForSaveDto();
        model.addAttribute("products", productForSaveDtos);
        return "admin";
    }

    @GetMapping("/getProductsBy/categoryId")
    public String getProductsByCategoryId(@RequestParam Integer categoryId, Model model, ProductNewDto productNewDto){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        List<ProductForSaveDto> productForSaveDtos = productService.getAllProductsAsProductForSaveDtoByCategoryId(categoryId);

        if(productForSaveDtos == null || productForSaveDtos.isEmpty()){
            model.addAttribute("message", "В этой категории нет продуктов");
        }else {
            model.addAttribute("products", productForSaveDtos);
        }
        return "admin";
    }

    @GetMapping("/getProductsBy/keyword")
    public String getProductByKeyword(@RequestParam String keyword, Model model, ProductNewDto productNewDto){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        List<ProductForSaveDto> productForSaveDtos = productService.getProductsAsProductForSaveDtoByKeyword(keyword);
        if(productForSaveDtos != null && !productForSaveDtos.isEmpty()){
            model.addAttribute("products", productForSaveDtos);
        }else {
            model.addAttribute("message", "Нет продукта с таким именем");
        }
        return "admin";
    }

    @GetMapping("/getProductsBy/companyId")
    public String getProductsByCompanyId(@RequestParam Integer companyId, Model model, ProductNewDto productNewDto){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        List<ProductForSaveDto> productForSaveDtos = productService.getProductsAsProductForSaveDtoByCompanyId(companyId);

        if(productForSaveDtos == null || productForSaveDtos.isEmpty()){
            model.addAttribute("message", "Нет продуктов данной компании");
        }else {
            model.addAttribute("products", productForSaveDtos);
        }
        return "admin";
    }

    @PostMapping("/newProduct")
    public String saveProduct(
            @ModelAttribute @Validated(OnCreate.class) ProductNewDto productNewDto,
            BindingResult bindingResult,
            Model model) throws IOException {
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        List<ProductImageDto> productImagesDto = new ArrayList<>();
        if(bindingResult.hasErrors()){
            model.addAttribute("message", "Ввели некорректные данные при создании продукта");
        }else {
            for(MultipartFile file : productNewDto.getFiles()){
                if(!file.isEmpty()){
                    String filePath = "C:\\Atractor\\WorkTable\\magazin\\src\\main\\resources\\static\\images\\products\\" + file.getOriginalFilename();
                    String src = "images/products/" + file.getOriginalFilename();
                    productImagesDto.add(ProductImageDto.builder()
                            .filePath(filePath)
                            .src(src)
                            .build());
                    file.transferTo(new File(filePath));
                }
            }
            ProductForSaveDto productForSaveDto = productService.save(productNewDto, productImagesDto);

            if(productForSaveDto != null){
                model.addAttribute("newProduct", productForSaveDto);
            }
        }

        return "admin";
    }

    @GetMapping("/allOrders")
    public String getAllOrders(Model model, ProductNewDto productNewDto){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        List<OrderDto> orderDtos = orderService.getAllOrders();

        if(orderDtos != null){
            model.addAttribute("orders", orderDtos);
        }else {
            model.addAttribute("message", "ордера не найдены");
        }

        return "admin";
    }

    @GetMapping("/getOrderBy/userEmail")
    public String getOrdersByUserEmail(
            Model model, ProductNewDto productNewDto, @RequestParam String userEmail){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }
        List<OrderDto> orderDtos = new ArrayList<>();
        if(userEmail != null){
            orderDtos = orderService.getOrdersByUserEmail(userEmail);
        }

        if(orderDtos.isEmpty()){
           model.addAttribute("message", "Нет заказов с таким email");
        }else {
            model.addAttribute("orders", orderDtos);
        }
        return "admin";
    }

    @PostMapping("/getOrderBy/date")
    public String getOrdersByDate(
            Model model,
            ProductNewDto productNewDto,
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate){
        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }
        List<OrderDto> orderDtos = new ArrayList<>();
        if(fromDate != null && toDate != null){
            orderDtos = orderService.getOrderByDate(fromDate, toDate);
        }

        if(orderDtos.isEmpty()){
            model.addAttribute("message", "Нет заказов в этом периоде");
        }else {
            model.addAttribute("orders", orderDtos);
        }
        return "admin";
    }

}
