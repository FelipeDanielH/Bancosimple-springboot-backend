package com.softease.BancoSimple.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softease.BancoSimple.dto.RecargaDTO;
import com.softease.BancoSimple.service.RecargaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RecargaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecargaService recargaService;

    @InjectMocks
    private RecargaController recargaController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(recargaController).build();
    }

    @Test
    public void testCrearRecarga() throws Exception {
        RecargaDTO recargaDTO = new RecargaDTO(1, 100, BigDecimal.valueOf(100), "PENDIENTE", LocalDateTime.now());

        when(recargaService.crearRecarga(any(RecargaDTO.class))).thenReturn(recargaDTO);

        mockMvc.perform(post("/recargas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recargaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.monto").value(100))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));

        verify(recargaService, times(1)).crearRecarga(any(RecargaDTO.class));
    }

    @Test
    public void testListarRecargas() throws Exception {
        RecargaDTO recargaDTO1 = new RecargaDTO(1, 100, BigDecimal.valueOf(100), "PENDIENTE", LocalDateTime.now());
        RecargaDTO recargaDTO2 = new RecargaDTO(2, 200, BigDecimal.valueOf(200), "COMPLETADO", LocalDateTime.now());

        List<RecargaDTO> recargas = Arrays.asList(recargaDTO1, recargaDTO2);

        when(recargaService.listarRecargas()).thenReturn(recargas);

        mockMvc.perform(get("/recargas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(recargaService, times(1)).listarRecargas();
    }

    @Test
    public void testObtenerRecargaPorId() throws Exception {
        RecargaDTO recargaDTO = new RecargaDTO(1, 100, BigDecimal.valueOf(100), "PENDIENTE", LocalDateTime.now());

        when(recargaService.obtenerRecargaPorId(1)).thenReturn(recargaDTO);

        mockMvc.perform(get("/recargas/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.monto").value(100))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));

        verify(recargaService, times(1)).obtenerRecargaPorId(1);
    }
}
