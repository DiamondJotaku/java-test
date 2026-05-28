# Backend Spring Boot

Application backend complète avec authentification JWT, gestion des produits, commandes et utilisateurs.

---

## Prérequis

- Java 17+
- Maven 3.8+
- IntelliJ IDEA 
- Postman

---

Installation et exécution

1. Cloner ou télécharger le projet

bash
git clone https://github.com/ton-repo/demo.git
cd demo

2. Lancer le projet

Avec IntelliJ :
- Ouvrir le projet
- Cliquer sur ▶ Run `DemoApplication`

Avec Maven :
bash
mvn spring-boot:run


3. Vérifier que le serveur est démarré

Started on port(s): 8080
Started DemoApplication

---

URLs disponibles

| URL | Description |
|-----|-------------|
| `http://localhost:8080/` | Page d'accueil (publique) |
| `http://localhost:8080/h2-console` | Console base de données H2 |
| `http://localhost:8080/api/auth/**` | Endpoints publics (login/register) |
| `http://localhost:8080/api/products/**` | Endpoints produits (publics en lecture) |
| `http://localhost:8080/api/user/**` | Endpoints utilisateur (authentifié) |
| `http://localhost:8080/api/admin/**` | Endpoints admin (ADMIN uniquement) |
| `http://localhost:8080/api/orders/**` | Endpoints commandes (authentifié) |

---

Description des fonctionnalités

Authentification (`/api/auth`)

| Méthode | Endpoint | Description | Auth |
|---------|----------|-------------|------|
| POST | `/api/auth/register` | Inscription d'un nouvel utilisateur | ❌ Public |
| POST | `/api/auth/login` | Connexion, retourne un JWT | ❌ Public |

Produits (`/api/products`)

| Méthode | Endpoint | Description | Auth |
|---------|----------|-------------|------|
| GET | `/api/products` | Liste tous les produits (paginé) | ❌ Public |
| GET | `/api/products/{id}` | Détail d'un produit | ❌ Public |
| GET | `/api/products/search?query=xxx` | Recherche par nom ou catégorie | ❌ Public |

Utilisateur (`/api/user`)

| Méthode | Endpoint | Description | Auth |
|---------|----------|-------------|------|
| GET | `/api/user/profile` | Voir son profil | ✅ USER / ADMIN |
| PUT | `/api/user/profile` | Modifier son profil | ✅ USER / ADMIN |

Commandes (`/api/orders`)

| Méthode | Endpoint | Description | Auth |
|---------|----------|-------------|------|
| POST | `/api/orders` | Créer une commande | ✅ USER / ADMIN |
| GET | `/api/orders/my-orders` | Voir ses commandes | ✅ USER / ADMIN |

Administration (`/api/admin`)

| Méthode | Endpoint | Description | Auth |
|---------|----------|-------------|------|
| POST | `/api/admin/products` | Créer un produit | ✅ ADMIN |
| PUT | `/api/admin/products/{id}` | Modifier un produit | ✅ ADMIN |
| DELETE | `/api/admin/products/{id}` | Supprimer un produit | ✅ ADMIN |
| GET | `/api/admin/users` | Lister tous les utilisateurs | ✅ ADMIN |
| PUT | `/api/admin/users/{id}` | Modifier un utilisateur | ✅ ADMIN |

---

Structure de la base de données

Table `USERS`
| Colonne | Type | Description |
|---------|------|-------------|
| `id` | BIGINT | Clé primaire auto-incrémentée |
| `username` | VARCHAR | Nom d'utilisateur unique |
| `email` | VARCHAR | Email unique |
| `password` | VARCHAR | Mot de passe hashé (BCrypt) |
| `role` | VARCHAR | Rôle : `USER` ou `ADMIN` |
| `enabled` | BOOLEAN | Compte actif ou non |
| `created_at` | TIMESTAMP | Date de création |

Table `CATEGORIE`
| Colonne | Type | Description |
|---------|------|-------------|
| `id` | BIGINT | Clé primaire auto-incrémentée |
| `name` | VARCHAR | Nom de la catégorie |

Table `PRODUCT`
| Colonne | Type | Description |
|---------|------|-------------|
| `id` | BIGINT | Clé primaire auto-incrémentée |
| `name` | VARCHAR | Nom du produit |
| `description` | VARCHAR | Description du produit |
| `price` | DOUBLE | Prix unitaire |
| `category_id` | BIGINT | Clé étrangère → `CATEGORIE.id` |
| `stock_quantity` | INTEGER | Quantité en stock |
| `lien_image` | VARCHAR | URL de l'image |
| `created_at` | TIMESTAMP | Date de création |

Table `ORDERS`
| Colonne | Type | Description |
|---------|------|-------------|
| `id` | BIGINT | Clé primaire auto-incrémentée |
| `user_id` | BIGINT | Clé étrangère → `USERS.id` |
| `product_id` | BIGINT | Clé étrangère → `PRODUCT.id` |
| `order_date` | TIMESTAMP | Date de la commande |
| `total_amount` | DOUBLE | Montant total |
| `status` | VARCHAR | `PENDING`, `PROCESSING`, `SHIPPED`, `DELIVERED` |
| `quantite` | INTEGER | Quantité commandée |

Schéma des relations

CATEGORIE (1) ──────< PRODUCT (N)
USERS     (1) ──────< ORDERS  (N)
PRODUCT   (1) ──────< ORDERS  (N)

---

Comptes de test

| Username | Password | Rôle | Accès |
|----------|----------|------|-------|
| `admin` | `1234` | ADMIN | Tout l'API |
| `alice` | `1234` | USER | Profil + Commandes |

