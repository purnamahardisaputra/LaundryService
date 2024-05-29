/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Purnama Hardi Saputra
 * Created: 26 May 2024
 */

CREATE DATABASE dblaundry;
USE dbLaundry;

CREATE TABLE dataadmin(
    username VARCHAR(30),
    password VARCHAR(30),
    code VARCHAR(10)
);

CREATE TABLE transaksi(
    username VARCHAR(30),
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    customer VARCHAR(30),
    items DOUBLE,
    price DOUBLE,
    typeService VARCHAR(20),
    typeDay VARCHAR(15),
    typeLaundry VARCHAR(20),
    typePayment VARCHAR(10),
    status VARCHAR(15)
);

CREATE TABLE fotoadmin(
    username VARCHAR(30),
    foto LONGBLOB
);