# Project: Auth Google / Account OlaChat

## Config

### MariaDB

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mariadb://localhost:3306/OlaChatAuthDB?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=sapassword
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
# spring.jpa.show-sql=true
```

### Attribute

```
    private Long id; -- Id
    private String email; -- Email
    private String phone; -- Phone
    private String firstName; -- First Name
    private String lastName; -- Last Name
    private LocalDateTime dateOfBirth; -- DOB
    private String gender; -- Giới Tính
    private String bio; -- Bio
    private String profilePicture; -- Avatar
    private String province; -- Tỉnh
    private String district; -- Quận
    private String ward; -- Huyện
    private int followersCount; -- Số lượng người theo dõi.
    private int followingCount; -- Số lượng người mà người dùng theo dõi.
    private int postsCount; -- Số bài đăng.
    private LocalDateTime createdAt; -- Thời gian tạo tài khoản.
    private LocalDateTime updatedAt; -- Thời gian cập nhật thông tin lần cuối.
    private LocalDateTime lastLogin; -- Thời gian đăng nhập lần cuối.
```

### Google Cloud
