create table yt_videos
(
    id    bigserial    not null,
    title varchar(255) not null unique,
    url   varchar(500) not null,
    primary key (id)
);

insert into yt_videos(title, url) VALUES
( 'OAuth 2.0 and OpenID Connect (in plain English)', 'https://www.youtube.com/watch?v=996OiexHze0'),
('The Why of Go', 'https://www.youtube.com/watch?v=bmZNaUcwBt4'),
('Rob Pike, Public Static Void', 'https://www.youtube.com/watch?v=5kj5ApnhPAE'),
('TDD, Where Did It All Go Wrong (Ian Cooper)', 'https://www.youtube.com/watch?v=EZ05e7EMOLM'),
( 'Simple Made Easy - Rich Hickey', 'https://www.youtube.com/watch?v=LKtk3HCgTa8'),
( 'Good Enough Architecture - Stefan Tilkov', 'https://www.youtube.com/watch?v=PzEox3szeRc'),
( 'The Well-Balanced Programmer (J.B. Rainsberger )', 'https://www.youtube.com/watch?v=XhN6fJYG81A'),
( 'Spring Boot RESTful CRUD Application with IntelliJ IDEA and JPA Buddy', 'https://www.youtube.com/watch?v=GM4FaPsRiyw'),
('JPA Buddy – From Zero to Hero', 'https://www.youtube.com/watch?v=TpD6bT9M1CE'),
( 'Developing Spring Boot Applications With Joy', 'https://www.youtube.com/watch?v=VvWtoaeHQUQ'),
('Working with Flyway Migrations in a Spring Boot Application', 'https://www.youtube.com/watch?v=AMopB9C2bH8'),
( 'My IntelliJ IDEA SetUp for Java Development', 'https://www.youtube.com/watch?v=HJYybOuPym4'),
( 'Spring Modulith Crash Course : Building Modular Monoliths using Spring Boot', 'https://www.youtube.com/watch?v=FkP2aZiBrhg'),
( 'IntelliJ IDEA Tips for Java Developers - Code Faster', 'https://www.youtube.com/watch?v=Kiry69tEIOE'),
('Spring Boot Microservices Complete Tutorial', 'https://www.youtube.com/watch?v=ZKQWwCUEABY'),
('Spring Boot: Build URL Shortener Application - Complete Course (5 Hours)', 'https://www.youtube.com/watch?v=XEgS8yq-zgw'),
('Spring JdbcClient: A better alternative to JdbcTemplate', 'https://youtu.be/oF7vlaJhySE')
;