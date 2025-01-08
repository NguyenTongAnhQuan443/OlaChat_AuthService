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
    private int followersCount; -- Số lượng người theo dõi.
    private int followingCount; -- Số lượng người mà người dùng theo dõi.
    private int postsCount; -- Số bài đăng.
    private LocalDateTime createdAt; -- Thời gian tạo tài khoản.
    private LocalDateTime lastLogin; -- Thời gian đăng nhập lần cuối.
```

### Google Cloud - Config
```
spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CLIENT_ID}
spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}
spring.security.oauth2.client.registration.google.redirect-uri=http://localhost:8080/login/oauth2/code/google

spring.security.oauth2.client.registration.google.scope=openid,profile,email,https://www.googleapis.com/auth/user.birthday.read,https://www.googleapis.com/auth/user.addresses.read,https://www.googleapis.com/auth/user.phonenumbers.read

spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/auth
spring.security.oauth2.client.provider.google.token-uri=https://oauth2.googleapis.com/token
spring.security.oauth2.client.provider.google.user-info-uri=https://www.googleapis.com/oauth2/v3/userinfo
spring.security.oauth2.client.provider.google.user-name-attribute=sub

```

