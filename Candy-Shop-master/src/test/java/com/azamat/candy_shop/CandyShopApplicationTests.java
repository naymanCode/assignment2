package com.azamat.candy_shop;

import antlr.BaseAST;
import com.azamat.candy_shop.entities.Order;
import com.azamat.candy_shop.entities.OrderItem;
import com.azamat.candy_shop.entities.Product;
import com.azamat.candy_shop.entities.User;
import com.azamat.candy_shop.repos.OrderRepo;
import com.azamat.candy_shop.repos.ProductRepo;
import com.azamat.candy_shop.repos.UserRepo;
import com.azamat.candy_shop.service.UserRepositoryUserDetailsService;
import com.azamat.candy_shop.util.Utils;
import com.azamat.candy_shop.web.ShopController;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandyShopApplicationTests {
    BaseAST encoder;
    private static Order order;
    private static User user;
    private static User user2;
    private static UserRepositoryUserDetailsService service;
    @Mock
    private UserRepo userRepo;


    @Before
    public void setUp() throws Exception {
        order = new Order();
        user = new User();
        user2 = new User();
        service = new UserRepositoryUserDetailsService(userRepo);
    }

    @After
    public void tearDown() throws Exception {
        order = null;
    }


    @Test
    public void calculateOrderSumTest() {
            order.getOrderDetails().getOrderItems().add(new OrderItem(order.getOrderDetails(), new Product("bayan-sulu", 1.1, 10), 1));

            order.getOrderDetails().getOrderItems().add(new OrderItem(order.getOrderDetails(), new Product("rakhat", 1.1, 10), 1));
            order.calculateSum();
            double expected = order.getSum();
            assertEquals(2.2, expected, 0.05);
    }
    @Test
    public void moneyToDisplay() {
        assertEquals("1.00", Utils.moneyToDisplay(1));
        assertEquals("0.10", Utils.moneyToDisplay(0.1));
        assertEquals("1.11", Utils.moneyToDisplay(1.111));
    }
    @Test
    public void checkDB() {
        assertNotNull(userRepo.findById(1));
    }
    @Test
    public void authorityTest() {
        Collection<? extends GrantedAuthority> actuals = user.getAuthorities();
        Collection<? extends GrantedAuthority> expected = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        assertEquals(actuals, expected);
    }
}
