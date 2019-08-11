package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    private Track track;

    //Create a mock for UserRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private TrackServiceImpl trackServiceImpl;
    private List<Track> list = null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(10);
        track.setName("track name");
        track.setComment("comment1");
        list = new ArrayList<>();
        list.add(track);


    }

    @After
    public void tearDown() {
        this.trackServiceImpl = null;
        this.track = null;
        this.list = null;
    }

    @Test
    public void givenTrackDetailsShouldSaveTracks() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track saveTrack = trackRepository.save(track);
        Assert.assertEquals(track, saveTrack);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void givenTrackDetailsShouldReturnTrackAlreadyExistsException() throws TrackAlreadyExistsException {
        when(trackRepository.save(track)).thenReturn(null);
        Track saveTrack = trackServiceImpl.saveTrack(track);
        Assert.assertEquals(track, saveTrack);


    }

    @Test
    public void givenInputShouldReturnAllTrackDetails() {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userlist = trackRepository.findAll();
        Assert.assertEquals(list, userlist);
    }

//    @Test
//    public void givenIdShouldReturnTrackDetails() {
//    trackRepository.save(track);
//        System.out.println(track);
//    when(trackRepository.findById(track.getId())).thenReturn(java.util.Optional.ofNullable(track));
////    track.setId(10);
////    trackRepository.deleteById(track.getId());
//        List<Track> userlist = trackRepository.findAll();
//        System.out.println(userlist);
//    Assert.assertEquals(track,trackRepository);
//    }

//    @Test
//    public void givenTrackDetailsShouldUpdateThePreviousDetails() {
//    trackRepository.save(track);
//     when(trackRepository.findById(track.getId())).thenReturn(track.getId());
//
//    }

}