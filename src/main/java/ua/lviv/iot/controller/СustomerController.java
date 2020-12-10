package ua.lviv.iot.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.implementation.CustomerServiceImpl;
import ua.lviv.iot.model.Customer;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class СustomerController {
  @Getter
  private static final СustomerController INSTANCE = new СustomerController();
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private CustomerServiceImpl customerService = CustomerServiceImpl.getINSTANCE();

  public void createCustomer() {
    Customer customer = createCustomerInstanceFromUserInputs();
    customerService.create(customer);
    System.out.println("There are 1 row created");
  }

  public void updateCustomer() {
    Customer customer = createCustomerInstanceFromUserInputs();
    System.out.println("Input id(id) for Customer:  ");
    customer.setId(input.nextInt());
    customerService.update(customer);
    System.out.println("There are 1 row updated");
  }

  public void deleteCustomer() {
    System.out.println("Input id(id) for Customer");
    Integer deleteId = input.nextInt();
    int count = customerService.delete(deleteId);
    System.out.println("There are " + count + " rows deleted");
  }

  public void selectAllFromCustomer() {
    System.out.println("\nTable: Customer");
    List<Customer> customers = customerService.findAll();
    for (Customer customer : customers) {
      System.out.println(customer);
    }
  }

  public void selectFromCustomerById() {
    System.out.println("Input id(id) for Customer");
    Integer selectId = input.nextInt();
    Customer customer = customerService.findById(selectId);
    System.out.println(customer);
  }

  private Customer createCustomerInstanceFromUserInputs() {
    System.out.println("Input email(email) for Customer:  ");
    String email = input.nextLine();
    System.out.println("Input password(password) for Customer:  ");
    String password = input.nextLine();
    System.out.println("Input firstName(first_name) for Customer:  ");
    String first_name = input.nextLine();
    System.out.println("Input surname(surname) for Customer:  ");
    String surname = input.nextLine();
    System.out.println("Input birthDate(birth_date) for Customer:  ");
    Date birthDate = Date.valueOf(input.nextLine());
    System.out.println("Input country(country) for Customer:  ");
    String country = input.nextLine();
    System.out.println("Input phoneNumber(phone_number) for Customer:  ");
    String phoneNumber = input.nextLine();
    System.out.println("Input nickname(nickname) for Customer:  ");
    String nickname = input.nextLine();
    return new Customer()
        .setEmail(email)
        .setPassword(password)
        .setFirstName(first_name)
        .setSurname(surname)
        .setBirthDate(birthDate)
        .setCountry(country)
        .setPhoneNumber(phoneNumber)
        .setNickname(nickname);
  }
}
