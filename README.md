# 🛒 E-Commerce Backend — Spring Boot + AI Integration

A production-ready Spring Boot e-commerce REST API with **Spring AI** capabilities — featuring a RAG-powered chatbot, AI-generated product descriptions, semantic search, and a full product/order management system.

---

## 🚀 Live Demo

> API Base URL: `http://localhost:8080` (run locally — see setup below)

---

## ✨ Features

### 🛍️ Core E-Commerce
- **Product Management** — Full CRUD with image upload (up to 10MB)
- **Order Management** — Place and track orders with line items
- **Stock Management** — Real-time inventory tracking
- **Category Browsing** — Filter products by category

### 🤖 AI-Powered
- **RAG Chatbot** — Customer support chatbot using OpenAI + pgvector for context-aware answers
- **AI Product Descriptions** — Auto-generate engaging descriptions from product name & category
- **Semantic Search** — Find products using natural language queries
- **Vector Store** — PostgreSQL + pgvector for embedding storage and retrieval

---

## 🛠️ Tech Stack

| Component | Technology |
|-----------|------------|
| **Framework** | Spring Boot 4.0.5 |
| **Language** | Java 21 |
| **ORM** | Spring Data JPA + Hibernate |
| **Database** | PostgreSQL 16 + pgvector |
| **AI Integration** | Spring AI 2.0.0-M4 (OpenAI) |
| **Build Tool** | Maven |
| **Utilities** | Lombok, Jakarta Persistence |

---

## 📡 API Endpoints

### Products
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/products` | Get all products |
| `GET` | `/api/product/{id}` | Get product by ID |
| `GET` | `/api/product/{id}/image` | Retrieve product image |
| `POST` | `/api/product` | Add a new product |
| `PUT` | `/api/product/{id}` | Update a product |
| `DELETE` | `/api/product/{id}` | Delete a product |
| `POST` | `/api/product/generate-description` | AI-generate a product description |

> `generate-description` Query Params: `name`, `category`

### Orders
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/orders/place` | Place a new order |
| `GET` | `/api/orders` | Get all orders |

### AI Chatbot
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/chat/ask` | Ask the AI assistant |

> Query Param: `message`

### Health
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/hello` | Health check / welcome |

---

## 📁 Project Structure

```
ecom/
├── src/main/java/com/project/ecom/
│   ├── EcomApplication.java
│   ├── config/
│   │   └── ChatClientConfig.java          # Spring AI ChatClient setup
│   ├── controller/
│   │   ├── ChatBotController.java
│   │   ├── ProductController.java
│   │   └── OrderController.java
│   ├── model/
│   │   ├── Product.java
│   │   ├── Order.java
│   │   ├── OrderItem.java
│   │   └── dto/                           # Request/Response DTOs
│   ├── repo/
│   │   ├── ProductRepo.java
│   │   └── OrderRepo.java
│   └── service/
│       ├── ProductService.java
│       ├── OrderService.java
│       ├── ChatBotService.java
│       └── AiImageGeneratorService.java
├── src/main/resources/
│   ├── application.properties
│   ├── promt/
│   │   └── chatbot-rag-promt.st           # RAG prompt template
│   └── insert-data                        # Seed data
├── docker-compose.yml                     # PostgreSQL + pgvector
├── init.sql                               # DB initialization
└── pom.xml
```

---

## ⚙️ Configuration

### application.properties

```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5433/ecom
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update

# AI
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=gpt-3.5-turbo
spring.ai.openai.embedding.options.model=text-embedding-ada-002
spring.ai.vectorstore.pgvector.initialize-schema=true

# File Upload
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=15MB
```

> ⚠️ Never hardcode secrets — always use environment variables.

---

## 🐳 Docker Setup

```bash
docker-compose up -d
```

Starts PostgreSQL 16 with pgvector on port `5433`:
- **Database:** `ecom`
- **User:** `postgres`
- **Password:** set via `DB_PASSWORD` env variable

---

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Maven 3.6+ (or use included `mvnw`)
- Docker & Docker Compose
- OpenAI API Key

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/ecom-backend.git
cd ecom-backend
```

**2. Set environment variables**
```bash
# Linux / macOS
export OPENAI_API_KEY=your_key_here
export DB_PASSWORD=your_db_password

# Windows
set OPENAI_API_KEY=your_key_here
set DB_PASSWORD=your_db_password
```

**3. Start the database**
```bash
docker-compose up -d
```

**4. Build the project**
```bash
./mvnw clean package
# Windows
mvnw.cmd clean package
```

**5. Run the application**
```bash
./mvnw spring-boot:run
# Windows
mvnw.cmd spring-boot:run
```

App starts at: `http://localhost:8080`

---

## 🗂️ Data Models

### Product
| Field | Type |
|-------|------|
| `id` | Integer (PK, Auto) |
| `name` | String |
| `description` | String |
| `brand` | String |
| `price` | BigDecimal |
| `category` | String |
| `releaseDate` | Date |
| `productAvailable` | Boolean |
| `stockQuantity` | Integer |
| `imageData` | Byte[] (BLOB) |

### Order
| Field | Type |
|-------|------|
| `id` | Long (PK, Auto) |
| `orderId` | String (Unique) |
| `customerName` | String |
| `email` | String |
| `status` | String |
| `orderDate` | LocalDate |
| `orderItems` | List\<OrderItem\> |

---

## 🤖 How the AI Works

### RAG Chatbot
1. User query → embedded via OpenAI
2. pgvector performs semantic similarity search
3. Top matching documents retrieved as context
4. GPT-3.5-turbo generates response using RAG prompt template
5. Context-aware answer returned to user

### Product Description Generator
- Input: product `name` + `category`
- Output: AI-written product description via GPT-3.5-turbo

---

## 🧪 Running Tests

```bash
./mvnw test
```

---

## 🔮 Future Enhancements

- [ ] JWT Authentication & Authorization
- [ ] Payment gateway (Stripe / Razorpay)
- [ ] Email order notifications
- [ ] Product reviews & ratings
- [ ] Admin dashboard
- [ ] API rate limiting
- [ ] Swagger / OpenAPI docs

---

## 👤 Author

**SACHIN K**
- GitHub: [https://github.com/JDEV-SACHIN-K]
- LinkedIn: [www.linkedin.com/in/sachin-k-dev]


---

## 📄 License

MIT License — free to use and modify.
https://github.com/JDEV-SACHIN-K
www.linkedin.com/in/sachin-k-dev
