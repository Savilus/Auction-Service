package pl.sda.pol122.auctionservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.dao.ProductRepository;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.ProductService;

import java.util.List;

@Controller
@RequestMapping()
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    public ProductController(ProductService productService,
                             ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }



    @GetMapping("product/details/{productId}")
    public String getProductsDetails(Model model,@PathVariable Integer productId){
        model.addAttribute("product", productService.getProductById(productId));
        return "productDetails";
    }


    @GetMapping(path = "/shop/houseProducts")
    public String loadHouseProductsList(Model model){
        List<Product> allHouseProducts = productService.getListOfProducts(1);
        model.addAttribute("houseProducts", allHouseProducts);
        return "houseProducts";
    }
    @GetMapping(path = "/shop/sportProducts")
    public String loadSportProductList(Model model){
        List<Product> allSportProducts = productService.getListOfProducts(2);
        model.addAttribute("sportProducts", allSportProducts);
        return "sportProducts";
    }
    @GetMapping(path = "/shop/electronicProducts")
    public String loadElectronicProducts(Model model){
        List<Product> allElectronicProducts = productService.getListOfProducts(3);
        model.addAttribute("electronicProducts", allElectronicProducts);
        return "electronicProducts";
    }

    @PostMapping()
    public String addProduct(ProductEntity productEntity){
        productRepository.save(productEntity);
        return "redirect:/products";
    }






}
