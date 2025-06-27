## ☆ THE DOLL HOUSE SHOP - API ☆ ##

The Doll House Shop is a comprehensive Spring Boot REST API for a full-featured e-commerce platform.

This robust backend API powers an online shopping experience, complete with product management, user authentication, shopping cart functionalities, and profile creation. Built with Spring Boot and MySQL, this API provides all the essential features needed for a modern e-commerce application.

The project demonstrates advanced backend development skills including JSON/JWT authentication, role-based access control, RESTful API design, and comprehensive data management. From browsing products by category to completing secure checkout processes, EasyShop handles the complete customer journey through well-structured, maintainable code.

---

## ☆ Project Overview ☆ 

This project simulates a real-world e-commerce backend development scenario where you inherit an existing codebase, fix critical bugs, and implement new features. 

**☆ Core Technologies:**
- **Spring Boot** - Main framework for REST API development
- **MySQL** - Database for persistent data storage  
- **JSON Authentication** - Secure user authentication and authorization
- **Postman** - API testing and endpoint validation
- **Maven** - Dependency management and build automation

**☆ Key Features Implemented:**
- User registration and JWT-based authentication
- Complete category management system (CRUD operations)
- Advanced product search and filtering capabilities
- Shopping cart functionality with persistent storage
- User profile management
- Order processing and checkout system
- Role-based access control (USER/ADMIN permissions)

---

## ☆ Application Architecture ☆

The Doll House Shop API follows a layered architecture pattern with clear separation of concerns:

### ☆ Authentication Layer
- The AuthenticationController handles user registration and login processes
- JSON tokens are generated upon successful authentication and required for protected endpoints
- Role-based access control ensures only administrators can modify products and categories

### ☆ Categories Management
- The CategoriesController provides CRUD operations for product categories
- Only administrators can create, update, or delete categories
- MySqlCategoriesDao handles all database interactions for category data
- Categories serve as the primary organization method for products

### ☆ Products Management  
- The ProductsController offers comprehensive product management capabilities
- Advanced filtering supports search by category, price range, and color
- Bug fixes implemented to resolve search accuracy and duplicate product issues
- Administrative controls prevent unauthorized product modifications

### ☆ Shopping Cart System
- The ShoppingCartController manages user-specific cart operations
- Persistent cart storage maintains items between user sessions
- Quantity management allows users to add, update, and remove items
- Cart totals calculated dynamically including line items and grand total

### ☆ User Profile Management
- The ProfileController enables users to view and update personal information
- MySqlProfileDao handles profile data persistence
- Automatic profile creation during user registration process

---

## ☆ API Endpoints ☆ 

### Authentication Endpoints
```
POST /register          - Create new user account
POST /login            - Authenticate existing user
```

### Category Management
```
GET    /categories      - Retrieve all categories
GET    /categories/{id} - Get specific category
POST   /categories      - Create new category (ADMIN only)
PUT    /categories/{id} - Update category (ADMIN only) 
DELETE /categories/{id} - Delete category (ADMIN only)
```

### Product Operations
```
GET    /products        - List products with optional filters
GET    /products/{id}   - Get specific product details
POST   /products        - Add new product (ADMIN only)
PUT    /products/{id}   - Update product (ADMIN only)
DELETE /products/{id}   - Remove product (ADMIN only)
```

**Product Filtering Parameters:**
- `cat` - Filter by category ID
- `minPrice` / `maxPrice` - Price range filtering
- `color` - Filter by product color

### Shopping Cart Features
```
GET    /cart                    - View current user's cart
POST   /cart/products/{id}      - Add product to cart
PUT    /cart/products/{id}      - Update product quantity
DELETE /cart                    - Clear entire cart
```

### User Profile
```
GET /profile    - View user profile information
PUT /profile    - Update profile details
```

---

## ☆ Database Structure ☆

The application utilizes a MySQL database with the following key tables:

- **users** - User account information and authentication data
- **categories** - Product category definitions
- **products** - Complete product catalog with pricing and details
- **shopping_cart** - User cart items with quantities
- **profiles** - Extended user profile information

---

## ☆ Bug Fixes Implemented ☆ 

### ☆ Search Functionality Bug
**Issue:** Product search was returning incorrect or incomplete results when filtering by multiple criteria.

**Solution:** Revised the search logic in the MySQLProductDao to properly handle the correct query to execute that combined filters (category, price range, color) to ensure accurate query parameter processing.

### ☆ Product Duplication Bug  
**Issue:** Product updates were creating duplicate entries instead of modifying existing records.

**Solution:** Fixed the PUT endpoint logic to properly identify and update existing products by ID, preventing unwanted duplicates in the database.

---

## ☆ Testing Strategy ☆

**Postman Integration:**
- Comprehensive test suites created for all endpoints
- Authentication token management for protected routes
- Parameter testing for search and filter functionality
- Error handling validation for edge cases

**Unit Testing:**
- JUnit tests implemented for critical business logic
- DAO layer testing to ensure proper database interactions
- Controller testing for request/response validation

---

## ☆ Security Features ☆ 

- **JSON Authentication** - Stateless authentication using JSON Web Tokens
- **Role-Based Access Control** - Separate permissions for USER and ADMIN roles
- **Password Encryption** - Secure password storage using industry standards
- **Input Validation** - Request parameter validation to prevent injection attacks

---

## ☆ Setup Instructions ☆

1. **Database Setup**
   ```sql
   -- Execute the provided create_database.sql script in MySQL Workbench
   -- This creates the easyshop database with sample data
   ```

2. **Application Configuration**
   ```properties
   # Update application.properties with your MySQL credentials
   spring.datasource.url=jdbc:mysql://localhost:3306/easyshop
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   # API will be available at http://localhost:8080
   ```

4. **Test with Sample Users**
   - Username: `admin` / Password: `password` (ADMIN role)
   - Username: `user` / Password: `password` (USER role)
   - Username: `george` / Password: `password` (USER role)

---

## ☆ Future Enhancements ☆

**Potential Version 3 Features:**
- Product reviews and ratings system
- Inventory management with stock tracking
- Advanced recommendation engine
- Order history and tracking
- Payment gateway integration
- Email notification system
- Administrative dashboard
- Multi-currency support

---

## ☆ Key Learning Outcomes ☆ 

This project demonstrates proficiency in:
- **RESTful API Design** - Following REST principles for clean, maintainable endpoints
- **Spring Boot Framework** - Leveraging Spring's powerful features for rapid development
- **Database Integration** - Implementing efficient data access patterns with MySQL
- **Authentication & Security** - JWT implementation and role-based access control
- **Bug Resolution** - Systematic debugging and testing methodologies
- **Code Organization** - Clean architecture with proper separation of concerns

---

## ☆ SCREENSHOTS ☆ ##

**☆ BACK END CODE ☆**
![Screenshot 2025-06-27 073913](https://github.com/user-attachments/assets/05bdbc3c-25f1-480a-9453-6df87754e7f1)
**☆ POSTMAN ☆**
![Screenshot 2025-06-26 191213](https://github.com/user-attachments/assets/53478623-609c-4cde-84ee-1d62ae14b1b9)
**☆ SQL ☆**
![Screenshot 2025-06-26 191240](https://github.com/user-attachments/assets/6bdd83e5-517b-43f1-bc89-aa301f16be59)



**☆ COMPLETED BY: JAYLA JONES - YEAR UP SOFTWARE-DEV STUDENT ☆**
