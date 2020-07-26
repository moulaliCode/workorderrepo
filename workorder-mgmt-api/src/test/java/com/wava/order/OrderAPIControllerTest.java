package com.wava.order;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.wava.ordermgmt.controller.WorkOrderApiController;
import com.wava.ordermgmt.model.Order;
import com.wava.ordermgmt.service.WorkOrderService;

@WebMvcTest(controllers = WorkOrderApiController.class)
public class OrderAPIControllerTest {

	private static final String CREATE_ORDER_JSON = "{\"orderDate\":\"\",  \"orderStatus\":\"\",  \"orderTotal\":\"8\",   \"channelType\": \"store\", "
			+ " \"orderLineItems\":[  {     \"orderLineItemNumber\":\"1\",      	\"orderDescription\":\"Spicy\", "
			+ "\"quantity\":\"1\",  \"price\":\"5\", \"topping1\":\"Onion\",      	\"topping2\":\"Pepper\",      	"
			+ "\"topping3\":\"Jalppino\",      	\"productCode\":\"VegPrizza\" },   	{      \"orderLineItemNumber\":\"2\",      	"
			+ "\"orderDescription\":\"Normal\",      	\"quantity\":\"1\",      	\"price\":\"3\",    "
			+ "  \"productCode\":\"SandWich\"	}   ]} ";

	private static final String ADD_URI = "/ordermgmt/order";

	@InjectMocks
	private WorkOrderApiController orderController;

	private MockMvc mockMvc;

	@Mock
	private WorkOrderService orderService;

	private static final Boolean TRUE = Boolean.TRUE;
	private static final Boolean FALSE = Boolean.FALSE;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();


	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateOrder_201() throws Exception {
		final String newStationId = UUID.randomUUID().toString();
		when(orderService.createOrder(any(Order.class))).thenReturn(orderId);

		mockMvc.perform(post(ADD_URI).content(CREATE_ORDER_JSON).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(print())
		.andExpect(status().isCreated());
		//.andExpect(header().string("orderLineItems",notNullValue()));

		//verify(orderService).createOrder(any(Order.class));
	}
	
	// Test case for validating OrderId from Service
	@Test
	public void getOrder_201() throws Exception {
		final String orderId = UUID.randomUUID().toString();
		when(orderService.getOrder(any(Order.class))).thenReturn(orderId);
		mockMvc.perform(get(ADD_URI).andExpect(status().isOk())
		        .andExpect(content().string("The Order exists.")));
	}


}
