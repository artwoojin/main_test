package com.seb42.main30.seb42_main_030.diary.mapper;

import com.seb42.main30.seb42_main_030.comment.dto.CommentDto;
import com.seb42.main30.seb42_main_030.comment.entity.Comment;
import com.seb42.main30.seb42_main_030.diary.dto.DiaryDto;
import com.seb42.main30.seb42_main_030.diary.entity.Diary;
import com.seb42.main30.seb42_main_030.user.entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-20T21:37:04+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class DiaryMapperImpl implements DiaryMapper {

    @Override
    public Diary diaryPostToDiary(DiaryDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Diary diary = new Diary();

        diary.setTitle( post.getTitle() );
        diary.setBody( post.getBody() );
        diary.setLikeCount( post.getLikeCount() );

        return diary;
    }

    @Override
    public Diary diaryPatchToDiary(DiaryDto.Patch patch) {
        if ( patch == null ) {
            return null;
        }

        Diary diary = new Diary();

        diary.setTitle( patch.getTitle() );
        diary.setBody( patch.getBody() );
        diary.setLikeCount( patch.getLikeCount() );

        return diary;
    }

    @Override
    public CommentDto.Response commentToCommentDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        String userNickname = null;
        long diaryId = 0L;
        long commentId = 0L;
        String body = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        userNickname = commentUserNickname( comment );
        Long diaryId1 = commentDiaryDiaryId( comment );
        if ( diaryId1 != null ) {
            diaryId = diaryId1;
        }
        commentId = comment.getCommentId();
        body = comment.getBody();
        createdAt = comment.getCreatedAt();
        modifiedAt = comment.getModifiedAt();

        CommentDto.Response response = new CommentDto.Response( commentId, diaryId, body, createdAt, modifiedAt, userNickname );

        return response;
    }

    @Override
    public DiaryDto.Response diaryToResponse(Diary diary) {
        if ( diary == null ) {
            return null;
        }

        String userNickname = null;
        List<CommentDto.Response> comments = null;
        long diaryId = 0L;
        String title = null;
        String body = null;
        int viewCount = 0;
        int likeCount = 0;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        userNickname = diaryUserNickname( diary );
        comments = commentListToResponseList( diary.getComments() );
        if ( diary.getDiaryId() != null ) {
            diaryId = diary.getDiaryId();
        }
        title = diary.getTitle();
        body = diary.getBody();
        viewCount = diary.getViewCount();
        likeCount = diary.getLikeCount();
        createdAt = diary.getCreatedAt();
        modifiedAt = diary.getModifiedAt();

        DiaryDto.Response response = new DiaryDto.Response( diaryId, title, body, viewCount, likeCount, createdAt, modifiedAt, userNickname, comments );

        return response;
    }

    @Override
    public List<DiaryDto.Response> diaryToResponses(List<Diary> diaries) {
        if ( diaries == null ) {
            return null;
        }

        List<DiaryDto.Response> list = new ArrayList<DiaryDto.Response>( diaries.size() );
        for ( Diary diary : diaries ) {
            list.add( diaryToResponse( diary ) );
        }

        return list;
    }

    private String commentUserNickname(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User user = comment.getUser();
        if ( user == null ) {
            return null;
        }
        String nickname = user.getNickname();
        if ( nickname == null ) {
            return null;
        }
        return nickname;
    }

    private Long commentDiaryDiaryId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Diary diary = comment.getDiary();
        if ( diary == null ) {
            return null;
        }
        Long diaryId = diary.getDiaryId();
        if ( diaryId == null ) {
            return null;
        }
        return diaryId;
    }

    private String diaryUserNickname(Diary diary) {
        if ( diary == null ) {
            return null;
        }
        User user = diary.getUser();
        if ( user == null ) {
            return null;
        }
        String nickname = user.getNickname();
        if ( nickname == null ) {
            return null;
        }
        return nickname;
    }

    protected List<CommentDto.Response> commentListToResponseList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentDto.Response> list1 = new ArrayList<CommentDto.Response>( list.size() );
        for ( Comment comment : list ) {
            list1.add( commentToCommentDto( comment ) );
        }

        return list1;
    }
}
