# User Management Service

Dự án User Management Service là một hệ thống quản lý người dùng được xây dựng bằng Spring Boot, cung cấp các chức năng xác thực và phân quyền dựa trên JWT (JSON Web Token) với hệ thống Role-Based Access Control (RBAC).

## 📋 Mục lục

- [Tổng quan](#tổng-quan)
- [Tính năng chính](#tính-năng-chính)
- [Công nghệ sử dụng](#công-nghệ-sử-dụng)
- [Cấu trúc dự án](#cấu-trúc-dự-án)
- [Yêu cầu hệ thống](#yêu-cầu-hệ-thống)
- [Cài đặt và chạy dự án](#cài-đặt-và-chạy-dự-án)
- [Cấu hình](#cấu-hình)
- [API Documentation](#api-documentation)
- [Bảo mật](#bảo-mật)
- [Kiến trúc hệ thống](#kiến-trúc-hệ-thống)

## 🎯 Tổng quan

User Management Service là một RESTful API service cho phép:
- Quản lý người dùng (tạo, đọc, cập nhật, xóa)
- Xác thực người dùng bằng JWT
- Quản lý vai trò (Roles) và quyền (Permissions)
- Phân quyền truy cập dựa trên vai trò và quyền của người dùng
- Token management (refresh, logout, introspect)

## ✨ Tính năng chính

### 1. Quản lý Người dùng
- ✅ Đăng ký người dùng mới
- ✅ Xem danh sách người dùng (yêu cầu quyền ADMIN)
- ✅ Xem thông tin người dùng theo ID
- ✅ Xem thông tin cá nhân (my-info)
- ✅ Cập nhật thông tin người dùng
- ✅ Xóa người dùng (yêu cầu quyền ADMIN)
- ✅ Validation dữ liệu đầu vào (email, số điện thoại, mật khẩu, ngày sinh)

### 2. Xác thực và Ủy quyền
- ✅ Đăng nhập và nhận JWT token
- ✅ Refresh token để gia hạn phiên đăng nhập
- ✅ Logout và vô hiệu hóa token
- ✅ Introspect token để kiểm tra tính hợp lệ
- ✅ Phân quyền dựa trên Role và Permission
- ✅ Bảo mật mật khẩu bằng BCrypt

### 3. Quản lý Role và Permission
- ✅ Tạo, xem, xóa Role
- ✅ Tạo, xem, xóa Permission
- ✅ Gán Permission cho Role
- ✅ Gán Role cho User

### 4. Bảo mật
- ✅ JWT token với thuật toán HS512
- ✅ Token blacklisting (vô hiệu hóa token khi logout)
- ✅ Method-level security với `@PreAuthorize` và `@PostAuthorize`
- ✅ Custom JWT decoder với token validation
- ✅ Global exception handling

## 🛠 Công nghệ sử dụng

### Core Framework
- **Spring Boot 3.3.2** - Framework chính
- **Java 21** - Ngôn ngữ lập trình
- **Maven** - Quản lý dependencies

### Database & Persistence
- **MySQL** - Cơ sở dữ liệu quan hệ
- **Spring Data JPA** - ORM framework
- **Hibernate** - JPA implementation

### Security
- **Spring Security** - Framework bảo mật
- **Spring OAuth2 Resource Server** - Xử lý JWT tokens
- **Nimbus JOSE + JWT** - Thư viện xử lý JWT
- **BCrypt** - Mã hóa mật khẩu

### Utilities & Tools
- **Lombok** - Giảm boilerplate code
- **MapStruct** - Object mapping
- **Bean Validation** - Validation dữ liệu đầu vào
- **Spring Boot DevTools** - Công cụ phát triển

## 📁 Cấu trúc dự án

```
user-service/
├── src/
│   ├── main/
│   │   ├── java/com/Tmh3101/user_manager/
│   │   │   ├── configuration/          # Cấu hình Spring
│   │   │   │   ├── ApplicationInitConfig.java    # Khởi tạo dữ liệu mặc định
│   │   │   │   ├── CustormJwtDecoder.java        # Custom JWT decoder
│   │   │   │   ├── JwtAuthenticationEntryPoint.java
│   │   │   │   └── SecurityConfig.java           # Cấu hình bảo mật
│   │   │   ├── controller/             # REST Controllers
│   │   │   │   ├── AuthenticationController.java
│   │   │   │   ├── UserController.java
│   │   │   │   ├── RoleController.java
│   │   │   │   └── PermissionController.java
│   │   │   ├── dto/                    # Data Transfer Objects
│   │   │   │   ├── request/            # Request DTOs
│   │   │   │   └── response/           # Response DTOs
│   │   │   ├── entity/                 # JPA Entities
│   │   │   │   ├── User.java
│   │   │   │   ├── Role.java
│   │   │   │   ├── Permission.java
│   │   │   │   └── InvalidatedToken.java
│   │   │   ├── exception/              # Exception handling
│   │   │   │   ├── AppException.java
│   │   │   │   ├── ErrorCode.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── mapper/                 # MapStruct mappers
│   │   │   ├── repo/                   # JPA Repositories
│   │   │   ├── service/                # Service interfaces
│   │   │   │   └── Impl/               # Service implementations
│   │   │   ├── validator/              # Custom validators
│   │   │   │   ├── DobConstraint.java
│   │   │   │   └── DobValidator.java
│   │   │   └── UserManagerApplication.java
│   │   └── resources/
│   │       └── application.yaml        # Cấu hình ứng dụng
│   └── test/                           # Test files
├── pom.xml                             # Maven configuration
└── README.md                           # Tài liệu dự án
```

## 💻 Yêu cầu hệ thống

- **JDK 21** hoặc cao hơn
- **Maven 3.6+**
- **MySQL 8.0+**
- **IDE** (IntelliJ IDEA, Eclipse, VS Code) - tùy chọn

## 🚀 Cài đặt và chạy dự án

### 1. Clone repository

```bash
git clone <repository-url>
cd user-service
```

### 2. Tạo database MySQL

Tạo database mới trong MySQL:

```sql
CREATE DATABASE `user-manager` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Cấu hình database

Chỉnh sửa file `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/user-manager
    username: root          # Thay đổi theo cấu hình của bạn
    password:              # Nhập mật khẩu MySQL của bạn
    driver-class-name: com.mysql.cj.jdbc.Driver
```

### 4. Cấu hình JWT

Trong file `application.yaml`, bạn có thể tùy chỉnh:

```yaml
jwt:
  signerKey: "your-secret-key-here-minimum-64-characters"  # Thay đổi key bảo mật
  valid-duration: 60        # Thời gian hiệu lực token (giây)
  refreshable-duration: 120 # Thời gian có thể refresh token (giây)
```

**Lưu ý**: `signerKey` phải có độ dài tối thiểu 64 ký tự cho thuật toán HS512.

### 5. Build và chạy dự án

Sử dụng Maven wrapper:

```bash
# Windows
mvnw.cmd clean install

# Linux/Mac
./mvnw clean install
```

Hoặc sử dụng Maven đã cài đặt:

```bash
mvn clean install
```

### 6. Chạy ứng dụng

```bash
# Sử dụng Maven wrapper
./mvnw spring-boot:run

# Hoặc chạy file JAR
java -jar target/user-manager-0.0.1-SNAPSHOT.jar
```

Ứng dụng sẽ chạy tại: `http://localhost:8080`

### 7. Tài khoản mặc định

Khi ứng dụng khởi động lần đầu, hệ thống sẽ tự động tạo tài khoản admin:

- **Email**: `admin@gmail.com`
- **Password**: `admin`

**⚠️ CẢNH BÁO**: Hãy đổi mật khẩu ngay sau lần đăng nhập đầu tiên!

## ⚙️ Cấu hình

### Database Configuration

Hibernate sẽ tự động tạo/update schema khi khởi động ứng dụng (`ddl-auto: update`).

### JWT Configuration

- **Algorithm**: HS512 (HMAC-SHA512)
- **Token Structure**: Header + Payload + Signature
- **Claims**: 
  - `sub`: Email của người dùng
  - `iss`: Issuer (Tmh3101.com)
  - `iat`: Thời gian phát hành
  - `exp`: Thời gian hết hạn
  - `jti`: JWT ID (để quản lý blacklist)
  - `scope`: Danh sách roles và permissions

### Security Configuration

Các endpoint công khai (không cần authentication):
- `POST /users/add` - Đăng ký người dùng mới
- `POST /auth/token` - Đăng nhập
- `POST /auth/introspect` - Kiểm tra token
- `POST /auth/logout` - Đăng xuất
- `POST /auth/refresh` - Refresh token

Tất cả các endpoint khác yêu cầu JWT token hợp lệ.

## 📚 API Documentation

### Base URL
```
http://localhost:8080
```

### Response Format

Tất cả API responses đều có format chuẩn:

```json
{
  "code": 1000,
  "message": "Success message (optional)",
  "result": { ... }
}
```

### Authentication APIs

#### 1. Đăng nhập (Login)

```http
POST /auth/token
Content-Type: application/json

{
  "email": "admin@gmail.com",
  "password": "admin"
}
```

**Response:**
```json
{
  "code": 1000,
  "result": {
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "authenticated": true
  }
}
```

#### 2. Kiểm tra Token (Introspect)

```http
POST /auth/introspect
Content-Type: application/json

{
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
```

**Response:**
```json
{
  "code": 1000,
  "result": {
    "valid": true
  }
}
```

#### 3. Refresh Token

```http
POST /auth/refresh
Content-Type: application/json

{
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
```

**Response:**
```json
{
  "code": 1000,
  "result": {
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "authenticated": true
  }
}
```

#### 4. Đăng xuất (Logout)

```http
POST /auth/logout
Content-Type: application/json
Authorization: Bearer {token}

{
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
```

**Response:**
```json
{
  "code": 1000
}
```

### User APIs

#### 1. Đăng ký người dùng mới

```http
POST /users/add
Content-Type: application/json

{
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "2000-01-01",
  "phoneNumber": "0123456789",
  "address": "123 Main St",
  "password": "password123",
  "roles": ["USER"]
}
```

**Validation Rules:**
- `email`: Bắt buộc, định dạng email hợp lệ
- `phoneNumber`: Bắt buộc, đúng 10 chữ số
- `password`: Tối thiểu 8 ký tự
- `dateOfBirth`: Tuổi tối thiểu 16

#### 2. Lấy danh sách người dùng

```http
GET /users
Authorization: Bearer {token}
```

**Yêu cầu**: Role `ADMIN`

**Response:**
```json
{
  "code": 1000,
  "result": [
    {
      "id": "uuid",
      "email": "user@example.com",
      "firstName": "John",
      "lastName": "Doe",
      "dateOfBirth": "2000-01-01",
      "phoneNumber": "0123456789",
      "address": "123 Main St",
      "roles": [...]
    }
  ]
}
```

#### 3. Lấy thông tin người dùng theo ID

```http
GET /users/{id}
Authorization: Bearer {token}
```

**Yêu cầu**: Chỉ có thể xem thông tin của chính mình (trừ ADMIN)

#### 4. Lấy thông tin cá nhân

```http
GET /users/my-info
Authorization: Bearer {token}
```

#### 5. Cập nhật người dùng

```http
PUT /users/update/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "email": "newemail@example.com",
  "firstName": "Jane",
  "lastName": "Doe",
  "dateOfBirth": "1995-05-15",
  "phoneNumber": "0987654321",
  "address": "456 Oak Ave",
  "password": "newpassword123",
  "roles": ["USER"]
}
```

**Yêu cầu**: Chỉ có thể cập nhật thông tin của chính mình

#### 6. Xóa người dùng

```http
DELETE /users/delete/{id}
Authorization: Bearer {token}
```

**Yêu cầu**: Role `ADMIN`

### Role APIs

#### 1. Tạo Role mới

```http
POST /roles/add
Authorization: Bearer {token}
Content-Type: application/json

{
  "name": "MANAGER",
  "description": "Manager role",
  "permissions": ["READ_USER", "UPDATE_USER"]
}
```

#### 2. Lấy danh sách Roles

```http
GET /roles
Authorization: Bearer {token}
```

#### 3. Xóa Role

```http
DELETE /roles/delete/{roleName}
Authorization: Bearer {token}
```

### Permission APIs

#### 1. Tạo Permission mới

```http
POST /permissions/add
Authorization: Bearer {token}
Content-Type: application/json

{
  "name": "READ_USER",
  "description": "Permission to read user information"
}
```

#### 2. Lấy danh sách Permissions

```http
GET /permissions
Authorization: Bearer {token}
```

#### 3. Xóa Permission

```http
DELETE /permissions/delete/{permissionName}
Authorization: Bearer {token}
```

## 🔒 Bảo mật

### JWT Token Security

1. **Token Structure**: Header + Payload + Signature
2. **Algorithm**: HS512 (HMAC-SHA512)
3. **Token Expiration**: Có thể cấu hình trong `application.yaml`
4. **Token Blacklisting**: Token bị vô hiệu hóa khi logout được lưu trong database

### Password Security

- Mật khẩu được mã hóa bằng BCrypt với strength = 10
- Không bao giờ lưu mật khẩu dạng plain text

### Authorization

- **Method-level Security**: Sử dụng `@PreAuthorize` và `@PostAuthorize`
- **Role-based**: Kiểm tra role với `hasRole('ADMIN')`
- **Permission-based**: Kiểm tra permission cụ thể
- **Self-access**: Người dùng chỉ có thể xem/cập nhật thông tin của chính mình

### Error Handling

Hệ thống có xử lý lỗi toàn cục với các mã lỗi chuẩn:

- `1001`: Email đã tồn tại
- `1002`: Số điện thoại đã tồn tại
- `1003`: Email không được để trống
- `1004`: Email không hợp lệ
- `1005`: Số điện thoại không được để trống
- `1006`: Số điện thoại phải có 10 chữ số
- `1007`: Mật khẩu phải có ít nhất 8 ký tự
- `1008`: Không tìm thấy người dùng
- `1009`: Chưa xác thực
- `1010`: Không có quyền truy cập
- `1011`: Không tìm thấy permission
- `1012`: Không tìm thấy role
- `1013`: Ngày sinh không hợp lệ (tuổi < 16)
- `9999`: Lỗi không phân loại

## 🏗 Kiến trúc hệ thống

### Entity Relationship

```
User ──┐
       │ Many-to-Many
       ├──> Role ──┐
       │            │ Many-to-Many
       │            ├──> Permission
       │
       └──> (has password, email, etc.)

InvalidatedToken (for token blacklisting)
```

### Security Flow

1. **Authentication**: User đăng nhập → Nhận JWT token
2. **Authorization**: Request với token → Spring Security validate → Check roles/permissions
3. **Token Validation**: Custom JWT decoder kiểm tra token qua introspect service
4. **Logout**: Token được thêm vào blacklist (InvalidatedToken)

### Data Flow

```
Controller → Service → Repository → Database
     ↓          ↓
   DTO      Entity
     ↓
  Mapper (MapStruct)
```

## 📝 Ghi chú

- Database schema được tự động tạo/update khi ứng dụng khởi động (`ddl-auto: update`)
- Tài khoản admin được tự động tạo khi ứng dụng khởi động lần đầu
- JWT token chứa roles và permissions trong claim `scope`
- Token format: `ROLE_<roleName> <permission1> <permission2> ...`

## 🔗 Tài liệu tham khảo

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [JWT.io](https://jwt.io/) - JWT Debugger
- [Nimbus JOSE + JWT](https://connect2id.com/products/nimbus-jose-jwt)

## 📄 License

Dự án này được phát triển cho mục đích học tập và nghiên cứu.

---

**Tác giả**: Tmh3101  
**Phiên bản**: 0.0.1-SNAPSHOT  
**Ngày cập nhật**: 2024
