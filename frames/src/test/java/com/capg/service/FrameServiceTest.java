package com.capg.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.capg.dto.FramesDTO;
import com.capg.entity.Frames;
import com.capg.exception.FrameNotFoundException;
import com.capg.repository.FramesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FrameServiceTest {

    @Mock
    private FramesRepository framesRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FramesServiceImpl framesService;

    private Frames frames;
    private FramesDTO framesDTO;

    @BeforeEach //Runs before each test case to initialize common objects
    void setUp() {
        frames = new Frames();
        frames.setFrameId("1");
        frames.setFrameName("Stylish Frame");

        framesDTO = new FramesDTO();
        framesDTO.setFrameId("1");
        framesDTO.setFrameName("Stylish Frame");
    }

    @Test
    void testAddFrame() {
        when(modelMapper.map(framesDTO, Frames.class)).thenReturn(frames);
        when(framesRepository.save(frames)).thenReturn(frames);
        when(modelMapper.map(frames, FramesDTO.class)).thenReturn(framesDTO);

        FramesDTO result = framesService.addFrame(framesDTO);

        assertEquals(framesDTO.getFrameId(), result.getFrameId());
    }

    @Test
    void testGetFrameById() {
        when(framesRepository.findById("1")).thenReturn(Optional.of(frames));
        when(modelMapper.map(frames, FramesDTO.class)).thenReturn(framesDTO);

        FramesDTO result = framesService.getFrameById("1");

        assertEquals(framesDTO.getFrameId(), result.getFrameId());
    }

    @Test
    void testGetFrameById_NotFound() {
        when(framesRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(FrameNotFoundException.class, () -> framesService.getFrameById("1"));
    }

    @Test
    void testGetAllFrames() {
        List<Frames> framesList = Arrays.asList(frames);
        when(framesRepository.findAll()).thenReturn(framesList);
        when(modelMapper.map(frames, FramesDTO.class)).thenReturn(framesDTO);

        List<FramesDTO> result = framesService.getAllFrames();

        assertEquals(1, result.size());
    }

    @Test
    void testUpdateFrame() {
        when(framesRepository.findById("1")).thenReturn(Optional.of(frames));

        doAnswer(invocation -> {
            FramesDTO source = invocation.getArgument(0);
            Frames destination = invocation.getArgument(1);
            destination.setFrameId(source.getFrameId());
            destination.setFrameName(source.getFrameName());
            return null;
        }).when(modelMapper).map(any(FramesDTO.class), any(Frames.class));

        when(framesRepository.save(any(Frames.class))).thenReturn(frames);
        when(modelMapper.map(frames, FramesDTO.class)).thenReturn(framesDTO);

        FramesDTO result = framesService.updateFrame("1", framesDTO);

        assertEquals(framesDTO.getFrameId(), result.getFrameId());
    }

    @Test
    void testDeleteFrame() {
        when(framesRepository.existsById("1")).thenReturn(true);
        doNothing().when(framesRepository).deleteById("1");

        assertDoesNotThrow(() -> framesService.deleteFrame("1"));
    }
}
