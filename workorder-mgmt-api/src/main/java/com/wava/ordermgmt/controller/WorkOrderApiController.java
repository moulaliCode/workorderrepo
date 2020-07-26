package com.wava.ordermgmt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wava.ordermgmt.model.Order;
import com.wava.ordermgmt.service.WorkOrderService;

@RestController
public class WorkOrderApiController {
	private WorkOrderService workOrderService;

	public WorkOrderApiController(WorkOrderService workOrderService) {
		this.workOrderService = workOrderService;
	}

	@PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Order createOrder(@RequestBody Order order) {
		return workOrderService.saveOrder(order);
	}

	@DeleteMapping("/orders/delete/{orderId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable long orderId) {
		workOrderService.deleteOrder(orderId);
	}
	
	@GetMapping("/orders/{orderId}")
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public void getOders(@PathVariable long orderId) {
	workOrderService.getOrder(orderId);
}

}
