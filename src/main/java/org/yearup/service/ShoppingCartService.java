package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.*;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;
import java.util.Optional;

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
    public CartItem addToCart(int userId, int productId) {

        //Make sure the item exists, By finding the userID and ProductID
        Optional <CartItem> existingItem =
                shoppingCartRepository.findByUserIdAndProductId(userId, productId);

        //If there's an item that already exists in the CartItem, It'll just add another count to the quantity and save it
        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            return shoppingCartRepository.save(cartItem);
        }

        //Create new cartItem and set UserId,ProductId and its quantity
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setProductId(productId);
        cartItem.setQuantity(1);

        return shoppingCartRepository.save(cartItem);
    }


}
