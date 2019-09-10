package com.hometask;

import com.hometask.model.Customer;
import com.hometask.service.CustomerService;
import com.hometask.service.Server;
import spark.Spark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author AmirArreaza
 */


public class Main {

    public static void displayServices(){
        System.out.println("");
        System.out.println("You can enjoy the following services!");
        System.out.println("Get all users   (/Customers)");
        System.out.println("Get user        (/Customer/:id)");
        System.out.println("LogIn user      (/CustomerLogIn/:id)");
        System.out.println("Transfer Money! (/Customer/Transfer/:acc1/:acc2/:amount)");
        System.out.println("");
        System.out.println("Try them!");
    }

    public static void main(String[] args) throws IOException {
        Server instance = new Server();
        CustomerService custServ = new CustomerService();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println();
            System.out.println("************** Welcome to Revolut Home Task **************");
            System.out.println();
            System.out.println("Server is running OK!");
            System.out.println("");

            System.out.println("Users in memory: ");

            List<Customer> customers = custServ.getAllCustomers(instance.executeService("/Customers","GET"));

            customers.forEach(k -> System.out.println("Customer Id: " + k.getId()));

            if(custServ.logIn(instance.executeService("/CustomerLogIn/" + customers.get(0).getId(),"GET"))){
                System.out.println("User " + customers.get(0).getFirstName() + " Logged in! ");
                System.out.println("User Account 1 " + customers.get(0).getAccounts().get(0).toString());
                System.out.println("User Account 2 " + customers.get(0).getAccounts().get(1).toString());
            }
            displayServices();

            System.out.println("");
            System.out.println("Do you want to continue?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            String response = reader.readLine();
            if(response.equals("No")){
                Spark.stop();
                System.exit(0);
            }
            System.out.flush();
        }


    }


}
