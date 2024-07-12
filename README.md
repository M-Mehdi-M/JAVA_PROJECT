MAHMOUDI Mohammadmehdi 322CB Project

The purpose of this project is to contribute to the implementation of functionalities for a
eBanking mobile application. Users will be able to create an account, they will be able to add money in
account, make transfers to other users and can exchange money, buy shares or
cryptocurrencies. To be able to help users, the application will be able to do
recommendations of shares that can be bought advantageously at that moment. Details about
the data format, as well as the commands to be implemented are provided in the sections
next.

Functionality
To be able to implement the functionalities for the eBanking application, you will need to
model the following entities:
User
It represents the user of the eBanking application, which will be uniquely identified according to the attribute
email (there cannot be two users with the same email) and will be able to have:
- email
- name
- first name
- Address
- a user's portfolio in the eBanking application which can contain:
- accounts in various types of currencies (the list of currencies accepted in the application will be
provided in the input currencies.txt file)
- shares (the list of companies whose shares can be bought will be provided in
input file stocks.txt)
- friends, a list of users of the application to whom the user can transfer money
Account
- Currency type (it can be a value only from the list of currencies accepted by the application)
- The amount
Actions
- Company name
- List of values ​​for the last 10 days
The attributes and way of organizing the entities is up to you, depending on the Design
Patterns and modeling chosen in project implementation.

Commands
In order to be able to implement the functionalities of the application, it will have to be able to execute a
series of commands, both those that create a user's account with personal data, and
and opening accounts in various currencies and then transactions in them (currency exchange,
money transfer to another friend). Any transfer order or purchase of
shares/cryptocurrencies will have to be reflected in the state of the accounts and the portfolio of one
user.
