package pl.sda.pol122.auctionservice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.Order;
import pl.sda.pol122.auctionservice.model.User;

import java.util.List;


public interface UserService {

    UserEntity getUserById(Integer id);

    void deleteById(Integer id);

    void saveAccountStatus(Integer userId, boolean accountStatus);

    void saveAccountChanges(User user);

    void createUserAccount(User user);
    void createAdminAccount(User user);

    User getAuthenticatedUser();

    List<Order> getUserOrderHistory();

    List<User> listOfUsers();

}

