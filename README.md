# Digital Wallet System

A full-stack Digital Wallet application built using Spring Boot.

## Features

• User Registration  
• Wallet Balance Management  
• Deposit Money  
• Withdraw Money  
• Transaction History  
• REST APIs  
• Simple Web UI  

## Tech Stack

Backend
- Java 21+
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL

Frontend
- HTML
- CSS
- JavaScript
- Fetch API

## API Endpoints

POST /users/register  
GET /wallet/{userId}  
GET /wallet/{userId}/balance  
POST /wallet/{userId}/deposit  
POST /wallet/{userId}/withdraw  
GET /wallet/{userId}/transaction  

## Project Structure

controllers  
service  
entity  
repositories  
DTO  

## How to Run

1. Clone repo
2. Setup PostgreSQL database
3. Update application.properties
4. Run DigitalWalletApplication
5. Open http://localhost:8080
