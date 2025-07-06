package com.capg.controller;
 
import com.capg.dto.FramesDTO;
import com.capg.dto.ProductDTO;
import com.capg.service.FramesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import java.util.Arrays;
import java.util.List;
 
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
public class FrameControllerTest {
 
    private MockMvc mockMvc;
 
    @Mock
    private FramesService frameService;
 
    @InjectMocks
    private FrameController frameController;
 
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(frameController).build();
    }
 
    @Test
    public void testGetAllFrames() throws Exception {
        FramesDTO frame1 = new FramesDTO("FRAME-1", "eyeglass", "Ray-Ban", "blue", 1800,
                "url", "rectangle", 10);
        FramesDTO frame2 = new FramesDTO("FRAME-2", "sunglass", "FastTrack", "black", 2200,
                "url2", "round", 5);
 
        when(frameService.getAllFrames()).thenReturn(Arrays.asList(frame1, frame2));
 
        mockMvc.perform(get("/allframes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].frameId").value("FRAME-1"))
                .andExpect(jsonPath("$[1].frameId").value("FRAME-2"));
    }
 
    @Test
    public void testGetFrameById_Found() throws Exception {
        FramesDTO frame = new FramesDTO("FRAME-1", "eyeglass", "Ray-Ban", "blue", 1800,
                "url", "rectangle", 10);
 
        when(frameService.getFrameById("FRAME-1")).thenReturn(frame);
 
        mockMvc.perform(get("/FRAME-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.frameId").value("FRAME-1"))
                .andExpect(jsonPath("$.frameName").value("eyeglass"));
    }
 
    @Test
    public void testGetFrameById_NotFound() throws Exception {
        when(frameService.getFrameById("FRAME-404")).thenReturn(null);
 
        mockMvc.perform(get("/FRAME-404"))
                .andExpect(status().isNotFound());
    }
 
    @Test
    public void testAddFrame() throws Exception {
        FramesDTO input = new FramesDTO("FRAME-1", "eyeglass", "Ray-Ban", "blue", 1800,
                "url", "rectangle", 10);
 
        when(frameService.addFrame(any(FramesDTO.class))).thenReturn(input);
 
        String json = """
                {
                    "frameId": "FRAME-1",
                    "frameName": "eyeglass",
                    "brand": "Ray-Ban",
                    "color": "blue",
                    "price": 1800,
                    "imageUrl": "url",
                    "shape": "rectangle",
                    "quantity": 10
                }
                """;
 
        mockMvc.perform(post("/add-frame")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.frameId").value("FRAME-1"));
    }
 
    @Test
    public void testUpdateFrame_Found() throws Exception {
        FramesDTO updated = new FramesDTO("FRAME-1", "updated-glass", "BrandX", "red", 2000,
                "url", "oval", 15);
 
        when(frameService.updateFrame(eq("FRAME-1"), any(FramesDTO.class))).thenReturn(updated);
 
        String json = """
                {
                    "frameId": "FRAME-1",
                    "frameName": "updated-glass",
                    "brand": "BrandX",
                    "color": "red",
                    "price": 2000,
                    "imageUrl": "url",
                    "shape": "oval",
                    "quantity": 15
                }
                """;
 
        mockMvc.perform(put("/update-frame/FRAME-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.frameName").value("updated-glass"));
    }
 
    @Test
    public void testUpdateFrame_NotFound() throws Exception {
        when(frameService.updateFrame(eq("FRAME-404"), any(FramesDTO.class))).thenReturn(null);
 
        String json = """
                {
                    "frameId": "FRAME-404",
                    "frameName": "ghost-frame",
                    "brand": "Ghost",
                    "color": "grey",
                    "price": 0,
                    "imageUrl": "none",
                    "shape": "ghost",
                    "quantity": 1
                }
                """;
 
        mockMvc.perform(put("/update-frame/FRAME-404")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }
 
    @Test
    public void testDeleteFrame_Found() throws Exception {
        when(frameService.deleteFrame("FRAME-1")).thenReturn(true);
 
        mockMvc.perform(delete("/FRAME-1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Frame with ID FRAME-1 deleted successfully"));
    }
 
    @Test
    public void testDeleteFrame_NotFound() throws Exception {
        when(frameService.deleteFrame("FRAME-404")).thenReturn(false);
 
        mockMvc.perform(delete("/FRAME-404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Frame with ID FRAME-404 not found"));
    }
 

}
 
 
