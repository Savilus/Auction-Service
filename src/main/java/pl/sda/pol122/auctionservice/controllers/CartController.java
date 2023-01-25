package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.model.CartItem;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.DefaultCartService;
import pl.sda.pol122.auctionservice.services.DefaultProductService;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping()
public class CartController {

    private final DefaultCartService defaultCartService;

    private final DefaultProductService defaultProductService;


    @GetMapping("/addToCart/{productId}")
    public String addToCart(Model model, @PathVariable Integer productId) {
        defaultCartService.addProductToCart(defaultProductService.getProductById(productId));
        model.addAttribute("product", defaultProductService.getProductById(productId));
        return "redirect:/product/details/{productId}";
    }

    @GetMapping("/buyNow/{productId}")
    public String buyNow(Model model, @PathVariable Integer productId) {
        defaultCartService.addProductToCart(defaultProductService.getProductById(productId));
        model.addAttribute("product", defaultProductService.getProductById(productId));
        return "redirect:/cart";
    }

    @GetMapping("/deleteFromCart/{productId}")
    public String deleteFromCart(Model model, @PathVariable Integer productId) {
        Product productById = defaultProductService.getProductById(productId);
        CartItem cartItem = new CartItem(productById, 0);
        defaultCartService.deleteProductFromCart(cartItem);
        return "redirect:/cart";
    }


    @GetMapping(path = "/cart/checkout")
    public String loadCartCheckout() {
        return "checkout";
    }

    @GetMapping(path = "/cart")
    public String loadUserCart(Model model) {
        List<CartItem> cartItems = defaultCartService.getAllProducts();
        BigDecimal totalPriceOfCart = defaultCartService.getTotalPriceOfCart();
        model.addAttribute("price", totalPriceOfCart);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }
}

