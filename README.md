<img width="1680" height="1050" alt="Screenshot 2025-12-21 at 9 42 24 PM" src="https://github.com/user-attachments/assets/d96a8ea5-8798-4703-8761-e1bacca4e80e" />
![Uploading Screenshot 2025-12-21 at 9.42.24 PM.p
<img width="1680" height="1050" alt="Screenshot 2025-12-21 at 9 53 59 PM" src="https://github.com/user-attachments/assets/3a0912d8-ca7d-40f0-8a5b-ad163e5857fd" />
<img width="1680" height="1050" alt="Screenshot 2025-12-21 at 9 52 29 PM" src="https://github.com/user-attachments/assets/31054496-e502-4561-b36b-23d96f607750" />
<img width="1680" height="1050" alt="Screenshot 2025-12-21 at 9 46 48 PM" src="https://github.com/user-attachments/assets/85b8c652-99ce-486b-8149-ff3fe073701f" />
<img width="1680" height="1050" alt="Screenshot 2025-12-21 at 9 45 47 PM" src="https://github.com/user-attachments/assets/bdd58325-7824-4aab-87ef-b65b350152d8" />
ng…]()


# 🧾 Transaction & Wallet Management Service

## 📌 Overview

This is a **Spring Boot REST API** that simulates a simple **wallet-based transaction system**.
It supports **wallet credit/debit by admin**, **order creation by client**, and **order & wallet tracking**.

Authentication is **simulated using a `client-id` request header**, as per assignment requirements.

---

## 🏗️ Tech Stack

* Java 17
* Spring Boot 4.x
* Spring Data JPA (Hibernate)
* MySQL
* Maven
* Postman (for API testing)

---

## 📂 Project Structure

```
com.wallet.transaction
├── controller
│   ├── OrderController
│   └── WalletController
├── service
│   ├── OrderService
│   ├── WalletService
│   └── impl
├── repository
├── entity
├── dto
└── config
```

---

## 🧑‍💼 Roles

| Role   | Responsibility                        |
| ------ | ------------------------------------- |
| ADMIN  | Credit / Debit client wallets         |
| CLIENT | Create orders and view wallet balance |

---

## 🔐 Authentication Strategy

* No JWT / OAuth implemented (not required for assignment)
* Each request contains a **`client-id` header**
* Backend validates role using the `users` table

Example:

```
client-id: CLIENT_001
```

---

## 🗄️ Database Setup

### 1️⃣ Create Database

```sql
CREATE DATABASE transaction_db;
```

### 2️⃣ Tables

Hibernate auto-creates:

* `users`
* `wallets`
* `orders`
* `wallet_ledger`

---

## 👤 Initial Data (Required)

### Create ADMIN user

```sql
INSERT INTO users (client_id, role)
VALUES ('ADMIN_001', 'ADMIN');
```

### Create CLIENT user

```sql
INSERT INTO users (client_id, role)
VALUES ('CLIENT_001', 'CLIENT');
```

### Create wallet for client

```sql
INSERT INTO wallets (user_id, wallet_balance)
SELECT id, 0 FROM users WHERE client_id = 'CLIENT_001';
```

> ℹ️ User & wallet seeding is intentionally done via SQL for simplicity.

---

## 🔗 API Endpoints

### 1️⃣ Credit Wallet (ADMIN)

```
POST /admin/wallet/credit
```

**Headers**

```
client-id: ADMIN_001
```

**Body**

```json
{
  "clientId": "CLIENT_001",
  "amount": 5000
}
```

---

### 2️⃣ Debit Wallet (ADMIN)

```
POST /admin/wallet/debit
```

---

### 3️⃣ Get Wallet Balance (CLIENT)

```
GET /wallet/balance
```

**Headers**

```
client-id: CLIENT_001
```

**Response**

```json
{
  "balance": 4500
}
```

---

### 4️⃣ Create Order (CLIENT)

```
POST /orders
```

**Headers**

```
client-id: CLIENT_001
```

**Body**

```json
{
  "amount": 500
}
```


---

### 5️⃣ Get Order Details (CLIENT)

```
GET /orders/{orderId}
```

---

## 🔄 Order Flow

1. Admin credits wallet
2. Client creates order
3. Wallet balance is atomically debited
4. Order is stored with status `CREATED`
5. External fulfillment API is called
6. Fulfillment ID is stored with order

---

## ⚙️ Transaction Safety

* Wallet debit operations use **pessimistic locking**
* All critical operations are wrapped in `@Transactional`
* Ledger entries are recorded for each wallet change

---



//Prompts
1).Suggest JPA entity classes and database relationships for users, wallets, orders, and wallet ledger entries in a transactional wallet system. Focus on correctness and simplicity.
2).How can I implement atomic wallet debit operations in Spring Boot using JPA and MySQL to prevent race conditions during concurrent order creation?


