package oneTomany.demo.controller;

import oneTomany.demo.model.Cart;
import oneTomany.demo.model.Item;
import oneTomany.demo.repository.cart;
import oneTomany.demo.repository.item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class controller {
    @Autowired
    private cart repocart;

    @Autowired
    private item repoitem;

    private List<Item> listofitems = new ArrayList<>();

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/index");
        List<Cart> carrinho= this.repocart.findAll();
        mv.addObject("cart",carrinho);
        System.out.println("Carrinho size: " + carrinho.size());
        System.out.println("ItemList size: " + carrinho.get(0).getItems().size());

        return mv;
    }

    @GetMapping("/newcart")
    public ModelAndView createcart(){
        ModelAndView mv = new ModelAndView("/newcart");
        return mv;
    }





    @PostMapping("/createItem")
    public String createItem(Model model, Item item){
        System.out.println("Inside Create item");
        listofitems.add(item);
       System.out.println("Item added to list");
        model.addAttribute("listofitem", listofitems);
        return "newcart";
    }

    @PostMapping("/finish")
    public ModelAndView finish(Cart cart){
        ModelAndView mv = new ModelAndView("/index");
        for(int i =0; i < listofitems.size(); i++){
            listofitems.get(i).setCart(cart);
        }
        cart.setItems(listofitems);
        this.repocart.save(cart);
        listofitems.clear();
        return mv;
    }



    @GetMapping("/{id}")
    public ModelAndView showcarts(@PathVariable Long id){
        Optional<Cart> cart = this.repocart.findById(id);
        if(cart.isPresent()){
            System.out.println("cart found");
            ModelAndView mv = new ModelAndView("/showCart");
            mv.addObject("cart", cart);
            System.out.println(cart.get().getId());
            return mv;
        }else{
            System.out.println("Cart Not found!");
            return new ModelAndView("redirect: /index");
        }


    }

}
