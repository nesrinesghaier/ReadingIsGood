INSERT INTO customers(EMAIL, ADDRESS, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('nesrinesghaier10@gmail.com', 'test1', 'Nesrine', 'Sghaier',
        '$2a$10$1VoHL/mKfPD4W.P40.GT7OU2CmJ7bcITjR3kGuobTBVQ0NoDPtboi');
INSERT INTO customers(EMAIL, ADDRESS, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('nesrinesghaier11@gmail.com', 'test', 'Nesrine', 'Sghaier',
        '$2a$12$bU1Na4bq2SnVzsrvuhKLd.LAO44eQguNmDU0nLjdhUtnC7giHhyxi');
INSERT INTO customers(EMAIL, ADDRESS, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('nesrinesghaier12@gmail.com', 'test', 'Nesrine', 'Sghaier',
        '$2a$12$FamgpgGZJi3J4TlTZmFRVu4.GsCLhXrybIi0iSO0Wj7uy807U4ODa');

INSERT INTO books(author, publisher, stock, title, type)
VALUES ('author1', 'publisher1', 20, 'title1', 'Drama');
INSERT INTO books(author, publisher, stock, title, type)
VALUES ('author2', 'publisher2', 10, 'title2', 'Thriller');
INSERT INTO books(author, publisher, stock, title, type)
VALUES ('author3', 'publisher3', 5, 'title3', 'Mix');
INSERT INTO orders(order_date_time, status, EMAIL)
VALUES ('2020-12-22 19:10:25-07', 'COMPLETED', 'nesrinesghaier11@gmail.com');
INSERT INTO orders(order_date_time, status, EMAIL)
VALUES ('2021-01-01 13:10:25-07', 'COMPLETED', 'nesrinesghaier11@gmail.com');
INSERT INTO orders(order_date_time, status, EMAIL)
VALUES ('2021-02-01 15:10:25-07', 'COMPLETED', 'nesrinesghaier10@gmail.com');
INSERT INTO orders(order_date_time, status, EMAIL)
VALUES ('2021-02-01 10:10:25-07', 'COMPLETED', 'nesrinesghaier11@gmail.com');
INSERT INTO orders(order_date_time, status, EMAIL)
VALUES ('2021-02-025 16:10:25-07', 'PENDING', 'nesrinesghaier11@gmail.com');
INSERT INTO order_details(price, quantity, book_id, order_id)
VALUES (22.1, 2, 1, 1);
INSERT INTO order_details(price, quantity, book_id, order_id)
VALUES (20, 5, 2, 2);
INSERT INTO order_details(price, quantity, book_id, order_id)
VALUES (25.9, 5, 3, 3);
INSERT INTO order_details(price, quantity, book_id, order_id)
VALUES (22.1, 2, 1, 1);
INSERT INTO order_details(price, quantity, book_id, order_id)
VALUES (22.1, 10, 1, 1);
INSERT INTO order_details(price, quantity, book_id, order_id)
VALUES (22.1, 1, 1, 4);
INSERT INTO order_details(price, quantity, book_id, order_id)
VALUES (20, 1, 2, 5);