package com.fanxan.bepo.controller;

import com.fanxan.bepo.exception.ResourceNotFoundException;
import com.fanxan.bepo.models.WebResponse;
import com.fanxan.bepo.models.entity.PurchaseOrderHeader;
import com.fanxan.bepo.models.service.PurchaseOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class PurchaseOrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PurchaseOrderService purchaseOrderService;

    @InjectMocks
    private PurchaseOrderController purchaseOrderController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(purchaseOrderController).build();
    }

    @Test
    void testGetPurchaseOrdersById_Success() throws Exception {
        PurchaseOrderHeader po = new PurchaseOrderHeader();
        po.setId(1);
        when(purchaseOrderService.getPurchaseOrderById(1)).thenReturn(Optional.of(po));

        mockMvc.perform(get("/api/po").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.[0].id").value(1));
    }

    @Test
    void testGetPurchaseOrdersById_NotFound() throws Exception {
        when(purchaseOrderService.getPurchaseOrderById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/po").param("id", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePurchaseOrder() throws Exception {
        PurchaseOrderHeader po = new PurchaseOrderHeader();
        po.setId(1);
        when(purchaseOrderService.createPurchaseOrder(any())).thenReturn(po);

        mockMvc.perform(post("/api/po")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"New PO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Purchase Order created successfully"))
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    void testUpdatePurchaseOrder() throws Exception {
        PurchaseOrderHeader po = new PurchaseOrderHeader();
        po.setId(1);
        when(purchaseOrderService.updatePurchaseOrder(eq(1), any())).thenReturn(Optional.of(po));

        mockMvc.perform(put("/api/po/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Updated PO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Purchase Order updated successfully"))
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    void testDeletePurchaseOrder_Success() throws Exception {
        when(purchaseOrderService.deletePurchaseOrder(1)).thenReturn(true);

        mockMvc.perform(delete("/api/po/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Purchase Order deleted successfully"));
    }

    @Test
    void testDeletePurchaseOrder_NotFound() throws Exception {
        when(purchaseOrderService.deletePurchaseOrder(1)).thenReturn(false);

        mockMvc.perform(delete("/api/po/1"))
                .andExpect(status().isNotFound());
    }
}