---

Collection Postman

Importer la collection

Copie ce JSON et importe-le dans Postman via **File → Import → Raw Text** :

```json
{
  "info": {
    "name": "API E-Commerce Spring Boot",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "variable": [
    { "key": "base_url", "value": "http://localhost:8080" },
    { "key": "token", "value": "" }
  ],
  "item": [
    {
      "name": "Auth",
      "item": [
        {
          "name": "Register",
          "request": {
            "method": "POST",
            "url": "{{base_url}}/api/auth/register",
            "header": [{ "key": "Content-Type", "value": "application/json" }],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"username\": \"bob\",\n  \"email\": \"bob@mail.com\",\n  \"password\": \"1234\"\n}"
            }
          }
        },
        {
          "name": "Login",
          "request": {
            "method": "POST",
            "url": "{{base_url}}/api/auth/login",
            "header": [{ "key": "Content-Type", "value": "application/json" }],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"username\": \"admin\",\n  \"password\": \"1234\"\n}"
            }
          }
        }
      ]
    },
    {
      "name": "Products",
      "item": [
        {
          "name": "GET All Products",
          "request": {
            "method": "GET",
            "url": "{{base_url}}/api/products"
          }
        },
        {
          "name": "GET Product by ID",
          "request": {
            "method": "GET",
            "url": "{{base_url}}/api/products/1"
          }
        },
        {
          "name": "Search Products",
          "request": {
            "method": "GET",
            "url": "{{base_url}}/api/products/search?query=iPhone"
          }
        }
      ]
    },
    {
      "name": "Orders",
      "item": [
        {
          "name": "Create Order",
          "request": {
            "method": "POST",
            "url": "{{base_url}}/api/orders",
            "header": [
              { "key": "Content-Type", "value": "application/json" },
              { "key": "Authorization", "value": "Bearer {{token}}" }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"productId\": 1,\n  \"quantite\": 2\n}"
            }
          }
        },
        {
          "name": "My Orders",
          "request": {
            "method": "GET",
            "url": "{{base_url}}/api/orders/my-orders",
            "header": [{ "key": "Authorization", "value": "Bearer {{token}}" }]
          }
        }
      ]
    },
    {
      "name": "Admin",
      "item": [
        {
          "name": "GET All Users",
          "request": {
            "method": "GET",
            "url": "{{base_url}}/api/admin/users",
            "header": [{ "key": "Authorization", "value": "Bearer {{token}}" }]
          }
        },
        {
          "name": "Create Product",
          "request": {
            "method": "POST",
            "url": "{{base_url}}/api/admin/products",
            "header": [
              { "key": "Content-Type", "value": "application/json" },
              { "key": "Authorization", "value": "Bearer {{token}}" }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"name\": \"MacBook Pro\",\n  \"description\": \"Laptop Apple\",\n  \"price\": 1999.99,\n  \"stockQuantity\": 10,\n  \"lienImage\": \"https://example.com/macbook.jpg\",\n  \"category\": { \"id\": 1 }\n}"
            }
          }
        },
        {
          "name": "Delete Product",
          "request": {
            "method": "DELETE",
            "url": "{{base_url}}/api/admin/products/1",
            "header": [{ "key": "Authorization", "value": "Bearer {{token}}" }]
          }
        }
      ]
    },
    {
      "name": "User",
      "item": [
        {
          "name": "GET Profile",
          "request": {
            "method": "GET",
            "url": "{{base_url}}/api/user/profile",
            "header": [{ "key": "Authorization", "value": "Bearer {{token}}" }]
          }
        },
        {
          "name": "UPDATE Profile",
          "request": {
            "method": "PUT",
            "url": "{{base_url}}/api/user/profile",
            "header": [
              { "key": "Content-Type", "value": "application/json" },
              { "key": "Authorization", "value": "Bearer {{token}}" }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"email\": \"nouveau@mail.com\"\n}"
            }
          }
        }
      ]
    }
  ]
}
```

Utilisation du token

Après un **Login**, copie le token de la réponse et :
1. Va dans **Collection → Variables**
2. Colle le token dans la variable `token`
3. Toutes les requêtes protégées l'utiliseront automatiquement

---

Structure du projet

```
src/main/java/com/example/demo/
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── AdminController.java
│   ├── HomeController.java
│   ├── OrderController.java
│   ├── ProductController.java
│   └── UserController.java
├── dto/
│   ├── JwtResponse.java
│   ├── LoginRequest.java
│   ├── OrderRequest.java
│   ├── RegisterRequest.java
│   └── UpdateProfileRequest.java
├── entity/
│   ├── Categorie.java
│   ├── Order.java
│   ├── Product.java
│   └── User.java
├── enums/
│   ├── OrderStatus.java
│   └── Role.java
├── repository/
│   ├── CategorieRepository.java
│   ├── OrderRepository.java
│   ├── ProductRepository.java
│   └── UserRepository.java
├── security/
│   ├── JwtFilter.java
│   ├── JwtUtil.java
│   └── UserDetailsServiceImpl.java
├── service/
│   ├── OrderService.java
│   ├── ProductService.java
│   └── UserService.java
└── DemoApplication.java

src/main/resources/
├── application.properties
└── data.sql
```

---

Sécurité

- Mots de passe hashés avec **BCrypt**
- Authentification sans état via **JWT** (valide 24h)
- Rôles enregistrés en dur à l'inscription (`USER` par défaut, `ADMIN` si précisé)
