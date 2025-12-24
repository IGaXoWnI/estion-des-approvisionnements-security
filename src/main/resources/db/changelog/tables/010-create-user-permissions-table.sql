--liquibase formatted sql

--changeset author:create-user-permissions-table
-- Create user_permissions table for individual user permissions
CREATE TABLE user_permissions (
    user_id BIGINT NOT NULL,
    permission VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, permission),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create index for better performance
CREATE INDEX idx_user_permissions_user_id ON user_permissions(user_id);