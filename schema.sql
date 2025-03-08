-- e-Dastavej Database Schema

-- Create roles table
CREATE TABLE edastavej.roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Create users table
CREATE TABLE edastavej.users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Create user_roles table for many-to-many relationship between users and roles
CREATE TABLE edastavej.user_roles (
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES edastavej.users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES edastavej.roles (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- Create categories table
CREATE TABLE edastavej.categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Create sub_categories table
CREATE TABLE edastavej.sub_categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category_id INTEGER NOT NULL,
    FOREIGN KEY (category_id) REFERENCES edastavej.categories (id) ON DELETE CASCADE
);

-- Create files table
CREATE TABLE edastavej.files (
    id SERIAL PRIMARY KEY,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    file_type VARCHAR(50),
    file_size BIGINT,
    description TEXT,
    category_id INTEGER NOT NULL,
    sub_category_id INTEGER NOT NULL,
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES edastavej.categories (id) ON DELETE CASCADE,
    FOREIGN KEY (sub_category_id) REFERENCES edastavej.sub_categories (id) ON DELETE CASCADE
);

-- CREATE TABLE edastavej.to track user's file uploads
CREATE TABLE edastavej.user_files (
    user_id INTEGER NOT NULL,
    file_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES edastavej.users (id) ON DELETE CASCADE,
    FOREIGN KEY (file_id) REFERENCES edastavej.files (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, file_id)
);