package com.project.ecom.model.dto;

public record OrderItemRequest(
        int productId,
        int quantity
) {}
