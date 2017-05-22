package com.itis.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itis.shop.dto.common.AbstractEntityDto;
import com.itis.shop.model.User;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 18.05.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends AbstractEntityDto {


    private String address;
    private String fio;
    private String email;
    private String password;
    private String confirmPassword;
    private Set<OrderDto> orders = new HashSet<>();

    public UserDto() {
    }

    public UserDto(User user) {
        super(user);
        this.address = user.getAddress();
        this.fio = user.getFio();
        this.email = user.getEmail();
        this.orders = user.getOrders().stream().map(OrderDto::new).collect(Collectors.toSet());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderDto> orders) {
        this.orders = orders;
    }
}
