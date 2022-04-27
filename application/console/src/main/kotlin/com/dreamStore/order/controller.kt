package com.dreamStore.order

import com.dreamStore.order.dto.RegisterOrderDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService
){
    @GetMapping
    fun getOrders(): List<Order> {
        return orderService.getOrders()
    }

    @PostMapping
    fun registerOrders(@RequestBody registerOrderDTO: RegisterOrderDTO): ResponseEntity<String> {
        orderService.registerOrder(registerOrderDTO)
        return ResponseEntity.ok().build()
    }
}