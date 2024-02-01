package org.example.integrationTests;

import org.example.TestContainersAbstractEnv;
import org.example.entity.LineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class BatchControllerIntegrationTests extends TestContainersAbstractEnv {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LineRepository lineRepository;


    @Test
    public void sampleIntegrationTest () throws Exception {
        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/batch/launch"));

        // Then
        response.andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertTrue(lineRepository.findAll().size() > 100);
        Assertions.assertEquals(0,
                lineRepository.findAll().stream().filter(line -> line.getAge() > 60).toList().size());
    }
}
