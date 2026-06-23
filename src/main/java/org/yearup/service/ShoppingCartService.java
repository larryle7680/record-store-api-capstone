package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

@Service
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService)
    {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId)
    {
        // load the user's cart rows
        List<CartItem> items = shoppingCartRepository.findByUserId(userId);
        ShoppingCart cart = new ShoppingCart();
         //look up each product, and build the ShoppingCart
        for(CartItem cartItem : items){

            //Getting the products ID from the cartItem and storing it individually as products
            Product products = productService.getById(cartItem.getProductId());
            //Create an empty Object
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setProduct(products);
            shoppingCartItem.setQuantity(cartItem.getQuantity());

            cart.add(shoppingCartItem);
        }

        return cart;
    }

    // add additional methods here
    //Creating a cartItem to add to cart
    //Use CartItem because I'm trying to save individual things into a collection
    public CartItem addToCart(CartItem cartItem){
        return shoppingCartRepository.save(cartItem);
    }
}
