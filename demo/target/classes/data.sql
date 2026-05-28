-- Catégories
INSERT INTO CATEGORIE (id, name) VALUES (1, 'Électronique');
INSERT INTO CATEGORIE (id, name) VALUES (2, 'Vêtements');
INSERT INTO CATEGORIE (id, name) VALUES (3, 'Alimentation');

-- Users (role directement dans la table)
INSERT INTO USERS (id, username, email, password, role, enabled, created_at)
VALUES (1, 'admin', 'admin@mail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN', true, CURRENT_TIMESTAMP);

INSERT INTO USERS (id, username, email, password, role, enabled, created_at)
VALUES (2, 'alice', 'alice@mail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER', true, CURRENT_TIMESTAMP);

-- Produits
INSERT INTO PRODUCT (id, name, description, price, stock_quantity, lien_image, created_at, categorie_id)
VALUES (1, 'iPhone 15', 'Smartphone Apple', 999.99, 50, 'https://example.com/iphone.jpg', CURRENT_TIMESTAMP, 1);

INSERT INTO PRODUCT (id, name, description, price, stock_quantity, lien_image, created_at, categorie_id)
VALUES (2, 'Samsung Galaxy S24', 'Smartphone Samsung', 849.99, 30, 'https://example.com/samsung.jpg', CURRENT_TIMESTAMP, 1);

INSERT INTO PRODUCT (id, name, description, price, stock_quantity, lien_image, created_at, categorie_id)
VALUES (3, 'T-shirt Nike', 'T-shirt sport', 29.99, 200, 'https://example.com/tshirt.jpg', CURRENT_TIMESTAMP, 2);

INSERT INTO PRODUCT (id, name, description, price, stock_quantity, lien_image, created_at, categorie_id)
VALUES (4, 'Jean Levi''s 501', 'Jean classique', 89.99, 100, 'https://example.com/jean.jpg', CURRENT_TIMESTAMP, 2);

INSERT INTO PRODUCT (id, name, description, price, stock_quantity, lien_image, created_at, categorie_id)
VALUES (5, 'Café Arabica', 'Café premium 500g', 12.99, 500, 'https://example.com/cafe.jpg', CURRENT_TIMESTAMP, 3);

-- Commandes
INSERT INTO ORDERS (id, order_date, total_amount, status, user_id, product_id, quantite)
VALUES (1, CURRENT_TIMESTAMP, 999.99, 'PENDING', 2, 1, 1);

INSERT INTO ORDERS (id, order_date, total_amount, status, user_id, product_id, quantite)
VALUES (2, CURRENT_TIMESTAMP, 59.98, 'DELIVERED', 2, 3, 2);