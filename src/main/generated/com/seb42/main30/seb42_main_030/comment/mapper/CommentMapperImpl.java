package com.seb42.main30.seb42_main_030.comment.mapper;

import com.seb42.main30.seb42_main_030.comment.dto.CommentDto;
import com.seb42.main30.seb42_main_030.comment.entity.Comment;
import com.seb42.main30.seb42_main_030.diary.entity.Diary;
import com.seb42.main30.seb42_main_030.user.entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-24T11:22:57+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentPostToComment(CommentDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setDiary( postToDiary( post ) );
        comment.setBody( post.getBody() );

        return comment;
    }

    @Override
    public Comment commentPatchToComment(CommentDto.Patch patch) {
        if ( patch == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setDiary( patchToDiary( patch ) );
        comment.setCommentId( patch.getCommentId() );
        comment.setBody( patch.getBody() );

        return comment;
    }

    @Override
    public CommentDto.Response commentToCommentDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        long diaryId = 0L;
        String userNickname = null;
        long commentId = 0L;
        String body = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        Long diaryId1 = commentDiaryDiaryId( comment );
        if ( diaryId1 != null ) {
            diaryId = diaryId1;
        }
        userNickname = commentUserNickname( comment );
        commentId = comment.getCommentId();
        body = comment.getBody();
        createdAt = comment.getCreatedAt();
        modifiedAt = comment.getModifiedAt();

        CommentDto.Response response = new CommentDto.Response( commentId, diaryId, body, createdAt, modifiedAt, userNickname );

        return response;
    }

    @Override
    public List<CommentDto.Response> commentsToResponses(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentDto.Response> list = new ArrayList<CommentDto.Response>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( commentToCommentDto( comment ) );
        }

        return list;
    }

    protected Diary postToDiary(CommentDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Diary diary = new Diary();

        diary.setDiaryId( post.getDiaryId() );

        return diary;
    }

    protected Diary patchToDiary(CommentDto.Patch patch) {
        if ( patch == null ) {
            return null;
        }

        Diary diary = new Diary();

        diary.setDiaryId( patch.getDiaryId() );

        return diary;
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
}
