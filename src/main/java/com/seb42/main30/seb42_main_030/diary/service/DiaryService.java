package com.seb42.main30.seb42_main_030.diary.service;


import com.seb42.main30.seb42_main_030.diary.dto.DiaryDto;
import com.seb42.main30.seb42_main_030.diary.entity.Diary;
import com.seb42.main30.seb42_main_030.diary.repository.DiaryRepository;
import com.seb42.main30.seb42_main_030.exception.BusinessException;
import com.seb42.main30.seb42_main_030.exception.ExceptionCode;
import com.seb42.main30.seb42_main_030.playlist.dto.PlaylistResponseDto;
import com.seb42.main30.seb42_main_030.playlist.entity.Playlist;
import com.seb42.main30.seb42_main_030.playlist.repository.PlaylistRepository;
import com.seb42.main30.seb42_main_030.user.entity.User;
import com.seb42.main30.seb42_main_030.user.repository.UserRepository;
import com.seb42.main30.seb42_main_030.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class DiaryService {

    private DiaryRepository diaryRepository;
    private UserRepository userRepository;
    private UserService userService;

    private PlaylistRepository playlistRepository;


//    create
    public Diary createDiary(Diary diary, DiaryDto.Post post){
        long userId = userService.getLoginUser().getUserId();
        User user = getUserFromId(userId);
        diary.setUser(user);

        List<Playlist> playlistList = new ArrayList<>();
        for(int i = 0; i < post.getPlaylists().size(); i++){
            Playlist playlist = new Playlist();
            playlist.setUrl(post.getPlaylists().get(i).getUrl());
            playlist.setTitle(post.getPlaylists().get(i).getTitle());
            playlist.setThumbnail(post.getPlaylists().get(i).getThumbnail());
            playlist.setChannelId(post.getPlaylists().get(i).getChannelId());
            playlist.setDiary(diary);
            playlistList.add(playlist);
            playlistRepository.save(playlist);
        }

        diary.setPlaylists(playlistList);
        Diary savedDiary = diaryRepository.save(diary);

        return savedDiary;

//        return diaryRepository.save(diary);
    }

    private User getUserFromId(long userId) {return userRepository.findById(userId).get();}

//    read
    public Diary readDiary(long diaryId){
        Diary diary = existDiary(diaryId);

        diary.setViewCount(diary.getViewCount() + 1);
        return diaryRepository.save(diary);
    }

//    read all
//    public List<Diary> readDiarys(Pageable pageable) {return diaryRepository.findAll(pageable); }
//    public Page<Diary> readDiarys(Pageable pageable){return diaryRepository.findAll(pageable);}

    //service
    public Page<Diary> diaryList(Pageable pageable){return diaryRepository.findAll(pageable);
    }


//    update
    public Diary updateDiary(long diaryId, Diary diary, DiaryDto.Patch patch){
        Diary verifyDiary = verifyWriter(diaryId);

        verifyDiary.setTitle(diary.getTitle());
        verifyDiary.setBody(diary.getBody());

        List<Playlist> playlistList = new ArrayList<>();
        for(int i = 0; i < patch.getPlaylists().size(); i++){
            Playlist playlist = new Playlist();
            playlist.setUrl(patch.getPlaylists().get(i).getUrl());
            playlist.setTitle(patch.getPlaylists().get(i).getTitle());
            playlist.setThumbnail(patch.getPlaylists().get(i).getThumbnail());
            playlist.setChannelId(patch.getPlaylists().get(i).getChannelId());
            playlist.setDiary(diary);
            playlistList.add(playlist);
            playlistRepository.save(playlist);
        }

        verifyDiary.setPlaylists(playlistList);
        verifyDiary.setModifiedAt(LocalDateTime.now());

        return diaryRepository.save(verifyDiary);
    }


//    delete
    public void deleteDiary(long diaryId){
        Diary verifyDiary = verifyWriter(diaryId);

        diaryRepository.deleteById(verifyDiary.getDiaryId());
    }


//    게시글 존재 확인
    private Diary existDiary(long diaryId){
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        return diary.orElseThrow(() -> new BusinessException(ExceptionCode.DIARY_NOT_FOUND));
    }

//    게시글 작성자와 사용자가 같은지 확인
    private Diary verifyWriter(long diaryId){
        long userId = userService.getLoginUser().getUserId();
        Diary diary = existDiary(diaryId);
        if (diary.getUser().getUserId() != userId){
            throw new BusinessException(ExceptionCode.NOT_AUTHORITY);
        }
        return diary;
    }

}
